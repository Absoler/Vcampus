package vo;

import java.io.Serializable;

public class Borrow implements Serializable {
	private String bookName;
	private int id;
	private boolean userType;
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
	public boolean isUserType() {
		return userType;
	}
	public void setUserType(boolean userType) {
		this.userType = userType;
	}

}