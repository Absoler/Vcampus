package server.dao;


import java.util.ArrayList;

import server.exception.AdminException;
import server.exception.NoAuthorityException;
import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.User;
import vo.Book;
import vo.BookInfo;


public interface LibraryDao {
	boolean insert(Book book) throws RecordAlreadyExistException;
	boolean delete(Book book) throws RecordNotFoundException;
	boolean update(Book book)throws RecordNotFoundException;
	boolean borrow(int id,int type,String bookName)throws OutOfLimitException,RecordNotFoundException,AdminException,NoAuthorityException;
	boolean returnBook(int id,int type,String bookName) throws RecordNotFoundException,AdminException;
	boolean returnAllBook(User user,int type) throws RecordNotFoundException,AdminException;
	ArrayList<Book> queryByUser(int userId,int userType);
	ArrayList<Book> query(String bookName);
	ArrayList<Book> query();
	ArrayList<BookInfo> queryAll();
	Book queryById(int id);

}
