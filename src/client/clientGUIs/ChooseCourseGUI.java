package client.clientGUIs;

import java.awt.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import client.util.Client;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import util.*;
import vo.*;

public class ChooseCourseGUI extends JPanel {

	private static final long serialVersionUID = -6352788025440244338L;
	private Image image = null;

	// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	static Student user;
	static Client client; // 加了static，可能有问题
	static ArrayList<Course> courses = new ArrayList<Course>();
	static Choose choose = new Choose();

	JLabel lWelcome0 = new JLabel("————————————————————————————————————————");
	JLabel lWelcome1 = new JLabel("★☆★☆欢迎来到选课系统☆★☆★");
	JLabel lWelcome2 = new JLabel("按下选课按钮即可选课！");
	JLabel lWelcome3 = new JLabel("注意：若课程冲突则不可选课；另外，不要重复选课噢。");
	JLabel lWelcome4 = new JLabel("————————————————————————————————————————");

	JTable table = new JTable(new JTableModel());

//	public void add(Component c, GridBagConstraints cons, int x, int y, int w, int h) {
//		cons.gridx = x;
//		cons.gridy = y;
//		cons.gridwidth = w;
//		cons.gridheight = h;
//		add(c, cons);
//	}

	//	重写ButtonRenderer
	class ButtonRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
													   boolean isSelected, boolean hasFocus, int row, int column) {
			JButton button = (JButton) value;
			button.setText("选课");
			return button;
		}
	}

	//	重写ButtonEditor
	class ButtonEditor extends DefaultCellEditor {
		private static final long serialVersionUID = -6546334664166791132L;

		public ButtonEditor() {
			super(new JTextField());
			this.setClickCountToStart(1);
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value,
													 boolean isSelected, int row, int column) {
			JButton button = (JButton) value;
			button.setText("选课");
			button.addActionListener(new AbstractAction() {
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					//System.out.println("edit!!!!"); //$NON-NLS-1$
				}
			});
			return button;
		}
	}

	//	继承AbstractTableModel
	public static class JTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMN_NAMES =
				new String[]{"课程名称", "任课老师", "学分", "选课"};
		private static final Class<?>[] COLUMN_TYPES =
				new Class<?>[]{String.class, String.class, String.class, JButton.class};

		@Override
		public int getColumnCount() {
			return COLUMN_NAMES.length;
		}

		@Override
		public int getRowCount() {
			return courses.size();
		}

		@Override
		public String getColumnName(int columnIndex) {
			return COLUMN_NAMES[columnIndex];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return COLUMN_TYPES[columnIndex];
		}

		@Override
		public Object getValueAt(final int rowIndex, final int columnIndex) {
			/*Adding components*/
			switch (columnIndex) {
				case 0:
					return courses.get(rowIndex).getCourseName();

				case 1:
					return courses.get(rowIndex).getTeacherName();

				case 2: // fall through
					/*Adding button and creating click listener*/
					return courses.get(rowIndex).getCredit() + "";

				case 3:
					final JButton button = new JButton(COLUMN_NAMES[columnIndex]);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							Message mes = new Message();
							mes.setUserType(0); // student: 0
							mes.setMessageType(Constants.course_choose);
							mes.setUserId(user.getId());
							choose.setCourseID(courses.get(rowIndex).getCourseID());
							choose.setStudentID(user.getId());
							mes.setData(choose);
							Message serverResponse = client.sendRequestToServer(mes);
							if (serverResponse.isLastOperState()) {
								JOptionPane.showMessageDialog(null, "选课成功！O(∩_∩)O");
							} else {
								JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
							}

////                        	通信时注释
//                            JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(button),
//                                    "选课：" + courses.get(rowIndex).getCourseName());
						}
					});
					return button;

				default:
					return "Error";
			}
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			switch (columnIndex) {

				case 0:
					return false;
				case 1:
					return false;
				case 2:
					return true;
				case 3:
					return true;
				default:
					return false;

			}
		}
	}

	public ChooseCourseGUI(Student u, ArrayList<Course> c, Image image) {
		this.image = image;

		user = u;
		courses = c;
		client = new Client();

////		设置布局
//		GridBagLayout lay = new GridBagLayout();
//		this.setLayout(lay);
//		GridBagConstraints cons = new GridBagConstraints();
//		cons.weightx = 3;
//		cons.weighty = 4;
//		cons.fill = GridBagConstraints.NONE;
//		cons.anchor = GridBagConstraints.CENTER;
//		GridBagConstraints cons1 = new GridBagConstraints();
//		cons1.weightx = 1;
//		cons1.weighty = 1;
//		cons1.fill = GridBagConstraints.NONE;
//		cons1.anchor = GridBagConstraints.CENTER;
//
////		设置窗口大小
//		Toolkit kit = Toolkit.getDefaultToolkit();
//		Dimension screenSize = kit.getScreenSize();
//		int width = screenSize.width;
//		int height = screenSize.height;

//		table.addColumn(new TableColumn(0));
//	    table.addColumn(new TableColumn(1));
		table.addColumn(new TableColumn(3, 200, new ButtonRenderer(), new ButtonEditor()));
//	    table.addColumn(new TableColumn(3, 200, new ButtonRenderer(), new ButtonEditor()));
//	    table.addColumn(new TableColumn(2, 200, 200, new ButtonEditor()));

//	    this.add(lWelcome0, cons, 0, 1, 1, 1);
//	    this.add(lWelcome1, cons, 0, 2, 1, 1);
//	    this.add(lWelcome2, cons, 0, 3, 1, 1);
//	    this.add(lWelcome3, cons, 0, 4, 1, 1);
//	    this.add(lWelcome4, cons, 0, 5, 1, 1);
//
//	    this.add(table, cons, 0, 0, 1, 1);

////		设置背景颜色
//		Color bgColor = new Color(255, 255, 255);
//		this.setBackground(bgColor);
//
////		设置字体颜色和大小、行高、标题
//		for(int i = 0; i < this.getComponentCount(); i++){
//			Font font = new Font("宋体", Font.CENTER_BASELINE, 15);
//			this.getComponent(i).setFont(font);
//		}
//		this.setBorder(BorderFactory.createTitledBorder("选课"));
		table.setRowHeight(40);
		table.setPreferredSize(new Dimension(800, 500));
		JScrollPane s = new JScrollPane(table);
		s.setBounds(60, 260, 800, 320);
		this.add(s);

//		Font font1 = new Font("楷体", Font.CENTER_BASELINE, 15);
//			lWelcome0.setFont(font1);
//			lWelcome1.setFont(font1);
//			lWelcome2.setFont(font1);
//			lWelcome3.setFont(font1);

		Font subFont = new Font("苹方 常规",Font.CENTER_BASELINE,70);

		JLabel title = new JLabel("| 学生选课系统");
		title.setFont(subFont);

		title.setSize(1000, 300);
		title.setLocation(280, -70);
		this.add(title);

		lWelcome0.setSize(1000,25);
		lWelcome0.setLocation(210,160);
		this.add(lWelcome0);

		lWelcome1.setSize(600,25);
		lWelcome1.setLocation(380,180);
		this.add(lWelcome1);

		lWelcome2.setSize(600,25);
		lWelcome2.setLocation(410,200);
		this.add(lWelcome2);

		lWelcome3.setSize(800,25);
		lWelcome3.setLocation(320,220);
		this.add(lWelcome3);

		lWelcome4.setSize(1000,25);
		lWelcome4.setLocation(210,240);
		this.add(lWelcome4);

		//  	白色
		final ImageIcon whitebox = new ImageIcon("src/imgs/white-dark.png");
		final JLabel loginbox = new JLabel(whitebox);
		loginbox.setSize(800,450);
		loginbox.setLocation(60,150);
		this.add(loginbox);

//  	校徽
		final ImageIcon logo = new ImageIcon("src/imgs/logo-small.png");
		final JLabel Llogo = new JLabel(logo);
		Llogo.setSize(130,130);
		Llogo.setLocation(120,15);
		this.add(Llogo);

		this.setOpaque(false);
		this.setLayout(null);

	}
}
//end class