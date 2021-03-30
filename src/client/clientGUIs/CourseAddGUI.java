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
	
	private int teacherId;//����ID
	private String teacherName;//��������
	private int[] time= {0,0,0};//ÿ��ʱ��
	private int courseID;//�γ�ID
	private String courseName;//�γ�����
	private String insName;//����ѧԺ����
	private int credit;//ѧ��
	private int courseHours;//�ܿ�ʱ
	private Date startDate;//��ʼ����
	private Date endDate;//��������
	private String classRoom;//�Ͽν���
	private int limitNum;//��ѡ����
	private int currentTime;//��ѡ����
	private int timePerWeek;//ÿ�ܿγ���
	private Date time1;//
	private Date time2;//
	private Date time3;//�Ͽ�ʱ��
	
	Course course=new Course();//Course ��ʵ��������
	
	//����ID
	JLabel Id=new  JLabel("����ID");
	JLabel teacher_Id=new JLabel();
	//��������
	JLabel Name=new  JLabel("��������");
	JLabel teacher_Name=new JLabel();
	//ÿ���Ͽ�ʱ��
	JLabel Time1=new  JLabel("ÿ��ʱ��");
	JTextField teacher_Time1=new JTextField(10);
	JLabel Time=new JLabel("-");
	JLabel Timeo=new JLabel("-");
	JTextField teacher_Time2=new JTextField(5);
	JTextField teacher_Time3=new JTextField(5);
	//�γ�ID
	JLabel CourseID=new JLabel("�γ�ID");
	JTextField Course_ID=new JTextField(10);
	//�γ�����
	JLabel CourseName=new JLabel("�γ�����");
	JTextField Course_Name=new JTextField(10);
	//����ѧԺ����
	JLabel CourseinsName=new JLabel("����ѧԺ����");
	JTextField Course_insName=new JTextField(10);
	//ѧ��
	JLabel CourseCredit=new JLabel("�γ�ѧ��");
	JTextField Course_Credit=new JTextField(10);
	//�ܿ�ʱ
	JLabel CourseHours=new JLabel("�ܿ�ʱ");
	JTextField Course_Hours=new JTextField(10);
	//��ʼ����
	JLabel CourseStart=new JLabel("��ʼ����");
	JTextField Course_Start=new JTextField(10);
	//��������
	JLabel CourseEnd=new JLabel("��������");
	JTextField Course_End=new JTextField(10);
	//�Ͽν���
	JLabel CourseRoom=new JLabel("�Ͽν���");
	JTextField Course_Room=new JTextField(10);
	//��ѡ����
	JLabel CourseLimitNum=new JLabel("��ѡ����");
	JTextField Course_LimitNum=new JTextField(10);
	//��ѡ����
	JLabel currentNum=new JLabel("��ѡ����");
	JLabel current_Num=new JLabel("0");
	
	//ÿ���Ͽ���
	JLabel CourseNum=new JLabel("ÿ���Ͽδ���");
	JTextField Course_Num=new JTextField(10);
	//ȷ����ť
	JButton button=new JButton("ȷ��");
	
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
		//��������
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
		//��һ��
		add(Id,cons,0,0,1,1);
		add(teacher_Id,cons,1,0,1,1);
		add(Name,cons,2,0,1,1);
		add(teacher_Name,cons,3,0,1,1);
		add(Time1,cons,4,0,1,1);
		add(teacher_Time1,cons,5,0,1,1);
		//�ڶ���
		add(CourseID,cons,0,1,1,1);
		add( Course_ID,cons,1,1,1,1);
		add(CourseName,cons,2,1,1,1);
		add(Course_Name,cons,3,1,1,1);
		add(CourseinsName,cons,4,1,1,1);
		add( Course_insName,cons,5,1,1,1);
		//������
		add(CourseCredit,cons,0,2,1,1);
		add(Course_Credit,cons,1,2,1,1);
		add(CourseHours,cons,2,2,1,1);
		add(Course_Hours,cons,3,2,1,1);
		add(CourseStart,cons,4,2,1,1);
		add(Course_Start,cons,5,2,1,1);
		//������
		add(CourseEnd,cons,0,3,1,1);
		add(Course_End,cons,1,3,1,1);
		add(CourseRoom,cons,2,3,1,1);
		add(Course_Room,cons,3,3,1,1);
		add(CourseLimitNum,cons,4,3,1,1);
		add(Course_LimitNum,cons,5,3,1,1);
		//������
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
				//�����Ͽ�ʱ���Ͽ�ʱ�������00-00-00�ĸ�ʽ����;15-16-17
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
//                course.setTeacherName("�ι���");
//				course.setTeacherId(101003519);
//				course.setTime(time);
//				course.setCourseID(2333);
//				course.setCourseName("������γ����");
//				course.setInsName("cs");
//				course.setCredit(2.5);
//				course.setCourseHours(100);
				
				//�γ̿�ʼʱ��ͽ���ʱ�������1900-01-00�ı�׼��ʽ����
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
				 //�õ�����������Ϣ
			}
		});
		
//		���ñ�����ɫ
		Color bgColor = new Color(255, 255, 255);
		this.setBackground(bgColor);
		
	}

}
