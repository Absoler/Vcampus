package server.biz;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import util.Message;
import vo.MsgChat;
import server.dao.ChatRoaming;
import server.dao.ChatRoamingImpl;
import util.Constants;

public class ChatServer {
	ServerSocket serverSocket;
	//�û�����socket��һ��ӳ��
	Map<String, ObjectInputStream> receiveStreams = new HashMap<>();
	Map<String, ObjectOutputStream> sendStreams = new HashMap<>();
	
//	ArrayList<ObjectInputStream>receiveStreams=new ArrayList<ObjectInputStream>();
//	ArrayList<ObjectOutputStream>sendStreams=new ArrayList<ObjectOutputStream>();
	
	LinkedList<MsgChat>msgList=new LinkedList<MsgChat>();
	Map<String, Boolean> online = new HashMap<>();
	ChatRoamingImpl chatRoaming;
	
	
	
	private String getClientName(ObjectInputStream receiveStream) {
		MsgChat head=null;
		try {
			Message message = (Message)receiveStream.readObject();
			if(message.getMessageType().equals(Constants.chatLogin)) {
				head=(MsgChat)message.getData();
//				System.out.println(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return head.getSenderName();
	}
	
	public ChatServer() {
		// TODO Auto-generated constructor stub
		try {
			serverSocket=new ServerSocket(9999);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chatRoaming=new ChatRoamingImpl();
		new AcceptSocketThread().start();
		if(sendStreams.size()>0) {
			new SendMsgThread().start();
		}
	}
	
	class AcceptSocketThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(this.isAlive()) {
				Socket socket=null;
				try {
//					System.out.println("��ʼ����");
					socket = serverSocket.accept();
					if(socket!=null) {
						//�µ�½�û�
						
						ObjectInputStream receiveStream=new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
						//��ȡ����û������֣���������������map
						String clientName=getClientName(receiveStream);
						System.out.println(clientName+"����");
						online.put(clientName, true);
						receiveStreams.put(clientName,receiveStream);
						new GetMsgThread(receiveStream,clientName).start();
						
						//������������������ʷ��Ϣ
						ObjectOutputStream sendStream=new ObjectOutputStream(socket.getOutputStream());											
						sendStreams.put(clientName,sendStream);
						new MsgRoamingThread(sendStream, clientName).start();
					}
//					System.out.println("��ʼ����");
					Thread.sleep(100);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class GetMsgThread extends Thread{
		ObjectInputStream receiveStream;
		String clientName;
		public GetMsgThread(ObjectInputStream receiveStream,String clientName) {
			// TODO Auto-generated constructor stub
			this.receiveStream=receiveStream;
			this.clientName=clientName;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(online.get(clientName)) {
				Message message=null;
				try {
					message=(Message)receiveStream.readObject(); 
					if(message.getMessageType().equals(Constants.chat_msg)) {
						MsgChat msg=(MsgChat)message.getData();
						chatRoaming.insert(msg);
						msgList.add(msg);
					}else if(message.getMessageType().equals(Constants.sign_out)) {
						//�û����߲�����������״̬����Ϊfalse��ɾ���������������
						String name = (String)message.getData();
						sendStreams.remove(name);
						receiveStreams.remove(name);
						online.put(name, false);
//						System.out.println("����");
//						chatRoaming.insert(new MsgChat());
					}
//					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
//	class UpdateContactThread extends Thread{
//		
//	}
	class MsgRoamingThread extends Thread{
		//��ʼ����ʷ��Ϣ��ÿ�½���һ���û��Ͷ����������߳�
		ObjectOutputStream sendStream;
		ArrayList<MsgChat> history;
		String recipientName;
		MsgChat flag = new MsgChat();
		
		public MsgRoamingThread(ObjectOutputStream sendStream, String recipientName) {
			this.sendStream=sendStream;
			this.recipientName=recipientName;
		}
		@Override
		public void run() {
			history=chatRoaming.roamingInit();
			try {
				for(MsgChat iChat:history) {
					if(iChat.getRecipientName().equals(this.recipientName)|| 
							iChat.getRecipientName().equals(Constants.allMembers)) {
						Message message = new Message();
						message.setData(iChat);
						message.setMessageType(Constants.chat_msg);
						
//						System.out.println(iChat.getContent());
						sendStream.writeObject(message);
						sendStream.flush();
//						Thread.sleep(190);
					}
				}
				flag.setContent("\n-----------------��ʷ��Ϣ�������-----------------\n");
				
				Message message = new Message();
				message.setData(flag);
				message.setMessageType(Constants.chat_msg);
				
				sendStream.writeObject(message);
				sendStream.flush();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	class SendMsgThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
//			for(MsgChat i:msgList) {
//				System.out.println(i.getContent() );
//			}
//			System.out.println("��ʼ����");
			while(this.isAlive()) {
				try {
					if(!msgList.isEmpty()) {
						//ÿ�δ����һ����Ϣ
						MsgChat msg=msgList.removeLast();
						Message message=new Message();
						message.setData(msg);
						message.setMessageType(Constants.chat_msg);
						
						if(msg.getRecipientName().equals(Constants.allMembers)) {
							//�����Ⱥ�ģ�����Ϣ����������
							Iterator<String> iterator = sendStreams.keySet().iterator();
							while(iterator.hasNext()) {
								String key = iterator.next();
								if(!online.get(key)) {
									continue;
								}
								ObjectOutputStream sendStream = sendStreams.get(key);
								sendStream.writeObject(message);
								sendStream.flush();
							}
						}else{
							//˽����ֻ������Ӧ����
							ObjectOutputStream sendStream = sendStreams.get(msg.getRecipientName());
							if(sendStream!=null&&online.get(msg.getRecipientName())) {	//˽��@
								sendStream.writeObject(message);
								System.out.println(msg.getContent());
								sendStream.flush();
							}
						}
						
					}
					updateUserList();
//					Thread.sleep(400);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	//�����û��б�
	private void updateUserList() {
		Collection<String> tmp=sendStreams.keySet();
		ArrayList<String> userlist = new ArrayList<String>(tmp);
		
		Message update = new Message();
		update.setMessageType(Constants.update_ContactList);
		update.setData(userlist);
		
		Iterator<String> iterator = sendStreams.keySet().iterator();
		try {
			while(iterator.hasNext()) {
				String key = iterator.next();
				ObjectOutputStream sendStream = sendStreams.get(key);
				sendStream.writeObject(update);
				sendStream.flush();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
//		System.out.println("2");
		new ChatServer();
	}
}
