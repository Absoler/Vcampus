//package client.clientGUIs;
//
//import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.event.TableModelEvent;
//import javax.swing.event.TableModelListener;
//import javax.swing.table.DefaultTableModel;
//import client.clientGUIs.MainGUI;
//import client.util.Client;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//import util.*;
//import vo.*;
//
//public class LibraryGUI extends JPanel implements TableModelListener{
////	 不能设置框架
////	JPanel的所有操作，直接用this
//	Client client=new Client();
//	User user;
//	ArrayList<BookInfo> books = new ArrayList<BookInfo>();
//	Message msg=new Message();
//	Object[][]s = new Object[100][100];
//	
//	String []n = {"现存图书"};
//	JLabel book_name = new JLabel("书名："); 
//	JLabel book_author = new JLabel("作者：");
//	JLabel book_press = new JLabel("出版社：");
//	JLabel book_stocknum = new JLabel("库存量：");
//	JLabel book_num = new JLabel("已借阅数量：");
//	
//	JButton bBorrow = new JButton("借阅");
//	JButton button_search=new JButton("搜索");
//	JTextField book_search=new JTextField(25);
//	JSeparator separator = new JSeparator(SwingConstants.CENTER);
//	JComboBox combo1 = null;
//	DefaultTableModel t = null;
//	int selected;
//	
//	public void add(Component c, GridBagConstraints cons, int x, int y, int w, int h) {
//		cons.gridx = x;
//		cons.gridy = y;
//		cons.gridwidth = w;
//		cons.gridheight = h;
//		add(c, cons);
//	}
//		
//	public LibraryGUI(User u, ArrayList<BookInfo> b) {
////		接收用户信息——已借阅数量
//		user = u;
//		books = b;
//		msg.setMessageType("LIBRARY_QUERY");
//		Message serverResponse = client.sendRequestToServer(msg);
//		books=(ArrayList<BookInfo>)serverResponse.getData();
//		book_num.setText(book_num.getText() + u.getBorrowNum());
//		JPanel p_search=new JPanel();
//		p_search.add(book_search);
//		p_search.add(button_search);
//		separator.setBackground(Color.blue);
//		separator.setPreferredSize(new Dimension(this.getWidth(),20));
//		this.add(separator);
//		GridBagLayout lay = new GridBagLayout();
//		this.setLayout(lay);
//		GridBagConstraints cons = new GridBagConstraints();
//		cons.weightx = 3;
//		cons.weighty = 4;
//		cons.fill=GridBagConstraints.NONE;
//		cons.anchor=GridBagConstraints.CENTER;
//		GridBagConstraints cons1 = new GridBagConstraints();
//		cons1.weightx = 1;
//		cons1.weighty = 1;
//		cons1.fill=GridBagConstraints.NONE;
//		cons1.anchor=GridBagConstraints.WEST;
//		
////		设置窗口大小
//		Toolkit kit = Toolkit.getDefaultToolkit();
//		Dimension screenSize = kit.getScreenSize();
//		int width = screenSize.width;
//		int height = screenSize.height;
//		
////		test
//		int num = b.size();
//		System.out.println(num);
//		for(int i = 0; i < num; i++) {
//			s[i][0] = books.get(i).getBookName();
//		}
////		设置下拉框
//		String []n= {"现存图书"};
//		t = new DefaultTableModel(s,n) {			
//			public boolean isCellEditable(int row, int column)
//			{
//				return false;
//			}
//				
//		};
//		t.addTableModelListener(this);
//		
////		设置表格
//		JTable table = new JTable(t);
//		
//		//设置消息响应函数
//	    table.setCellSelectionEnabled(true);
//	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
//	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	     
//	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
//	    	public void valueChanged(ListSelectionEvent e) {
//			    String selectedData = null;
//			    int[] selectedRow = table.getSelectedRows();
//			    int[] selectedColumns = table.getSelectedColumns();
//			    String stocknum = books.get(selectedRow[0]).getStockNum() + "";
//			    selected = selectedRow[0];
//			    
//			    book_name.setText("书名："+(String)books.get(selectedRow[0]).getBookName());
//			    book_author.setText("作者："+(String)books.get(selectedRow[0]).getAuthor());
//			    book_press.setText("出版社："+(String)books.get(selectedRow[0]).getPublisher());
//			    book_stocknum.setText("库存量：" + stocknum);
//			}
//	    });
//	    
////		借阅button消息响应
//		bBorrow.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent Event) {
//				// TODO Auto-generated method stub
////				判断是否符合借书条件
//				if(user.getBorrowNum() >= 3) {
//					JOptionPane.showMessageDialog(null, "每人借书不得超过三本。");
//				}
//				else {
//					Message mes = new Message();
//					mes.setMessageType(Constants.Borrow);
//					Borrow borrow = new Borrow();
//					borrow.setBookName(books.get(selected).getBookName());
//					borrow.setId(user.getId());
//					borrow.setUserType(user.getUserType());
//					
//					mes.setData(borrow);
//					Message serverResponse = client.sendRequestToServer(mes);
//					if (!serverResponse.isLastOperState()) {
//						JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
//					}
//					else {
//						JOptionPane.showMessageDialog(null, "借书成功！");
//					}
//				}
//			}
//		});
//		
//		//第二个部分设置图片
//		JLabel image=new JLabel ();
//		ImageIcon icon_t = new ImageIcon("src\\imgs\\ThreeBody.jpg");//三体的图片
//		ImageIcon icon_p = new ImageIcon("src\\imgs\\ThreeBody.jpg");//战争与和平的图片
//		image.setIcon(icon_t);
//		icon_t.setImage(icon_t.getImage().getScaledInstance(width/5,height/3,Image.SCALE_DEFAULT ));
//		//image.setBounds(10,10,30,40);
//		image.setBorder(BorderFactory.createTitledBorder("图书照片："));
//		 button_search.addActionListener(new ActionListener() {
//		    	public void actionPerformed(ActionEvent e)
//		    	{
//		    		String name=book_search.getText();
//		    		for(int i=0;i<s.length;i++)
//		    		{
//		    			if(s[i][0].equals(name))
//		    			{
//		    				book_name.setText("书名:"+(String)s[i][0]);
//		    		        book_author.setText("作者:"+(String)s[i][0]);
//		    		        book_press.setText("出版社:"+(String)s[i][0]);
//		    		        image.setIcon(icon_t);
//		    		       table.setRowSelectionInterval(i,i);
//		    		        break;
//		    			}
//		    			
//		    				
//		    		}//end for
//		    		//if(up==false)
//		    		//System.out.println(s[2][0]+" "+name);	
//		    	}
//		    });
////		加入组件
//		table.setPreferredScrollableViewportSize(new Dimension(300, 250));
//		JScrollPane sp = new JScrollPane(table);
//		add(book_num,cons1,0,3,1,1);
//		add(sp,cons,0,1,1,1);
//		add(p_search,cons,0,0,1,1);
//		add(bBorrow,cons,0,4,1,1);
//		add(image,cons,1,0,1,1);
//		add(book_name,cons,1,1,1,1);
////			book_name.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//		add(book_author,cons,1,2,1,1);
////			book_author.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//		add(book_press,cons,1,3,1,1);
////			book_press.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//		add(book_stocknum,cons,1,4,1,1);
////			book_stocknum.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//		
////		设置字体颜色和大小
//		for(int i = 0; i < this.getComponentCount(); i++){ 
//			Font font = new Font("楷体 常规", Font.CENTER_BASELINE, 15); 
//			this.getComponent(i).setFont(font);
//		}
//		this.setBorder(BorderFactory.createTitledBorder("图书馆服务"));
////		设置背景颜色
//		Color bgColor = new Color(255, 255, 255);
//		this.setBackground(bgColor);
//		
//	}//end constructor function
//		
//	//定义消息响应函数
//	public void tableChanged(TableModelEvent e)
//	{
//		int row = e.getFirstRow();//获取所选数据的行数
//		book_name.setText((String)s[row][1]);
//		System.out.print("111");
//		//String bookName=row.toString();
//	}
//}//end class
package client.clientGUIs;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import client.clientGUIs.MainGUI;
import client.util.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import util.*;
import vo.*;

public class LibraryGUI extends JPanel implements TableModelListener{
//	 不能设置框架
//	JPanel的所有操作，直接用this
	Client client=new Client();
	User user;
	ArrayList<BookInfo> books = new ArrayList<BookInfo>();
	Message msg=new Message();
	Object[][]s = new Object[100][100];
	
	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;

   // 固定背景图片，允许这个JPanel可以在图片上添加其他组件
    protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
    
    JLabel title2 = new JLabel("| 图书馆借书服务");
	
	String []n = {"现存图书"};
	JLabel book_name = new JLabel("书名："); 
	JLabel book_author = new JLabel("作者：");
	JLabel book_press = new JLabel("出版社：");
	JLabel book_stocknum = new JLabel("库存量：");
	JLabel book_num = new JLabel("已借阅数量：");
	
	JButton bBorrow = new JButton("借阅");
	JButton button_search=new JButton("搜索");
	JTextField book_search=new JTextField(25);
	JSeparator separator = new JSeparator(SwingConstants.CENTER);
	//JComboBox combo1 = null;
	DefaultTableModel t = null;
	int selected;
	
//	public void add(Component c, GridBagConstraints cons, int x, int y, int w, int h) {
//		cons.gridx = x;
//		cons.gridy = y;
//		cons.gridwidth = w;
//		cons.gridheight = h;
//		add(c, cons);
//	}
		
	public LibraryGUI(User u, ArrayList<BookInfo> b,Image image) {
		this.image = image;
//		接收用户信息——已借阅数量
		user = u;
		books = b;
		msg.setMessageType("LIBRARY_QUERY");
		Message serverResponse = client.sendRequestToServer(msg);
		books=(ArrayList<BookInfo>)serverResponse.getData();
		book_num.setText(book_num.getText() + u.getBorrowNum());
		
//		设置字体格式和大小
		Font megaFont = new Font("苹方 常规",Font.CENTER_BASELINE,60);
		Font subFont = new Font("苹方 常规",Font.CENTER_BASELINE,70);
  		Font font = new Font("苹方 常规",Font.CENTER_BASELINE,30);
	    Font font1 = new Font("苹方 常规",Font.BOLD,15);
	    Font font2 = new Font("苹方 常规",Font.CENTER_BASELINE,15);
	    //title.setFont(megaFont);
	    title2.setFont(subFont);
		book_name.setFont(font);
		book_author.setFont(font);
		book_press.setFont(font);
		book_stocknum.setFont(font);
		book_num.setFont(font);
		bBorrow.setFont(font1);
		button_search.setFont(font1);
		//book_search.setFont(font1);

		
		//JPanel p_search=new JPanel();
		this.add(book_search);
		this.add(button_search);
		
//		separator.setBackground(Color.blue);
//		separator.setPreferredSize(new Dimension(this.getWidth(),20));
//		this.add(separator);
//		GridBagLayout lay = new GridBagLayout();
//		this.setLayout(lay);
//		GridBagConstraints cons = new GridBagConstraints();
//		cons.weightx = 3;
//		cons.weighty = 4;
//		cons.fill=GridBagConstraints.NONE;
//		cons.anchor=GridBagConstraints.CENTER;
//		GridBagConstraints cons1 = new GridBagConstraints();
//		cons1.weightx = 1;
//		cons1.weighty = 1;
//		cons1.fill=GridBagConstraints.NONE;
//		cons1.anchor=GridBagConstraints.WEST;
//		
////		设置窗口大小
//		Toolkit kit = Toolkit.getDefaultToolkit();
//		Dimension screenSize = kit.getScreenSize();
//		int width = screenSize.width;
//		int height = screenSize.height;
		
//		test
		int num = b.size();
		System.out.println(num);
		for(int i = 0; i < num; i++) {
			s[i][0] = books.get(i).getBookName();
		}
//		设置下拉框
		String []n= {"现存图书"};
		t = new DefaultTableModel(s,n) {			
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
				
		};
		t.addTableModelListener(this);
		
//		设置表格
		JTable table = new JTable(t);
		JTableHeader title = table.getTableHeader();
		title.setFont(new Font("楷体",Font.PLAIN,14));
		table.setPreferredScrollableViewportSize(new Dimension(300, 250));
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(120, 270, 350, 260);
		bBorrow.setBounds(410, 550, 100, 40);
		book_search.setBounds(120, 210, 250, 40);//input
		button_search.setBounds(370, 210, 100, 40);//button
		this.add(sp);
		this.add(bBorrow);
		this.add(button_search);
		
		title2.setSize(1000, 300);
		title2.setLocation(230, -60);
		this.add(title2);
		
		book_name.setSize(400,50);
		book_name.setLocation(530,220);
 		this.add(book_name);
 		
 		book_author.setSize(400,50);
 		book_author.setLocation(530,280);
		this.add(book_author);
		
		
		book_press.setSize(400,50);
		book_press.setLocation(530,340);
		this.add(book_press);
		
		book_stocknum.setSize(400,50);
		book_stocknum.setLocation(530,400);
		this.add(book_stocknum);
		
		
		book_num.setSize(400,50);
		book_num.setLocation(530, 460);
		this.add(book_num);
		
		
		//设置消息响应函数
	    table.setCellSelectionEnabled(true);
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	     
	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	    	public void valueChanged(ListSelectionEvent e) {
			    String selectedData = null;
			    int[] selectedRow = table.getSelectedRows();
			    int[] selectedColumns = table.getSelectedColumns();
			    String stocknum = books.get(selectedRow[0]).getStockNum() + "";
			    selected = selectedRow[0];
			    
			    book_name.setText("书名："+(String)books.get(selectedRow[0]).getBookName());
			    book_author.setText("作者："+(String)books.get(selectedRow[0]).getAuthor());
			    book_press.setText("出版社："+(String)books.get(selectedRow[0]).getPublisher());
			    book_stocknum.setText("库存量：" + stocknum);
			}
	    });
	    
//		借阅button消息响应
		bBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent Event) {
				// TODO Auto-generated method stub
//				判断是否符合借书条件
				if(user.getBorrowNum() >= 3) {
					JOptionPane.showMessageDialog(null, "每人借书不得超过三本。");
				}
				else {
					Message mes = new Message();
					mes.setMessageType(Constants.Borrow);
					Borrow borrow = new Borrow();
					borrow.setBookName(books.get(selected).getBookName());
					borrow.setId(user.getId());
					borrow.setUserType(user.getUserType());
					
					mes.setData(borrow);
					Message serverResponse = client.sendRequestToServer(mes);
					if (!serverResponse.isLastOperState()) {
						JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
					}
					else {
						JOptionPane.showMessageDialog(null, "借书成功！");
					}
				}
			}
		});
		 button_search.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		String name=book_search.getText();
		    		for(int i=0;i<s.length;i++)
		    		{
		    			if(s[i][0].equals(name))
		    			{
		    				book_name.setText("书名:"+(String)s[i][0]);
		    		        book_author.setText("作者:"+(String)s[i][0]);
		    		        book_press.setText("出版社:"+(String)s[i][0]);
		    		       table.setRowSelectionInterval(i,i);
		    		        break;
		    			}
		    			
		    				
		    		}//end for
		    		//if(up==false)
		    		//System.out.println(s[2][0]+" "+name);	
		    	}
		    });
//	  	白色
	  		final ImageIcon whitebox = new ImageIcon("src/imgs/white-dark.png");
	  		final JLabel loginbox = new JLabel(whitebox);
	  		loginbox.setSize(760,420);
	  		loginbox.setLocation(80,180);
	  		this.add(loginbox);

//	  	校徽
	  		final ImageIcon logo = new ImageIcon("src/imgs/logo-small.png");
	  		final JLabel Llogo = new JLabel(logo);
	  		Llogo.setSize(130,130);
	  		Llogo.setLocation(60,30);
	  		this.add(Llogo);
			
			this.setOpaque(false);
			this.setLayout(null);
		 
//		//第二个部分设置图片
//		JLabel image=new JLabel ();
//		ImageIcon icon = new ImageIcon("D:\\18-JDKandEclipse\\1-Eclipse\\0-workspace\\ClientGUIs\\src\\client\\pictures\\test00.png");
//		image.setIcon(icon);
//		icon.setImage(icon.getImage().getScaledInstance(width/5,height/3,Image.SCALE_DEFAULT ));
//		//image.setBounds(10,10,30,40);
//		image.setBorder(BorderFactory.createTitledBorder("图书照片："));
		
//		加入组件
//		add(book_num,cons1,0,3,1,1);
//		add(sp,cons,0,1,1,1);
//		add(p_search,cons,0,0,1,1);
//		add(bBorrow,cons,0,4,1,1);
//		add(image,cons,1,0,1,1);
//		add(book_name,cons,1,1,1,1);
////			book_name.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//		add(book_author,cons,1,2,1,1);
////			book_author.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//		add(book_press,cons,1,3,1,1);
////			book_press.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//		add(book_stocknum,cons,1,4,1,1);
////			book_stocknum.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
////		设置字体颜色和大小
//		for(int i = 0; i < this.getComponentCount(); i++){ 
//			Font font = new Font("楷体 常规", Font.CENTER_BASELINE, 15); 
//			this.getComponent(i).setFont(font);
//		}
//		this.setBorder(BorderFactory.createTitledBorder("图书馆服务"));
////		设置背景颜色
//		Color bgColor = new Color(255, 255, 255);
//		this.setBackground(bgColor);
		
	}//end constructor function
		
	//定义消息响应函数
	public void tableChanged(TableModelEvent e)
	{
		int row = e.getFirstRow();//获取所选数据的行数
		book_name.setText((String)s[row][1]);
		System.out.print("111");
		//String bookName=row.toString();
	}
}//end class