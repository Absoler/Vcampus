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
//		 �������ÿ��
//		JPanel�����в�����ֱ����this
	Object[][]s= {
			{"����¶�"},
			{"think in java"},
			{"������Щ�¶�"},
			};
	String []n= {"�ִ�ͼ��"};
	JFrame f=new JFrame("Library");
	JLabel book_name=new JLabel("����:����¶�");//Ĭ���ǰ���¶�
	JLabel book_author=new JLabel("����");
	JLabel book_press=new JLabel("������");
	JLabel book_stocknum=new  JLabel("�����");
	JButton button=new JButton("����");
	JLabel book_num=new JLabel("�ѽ�������:");
	JTextField book_search=new JTextField(25);
	JButton button_search=new JButton("����");
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
			
//			 ���ò���
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
			
//			 ���ô��ڴ�С
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int width = screenSize.width;
			int height = screenSize.height;
			f.setSize(width, height);
			//����������
			Object[][]s= {
					{"����¶�"},
					{"think in java"},
					{"������Щ�¶�"},
					};
			String []n= {"�ִ�ͼ��"};
			t=new DefaultTableModel(s,n) {
				
					public boolean isCellEditable(int row, int column)
					{
					return false;
					}
					
			};
			t.addTableModelListener(this);
			JTable table=new JTable(t);
			//������Ϣ��Ӧ����
		    table.setCellSelectionEnabled(true);
		    ListSelectionModel cellSelectionModel = table.getSelectionModel();
		    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		        String selectedData = null;
		        int[] selectedRow = table.getSelectedRows();
		        int[] selectedColumns = table.getSelectedColumns();
		        book_name.setText("����:"+(String)s[selectedRow[0]][0]);
		        book_author.setText("����:"+(String)s[selectedRow[0]][0]);
		        book_press.setText("������:"+(String)s[selectedRow[0]][0]);
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
		    				book_name.setText("����:"+(String)s[i][0]);
		    		        book_author.setText("����:"+(String)s[i][0]);
		    		        book_press.setText("������:"+(String)s[i][0]);
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
			//�ڶ�����������ͼƬ
			JLabel image=new JLabel ();
			
			ImageIcon icon=new ImageIcon("C:\\Users\\yuanyuan\\Desktop\\timg.jpg");
			image.setIcon(icon);
			
			icon.setImage(icon.getImage().getScaledInstance(width/7,height/5,Image.SCALE_DEFAULT ));
			//image.setBounds(10,10,30,40);
			image.setBorder(BorderFactory.createTitledBorder("ͼ����Ƭ��"));
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
			
			
//			����������ɫ�ʹ�С
			for(int i = 0; i < this.getComponentCount(); i++){ 
				Font font = new Font("ƻ�� ����", Font.CENTER_BASELINE, 17); 
				this.getComponent(i).setFont(font);
			}
			this.setBorder(BorderFactory.createTitledBorder("ͼ��ݷ���"));
//			���ñ�����ɫ
			Color bgColor = new Color(255, 255, 255);
			this.setBackground(bgColor);
			f.setContentPane(this);
			f.setVisible(true);
			
		}//end constructor function
		
		//������Ϣ��Ӧ����
		public void tableChanged(TableModelEvent e)
		{
			int row=e.getFirstRow();//��ȡ��ѡ���ݵ�����
			book_name.setText((String)s[row][1]);
			System.out.print("111");
			//String bookName=row.toString();
		}
		public static void  main(String []args)
		{
			library lb=new library();
		}

	}//end class


