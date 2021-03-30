package vo;

import java.io.Serializable;

public class Register implements Serializable {
	private static final long serialVersionUID = 50000;
	private String nickName;
	private String pwd;
	private boolean userType;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isUserType() {
		return userType;
	}
	public void setUserType(boolean userType) {
		this.userType = userType;
	}
	
}
