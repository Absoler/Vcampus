package server.biz;

import java.util.ArrayList;
import java.util.zip.CRC32;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;
import javax.swing.text.html.CSS;

import server.dao.CourseChooseDaoImpl;
import server.dao.CourseDaoImpl;
import server.dao.StudentDaoImpl;
import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import server.exception.TimeClashException;
import util.Student;
import vo.Choose;
import vo.Course;

public class ChooseCourseServiceImpl implements ChooseCourseService{
	
	CourseDaoImpl cr = new CourseDaoImpl();
	CourseChooseDaoImpl crl = new CourseChooseDaoImpl();
	StudentDaoImpl st = new StudentDaoImpl();

	@Override
	public ArrayList<Course> queryCourseList(int studentID) 
	{
		// TODO Auto-generated method stub
		try {
			ArrayList<Course> ls = new ArrayList<Course>();
			ArrayList<Choose> lst = crl.query(studentID);
			Course c = new Course();
			if(lst!=null)
			{for (int i = 0; i < lst.size(); i++) {
				c=cr.queryOne(lst.get(i).getCourseID());
				ls.add(c);
			}}
			return ls;
 		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			System.out.println("查课失败");
		}
		return null;
	}

	@Override
	public ArrayList<Course> queryAllClasses() {
		// TODO Auto-generated method stub
		try {
			ArrayList<Course> ls=cr.queryAll();
			return ls;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addClass(Choose choose) throws RecordAlreadyExistException,RecordNotFoundException, OutOfLimitException,TimeClashException {
		// TODO Auto-generated method stub
		
			Course course=cr.queryOne(choose.getCourseID());
			if(course==null)throw new RecordNotFoundException();
			if(course.getLimitNum()==course.getCurrentNum())throw new OutOfLimitException();
			ArrayList<Course> ls=queryCourseList(choose.getStudentID());
			ArrayList<Choose> lst=crl.query(choose.getStudentID());
			boolean num[] = new boolean [21];
			for(int i=0;i<=20;i++)
			{
				num[i]=false;
			}
			if(lst!=null)
			{for(Choose cs:lst)
			{
				if(cs.getCourseID()==choose.getCourseID())
					throw new RecordAlreadyExistException();
			}}
			if(ls!=null)
			{for(Course c:ls)
			{
				
				num[c.getTime1()]=true;
				num[c.getTime2()]=true;
				num[c.getTime3()]=true;
			}}
			Course c=cr.queryOne(choose.getCourseID());
			if(c==null) throw new RecordNotFoundException();
			if(num[c.getTime1()]==true&&c.getTime1()!=0)throw new TimeClashException();
			if(num[c.getTime2()]==true&&c.getTime2()!=0)throw new TimeClashException();
			if(num[c.getTime3()]==true&&c.getTime3()!=0)throw new TimeClashException();
			course.setCurrentNum(course.getCurrentNum()+1);
			cr.update(course);
			Student stu=st.query(choose.getStudentID());
			choose.setCourseName(course.getCourseName());
			choose.setStudentName(stu.getRealName());
			choose.setTeacherID(course.getTeacherId());
			choose.setTeacherName(course.getTeacherName());
			crl.insert(choose);
		return true;
	}

	@Override
	public boolean returnClass(Choose choose) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		Course course=cr.queryOne(choose.getCourseID());
		if(course==null) throw new RecordNotFoundException();
		crl.delete(choose);
		course.setCurrentNum(course.getCurrentNum()-1);
		try {
			cr.update(course);
			return true;
		} catch (TimeClashException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OutOfLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		ChooseCourseServiceImpl ccs=new ChooseCourseServiceImpl();
		//选课功能测试代码
		Choose choose=new Choose();
		choose.setCourseID(123);
		choose.setCourseName("计算机组成原理");
		choose.setStudentID(213170240);
		choose.setStudentName("郑健雄");
		choose.setTeacherID(233);
		choose.setTeacherName("任国林");
		try {
			ccs.addClass(choose);
		} catch (RecordAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OutOfLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeClashException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询所有课程功能测试
		ArrayList<Course> ls=ccs.queryAllClasses();
		if(ls!=null)
		{
			for(Course c:ls)
			{
				System.out.println(c.getCourseName()+" "+c.getTeacherName());
			}
		}
		//查询自己课表功能测试
		ArrayList<Course> lst=ccs.queryCourseList(213170240);
		if(lst!=null)
		{
			for(Course c:lst)
			{
				System.out.println(c.getCourseName());
			}
		}
		//退课功能测试
		try {
			ccs.returnClass(choose);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
