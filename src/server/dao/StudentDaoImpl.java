package server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.Constants;
import util.Student;

public class StudentDaoImpl implements StudentDao{
	
	private Access access=new Access();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	private ArrayList<Student> rsToStudentList()
	{
		try {
		ArrayList<Student> ls=new ArrayList<Student>();
		do {
			Student stu=new Student();
			stu.setUserType(0);
			stu.setId(rs.getInt("StudentID"));
			stu.setNickName(rs.getString("NickName"));
			stu.setAge(rs.getInt("Age"));
			stu.setPwd(rs.getString("Password"));
			stu.setDeposit(rs.getDouble("Deposit"));
			stu.setBorrowNum(rs.getInt("BorrowNum"));
			stu.setGPA(rs.getDouble("GPA"));
			stu.setDormNum(rs.getString("DormNumber"));
			stu.setSex(rs.getString("Sex"));
			stu.setRealName(rs.getString("RealName"));
			stu.setPunishment(rs.getString("Punishment"));
			stu.setSRTP(rs.getDouble("SRTP"));
			ls.add(stu);
		} while (rs.next());
		return ls;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private Student reToStudent()
	{
		try
		{
			Student stu = new Student();
			stu.setUserType(0);
			stu.setId(rs.getInt("StudentID"));
			stu.setNickName(rs.getString("NickName"));
			stu.setAge(rs.getInt("Age"));
			stu.setPwd(rs.getString("Password"));
			stu.setDeposit(rs.getDouble("Deposit"));
			stu.setBorrowNum(rs.getInt("BorrowNum"));
			stu.setGPA(rs.getDouble("GPA"));
			stu.setDormNum(rs.getString("DormNumber"));
			stu.setSex(rs.getString("Sex"));
			stu.setRealName(rs.getString("RealName"));
			stu.setPunishment(rs.getString("Punishment"));
			stu.setSRTP(rs.getDouble("SRTP"));
			return stu;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insert(Student student) throws RecordAlreadyExistException {
		// TODO Auto-generated method stub
		try
		{
			Student stu = query(student.getId());
			if(stu!=null)throw new RecordAlreadyExistException();
			String sql = "INSERT INTO tblStudent (StudentID, PassWord,NickName,"
					+"Deposit,BorrowNum,GPA,DormNumber,Age,Sex,RealName,Punishment,SRTP) VALUES ( '"
			+student.getId()
			+"' , '"+student.getPwd()
			+"' , '"+student.getNickName()
			+"' , '"+student.getDeposit()
			+"' , '"+student.getBorrowNum()
			+"' , '"+student.getGPA()
			+"' , '"+student.getDormNum()
			+"' , '"+student.getAge()
			+"' , '"+student.getSex()
			+"' , '"+student.getRealName()
			+"' , '"+student.getPunishment()
			+"' , '"+student.getSRTP()
			+"' )";
			stmt = access.connection.prepareStatement(sql);
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}

		return true;
		
	}

	@Override
	public boolean delete(Student student) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		try {
			
			//此处还需要添加一个还书的操作
			Student stu = query(student.getId());
			if(stu==null) throw new RecordNotFoundException();
			String sql = "DELETE * FROM tblStudent WHERE StudentID=?";
			//String sql = "DELETE * FROM tblStudent";
			stmt = access.connection.prepareStatement(sql);
			stmt.setInt(1, student.getId());
			stmt.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean update(Student student) throws RecordNotFoundException{
		// TODO Auto-generated method stub
//		try
//		{
//			Student stu = query(student.getId());
//			if(stu==null) throw new RecordNotFoundException();
//			String sql = "UPDATE tblStudent SET "
//		}catch(SQLException e)
//		{
//			e.printStackTrace();
//			return false;
//		}
		try
		{
			Student stu = query(student.getId());
			if(stu==null) throw new RecordNotFoundException();
			String sql="UPDATE tblStudent SET NickName=?,Password=?,Deposit=?,"
					+ "BorrowNum=?,GPA=?,DormNumber=?,Age=?,Sex=?,Punishment=?,"
					+ "RealName=?,SRTP=?"
					+ "WHERE StudentID=?";
			stmt = access.connection.prepareStatement(sql);
			stmt.setString(1, student.getNickName());
			stmt.setString(2, student.getPwd());
			stmt.setDouble(3, student.getDeposit());
			stmt.setInt(4, student.getBorrowNum());
			stmt.setDouble(5, student.getGPA());
			stmt.setString(6, student.getDormNum());
			stmt.setInt(7, student.getAge());
			stmt.setString(8, student.getSex());
			stmt.setString(9, student.getPunishment());
			stmt.setString(10,student.getRealName());
			stmt.setDouble(11, student.getSRTP());
			stmt.setInt(12, student.getId());
			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public Student query(int studentID) {
		// TODO Auto-generated method stub
		try
		{
			String sql="select * from tblStudent where StudentID ="+"'"+studentID+"'";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next())
			{
				return reToStudent();
			}
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
		
	}
	
	public Student queryByNickName(String nickName)
	{
		try
		{
			String sql="select * from tblStudent where NickName ="+"'"+nickName+"'";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next())
			{
				return reToStudent();
			}
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void main(String[] args) {
		//初始化部分
		Student stu=new Student();
		stu.setAge(19);
		stu.setBorrowNum(0);
		stu.setDeposit(1000);
		stu.setDormNum("4S216");
		stu.setGPA(2.33333);
		stu.setId(2131702);
		stu.setNickName("zhuzhuzhu");
		stu.setPunishment(Constants.none);
		stu.setPwd("123456");
		stu.setRealName("郑健雄");
		stu.setSex("male");
		stu.setSRTP(2.0);
		stu.setUserType(0);
		
		StudentDaoImpl s = new StudentDaoImpl();
		//插入测试部分
//		try {
//			s.insert(stu);
//		} catch (RecordAlreadyExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//查询测试部分
		Student student=new Student();
	    student = s.queryByNickName("zhuzhuzhu");
	    System.out.println(student.getRealName()+" "+student.getNickName()+" "+student.getPwd());
		System.out.println(student.getId());
		student = s.query(stu.getId());
		System.out.println(student.getRealName()+" "+student.getNickName()+" "+student.getPwd());
		System.out.println(student.getId());
		//更新测试部分
//		Student stu1 = s.query(213170240);
//		stu1.setAge(20);
//		stu1.setBorrowNum(20);
//		stu1.setDeposit(500);
//		try {
//			s.update(stu1);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//删除测试部分
		try {
			s.delete(stu);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		
	}

	@Override
	public ArrayList<Student> queryAll() 
	{
		// TODO Auto-generated method stub
		try
		{
			String sql="select * from tblStudent";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next())
			{
				return rsToStudentList();
			}
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

//	public ArrayList<Student> queryAll() 
//	{
//		// TODO Auto-generated method stub
//		try
//		{
//			String sql="select * from tblStudent";
//			stmt = access.connection.prepareStatement(sql);
//			rs = stmt.executeQuery();
//			
//			if(rs.next())
//			{
//				return rsToStudentList();
//			}
//		}
//		catch (SQLException e) {
//            System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//		return null;
//	}
	

}
