package server.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Access {
	public Connection connection;
	static {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//加载ucanaccess驱动
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
			//获取Access数据库连接(Connection)
			this.connection = DriverManager.getConnection("jdbc:ucanaccess://./db/database.accdb", user, pwd);
			System.out.println("数据库连接成功");
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
