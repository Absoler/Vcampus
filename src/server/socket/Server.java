package server.socket;

import java.net.ServerSocket;
import java.net.Socket;

import server.biz.ChatServer;
import server.biz.*;
public class Server implements Runnable{
	//private ArrayList<ContactInfo> onlineUsers;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			ChatServer3 chatServer = new ChatServer3();
			ServerSocket serverSocket = new ServerSocket(19888);
			System.out.println("��������������ȴ��ͻ�������..");
			
			while(true)
			{
				Socket socket = serverSocket.accept();
				ServerSocketThread n = new ServerSocketThread(socket);
				Thread t = new Thread(n);
				t.start();
				Thread.sleep(200);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Server server = new Server();
		server.run();
		
	}
	

}
