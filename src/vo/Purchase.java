package vo;

import java.io.Serializable;

//�ڿͻ������ж�����Ƿ��㹻��
public class Purchase implements Serializable 
{
	private static final long serialVersionUID = 50000;
	private String productName;//��Ʒ��
	private int userId;	//������
	private int userType;//�û�����
	private int buyNum;//��������
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
	private int num;	//��������
}
