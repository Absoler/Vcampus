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
	
//	是否登陆成功
	boolean isSuccess = false;
	
//	设置为老师还是学生
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
      //背景图片的路径。（相对路径或者绝对路径。本例图片放于"java项目名"的文件下）
      		String path = "src/imgs/siBG2.png";
      		// 背景图片
      		final ImageIcon background = new ImageIcon(path);
      		// 把背景图片显示在一个标签里面
      		final JLabel label = new JLabel(background);
      		// 把标签的大小位置设置为图片刚好填充整个面板
      		label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
      		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
      		JPanel imagePanel = (JPanel) frame.getContentPane();
      		imagePanel.setOpaque(false);
      		// 把背景图片添加到分层窗格的最底层作为背景(min_value)
      		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
      		
      		
      		//为主面板添加窗口监听器（随屏幕改变大小）
      		frame.addComponentListener(new ComponentAdapter(){		
    			@Override
    			public void componentResized(ComponentEvent e)
    			{
    				//删除JLabel
    				frame.remove(label);
    				//重新添加
    				imageIcon[0] = new ImageIcon(new ImageIcon("D:\\eclipse\\workingPath\\javaProjectDesign\\imgs\\siBG2.png").getImage().getScaledInstance(frame.getSize().width, frame.getSize().height, Image.SCALE_DEFAULT));
    				label.setIcon(imageIcon[0]);
    				label.setHorizontalAlignment(SwingConstants.CENTER);
    				frame.add(label);
    			}
    		});
      		
      		// 背景图片
      		final ImageIcon whitebox = new ImageIcon("D:\\eclipse\\workingPath\\javaProjectDesign\\imgs\\white.png");
      		// 把背景图片显示在一个标签里面
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
      		
      		JLabel lName = new JLabel("用户名");
      		JLabel lPassword = new JLabel("密码");
      		JButton bOK = new JButton("确定");
      		JButton bCancel = new JButton("清空");
      		final JTextField nameinput = new JTextField(15);
      		final JPasswordField passwordinput = new JPasswordField(15);
      		
      		JRadioButton rbStu = new JRadioButton("学生");
      		JRadioButton rbTea = new JRadioButton("老师");
      		JRadioButton rbAdm = new JRadioButton("管理员");
      		ButtonGroup group = new ButtonGroup();
//    		将单选按钮放到按钮组中
    		group.add(rbStu);
    		group.add(rbTea);
    		group.add(rbAdm);
    		
      		Font font = new Font("苹方 常规",Font.CENTER_BASELINE,12);//设置字体格式和大小
    	    Font font1 = new Font("苹方 常规",Font.BOLD,12);//设置字体格式和大小
    		lName.setFont(font);
    		lPassword.setFont(font1);
    		bOK.setFont(font);
    		bCancel.setFont(font1);
    		
    		JButton b = new JButton("登陆");//登陆按钮
    		b.setSize(400, 50);
    		b.setLocation(525,550);//725，610
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
    		
    		//设置可见
      		frame.setVisible(true);
    		
//    		Cancel按钮响应——清空用户名和密码
    		bCancel.addActionListener(new ActionListener(){
    			public void actionPerformed(java.awt.event.ActionEvent Event) {			
    				nameinput.setText("");
    				passwordinput.setText("");
    			}
    			
    		});
	}
}