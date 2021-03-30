package client.clientGUIs;

import java.awt.*;

import java.awt.event.ActionListener;

import javax.swing.*;

import client.util.Client;
import java.awt.event.*;
import util.*;

public class StudentInfoGUI extends JPanel {
	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;

	// �̶�����ͼƬ���������JPanel������ͼƬ������������
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	Student stu;
	Client client;
//		 ѧ��������Ϣ
	JLabel title = new JLabel("| ѧ��������Ϣȷ��");
	JLabel lName = new JLabel("������");
	JLabel lSex = new JLabel("�Ա�");
	JLabel lID = new JLabel("ѧ�ţ�");
	JLabel lDorm = new JLabel("���᣺");
	JLabel lDeposit = new JLabel("��");
	JLabel lBorrow = new JLabel("�ѽ�����Ŀ��");
	JButton button = new JButton("ȷ���޸�");

	JComboBox comboBox = new JComboBox();
	String tmp; // ���ڻ�ȡ�Ա�
	Message msg = new Message();
	JTextField nameinput = new JTextField(15);
//	JTextField sexinput = new JTextField(15);
	JTextField dorminput = new JTextField(15);
	JTextField IDinput = new JTextField(15);
	JTextField depositinput = new JTextField(15);
	JTextField borrowinput = new JTextField(15);
			
//		public void paintComponent(Graphics g) {
//			super.paintComponent(g);
//			setBackground(Color.WHITE);
//			if (image != null) {
//				int height = image.getHeight(this);
//				int width = image.getWidth(this);
//				if (height != -1 && height > getHeight())
//					height = getHeight();
//				if (width != -1 && width > getWidth())
//					width = getWidth();
//				int x = (int)(((double)(getWidth() - width)) / 2.0);
//				int y = (int)(((double)(getHeight() - height)) / 2.0);
//				g.drawImage(image, x, y, width, height, this);
//			}
//		}
	
//			���ڴ�ӡ������Ϣ�ĺ���	
	public void display(Student user) {
		nameinput.setText(user.getRealName());
//		sexinput.setText(user.getSex());
		dorminput.setText(user.getDormNum());
		IDinput.setText("" + user.getId());
		depositinput.setText("" + user.getDeposit());
		borrowinput.setText(user.getBorrowNum() + "");
		comboBox.setSelectedItem(user.getSex());
	}
	
	public StudentInfoGUI(Student user, Image image) {
		this.image = image;

		client = new Client();
		
//			�������
		stu = (Student)user;
		comboBox.addItem("��");
		comboBox.addItem("Ů");
		comboBox.setSize(300, 300);
		msg.setMessageType(Constants.updateUserInfo);
		msg.setUserType(0);
		display(stu);
		
//		nameinput.setEditable(false);
//		sexinput.setEditable(false);
		dorminput.setEditable(false);
		IDinput.setEditable(false);
		depositinput.setEditable(false);
		borrowinput.setEditable(false);

		
////			����������ɫ�ʹ�С
//		for(int i = 0; i < this.getComponentCount(); i++){
//			Font font = new Font("ƻ�� ����", Font.CENTER_BASELINE,15);
//			this.getComponent(i).setFont(font);
//		}
		//		���������ʽ�ʹ�С
		Font megaFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,60);
		Font subFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,70);
		Font font = new Font("ƻ�� ����",Font.CENTER_BASELINE,30);
		Font font1 = new Font("ƻ�� ����",Font.BOLD,15);
		Font font2 = new Font("ƻ�� ����",Font.CENTER_BASELINE,15);
		//title.setFont(megaFont);
		title.setFont(subFont);
		lName.setFont(font);
		lSex.setFont(font);
		lID.setFont(font);
		lDorm.setFont(font);
		lDeposit.setFont(font);
		lBorrow.setFont(font);

		title.setSize(1000, 300);
		title.setLocation(230, -60);
		this.add(title);


		lName.setSize(150,40);
		lName.setLocation(330,190);
		this.add(lName);

		nameinput.setSize(170,40);
		nameinput.setLocation(470,190);
		this.add(nameinput);


		lSex.setSize(150,40);
		lSex.setLocation(330,250);
		this.add(lSex);

		comboBox.setSize(170,40);
		comboBox.setLocation(470,250);
		this.add(comboBox);


		lID.setSize(150,40);
		lID.setLocation(330, 310);
		this.add(lID);

		IDinput.setSize(170,40);
		IDinput.setLocation(470,310);
		this.add(IDinput);


		lDorm.setSize(150,40);
		lDorm.setLocation(330, 370);
		this.add(lDorm);

		dorminput.setSize(170,40);
		dorminput.setLocation(470,370);
		this.add(dorminput);


		lDeposit.setSize(150,40);
		lDeposit.setLocation(330,430);
		this.add(lDeposit);

		depositinput.setSize(170,40);
		depositinput.setLocation(470,430);
		this.add(depositinput);


		lBorrow.setSize(230,40);
		lBorrow.setLocation(280,490);
		this.add(lBorrow);

		borrowinput.setSize(170,40);
		borrowinput.setLocation(470,490);
		this.add(borrowinput);


		button.setSize(150,35);
		button.setLocation(380,550);
		this.add(button);

		//  	��ɫ
		final ImageIcon whitebox = new ImageIcon("src/imgs/white-dark.png");
		final JLabel loginbox = new JLabel(whitebox);
		loginbox.setSize(760,420);
		loginbox.setLocation(80,180);
		this.add(loginbox);

//  	У��
		final ImageIcon logo = new ImageIcon("src/imgs/logo-small.png");
		final JLabel Llogo = new JLabel(logo);
		Llogo.setSize(130,130);
		Llogo.setLocation(60,30);
		this.add(Llogo);

		this.setOpaque(false);
		this.setLayout(null);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// TODO Auto-generated method stub
				
				tmp=(String)comboBox.getSelectedItem();
				stu.setSex(tmp);
				stu.setRealName(nameinput.getText());
				msg.setData(stu);
				Message serverResponse = client.sendRequestToServer(msg);//����msg
				if(serverResponse.isLastOperState())
					JOptionPane.showMessageDialog(null, "������Ϣ���³ɹ���");
				else 
					JOptionPane.showMessageDialog(null, "δ֪������Ϣ���´����������ݿ⣡");
			}
		});
	}
}
