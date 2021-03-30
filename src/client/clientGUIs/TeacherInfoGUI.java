package client.clientGUIs;
import java.awt.*;
import javax.swing.*;
import client.util.Client;
import util.Message;
import util.Teacher;
//import org.omg.CORBA.IMP_LIMIT;



import client.clientGUIs.LogInGUI;
//import client.clientGUIs.test2;

import java.awt.event.*;
import util.*;
import vo.*;
import client.util.*;

/**
 * @author zzy
 *
 */
public class TeacherInfoGUI extends JPanel {

	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;

   // �̶�����ͼƬ���������JPanel������ͼƬ������������
    protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

    Teacher tea;
	Client client;

//	 ��ʦ������Ϣ
    JLabel title2 = new JLabel("| ��ʦ������Ϣȷ��");
	JLabel lName = new JLabel("������");
	JLabel lSex = new JLabel("�Ա�");
	JLabel lID = new JLabel("�̹��ţ�");
	JLabel lTitle = new JLabel("ְ�ƣ�");
	JLabel lDeposit = new JLabel("��");
	JLabel lBorrow = new JLabel("�ѽ�����Ŀ��");
	JButton button = new JButton("ȷ���޸�");
	JTextField nameinput = new JTextField(15);
	JTextField titleinput = new JTextField(15);
	JTextField IDinput = new JTextField(15);
	JTextField depositinput = new JTextField(15);
	JTextField borrowinput = new JTextField(15);
	JComboBox comboBox = new JComboBox();

	String tmp; // ���ڻ�ȡ�Ա�
	Message msg = new Message();
//	���ڴ�ӡ������Ϣ�ĺ���	
	public void display(Teacher user) {
		nameinput.setText(user.getRealName());
//		sexinput.setText(user.getSex());
		titleinput.setText(user.getTitle());
		IDinput.setText(user.getId() + "");
		depositinput.setText("" + user.getDeposit());
		borrowinput.setText(user.getBorrowNum() + "");
		comboBox.setSelectedItem(user.getSex());
	}
	

	
	
	public TeacherInfoGUI(Teacher user,Image image) {
		this.image = image;
		
		client = new Client();
//		�������
		tea = user;
		comboBox.addItem("��");
		comboBox.addItem("Ů");
		comboBox.setSize(300, 300);
		msg.setMessageType("UPDATE_USER_INFO");
		msg.setUserType(1);
		display(tea);
		
		titleinput.setEditable(false);
		IDinput.setEditable(false);
		depositinput.setEditable(false);
		borrowinput.setEditable(false);
		
//		���������ʽ�ʹ�С
		Font megaFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,60);
		Font subFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,70);
  		Font font = new Font("ƻ�� ����",Font.CENTER_BASELINE,30);
	    Font font1 = new Font("ƻ�� ����",Font.BOLD,15);
	    Font font2 = new Font("ƻ�� ����",Font.CENTER_BASELINE,15);
	    //title.setFont(megaFont);
	    title2.setFont(subFont);
		lName.setFont(font);
		lSex.setFont(font);
		lID.setFont(font);
		lTitle.setFont(font);
		lDeposit.setFont(font);
		lBorrow.setFont(font);
			
//		����λ��
//		title.setSize(1050, 600);
//		title.setLocation(70,-120);
//		this.add(title);
		
		title2.setSize(1000, 300);
		title2.setLocation(230, -60);
		this.add(title2);
		   	
		
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
		lID.setLocation(310, 310);
		this.add(lID);
		
		IDinput.setSize(170,40);
		IDinput.setLocation(470,310);
		this.add(IDinput); 
		
		
		lTitle.setSize(150,40);
		lTitle.setLocation(330, 370);
		this.add(lTitle);
		
		titleinput.setSize(170,40);
		titleinput.setLocation(470,370);
		this.add(titleinput);
		
		
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

//		button��ť��Ӧ
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// TODO Auto-generated method stub
				
				tmp=(String)comboBox.getSelectedItem();
				tea.setSex(tmp);
				tea.setRealName(nameinput.getText());
				msg.setData(tea);
				Message serverResponse = client.sendRequestToServer(msg);//����msg
				if(serverResponse.isLastOperState())
					JOptionPane.showMessageDialog(null, "������Ϣ���³ɹ���");
				else 
					JOptionPane.showMessageDialog(null, "δ֪������Ϣ���´����������ݿ⣡");
			}
		});
	}

}