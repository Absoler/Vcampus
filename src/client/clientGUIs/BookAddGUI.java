package client.clientGUIs;

import javax.swing.*;


import client.util.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.*;
import vo.*;

public class BookAddGUI extends JPanel{
	
	Client client;
	Admin admin;
	Book book=new Book();
	
	JButton bOK = new JButton("确定");
	JButton bCancel = new JButton("取消");
	JLabel lBookName = new JLabel("书籍名称：");
	JLabel lAuthor = new JLabel("作者：");
	JLabel lPublisher = new JLabel("出版社：");
	JLabel lStockNum = new JLabel("库存量：");
	
	JTextField tBookName = new JTextField(10);
	JTextField tAuthor = new JTextField(10);
	JTextField tPublisher = new JTextField(10);
	JTextField tStockNum = new JTextField(10);
	
	public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h){
		constraints.gridx=x;
		constraints.gridy=y;
		constraints.gridwidth=w;
		constraints.gridheight=h;
		add(c,constraints);
	}
	
	public BookAddGUI() {
		client = new Client();
//		admin = a;
		

//		创建网格组布局方式对象
		GridBagLayout lay = new GridBagLayout();
		setLayout(lay);
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.NONE;
//		cons.anchor = GridBagConstraints.EAST;
		cons.weightx = 3;
		cons.weighty = 4;
		
		add(lBookName, cons, 0, 0, 1, 1);
		add(tBookName, cons, 1, 0, 1, 1);
		add(lAuthor, cons, 2, 0, 1, 1);
		add(tAuthor, cons, 3, 0, 1, 1);
		add(lPublisher, cons, 0, 1, 1, 1);
		add(tPublisher, cons, 1, 1, 1, 1);
		add(lStockNum, cons, 2, 1, 1, 1);
		add(tStockNum, cons, 3, 1, 1, 1);
		add(bOK, cons, 1, 5, 1, 1);
		add(bCancel, cons, 2, 5, 1, 1);
		
		
//		OK响应——通信
		bOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bookNametext = tBookName.getText();
				String authortext = tAuthor.getText();
				String publishertext = tPublisher.getText();
				String stockNumtext = tStockNum.getText();
				boolean flag=true;
				
//				异常事件处理，弹出对话框
				if(tBookName.equals("")) {
					JOptionPane.showMessageDialog(null, "书名不得为空，请重新输入。");
				}else if(authortext.equals("")) {
					JOptionPane.showMessageDialog(null, "作者不得为空，请重新输入。");
				}else if(publishertext.equals("")) {
					JOptionPane.showMessageDialog(null, "出版社不得为空，请重新输入。");
				}else if(stockNumtext.equals("")) {
					JOptionPane.showMessageDialog(null, "库存量不得为空，请重新输入。");
				}
				
				for(int t=0;t<Integer.parseInt(stockNumtext);t++) {
					Message mes = new Message();
					mes.setMessageType(Constants.add_book);
					book.setBookName(bookNametext);
					book.setBookAuthor(authortext);
					book.setBorrowed(false);
					book.setUserId(0);
					book.setUserType(-1);
					book.setPublisher(publishertext);
					mes.setData(book);
					Message serverResponse = client.sendRequestToServer(mes);
					if(!serverResponse.isLastOperState())
						flag=false;
				}
//					成功与否
					if(flag)
						JOptionPane.showMessageDialog(null, "添加成功！");
					else {
						JOptionPane.showMessageDialog(null, "不知名的添加错误！");
					}
			}
		});
		
//		取消响应——清除所有信息
		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tBookName.setText("");
				tAuthor.setText("");
				tPublisher.setText("");
//				tStockNum.setText("");
			}
		});
		
		
//		 设置窗口大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		this.setSize(width, height);
		
//		设置背景颜色
		Color bgColor = new Color(255, 255, 255);
		this.setBackground(bgColor);
		
//		设置字体颜色和大小
		for(int i = 0; i < this.getComponentCount(); i++){ 
			Font font = new Font("苹方 常规", Font.CENTER_BASELINE,15); 
			this.getComponent(i).setFont(font);
		}
		this.setBorder(BorderFactory.createTitledBorder("添加图书"));
		
	}

}
