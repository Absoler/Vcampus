package client.clientGUIs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import client.util.Client;
import util.Constants;
import util.Message;
import util.User;
import vo.Book;

public class BookReturnGUI extends JPanel implements TableModelListener {

	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;

   // 固定背景图片，允许这个JPanel可以在图片上添加其他组件
    protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	Client client = new Client();
	// 添加一个判断逻辑
//	ArrayList<Integer> ls = new ArrayList<Integer>();
	User u;
	// BookInfo book;
	JLabel title2 = new JLabel("| 图书馆还书服务");
	String[] n = { "已借图书" };
	Object[][] s = new Object[100][100];
	String book_name;// 书的名字
	DefaultTableModel t;
	JButton bOK = new JButton("确定归还");
	JLabel lBookName = new JLabel("书籍名称：");
	JLabel lAuthor = new JLabel("作者：");
	JLabel lBookPublish = new JLabel("出版社：");
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Book> book_return = new ArrayList<Book>();

//	public void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
//		constraints.gridx = x;
//		constraints.gridy = y;
//		constraints.gridwidth = w;
//		constraints.gridheight = h;
//		add(c, constraints);
//	}

	public BookReturnGUI(User u,Image image) {
		this.image = image;
		this.u = u;

		Message msg = new Message();
		msg.setMessageType(Constants.returnQuery);
		msg.setUserId(u.getId());
		msg.setUserType(u.getUserType());
		Message serverResponse = client.sendRequestToServer(msg);
		books = (ArrayList<Book>) serverResponse.getData();

		int num = books.size();

		for (int i = 0; i < num; i++) {
			s[i][0] = books.get(i).getBookName();
		}
//		Color bgColor = new Color(255, 255, 255);
//		this.setBackground(bgColor);
////		创建网格组布局方式对象
//		GridBagLayout lay = new GridBagLayout();
//		setLayout(lay);

//		设置字体格式和大小
		Font megaFont = new Font("苹方 常规",Font.CENTER_BASELINE,60);
		Font subFont = new Font("苹方 常规",Font.CENTER_BASELINE,70);
  		Font font = new Font("苹方 常规",Font.CENTER_BASELINE,30);
	    Font font1 = new Font("苹方 常规",Font.BOLD,15);
	    Font font2 = new Font("苹方 常规",Font.CENTER_BASELINE,15);
	    //title.setFont(megaFont);
	    title2.setFont(subFont);
		lBookName.setFont(font);
		lAuthor.setFont(font);
		lBookPublish.setFont(font);
		bOK.setFont(font1);
		
		
		t = new DefaultTableModel(s, n);//内容，表头建立表
		// t.se
		JTable table = new JTable(t);
		
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				int row = table.getSelectedRow();// 获取所选数据的行数
				// System.out.println(book_name);
				if (row < books.size()) {
					if (!book_return.isEmpty())
						book_return.clear();
					book_name = (String) s[row][0];
					String book_publish = books.get(row).getPublisher();
					String book_author = books.get(row).getBookAuthor();
					lBookName.setText("书籍名称:" + book_name);
					lBookPublish.setText("书籍出版社:" + book_publish);
					lAuthor.setText("书籍作者" + book_author);
					book_return.add(books.get(row));
				}
				// ls.add(row);
//				int flag = 0;
//				if (row < books.size()) {
//					for (int i = 0; i < ls.size(); i++) {
//						if (ls.get(i) == row)
//							flag = 1;
//					}
//					if (flag == 0) {
//						book_name = (String) s[row][0];
//						String book_publish = books.get(row).getPublisher();
//						String book_author = books.get(row).getBookAuthor();
//						lBookName.setText("书籍名称:" + book_name);
//						lBookPublish.setText("书籍出版社:" + book_publish);
//						lAuthor.setText("书籍作者" + book_author);
//						book_return.add(books.get(row));
//						ls.add(row);
//					}
//				}

			}// end valueChanged function
		});


		table.setPreferredScrollableViewportSize(new Dimension(200, 300));

//		OK响应——通信

		bOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Message mes = new Message();
				mes.setUserId(u.getId());
				mes.setUserType(u.getUserType());
				mes.setData(book_return);
				for (int i = 0; i < books.size(); i++) {
					// s[i][0] = books.get(i).getBookName();
					// System.out.println(book_return.get(i).getBookName());
				} // 重新设置s
				mes.setMessageType(Constants.Return);
				Message serverResponse = client.sendRequestToServer(mes);

//				成功与否
				if (serverResponse.isLastOperState()) {
					JOptionPane.showMessageDialog(null, "还书成功！");
					mes.setMessageType(Constants.returnQuery);
					mes.setUserId(u.getId());
					mes.setUserType(u.getUserType());
					serverResponse = client.sendRequestToServer(mes);
					books = (ArrayList<Book>) serverResponse.getData();
				} else {
					JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
				} // end else
//				for(int i=0;i<books.size();i++)
//				{
//					if(books.get(i).getBookName().equals(book_name))
//					{
//						books.remove(i);
//					}
//				}//end  for
//				books=(ArrayList<Book>)serverResponse.getData();
				if (books == null) {
					for (int i = 0; i < num; i++) {
						s[i][0] = " ";
					} // 重新设置s

				} else {

					for (int i = 0; i < books.size(); i++) {
						s[i][0] = books.get(i).getBookName();
						// System.out.println(books.get(i).getBookName());
					} // 重新设置s
				}
			}
		});// end button 的响应函数
		t = new DefaultTableModel(s, n) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane scrollPane = new JScrollPane(table);
		t.addTableModelListener(this);
		
		JTableHeader title = table.getTableHeader();
		title.setFont(new Font("楷体",Font.PLAIN,14));
		table.setPreferredScrollableViewportSize(new Dimension(300, 250));
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(120, 230, 350, 320);
		bOK.setBounds(600, 450, 100, 40);
		this.add(sp);
		this.add(bOK);
		
		title2.setSize(1000, 300);
		title2.setLocation(230, -60);
		this.add(title2);
		
		lBookName.setSize(400,50);
		lBookName.setLocation(530,250);
 		this.add(lBookName);
 		
 		lAuthor.setSize(400,50);
 		lAuthor.setLocation(530,310);
		this.add(lAuthor);
		
		
		lBookPublish.setSize(400,50);
		lBookPublish.setLocation(530,370);
		this.add(lBookPublish);
		
//  	白色
  		final ImageIcon whitebox = new ImageIcon("src/imgs/white-dark.png");
  		final JLabel loginbox = new JLabel(whitebox);
  		loginbox.setSize(760,420);
  		loginbox.setLocation(80,180);
  		this.add(loginbox);

//  	校徽
  		final ImageIcon logo = new ImageIcon("src/imgs/logo-small.png");
  		final JLabel Llogo = new JLabel(logo);
  		Llogo.setSize(130,130);
  		Llogo.setLocation(60,30);
  		this.add(Llogo);
		
		this.setOpaque(false);
		this.setLayout(null);
		
		
//		JPanel jrJPanel = new JPanel();
//		jrJPanel.setBorder(BorderFactory.createTitledBorder("详细信息"));
//		jrJPanel.setSize(100, 100);
//		jrJPanel.setLayout(new GridBagLayout());
//		add(lBookName, cons, 1, 1, 1, 1);
//		add(lBookPublish, cons, 1, 2, 1, 1);
//		add(lAuthor, cons, 1, 3, 1, 1);
//		add(scrollPane, cons, 0, 0, 1, 1);
//		add(bOK, cons, 1, 5, 1, 1);
//		// add(bCancel, cons, 1, 5, 1, 1);
//		for (int i = 0; i < this.getComponentCount(); i++) {
//			Font font = new Font("楷体 常规", Font.CENTER_BASELINE, 15);
//			this.getComponent(i).setFont(font);
//		}
////		 设置窗口大小
//		Toolkit kit = Toolkit.getDefaultToolkit();
//		Dimension screenSize = kit.getScreenSize();
//		int width = screenSize.width;
//		int height = screenSize.height;
//		this.setSize(width, height);

	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int row = e.getFirstRow();// 获取所选数据的行数
		book_name = (String) s[row][1];
		String book_publish = books.get(row).getPublisher();
		String book_author = books.get(row).getBookAuthor();
		lBookName.setText("书籍名称:" + book_name);
		lBookPublish.setText("书籍出版社:" + book_publish);
		lAuthor.setText("书籍作者" + book_author);
		book_return.add(books.get(row));
//		book.setBookAuthor(book_author);
//		book.setBookName(book_name);

	}

}