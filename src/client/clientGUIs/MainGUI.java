package client.clientGUIs;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import client.util.Client;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import util.*;
import vo.*;

// 加一个构造函数，把登录的
public class MainGUI implements TreeSelectionListener{
	
	JFrame f = new JFrame("VCampus");
	
//	ImageIcon icon1 = new ImageIcon("src/imgs/pictures\\00.png" );
//	
//	JLayeredPane layeredPane = new JLayeredPane()
//	{			
//		public void paintComponent(Graphics g) {
//			//重写绘制面板的方法				
//			super.paintComponent(g);				
//			ImageIcon image = new ImageIcon("src/imgs/pictures\\00.png");//导入图片				
//			image.setImage(image.getImage().getScaledInstance(this.getWidth(), 
//					this.getHeight(), Image.SCALE_AREA_AVERAGING));//设置图片大小跟随面板大小				
//			g.drawImage(image.getImage(), 0, 0, this);//重新绘制面板		
//		}
//	};
	
	static final int WIDTH = 1080;
	static final int HEIGHT = 675;
	
//	0/1/2是学生/老师/管理员
	int userType;
	
//	user
	User user;
	Client client;
	
//	books, courses
	ArrayList<BookInfo> books = new ArrayList<BookInfo>();
	ArrayList<Course> courses = new ArrayList<Course>();
	ArrayList<Course> chooseCourses = new ArrayList<Course>();
	ArrayList<Product> products = new ArrayList<Product>();
	
//	各个UI
	StudentInfoGUI stuInfor; // = new StudentInfoGUI(null);
	TeacherInfoGUI teaInfor; // = new TeacherInfoGUI(null);
	AcademicAffairGUI academicAffair; // = new AcademicAffairGUI();
	ShopGUI shop; // = new ShopGUI();
	DormChangeGUI dormChange; // = new DormChangeGUI();
	PunishGUI punish; // = new PunishGUI();
	LibraryGUI library;
	CheckCourseGUI checkCourse;
	ChooseCourseGUI chooseCourse;
	CourseAddGUI courseAdd;
	ProductAddGUI productAdd;
	BookAddGUI bookAdd;
	CourseDeleteGUI courseDelete;
	TeacherCheckCourseGUI teacherCheckCourse;
	BookReturnGUI bookReturn;
	
//	 Splitpane及左右panel, 以及结点
	JTree tr;
	
	JSplitPane sp = new JSplitPane();
	JPanel lpanel = new JPanel();
	JPanel rpanel = new JPanel();
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("信息门户");
	DefaultMutableTreeNode nd1 = new DefaultMutableTreeNode("个人信息");
	DefaultMutableTreeNode nd2 = new DefaultMutableTreeNode("教务服务");
	DefaultMutableTreeNode nd21 = new DefaultMutableTreeNode("调宿舍");
	DefaultMutableTreeNode nd22 = new DefaultMutableTreeNode("处分");
	DefaultMutableTreeNode nd23 = new DefaultMutableTreeNode("选课");
	DefaultMutableTreeNode nd24 = new DefaultMutableTreeNode("查课表");
	DefaultMutableTreeNode nd25 = new DefaultMutableTreeNode("开课");
	DefaultMutableTreeNode nd26 = new DefaultMutableTreeNode("退课");
	DefaultMutableTreeNode nd3 = new DefaultMutableTreeNode("虚拟图书馆");
	DefaultMutableTreeNode nd4 = new DefaultMutableTreeNode("虚拟商店");
	DefaultMutableTreeNode nd5 = new DefaultMutableTreeNode("添加商品"); // 管理员用
	DefaultMutableTreeNode nd6 = new DefaultMutableTreeNode("添加图书"); // 管理员用
	DefaultMutableTreeNode nd31 = new DefaultMutableTreeNode("还书"); // 还书
	
//	 设置滚动条
	JScrollPane slp;
	
	public MainGUI(User u) {
		client = new Client();
//		生成老师/学生的user
		this.user = u;
		userType = u.getUserType();
		
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		Image image = new ImageIcon("src/imgs/siBG2-dark.png").getImage();
//		library test
		BookInfo b1 = new BookInfo(); b1.setBookName("《小王子》"); 
		b1.setAuthor("安托万·圣"); b1.setPublisher("西瓜出版社"); b1.setStockNum(5);
		BookInfo b2 = new BookInfo(); b2.setBookName("《红楼梦》"); 
		b2.setAuthor("曹雪芹"); b2.setPublisher("新闻出版社"); b2.setStockNum(10);
		BookInfo b3 = new BookInfo(); b3.setBookName("《Pokemon Dream Travel》"); 
		b3.setAuthor("任天堂"); b3.setPublisher("PM出版社"); b3.setStockNum(8);
		books.add(b1); books.add(b2); books.add(b3);
		
//		course test
		Course c1 = new Course(); Course c2 = new Course(); 
		int []t1 = {3, 15, 16}; int []t2 = {1, 9, 0};
		c1.setClassRoom("J1-404"); c1.setTime(t1);
		c2.setClassRoom("J4-101"); c2.setTime(t2);
		c1.setCourseName("JAVA"); c2.setCourseName("计组");
		c1.setTeacherName("王世杰"); c2.setTeacherName("任国林");
		c1.setCredit(2); c2.setCredit(1);
		courses.add(c1); courses.add(c2);
		chooseCourses.add(c1); chooseCourses.add(c2);
		chooseCourses.add(c1); chooseCourses.add(c2);
		chooseCourses.add(c1); chooseCourses.add(c2);
		chooseCourses.add(c1); chooseCourses.add(c2);
		
//		 创建树
		tr = new JTree(root);
		if(userType == 0 || userType == 1) {
			root.add(nd1); root.add(nd2);
			root.add(nd3); root.add(nd4);
//			教师
			if(userType == 1) {
				nd2.add(nd21); // 调宿舍
				nd2.add(nd22); // 处分
				nd2.add(nd24); // 查课表
				nd2.add(nd25); // 开课
				nd3.add(nd31); // 还书
			}
//			学生
			else {
				nd2.add(nd23); // 选课
				nd2.add(nd24); // 查课表	
				nd2.add(nd26); // 退课
				nd3.add(nd31); // 还书
			}
		}
//		管理员
		else {
			root.add(nd5); // 图书馆
			root.add(nd6); // 商店
		}
			
		
//		创建各个UI
		Message mes = new Message();
//		学生
		if(userType == 0) {
			
			f.setVisible(true);
			sp.setDividerLocation(150);
			stuInfor = new StudentInfoGUI((Student)(this.user),image);
			academicAffair = new AcademicAffairGUI((Student)(this.user),image);
		
			
//			通信——课表查询		
			mes.setUserType(0); // student: 0
			mes.setMessageType("COURSE_QUERY");
			mes.setUserId(user.getId());
			Message serverResponse = client.sendRequestToServer(mes);
			courses = (ArrayList<Course>)serverResponse.getData();
		
//			通信——选课
			mes.setMessageType("CHOOSE_QUERY");
			serverResponse = client.sendRequestToServer(mes);
			chooseCourses = (ArrayList<Course>)serverResponse.getData();
			
			checkCourse = new CheckCourseGUI(this.user, courses,image);
			chooseCourse = new ChooseCourseGUI((Student)(this.user), chooseCourses,image);
			courseDelete = new CourseDeleteGUI((Student)(this.user), chooseCourses,image);
		}
			
//		教师
		else if(userType == 1){
			
//			通信——课表查询		
			mes.setUserType(1); // student: 0
			mes.setMessageType("COURSE_QUERY");
			mes.setUserId(user.getId());
			Message serverResponse = client.sendRequestToServer(mes);
			courses = (ArrayList<Course>)serverResponse.getData();
			
			teacherCheckCourse = new TeacherCheckCourseGUI((Teacher)(this.user),courses);

			
			f.setVisible(true);
			sp.setDividerLocation(150);
			teaInfor = new TeacherInfoGUI((Teacher)(this.user),image);
			
			mes.setMessageType(Constants.punishQUERY);
			serverResponse = client.sendRequestToServer(mes);
			ArrayList<Student> students = new ArrayList<Student>();
			students = (ArrayList<Student>)serverResponse.getData();
			
			dormChange = new DormChangeGUI(students,image);
			punish = new PunishGUI(image);
			courseAdd = new CourseAddGUI((Teacher)(this.user));
			
//			checkCourse = new CheckCourseGUI(this.user, courses);
		}
//		管理员
		else if(userType == 2){
			productAdd = new ProductAddGUI();
			bookAdd = new BookAddGUI();
		}
		
//		通信——图书馆
		mes.setMessageType("LIBRARY_QUERY");
		mes.setUserId(user.getId());
		Message serverResponse = client.sendRequestToServer(mes);
//		serverResponse = client.sendRequestToServer(mes);
		books = (ArrayList<BookInfo>)serverResponse.getData();
		library = new LibraryGUI(this.user, books,image);
		
//		通信——商店
		mes.setMessageType("SHOP_QUERY");
		serverResponse = client.sendRequestToServer(mes);
		products = (ArrayList<Product>)serverResponse.getData();
		shop = new ShopGUI(this.user);
		
//		还书
		bookReturn = new BookReturnGUI(this.user,image);
		
		
		
		slp = new JScrollPane(tr, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		lpanel.add(slp);
//		lpanel.add(icon1);
		
		sp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		sp.setLeftComponent(lpanel);
		
//		设置背景颜色
		Color bgColor = new Color(255, 255, 255); // 白色
		lpanel.setBackground(bgColor);
		rpanel.setBackground(bgColor);
		
//		 设置窗口大小——在这里面设好像没什么用
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		f.setSize(width, height);
		
//		设置splitpane左右部件
		sp.setLeftComponent(slp);
		sp.setRightComponent(rpanel); // 不可缺省
		
//		 设置树的选择模式、监听器
		tr.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tr.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectednd = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
				if(selectednd.equals(nd1)) {
					if(userType == 0) {
						slp = new JScrollPane(stuInfor, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//						layeredPane.add(slp);
//						f.setContentPane(layeredPane);
						sp.setRightComponent(slp);
					}
					else {
						slp = new JScrollPane(teaInfor, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
						sp.setRightComponent(slp);
					}
				}
				else if(selectednd.equals(nd2)) {
					if(userType == 0) {
						slp = new JScrollPane(academicAffair, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
						sp.setRightComponent(slp);
					}
				}
				else if(selectednd.equals(nd21)) {
					slp = new JScrollPane(dormChange, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd22)) {
					slp = new JScrollPane(punish, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd23)) { // 选课
					slp = new JScrollPane(chooseCourse, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd24)) { // 查课表
					if(userType == 0) {
						slp = new JScrollPane(checkCourse, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
								ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
						sp.setRightComponent(slp);
					}
					else if(userType == 1) {
						slp = new JScrollPane(teacherCheckCourse, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
								ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
						sp.setRightComponent(slp);
					}
				}
				else if(selectednd.equals(nd25)) { // 开课
					slp = new JScrollPane(courseAdd, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd26)) { // 开课
					slp = new JScrollPane(courseDelete, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd3)) {
					slp = new JScrollPane(library, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd31)) {
					slp = new JScrollPane(bookReturn, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd4)) {
					slp = new JScrollPane(shop, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd5)) {
					slp = new JScrollPane(productAdd, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd6)) {
					slp = new JScrollPane(bookAdd, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
			}
		});
		
		f.setLayout(new GridLayout(1, 1));//将面板设置成网格布局，只定义一行一列，使图片充满整个面板，并可以随着面板的放大和缩小改变
		
//		JLabel bgp = new JLabel(icon1);
//		JLabel test = new JLabel("test");
//		JButton testb = new JButton("testb");
//		JPanel tmp = new JPanel();
//		tmp.add(testb);
//		tmp.add(layeredPane);
//		layeredPane.setLayer(test, new Integer(0), 0);
//		layeredPane.setLayer(bgp, -1);
		
//		layeredPane.add(testb, new Integer(200), 0);
//		layeredPane.add(bgp);
		sp.setOpaque(false);
//		layeredPane.add(sp, new Integer(200), 0);
//		
//		layeredPane.setOpaque(false);
//
//		f.setContentPane(layeredPane);
		
//		JPanel tmp = new JPanel();
//		tmp.add(sp);
		
		f.setContentPane(sp);
		
		
		
		
//		f.pack();
		f.setSize(WIDTH, HEIGHT);
//		f.setResizable(false); // 是否可由用户调整窗体大小
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		设置分割线比例以及不可调整——OK，必须放在f.setVisible后
		sp.setDividerLocation(0.12);
//		sp.setEnabled(false);
		int width1 = screenSize.width;
		int height1 = screenSize.height;
		int x = (width1 - WIDTH) / 2-240;
		int y = (height1 - HEIGHT) / 2;
		f.setLocation(x, y);
		
//		据说是隐藏分割线，目前无用
//		UIDefaults defaults = UIManager.getDefaults();
//		defaults.remove( "SplitPane.border");
//		defaults.remove( "SplitPaneDivider.border"); 
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
