package vo;

import java.io.Serializable;

public class Borrow implements Serializable {
	private static final long serialVersionUID = 50000;
	private String bookName;
	private int id;
	private int userType;
	//�ͻ������жϣ�����û������������ޣ���ֱ���ڿͻ��˾ܾ����󣻷��͸��������˵�����Ĳ����ǺϷ��ģ�����˲����ж�
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	

}
