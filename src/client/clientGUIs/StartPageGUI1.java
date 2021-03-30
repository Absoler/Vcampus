package client.clientGUIs;

import util.*;
import vo.*;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

//import Login;
//import client.util.Client;
import client.util.*;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

class test2 implements TreeSelectionListener{
	
//	�û�
	Client client = new Client();
	
//	User
	User user;
	
	private static ImageIcon[] imageIcon = new ImageIcon[1];//ͼ��ʹ��
	
	public test2(){
		new test1();
	}
	
	JSplitPane sp = new JSplitPane();
	JPanel lp1 = new JPanel();
	JPanel temprp = new JPanel(); // ������������ɫ
	
	Image image=new ImageIcon("src/imgs\\siBG2.png").getImage();
	LogInGUI rp1 = new LogInGUI(image); // �û���½�Ľ���
	RegisterGUI rp2 = new RegisterGUI(image); 

//	���ñ�����ɫ
	Color bgColor = new Color(255, 255, 255);
	
	JTree tr;
	DefaultTreeModel tm;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("��Ϣ�Ż�");
	DefaultMutableTreeNode nd1 = new DefaultMutableTreeNode("��¼");
	DefaultMutableTreeNode nd2 = new DefaultMutableTreeNode("ע��");
	
	class test1 extends JPanel{
		private static final long serialVersionUID = 1L;
		static final int WIDTH = 400;
		static final int HEIGHT = 200;
		//JFrame f;
		final JFrame f;
		
		public void add(Component c, GridBagConstraints cons, int x, int y, int w, int h) {
			cons.gridx = x;
			cons.gridy = y;
			cons.gridwidth = w;
			cons.gridheight = h;
			add(c, cons);
		}
		
		public test1() // ��������ֵ����๹�캯��
		{
			f = new JFrame("VcampusУ԰��Ϣ����ϵͳ");
			f.setSize(1080,675); 
			f.setLocationRelativeTo(null);
			f.setResizable(false);
			//f = new JFrame("�û���¼");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//GridBagLayout lay = new GridBagLayout();
			//setLayout(lay);
			
//			f.add(this, BorderLayout.WEST);
//			f.setSize(WIDTH, HEIGHT);
//			Toolkit kit = Toolkit.getDefaultToolkit();
//			Dimension screenSize = kit.getScreenSize();
//			int width = screenSize.width;
//			int height = screenSize.height;
//			int x = (width - WIDTH) / 2;
//			int y = (height - HEIGHT) / 2;
//			f.setLocation(x, y);
			
//			 ������
			root.add(nd1);
			root.add(nd2);
			tr = new JTree(root);
			
			lp1.add(tr);
			sp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			sp.setLeftComponent(lp1);
			sp.setDividerSize(7);
			sp.setContinuousLayout(true);
//			 ��������ѡ��ģʽ
			tr.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			
//			 ������ɫ
			lp1.setBackground(bgColor);
			rp1.setBackground(bgColor);
			rp2.setBackground(bgColor);
			temprp.setBackground(bgColor);
			sp.setRightComponent(temprp);

//			��������
			
      		JPanel subPanel = new JPanel();
    		subPanel.setOpaque(false);
    		subPanel.setLayout(null);
			
			// ����ͼƬ
			String path = "src/imgs/siBG2.png";
      		final ImageIcon background = new ImageIcon(path);
      		final JLabel label = new JLabel(background);
      		label.setBounds(0, 0, 1080,f.getHeight());
      		final JPanel imagePanel = (JPanel) f.getContentPane();
      		imagePanel.setOpaque(false);
      		//subPanel.getRootPane().add(label, new Integer(Integer.MIN_VALUE));
      		
      	    //Ϊ�������Ӵ��ڼ�����������Ļ�ı��С��
      		imagePanel.addComponentListener(new ComponentAdapter(){		
    			@Override
    			public void componentResized(ComponentEvent e)
    			{
    				//ɾ��JLabel
    				imagePanel.remove(label);
    				//�������
    				imageIcon[0] = new ImageIcon(new ImageIcon("src/imgs\\siBG2.png").getImage().getScaledInstance(imagePanel.getSize().width, imagePanel.getSize().height, Image.SCALE_DEFAULT));
    				label.setIcon(imageIcon[0]);
    				label.setHorizontalAlignment(SwingConstants.CENTER);
    				imagePanel.add(label);
    			}
    		});
    		
    		JPanel wordPanel = new JPanel();
    		wordPanel.setOpaque(false);
    		wordPanel.setLayout(null);
    		
    		//����
    		JLabel title = new JLabel("Vcampus У԰��Ϣ����ϵͳ");
    		Font megaFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,55);
    		title.setFont(megaFont);
    		title.setSize(1000, 200);
    		title.setLocation(135, 200);
    		wordPanel.add(title);
   		
    		imagePanel.add(wordPanel);
    		
    		sp.setRightComponent(imagePanel);

			f.setVisible(true);
			f.setContentPane(sp);
			
//			 ���ڵ���¼�����
			tr.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent e) {
					DefaultMutableTreeNode selectednd = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
						
//					��¼
					if(selectednd == nd1) {
						sp.setRightComponent(rp1);
						f.setVisible(true);
						sp.setDividerLocation(150);
//						OK��ť��Ӧ
						rp1.bOK.addActionListener(new ActionListener(){
							public void actionPerformed(java.awt.event.ActionEvent Event) {
								String idtext = rp1.nameinput.getText();
								int intidtext = Integer.parseInt(idtext);
								
								String pswtext = rp1.passwordinput.getText();
								String str = new String(pswtext);
//								isStu == 1 --> ѡ����ѧ��
								rp1.isStu = rp1.rbStu.isSelected();
								rp1.isTea = rp1.rbTea.isSelected();
								rp1.isAdm = rp1.rbAdm.isSelected();
								
//								�쳣�¼����������Ի���
								if(idtext.equals("")) {
									JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ����������롣");
								}else if(pswtext.equals("")) {
									JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ����������롣");
								}else if(!rp1.isStu && !rp1.isTea && !rp1.isAdm) {
									JOptionPane.showMessageDialog(null, "��ѡ����ݣ�ѧ��/��ʦ/����Ա��");
								}
//								��¼����
								else if(rp1.isStu){
									
									Message mes = new Message();
									mes.setUserType(0); // student: 0
									
									Login login = new Login();
									login.setId(intidtext);
									login.setUserType(0);
									login.setPwd(pswtext);
									mes.setData(login);
									mes.setMessageType("USER_LOGIN");
									
									Message serverResponse = client.sendRequestToServer(mes);
									
//									����¼�ɹ�
									Student student;
									if(serverResponse.isLastOperState()){
										rp1.isSuccess = true;
										student = (Student)(serverResponse.getData());
										
										f.dispose();
										new MainGUI(student);
										new ChatGUI3(student.getNickName()).setVisible(true);;
									}
//									��½���ɹ�
									else {
										JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
									}
										
										
//									rp1.isSuccess = true;
//									f.dispose();
//									Student student = new Student();
//									student.setAge(18);
//									student.setBorrowNum(2);
//									student.setDeposit(143);
//									student.setDormNum("÷5D637");
//									student.setGPA(3.56);
//									student.setId(301);
//									student.setNickName("����");
//									student.setPunishment("��");
//									student.setPwd("111");
//									student.setRealName("����");
//									student.setSex("Ů");
//									
//									
//									new MainGUI(student);
										
								}
								else if(rp1.isTea) {
									
									Message mes = new Message();
									mes.setUserType(1); // teacher: 1
									
									Login login = new Login();
									login.setUserType(1);
									login.setPwd(pswtext);
									login.setId(intidtext);
									mes.setData(login);
									mes.setMessageType("USER_LOGIN");
									
									Message serverResponse = client.sendRequestToServer(mes);
									
//									����¼�ɹ�
									Teacher teacher;
									if(serverResponse.isLastOperState()){
										rp1.isSuccess = true;
										teacher = (Teacher)(serverResponse.getData());
										
										f.dispose();
										new MainGUI(teacher);
										new ChatGUI3(teacher.getNickName()).setVisible(true);;
									}
//									��½���ɹ�
									else {
										JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
									}
//									
//									rp1.isSuccess = true;
//									f.dispose();
//									Teacher teacher = new Teacher();
//									teacher.setAge(36);
//									teacher.setBorrowNum(1);
//									teacher.setDeposit(8059);
//									teacher.setId(213);
//									teacher.setNickName("����");
//									teacher.setPwd("111");
//									
//									teacher.setRealName("���ܸ�");
//									teacher.setSex("��");
//									teacher.setTitle("������");
//									
//									new MainGUI(teacher);
								}
//								����Ա
								else if(rp1.isAdm) {
									Message mes = new Message();
									mes.setUserType(1); // teacher: 1
									
									Login login = new Login();
									login.setUserType(2);
									login.setPwd(pswtext);
									login.setId(intidtext);
									mes.setData(login);
									mes.setMessageType("USER_LOGIN");
									
									Message serverResponse = client.sendRequestToServer(mes);
									
//									����¼�ɹ�
									Admin admin;
									if(serverResponse.isLastOperState()){
										rp1.isSuccess = true;
										admin = (Admin)(serverResponse.getData());
										
										f.dispose();
										new MainGUI(admin);
									}
//									��½���ɹ�
									else {
										JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
									}
									
//									rp1.isSuccess = true;
//									f.dispose();
//									Admin admin = new Admin();
////									admin.setAge(77);
//									
//									new MainGUI(admin);
								}
							}

						});
					}
						
//					ע��
					else if(selectednd == nd2) {
						sp.setRightComponent(rp2);
						f.setVisible(true);
						sp.setDividerLocation(150);
						rp2.bOK.addActionListener(new ActionListener(){
							public void actionPerformed(java.awt.event.ActionEvent Event) {
								String idtext = rp2.nameinput.getText();
								//int intidtext = Integer.parseInt(idtext);
								String pswtext = rp2.passwordinput.getText();
								String admitpswtext = rp2.admitpasswordinput.getText();
								String str = new String(pswtext);
								
//								isStu == 1 --> ѡ����ѧ��
								rp2.isStu = rp2.rbStu.isSelected();
								rp2.isTea = rp2.rbTea.isSelected();
//								rp2.isAdm = rp2.rbAdm.isSelected();
								
//								�쳣�¼����������Ի���
								if(idtext.equals("")) {
									JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ����������롣");
									rp2.passwordinput.setText("");
									rp2.admitpasswordinput.setText("");
								}else if(pswtext.equals("")) {
									JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ����������롣");
								}else if(admitpswtext.equals("")) {
									JOptionPane.showMessageDialog(null, "��ȷ�����롣");
								}else if(!admitpswtext.equals(pswtext)) {
									JOptionPane.showMessageDialog(null, "�����������벻��ͬ�����������롣");
									rp2.passwordinput.setText("");
									rp2.admitpasswordinput.setText("");
								}else {
									Message mes = new Message();
									if(rp2.isStu)
										mes.setUserType(0); // student: 0
									else if(rp2.isTea)
										mes.setUserType(1);
//								else if(rp2.isAdm)
//									mes.setUserType(2);
								
//								Login login = new Login();
//								login.setId(intidtext);
//								if(rp2.isStu)
//									login.setUserType(0);
//								else if(rp2.isTea)
//									login.setUserType(1);
////								else if(rp2.isAdm)
////									login.setUserType(2);
//								login.setPwd(pswtext);
//								mes.setData(login);
								
									if(rp2.isStu) {
										Student s=new Student();
										s.setUserType(0);
										s.setNickName(idtext);
										s.setPwd(pswtext);
										mes.setData(s);
									}
									else if(rp2.isTea) {
										Teacher t=new Teacher();
										t.setUserType(1);
										t.setNickName(idtext);
										t.setPwd(pswtext);
										mes.setData(t);
									}
								
									mes.setMessageType(Constants.register);
									Message serverResponse = client.sendRequestToServer(mes);
								
//								��ע��ɹ����������ڸ����û�ID
									if(serverResponse.isLastOperState())
										JOptionPane.showMessageDialog(null, "ע��ɹ��������û���Ϊ��\n" 
												+ serverResponse.getData());
									else {
										JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
									}
								}
							}
						});
					} 
					
				}
			});
			f.setVisible(true);
			sp.setDividerLocation(150);
		}
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

public class StartPageGUI1{
	public static void main(String []args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {}
		test2 log = new test2();
	}
}

