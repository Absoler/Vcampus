package server.biz;

import java.util.ArrayList;
import java.util.Random;

import server.dao.LibraryDaoImpl;
import server.exception.AdminException;
import server.exception.NoAuthorityException;
import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import vo.Book;
import vo.BookInfo;

public class LibraryServiceImpl implements LibraryService{
	LibraryDaoImpl lb = new LibraryDaoImpl();
	

	@Override
	public ArrayList<BookInfo> listBook() {
		// TODO Auto-generated method stub
		return lb.queryAll();
	}

	@Override
	public ArrayList<Book> queryBook(String bookName)  
	{
		// TODO Auto-generated method stub
		ArrayList<Book> ls=new ArrayList<Book>();
		ArrayList<Book> l2=lb.query(bookName);
		if(l2!=null)
		{
			for(Book b:l2)
			{
				if(!b.isBorrowed())
					ls.add(b);
			}
		}
		return ls;
	}

	@Override
	public boolean borrowBook(String bookName, int id, int userType)
			throws RecordNotFoundException, OutOfLimitException 
	{
		
			try {
				lb.borrow(id, userType, bookName);
			} catch (NoAuthorityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AdminException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean returnBook(String bookName, int id, int userType) throws RecordNotFoundException 
	{
		// TODO Auto-generated method stub
		try {
			lb.returnBook(id, userType, bookName);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean addBook(Book book) throws RecordAlreadyExistException 
	{
		// TODO Auto-generated method stub
		
			int r;
			do {
				Random random=new Random();
				r = random.nextInt(1000000)+1;
			} while (lb.queryById(r)!=null);
			book.setId(r);
			lb.insert(book);
		return true;
	}
	
	
	
	public static void main(String[] args) {
		LibraryServiceImpl lbs=new LibraryServiceImpl();
		//������������
		Book b=new Book();
		b.setBookAuthor("���");
		b.setBookName("��������");
		b.setBorrowed(false);
		b.setId(258);
		b.setPublisher("������ѧ������");
		b.setUserId(0);
		b.setUserType(-1);
//		try {
//			lbs.addBook(b);
//		} catch (RecordAlreadyExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//���鹦�ܲ���
//		try {
//			lbs.borrowBook("��������", 213170240, 0);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OutOfLimitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//���鹦�ܲ���
		try {
			lbs.returnBook("��������", 213170240, 0);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//�����鼮��Ϣ�����
//		ArrayList<BookInfo> ls=lbs.listBook();
//		if(ls!=null)
//		{
//			for(BookInfo b1:ls)
//			{
//				System.out.println(b1.getBookName()+" "+b1.getStockNum());
//			}
//		}
//		//�����鼮����
//		ArrayList<Book> lst=lbs.queryBook("ս�����ƽ");
//		if(lst!=null)
//		{
//			for(Book b1:lst)
//			{
//				System.out.println(b1.getId());
//			}
//		}
		//ɾ���鼮���ܲ���
		try {
			lbs.deleteBook(b);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoAuthorityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean deleteBook(Book book) throws RecordNotFoundException,NoAuthorityException
	{
		// TODO Auto-generated method stub
		Book b=lb.queryById(book.getId());
		if(b==null) throw new RecordNotFoundException();
		if(b.isBorrowed()) throw new NoAuthorityException();
		lb.delete(book);
		
		return true;
		
	}

	@Override
	public ArrayList<Book> listBorrowed(int userID, int userType) {
		// TODO Auto-generated method stub
		ArrayList<Book> ls=lb.queryByUser(userID, userType);
		if(ls!=null)
			return ls;
		else {
			return new ArrayList<Book>();
		}
		
	}

	
	

}
