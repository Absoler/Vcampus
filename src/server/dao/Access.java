package server.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Access {
	public Connection connection;
	static {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//����ucanaccess����
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	Access()
	{
		try {
		connection = DriverManager.getConnection("jdbc:ucanaccess://./db/database.accdb","","");
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Connection getAccessConnection(String user, String pwd) 
	{
		try {
			//��ȡAccess���ݿ�����(Connection)
			this.connection = DriverManager.getConnection("jdbc:ucanaccess://./db/database.accdb", user, pwd);
			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return this.connection;
	}
	public static void main(String[] args) {
		Access access=new Access();
		Connection connection=access.getAccessConnection("", "");
	}
}
