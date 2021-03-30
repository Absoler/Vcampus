package vo;

import java.io.Serializable;
import java.sql.Date;

public class Choose implements Serializable{
	private static final long serialVersionUID = 50000;
	private int courseID;
	private String courseName;
	//private Date signTime;
	private int studentID;
	private int teacherID;
	private String studentName;
	private String teacherName;
	public Choose() {
		// TODO Auto-generated constructor stub
		courseID=0;
		courseName="";
		studentID = 0;
		teacherID = 0;
		studentName="";
		teacherName="";
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
//	public Date getSignTime() {
//		return signTime;
//	}
//	public void setSignTime(Date signTime) {
//		this.signTime = signTime;
//	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}
