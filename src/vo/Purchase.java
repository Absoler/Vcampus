package vo;

import java.io.Serializable;

//�ڿͻ������ж�����Ƿ��㹻��
public class Purchase implements Serializable {
	private String productName;
	private int userId;	//������
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
	private int num;	//��������
}
