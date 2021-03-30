package client.clientGUIs;

import java.awt.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.lang.ObjectUtils.Null;

import client.util.Client;
import util.Message;
import util.Student;
import javax.swing.table.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.color.*;
import util.*;
import vo.*;

public class DormChangeGUI extends JPanel implements TableModelListener{
	
	Client client;
	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;
	JLabel title2 = new JLabel("| 学生管理");
	// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	String []type= { "姓名","宿舍号","成绩"};
	int[]selectedRows;
	int[]selectCols;
	String tmp;
	Object [][]p=new Object[100][100];
	DefaultTableModel model;
	JButton button=new JButton("确定") ;
	Message msg=new Message();
	JComboBox c;
	JTable t;
	JTextField grade = new JTextField(10);
	ArrayList<Student>students;
//	public void add(Component c, GridBagConstraints cons, int x, int y, int w, int h) {
//		cons.gridx = x;
//		cons.gridy = y;
//		cons.gridwidth = w;
//		cons.gridheight = h;
//		add(c, cons);
//	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int col = e.getColumn();
		if(col==2) {
			students.get(row).setGPA(Double.parseDouble((String)model.getValueAt(row, col)));
		}else if (col==1) {
			students.get(row).setDormNum((String)model.getValueAt(row, col));
		}
	}
	
	public DormChangeGUI(ArrayList<Student>students1,Image image)
	{
		client = new Client();
		students=students1;
		this.image = image;
		for(int i=0;i<students.size();i++)
		{
			p[i][0]=students.get(i).getRealName();
			p[i][1]=students.get(i).getDormNum();
			p[i][2]=students.get(i).getGPA();
						
		}
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//button的消息响应函数

				for(int i=0;i<students.size();i++)
				{
					System.out.println(students.get(i).getRealName()+" "+students.get(i).getDormNum());
				}
				msg.setMessageType("UPDATE_USER_INFO");//设置消息类型
				msg.setData(students);//把students设置到Meg里面
				Message serverResponse = client.sendRequestToServer(msg);//把students传回服务端
				if(serverResponse.isLastOperState()==false)
					JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
			}
		});
		

		String []type= { "姓名","新宿舍号","成绩"};
		
		System.out.println(button.getBackground());
		
		model = new DefaultTableModel(p,type);
		model.addTableModelListener(this);
		t=new JTable(model);
		//设置表格的标题栏
		
		JTableHeader table=t.getTableHeader();
		table.setFont(new Font("楷体",Font.PLAIN,14));
		table.setPreferredSize(new Dimension(table.getWidth(),40));
		JScrollPane s=new JScrollPane(t);
		s.setBounds(240, 230, 440, 300);
		button.setBounds(410, 550, 100, 40);
		this.add(s);
		this.add(button);
		//t.setSelectionBackground(Color.yellow);//设置被选中行的颜色
	
		TableColumn dorm = t.getColumnModel().getColumn(1);
		JComboBox c=new JComboBox();
		c.addItem("M6B");//
		c.addItem("M1C");//
		c.addItem("T2B");//
		c.addItem("M1B");
		c.addItem("M3B");//
		c.addItem("M4C");//
		c.addItem("T5B");//
		c.addItem("M7B");
		dorm.setCellEditor(new DefaultCellEditor(c));
//		c.addItemListener(new ItemListener() {	
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				// TODO Auto-generated method stub
//				// TODO Auto-generated method stub
//				 tmp=(String)c.getSelectedItem();
//		         selectedRows = t.getSelectedRows();		        
//		         students.get(t.getSelectedRow()).setDormNum(tmp);
//			}
//		});
//		GridBagConstraints cons=new GridBagConstraints();
//		
//		cons.weightx = 3;
//		cons.weighty = 4;
//		cons.fill=GridBagConstraints.NONE;
//		cons.anchor=GridBagConstraints.CENTER;
		
	//设置表格的渲染器
		
		
		
		Font subFont = new Font("苹方 常规",Font.CENTER_BASELINE,70);
		title2.setFont(subFont);
		title2.setSize(1000, 300);
		title2.setLocation(230, -60);
		this.add(title2);

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
		
	}

}//end class