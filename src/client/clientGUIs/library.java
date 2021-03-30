package client.clientGUIs;
import javax.swing.*;
import javax.swing.text.html.ImageView;

import util.Message;
import vo.BookInfo;

import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.util.Vector;
import java.awt.event.*;
import javax.swing.event.*;
public class library extends JPanel implements  TableModelListener{
//		 不能设置框架
//		JPanel的所有操作，直接用this
	Object[][]s= {
			{"百年孤独"},
			{"think in java"},
			{"明朝那些事儿"},
			};
	String []n= {"现存图书"};
	JFrame f=new JFrame("Library");
	JLabel book_name=new JLabel("书名:百年孤独");//默认是百年孤独
	JLabel book_author=new JLabel("作者");
	JLabel book_press=new JLabel("出版社");
	JLabel book_stocknum=new  JLabel("库存量");
	JButton button=new JButton("借阅");
	JLabel book_num=new JLabel("已借阅数量:");
	JTextField book_search=new JTextField(25);
	JButton button_search=new JButton("搜索");
	JSeparator separator=new JSeparator(SwingConstants.CENTER);
		JComboBox combo1=null;
		DefaultTableModel t=null;
		String name;
		public void add(Component c, GridBagConstraints cons, int x, int y, int w, int h) {
			cons.gridx = x;
			cons.gridy = y;
			cons.gridwidth = w;
			cons.gridheight = h;
			add(c, cons);
		}
		
		public library() {
			
//			 设置布局
			Message msg = new Message ();
			msg.setMessageType("LIBRARY_QUERY");
			BookInfo library=(BookInfo)msg.getData();
			JPanel p_search=new JPanel();
			book_num.setBorder(BorderFactory.createLineBorder(Color.gray));
			separator.setBackground(Color.blue);
			separator.setPreferredSize(new Dimension(this.getWidth(),20));
			this.add(separator);
			GridBagLayout lay = new GridBagLayout();
			this.setLayout(lay);
			GridBagConstraints cons = new GridBagConstraints();
			cons.weightx = 3;
			cons.weighty = 4;
			cons.fill=GridBagConstraints.NONE;
			cons.anchor=GridBagConstraints.CENTER;
			GridBagConstraints cons1 = new GridBagConstraints();
			cons1.weightx = 1;
			cons1.weighty = 1;
			cons1.fill=GridBagConstraints.NONE;
			cons1.anchor=GridBagConstraints.WEST;
			
//			 设置窗口大小
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int width = screenSize.width;
			int height = screenSize.height;
			f.setSize(width, height);
			//设置下拉框
			Object[][]s= {
					{"百年孤独"},
					{"think in java"},
					{"明朝那些事儿"},
					};
			String []n= {"现存图书"};
			t=new DefaultTableModel(s,n) {
				
					public boolean isCellEditable(int row, int column)
					{
					return false;
					}
					
			};
			t.addTableModelListener(this);
			JTable table=new JTable(t);
			//设置消息响应函数
		    table.setCellSelectionEnabled(true);
		    ListSelectionModel cellSelectionModel = table.getSelectionModel();
		    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		        String selectedData = null;
		        int[] selectedRow = table.getSelectedRows();
		        int[] selectedColumns = table.getSelectedColumns();
		        book_name.setText("书名:"+(String)s[selectedRow[0]][0]);
		        book_author.setText("作者:"+(String)s[selectedRow[0]][0]);
		        book_press.setText("出版社:"+(String)s[selectedRow[0]][0]);
		        System.out.println("111");
		      }
		    });
		    button_search.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		name=book_search.getText();
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
			table.setPreferredScrollableViewportSize(new Dimension(300,300));
			JScrollPane sp=new JScrollPane(table);
			p_search.add(book_search);
			p_search.add(button_search);
			add(p_search,cons,0,0,1,1);
			add(book_num,cons1,0,1,1,1);
			add(sp,cons,0,2,1,1);
			add(button,cons,0,3,1,1);
			//第二个部分设置图片
			JLabel image=new JLabel ();
			
			ImageIcon icon=new ImageIcon("C:\\Users\\yuanyuan\\Desktop\\timg.jpg");
			image.setIcon(icon);
			
			icon.setImage(icon.getImage().getScaledInstance(width/7,height/5,Image.SCALE_DEFAULT ));
			//image.setBounds(10,10,30,40);
			image.setBorder(BorderFactory.createTitledBorder("图书照片："));
			//image=Toolkit.getDefaultToolkit().getImage("C:\\Users\\yuanyuan\\Desktop\\ting.jpg");
			add(image,cons,1,0,1,1);
			add(book_name,cons,1,2,1,1);
			book_name.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			add(book_author,cons,1,3,1,1);
			book_author.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			add(book_press,cons,1,4,1,1);
			book_press.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			add(book_stocknum,cons,1,5,1,1);
			book_stocknum.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			
			f.add(separator);
			f.setContentPane(this);
			f.setVisible(true);
			
			
//			设置字体颜色和大小
			for(int i = 0; i < this.getComponentCount(); i++){ 
				Font font = new Font("苹方 常规", Font.CENTER_BASELINE, 17); 
				this.getComponent(i).setFont(font);
			}
			this.setBorder(BorderFactory.createTitledBorder("图书馆服务"));
//			设置背景颜色
			Color bgColor = new Color(255, 255, 255);
			this.setBackground(bgColor);
			f.setContentPane(this);
			f.setVisible(true);
			
		}//end constructor function
		
		//定义消息响应函数
		public void tableChanged(TableModelEvent e)
		{
			int row=e.getFirstRow();//获取所选数据的行数
			book_name.setText((String)s[row][1]);
			System.out.print("111");
			//String bookName=row.toString();
		}
		public static void  main(String []args)
		{
			library lb=new library();
		}

	}//end class


