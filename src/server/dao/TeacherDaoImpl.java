package server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.Constants;
import util.Teacher;

public class TeacherDaoImpl implements TeacherDao {
	private Access access=new Access();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	public Teacher rsToTeacher()
	{
		try
		{
			Teacher teacher = new Teacher();
			teacher.setUserType(1);
			teacher.setAge(rs.getInt("Age"));
			teacher.setBorrowNum(rs.getInt("BorrowNumber"));
			teacher.setDeposit(rs.getDouble("Deposit"));
			teacher.setId(rs.getInt("TeacherID"));
			teacher.setNickName(rs.getString("NickName"));
			teacher.setPwd(rs.getString("Password"));
			teacher.setRealName(rs.getString("TeacherName"));
			teacher.setSex(rs.getString("Sex"));
			teacher.setTitle(rs.getString("Title"));
			return teacher;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insert(Teacher teacher) throws RecordAlreadyExistException {
		// TODO Auto-generated method stub
		try
		{
			Teacher tc = query(teacher.getId());
			if(tc!=null) throw new RecordAlreadyExistException();
			String sql = "INSERT INTO tblTeacher (TeacherID, PassWord,NickName,"
					+"Deposit,BorrowNumber,Title,Age,Sex,TeacherName) VALUES ( '"
			+teacher.getId()
			+"' , '"+teacher.getPwd()
			+"' , '"+teacher.getNickName()
			+"' , '"+teacher.getDeposit()
			+"' , '"+teacher.getBorrowNum()
			+"' , '"+teacher.getTitle()
			+"' , '"+teacher.getAge()
			+"' , '"+teacher.getSex()
			+"' , '"+teacher.getRealName()
			+"' )";
			stmt = access.connection.prepareStatement(sql);
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
	public boolean delete(Teacher teacher) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		try
		{
			//此处还需要一个还书的操作，之后再添加即可
			Teacher tc=query(teacher.getId());
			if(tc==null) throw new RecordNotFoundException();
			String sql = "DELETE * FROM tblTeacher WHERE TeacherID =?";
			stmt=access.connection.prepareStatement(sql);
			stmt.setInt(1, teacher.getId());
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Teacher teacher) throws RecordNotFoundException{
		// TODO Auto-generated method stub
		try
		{
			Teacher tc=query(teacher.getId());
			if(tc==null) throw new RecordNotFoundException();
			String sql = "UPDATE tblTeacher SET NickName=?,Password=?,Deposit=?,"
					+ "BorrowNumber=?,Age=?,Sex=?,Title=?,"
					+ "TeacherName=?"
					+ "WHERE TeacherID=?";
			stmt=access.connection.prepareStatement(sql);
			stmt = access.connection.prepareStatement(sql);
			stmt.setString(1, teacher.getNickName());
			stmt.setString(2, teacher.getPwd());
			stmt.setDouble(3, teacher.getDeposit());
			stmt.setInt(4, teacher.getBorrowNum());
			stmt.setInt(5, teacher.getAge());
			stmt.setString(6, teacher.getSex());
			stmt.setString(7, teacher.getTitle());
			stmt.setString(8, teacher.getRealName());
			stmt.setInt(9, teacher.getId());
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Teacher query(int TeacherID) {
		// TODO Auto-generated method stub
		try
		{
			String sql= "SELECT * FROM tblTeacher where TeacherID="+ "'"+ TeacherID+"'";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next())
			{
				return rsToTeacher();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Teacher queryByNickName(String nickName)
	{
		try
		{
			String sql= "SELECT * FROM tblTeacher where NickName="+ "'"+ nickName+"'";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next())
			{
				return rsToTeacher();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		Teacher tc=new Teacher();
		tc.setAge(40);
		tc.setBorrowNum(20);
		tc.setDeposit(2000);
		tc.setId(123456);
		tc.setNickName("单周期cpu");
		tc.setPwd("123456s");
		tc.setRealName("任国林");
		tc.setTitle(Constants.professor);
		tc.setSex("male");
		tc.setUserType(1);
		TeacherDaoImpl s=new TeacherDaoImpl();
		//插入测试部分
		try {
			s.insert(tc);
		} catch (RecordAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询测试部分
		tc=s.query(123456);
		System.out.println(tc.getAge());
		//更新测试部分
//		tc.setNickName("多周期cpu");
//		tc.setBorrowNum(10);
//		try {
//			s.update(tc);
//		} catch (RecordNotFoundException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//删除测试部分
		try {
			s.delete(tc);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
