package server.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.text.html.CSS;

import com.healthmarketscience.jackcess.query.Query;

import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import server.exception.TimeClashException;
import vo.Course;

public class CourseDaoImpl implements CourseDao {
	
	private Access access=new Access();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	
	public Course rsToOneClass()
	{
		try {
			Course c = new Course();
			c.setCourseID(rs.getInt("CourseID"));
			c.setCourseName(rs.getString("CourseName"));
			c.setInsName(rs.getString("Insititute"));
			c.setTeacherName(rs.getString("TeacherName"));
			c.setTeacherId(rs.getInt("TeacherID"));
			c.setCredit(rs.getInt("Credit"));
			c.setCourseHours(rs.getInt("CourseHours"));
			c.setStartDate(rs.getString("StartDate"));
			c.setEndDate(rs.getString("EndDate"));
			c.setClassRoom(rs.getString("ClassRoom"));
			c.setLimitNum(rs.getInt("LimitNum"));
			c.setCurrentNum(rs.getInt("CurrentNum"));
			c.setTimePerWeek(rs.getInt("TimesPerWeek"));
			c.setTime1(rs.getInt("Time1"));
			c.setTime2(rs.getInt("Time2"));
			c.setTime3(rs.getInt("Time3"));
			return c;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Course> rsToCourseList(){
		try {
			ArrayList<Course> list=new ArrayList<Course>();
			do {
				Course c=new Course();
				c.setCourseID(rs.getInt("CourseID"));
				c.setCourseName(rs.getString("CourseName"));
				c.setInsName(rs.getString("Insititute"));
				c.setTeacherName(rs.getString("TeacherName"));
				c.setTeacherId(rs.getInt("TeacherID"));
				c.setCredit(rs.getInt("Credit"));
				c.setCourseHours(rs.getInt("CourseHours"));
				c.setStartDate(rs.getString("StartDate"));
				c.setEndDate(rs.getString("EndDate"));
				c.setClassRoom(rs.getString("ClassRoom"));
				c.setLimitNum(rs.getInt("LimitNum"));
				c.setCurrentNum(rs.getInt("CurrentNum"));
				c.setTimePerWeek(rs.getInt("TimesPerWeek"));
				c.setTime1(rs.getInt("Time1"));
				c.setTime2(rs.getInt("Time2"));
				c.setTime3(rs.getInt("Time3"));
				list.add(c);
			}while(rs.next());
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insert(Course course) throws RecordAlreadyExistException, TimeClashException, OutOfLimitException {
		try {
			if(course.getTime1()==0&&course.getTime2()==0&&course.getTime3()==0)
				throw new OutOfLimitException();
			ArrayList<Course> c1=query(course.getCourseName());
			if(c1!=null)
			{
				for(Course c:c1)
				{
					if(c.getTeacherId()==course.getTeacherId())
						throw new RecordAlreadyExistException();
				}
			}
			ArrayList<Course>c2=query(course.getTeacherId());
			if(c2!=null)
			{
				for(Course c:c2)
				{
					if((course.getTime1()==c.getTime1()&&course.getTime1()!=0)||(course.getTime1()==c.getTime1()&&course.getTime1()!=0)||(course.getTime3()==c.getTime3()&&course.getTime3()!=0))
					{
						throw new TimeClashException();
					}
				}
			}
//			ArrayList<Course>c1=query(course.getCourseName());
//			ArrayList<Course>c2=query(course.getTeacherId());
//			if(c1!=null&&c2!=null)
//			{for(int i=0;i<c1.size();i++) {
//				for(int j=0;j<c2.size();j++)
//					{if(c1.get(i).getCourseName().equals(c2.get(j).getCourseName())&&(c1.get(i).getTeacherId()==c2.get(j).getTeacherId()))
//						throw new RecordAlreadyExistException();}
//
//			}}
//			ArrayList<Course>c3=queryAll();
//			if(c3!=null)
//			{for(Course cs:c3)
//			{
//				if((course.getTime1()==cs.getTime1()&&course.getTime1()!=0)||(course.getTime1()==cs.getTime1()&&course.getTime1()!=0)||(course.getTime3()==cs.getTime3()&&course.getTime3()!=0))
//					throw new TimeClashException();
//			}}
			String sql="INSERT INTO tblCourseInformation (CourseID,CourseName,Insititute,TeacherName,TeacherID,Credit,CourseHours,StartDate,EndDate,ClassRoom,LimitNum,CurrentNum,TimesPerWeek,Time1,Time2,Time3)"
					+"VALUES ( '"
			+course.getCourseID()
			+"' , '"+course.getCourseName()
			+"' , '"+course.getInsName()
			+"' , '"+course.getTeacherName()
			+"' , '"+course.getTeacherId()
			+"' , '"+course.getCredit()
			+"' , '"+course.getCourseHours()
			+"' , '"+course.getStartDate()
			+"' , '"+course.getEndDate()
			+"' , '"+course.getClassRoom()
			+"' , '"+course.getLimitNum()
			+"' , '"+course.getCurrentNum()
			+"' , '"+course.getTimePerWeek()
			+"' , '"+course.getTime1()
			+"' , '"+course.getTime2()
			+"' , '"+course.getTime3()
			+"' )";
			stmt=access.connection.prepareStatement(sql);
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		// TODO Auto-generated method stub
		return true;
		
	}


	@Override
	public boolean delete(Course course) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		try {
			Course c = queryOne(course.getCourseID());
			if(c==null)
				throw new RecordNotFoundException();
			String sql="DELETE * FROM tblCourseInformation WHERE CourseID=?";
			stmt = access.connection.prepareStatement(sql);
			stmt.setInt(1, course.getCourseID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Course course) throws RecordNotFoundException, TimeClashException, OutOfLimitException {
		// TODO Auto-generated method stub
		try {
			Course c = queryOne(course.getCourseID());
			if(c==null) throw new RecordNotFoundException();
			ArrayList<Course> ls=query(course.getTeacherId());
			if(course.getTime1()==0&&course.getTime2()==0&&course.getTime3()==0)
				throw new OutOfLimitException();
			if(ls!=null)
			{for(Course c1:ls)
			{
				if(course.getTeacherId()!=c1.getTeacherId()&&((course.getTime1()==c1.getTime1()&&course.getTime1()!=0)||(course.getTime1()==c1.getTime1()&&course.getTime1()!=0)||(course.getTime1()==c1.getTime1()&&course.getTime1()!=0)))
					throw new TimeClashException();
			}}
			String sql="UPDATE tblCourseInformation SET CourseName=?,Insititute=?,TeacherName=?,TeacherID=?,"
					+"Credit=?,CourseHours=?,StartDate=?,EndDate=?,ClassRoom=?,LimitNum=?,CurrentNum=?,"
					+"TimesPerWeek=?,Time1=?,Time2=?,Time3=? WHERE CourseID = ?";
			stmt = access.connection.prepareStatement(sql);
			stmt.setString(1, course.getCourseName());
			stmt.setString(2, course.getInsName());
			stmt.setString(3, course.getTeacherName());
			stmt.setInt(4, course.getTeacherId());
			stmt.setDouble(5, course.getCredit());
			stmt.setInt(6,course.getCourseHours());
			stmt.setString(7, course.getStartDate());
			stmt.setString(8,course.getEndDate());
			stmt.setString(9,course.getClassRoom());
			stmt.setInt(10,course.getLimitNum());
			stmt.setInt(11,course.getCurrentNum());
			stmt.setInt(12,course.getTimePerWeek());
			stmt.setInt(13,course.getTime1());
			stmt.setInt(14,course.getTime2());
			stmt.setInt(15,course.getTime3());
			stmt.setInt(16,course.getCourseID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<Course> query(String courseName) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM tblCourseInformation WHERE CourseName="+"'"+courseName+"'";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				return rsToCourseList();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ArrayList<Course> queryAll() {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM tblCourseInformation";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
			return rsToCourseList();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Course> query(int teacherID) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM tblCourseInformation WHERE TeacherID ="+"'"+teacherID +"'";
			stmt  = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				return rsToCourseList();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Course queryOne(int courseID) {
		try {
			String sql = "SELECT * FROM tblCourseInformation WHERE CourseID="+"'"+courseID+"'";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
			{return rsToOneClass();}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String arg[]){
//		Course c=new Course();
//		CourseDaoImpl co=new CourseDaoImpl();
//		c.setCourseID(101024);
//		c.setCourseName("xinlijiankang");
//		c.setInsName("renwen");
//		c.setTeacherName("sad");
//		c.setTeacherId(23412);
//		c.setCredit(2);
//		c.setCourseHours(36);
//		c.setStartDate("2019-1-4");
//		c.setEndDate("2019-7-14");
//		c.setClassRoom("J2-302");
//		c.setLimitNum(50);
//		c.setCurrentNum(0);
//		c.setTimePerWeek(2);
//		c.setTime1(3);
//		c.setTime2(7);
//		c.setTime3(0);
//		
//		try {
//			//co.insert(c);
//			ArrayList<Course>cl= co.queryAll();
//			for(Course course:cl){
//				System.out.println(course.getCourseName()
//						+" "+course.getCourseID()
//						+" "+course.getInsName()
//						+" "+course.getTeacherName()
//						+" "+course.getTeacherId()
//						);
//			}
//			
//			System.out.println("shit1!");
//			ArrayList<Course>ctl= co.query(123456);
//			for(Course course:ctl){
//				System.out.println(course.getCourseName()
//						+" "+course.getCourseID()
//						+" "+course.getInsName()
//						+" "+course.getTeacherName()
//						+" "+course.getTeacherId()
//						);
//			}
//			
//			System.out.println("shit2!");
//			Course cou=co.queryOne(101023);
//			System.out.println(cou.getCourseName()
//					+" "+cou.getCourseID()
//					+" "+cou.getInsName()
//					+" "+cou.getTeacherName()
//					+" "+cou.getTeacherId()
//					);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			
//		}
		//初始化操作
		Course c=new Course();
		c.setClassRoom("J401");
		c.setCourseHours(120);
		c.setCourseID(123);
		c.setCourseName("计算机组成原理");
		c.setCredit(2.5);
		c.setCurrentNum(0);
		c.setEndDate("2020/1/2");
		c.setInsName("CS");
		c.setLimitNum(120);
		c.setStartDate("2019/9/1");
		c.setTeacherId(2222);
		c.setTeacherName("任国林");
		c.setTime1(1);
		c.setTime2(2);
		c.setTime3(3);
		c.setTimePerWeek(3);
		CourseDaoImpl cs=new CourseDaoImpl();
		//插入测试代码
		try {
			cs.insert(c);
		} catch (RecordAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeClashException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OutOfLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询操作测试
		ArrayList<Course> c1 =cs.queryAll();
		if(c1!=null)
		{
			for(Course a:c1)
			{
				System.out.println(a.getCourseName());
			}
		}
		Course c2=cs.queryOne(213);
		if(c2!=null)
			System.out.println(c2.getTeacherName());
		ArrayList<Course> c3=cs.query("计算机组成原理");
		if(c3!=null)
		{
			for(Course a:c3)
			{
				System.out.println(a.getTeacherId());
			}
		}
		ArrayList<Course> c4=cs.query(211);
		if(c4!=null)
		{
			for(Course a:c4)
			{
				System.out.println(a.getCourseName());
			}
		}
		//更新代码测试
//		c.setCourseHours(240);
//		c.setCredit(10);
//		try {
//			cs.update(c);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TimeClashException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OutOfLimitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//删除代码测试
//		try {
//			cs.delete(c);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
