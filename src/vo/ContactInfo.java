package vo;

import java.io.Serializable;
import java.util.ArrayList;

public class ContactInfo implements Serializable {
	private static final long serialVersionUID = 50000;
	private String name;
	private int id;
	private int userType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
