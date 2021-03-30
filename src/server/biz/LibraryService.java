package server.biz;

import java.util.ArrayList;

import server.exception.NoAuthorityException;
import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import vo.Book;
import vo.BookInfo;

/**ͼ��������
 * 
 * @author 24989
 *
 */
public interface LibraryService {
	public ArrayList<BookInfo> listBook();//����������
	public ArrayList<Book> queryBook(String bookName) throws RecordNotFoundException;//����һ������б�
	boolean borrowBook(String bookName, int id,int userType) throws RecordNotFoundException,OutOfLimitException;
	boolean returnBook(String bookName,int id,int userType) throws RecordNotFoundException;
	boolean addBook(Book book) throws RecordAlreadyExistException;
	public ArrayList<Book> listBorrowed(int userID,int userType);
	boolean deleteBook(Book book) throws RecordNotFoundException,NoAuthorityException;

}
