package server.dao;

import java.util.ArrayList;

import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import server.exception.TimeClashException;
import vo.Course;

public interface CourseDao {
	boolean insert(Course course) throws RecordAlreadyExistException,TimeClashException,OutOfLimitException;
	boolean delete(Course course) throws RecordNotFoundException;
	boolean update(Course course) throws RecordNotFoundException,OutOfLimitException,TimeClashException;
	ArrayList<Course> query(String courseName);
	ArrayList<Course> queryAll();
	ArrayList<Course> query(int teacherID);
	Course queryOne(int courseID);

}
