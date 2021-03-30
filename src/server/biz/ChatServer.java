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
	//用户名和socket的一个映射
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
//					System.out.println("开始监听");
					socket = serverSocket.accept();
					if(socket!=null) {
						//新登陆用户
						
						ObjectInputStream receiveStream=new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
						//获取这个用户的名字，并将输入流加入map
						String clientName=getClientName(receiveStream);
						System.out.println(clientName+"上线");
						online.put(clientName, true);
						receiveStreams.put(clientName,receiveStream);
						new GetMsgThread(receiveStream,clientName).start();
						
						//添加输出流，并漫游历史消息
						ObjectOutputStream sendStream=new ObjectOutputStream(socket.getOutputStream());											
						sendStreams.put(clientName,sendStream);
						new MsgRoamingThread(sendStream, clientName).start();
					}
//					System.out.println("开始监听");
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
						//用户下线操作，将在线状态重置为false，删除它的输入输出流
						String name = (String)message.getData();
						sendStreams.remove(name);
						receiveStreams.remove(name);
						online.put(name, false);
//						System.out.println("下线");
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
		//初始化历史消息，每新接入一个用户就对它启动此线程
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
				flag.setContent("\n-----------------历史消息加载完毕-----------------\n");
				
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
//			System.out.println("开始发送");
			while(this.isAlive()) {
				try {
					if(!msgList.isEmpty()) {
						//每次处理掉一条消息
						MsgChat msg=msgList.removeLast();
						Message message=new Message();
						message.setData(msg);
						message.setMessageType(Constants.chat_msg);
						
						if(msg.getRecipientName().equals(Constants.allMembers)) {
							//如果是群聊，将消息发给所有人
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
							//私聊则只发给对应的人
							ObjectOutputStream sendStream = sendStreams.get(msg.getRecipientName());
							if(sendStream!=null&&online.get(msg.getRecipientName())) {	//私聊@
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
	//更新用户列表
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
