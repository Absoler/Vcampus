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

    //���빺�ﳵ����Ʒ����
    ArrayList<buy> totalCart = new ArrayList<buy>();
    double deposit;


    //��Ʒ�������
    JTree tree;
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("��Ʒ����");
    DefaultMutableTreeNode nd1 = new DefaultMutableTreeNode("��װ");
    DefaultMutableTreeNode nd2 = new DefaultMutableTreeNode("Ůװ");
    DefaultMutableTreeNode nd3 = new DefaultMutableTreeNode("������Ʒ");
    DefaultMutableTreeNode nd4 = new DefaultMutableTreeNode("ͼ���ľ�");
    DefaultMutableTreeNode nd5 = new DefaultMutableTreeNode("������ױ");
    DefaultMutableTreeNode nd6 = new DefaultMutableTreeNode("�˶�װ��");
    DefaultMutableTreeNode nd7 = new DefaultMutableTreeNode("�ֻ�����");
    DefaultMutableTreeNode nd8 = new DefaultMutableTreeNode("ҽҩ����");

    ShopGUI(User u){

        user = u;
        deposit = user.getDeposit();

        this.setOneTouchExpandable(false);
        this.setContinuousLayout(true);
        this.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.setDividerSize(3);
        this.setEnabled(false);

        //����Ϊ���������֣��²���������������
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

        //�ϲ����Ź��ܰ�ť
        JLabel showDep = new JLabel("��ʾ��"+deposit);
        JLabel space1 = new JLabel("               ");
        JButton depositB = new JButton("��ֵ���");
        JLabel space2 = new JLabel("               ");
        JTextField search = new JTextField(15);
        JButton searchB = new JButton("������Ʒ");
        JLabel space3 = new JLabel("               ");
        JLabel addToCart = new JLabel("���ͼƬ���빺�ﳵ");
        JButton cartB = new JButton("���ﳵ");

        pUP.add(showDep);
        pUP.add(space1);
        pUP.add(depositB);
        pUP.add(space2);
        pUP.add(search);
        pUP.add(searchB);
        pUP.add(space3);
        pUP.add(addToCart);
        pUP.add(cartB);


        //��������й�������������Ʒ�������
        JScrollPane pLEFT=new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pDOWN.setLeftComponent(pLEFT);

        tree=new JTree(root);
        pLEFT.setViewportView(tree);
        root.add(nd1); root.add(nd2);root.add(nd3); root.add(nd4);
        root.add(nd5); root.add(nd6);root.add(nd7); root.add(nd8);

        DefaultMutableTreeNode lfnd;
        //Ůװ
        lfnd = new DefaultMutableTreeNode("����");
        nd1.add(lfnd);
        lfnd = new DefaultMutableTreeNode("����");
        nd1.add(lfnd);
        lfnd = new DefaultMutableTreeNode("��ȹ");
        nd1.add(lfnd);
        lfnd = new DefaultMutableTreeNode("Ь��");
        nd1.add(lfnd);

        //��װ
        lfnd = new DefaultMutableTreeNode("����");
        nd2.add(lfnd);
        lfnd = new DefaultMutableTreeNode("����");
        nd2.add(lfnd);
        lfnd = new DefaultMutableTreeNode("����");
        nd2.add(lfnd);
        lfnd = new DefaultMutableTreeNode("Ь��");
        nd2.add(lfnd);

        //������Ʒ
        lfnd = new DefaultMutableTreeNode("������Ʒ");
        nd3.add(lfnd);
        lfnd = new DefaultMutableTreeNode("������Ʒ");
        nd3.add(lfnd);
        lfnd = new DefaultMutableTreeNode("�ҵ���Ʒ");
        nd3.add(lfnd);
        lfnd = new DefaultMutableTreeNode("�Ҿ�װ��");
        nd3.add(lfnd);
        lfnd = new DefaultMutableTreeNode("�ճ���Ʒ");
        nd3.add(lfnd);

        DefaultMutableTreeNode lfnd1;
        DefaultMutableTreeNode lfnd2;
        //ͼ���ľ�
        lfnd1 = new DefaultMutableTreeNode("ͼ��");
        nd4.add(lfnd1);
        lfnd2 = new DefaultMutableTreeNode("�ľ�");
        nd4.add(lfnd2);

        //������ױ
        lfnd = new DefaultMutableTreeNode("������Ʒ");
        nd5.add(lfnd);
        lfnd = new DefaultMutableTreeNode("��ױ����");
        nd5.add(lfnd);

        //�˶�װ��
        lfnd = new DefaultMutableTreeNode("�˶���");
        nd6.add(lfnd);
        lfnd = new DefaultMutableTreeNode("�˶�Ь");
        nd6.add(lfnd);
        lfnd = new DefaultMutableTreeNode("�˶����");
        nd6.add(lfnd);
        lfnd = new DefaultMutableTreeNode("�˶���е");
        nd6.add(lfnd);

        //�ֻ�����
        lfnd = new DefaultMutableTreeNode("�ֻ�");
        nd7.add(lfnd);
        lfnd = new DefaultMutableTreeNode("����");
        nd7.add(lfnd);
        lfnd = new DefaultMutableTreeNode("����");
        nd7.add(lfnd);
        lfnd = new DefaultMutableTreeNode("ƽ��");
        nd7.add(lfnd);
        lfnd = new DefaultMutableTreeNode("���");
        nd7.add(lfnd);
        lfnd = new DefaultMutableTreeNode("�����豸");
        nd7.add(lfnd);

        //ҽҩ����
        lfnd = new DefaultMutableTreeNode("Ӫ������");
        nd8.add(lfnd);
        lfnd = new DefaultMutableTreeNode("�ҳ�����ҩ");
        nd8.add(lfnd);


        //��ֵ�����¼�������
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

                JButton  ok = new JButton("ȷ��");
                JButton cancel = new JButton("ȡ��");
                JLabel balance = new JLabel("��ֵ��");
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
                            //�������˳�ֵ

                            Message mes = new Message();
                            mes.setUserId(user.getId());
                            mes.setMessageType(Constants.updateUserInfo);
                            mes.setUserType(user.getUserType());
                            deposit += bala;
                            user.setDeposit(deposit);

                            showDep.setText("��ʾ��"+deposit);
                            
                            mes.setData(user);
                            Message serverResponse = client.sendRequestToServer(mes);
                            
                            frame.dispose();
//                          ���Լ��쳣��Ӧ

                            

                        }
                    }
                });
            }
        });

        //������Ʒ���¼�������
        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = search.getText();
                if(!str.equals("")) {
                    ProductGUI goods = new ProductGUI(str);
                    pDOWN.setRightComponent(goods);
                    ArrayList<buy> c = goods.getCart();//��һ��c����нӸ��������ӵ����ﳵ�е���Ʒ
                    totalCart.addAll(c);
                }

            }
        });

        ProductGUI goods = new ProductGUI("ͼ���ľ�");
        JScrollPane slp = new JScrollPane(goods, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //���ﳵ��ť���¼�������
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
//                showDep.setText("��ʾ��"+deposit);

            }
        });

        
        
        //������ؽ����¼�������
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
                	//ProductGUI goods = new ProductGUI("ͼ���ľ�");
                    pDOWN.setRightComponent(slp);
                    
                    
                }
//                if(selectednd.equals(lfnd1)){
//                    productGUI goods = new productGUI("ͼ��");
//                    pDOWN.setRightComponent(goods);
//                    ArrayList<buy> c = goods.getCart();
//                    totalCart.addAll(c);
//                }
//                if(selectednd.equals(lfnd2)){
//                    productGUI goods = new productGUI("�ľ�");
//                    pDOWN.setRightComponent(goods);
//                    ArrayList<buy> c = goods.getCart();
//                    totalCart.addAll(c);
//                }
            }
        });
    }

}

