package client.clientGUIs;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JLabel_test{
	private static ImageIcon[] imageIcon = new ImageIcon[1];
	
//	�Ƿ��½�ɹ�
	boolean isSuccess = false;
	
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
	
	public static void main(String args[])
	{
		final JFrame frame = new JFrame();
		frame.setSize(1440,900); 
        frame.setLocation(0,0); 
      //����ͼƬ��·���������·�����߾���·��������ͼƬ����"java��Ŀ��"���ļ��£�
      		String path = "src/imgs/siBG2.png";
      		// ����ͼƬ
      		final ImageIcon background = new ImageIcon(path);
      		// �ѱ���ͼƬ��ʾ��һ����ǩ����
      		final JLabel label = new JLabel(background);
      		// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
      		label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
      		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
      		JPanel imagePanel = (JPanel) frame.getContentPane();
      		imagePanel.setOpaque(false);
      		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����(min_value)
      		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
      		
      		
      		//Ϊ�������Ӵ��ڼ�����������Ļ�ı��С��
      		frame.addComponentListener(new ComponentAdapter(){		
    			@Override
    			public void componentResized(ComponentEvent e)
    			{
    				//ɾ��JLabel
    				frame.remove(label);
    				//�������
    				imageIcon[0] = new ImageIcon(new ImageIcon("D:\\eclipse\\workingPath\\javaProjectDesign\\imgs\\siBG2.png").getImage().getScaledInstance(frame.getSize().width, frame.getSize().height, Image.SCALE_DEFAULT));
    				label.setIcon(imageIcon[0]);
    				label.setHorizontalAlignment(SwingConstants.CENTER);
    				frame.add(label);
    			}
    		});
      		
      		// ����ͼƬ
      		final ImageIcon whitebox = new ImageIcon("D:\\eclipse\\workingPath\\javaProjectDesign\\imgs\\white.png");
      		// �ѱ���ͼƬ��ʾ��һ����ǩ����
      		final JLabel loginbox = new JLabel(whitebox);
      		loginbox.setSize(450,350);
      		loginbox.setLocation(500,260);
      		JPanel box = (JPanel) frame.getContentPane();
      		//loginbox.setBounds(100, 10, 4, 4);
      		box.setOpaque(false);
      		box.add(loginbox);
      		//frame.add(box);
      		
      		//JPanel jp = (JPanel)frame.getContentPane();
    		//jp.setOpaque(false);
    		JPanel panel = new JPanel();
    		panel.setOpaque(false);
    		panel.setLayout(null);
      		
      		JLabel lName = new JLabel("�û���");
      		JLabel lPassword = new JLabel("����");
      		JButton bOK = new JButton("ȷ��");
      		JButton bCancel = new JButton("���");
      		final JTextField nameinput = new JTextField(15);
      		final JPasswordField passwordinput = new JPasswordField(15);
      		
      		JRadioButton rbStu = new JRadioButton("ѧ��");
      		JRadioButton rbTea = new JRadioButton("��ʦ");
      		JRadioButton rbAdm = new JRadioButton("����Ա");
      		ButtonGroup group = new ButtonGroup();
//    		����ѡ��ť�ŵ���ť����
    		group.add(rbStu);
    		group.add(rbTea);
    		group.add(rbAdm);
    		
      		Font font = new Font("ƻ�� ����",Font.CENTER_BASELINE,12);//���������ʽ�ʹ�С
    	    Font font1 = new Font("ƻ�� ����",Font.BOLD,12);//���������ʽ�ʹ�С
    		lName.setFont(font);
    		lPassword.setFont(font1);
    		bOK.setFont(font);
    		bCancel.setFont(font1);
    		
    		JButton b = new JButton("��½");//��½��ť
    		b.setSize(400, 50);
    		b.setLocation(525,550);//725��610
    		panel.add(b);
    		   	
    		lName.setSize(100,40);
     		lName.setLocation(525, 300);
     		panel.add(lName);
    		
    		lPassword.setSize(100,40);
    		lPassword.setLocation(525,380);
    		panel.add(lPassword);
    		
    		bOK.setSize(150,40);
    		bOK.setLocation(550,500);
    		panel.add(bOK);
    		
    		bCancel.setSize(150,40);
    		bCancel.setLocation(750,500);
    		panel.add(bCancel);
    		
    		rbStu.setSize(100,40);
    		rbStu.setLocation(550,430);
    		panel.add(rbStu);
    		
    		rbTea.setSize(100,40);
    		rbTea.setLocation(680,430);
    		panel.add(rbTea);
    		
    		rbAdm.setSize(100,40);
    		rbAdm.setLocation(810,430);
    		panel.add(rbAdm);
    		
    		nameinput.setSize(300,40);
    		nameinput.setLocation(625,300);
    		panel.add(nameinput);
    		
    		passwordinput.setSize(300,40);
    		passwordinput.setLocation(625,380);
    		panel.add(passwordinput);
    		
    		
//    		JPanel p1 = new JPanel();
//    		p1.setOpaque(false);
//    		p1.setLayout(null);
//    		lName.setSize(100,40);
//    		lName.setLocation(525, 450);
//    		p1.add(lName);
    		
    		frame.add(panel);
    		//frame.add(p1,BorderLayout.CENTER);
    		
    		//���ÿɼ�
      		frame.setVisible(true);
    		
//    		Cancel��ť��Ӧ��������û���������
    		bCancel.addActionListener(new ActionListener(){
    			public void actionPerformed(java.awt.event.ActionEvent Event) {			
    				nameinput.setText("");
    				passwordinput.setText("");
    			}
    			
    		});
	}
}