package util;

import java.io.Serializable;

public class Teacher extends User implements Serializable {
	private static final long serialVersionUID = 50000;
	private String title;
	public Teacher()
	{
		this.setAge(0);
		this.setBorrowNum(0);
		this.setDeposit(0);
		this.setId(0);
		this.setNickName("");
		this.setPwd("");
		this.setRealName("");
		this.setSex("");
		this.setTitle("");
		this.setUserType(1);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isUserType() {
		return userType;
	}
	private final boolean userType = true;
}
