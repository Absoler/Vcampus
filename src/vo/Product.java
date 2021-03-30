package vo;

import java.io.Serializable;

//一类商品，商店初始化时从服务端发向客户端Arraylist<Product>
public class Product implements Serializable {
	private static final long serialVersionUID = 50000;
	private String productName;
	private double productPrice;
	private int stockNum;
	private int productID;
//	private byte[] image;
	public Product()
	{
		this.setProductID(0);
		this.setProductName("");
		this.setProductPrice(0);
		this.setStockNum(0);
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getStockNum() {
		return stockNum;
	}
	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	
}
