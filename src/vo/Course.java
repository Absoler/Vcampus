package vo;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.crypto.Data;

public class Course implements Serializable{
	private static final long serialVersionUID = 50000;
	private int teacherId;//����ID
	private String teacherName;//��������
	private int[] time= {0,0,0};//ÿ��ʱ��
	private int courseID;//�γ�ID
	private String courseName;//�γ�����
	private String insName;//����ѧԺ����
	private double credit;//ѧ��
	private int courseHours;//�ܿ�ʱ
	private String startDate;//��ʼ����
	private String endDate;//��������
	private String classRoom;//�Ͽν���
	private int limitNum;//��ѡ����
	private int currentNum;//��ѡ����
	private int timePerWeek;//ÿ�ܿγ���
	private int time1;//
	private int time2;//
	private int time3;//�Ͽ�ʱ��
	
	
	
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int[] getTime() {
		int a[] = {time1,time2,time3};
		return a;
	}
	public void setTime(int[] time) {
		this.time = time;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public int getCourseHours() {
		return courseHours;
	}
	public void setCourseHours(int courseHours) {
		this.courseHours = courseHours;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEnDate() {
		return endDate;
	}
	public void setEnDate(String enDate) {
		this.endDate = enDate;
	}
	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	public int getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
	
	public int getTimePerWeek() {
		return timePerWeek;
	}
	public void setTimePerWeek(int timePerWeek) {
		this.timePerWeek = timePerWeek;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getTime1() {
		return time1;
	}
	public void setTime1(int time1) {
		this.time1 = time1;
	}
	public int getTime2() {
		return time2;
	}
	public void setTime2(int time2) {
		this.time2 = time2;
	}
	public int getTime3() {
		return time3;
	}
	public void setTime3(int time3) {
		this.time3 = time3;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	
}
