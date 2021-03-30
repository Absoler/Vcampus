package vo;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = 50000;
	private String bookName;
	private String bookAuthor;
	private String publisher;
	private boolean isBorrowed;
	private int userType;
	private int id;
	private int userId;
	
//	private int stockNum;
//	private byte[] image;
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
//	public int getStockNum() {
//		return stockNum;
//	}
//	public void setStockNum(int stockNum) {
//		this.stockNum = stockNum;
//	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isBorrowed() {
		return isBorrowed;
	}
	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
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
