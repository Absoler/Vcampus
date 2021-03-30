package client.clientGUIs;

import javax.swing.*;

import vo.Product;
import client.util.*;
import util.Constants;
import util.Message;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class goodsGUI extends JPanel {
	ArrayList<Product> products;
	Client client;
	String path = "";
	ArrayList<Product> cart;	//选中的商品
	
    public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h){
        constraints.gridx=x;
        constraints.gridy=y;
        constraints.gridwidth=w;
        constraints.gridheight=h;
        add(c,constraints);
    }

    int count=0;

    goodsGUI(String str){
    	client = new Client();
    	Message message = new Message();
    	message.setMessageType(Constants.shopQuery);
    	Message serverResponse = client.sendRequestToServer(message);
    	if(message.getMessageType().equals(Constants.shopInit)) {
    		products=(ArrayList<Product>)serverResponse.getData();
    	}
    	
        this.setPreferredSize(new Dimension(50,100));
        GridBagLayout lay=new GridBagLayout();
        setLayout(lay);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,20,20);
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1.0;

        class Product_Label extends JLabel{
        	private int index;
        	
			public int getIndex() {
				return index;
			}
			public void setIndex(int index) {
				this.index = index;
			}
        	
        }
        
//        class goods{
//            private String productName;//商品名称
//            private double productPrice;//价格
//            private int stockNum;//库存
//
//            private int row;//行
//            private int column;//列
//
//            public void setProductName(String productName) { this.productName = productName; }
//
//            public String getProductName() { return productName; }
//
//            public void setProductPrice(double productPrice) { this.productPrice = productPrice; }
//
//            public double getProductPrice() { return productPrice; }
//
//            public void setStockNum(int stockNum) { this.stockNum = stockNum; }
//
//            public int getStockNum() { return stockNum; }
//
//            goods(int count){
//                column = count%6;
//                row = (count-column)/3;
//            }
//
//            public void picture(String path){
//                JLabel L=new JLabel();
//                L.addMouseListener(new MouseAdapter() {
//                	
//				});
//                L.setIcon(new ImageIcon(path));
//                
//                add(L,constraints,column,row,1,1);
//            }
//            public void info(){
//                JLabel L = new JLabel(productName+"      ￥"+ productPrice );
//                add(L,constraints,column,row+1,1,1);
//            }
//
//        }

        if(str.equals("图书文具")) {
        	
        	for(int i=0;i<products.size();++i) {
        		Product now = products.get(i);
        		Product_Label label = new Product_Label();
        		label.setIndex(count++);
        		label.setIcon(new ImageIcon(path+now.getProductName()+".jpg"));
        		label.setText(now.getProductName()+"      ￥"+ now.getProductPrice());
        		
        		//设置文字位置
        		label.setHorizontalTextPosition(SwingConstants.CENTER);
        		label.setVerticalTextPosition(SwingConstants.BOTTOM);
        		
        		label.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent e) {
        				Product choose = products.get(label.getIndex());
        				cart.add(choose);
        			}
				});
        		
        		add(label,constraints,count%6,count/6,1,1);
        	}
        	
        	
//            goods G1 = new goods(count++);
//            G1.setProductName("书包");
//            G1.setProductPrice(56);
//            G1.picture("C:\\商品图片\\bag1.jpg");
//            G1.info();
//
//            goods G2 = new goods(count++);
//            G2.setProductName("笔记本");
//            G2.setProductPrice(18);
//            G2.picture("C:\\商品图片\\notebook1.jpg");
//            G2.info();
//
//            goods G3 = new goods(count++);
//            G3.setProductName("钢笔");
//            G3.setProductPrice(220);
//            G3.picture("C:\\商品图片\\pen1.jpg");
//            G3.info();
//
//            goods G4 = new goods(count++);
//            G4.setProductName("刨笔刀");
//            G4.setProductPrice(9.9);
//            G4.picture("C:\\商品图片\\刨笔刀1.jpg");
//            G4.info();
//
//            goods G5 = new goods(count++);
//            G5.setProductName("圆规三角尺");
//            G5.setProductPrice(13.5);
//            G5.picture("C:\\商品图片\\圆规三角尺1.jpg");
//            G5.info();
//
//            goods G6 = new goods(count++);
//            G6.setProductName("文件筐");
//            G6.setProductPrice(19.9);
//            G6.picture("C:\\商品图片\\文件框1.jpg");
//            G6.info();
//
//            goods G7 = new goods(count++);
//            G7.setProductName("《浮生六记》");
//            G7.setProductPrice(16);
//            G7.picture("C:\\商品图片\\《浮生六记》.jpg");
//            G7.info();




        }



    }

}
