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

   // 固定背景图片，允许这个JPanel可以在图片上添加其他组件
    protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
      
//  底层页面
	JPanel subPanel = new JPanel();
	
//	是否登陆成功
	boolean isSuccess = false;
	
//	设置单选按钮
	JRadioButton rbStu = new JRadioButton("学生");
	JRadioButton rbTea = new JRadioButton("老师");
	JRadioButton rbAdm = new JRadioButton("管理员");
	ButtonGroup bg = new ButtonGroup();
	
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

	JLabel title = new JLabel("Vcampus 校园信息管理系统");
	JLabel title2 = new JLabel("用户登陆");
	JLabel lName = new JLabel("用户名:");
	JLabel lPassword = new JLabel(" 密码:");
	JButton bOK = new JButton("登陆");
	JButton bCancel = new JButton("清空");
	//JButton bSwitch = new JButton("还没有账号？点击此处注册");
	final JTextField nameinput = new JTextField(15);
	final JPasswordField passwordinput = new JPasswordField(15);
	
	public LogInGUI(Image image) {
		this.image = image;

//		将单选按钮放到按钮组中
		bg.add(rbStu);
		bg.add(rbTea);
		bg.add(rbAdm);
		
//		设置字体格式和大小
		Font megaFont = new Font("苹方 常规",Font.CENTER_BASELINE,60);
		Font subFont = new Font("苹方 常规",Font.CENTER_BASELINE,40);
  		Font font = new Font("苹方 常规",Font.CENTER_BASELINE,20);
	    Font font1 = new Font("苹方 常规",Font.BOLD,15);
	    Font font2 = new Font("苹方 常规",Font.CENTER_BASELINE,15);
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
		
//		设置位置
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
		
//  	白色
  		final ImageIcon whitebox = new ImageIcon("src/imgs\\white.png");
  		final JLabel loginbox = new JLabel(whitebox);
  		loginbox.setSize(320,220);
  		loginbox.setLocation(300,300);
  		this.add(loginbox);
		
		this.setOpaque(false);
		this.setLayout(null);
		
//		OK按钮响应
//		写在StartPageGUI里
		
//		Cancel按钮响应——清空用户名和密码
		bCancel.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent Event) {			
				nameinput.setText("");
				passwordinput.setText("");
			}
			
		});
	}
}