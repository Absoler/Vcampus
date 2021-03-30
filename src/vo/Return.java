package vo;

import java.io.Serializable;

public class Return implements Serializable{
	private static final long serialVersionUID = 50000;
	private String bookName;
	private int userId;
	private int userType;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
}
