package vo;

import java.io.Serializable;

public class Restore implements Serializable{
	private static final long serialVersionUID = 50000;
	private int addStockNum;//���ӵ���Ʒ����
	private String proName;//��Ʒ������
	private double proPrice;	//��Ʒ�۸�
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	public int getAddStockNum() {
		return addStockNum;
	}
	public void setAddStockNum(int addStockNum) {
		this.addStockNum = addStockNum;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	

}
