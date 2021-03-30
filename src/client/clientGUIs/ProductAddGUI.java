package client.clientGUIs;

import javax.swing.*;
import client.util.Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.*;
import vo.*;

public class ProductAddGUI extends JPanel{
	
	Client client;
	Admin admin;
	Product product=new Product();
	
	JButton bOK = new JButton("ȷ��");
	JButton bCancel = new JButton("ȡ��");
	JLabel lProductName = new JLabel("��Ʒ���ƣ�");
	JLabel lPrice = new JLabel("�۸�");
	JLabel lStockNum = new JLabel("�������");

	JTextField tProductName = new JTextField(10);
	JTextField tPrice = new JTextField(10);
	JTextField tStockNum = new JTextField(10);
	
	public void add(Component c, GridBagConstraints constraints,int x,int y,int w,int h){
		constraints.gridx=x;
		constraints.gridy=y;
		constraints.gridwidth=w;
		constraints.gridheight=h;
		add(c,constraints);
	}
	
	public ProductAddGUI() {
		client = new Client();
//		admin = a;
		
//		���������鲼�ַ�ʽ����
		GridBagLayout lay = new GridBagLayout();
		setLayout(lay);
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.NONE;
//		cons.anchor = GridBagConstraints;
		cons.weightx = 6;
		cons.weighty = 8;
		
		add(lProductName, cons, 0, 0, 1, 1);
		add(tProductName, cons, 1, 0, 1, 1);
		add(lPrice, cons, 2, 0, 1, 1);
		add(tPrice, cons, 3, 0, 1, 1);
		add(lStockNum, cons, 0, 1, 1, 1);
		add(tStockNum, cons, 1, 1, 1, 1);
		add(bOK, cons, 1, 5, 1, 1);
		add(bCancel, cons, 2, 5, 1, 1);
		
//		OK��Ӧ����ͨ��
		bOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String productNametext = tProductName.getText();
				String pricetext = tPrice.getText();
				String stockNumtext = tStockNum.getText();
				
//				�쳣�¼����������Ի���
				if(productNametext.equals("")) {
					JOptionPane.showMessageDialog(null, "��Ʒ���Ʋ���Ϊ�գ����������롣");
				}else if(pricetext.equals("")) {
					JOptionPane.showMessageDialog(null, "�۸񲻵�Ϊ�գ����������롣");
				}else if(stockNumtext.equals("")) {
					JOptionPane.showMessageDialog(null, "���������Ϊ�գ����������롣");
				}
				
				Message mes = new Message();
				mes.setMessageType(Constants.add_product);
				product.setProductName(productNametext);
				product.setProductPrice(Double.parseDouble(pricetext));
				product.setStockNum(Integer.parseInt(stockNumtext));
				mes.setData(product);
				Message serverResponse = client.sendRequestToServer(mes);
				
//				�ɹ����
				if(serverResponse.isLastOperState())
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
				else {
					JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
				}
			}
		});
		
//		ȡ����Ӧ�������������Ϣ
		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tProductName.setText("");
				tPrice.setText("");
				tStockNum.setText("");
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
		this.setBorder(BorderFactory.createTitledBorder("�����Ʒ"));
	}

}
