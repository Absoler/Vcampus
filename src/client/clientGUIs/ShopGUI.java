package client.clientGUIs;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.File;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.ArrayList;
import client.clientGUIs.ProductGUI.buy;

import util.*;
import client.util.Client;

public class ShopGUI extends JSplitPane {

    User user;
    Client client=new Client();

    //加入购物车的商品数组
    ArrayList<buy> totalCart = new ArrayList<buy>();
    double deposit;


    //商品分类的树
    JTree tree;
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("商品分类");
    DefaultMutableTreeNode nd1 = new DefaultMutableTreeNode("男装");
    DefaultMutableTreeNode nd2 = new DefaultMutableTreeNode("女装");
    DefaultMutableTreeNode nd3 = new DefaultMutableTreeNode("生活用品");
    DefaultMutableTreeNode nd4 = new DefaultMutableTreeNode("图书文具");
    DefaultMutableTreeNode nd5 = new DefaultMutableTreeNode("护肤彩妆");
    DefaultMutableTreeNode nd6 = new DefaultMutableTreeNode("运动装备");
    DefaultMutableTreeNode nd7 = new DefaultMutableTreeNode("手机数码");
    DefaultMutableTreeNode nd8 = new DefaultMutableTreeNode("医药健康");

    ShopGUI(User u){

        user = u;
        deposit = user.getDeposit();

        this.setOneTouchExpandable(false);
        this.setContinuousLayout(true);
        this.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.setDividerSize(3);
        this.setEnabled(false);

        //面板分为上下两部分，下部面板分左右两部分
        JPanel pUP = new JPanel();
        JSplitPane pDOWN = new JSplitPane();
        pDOWN.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        pDOWN.setOneTouchExpandable(false);
        pDOWN.setContinuousLayout(true);
        pDOWN.setDividerSize(3);
        pDOWN.setDividerLocation(150);
        pDOWN.setEnabled(true);

        this.setLeftComponent(pUP);
        this.setRightComponent(pDOWN);

        //上部面板放功能按钮
        JLabel showDep = new JLabel("显示余额："+deposit);
        JLabel space1 = new JLabel("               ");
        JButton depositB = new JButton("充值余额");
        JLabel space2 = new JLabel("               ");
        JTextField search = new JTextField(15);
        JButton searchB = new JButton("搜索商品");
        JLabel space3 = new JLabel("               ");
        JLabel addToCart = new JLabel("点击图片加入购物车");
        JButton cartB = new JButton("购物车");

        pUP.add(showDep);
        pUP.add(space1);
        pUP.add(depositB);
        pUP.add(space2);
        pUP.add(search);
        pUP.add(searchB);
        pUP.add(space3);
        pUP.add(addToCart);
        pUP.add(cartB);


        //下左面板有滚动条，放置商品分类的树
        JScrollPane pLEFT=new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pDOWN.setLeftComponent(pLEFT);

        tree=new JTree(root);
        pLEFT.setViewportView(tree);
        root.add(nd1); root.add(nd2);root.add(nd3); root.add(nd4);
        root.add(nd5); root.add(nd6);root.add(nd7); root.add(nd8);

        DefaultMutableTreeNode lfnd;
        //女装
        lfnd = new DefaultMutableTreeNode("外套");
        nd1.add(lfnd);
        lfnd = new DefaultMutableTreeNode("上衣");
        nd1.add(lfnd);
        lfnd = new DefaultMutableTreeNode("裤裙");
        nd1.add(lfnd);
        lfnd = new DefaultMutableTreeNode("鞋袜");
        nd1.add(lfnd);

        //男装
        lfnd = new DefaultMutableTreeNode("外套");
        nd2.add(lfnd);
        lfnd = new DefaultMutableTreeNode("上衣");
        nd2.add(lfnd);
        lfnd = new DefaultMutableTreeNode("裤子");
        nd2.add(lfnd);
        lfnd = new DefaultMutableTreeNode("鞋袜");
        nd2.add(lfnd);

        //生活用品
        lfnd = new DefaultMutableTreeNode("床上用品");
        nd3.add(lfnd);
        lfnd = new DefaultMutableTreeNode("厨卫用品");
        nd3.add(lfnd);
        lfnd = new DefaultMutableTreeNode("家电用品");
        nd3.add(lfnd);
        lfnd = new DefaultMutableTreeNode("家具装饰");
        nd3.add(lfnd);
        lfnd = new DefaultMutableTreeNode("日常用品");
        nd3.add(lfnd);

        DefaultMutableTreeNode lfnd1;
        DefaultMutableTreeNode lfnd2;
        //图书文具
        lfnd1 = new DefaultMutableTreeNode("图书");
        nd4.add(lfnd1);
        lfnd2 = new DefaultMutableTreeNode("文具");
        nd4.add(lfnd2);

        //护肤彩妆
        lfnd = new DefaultMutableTreeNode("护肤用品");
        nd5.add(lfnd);
        lfnd = new DefaultMutableTreeNode("彩妆美甲");
        nd5.add(lfnd);

        //运动装备
        lfnd = new DefaultMutableTreeNode("运动服");
        nd6.add(lfnd);
        lfnd = new DefaultMutableTreeNode("运动鞋");
        nd6.add(lfnd);
        lfnd = new DefaultMutableTreeNode("运动配件");
        nd6.add(lfnd);
        lfnd = new DefaultMutableTreeNode("运动器械");
        nd6.add(lfnd);

        //手机数码
        lfnd = new DefaultMutableTreeNode("手机");
        nd7.add(lfnd);
        lfnd = new DefaultMutableTreeNode("耳机");
        nd7.add(lfnd);
        lfnd = new DefaultMutableTreeNode("电脑");
        nd7.add(lfnd);
        lfnd = new DefaultMutableTreeNode("平板");
        nd7.add(lfnd);
        lfnd = new DefaultMutableTreeNode("相机");
        nd7.add(lfnd);
        lfnd = new DefaultMutableTreeNode("智能设备");
        nd7.add(lfnd);

        //医药健康
        lfnd = new DefaultMutableTreeNode("营养保健");
        nd8.add(lfnd);
        lfnd = new DefaultMutableTreeNode("家常备用药");
        nd8.add(lfnd);


        //充值余额的事件处理函数
        depositB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                JFrame frame=new JFrame();
                JPanel p = new JPanel();
                frame.setVisible(true);
                frame.setContentPane(p);
                frame.setSize(200,100);
                frame.setLocation( (screenSize.width - frame.getWidth()) / 2,
                        (screenSize.height - frame.getHeight()) / 2);

                JButton  ok = new JButton("确定");
                JButton cancel = new JButton("取消");
                JLabel balance = new JLabel("充值金额：");
                JTextField bal = new JTextField(10);

                p.add(balance);
                p.add(bal);
                p.add(ok);
                p.add(cancel);

                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });

                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = bal.getText();
                        if(str!=null) {
                            double bala = Double.parseDouble(str);
                            //服务器端充值

                            Message mes = new Message();
                            mes.setUserId(user.getId());
                            mes.setMessageType(Constants.updateUserInfo);
                            mes.setUserType(user.getUserType());
                            deposit += bala;
                            user.setDeposit(deposit);

                            showDep.setText("显示余额："+deposit);
                            
                            mes.setData(user);
                            Message serverResponse = client.sendRequestToServer(mes);
                            
                            frame.dispose();
//                          可以加异常响应

                            

                        }
                    }
                });
            }
        });

        //搜索商品的事件处理函数
        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = search.getText();
                if(!str.equals("")) {
                    ProductGUI goods = new ProductGUI(str);
                    pDOWN.setRightComponent(goods);
                    ArrayList<buy> c = goods.getCart();//用一个c数组承接该面板上添加到购物车中的商品
                    totalCart.addAll(c);
                }

            }
        });

        ProductGUI goods = new ProductGUI("图书文具");
        JScrollPane slp = new JScrollPane(goods, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //购物车按钮的事件处理函数
        cartB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ArrayList<buy> c = goods.getCart();
//            	for(buy c1:c) {
//            		for(buy c2:totalCart) {
//            			if(c1.getProductName().equals(c2.getProductName()))
//            				c2.setNum(c1.getNum());
//            		}
//            	}
//                totalCart.addAll(c);
            	totalCart=c;
                CartGUI cart = new CartGUI(user,client,totalCart,showDep);
                
//                boolean judge = cart.getSuccess();
//                if(judge == true) {
//                	double totalExp = cart.getTotalExpenditure();
//                	deposit -= totalExp;
//                }
//                showDep.setText("显示余额："+deposit);

            }
        });

        
        
        //树中相关结点的事件处理函数
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectednd= (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
                if(selectednd.equals(nd4)){
//                    Message mes = new Message();
//                    mes.setMessageType(Constants.shopQuery);
//                    mes.setUserId(user.getId());
//                    mes.setUserType(user.getUserType());
//                    Message serverResponse = client.sendRequestToServer(mes);
//
                	//ProductGUI goods = new ProductGUI("图书文具");
                    pDOWN.setRightComponent(slp);
                    
                    
                }
//                if(selectednd.equals(lfnd1)){
//                    productGUI goods = new productGUI("图书");
//                    pDOWN.setRightComponent(goods);
//                    ArrayList<buy> c = goods.getCart();
//                    totalCart.addAll(c);
//                }
//                if(selectednd.equals(lfnd2)){
//                    productGUI goods = new productGUI("文具");
//                    pDOWN.setRightComponent(goods);
//                    ArrayList<buy> c = goods.getCart();
//                    totalCart.addAll(c);
//                }
            }
        });
    }

}

