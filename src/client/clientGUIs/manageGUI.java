package client.clientGUIs;
import java.awt.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;
import java.awt.event.*;
import java.awt.color.*;
import java.util.*;
public class manageGUI extends JPanel implements ActionListener{
	
	Object[][] p= {
			
			{"王鹏","Warning"},
			{"宋冰","Warning"},
		
};
String []type= { "姓名","处分类型"};
JComboBox c=new JComboBox();//处罚的类型下拉列表框
String tmp;//处罚的类型
int[]selectedRow;
ArrayList<Dorm> v1=new ArrayList<Dorm>();//存储变更过的信息,最多一次变更一百次
JTable t;
public void add(Component c, GridBagConstraints cons, int x, int y, int w, int h) {
	cons.gridx = x;
	cons.gridy = y;
	cons.gridwidth = w;
	cons.gridheight = h;
	add(c, cons);
}
	public manageGUI()
	{
	
		JButton button=new JButton("确定");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			
				//button的消息响应函数
				int count=0;
				for(int i=0;i<(v1.size());i++)
				{
					for(int j=i+1;j<v1.size();j++)
					{
					if(v1.get(j).getName().equals(v1.get(i).getName()))
					{
						
						v1.remove(i);
						i--;
						break;
					}//end inner if
					}//end inner for
					
				}//end outer for
				//System.out.println(v1.size());
				for(int i=0;i<v1.size();i++)
					System.out.println(v1.get(i).getDorm()+" "+v1.get(i).getName());
				
				
//				
			}
		});
		//button.setBackground(Color.Black);
		Object[][] p= {
				
				{"王鹏","Warning"},
				{"宋冰","Warning"},
			
	};
	String []type= { "姓名","处分类型"};

		JFrame f=new JFrame();
		
		System.out.println(button.getBackground());
		MyTable mt=new MyTable();
		 t=new JTable(mt);
		//设置表格的标题栏
		JTableHeader table=t.getTableHeader();
		
		table.setFont(new Font("楷体",Font.PLAIN,14));
		
		t.setSelectionBackground(Color.yellow);//设置被选中行的颜色
		GridBagLayout lay=new GridBagLayout();
		this.setLayout(lay);
		GridBagConstraints cons=new GridBagConstraints();
		cons.weightx = 3;
		cons.weighty = 4;
		cons.fill=GridBagConstraints.NONE;
		cons.anchor=GridBagConstraints.CENTER;
		
		c.addItem("Warning");//警告
		c.addItem("Demerit");//记过
		c.addItem("Expulsion");//开除
		c.addActionListener(this);
	//设置表格的渲染器
		//t.getColumnModel().getColumn(1).setCellRenderer(new MyButtonRender());
		t.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(c));//将combox作为第二列的默认编辑组件
		t.setPreferredScrollableViewportSize(new Dimension(1000,30));
		//t.getColumnModel().getColumn(0).addPropertyChangeListener(x);
		JScrollPane s=new JScrollPane(t);
		//f.getContentPane().add(s,BorderLayout.CENTER);
		add(s,cons,0,0,1,1);
		add(button,cons,0,2,1,1);
		this.setBorder(BorderFactory.createTitledBorder("教务管理"));
		ListSelectionModel cellSelectionModel = t.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		        String selectedData = null;
		         selectedRow = t.getSelectedRows();
		         Dorm d=new Dorm(p[selectedRow[0]][0],tmp);
		         
		       //判断是否重复
		         if(v1.size()==0)
		         {
		        	 v1.add(d);
		         }
		       for(int i=0;i<v1.size();i++)
		       {
		    	   if(v1.get(i).getName().equals(p[selectedRow[0]][0]))
		    	   {
		    		   
		    	   
		    		   v1.get(i).setDorm(tmp);//end if
		    	   }
		    	   else
		    		   v1.add(d);
		    	  
		    		   
		       }//end for
		       
		      }//end valueChanged function
		    });
		f.setContentPane(this);
		f.setTitle("manageGUI");
		f.pack();
		f.setVisible(true);
	}
//		
		class MyTable extends AbstractTableModel
		{
			Object[][] p= {
					 
						{"王鹏","Warning"},
						{"宋冰","Warning"},
					
			};
			String []type= { "姓名","处分类型"};
			@Override
			public int getRowCount() {
				// TODO 自动生成的方法存根
				return p.length;
			}

			@Override
			public int getColumnCount() {
				// TODO 自动生成的方法存根
				return type.length;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO 自动生成的方法存根
				return p[rowIndex][columnIndex];
			}
			public void setValueAt(Object value,int row,int col)
			{
				p[row][col]=value;
				fireTableCellUpdated(row,col);
				}
			public Class getColumClass(int c)
			{
				return getValueAt(0,c).getClass();
			}
		public boolean isCellEditable(int rowIndex,int columnIndex)
		{
			return true;
		}//end isCellEditable function
		
		}//end class MyTable
		
	
		public static void  main(String []args)
		{
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch(Exception e) {}
			manageGUI manage=new manageGUI();
			
			

		}//end main function
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			 tmp=(String)c.getSelectedItem();
			 String selectedData = null;
	         selectedRow = t.getSelectedRows();
	         Dorm d=new Dorm(p[selectedRow[0]][0],tmp);
	         
	       //判断是否重复
	         if(v1.size()==0)
	         {
	        	 v1.add(d);
	         }
	       for(int i=0;i<v1.size();i++)
	       {
	    	   if(v1.get(i).getName().equals(p[selectedRow[0]][0]))
	    	   {
	    		   
	    	  
	    		   v1.get(i).setDorm(tmp);//end if
	    	   }
	    	   else
	    		   v1.add(d);
	    	  
	    		   
	       }//end for
			
			
			
		}
		public class Dorm{
			private Object name;
			private String dorm;
			public Dorm(Object  name,String dorm)
			{
				this.name=name;
				this.dorm=dorm;
			}
			public void setDorm(String dorm)
			{
				this.dorm=dorm;
			}
			public Object getName()
			{
				return name;
			}
			public String getDorm()
			{
				return dorm;
				
			}
			
		}//end class Dorm
		}//end class
	

