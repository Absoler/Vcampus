package client.clientGUIs;

import client.util.Client;
import client.clientGUIs.ProductGUI.buy;

import javax.management.modelmbean.ModelMBean;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

import java.util.ArrayList;
import util.*;
import vo.Purchase;

import java.awt.Component;
import javax.swing.table.TableCellRenderer;


public class CartGUI extends JFrame {

    User user;
    Client client;
    ArrayList <Purchase> purchase;
    
//    boolean success;

    //购物车中物品总数
    private int totalNum;
    //总计金额
    private static double totalExpenditure ;
    //显示总计金额
    static JLabel totalExp = new JLabel();
    // 创建购买按钮
    JButton buyButton = new JButton("确认购买");
    //创建删除按钮
    JButton delete = new JButton("删除");
    // 创建取消按钮
    JButton closeButton = new JButton("取消");

    JTable table=new JTable();

//	重写ButtonRenderer
//    class ButtonRenderer implements TableCellRenderer {
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value,
//                                                       boolean isSelected, boolean hasFocus, int row, int column) {
//            JButton button = (JButton)value;
//            button.setText("删除");
//            return button;
//        }
//    }
//
//    //	重写ButtonEditor
//    class ButtonEditor extends DefaultCellEditor {
//        private static final long serialVersion =  -6546334664166791132L;
//        public ButtonEditor() {
//            super(new JTextField());
//            this.setClickCountToStart(1);
//        }
//
//        @Override
//        public Component getTableCellEditorComponent(JTable table, Object value,
//                                                     boolean isSelected, int row, int column) {
//            JButton button = (JButton)value;
//            button.setText("移除");
//            button.addActionListener(new AbstractAction() {
//                private static final long serialVersionUID = 1L;
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
////                    System.out.println("edit!!!!");
//                }
//            });
//            return button;
//        }
//    }
//
//    //	继承AbstractTableModel
//    public static class newJTableModel extends AbstractTableModel {
//    	public newJTableModel(ArrayList<buy> c) {
//			// TODO Auto-generated constructor stub
//    		cartBuy = c;
//		}
//    	private ArrayList<buy> cartBuy;
//        private static final long serialVersionUID = 1L;
//        private static final String[] COLUMN_NAMES =
//                new String[] { "商品", "单价", "数量" ,"移除"};
//        private static final Class<?>[] COLUMN_TYPES =
//                new Class<?>[] {String.class, Double.class, Integer.class, JButton.class};
//
//        @Override public int getColumnCount() {
//            return COLUMN_NAMES.length;
//        }
//
//        @Override public int getRowCount() {
//            return cartBuy.size();
//        }
//
//        @Override public String getColumnName(int columnIndex) {
//            return COLUMN_NAMES[columnIndex];
//        }
//
//        @Override public Class<?> getColumnClass(int columnIndex) {
//            return COLUMN_TYPES[columnIndex];
//        }
//
//        @Override public Object getValueAt(final int rowIndex, final int columnIndex) {
//            /*Adding components*/
//            switch (columnIndex) {
//                case 0:
//                    return cartBuy.get(rowIndex).getProductName();
//
//                case 1:
//                    return cartBuy.get(rowIndex).getProductPrice();
//
//                case 2: // fall through
//                    /*Adding button and creating click listener*/
//                    return cartBuy.get(rowIndex).getNum();
//
//                case 3:
//                    final JButton button = new JButton(COLUMN_NAMES[columnIndex]);
//                    button.addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent arg0) {
////                            cartBuy.remove(rowIndex);
////                            DefaultTableModel tmp=(DefaultTableModel)(table.getModel());
////                            tmp.removeRow(rowIndex);
//                          
////                            totalExpenditure -= cartBuy.get(rowIndex).getProductPrice() * cartBuy.get(rowIndex).getNum();
////                            totalExp.setText("总计：" + totalExpenditure);
//                        }
//                    });
//                    return button;
//
//                default: return "Error";
//            }
//        }
//
//        @Override
//        public boolean isCellEditable(int rowIndex, int columnIndex) {
//            switch (columnIndex) {
//
//                case 0:
//                    return false;
//                case 1:
//                    return false;
//                case 2:
//                    return true;
//                case 3:
//                    return false;
//                default:
//                    return false;
//
//            }
//        }
//    }
//


    public JPanel createPanel(ArrayList<buy> cartBuy) {
    	
        JPanel panel = new JPanel();

        // 创建表格
        String[] COLUMN_NAMES =
              new String[] { "商品", "单价", "数量" };
        String[][] VALUES=new String[cartBuy.size()][3];
        for(int i=0;i<cartBuy.size();i++) {
        	VALUES[i][0]=cartBuy.get(i).getProductName();
        	VALUES[i][1]=String.valueOf(cartBuy.get(i).getProductPrice());
        	VALUES[i][2]=String.valueOf(cartBuy.get(i).getNum());
        }
        DefaultTableModel model = new DefaultTableModel(VALUES,COLUMN_NAMES);
        table.setModel(model);
        table = new JTable(model);
//        table.getColumn("移除").setCellEditor(new ButtonEditor());
//        table.getColumn("移除").setCellRenderer(new ButtonRenderer());
        // 创建包含表格的滚动窗格
        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(0,0,300,550);
//        scrollPane.setSize(400,550);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        // 定义 panel 的布局为 BoxLayout，BoxLayout 为垂直排列
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
//        // 先加入一个不可见的 Strut，从而使 panel 对顶部留出一定的空间
//        panel.add(Box.createVerticalStrut(20));

//        scrollPane.setLocation(0,10);
//        totalExp.setLocation(50,560);
//        buyButton.setLocation(150,560);
//        delete.setLocation(250,560);
//        closeButton.setLocation(350,560);

        panel.add(scrollPane);
        panel.add(buyButton);
        panel.add(closeButton);
        panel.add(delete);
        panel.add(totalExp);
        
        totalExpenditure = 0;
        for(int i = 0; i < cartBuy.size(); i++){
            totalExpenditure +=
                    cartBuy.get(i).getProductPrice() * cartBuy.get(i).getNum();
        }

        
        totalExp.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        delete.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        totalExp.setText("总计：" + totalExpenditure);
        // 加入包含表格的滚动窗格
        panel.add(scrollPane);
        // 再加入一个不可见的 Strut，从而使 panel 和 middlePanel 之间留出一定的空间
        panel.add(Box.createVerticalStrut(10));
        
        delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String name = (String)table.getValueAt(row, 0);
				if(row!=-1) {
//					for(buy b:cartBuy) {
//						if(b.getProductName().equals(name)) {	
//							cartBuy.remove(b);
//						}
//					}
					totalExpenditure-=(double)(Double.parseDouble((String)table.getValueAt(row, 1))*Double.parseDouble((String)table.getValueAt(row, 2)));
					totalExp.setText("总计：" + totalExpenditure);
					model.removeRow(row);
				}
				
			}
		});
        
        return  panel;
    }

//	public JPanel createBottomPanel(ArrayList<buy> cartBuy) {
//        // 创建 bottomPanel
//        JPanel bottomPanel = new JPanel();
//        // 设置 bottomPanel 为垂直布局
//        bottomPanel .setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS ));
//        // 创建包含一个label和两个button的 Panel
//        JPanel panel = new JPanel();
//        // 设置 bottomPanel 为水平布局
//        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS ));
//
//        //将总计的label加入到panel
////        panel.add(totalExp);
//        //加入一个 glue, glue 会挤占两个按钮之间的空间
////        panel.add(Box.createHorizontalGlue ());
//        // 将确认购买按钮加入到 panel
//        panel.add(buyButton);
//        //加入一个 glue, glue 会挤占两个按钮之间的空间
//        panel.add(Box.createHorizontalGlue ());
//        // 将取消按钮加入到 panel
//        panel.add(closeButton);
////        panel.add(Box.createHorizontalGlue());
////        panel.add(delete);
//
//        // 加入一个 Strut，从而使 bottomPanel 和 middlePanel 上下之间留出距离
//        bottomPanel .add(Box.createVerticalStrut (10));
//        // 加入 panel
//        bottomPanel .add(panel);
//        // 加入一个 Strut，从而使 bottomPanel 和底部之间留出距离
//        bottomPanel .add(Box.createVerticalStrut (10));
//
//        return  bottomPanel;
//    }

//    public double getTotalExpenditure(){ return totalExpenditure; }

//    public boolean getSuccess() { return success; }

    public CartGUI(User u,Client c, ArrayList<buy> cartBuy, JLabel showDep){

        user = u;
        client = c;
        totalNum = cartBuy.size();
//        success = false;

        JPanel p = createPanel(cartBuy);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("购物车");
        this.setVisible(true);
        this.setContentPane(p);
        this.setSize(490, 530);
        this.setLocation((screenSize.width - this.getWidth()) / 2,
                (screenSize.height - this.getHeight()) / 2);


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
//            	success = true;
            	
                //服务器端  扣钱  减库存
                if(user.getDeposit()<totalExpenditure){
                    JOptionPane.showMessageDialog(null,"余额不足，购买失败！");
//                    System.out.println(user.getDeposit());
//                    System.out.println(totalExpenditure);
                }
                else {
                	user.setDeposit(user.getDeposit() - totalExpenditure);
                	showDep.setText("显示余额："+user.getDeposit());
                	
                    Message mes = new Message();
                    mes.setUserId(user.getId());
                    mes.setUserType(user.getUserType());
                    mes.setMessageType(Constants.Buy);
                    ArrayList<Purchase>ccc=new ArrayList<Purchase>();
                    
                    for(buy b:cartBuy) {
                    	Purchase p=new Purchase();
                    	p.setBuyNum(b.getNum());
                    	p.setProductName(b.getProductName());
                    	p.setUserId(user.getId());
                    	p.setUserType(user.getUserType());
                    	ccc.add(p);
                    }
                    	
                    mes.setData(ccc);
                    Message severResponse = client.sendRequestToServer(mes);
                    if(severResponse.isLastOperState()) {
                    	JOptionPane.showMessageDialog(null,"购买成功！");
                        cartBuy.clear();
                        totalExp.setText("总计：0.00");
                    }else {
                    	JOptionPane.showMessageDialog(null,"购买失败");
                    }
                    

                    dispose();
                }
//                try{
//                    Thread.sleep(1500);
//                }catch(InterruptedException ie){ }
//                frame.dispose();
            }
        });
    }
}