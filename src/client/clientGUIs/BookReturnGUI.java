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

   // �̶�����ͼƬ���������JPanel������ͼƬ������������
    protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	Client client = new Client();
	// ���һ���ж��߼�
//	ArrayList<Integer> ls = new ArrayList<Integer>();
	User u;
	// BookInfo book;
	JLabel title2 = new JLabel("| ͼ��ݻ������");
	String[] n = { "�ѽ�ͼ��" };
	Object[][] s = new Object[100][100];
	String book_name;// �������
	DefaultTableModel t;
	JButton bOK = new JButton("ȷ���黹");
	JLabel lBookName = new JLabel("�鼮���ƣ�");
	JLabel lAuthor = new JLabel("���ߣ�");
	JLabel lBookPublish = new JLabel("�����磺");
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
////		���������鲼�ַ�ʽ����
//		GridBagLayout lay = new GridBagLayout();
//		setLayout(lay);

//		���������ʽ�ʹ�С
		Font megaFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,60);
		Font subFont = new Font("ƻ�� ����",Font.CENTER_BASELINE,70);
  		Font font = new Font("ƻ�� ����",Font.CENTER_BASELINE,30);
	    Font font1 = new Font("ƻ�� ����",Font.BOLD,15);
	    Font font2 = new Font("ƻ�� ����",Font.CENTER_BASELINE,15);
	    //title.setFont(megaFont);
	    title2.setFont(subFont);
		lBookName.setFont(font);
		lAuthor.setFont(font);
		lBookPublish.setFont(font);
		bOK.setFont(font1);
		
		
		t = new DefaultTableModel(s, n);//���ݣ���ͷ������
		// t.se
		JTable table = new JTable(t);
		
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				int row = table.getSelectedRow();// ��ȡ��ѡ���ݵ�����
				// System.out.println(book_name);
				if (row < books.size()) {
					if (!book_return.isEmpty())
						book_return.clear();
					book_name = (String) s[row][0];
					String book_publish = books.get(row).getPublisher();
					String book_author = books.get(row).getBookAuthor();
					lBookName.setText("�鼮����:" + book_name);
					lBookPublish.setText("�鼮������:" + book_publish);
					lAuthor.setText("�鼮����" + book_author);
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
//						lBookName.setText("�鼮����:" + book_name);
//						lBookPublish.setText("�鼮������:" + book_publish);
//						lAuthor.setText("�鼮����" + book_author);
//						book_return.add(books.get(row));
//						ls.add(row);
//					}
//				}

			}// end valueChanged function
		});


		table.setPreferredScrollableViewportSize(new Dimension(200, 300));

//		OK��Ӧ����ͨ��

		bOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Message mes = new Message();
				mes.setUserId(u.getId());
				mes.setUserType(u.getUserType());
				mes.setData(book_return);
				for (int i = 0; i < books.size(); i++) {
					// s[i][0] = books.get(i).getBookName();
					// System.out.println(book_return.get(i).getBookName());
				} // ��������s
				mes.setMessageType(Constants.Return);
				Message serverResponse = client.sendRequestToServer(mes);

//				�ɹ����
				if (serverResponse.isLastOperState()) {
					JOptionPane.showMessageDialog(null, "����ɹ���");
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
					} // ��������s

				} else {

					for (int i = 0; i < books.size(); i++) {
						s[i][0] = books.get(i).getBookName();
						// System.out.println(books.get(i).getBookName());
					} // ��������s
				}
			}
		});// end button ����Ӧ����
		t = new DefaultTableModel(s, n) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane scrollPane = new JScrollPane(table);
		t.addTableModelListener(this);
		
		JTableHeader title = table.getTableHeader();
		title.setFont(new Font("����",Font.PLAIN,14));
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
		
		
//		JPanel jrJPanel = new JPanel();
//		jrJPanel.setBorder(BorderFactory.createTitledBorder("��ϸ��Ϣ"));
//		jrJPanel.setSize(100, 100);
//		jrJPanel.setLayout(new GridBagLayout());
//		add(lBookName, cons, 1, 1, 1, 1);
//		add(lBookPublish, cons, 1, 2, 1, 1);
//		add(lAuthor, cons, 1, 3, 1, 1);
//		add(scrollPane, cons, 0, 0, 1, 1);
//		add(bOK, cons, 1, 5, 1, 1);
//		// add(bCancel, cons, 1, 5, 1, 1);
//		for (int i = 0; i < this.getComponentCount(); i++) {
//			Font font = new Font("���� ����", Font.CENTER_BASELINE, 15);
//			this.getComponent(i).setFont(font);
//		}
////		 ���ô��ڴ�С
//		Toolkit kit = Toolkit.getDefaultToolkit();
//		Dimension screenSize = kit.getScreenSize();
//		int width = screenSize.width;
//		int height = screenSize.height;
//		this.setSize(width, height);

	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int row = e.getFirstRow();// ��ȡ��ѡ���ݵ�����
		book_name = (String) s[row][1];
		String book_publish = books.get(row).getPublisher();
		String book_author = books.get(row).getBookAuthor();
		lBookName.setText("�鼮����:" + book_name);
		lBookPublish.setText("�鼮������:" + book_publish);
		lAuthor.setText("�鼮����" + book_author);
		book_return.add(books.get(row));
//		book.setBookAuthor(book_author);
//		book.setBookName(book_name);

	}

}