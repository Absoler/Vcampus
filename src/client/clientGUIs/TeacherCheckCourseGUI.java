package client.clientGUIs;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import client.util.Client;
import vo.*;
import util.*;

public class TeacherCheckCourseGUI extends JPanel{
	public void add(Component c, GridBagConstraints cons, int x, int y, int w, int h) {
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = w;
		cons.gridheight = h;
		add(c, cons);
	}
	Client client;
	Teacher user;
	ArrayList<Course>courses;
	
	public TeacherCheckCourseGUI(Teacher u,ArrayList<Course> Courses)
	{
		user = u;
		client = new Client();
		courses=Courses;
		//System.out.println(courses.size());
		this.setLayout(null);
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.weightx=3;
		constraints.weighty=4;
		constraints.anchor=GridBagConstraints.WEST;
		JScrollPane sp=new JScrollPane(this);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		int count=courses.size();
		if(count==0)
		{
			//该老师没有开课，空面板
			
		}
		if(count==1)
		{
			jpanel1 jp1=new jpanel1();
			jp1.setBorder(BorderFactory.createTitledBorder("课程一"));
			jp1.setBounds(0, 0, 1000, 200);
			this.add(jp1);
		}
		if(count==2)
		{
			jpanel1 jp1=new jpanel1();
			jp1.setBorder(BorderFactory.createTitledBorder("课程一"));
			jp1.setBounds(0, 0, 1000, 200);
			jpanel2 jp2=new jpanel2();
			jp2.setBounds(0, 200, 1000, 200);
			jp2.setBorder(BorderFactory.createTitledBorder("课程二"));
			this.add(jp1);
			this.add(jp2);
		}
		if(count==3)
		{
			jpanel1 jp1=new jpanel1();
			jp1.setBorder(BorderFactory.createTitledBorder("课程一"));
			jp1.setBounds(0, 0, 1000, 200);
			jpanel2 jp2=new jpanel2();
			jp2.setBorder(BorderFactory.createTitledBorder("课程二"));
			jpanel3 jp3=new jpanel3();
			jp3.setBorder(BorderFactory.createTitledBorder("课程三"));
			jp2.setBounds(0, 200, 1000, 200);
			jp3.setBounds(0, 400, 1000, 200);
			this.add(jp1);
			this.add(jp2);
			this.add(jp3);
//			this.add(jp1,constraints,0,0,1,1);
//			this.add(jp2,constraints,0,1,1,1);
//			this.add(jp3,constraints,0,2,1,1);
		}
		for(int i=0;i<courses.size();i++)
		{
			
		}
		//message.getData();
		//JScrollPane scrollPane =new JScrollPane(this);
	}//end constructor function

public class jpanel1 extends JPanel{
	//教授ID
	Course course=courses.get(0);

		JLabel Id=new  JLabel("教授ID");	
		JLabel teacher_Id=new JLabel("ID");
		//course.setTeacher(Integer.parseint(Id.getText()));
		
		//教授姓名
		JLabel Name=new  JLabel("教授姓名");
		JLabel teacher_Name=new JLabel("Name");
		
		//每周上课时间
		JLabel Time1=new  JLabel("每周时间  		");
		JLabel teacher_Time1=new JLabel("Time1");
		
		JLabel Time=new JLabel("-");
		JLabel teacher_Time2=new JLabel("Time2");
		JLabel Timeo=new JLabel("-");
		JLabel teacher_Time3=new JLabel("Time3");
		
		//课程ID
		JLabel CourseID=new JLabel("课程ID");
		JLabel Course_ID=new JLabel();
		
		//课程名称
		JLabel CourseName=new JLabel("课程名字");
		JLabel Course_Name=new JLabel();
		
		//开设学院名称
		JLabel CourseinsName=new JLabel("开设学院名称");
		JLabel Course_insName=new JLabel();
		
		//学分
		JLabel CourseCredit=new JLabel("课程学分");
		JLabel Course_Credit=new JLabel();
		
		//总课时
		JLabel CourseHours=new JLabel("总课时");
		JLabel Course_Hours=new JLabel();
		
		//开始日期
		JLabel CourseStart=new JLabel("开始日期");
		JLabel Course_Start=new JLabel();
		
		//结束日期
		JLabel CourseEnd=new JLabel("结束日期");
		JLabel Course_End=new JLabel();
		
		//上课教室
		JLabel CourseRoom=new JLabel("上课教室");
		JLabel Course_Room=new JLabel();
		
		//限选人数
		JLabel CourseLimitNum=new JLabel("限选人数");
		JLabel Course_LimitNum=new JLabel();
		
		//已选人数
		JLabel currentNum=new JLabel("已选人数");
		JLabel current_Num=new JLabel();
		
		//每周上课数
		JLabel CourseNum=new JLabel("每周上课次数");
		JLabel Course_Num=new JLabel();
		
		public void add(Component c,GridBagConstraints cons,int x,int y,int w,int h)
		{
			cons.gridx = x;
			cons.gridy = y;
			cons.gridwidth = w;
			cons.gridheight = h;
			add(c, cons);
		}
		public jpanel1()
		{
				//四行三列
			teacher_Id.setText(course.getTeacherId()+"");
			teacher_Name.setText(course.getTeacherName());
			Course_ID.setText(course.getCourseID()+"");
			Course_Name.setText(course.getCourseName());
			Course_Hours.setText(course.getCourseHours()+"");
			Course_Credit.setText(course.getCredit()+"");
			Course_insName.setText(course.getInsName());
			Course_LimitNum.setText(course.getLimitNum()+"");
			Course_Num.setText(course.getTimePerWeek()+"");
			Course_Room.setText(course.getClassRoom());
			Course_End.setText((course.getEndDate()));
			Course_Start.setText((course.getStartDate()));
			current_Num.setText(course.getCurrentNum()+"");
			course.getClassRoom();
			teacher_Time1.setText(course.getTime1()+"");
			teacher_Time2.setText(course.getTime2()+"");
			teacher_Time3.setText(course.getTime3()+"");
			GridBagLayout lay = new GridBagLayout();
			this.setLayout(lay);
			GridBagConstraints cons=new GridBagConstraints();
			cons.weightx=3;
			cons.weighty=4;
			cons.anchor=GridBagConstraints.WEST;
			JPanel jt=new JPanel();
//			jt.add(Time1);//每周上课时间
			jt.add(teacher_Time1);
			jt.add(Time);
			jt.add(teacher_Time2);
			jt.add(Timeo);
			jt.add(teacher_Time3);
			//第一行
			add(Id,cons,0,0,1,1);
			add(teacher_Id,cons,1,0,1,1);
			add(Name,cons,2,0,1,1);
			add(teacher_Name,cons,3,0,1,1);
			add(Time1,cons,4,0,1,1);
			add(teacher_Time1,cons,5,0,1,1);
			//add(jt,cons,5,0,1,1);
			//第二行
			add(CourseID,cons,0,1,1,1);
			add( Course_ID,cons,1,1,1,1);
			add(CourseName,cons,2,1,1,1);
			add(Course_Name,cons,3,1,1,1);
			add(CourseinsName,cons,4,1,1,1);
			add( Course_insName,cons,5,1,1,1);
			//第三行
			add(CourseCredit,cons,0,2,1,1);
			add(Course_Credit,cons,1,2,1,1);
			add(CourseHours,cons,2,2,1,1);
			add(Course_Hours,cons,3,2,1,1);
			add(CourseStart,cons,4,2,1,1);
			add(Course_Start,cons,5,2,1,1);
			//第四行
			add(CourseEnd,cons,0,3,1,1);
			add(Course_End,cons,1,3,1,1);
			add(CourseRoom,cons,2,3,1,1);
			add(Course_Room,cons,3,3,1,1);
			add(CourseLimitNum,cons,4,3,1,1);
			add(Course_LimitNum,cons,5,3,1,1);
			//第五行
			add(currentNum,cons,0,4,1,1);
			add(current_Num,cons,1,4,1,1);
			//add(CourseNum,cons,2,4,1,1);
			add(CourseNum,cons,2,4,1,1);
			add(Course_Num,cons,3,4,1,1);
			
			
		}//add jpanel1 constructor function
	
}//end jpanel1 class
public class jpanel2 extends JPanel{
	//教授ID
	Course course=courses.get(1);

		JLabel Id=new  JLabel("教授ID");	
		JLabel teacher_Id=new JLabel("ID");
		//course.setTeacher(Integer.parseint(Id.getText()));
		
		//教授姓名
		JLabel Name=new  JLabel("教授姓名");
		JLabel teacher_Name=new JLabel("Name");
		
		//每周上课时间
		JLabel Time1=new  JLabel("每周时间  		");
		JLabel teacher_Time1=new JLabel("Time1");
		
		JLabel Time=new JLabel("-");
		JLabel teacher_Time2=new JLabel("Time2");
		JLabel Timeo=new JLabel("-");
		JLabel teacher_Time3=new JLabel("Time3");
		
		//课程ID
		JLabel CourseID=new JLabel("课程ID");
		JLabel Course_ID=new JLabel();
		
		//课程名称
		JLabel CourseName=new JLabel("课程名字");
		JLabel Course_Name=new JLabel();
		
		//开设学院名称
		JLabel CourseinsName=new JLabel("开设学院名称");
		JLabel Course_insName=new JLabel();
		
		//学分
		JLabel CourseCredit=new JLabel("课程学分");
		JLabel Course_Credit=new JLabel();
		
		//总课时
		JLabel CourseHours=new JLabel("总课时");
		JLabel Course_Hours=new JLabel();
		
		//开始日期
		JLabel CourseStart=new JLabel("开始日期");
		JLabel Course_Start=new JLabel();
		
		//结束日期
		JLabel CourseEnd=new JLabel("结束日期");
		JLabel Course_End=new JLabel();
		
		//上课教室
		JLabel CourseRoom=new JLabel("上课教室");
		JLabel Course_Room=new JLabel();
		
		//限选人数
		JLabel CourseLimitNum=new JLabel("限选人数");
		JLabel Course_LimitNum=new JLabel();
		
		//已选人数
		JLabel currentNum=new JLabel("已选人数");
		JLabel current_Num=new JLabel();
		
		//每周上课数
		JLabel CourseNum=new JLabel("每周上课次数");
		JLabel Course_Num=new JLabel();
		
		public void add(Component c,GridBagConstraints cons,int x,int y,int w,int h)
		{
			cons.gridx = x;
			cons.gridy = y;
			cons.gridwidth = w;
			cons.gridheight = h;
			add(c, cons);
		}
		public jpanel2()
		{
				//四行三列
			teacher_Id.setText(course.getTeacherId()+"");
			teacher_Name.setText(course.getTeacherName());
			Course_ID.setText(course.getCourseID()+"");
			Course_Name.setText(course.getCourseName());
			Course_Hours.setText(course.getCourseHours()+"");
			Course_Credit.setText(course.getCredit()+"");
			Course_insName.setText(course.getInsName());
			Course_LimitNum.setText(course.getLimitNum()+"");
			Course_Num.setText(course.getTimePerWeek()+"");
			Course_Room.setText(course.getClassRoom());
			Course_End.setText((course.getEndDate()));
			Course_Start.setText((course.getStartDate()));
			current_Num.setText(course.getCurrentNum()+"");
			course.getClassRoom();
			teacher_Time1.setText(course.getTime1()+"");
			teacher_Time2.setText(course.getTime2()+"");
			teacher_Time3.setText(course.getTime3()+"");
			GridBagLayout lay = new GridBagLayout();
			this.setLayout(lay);
			GridBagConstraints cons=new GridBagConstraints();
			cons.weightx=3;
			cons.weighty=4;
			cons.anchor=GridBagConstraints.WEST;
			JPanel jt=new JPanel();
			//jt.add(Time1);//每周上课时间
			jt.add(teacher_Time1);
			jt.add(Time);
			jt.add(teacher_Time2);
			jt.add(Timeo);
			jt.add(teacher_Time3);
			//第一行
			add(Id,cons,0,0,1,1);
			add(teacher_Id,cons,1,0,1,1);
			add(Name,cons,2,0,1,1);
			add(teacher_Name,cons,3,0,1,1);
			add(Time1,cons,4,0,1,1);
			add(jt,cons,5,0,1,1);
			//add(jt,cons,5,0,1,1);
			//第二行
			add(CourseID,cons,0,1,1,1);
			add( Course_ID,cons,1,1,1,1);
			add(CourseName,cons,2,1,1,1);
			add(Course_Name,cons,3,1,1,1);
			add(CourseinsName,cons,4,1,1,1);
			add( Course_insName,cons,5,1,1,1);
			//第三行
			add(CourseCredit,cons,0,2,1,1);
			add(Course_Credit,cons,1,2,1,1);
			add(CourseHours,cons,2,2,1,1);
			add(Course_Hours,cons,3,2,1,1);
			add(CourseStart,cons,4,2,1,1);
			add(Course_Start,cons,5,2,1,1);
			//第四行
			add(CourseEnd,cons,0,3,1,1);
			add(Course_End,cons,1,3,1,1);
			add(CourseRoom,cons,2,3,1,1);
			add(Course_Room,cons,3,3,1,1);
			add(CourseLimitNum,cons,4,3,1,1);
			add(Course_LimitNum,cons,5,3,1,1);
			//第五行
			add(currentNum,cons,0,4,1,1);
			add(current_Num,cons,1,4,1,1);
			//add(CourseNum,cons,2,4,1,1);
			add(CourseNum,cons,2,4,1,1);
			add(Course_Num,cons,3,4,1,1);
			
			
		}//add jpanel1 constructor function
	
}//end jpanel1 class
public class jpanel3 extends JPanel{
	//教授ID
	Course course=courses.get(2);

		JLabel Id=new  JLabel("教授ID");	
		JLabel teacher_Id=new JLabel("ID");
		//course.setTeacher(Integer.parseint(Id.getText()));
		
		//教授姓名
		JLabel Name=new  JLabel("教授姓名");
		JLabel teacher_Name=new JLabel("Name");
		
		//每周上课时间
		JLabel Time1=new  JLabel("每周时间  		");
		JLabel teacher_Time1=new JLabel("Time1");
		
		JLabel Time=new JLabel("-");
		JLabel teacher_Time2=new JLabel("Time2");
		JLabel Timeo=new JLabel("-");
		JLabel teacher_Time3=new JLabel("Time3");
		
		//课程ID
		JLabel CourseID=new JLabel("课程ID");
		JLabel Course_ID=new JLabel();
		
		//课程名称
		JLabel CourseName=new JLabel("课程名字");
		JLabel Course_Name=new JLabel();
		
		//开设学院名称
		JLabel CourseinsName=new JLabel("开设学院名称");
		JLabel Course_insName=new JLabel();
		
		//学分
		JLabel CourseCredit=new JLabel("课程学分");
		JLabel Course_Credit=new JLabel();
		
		//总课时
		JLabel CourseHours=new JLabel("总课时");
		JLabel Course_Hours=new JLabel();
		
		//开始日期
		JLabel CourseStart=new JLabel("开始日期");
		JLabel Course_Start=new JLabel();
		
		//结束日期
		JLabel CourseEnd=new JLabel("结束日期");
		JLabel Course_End=new JLabel();
		
		//上课教室
		JLabel CourseRoom=new JLabel("上课教室");
		JLabel Course_Room=new JLabel();
		
		//限选人数
		JLabel CourseLimitNum=new JLabel("限选人数");
		JLabel Course_LimitNum=new JLabel();
		
		//已选人数
		JLabel currentNum=new JLabel("已选人数");
		JLabel current_Num=new JLabel();
		
		//每周上课数
		JLabel CourseNum=new JLabel("每周上课次数");
		JLabel Course_Num=new JLabel();
		
		public void add(Component c,GridBagConstraints cons,int x,int y,int w,int h)
		{
			cons.gridx = x;
			cons.gridy = y;
			cons.gridwidth = w;
			cons.gridheight = h;
			add(c, cons);
		}
		public jpanel3()
		{
				//四行三列
			teacher_Id.setText(course.getTeacherId()+"");
			teacher_Name.setText(course.getTeacherName());
			Course_ID.setText(course.getCourseID()+"");
			Course_Name.setText(course.getCourseName());
			Course_Hours.setText(course.getCourseHours()+"");
			Course_Credit.setText(course.getCredit()+"");
			Course_insName.setText(course.getInsName());
			Course_LimitNum.setText(course.getLimitNum()+"");
			Course_Num.setText(course.getTimePerWeek()+"");
			Course_Room.setText(course.getClassRoom());
			Course_End.setText((course.getEndDate()));
			Course_Start.setText((course.getStartDate()));
			current_Num.setText(course.getCurrentNum()+"");
			course.getClassRoom();
			teacher_Time1.setText(course.getTime1()+"");
			teacher_Time2.setText(course.getTime2()+"");
			teacher_Time3.setText(course.getTime3()+"");
			GridBagLayout lay = new GridBagLayout();
			this.setLayout(lay);
			GridBagConstraints cons=new GridBagConstraints();
			cons.weightx=3;
			cons.weighty=4;
			cons.anchor=GridBagConstraints.WEST;
			GridBagConstraints cons1=new GridBagConstraints();
			JPanel jt=new JPanel();
			jt.setLayout(new GridBagLayout());
			//jt.add(Time1);//每周上课时间
			jt.add(teacher_Time1);
			jt.add(Time);
			jt.add(teacher_Time2);
			jt.add(Timeo);
			jt.add(teacher_Time3);
			//第一行
			add(Id,cons,0,0,1,1);
			add(teacher_Id,cons,1,0,1,1);
			add(Name,cons,2,0,1,1);
			add(teacher_Name,cons,3,0,1,1);
			add(Time1,cons,4,0,1,1);
			add(jt,cons,5,0,1,1);
			
			//add(jt,cons,5,0,1,1);
			//第二行
			add(CourseID,cons,0,1,1,1);
			add( Course_ID,cons,1,1,1,1);
			add(CourseName,cons,2,1,1,1);
			add(Course_Name,cons,3,1,1,1);
			add(CourseinsName,cons,4,1,1,1);
			add( Course_insName,cons,5,1,1,1);
			//第三行
			add(CourseCredit,cons,0,2,1,1);
			add(Course_Credit,cons,1,2,1,1);
			add(CourseHours,cons,2,2,1,1);
			add(Course_Hours,cons,3,2,1,1);
			add(CourseStart,cons,4,2,1,1);
			add(Course_Start,cons,5,2,1,1);
			//第四行
			add(CourseEnd,cons,0,3,1,1);
			add(Course_End,cons,1,3,1,1);
			add(CourseRoom,cons,2,3,1,1);
			add(Course_Room,cons,3,3,1,1);
			add(CourseLimitNum,cons,4,3,1,1);
			add(Course_LimitNum,cons,5,3,1,1);
			//第五行
			add(currentNum,cons,0,4,1,1);
			add(current_Num,cons,1,4,1,1);
			//add(CourseNum,cons,2,4,1,1);
			add(CourseNum,cons,2,4,1,1);
			add(Course_Num,cons,3,4,1,1);
			
			
		}//add jpanel1 constructor function
	
	}//end jpanel1 class
}//end courseTable class