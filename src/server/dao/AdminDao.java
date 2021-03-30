package server.dao;

import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.Admin;

public interface AdminDao {
	boolean insert(Admin admin) throws RecordAlreadyExistException ;
	boolean delete(Admin admin ) throws RecordNotFoundException;
	boolean update(Admin admin ) throws RecordNotFoundException;
	Admin query(int adminID);

}
