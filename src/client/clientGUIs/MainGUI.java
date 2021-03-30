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

// ��һ�����캯�����ѵ�¼��
public class MainGUI implements TreeSelectionListener{
	
	JFrame f = new JFrame("VCampus");
	
//	ImageIcon icon1 = new ImageIcon("src/imgs/pictures\\00.png" );
//	
//	JLayeredPane layeredPane = new JLayeredPane()
//	{			
//		public void paintComponent(Graphics g) {
//			//��д�������ķ���				
//			super.paintComponent(g);				
//			ImageIcon image = new ImageIcon("src/imgs/pictures\\00.png");//����ͼƬ				
//			image.setImage(image.getImage().getScaledInstance(this.getWidth(), 
//					this.getHeight(), Image.SCALE_AREA_AVERAGING));//����ͼƬ��С��������С				
//			g.drawImage(image.getImage(), 0, 0, this);//���»������		
//		}
//	};
	
	static final int WIDTH = 1080;
	static final int HEIGHT = 675;
	
//	0/1/2��ѧ��/��ʦ/����Ա
	int userType;
	
//	user
	User user;
	Client client;
	
//	books, courses
	ArrayList<BookInfo> books = new ArrayList<BookInfo>();
	ArrayList<Course> courses = new ArrayList<Course>();
	ArrayList<Course> chooseCourses = new ArrayList<Course>();
	ArrayList<Product> products = new ArrayList<Product>();
	
//	����UI
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
	
//	 Splitpane������panel, �Լ����
	JTree tr;
	
	JSplitPane sp = new JSplitPane();
	JPanel lpanel = new JPanel();
	JPanel rpanel = new JPanel();
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("��Ϣ�Ż�");
	DefaultMutableTreeNode nd1 = new DefaultMutableTreeNode("������Ϣ");
	DefaultMutableTreeNode nd2 = new DefaultMutableTreeNode("�������");
	DefaultMutableTreeNode nd21 = new DefaultMutableTreeNode("������");
	DefaultMutableTreeNode nd22 = new DefaultMutableTreeNode("����");
	DefaultMutableTreeNode nd23 = new DefaultMutableTreeNode("ѡ��");
	DefaultMutableTreeNode nd24 = new DefaultMutableTreeNode("��α�");
	DefaultMutableTreeNode nd25 = new DefaultMutableTreeNode("����");
	DefaultMutableTreeNode nd26 = new DefaultMutableTreeNode("�˿�");
	DefaultMutableTreeNode nd3 = new DefaultMutableTreeNode("����ͼ���");
	DefaultMutableTreeNode nd4 = new DefaultMutableTreeNode("�����̵�");
	DefaultMutableTreeNode nd5 = new DefaultMutableTreeNode("�����Ʒ"); // ����Ա��
	DefaultMutableTreeNode nd6 = new DefaultMutableTreeNode("���ͼ��"); // ����Ա��
	DefaultMutableTreeNode nd31 = new DefaultMutableTreeNode("����"); // ����
	
//	 ���ù�����
	JScrollPane slp;
	
	public MainGUI(User u) {
		client = new Client();
//		������ʦ/ѧ����user
		this.user = u;
		userType = u.getUserType();
		
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		Image image = new ImageIcon("src/imgs/siBG2-dark.png").getImage();
//		library test
		BookInfo b1 = new BookInfo(); b1.setBookName("��С���ӡ�"); 
		b1.setAuthor("������ʥ"); b1.setPublisher("���ϳ�����"); b1.setStockNum(5);
		BookInfo b2 = new BookInfo(); b2.setBookName("����¥�Ρ�"); 
		b2.setAuthor("��ѩ��"); b2.setPublisher("���ų�����"); b2.setStockNum(10);
		BookInfo b3 = new BookInfo(); b3.setBookName("��Pokemon Dream Travel��"); 
		b3.setAuthor("������"); b3.setPublisher("PM������"); b3.setStockNum(8);
		books.add(b1); books.add(b2); books.add(b3);
		
//		course test
		Course c1 = new Course(); Course c2 = new Course(); 
		int []t1 = {3, 15, 16}; int []t2 = {1, 9, 0};
		c1.setClassRoom("J1-404"); c1.setTime(t1);
		c2.setClassRoom("J4-101"); c2.setTime(t2);
		c1.setCourseName("JAVA"); c2.setCourseName("����");
		c1.setTeacherName("������"); c2.setTeacherName("�ι���");
		c1.setCredit(2); c2.setCredit(1);
		courses.add(c1); courses.add(c2);
		chooseCourses.add(c1); chooseCourses.add(c2);
		chooseCourses.add(c1); chooseCourses.add(c2);
		chooseCourses.add(c1); chooseCourses.add(c2);
		chooseCourses.add(c1); chooseCourses.add(c2);
		
//		 ������
		tr = new JTree(root);
		if(userType == 0 || userType == 1) {
			root.add(nd1); root.add(nd2);
			root.add(nd3); root.add(nd4);
//			��ʦ
			if(userType == 1) {
				nd2.add(nd21); // ������
				nd2.add(nd22); // ����
				nd2.add(nd24); // ��α�
				nd2.add(nd25); // ����
				nd3.add(nd31); // ����
			}
//			ѧ��
			else {
				nd2.add(nd23); // ѡ��
				nd2.add(nd24); // ��α�	
				nd2.add(nd26); // �˿�
				nd3.add(nd31); // ����
			}
		}
//		����Ա
		else {
			root.add(nd5); // ͼ���
			root.add(nd6); // �̵�
		}
			
		
//		��������UI
		Message mes = new Message();
//		ѧ��
		if(userType == 0) {
			
			f.setVisible(true);
			sp.setDividerLocation(150);
			stuInfor = new StudentInfoGUI((Student)(this.user),image);
			academicAffair = new AcademicAffairGUI((Student)(this.user),image);
		
			
//			ͨ�š����α��ѯ		
			mes.setUserType(0); // student: 0
			mes.setMessageType("COURSE_QUERY");
			mes.setUserId(user.getId());
			Message serverResponse = client.sendRequestToServer(mes);
			courses = (ArrayList<Course>)serverResponse.getData();
		
//			ͨ�š���ѡ��
			mes.setMessageType("CHOOSE_QUERY");
			serverResponse = client.sendRequestToServer(mes);
			chooseCourses = (ArrayList<Course>)serverResponse.getData();
			
			checkCourse = new CheckCourseGUI(this.user, courses,image);
			chooseCourse = new ChooseCourseGUI((Student)(this.user), chooseCourses,image);
			courseDelete = new CourseDeleteGUI((Student)(this.user), chooseCourses,image);
		}
			
//		��ʦ
		else if(userType == 1){
			
//			ͨ�š����α��ѯ		
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
//		����Ա
		else if(userType == 2){
			productAdd = new ProductAddGUI();
			bookAdd = new BookAddGUI();
		}
		
//		ͨ�š���ͼ���
		mes.setMessageType("LIBRARY_QUERY");
		mes.setUserId(user.getId());
		Message serverResponse = client.sendRequestToServer(mes);
//		serverResponse = client.sendRequestToServer(mes);
		books = (ArrayList<BookInfo>)serverResponse.getData();
		library = new LibraryGUI(this.user, books,image);
		
//		ͨ�š����̵�
		mes.setMessageType("SHOP_QUERY");
		serverResponse = client.sendRequestToServer(mes);
		products = (ArrayList<Product>)serverResponse.getData();
		shop = new ShopGUI(this.user);
		
//		����
		bookReturn = new BookReturnGUI(this.user,image);
		
		
		
		slp = new JScrollPane(tr, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		lpanel.add(slp);
//		lpanel.add(icon1);
		
		sp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		sp.setLeftComponent(lpanel);
		
//		���ñ�����ɫ
		Color bgColor = new Color(255, 255, 255); // ��ɫ
		lpanel.setBackground(bgColor);
		rpanel.setBackground(bgColor);
		
//		 ���ô��ڴ�С�����������������ûʲô��
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		f.setSize(width, height);
		
//		����splitpane���Ҳ���
		sp.setLeftComponent(slp);
		sp.setRightComponent(rpanel); // ����ȱʡ
		
//		 ��������ѡ��ģʽ��������
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
				else if(selectednd.equals(nd23)) { // ѡ��
					slp = new JScrollPane(chooseCourse, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd24)) { // ��α�
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
				else if(selectednd.equals(nd25)) { // ����
					slp = new JScrollPane(courseAdd, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setRightComponent(slp);
				}
				else if(selectednd.equals(nd26)) { // ����
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
		
		f.setLayout(new GridLayout(1, 1));//��������ó����񲼾֣�ֻ����һ��һ�У�ʹͼƬ����������壬�������������ķŴ����С�ı�
		
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
//		f.setResizable(false); // �Ƿ�����û����������С
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		���÷ָ��߱����Լ����ɵ�������OK���������f.setVisible��
		sp.setDividerLocation(0.12);
//		sp.setEnabled(false);
		int width1 = screenSize.width;
		int height1 = screenSize.height;
		int x = (width1 - WIDTH) / 2-240;
		int y = (height1 - HEIGHT) / 2;
		f.setLocation(x, y);
		
//		��˵�����طָ��ߣ�Ŀǰ����
//		UIDefaults defaults = UIManager.getDefaults();
//		defaults.remove( "SplitPane.border");
//		defaults.remove( "SplitPaneDivider.border"); 
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
