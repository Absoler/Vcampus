package vo;

import java.io.Serializable;

public class Restore implements Serializable{
	private static final long serialVersionUID = 50000;
	private int addStockNum;//增加的商品数量
	private String proName;//商品的名字
	private double proPrice;	//商品价格
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
