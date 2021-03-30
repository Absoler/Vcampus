package client.clientGUIs;

import java.awt.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import client.util.Client;
import javax.swing.table.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.color.*;
import javax.swing.*;
import java.awt.*;
import util.*;
import vo.*;

public class CourseAddGUI extends JPanel{
	
	Teacher user;
	Client client ;
	
	private int teacherId;//教授ID
	private String teacherName;//教授姓名
	private int[] time= {0,0,0};//每周时间
	private int courseID;//课程ID
	private String courseName;//课程名字
	private String insName;//开设学院名字
	private int credit;//学分
	private int courseHours;//总课时
	private Date startDate;//开始日期
	private Date endDate;//结束日期
	private String classRoom;//上课教室
	private int limitNum;//限选人数
	private int currentTime;//已选人数
	private int timePerWeek;//每周课程数
	private Date time1;//
	private Date time2;//
	private Date time3;//上课时间
	
	Course course=new Course();//Course 的实例化对象
	
	//教授ID
	JLabel Id=new  JLabel("教授ID");
	JLabel teacher_Id=new JLabel();
	//教授姓名
	JLabel Name=new  JLabel("教授姓名");
	JLabel teacher_Name=new JLabel();
	//每周上课时间
	JLabel Time1=new  JLabel("每周时间");
	JTextField teacher_Time1=new JTextField(10);
	JLabel Time=new JLabel("-");
	JLabel Timeo=new JLabel("-");
	JTextField teacher_Time2=new JTextField(5);
	JTextField teacher_Time3=new JTextField(5);
	//课程ID
	JLabel CourseID=new JLabel("课程ID");
	JTextField Course_ID=new JTextField(10);
	//课程名称
	JLabel CourseName=new JLabel("课程名字");
	JTextField Course_Name=new JTextField(10);
	//开设学院名称
	JLabel CourseinsName=new JLabel("开设学院名称");
	JTextField Course_insName=new JTextField(10);
	//学分
	JLabel CourseCredit=new JLabel("课程学分");
	JTextField Course_Credit=new JTextField(10);
	//总课时
	JLabel CourseHours=new JLabel("总课时");
	JTextField Course_Hours=new JTextField(10);
	//开始日期
	JLabel CourseStart=new JLabel("开始日期");
	JTextField Course_Start=new JTextField(10);
	//结束日期
	JLabel CourseEnd=new JLabel("结束日期");
	JTextField Course_End=new JTextField(10);
	//上课教室
	JLabel CourseRoom=new JLabel("上课教室");
	JTextField Course_Room=new JTextField(10);
	//限选人数
	JLabel CourseLimitNum=new JLabel("限选人数");
	JTextField Course_LimitNum=new JTextField(10);
	//已选人数
	JLabel currentNum=new JLabel("已选人数");
	JLabel current_Num=new JLabel("0");
	
	//每周上课数
	JLabel CourseNum=new JLabel("每周上课次数");
	JTextField Course_Num=new JTextField(10);
	//确定按钮
	JButton button=new JButton("确定");
	
	public void add(Component c,GridBagConstraints cons,int x,int y,int w,int h)
	{
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = w;
		cons.gridheight = h;
		add(c, cons);
	}
	
	public CourseAddGUI(Teacher u)
	{
		client = new Client();
		user = u;
		
//		Message mes=new Message();
//		teacher_ID.getmes.getUserId()
		//四行三列
		teacher_Id.setText(u.getId()+"");
		teacher_Name.setText(u.getRealName());
		GridBagLayout lay = new GridBagLayout();
		this.setLayout(lay);
		GridBagConstraints cons=new GridBagConstraints();
		cons.weightx=3;
		cons.weighty=4;
		cons.anchor=GridBagConstraints.CENTER;
		GridBagConstraints cons1=new GridBagConstraints();
		cons1.weightx=1;
		cons1.weighty=4;
		cons1.anchor=GridBagConstraints.EAST;
		JPanel jt=new JPanel();
		jt.add(teacher_Time1);
		jt.add(Timeo);
		jt.add(teacher_Time2);
		jt.add(Time);
		jt.add(teacher_Time3);
		//第一行
		add(Id,cons,0,0,1,1);
		add(teacher_Id,cons,1,0,1,1);
		add(Name,cons,2,0,1,1);
		add(teacher_Name,cons,3,0,1,1);
		add(Time1,cons,4,0,1,1);
		add(teacher_Time1,cons,5,0,1,1);
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
		add(button,cons,3,5,1,1);
		//ArrayList<Component>  c1=new ArrayList<Component>(this.getComponentCount());
		
		
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//设置上课时间上课时间必须以00-00-00的格式输入;15-16-17
				String t1=teacher_Time1.getText();
				time[0]=Integer.parseInt(t1.substring(0, 2));
				time[1]=Integer.parseInt(t1.substring(3, 5));
				time[2]=Integer.parseInt(t1.substring(6, 8));
				//System.out.println(teacher_Name.getText());
				course.setTeacherName(teacher_Name.getText());
//				System.out.println(time[0]);
//				System.out.println(time[1]);
//				System.out.println(time[2]);
//				
//				
				course.setTeacherId(Integer.parseInt(teacher_Id.getText()));
				course.setTime(time);
				course.setTime1(time[0]);
				course.setTime2(time[1]);
				course.setTime3(time[2]);
				course.setCourseID(Integer.parseInt(Course_ID.getText()));
				course.setCourseName(Course_Name.getText());
				course.setInsName(Course_insName.getText());
				course.setCredit(Double.parseDouble(Course_Credit.getText()));
				course.setCourseHours(Integer.parseInt(Course_Hours.getText()));
//                course.setTeacherName("任国林");
//				course.setTeacherId(101003519);
//				course.setTime(time);
//				course.setCourseID(2333);
//				course.setCourseName("计算机课程设计");
//				course.setInsName("cs");
//				course.setCredit(2.5);
//				course.setCourseHours(100);
				
				//课程开始时间和结束时间必须以1900-01-00的标准形式输入
				String date = Course_Start.getText();
//				Date d1 = new Date(Integer.parseInt(date.substring(0, 4))-1900,
//						Integer.parseInt(date.substring(5, 7))-1,
//				Integer.parseInt(date.substring(8, 10)));
				course.setStartDate(date);
				String date_End=Course_End.getText();
//				Date d2 = new Date(Integer.parseInt(date_End.substring(0, 4))-1900,
//						Integer.parseInt(date_End.substring(5, 7))-1,
//				Integer.parseInt(date_End.substring(8, 10)));
				course.setEndDate(date_End);
				course.setLimitNum(Integer.parseInt(Course_LimitNum.getText()));
				
				course.setClassRoom(Course_Room.getText());
				
				Message msg=new Message();
				msg.setData(course);
				msg.setMessageType(Constants.course_add);
				Message serverResponse = client.sendRequestToServer(msg);
				if(serverResponse.isLastOperState()==false)
					JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());

				teacher_Time1.setText("");
				teacher_Time2.setText("");
				teacher_Time3.setText("");
				Course_ID.setText("");
				Course_Name.setText("");
				Course_insName.setText("");
				Course_Credit.setText("");;
				Course_Hours.setText("");
				Course_Start.setText("");
				Course_End.setText("");
				Course_LimitNum.setText("");
				Course_Num.setText("");
				current_Num.setText("");
				Course_Room.setText("");
				 //得到各个输入信息
			}
		});
		
//		设置背景颜色
		Color bgColor = new Color(255, 255, 255);
		this.setBackground(bgColor);
		
	}

}
