package server.dao;

import java.util.ArrayList;

import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.Student;

public interface StudentDao {
	boolean insert(Student student) throws RecordAlreadyExistException ;
	boolean delete(Student student) throws RecordNotFoundException;
	boolean update(Student student) throws RecordNotFoundException;
	ArrayList<Student> queryAll();
	Student query(int studentID);
	

}
