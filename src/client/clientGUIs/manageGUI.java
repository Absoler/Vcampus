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
			
			{"����","Warning"},
			{"�α�","Warning"},
		
};
String []type= { "����","��������"};
JComboBox c=new JComboBox();//���������������б��
String tmp;//����������
int[]selectedRow;
ArrayList<Dorm> v1=new ArrayList<Dorm>();//�洢���������Ϣ,���һ�α��һ�ٴ�
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
	
		JButton button=new JButton("ȷ��");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			
				//button����Ϣ��Ӧ����
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
				
				{"����","Warning"},
				{"�α�","Warning"},
			
	};
	String []type= { "����","��������"};

		JFrame f=new JFrame();
		
		System.out.println(button.getBackground());
		MyTable mt=new MyTable();
		 t=new JTable(mt);
		//���ñ��ı�����
		JTableHeader table=t.getTableHeader();
		
		table.setFont(new Font("����",Font.PLAIN,14));
		
		t.setSelectionBackground(Color.yellow);//���ñ�ѡ���е���ɫ
		GridBagLayout lay=new GridBagLayout();
		this.setLayout(lay);
		GridBagConstraints cons=new GridBagConstraints();
		cons.weightx = 3;
		cons.weighty = 4;
		cons.fill=GridBagConstraints.NONE;
		cons.anchor=GridBagConstraints.CENTER;
		
		c.addItem("Warning");//����
		c.addItem("Demerit");//�ǹ�
		c.addItem("Expulsion");//����
		c.addActionListener(this);
	//���ñ�����Ⱦ��
		//t.getColumnModel().getColumn(1).setCellRenderer(new MyButtonRender());
		t.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(c));//��combox��Ϊ�ڶ��е�Ĭ�ϱ༭���
		t.setPreferredScrollableViewportSize(new Dimension(1000,30));
		//t.getColumnModel().getColumn(0).addPropertyChangeListener(x);
		JScrollPane s=new JScrollPane(t);
		//f.getContentPane().add(s,BorderLayout.CENTER);
		add(s,cons,0,0,1,1);
		add(button,cons,0,2,1,1);
		this.setBorder(BorderFactory.createTitledBorder("�������"));
		ListSelectionModel cellSelectionModel = t.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
		        String selectedData = null;
		         selectedRow = t.getSelectedRows();
		         Dorm d=new Dorm(p[selectedRow[0]][0],tmp);
		         
		       //�ж��Ƿ��ظ�
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
					 
						{"����","Warning"},
						{"�α�","Warning"},
					
			};
			String []type= { "����","��������"};
			@Override
			public int getRowCount() {
				// TODO �Զ����ɵķ������
				return p.length;
			}

			@Override
			public int getColumnCount() {
				// TODO �Զ����ɵķ������
				return type.length;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO �Զ����ɵķ������
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
			// TODO �Զ����ɵķ������
			 tmp=(String)c.getSelectedItem();
			 String selectedData = null;
	         selectedRow = t.getSelectedRows();
	         Dorm d=new Dorm(p[selectedRow[0]][0],tmp);
	         
	       //�ж��Ƿ��ظ�
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
	

