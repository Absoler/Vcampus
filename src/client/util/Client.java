package client.util;
import java.io.BufferedInputStream;

import util.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import util.Message;
public class Client {
	public Message sendRequestToServer (Message clientRequest ) {
		try
		{
			Socket socket = new Socket("localhost",19888);
			socket.setSoTimeout(10000);
			ObjectOutputStream request = new ObjectOutputStream(socket.getOutputStream());
			request.writeObject(clientRequest);
			request.flush();
			socket.shutdownOutput();
			
			ObjectInputStream response = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Message message = (Message)response.readObject();
			response.close();
			socket.close();
			
			if(message!=null)
			{
				return message;
			}
			
			/*Socket socket=new Socket("localhost",8888);
			OutputStream outputStream=socket.getOutputStream();//��ȡһ��������������˷�����Ϣ
            PrintWriter printWriter=new PrintWriter(outputStream);//���������װ�ɴ�ӡ��
            printWriter.print("�������ã�����Balla_����");
            printWriter.flush();
            socket.shutdownOutput();//�ر������
            
            InputStream inputStream=socket.getInputStream();//��ȡһ�������������շ���˵���Ϣ
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//��װ���ַ��������Ч��
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//������
            String info="";
            String temp=null;//��ʱ����
            while((temp=bufferedReader.readLine())!=null){
                info+=temp;
                System.out.println("�ͻ��˽��շ���˷�����Ϣ��"+info);
            }
            
            //�ر����Ӧ����Դ
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();
            */
		}
			catch (UnknownHostException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	public static void main(String[] args) {
		
		try
		{
			/*Message clientRequest = new Message("Lx1234",3);
		    //Socket socket = new Socket("localhost",18888);
			Socket socket = new Socket("10.203.244.213",18888);
			socket.setSoTimeout(10000);
			ObjectOutputStream request = new ObjectOutputStream(socket.getOutputStream());
			request.writeObject(clientRequest);
			request.flush();
			socket.shutdownOutput();
			
			ObjectInputStream response = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Message message = (Message)response.readObject();
			response.close();
			//socket.close();
			
			if(message!=null)
			{
				if(message.getFlag()==1)
					System.out.println("��½�ɹ�");
				else
					System.out.println("��½ʧ��");
			}*/
			
			Socket socket=new Socket("localhost",19888);
			OutputStream outputStream=socket.getOutputStream();//��ȡһ��������������˷�����Ϣ
            PrintWriter printWriter=new PrintWriter(outputStream);//���������װ�ɴ�ӡ��
            printWriter.print("�������ã�����Balla_����");
            printWriter.flush();
            socket.shutdownOutput();//�ر������
            
            InputStream inputStream=socket.getInputStream();//��ȡһ�������������շ���˵���Ϣ
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//��װ���ַ��������Ч��
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//������
            String info="";
            String temp=null;//��ʱ����
            while((temp=bufferedReader.readLine())!=null){
                info+=temp;
                System.out.println("�ͻ��˽��շ���˷�����Ϣ��"+info);
            }
            
            //�ر����Ӧ����Դ
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();
		}
			catch (UnknownHostException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			} 
			/*catch (ClassNotFoundException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
	}
}

