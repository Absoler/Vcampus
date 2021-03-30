package server.biz;

import java.util.ArrayList;

import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import vo.Product;

/**
 * ÉÌµê·þÎñ
 * @author 24989
 *
 */
public interface ShopService {
	public ArrayList<Product> listProducts();
	public Product query(String pdName);
	public boolean buyProduct(String pdName,int id,int userType,int num) throws RecordNotFoundException,OutOfLimitException;
	public boolean addProduct(Product pd) throws RecordAlreadyExistException,OutOfLimitException;
	public boolean restockProduct(String pdName,int num,double price) throws RecordNotFoundException,OutOfLimitException;
	public boolean deleteProduct(Product pd) throws RecordNotFoundException;

}
