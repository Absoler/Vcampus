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
	
	JButton bOK = new JButton("确定");
	JButton bCancel = new JButton("取消");
	JLabel lProductName = new JLabel("商品名称：");
	JLabel lPrice = new JLabel("价格：");
	JLabel lStockNum = new JLabel("库存量：");

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
		
//		创建网格组布局方式对象
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
		
//		OK响应——通信
		bOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String productNametext = tProductName.getText();
				String pricetext = tPrice.getText();
				String stockNumtext = tStockNum.getText();
				
//				异常事件处理，弹出对话框
				if(productNametext.equals("")) {
					JOptionPane.showMessageDialog(null, "商品名称不得为空，请重新输入。");
				}else if(pricetext.equals("")) {
					JOptionPane.showMessageDialog(null, "价格不得为空，请重新输入。");
				}else if(stockNumtext.equals("")) {
					JOptionPane.showMessageDialog(null, "库存量不得为空，请重新输入。");
				}
				
				Message mes = new Message();
				mes.setMessageType(Constants.add_product);
				product.setProductName(productNametext);
				product.setProductPrice(Double.parseDouble(pricetext));
				product.setStockNum(Integer.parseInt(stockNumtext));
				mes.setData(product);
				Message serverResponse = client.sendRequestToServer(mes);
				
//				成功与否
				if(serverResponse.isLastOperState())
					JOptionPane.showMessageDialog(null, "添加成功！");
				else {
					JOptionPane.showMessageDialog(null, serverResponse.getErrorMessage());
				}
			}
		});
		
//		取消响应——清除所有信息
		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tProductName.setText("");
				tPrice.setText("");
				tStockNum.setText("");
			}
		});
		
		
//		 设置窗口大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		this.setSize(width, height);
//		设置背景颜色
		Color bgColor = new Color(255, 255, 255);
		this.setBackground(bgColor);
//		设置字体颜色和大小
		for(int i = 0; i < this.getComponentCount(); i++){ 
			Font font = new Font("苹方 常规", Font.CENTER_BASELINE,15); 
			this.getComponent(i).setFont(font);
		}
		this.setBorder(BorderFactory.createTitledBorder("添加商品"));
	}

}
