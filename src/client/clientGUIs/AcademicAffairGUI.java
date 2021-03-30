package client.clientGUIs;

import java.awt.*;

import javax.swing.*;

import util.Student;

public class AcademicAffairGUI extends JPanel{

	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;

	// �̶�����ͼƬ���������JPanel������ͼƬ������������
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	Student stu;

	JLabel title = new JLabel("| ѧ���ɼ���Ϣȷ��");
	JLabel lGPA ;
	JLabel lSRTP = new JLabel("SRTPѧ�֣�");
	JLabel lPunish = new JLabel("���������");

	JTextField SRTPoutput = new JTextField(15);
	JTextField GPAoutput = new JTextField(15);
	JTextField Punishoutput = new JTextField(15);

	
	public AcademicAffairGUI(Student user,Image image) {
		this.image = image;

		stu = user;
		
		SRTPoutput.setText(stu.getSRTP() + "");
		GPAoutput.setText(stu.getGPA() + "");
		Punishoutput.setText(stu.getPunishment() + "");
		
//		����Label��ʽ����try
		lGPA = new JLabel("���㣺", SwingConstants.RIGHT);
//		lGPA.setHorizontalAlignment(SwingConstants.RIGHT);
		
//		���ñ�����ɫ
		Color bgColor = new Color(255, 255, 255);
		this.setBackground(bgColor);
		
//		lGPA.setOpaque(true);  	 
//		lGPA.setBackground(Color.GREEN);
//		����JLabelͼ�ꡪ��try����OK
//		��������ӦlID��С 
//		icon.setImage(icon.getImage().getScaledInstance((icon.getIconWidth()/4), 
//				(icon.getIconHeight()/4), Image.SCALE_DEFAULT)); 
//	    System.out.println(icon.getIconWidth() + "x" + icon.getIconHeight());
//	    lID.setIcon(icon);//����ͼƬ
//	    lID.setBounds(10, 10, 190, 330);//����lID�Ĵ�С
		

		
		SRTPoutput.setEditable(false);
		GPAoutput.setEditable(false);
		Punishoutput.setEditable(false);
		
//		������и���
//		add(lID, cons, 0, 0, 1, 1); // ��һ��
//		add(IDinput, cons, 1, 0, 1, 1); 
//		JSeparator sep = new JSeparator(SwingConstants.CENTER);
//		sep.setPreferredSize(new Dimension(this.getWidth()/3, 2));
//		sep.setBackground(new Color(153,153,153));
//		this.add(sep, cons, 0, 2, 4, 1);

////		����������ɫ�ʹ�С
//		for(int i = 0; i < this.getComponentCount(); i++){
//			Font font = new Font("ƻ�� ����", Font.CENTER_BASELINE, 15);
//			this.getComponent(i).setFont(font);
//		}
//		this.setBorder(BorderFactory.createTitledBorder("�ɼ���ѯ"));

		Font subFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,70);
		Font font = new Font("ƻ�� ����",Font.CENTER_BASELINE,30);
		title.setFont(subFont);
		lGPA.setFont(font);
		lSRTP.setFont(font);
		lPunish.setFont(font);

		title.setSize(1000, 300);
		title.setLocation(230, -60);
		this.add(title);

		lGPA.setSize(150,40);
		lGPA.setLocation(310,240);
		this.add(lGPA);

		GPAoutput.setSize(170,40);
		GPAoutput.setLocation(470,240);
		this.add(GPAoutput);

		lSRTP.setSize(200,40);
		lSRTP.setLocation(280, 370);
		this.add(lSRTP);

		SRTPoutput.setSize(170,40);
		SRTPoutput.setLocation(470,370);
		this.add(SRTPoutput);

		lPunish.setSize(170,40);
		lPunish.setLocation(310,500);
		this.add(lPunish);

		Punishoutput.setSize(170,40);
		Punishoutput.setLocation(470,500);
		this.add(Punishoutput);

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

	}
		
}


