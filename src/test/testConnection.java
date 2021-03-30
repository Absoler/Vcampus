package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class testConnection {
	
	private Connection connection;
	{
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//加载ucanaccess驱动
		} 
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
			
	public Connection getAccessConnection(String path, String user, String pwd) {
		try {
			//获取Access数据库连接(Connection)
			this.connection = DriverManager.getConnection("jdbc:ucanaccess://" + path, user, pwd);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return this.connection;
	}

	public static void main(String[] args) throws Exception {
		testConnection access=new testConnection();
		Connection connection = access.getAccessConnection("D:\\Test\\testStu.mdb", "", "");
		int id=10;
		String pwd="x1234";
		if(access.findById(connection,id)!=0) 
			if(access.loginCheck(connection,id,pwd)==1) {
				System.out.println("You are Logined!");
				access.selectById(connection, id);
			}
			else
				System.out.println("Wrong password!please check it.");
		else 
			System.out.println("The student does not exist!");
	}

	public int insert(Connection connection) throws Exception {
		// ? 是 JDBC 预编译的占位符
		PreparedStatement statement=connection.prepareStatement("insert into student(id,name1,address,age) values(?,?,?,?)");
		statement.setInt(0, 1);//学生编号
		statement.setString(1, "赵六");//学生姓名
		statement.setString(2, "湖南省、衡阳市、珠晖区1");//学生住址
		statement.setInt(3, 20);//学生年龄
		int result = statement.executeUpdate();
		statement.close();
		connection.close();
		return result;
	}

	public int delete(Connection connection) throws Exception {
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate("delete from student where id=3");
		statement.close();
		connection.close();
		return result;
	}

	public int update(Connection connection) throws Exception {
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate("update student set address='湖南省、衡阳市、珠晖区' where id=1");
		statement.close();
		connection.close();
		return result;
	}

	public void selectById(Connection connection,int id) throws Exception {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from student where id="+id);
		while (result.next()) {
			System.out.print(result.getString("id") + "\t");
			System.out.print(result.getString("nickname") + "\t");
			System.out.print(result.getString("pwd") + "\t");
			System.out.print(result.getInt("age") + "\t");
			System.out.print(result.getString("deposit") + "\t");
			System.out.print(result.getString("dormNum") + "\t");
			System.out.print(result.getString("gpa") + "\t");
			System.out.print(result.getString("srtp") + "\t");
			System.out.print(result.getString("borrowNum") + "\t");
			System.out.println();
		}
		statement.close();
		//connection.close();
	}
		
	public int findById(Connection connection,int id) throws Exception {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from student where id="+id);
		int count=0;
		while(result.next())
			count++;
		//statement.close();
		if(count==0)
			return 0;
		return count;
	}

	public int loginCheck(Connection connection,int id,String pwd) throws Exception {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select * from student where id="+id);
		result.next();
		if(result.getString("pwd").equals(pwd))
			return 1;
		else
			return 0;
	}
}

