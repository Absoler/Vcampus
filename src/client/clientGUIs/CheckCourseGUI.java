package client.clientGUIs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import util.*;
import vo.*;
import client.util.Client;

public class CheckCourseGUI extends JPanel{

	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;

	// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	Client client;
	User user;
	
	ArrayList<Course> courses = new ArrayList<Course>();
	Object[][]p = new Object[4][6];
	
	JTable table;
	JButton bRefresh = new JButton("刷新课表");
	
	JLabel lWelcome0 = new JLabel("————————————————————————————————————————");
	JLabel lWelcome1 = new JLabel("★☆★☆欢迎查询课表☆★☆★");
	JLabel lWelcome2 = new JLabel("按下刷新课表按钮即可刷新课表！");
	JLabel lWelcome3 = new JLabel("注意：选课、退课之后都要先刷新课表才能看到最新课表噢。");
	JLabel lWelcome4 = new JLabel("————————————————————————————————————————");
	

	String []type = {"", "星期一", "星期二", "星期三", "星期四", "星期五"};
	
	DefaultTableModel t;
	int selected;
	
//	public void add(Component c, GridBagConstraints cons, int x, int y, int w, int h) {
//		cons.gridx = x;
//		cons.gridy = y;
//		cons.gridwidth = w;
//		cons.gridheight = h;
//		add(c, cons);
//	}
		
	public CheckCourseGUI(User u, ArrayList<Course> c,Image image) {
		this.image = image;

		client = new Client();

		JLabel title = new JLabel("| 学生课表查询");
//		设置布局
		GridBagLayout lay = new GridBagLayout();
		this.setLayout(lay);
		GridBagConstraints cons = new GridBagConstraints();
		cons.weightx = 3;
		cons.weighty = 4;
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.CENTER;
		GridBagConstraints cons1 = new GridBagConstraints();
		cons1.weightx = 1;
		cons1.weighty = 1;
		cons1.fill = GridBagConstraints.NONE;
		cons1.anchor = GridBagConstraints.WEST;
		
////		设置窗口大小
//		Toolkit kit = Toolkit.getDefaultToolkit();
//		Dimension screenSize = kit.getScreenSize();
//		int width = screenSize.width;
//		int height = screenSize.height;
//
//		设置表格
		int num = c.size();
//		table.setPreferredScrollableViewportSize(new Dimension(300, 250));
//		p[0][1] = "星期一"; p[0][2] = "星期二"; p[0][3] = "星期三"; p[0][4] = "星期四"; p[0][5] = "星期五";
		p[0][0] = "第一二节"; p[1][0] = "第三四节";
		p[2][0] = "第五六节"; p[3][0] = "第七八节";
//		else {
		user = u;
		courses = c;
		if(num!=0) 
		{
			for(int i = 0; i < num; i++) {
				for(int j = 0; j < 3; j++) {
//						判断是否有课
					if((courses.get(i).getTime())[j] == 0) {
					} else {
						int x = (courses.get(i).getTime())[j] % 4 - 1;
						int y = (courses.get(i).getTime())[j] / 4;
						if(x == -1) x = 3;
						else y += 1;
						p[x][y] = "<html>"+courses.get(i).getCourseName() + "<br>" + courses.get(i).getClassRoom()+
								"<br>"+courses.get(i).getTeacherName()+"</html>";
					}
				}
			}
		}
		
		table = new JTable(p,type);
		
//		this.add(table, BorderLayout.CENTER);
//		this.add(table.getTableHeader(), BorderLayout.NORTH);
//		add(table, cons, 0, 5, 1, 1);

//		add(bRefresh, cons, 0, 6, 1, 1);
		
//		刷新响应——通信
		bRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				通信——课表查询
				Message mes = new Message();
				mes.setUserId(user.getId());
				mes.setUserType(user.getUserType());
				mes.setMessageType(Constants.course_query);
				Message serverResponse = client.sendRequestToServer(mes);
				courses = (ArrayList<Course>)serverResponse.getData();
				
				
				for(Course c:courses)
				{
					System.out.println(c.getCourseName());
				}
				
				if(serverResponse.isLastOperState()&&(courses.size()!=0)) {
					System.out.println(serverResponse.isLastOperState()+"!!!");

					for(int i1=0;i1<4;i1++)
						for(int j1=1;j1<6;j1++)
							p[i1][j1]="";
					
					for(int i = 0; i < courses.size(); i++) {
						for(int j = 0; j < 3; j++) {
	//				判断是否有课
							if((courses.get(i).getTime())[j] == 0) {
							} else {
								int x = (courses.get(i).getTime())[j] % 4 - 1;
								int y = (courses.get(i).getTime())[j] / 4;
								if(x == -1) x = 3;
								else y += 1;
								p[x][y] = "<html>"+courses.get(i).getCourseName() + "<br>" + courses.get(i).getClassRoom()+
										"<br>"+courses.get(i).getTeacherName()+"</html>";
							}
						}
					}
					
					table.repaint();
				}
				
				else if(serverResponse.isLastOperState()&&(courses.size()==0)) {
					for(int i1=0;i1<4;i1++)
						for(int j1=1;j1<6;j1++)
							p[i1][j1]="";
					JOptionPane.showMessageDialog(null, "你当前没有课程，请注意选课！");
					table.repaint();
				}
				
				
//				else
//					JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
				
			}
		});
		
		
////		设置字体颜色和大小
//		for(int i = 0; i < this.getComponentCount(); i++){
//			Font font = new Font("宋体", Font.CENTER_BASELINE, 14);
//			this.getComponent(i).setFont(font);
//		}
		
//
//		Font font1 = new Font("楷体", Font.CENTER_BASELINE, 15);
//		lWelcome0.setFont(font1);
//		lWelcome1.setFont(font1);
//		lWelcome2.setFont(font1);
//		lWelcome3.setFont(font1);
//		lWelcome4.setFont(font1);
		
//		this.add(lWelcome0, cons, 0, 0, 1, 1);
//	    this.add(lWelcome1, cons, 0, 1, 1, 1);
//	    this.add(lWelcome2, cons, 0, 2, 1, 1);
//	    this.add(lWelcome3, cons, 0, 3, 1, 1);
//	    this.add(lWelcome4, cons, 0, 4, 1, 1);
////		设置背景颜色
//		Color bgColor = new Color(206, 206, 206);
//		this.setBackground(bgColor);

//		this.setBorder(BorderFactory.createTitledBorder("课表查询"));
		table.setPreferredSize(new Dimension(800,320));
		table.setRowHeight(60);
		JScrollPane s=new JScrollPane(table);
		s.setBounds(60, 260, 800, 320);
		this.add(s);

//		add(type, 0, 0, 1, 1);

		Font subFont = new Font("苹方 常规",Font.CENTER_BASELINE,70);
		Font font = new Font("苹方 常规",Font.CENTER_BASELINE,15);

		title.setFont(subFont);
		bRefresh.setFont(font);

		title.setSize(1000, 300);
		title.setLocation(210, -70);
		this.add(title);

		bRefresh.setSize(120,35);
		bRefresh.setLocation(720,60);
		this.add(bRefresh);

		lWelcome0.setSize(1000,25);
		lWelcome0.setLocation(210,160);
		this.add(lWelcome0);

		lWelcome1.setSize(600,25);
		lWelcome1.setLocation(380,180);
		this.add(lWelcome1);

		lWelcome2.setSize(600,25);
		lWelcome2.setLocation(380,200);
		this.add(lWelcome2);

		lWelcome3.setSize(800,25);
		lWelcome3.setLocation(320,220);
		this.add(lWelcome3);

		lWelcome4.setSize(1000,25);
		lWelcome4.setLocation(210,240);
		this.add(lWelcome4);

		//  	白色
		final ImageIcon whitebox = new ImageIcon("src/imgs/white-dark.png");
		final JLabel loginbox = new JLabel(whitebox);
		loginbox.setSize(800,450);
		loginbox.setLocation(60,150);
		this.add(loginbox);

//  	校徽
		final ImageIcon logo = new ImageIcon("src/imgs/logo-small.png");
		final JLabel Llogo = new JLabel(logo);
		Llogo.setSize(130,130);
		Llogo.setLocation(60,15);
		this.add(Llogo);

		this.setOpaque(false);
		this.setLayout(null);
	}
}