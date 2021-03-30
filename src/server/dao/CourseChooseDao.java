package server.dao;

import java.util.ArrayList;

import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import vo.Choose;
import vo.Course;

public interface CourseChooseDao {
	boolean insert(Choose choose) throws RecordAlreadyExistException;
	boolean delete(Choose choose) throws RecordNotFoundException;
	boolean update(Choose choose) throws RecordNotFoundException;
	ArrayList<Choose> queryAll();
	ArrayList<Choose> query(int studentID);
}
