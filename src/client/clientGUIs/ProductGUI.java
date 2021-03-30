package client.clientGUIs;

import javax.swing.*;

import vo.Product;
import client.util.*;
import util.Constants;
import util.Message;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProductGUI extends JPanel {
    ArrayList<Product> products;
    Client client;
    String path = "D:\\code\\Java\\Vcampus\\imgs\\";
//    ArrayList<Product> cart=new ArrayList<Product>();//选中的商品
    static ArrayList<buy> cartBuy = new ArrayList<>();//整理过的购物车（合并同类项、整合商品数量）
    int count = -1;

    class buy extends Product{
        private int num;//购买数量

        public void setNum(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }

    public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h){
//    	c.setMinimumSize(new Dimension(150, 400));
        constraints.gridx=x;
        constraints.gridy=y;
        constraints.ipadx = 10;
        constraints.ipady = 10;
        constraints.gridwidth=w;
        constraints.gridheight=h;
        add(c,constraints);
    }

    public ProductGUI(String str){
        client = new Client();
        Message message = new Message();
        message.setMessageType(Constants.shopQuery);
        Message serverResponse = client.sendRequestToServer(message);
        //if(message.getMessageType().equals(Constants.shopInit)) {
            products=(ArrayList<Product>)serverResponse.getData();
        //}

        this.setPreferredSize(new Dimension(50,100));
        GridBagLayout lay=new GridBagLayout();
        setLayout(lay);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,5,10,10);
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weighty = 1.0;

        class Product_Label extends JLabel{
            private int index;
            @Override
            public void setPreferredSize(Dimension preferredSize) {
            	// TODO Auto-generated method stub
            	super.setPreferredSize(preferredSize);
            }
            public int getIndex() { return index; }
            public void setIndex(int index) { this.index = index; }

        }

        if(str.equals("图书文具")) {
            for(int i=0;i<products.size();++i) {
                Product now = products.get(i);
                Product_Label label = new Product_Label();
                label.setIndex(count++);
                label.setIcon(new ImageIcon(path+now.getProductName()+".jpg"));
                label.setText(now.getProductName()+" ￥"+ now.getProductPrice());

                //设置文字位置
                label.setHorizontalTextPosition(SwingConstants.CENTER);
                label.setVerticalTextPosition(SwingConstants.BOTTOM);

                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Product choose = products.get(label.getIndex()+1);
                        boolean tag = false;//如购物车中已有该种商品，则改为true；否则为false

                        if(cartBuy!=null) {
                        	for(int i=0;i<cartBuy.size();i++) {
                        		//遍历 看新点击的商品是否已经添加过，如是则数量加一
                        		Product p = cartBuy.get(i);
                        		if (choose.getProductID() == p.getProductID()) {
                        			int temp = cartBuy.get(i).getNum();
                        			if(cartBuy.get(i).getStockNum() <= temp-1){
                        				JOptionPane.showMessageDialog
                                            (null,"库存不足添加失败！");
                        			}
                        			else
                        				cartBuy.get(i).setNum(temp + 1);
                        			tag = true;
                        			break;
                        		}
                        	}
                        }
                    	if(tag == false) {
                    		buy now = new buy();
                    		now.setProductID(choose.getProductID());
                    		now.setProductName(choose.getProductName());
                    		now.setProductPrice(choose.getProductPrice());
                    		now.setStockNum(choose.getStockNum());
                    		now.setNum(1);
                    		cartBuy.add(now);
                    	}
                    	JOptionPane.showMessageDialog(null,"添加成功");
                    }
                });
                add(label,constraints,count%4,count/4,1,1);
            }
        }
        
//		设置背景颜色
		Color bgColor = new Color(255, 255, 255);
		this.setBackground(bgColor);
//		设置字体颜色和大小
		for(int i = 0; i < this.getComponentCount(); i++){ 
			Font font = new Font("苹方 常规", Font.CENTER_BASELINE,15); 
			this.getComponent(i).setFont(font);
		}
//		this.setBorder(BorderFactory.createTitledBorder(""));
    }

    public ArrayList<buy> getCart(){ return cartBuy; }

}
