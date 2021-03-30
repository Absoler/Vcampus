package client.clientGUIs;

import javax.swing.*;


import client.util.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.*;
import vo.*;

public class BookAddGUI extends JPanel{
	
	Client client;
	Admin admin;
	Book book=new Book();
	
	JButton bOK = new JButton("ȷ��");
	JButton bCancel = new JButton("ȡ��");
	JLabel lBookName = new JLabel("�鼮���ƣ�");
	JLabel lAuthor = new JLabel("���ߣ�");
	JLabel lPublisher = new JLabel("�����磺");
	JLabel lStockNum = new JLabel("�������");
	
	JTextField tBookName = new JTextField(10);
	JTextField tAuthor = new JTextField(10);
	JTextField tPublisher = new JTextField(10);
	JTextField tStockNum = new JTextField(10);
	
	public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h){
		constraints.gridx=x;
		constraints.gridy=y;
		constraints.gridwidth=w;
		constraints.gridheight=h;
		add(c,constraints);
	}
	
	public BookAddGUI() {
		client = new Client();
//		admin = a;
		

//		���������鲼�ַ�ʽ����
		GridBagLayout lay = new GridBagLayout();
		setLayout(lay);
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.NONE;
//		cons.anchor = GridBagConstraints.EAST;
		cons.weightx = 3;
		cons.weighty = 4;
		
		add(lBookName, cons, 0, 0, 1, 1);
		add(tBookName, cons, 1, 0, 1, 1);
		add(lAuthor, cons, 2, 0, 1, 1);
		add(tAuthor, cons, 3, 0, 1, 1);
		add(lPublisher, cons, 0, 1, 1, 1);
		add(tPublisher, cons, 1, 1, 1, 1);
		add(lStockNum, cons, 2, 1, 1, 1);
		add(tStockNum, cons, 3, 1, 1, 1);
		add(bOK, cons, 1, 5, 1, 1);
		add(bCancel, cons, 2, 5, 1, 1);
		
		
//		OK��Ӧ����ͨ��
		bOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bookNametext = tBookName.getText();
				String authortext = tAuthor.getText();
				String publishertext = tPublisher.getText();
				String stockNumtext = tStockNum.getText();
				boolean flag=true;
				
//				�쳣�¼����������Ի���
				if(tBookName.equals("")) {
					JOptionPane.showMessageDialog(null, "��������Ϊ�գ����������롣");
				}else if(authortext.equals("")) {
					JOptionPane.showMessageDialog(null, "���߲���Ϊ�գ����������롣");
				}else if(publishertext.equals("")) {
					JOptionPane.showMessageDialog(null, "�����粻��Ϊ�գ����������롣");
				}else if(stockNumtext.equals("")) {
					JOptionPane.showMessageDialog(null, "���������Ϊ�գ����������롣");
				}
				
				for(int t=0;t<Integer.parseInt(stockNumtext);t++) {
					Message mes = new Message();
					mes.setMessageType(Constants.add_book);
					book.setBookName(bookNametext);
					book.setBookAuthor(authortext);
					book.setBorrowed(false);
					book.setUserId(0);
					book.setUserType(-1);
					book.setPublisher(publishertext);
					mes.setData(book);
					Message serverResponse = client.sendRequestToServer(mes);
					if(!serverResponse.isLastOperState())
						flag=false;
				}
//					�ɹ����
					if(flag)
						JOptionPane.showMessageDialog(null, "��ӳɹ���");
					else {
						JOptionPane.showMessageDialog(null, "��֪������Ӵ���");
					}
			}
		});
		
//		ȡ����Ӧ�������������Ϣ
		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tBookName.setText("");
				tAuthor.setText("");
				tPublisher.setText("");
//				tStockNum.setText("");
			}
		});
		
		
//		 ���ô��ڴ�С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		this.setSize(width, height);
		
//		���ñ�����ɫ
		Color bgColor = new Color(255, 255, 255);
		this.setBackground(bgColor);
		
//		����������ɫ�ʹ�С
		for(int i = 0; i < this.getComponentCount(); i++){ 
			Font font = new Font("ƻ�� ����", Font.CENTER_BASELINE,15); 
			this.getComponent(i).setFont(font);
		}
		this.setBorder(BorderFactory.createTitledBorder("���ͼ��"));
		
	}

}
