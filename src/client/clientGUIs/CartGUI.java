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

    //���ﳵ����Ʒ����
    private int totalNum;
    //�ܼƽ��
    private static double totalExpenditure ;
    //��ʾ�ܼƽ��
    static JLabel totalExp = new JLabel();
    // ��������ť
    JButton buyButton = new JButton("ȷ�Ϲ���");
    //����ɾ����ť
    JButton delete = new JButton("ɾ��");
    // ����ȡ����ť
    JButton closeButton = new JButton("ȡ��");

    JTable table=new JTable();

//	��дButtonRenderer
//    class ButtonRenderer implements TableCellRenderer {
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value,
//                                                       boolean isSelected, boolean hasFocus, int row, int column) {
//            JButton button = (JButton)value;
//            button.setText("ɾ��");
//            return button;
//        }
//    }
//
//    //	��дButtonEditor
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
//            button.setText("�Ƴ�");
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
//    //	�̳�AbstractTableModel
//    public static class newJTableModel extends AbstractTableModel {
//    	public newJTableModel(ArrayList<buy> c) {
//			// TODO Auto-generated constructor stub
//    		cartBuy = c;
//		}
//    	private ArrayList<buy> cartBuy;
//        private static final long serialVersionUID = 1L;
//        private static final String[] COLUMN_NAMES =
//                new String[] { "��Ʒ", "����", "����" ,"�Ƴ�"};
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
////                            totalExp.setText("�ܼƣ�" + totalExpenditure);
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

        // �������
        String[] COLUMN_NAMES =
              new String[] { "��Ʒ", "����", "����" };
        String[][] VALUES=new String[cartBuy.size()][3];
        for(int i=0;i<cartBuy.size();i++) {
        	VALUES[i][0]=cartBuy.get(i).getProductName();
        	VALUES[i][1]=String.valueOf(cartBuy.get(i).getProductPrice());
        	VALUES[i][2]=String.valueOf(cartBuy.get(i).getNum());
        }
        DefaultTableModel model = new DefaultTableModel(VALUES,COLUMN_NAMES);
        table.setModel(model);
        table = new JTable(model);
//        table.getColumn("�Ƴ�").setCellEditor(new ButtonEditor());
//        table.getColumn("�Ƴ�").setCellRenderer(new ButtonRenderer());
        // �����������Ĺ�������
        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(0,0,300,550);
//        scrollPane.setSize(400,550);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        // ���� panel �Ĳ���Ϊ BoxLayout��BoxLayout Ϊ��ֱ����
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
//        // �ȼ���һ�����ɼ��� Strut���Ӷ�ʹ panel �Զ�������һ���Ŀռ�
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
        totalExp.setText("�ܼƣ�" + totalExpenditure);
        // ����������Ĺ�������
        panel.add(scrollPane);
        // �ټ���һ�����ɼ��� Strut���Ӷ�ʹ panel �� middlePanel ֮������һ���Ŀռ�
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
					totalExp.setText("�ܼƣ�" + totalExpenditure);
					model.removeRow(row);
				}
				
			}
		});
        
        return  panel;
    }

//	public JPanel createBottomPanel(ArrayList<buy> cartBuy) {
//        // ���� bottomPanel
//        JPanel bottomPanel = new JPanel();
//        // ���� bottomPanel Ϊ��ֱ����
//        bottomPanel .setLayout(new BoxLayout(bottomPanel,BoxLayout.Y_AXIS ));
//        // ��������һ��label������button�� Panel
//        JPanel panel = new JPanel();
//        // ���� bottomPanel Ϊˮƽ����
//        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS ));
//
//        //���ܼƵ�label���뵽panel
////        panel.add(totalExp);
//        //����һ�� glue, glue �ἷռ������ť֮��Ŀռ�
////        panel.add(Box.createHorizontalGlue ());
//        // ��ȷ�Ϲ���ť���뵽 panel
//        panel.add(buyButton);
//        //����һ�� glue, glue �ἷռ������ť֮��Ŀռ�
//        panel.add(Box.createHorizontalGlue ());
//        // ��ȡ����ť���뵽 panel
//        panel.add(closeButton);
////        panel.add(Box.createHorizontalGlue());
////        panel.add(delete);
//
//        // ����һ�� Strut���Ӷ�ʹ bottomPanel �� middlePanel ����֮����������
//        bottomPanel .add(Box.createVerticalStrut (10));
//        // ���� panel
//        bottomPanel .add(panel);
//        // ����һ�� Strut���Ӷ�ʹ bottomPanel �͵ײ�֮����������
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
        this.setTitle("���ﳵ");
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
            	
                //��������  ��Ǯ  �����
                if(user.getDeposit()<totalExpenditure){
                    JOptionPane.showMessageDialog(null,"���㣬����ʧ�ܣ�");
//                    System.out.println(user.getDeposit());
//                    System.out.println(totalExpenditure);
                }
                else {
                	user.setDeposit(user.getDeposit() - totalExpenditure);
                	showDep.setText("��ʾ��"+user.getDeposit());
                	
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
                    	JOptionPane.showMessageDialog(null,"����ɹ���");
                        cartBuy.clear();
                        totalExp.setText("�ܼƣ�0.00");
                    }else {
                    	JOptionPane.showMessageDialog(null,"����ʧ��");
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