package server.dao;

import java.util.ArrayList;

import server.exception.AdminException;
import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.User;
import vo.Product;

public interface ShopDao {
	boolean insert(Product product) throws RecordAlreadyExistException,OutOfLimitException;
	boolean delete(Product product) throws RecordNotFoundException;
	boolean buyProduct(int userID,int type,String proName, int num) throws RecordNotFoundException,OutOfLimitException;
	boolean update(Product product) throws RecordNotFoundException;
	Product query(int id);
	ArrayList<Product> queryAll();
	Product query(String name);
}
