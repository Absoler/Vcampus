package client.clientGUIs;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;
import util.*;
import vo.*;
import client.util.*;

public class LogInGUI extends JPanel {

	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;

   // �̶�����ͼƬ���������JPanel������ͼƬ������������
    protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
      
//  �ײ�ҳ��
	JPanel subPanel = new JPanel();
	
//	�Ƿ��½�ɹ�
	boolean isSuccess = false;
	
//	���õ�ѡ��ť
	JRadioButton rbStu = new JRadioButton("ѧ��");
	JRadioButton rbTea = new JRadioButton("��ʦ");
	JRadioButton rbAdm = new JRadioButton("����Ա");
	ButtonGroup bg = new ButtonGroup();
	
//	����Ϊ��ʦ����ѧ��
	boolean isStu = false;
	boolean isTea = false;
	boolean isAdm = false;
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public boolean isStu() {
		return isStu;
	}

	public void setStu(boolean isStu) {
		this.isStu = isStu;
	}

	public boolean isTea() {
		return isTea;
	}

	public void setTea(boolean isTea) {
		this.isTea = isTea;
	}

	JLabel title = new JLabel("Vcampus У԰��Ϣ����ϵͳ");
	JLabel title2 = new JLabel("�û���½");
	JLabel lName = new JLabel("�û���:");
	JLabel lPassword = new JLabel(" ����:");
	JButton bOK = new JButton("��½");
	JButton bCancel = new JButton("���");
	//JButton bSwitch = new JButton("��û���˺ţ�����˴�ע��");
	final JTextField nameinput = new JTextField(15);
	final JPasswordField passwordinput = new JPasswordField(15);
	
	public LogInGUI(Image image) {
		this.image = image;

//		����ѡ��ť�ŵ���ť����
		bg.add(rbStu);
		bg.add(rbTea);
		bg.add(rbAdm);
		
//		���������ʽ�ʹ�С
		Font megaFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,60);
		Font subFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,40);
  		Font font = new Font("ƻ�� ����",Font.CENTER_BASELINE,20);
	    Font font1 = new Font("ƻ�� ����",Font.BOLD,15);
	    Font font2 = new Font("ƻ�� ����",Font.CENTER_BASELINE,15);
	    title.setFont(megaFont);
	    title2.setFont(subFont);
		lName.setFont(font);
		lPassword.setFont(font);
		bOK.setFont(font1);
		bCancel.setFont(font1);
		//bSwitch.setFont(font1);
//		b.setFont(font1);
		rbStu.setFont(font2);
		rbTea.setFont(font2);
		rbAdm.setFont(font2);
		
//		����λ��
		title.setSize(1050, 600);
		title.setLocation(70,-120);
		this.add(title);
		
		title2.setSize(1000, 300);
		title2.setLocation(381, 110);
		this.add(title2);
		   	
		lName.setSize(75,30);
 		lName.setLocation(330, 320);
 		this.add(lName);
		
		lPassword.setSize(75,30);
		lPassword.setLocation(330,380);
		this.add(lPassword);
		
		bOK.setSize(123,35);
		bOK.setLocation(318,470);
		this.add(bOK);
		
		bCancel.setSize(123,35);
		bCancel.setLocation(480,470);
		this.add(bCancel);
		
//		bSwitch.setSize(300,30);
//		bSwitch.setLocation(550, 650);
//		this.add(bSwitch);
		
		rbStu.setSize(75,30);
		rbStu.setLocation(333,425);
		this.add(rbStu);
		
		rbTea.setSize(75,30);
		rbTea.setLocation(420,425);
		this.add(rbTea);
		
		rbAdm.setSize(105,30);
		rbAdm.setLocation(508,425);
		this.add(rbAdm);
		
		nameinput.setSize(188,30);
		nameinput.setLocation(400,320);
		this.add(nameinput);
		
		passwordinput.setSize(188,30);
		passwordinput.setLocation(400,380);
		this.add(passwordinput); 
		
//  	��ɫ
  		final ImageIcon whitebox = new ImageIcon("src/imgs\\white.png");
  		final JLabel loginbox = new JLabel(whitebox);
  		loginbox.setSize(320,220);
  		loginbox.setLocation(300,300);
  		this.add(loginbox);
		
		this.setOpaque(false);
		this.setLayout(null);
		
//		OK��ť��Ӧ
//		д��StartPageGUI��
		
//		Cancel��ť��Ӧ��������û���������
		bCancel.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent Event) {			
				nameinput.setText("");
				passwordinput.setText("");
			}
			
		});
	}
}