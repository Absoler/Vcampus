package vo;

import java.io.Serializable;

//在客户端先判断余额是否足够，
public class Purchase implements Serializable 
{
	private static final long serialVersionUID = 50000;
	private String productName;//商品名
	private int userId;	//购买人
	private int userType;//用户类型
	private int buyNum;//购买数量
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	private int num;	//购买数量
}
