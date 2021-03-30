package server.dao;

import java.awt.Choice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import vo.Choose;
import vo.Course;

public class CourseChooseDaoImpl implements CourseChooseDao{
	
	private Access access=new Access();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;

	public Choose rsToCourseChoose()
	{
		try{
			Choose c = new Choose();
			c.setCourseID(rs.getInt("CourseID"));
			c.setCourseName(rs.getString("CourseName"));
			c.setStudentID(rs.getInt("StudentID"));
			c.setTeacherID(rs.getInt("TeacherID"));
			c.setStudentName(rs.getString("StudentName"));
			c.setTeacherName(rs.getString("TeacherName"));
			return c;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Choose> rsToCourseChooseList()
	{
		try{
			ArrayList<Choose> list = new ArrayList<Choose>();
			do{
				Choose c = new Choose();
				c.setCourseID(rs.getInt("CourseID"));
				c.setCourseName(rs.getString("CourseName"));
				c.setStudentID(rs.getInt("StudentID"));
				c.setTeacherID(rs.getInt("TeacherID"));
				c.setStudentName(rs.getString("StudentName"));
				c.setTeacherName(rs.getString("TeacherName"));
				list.add(c);
			}while(rs.next());
			return list;
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("查课失败");
		}
		return null;
	}
	
	@Override
	public boolean insert(Choose choose) throws RecordAlreadyExistException {
		// TODO Auto-generated method stub
		try{
			ArrayList<Choose> c = query(choose.getStudentID());
			//if(c!=null) throw new RecordAlreadyExistException();
			if(c!=null)
			{for(int i=0;i<c.size();i++)
			{
				if(c.get(i).getCourseID()==choose.getCourseID())
					throw new RecordAlreadyExistException();
			}}
			
			
			
			String sql = "INSERT INTO tblCourseChoose (CourseID,CourseName,"
					+"StudentID,TeacherID,StudentName,TeacherName)"
					+"VALUES('"
					+choose.getCourseID()
					+"' , '"+choose.getCourseName()
					+"' , '"+choose.getStudentID()
					+"' , '"+choose.getTeacherID()
					+"' , '"+choose.getStudentName()
					+"' , '"+choose.getTeacherName()
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
	public boolean delete(Choose choose) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		try
		{
			int flag=0;
			ArrayList<Choose> c = query(choose.getStudentID());
			if(c==null) throw new RecordNotFoundException();
			for(int i=0;i<c.size();i++)
			{
				if(c.get(i).getCourseID()==choose.getCourseID())
					{flag=1;
					break;
					}
			}
			if(flag==0)throw new RecordNotFoundException();
			String sql = "DELETE * FROM tblCourseChoose WHERE CourseID = ? AND "
					+ "StudentID=?";
			stmt =access.connection.prepareStatement(sql);
			stmt.setInt(1,choose.getCourseID());
			stmt.setInt(2,choose.getStudentID());
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Choose choose) throws RecordNotFoundException {
//		// TODO Auto-generated method stub
//		try{
//			Choose c = query(choose.getCourseID());
//			if(c==null) throw new RecordNotFoundException();
//			String sql = "UPDATE tblCourseChoose SET CourseName=?,StudentID=?"
//					+""
//		}
//		return true;
		return true;
	}

	@Override
	public ArrayList<Choose> queryAll() {
		// TODO Auto-generated method stub
		try{
			String sql = "SELECT * FROM tblCourseChoose";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()){
				return rsToCourseChooseList();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Choose> query(int studentID) {
		try{
			String sql="SELECT * FROM tblCourseChoose WHERE StudentID ="+"'"+studentID+"'";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				return rsToCourseChooseList();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			//System.out.println("没有数据");
		}
		return null;
	}
	
	public static void main(String[] args) {
		//初始化部分
		CourseChooseDaoImpl csd=new CourseChooseDaoImpl();
		Choose c=new Choose();
		c.setCourseID(217);
		c.setCourseName("计算机组成原理");
		c.setStudentID(213170240);
		c.setStudentName("郑健雄");
		c.setTeacherID(211);
		c.setTeacherName("任国林");
		//插入测试代码
//		try {
//			csd.insert(c);
//		} catch (RecordAlreadyExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//查询测试代码
		ArrayList<Choose> ls=csd.query(213170240);
		if(ls!=null)
		{
			for(Choose cw:ls)
			{
				System.out.println(cw.getCourseName());
			}
		}
		ArrayList<Choose> lst=csd.queryAll();
		if(lst!=null)
		{
			for(Choose cw:lst)
			{
				System.out.println(cw.getCourseName());
			}
		}
		//更新测试部分
		try {
			if(csd.update(c))
				System.out.println("pass");
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		//删除测试代码
		try {
			csd.delete(c);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
