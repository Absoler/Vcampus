package client.clientGUIs;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import util.*;
import vo.*;
	
public class RegisterGUI extends JPanel {
	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;
	
	//private static ImageIcon[] imageIcon = new ImageIcon[1];//ͼ��ʹ��

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
	//JRadioButton rbAdm = new JRadioButton("����Ա");
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
	JLabel title2 = new JLabel("�û�ע��");
	JLabel lName = new JLabel(" �ǳ�:");
	JLabel lPassword = new JLabel(" ����:");
	JLabel admitpassword = new JLabel("ȷ������:");
	JButton bOK = new JButton("ȷ��");
	JButton bCancel = new JButton("���");
	final JTextField nameinput = new JTextField(15);
	final JPasswordField passwordinput = new JPasswordField(15);
	final JPasswordField admitpasswordinput = new JPasswordField(15);
	
	public RegisterGUI(Image image) {
		this.image = image;

//		����ѡ��ť�ŵ���ť����
		bg.add(rbStu);
		bg.add(rbTea);
		//bg.add(rbAdm);
		
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
		admitpassword.setFont(font);
		bOK.setFont(font1);
		bCancel.setFont(font1);
		rbStu.setFont(font2);
		rbTea.setFont(font2);
		
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
		lPassword.setLocation(330,370);
		this.add(lPassword);
		
		admitpassword.setSize(105,30);
		admitpassword.setLocation(330, 420);
		this.add(admitpassword);
		
		bOK.setSize(123,35);
		bOK.setLocation(318,500);
		this.add(bOK);
		
		bCancel.setSize(123,35);
		bCancel.setLocation(480,500);
		this.add(bCancel);
		
		rbStu.setSize(75,30);
		rbStu.setLocation(353,465);
		this.add(rbStu);
		
		rbTea.setSize(75,30);
		rbTea.setLocation(498,465);
		this.add(rbTea);
		
		nameinput.setSize(168,30);
		nameinput.setLocation(420,320);
		this.add(nameinput);
		
		passwordinput.setSize(168,30);
		passwordinput.setLocation(420,370);
		this.add(passwordinput); 
		
		admitpasswordinput.setSize(168,30);
		admitpasswordinput.setLocation(420,420);
		this.add(admitpasswordinput);
		
		this.setOpaque(false);
		this.setLayout(null);
		
//  	��ɫ
  		final ImageIcon whitebox = new ImageIcon("src/imgs\\white.png");
  		final JLabel loginbox = new JLabel(whitebox);
  		loginbox.setSize(320,250);
  		loginbox.setLocation(300,300);
  		this.add(loginbox);
		
//		OK��ť��Ӧ
//		д��StartPageGUI��
		
//		Cancel��ť��Ӧ��������û���������
		bCancel.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent Event) {			
				nameinput.setText("");
				passwordinput.setText("");
				admitpasswordinput.setText("");
			}
			
		});
	}
}