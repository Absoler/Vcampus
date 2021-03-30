package util;

import java.io.Serializable;

public class Student extends User implements Serializable {
	private static final long serialVersionUID = 50000;
	private double GPA;
	private String dormNum;
	private String punishment;
	private double SRTP;
	//private String punish;
	public Student()
	{
		this.setAge(0);
		this.setBorrowNum(0);
		this.setDeposit(0);
		this.setDormNum("");
		this.setGPA(0);
		this.setId(0);
		this.setNickName("");
		this.setPwd("");
		this.setSex("");
		this.setUserType(0);
		this.setRealName("");
		this.setPunishment("");
	}
	private final boolean userType = false;	//Ê¼ÖÕÎª0
	public double getGPA() {
		return GPA;
	}
	public void setGPA(double gPA) {
		GPA = gPA;
	}
	public String getDormNum() {
		return dormNum;
	}
	public void setDormNum(String dormNum) {
		this.dormNum = dormNum;
	}
	public boolean isUserType() {
		return userType;
	}
	public String getPunishment() {
		return punishment;
	}
	public void setPunishment(String punishment) {
		this.punishment = punishment;
	}
	public double getSRTP() {
		return SRTP;
	}
	public void setSRTP(double sRTP) {
		SRTP = sRTP;
	}
}
