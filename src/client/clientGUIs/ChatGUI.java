package client.clientGUIs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.ObjectUtils.Null;

import client.util.Client;
import util.Constants;
import util.Message;
import vo.MsgChat;
public class ChatGUI extends JPanel {
	Socket socket;
	ObjectOutputStream sendStream;
	ObjectInputStream receiveStream;
	JPanel panel;
	JScrollPane textPane;
	JTable userList;	//���
	JScrollPane userListPane;
	JLabel lblSend;
	JTextField txtSend;
	static JTextArea txtContent;
	JButton btnSend;
	
	
	//�߳�
//	Update update;
	GetMsg getMsg;
//	static String add;
//	updateMsg update = null;
	
	UserListModel model=null;
	
	//�����û����
	String tblName[] = {"�����û��б�"};
	String tblContents[][] = {{"ȫ���Ա"}};
	
	//���ͻ��˷�����Ϣ��Ŀ��
	String recipientName;
	String name;	//�����ã����ͻ�������
	
	//�ײ�panel
//	JPanel topPanel;
	public ChatGUI(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
		
//		add=new String();
		
		txtContent=new JTextArea();
		txtContent.setEditable(false);
		textPane=new JScrollPane(txtContent);
		
		lblSend=new JLabel("����");
		txtSend=new JTextField(20);
		btnSend=new JButton("����");
		
		//�ȴ�����������model
		model=new UserListModel();
		model.setDataVector(tblContents, tblName);
		userList = new JTable(model);
		//���������趨
		userListPane=new JScrollPane(userList);
		userList.setRowSelectionAllowed(true);
		userList.setColumnSelectionAllowed(true);
		userList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = userList.rowAtPoint(e.getPoint());
				int col = userList.columnAtPoint(e.getPoint());
				recipientName=(String)userList.getValueAt(row, col);
				
				System.out.println(recipientName);
			}
		});
		
		
		this.setLayout(new BorderLayout());
		
		//�ؼ�panel
		panel=new JPanel();
		panel.add(btnSend);
		panel.add(lblSend);
		panel.add(txtSend);
		
		this.add(textPane,BorderLayout.CENTER);
		userListPane.setPreferredSize(new Dimension(120, 400));
		this.add(userListPane,BorderLayout.EAST);
		this.add(panel,BorderLayout.SOUTH);
		
		
		
		
	
		
//		System.out.println("1");
		try {
			
			socket=new Socket();
			SocketAddress sAddress=new InetSocketAddress("localhost",9999);
			socket.connect(sAddress);
			
//			System.out.println("���ӳɹ�");
//			System.out.println("1");
			sendStream=new ObjectOutputStream(socket.getOutputStream());
			//��һ����Ϣ��һ�±��ͻ�������
			sendStream.writeObject(login(this.name));
			sendStream.flush();
			
			receiveStream=new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
//			System.out.println("IO����");
			
			getMsg = new GetMsg();
//			getMsg.setPriority(10);
			getMsg.start();
//			update.setPriority(10);
//			update.start();
//			update = new updateMsg();
//			update.start();
//			System.out.println("�����߳�����");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��������¼�
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				// TODO Auto-generated method stub
//				super.windowClosing(e);
//				Message message = new Message();
//				message.setMessageType(Constants.sign_out);
//				message.setData(name);
//				try {
//					sendStream.writeObject(message);
//					sendStream.flush();
//					System.out.println("sign out");
//				}catch (Exception e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
		
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String senderName=name;
				String strSend=txtSend.getText();
//				txtContent.append("\n1");
				if(strSend.equals("")) {
					System.out.println(txtContent.getText());
					txtContent.paintImmediately(txtContent.getBounds());
//					txtContent.append(add);
//					txtContent.append("2");
//					add="";
				}
				if(!strSend.equals("")) {
					MsgChat msg=new MsgChat();
					msg.setContent(strSend);
//					msg.setRecipientId(-1);
					msg.setRecipientName(recipientName);
//					msg.setSenderId(1);
					msg.setSenderName(senderName);
					
					
					Message message=new Message();
					message.setMessageType(Constants.chat_msg);
					message.setData(msg);
					try {
						sendStream.writeObject(message);
//						System.out.println("send to"+msg.getSenderName());
						sendStream.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
//					System.out.println("send");
					txtSend.setText("");
				}
			}
		});
		
	}
	
//	class Update extends Thread{
//		@Override
//		public void run() {
//			while(true) {
//				txtContent.repaint();
//				txtContent.paintImmediately(txtContent.getBounds());
//			}
//		}
//	}
	
	public void signout() {
		Message message = new Message();
		message.setMessageType(Constants.sign_out);
		message.setData(name);
		try {
			sendStream.writeObject(message);
			sendStream.flush();
			System.out.println("sign out");
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	class UserListModel extends DefaultTableModel{
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
	
	//ģ���½״̬���������û������������
	private Message login(String name) {
		MsgChat init = new MsgChat();
		init.setContent("");
		init.setSenderName(name);
		init.setRecipientName(Constants.allMembers);
		
		Message message=new Message();
		message.setData(init);
		message.setMessageType(Constants.chatLogin);
//		System.out.println(init.getSenderName());
//		System.out.println(((MsgChat)message.getData()).getSenderName());
		return message;
	}
	
	//���������Ϣ��ʾ
//	class updateMsg extends Thread{
//		@Override
//		public void run() {
//			txtContent.append(add);
//		}
//	}
//	
	//��Ϣ���գ����������Ϣ�Լ���ʾ
	class GetMsg extends Thread{
		@Override
		public void run() {
//			System.out.println("start receving");
			while(true) {
				try {
//					System.out.println("Client.GetMsg.run()");
					Message message=(Message)receiveStream.readObject();
//					System.out.println("get: "+strMsg);
					//����Ѵ�����Ϣ�͸����û��б�������ֲ�ͬ�����message������socket�ĳ�ͻ����
					if(message.getMessageType().equals(Constants.chat_msg)) {
						//�յ�������Ϣֱ����ʾ���ɣ�����ڷ�����Ѿ����ֹ���
//						System.out.println("get one");
						MsgChat msg = (MsgChat)message.getData();
						String str=msg.getSenderName()+" send to "
								+msg.getRecipientName()+": "+msg.getContent()+"\n";
						
						System.out.println(str);
//						add+=str;
//						new updateMsg().start();
//						updateTextArea(str);
						
//						add+=str;
						txtContent.append(str);
//						txtContent.repaint();
//						txtContent.invalidate();
//						txtContent.paintImmediately(txtContent.getBounds());
//						System.out.println(txtContent.getText());			
						
					}else if(message.getMessageType().equals(Constants.update_ContactList)) {
						ArrayList<String> new_userlist = (ArrayList<String>)message.getData();
						tblContents = toUserList(new_userlist);
						model.setDataVector(tblContents, tblName);
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//�����յ����û��б�תΪ��ά����
	private String[][] toUserList(ArrayList<String> userlist){

		String[] tmp = new String[userlist.size()];
		tmp=(String[])userlist.toArray(tmp);
	
		String [][] new_userlist = new String[tmp.length+1][1];
		int i=0;
		for(String user:tmp) {
			new_userlist[i++][0] = user;
		}
		new_userlist[i][0] = "ȫ���Ա";
		
		return new_userlist;
	}
	
	public static void main(String[] args) {
//		System.out.println("1");
		JFrame frame = new JFrame();
		frame.add(new ChatGUI("zkb"));
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
