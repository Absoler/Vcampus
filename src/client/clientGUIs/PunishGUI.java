package client.clientGUIs;

import java.awt.*;

import client.util.Client;
import util.*;
import vo.*;
import javax.swing.table.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.color.*;

public class PunishGUI extends JPanel implements TableModelListener{
	
	Client client;
	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;

	// �̶�����ͼƬ���������JPanel������ͼƬ������������
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	Object [][]p=new Object[100][100];
	JLabel title2 = new JLabel("| ѧ����������");
	JComboBox c=new JComboBox();//���������������б��
	String tmp;//����������
	ArrayList<Student>students;
	int[]selectedRow;
	int[]selectedCol;
	DefaultTableModel model = new DefaultTableModel();
//	ArrayList<Dorm> v1=new ArrayList<Dorm>();//�洢���������Ϣ,���һ�α��һ�ٴ�
	JTable t;
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
		if(col==1) {
			students.get(row).setPunishment(((String)model.getValueAt(row, col)));
		}
	}
	
	public PunishGUI(Image image) {
		this.image = image;
		client = new Client();
		Message msg=new Message();
		msg.setMessageType(Constants.punishQUERY);
		Message serverResponse = client.sendRequestToServer(msg);
		students= (ArrayList<Student>)serverResponse.getData();
		
		for(int i=0;i<students.size();i++)
		{
			p[i][1]=students.get(i).getPunishment();
			p[i][0]=students.get(i).getRealName();
		}
		JButton button=new JButton("ȷ��");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				//button����Ϣ��Ӧ����
				for(int i=0;i<students.size();i++)
				{
					System.out.println(students.get(i).getRealName()+" "+students.get(i).getDormNum());
				}
				msg.setMessageType("UPDATE_USER_INFO");//������Ϣ����
				msg.setData(students);//��students���õ�Meg����
				Message serverResponse = client.sendRequestToServer(msg);//��students���ط����
				if(serverResponse.isLastOperState()==false)
					JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
			}
				
		});
		//button.setBackground(Color.Black);

		
		System.out.println(button.getBackground());
		
		String []type= { "����","��������"};
		model = new DefaultTableModel(p,type);
		model.addTableModelListener(this);
		t = new JTable(model);
		//���ñ��ı�����
		
		TableColumn dorm = t.getColumnModel().getColumn(1);
		
		JComboBox c=new JComboBox();
		c.addItem(Constants.warning);
		c.addItem(Constants.demerit);
		c.addItem(Constants.expulsion);
		dorm.setCellEditor(new DefaultCellEditor(c));
		
		
		
		//t.setSelectionBackground(Color.yellow);//���ñ�ѡ���е���ɫ
//		GridBagLayout lay=new GridBagLayout();
//		this.setLayout(lay);
//		GridBagConstraints cons=new GridBagConstraints();
//		cons.weightx = 3;
//		cons.weighty = 4;
//		cons.fill=GridBagConstraints.NONE;
//		cons.anchor=GridBagConstraints.CENTER;
		
		
//		t.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(c));//��combox��Ϊ�ڶ��е�Ĭ�ϱ༭���
		t.setPreferredScrollableViewportSize(new Dimension(200,300));
		
		
		JTableHeader table=t.getTableHeader();
		table.setFont(new Font("����",Font.PLAIN,14));
		table.setPreferredSize(new Dimension(table.getWidth(),40));
		JScrollPane s=new JScrollPane(t);
		s.setBounds(240, 230, 440, 300);
		
		button.setBounds(410, 550, 100, 40);
		this.add(s);
		this.add(button);
		Font subFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,70);
		title2.setFont(subFont);
		title2.setSize(1000, 300);
		title2.setLocation(230, -60);
		this.add(title2);

//  	��ɫ
		final ImageIcon whitebox = new ImageIcon("src/imgs/white-dark.png");
		final JLabel loginbox = new JLabel(whitebox);
		loginbox.setSize(760,420);
		loginbox.setLocation(80,180);
		this.add(loginbox);

//  	У��
		final ImageIcon logo = new ImageIcon("src/imgs/logo-small.png");
		final JLabel Llogo = new JLabel(logo);
		Llogo.setSize(130,130);
		Llogo.setLocation(60,30);
		this.add(Llogo);

		this.setOpaque(false);
		this.setLayout(null);
	}			
			

}//end class