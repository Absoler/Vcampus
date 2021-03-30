package server.dao;

import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.Teacher;

public interface TeacherDao {
	boolean insert(Teacher teacher) throws RecordAlreadyExistException;
	boolean delete(Teacher teacher) throws RecordNotFoundException;
	boolean update(Teacher teacher) throws RecordNotFoundException;
	Teacher query(int TeacherID);
}
