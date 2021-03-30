package client.clientGUIs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import util.Constants;
import util.Message;
import vo.MsgChat;
public class ChatGUI3 extends JFrame {
	Socket socket;
	ObjectOutputStream sendStream;
	ObjectInputStream receiveStream;
	JPanel panel;
	JScrollPane textPane;
	JTable userList;	//���
	JScrollPane userListPane;
	JLabel lblSend;
	JTextField txtSend;
	JTextArea txtContent;
	JButton btnSend;
	
	UserListModel model=null;
	
	//�����û����
	String tblName[] = {"�����û��б�"};
	String tblContents[][] = {{"ȫ���Ա"}};
	
	//���ͻ��˷�����Ϣ��Ŀ��
	String recipientName;
	String name;	//�����ã����ͻ�������
	
	//�ײ�panel
	JPanel topPanel;
	public ChatGUI3(String name) {
		// TODO Auto-generated constructor stub
		super(name);
		this.name=name;
		
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
		
		//���д���
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width1 = screenSize.width;
		int height1 = screenSize.height;
		int x = (width1 - WIDTH ) / 2 + 360;
		int y = (height1 - HEIGHT) / 2;
		this.setLocation(x, y);
		
		
		//�ײ�panel
		topPanel=new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		//�ؼ�panel
		panel=new JPanel();
		panel.add(btnSend);
		panel.add(lblSend);
		panel.add(txtSend);
		
		topPanel.add(textPane,BorderLayout.CENTER);
		userListPane.setPreferredSize(new Dimension(120, 400));
		topPanel.add(userListPane,BorderLayout.EAST);
		topPanel.add(panel,BorderLayout.SOUTH);
		this.add(topPanel);
		
		
		
		this.setSize(600,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
			
			new GetMsg().start();
//			System.out.println("�����߳�����");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��������¼�
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				Message message = new Message();
				message.setMessageType(Constants.sign_out);
				message.setData(name);
				try {
					sendStream.writeObject(message);
					sendStream.flush();
//					System.out.println("sign out");
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String senderName=name;
				String strSend=txtSend.getText();
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
	
	class UserListModel extends DefaultTableModel{
		@Override
		public boolean isCellEditable(int row, int column) {
			// TODO Auto-generated method stub
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
	
	
	//��Ϣ���գ����������Ϣ�Լ���ʾ
	class GetMsg extends Thread{
		@Override
		public void run() {
//			System.out.println("start receving");
			while(this.isAlive()) {
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
						txtContent.append(str);
					}else if(message.getMessageType().equals(Constants.update_ContactList)) {
						ArrayList<String> new_userlist = (ArrayList<String>)message.getData();
						tblContents = toUserList(new_userlist);
						model.setDataVector(tblContents, tblName);
					}
					
					Thread.sleep(200);
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
		new ChatGUI3("zkb").setVisible(true);
	}
}
