package server.biz;

import java.util.ArrayList;

import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import server.exception.TimeClashException;
import util.Student;
import vo.Choose;
import vo.Course;

public interface ChooseCourseService {
	ArrayList<Course> queryCourseList(int studentID);
	ArrayList<Course> queryAllClasses();
	boolean addClass(Choose choose) throws OutOfLimitException,RecordNotFoundException,RecordAlreadyExistException,TimeClashException;
	boolean returnClass(Choose choose) throws RecordNotFoundException;	

}
