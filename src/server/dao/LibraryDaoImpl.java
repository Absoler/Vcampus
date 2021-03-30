package server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import server.exception.AdminException;
import server.exception.NoAuthorityException;
import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.Student;
import util.Teacher;
import util.User;
import vo.Book;
import vo.BookInfo;

public class LibraryDaoImpl implements LibraryDao{
	private Access access=new Access();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	private ResultSet rsEx = null;
	private StudentDaoImpl student=new StudentDaoImpl();
	private TeacherDaoImpl teacher = new TeacherDaoImpl();
	private AdminDaoImpl admin = new AdminDaoImpl();
	
	public ArrayList<Book> rsToBookList()
	{
		try {
			ArrayList<Book> list = new ArrayList<Book>();
			do
			{
				Book b=new Book();
				b.setBookAuthor(rs.getString("Author"));
				b.setBookName(rs.getString("BookName"));
				b.setId(rs.getInt("BookID"));
				b.setBorrowed(rs.getBoolean("isBorrowed"));
				b.setPublisher(rs.getString("Publisher"));
				b.setUserId(rs.getInt("BorrowedID"));
				b.setUserType(rs.getInt("BorrowedType"));
				//if(b.isBorrowed()==false)
					list.add(b);
			}while(rs.next());
			return list;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Book> rsToBorrowedList()
	{
		try {
			ArrayList<Book> list = new ArrayList<Book>();
			do
			{
				Book b=new Book();
				b.setBookAuthor(rs.getString("Author"));
				b.setBookName(rs.getString("BookName"));
				b.setId(rs.getInt("BookID"));
				b.setBorrowed(rs.getBoolean("isBorrowed"));
				b.setPublisher(rs.getString("Publisher"));
				b.setUserId(rs.getInt("BorrowedID"));
				b.setUserType(rs.getInt("BorrowedType"));
				if(b.isBorrowed()==true)
					list.add(b);
			}while(rs.next());
			return list;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Book rsToOneBook()
	{
		try
		{
			Book book =new Book();
			book.setBookAuthor(rs.getString("Author"));
			book.setBookName(rs.getString("BookName"));
			book.setId(rs.getInt("BookID"));
			book.setBorrowed(rs.getBoolean("IsBorrowed"));
			book.setPublisher(rs.getString("Publisher"));
			book.setUserType(rs.getInt("BorrowedType"));
			book.setUserId(rs.getInt("BorrowedID"));
			return book;
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private ArrayList<BookInfo> rsToBookInfo()
	{
		try
		{
			int flag=0;
			int sum=0;
			ArrayList<BookInfo> list = new ArrayList<BookInfo>();
			do {
				flag=0;
				sum=0;
				BookInfo b= new BookInfo();
				ArrayList<Book> li = query(rsEx.getString("BookName"));
				for(BookInfo bs:list)
				{
					
					if(bs.getBookName().contentEquals(li.get(0).getBookName()))
					{
						flag=1;
						break;
					}
				}
				if(flag==0)
				{
				for(Book bs:li)
				{
					if(bs.isBorrowed())
						continue;
					sum++;
				}
				}
				if(sum!=0)
				{
				b.setAuthor(li.get(0).getBookAuthor());
				b.setBookName(li.get(0).getBookName());
				b.setPublisher(li.get(0).getPublisher());
				b.setStockNum(sum);
				list.add(b);
				}
			}while(rsEx.next());
			return list;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
			
		}
		
	}

	@Override
	public boolean insert(Book book) throws RecordAlreadyExistException {
		try {
		Book b=queryById(book.getId());
		if(b!=null) throw new RecordAlreadyExistException();
		String sql = "INSERT INTO tblLibrary (BookID,BookName,Author,Publisher,IsBorrowed,BorrowedID,BorrowedType)"
				+" VALUES ( '"
				+book.getId()
				+"' , '"+book.getBookName()
				+"' , '"+book.getBookAuthor()
				+"' , '"+book.getPublisher()
				+"' , '"+book.isBorrowed()
				+"' , '"+book.getUserId()
				+"' , '"+book.getUserType()
				+"' )";
		stmt = access.connection.prepareStatement(sql);
		stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean delete(Book book) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		try
		{
			Book b=queryById(book.getId());
			if(b==null) throw new RecordNotFoundException();
			String sql = "DELETE * FROM tblLibrary WHERE BookID= ?";
			stmt=access.connection.prepareStatement(sql);
			stmt.setInt(1, book.getId());
			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
		
	}


	@Override
	public ArrayList<Book> query(String bookName) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "SELECT * FROM tblLibrary WHERE BookName = "+"'"+bookName+"'";
			stmt=access.connection.prepareStatement(sql);
			rs=stmt.executeQuery();
			if(rs.next());
			{
				return rsToBookList();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<BookInfo> queryAll() {
		// TODO Auto-generated method stub
		try
		{
			String sql="SELECT * FROM tblLibrary";
			stmt  =access.connection.prepareStatement(sql);
			rsEx = stmt.executeQuery();
			if(rsEx.next())
			{
				return rsToBookInfo();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book queryById(int id) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "SELECT * FROM tblLibrary WHERE BookID="+"'"+id+"'";
			stmt = access.connection.prepareStatement(sql);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rsToOneBook();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean update(Book book) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		try
		{
			Book b=queryById(book.getId());
			if(b==null) throw new RecordNotFoundException();
			String sql="UPDATE tblLibrary SET IsBorrowed=?,BorrowedID=?,BorrowedType=? WHERE "
					+"BookID =?";
			stmt = access.connection.prepareStatement(sql);
			stmt.setBoolean(1, book.isBorrowed());
			stmt.setInt(2, book.getUserId());
			stmt.setInt(3, book.getUserType());
			stmt.setInt(4, book.getId());
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean borrow(int id, int type, String bookName)
			throws OutOfLimitException, RecordNotFoundException,NoAuthorityException,AdminException
	{
		// TODO Auto-generated method stub
			ArrayList<Book> ls = query(bookName);
			ArrayList<Book> list = new ArrayList<Book>();
			if(ls==null)throw new RecordNotFoundException();
			for(int i=0;i<ls.size();i++)
			{
				if(ls.get(i).isBorrowed()==true)
				{
					if(ls.get(i).getUserId()==id&&ls.get(i).getUserType()==type)
					{
						throw new NoAuthorityException();
					}
					continue;
				}
				else
				{
					list.add(ls.get(i));
				}
				
				
			}
			if(list.isEmpty())
			{
				throw new OutOfLimitException();
			}
			if(type==0)
			{
				Student st=student.query(id);
				//StudentDaoImpl sf=new StudentDaoImpl();
				if(st==null) throw new RecordNotFoundException();
				st.setBorrowNum(st.getBorrowNum()+1);
				student.update(st);
				Book b = list.get(0);
				b.setBorrowed(true);
				b.setUserId(st.getId());
				b.setUserType(0);
				update(b);
				
			}
			else if(type==1)
			{
				Teacher tc=teacher.query(id);
				if(tc==null) throw new RecordNotFoundException();
				tc.setBorrowNum(tc.getBorrowNum()+1);
				teacher.update(tc);
				Book b = list.get(0);
				b.setBorrowed(true);
				b.setUserId(tc.getId());
				b.setUserType(1);
				update(b);
			}
			else if(type==2)
			{
				throw new AdminException();
			}
		
		return true;
	}

	@Override
	public boolean returnBook(int id, int type, String bookName) throws AdminException,RecordNotFoundException {
		// TODO Auto-generated method stub
			ArrayList<Book> list = query(bookName);
			if(list==null) throw new RecordNotFoundException();
			int flag=0;
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i).getUserId()==id&&type==list.get(i).getUserType())
				{
					if(type==1)
					{
						Teacher tc = teacher.query(id);
						if(tc==null) throw new RecordNotFoundException();
						tc.setBorrowNum(tc.getBorrowNum()-1);
						teacher.update(tc);
						Book b=list.get(i);
						b.setUserId(0);
						b.setUserType(-1);
						b.setBorrowed(false);
						update(b);
						flag=1;
						break;
					}
					else if(type==0)
					{
						Student st = student.query(id);
						if(st==null) throw new RecordNotFoundException();
						st.setBorrowNum(st.getBorrowNum()-1);
						student.update(st);
						Book b=list.get(i);
						b.setUserId(0);
						b.setUserType(-1);
						b.setBorrowed(false);
						update(b);
						flag=1;
						break;
					}
					else if(type==2){
						throw new AdminException();
					}
				}

			}
			if(flag==0)
			{
				//System.out.println(num+" "+list.size());
				throw new RecordNotFoundException();
			}
		return true;
	}
	public static void main(String[] args) {
		//初始化部分
		Book b=new Book();
		LibraryDaoImpl li = new LibraryDaoImpl();
		b.setBookAuthor("刘慈欣");
		b.setBookName("三体");
		b.setBorrowed(false);
		b.setId(1234);
		b.setPublisher("科学出版社");
		b.setUserId(0);
		b.setUserType(-1);
		
		Book b1=new Book();
		b1.setBookAuthor("托尔斯泰");
		b1.setBookName("战争与和平");
		b1.setBorrowed(false);
		b1.setId(11);
		b1.setPublisher("人民文学出版社");
		b1.setUserId(0);
		b1.setUserType(-1);
		//添加测试部分
		try {
			li.insert(b);
			li.insert(b1);
		} catch (RecordAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询测试部分
		ArrayList<BookInfo> bs=li.queryAll();
		if(li!=null)
		{for(BookInfo s:bs)
		{
			System.out.println(s.getBookName()+" "+s.getStockNum());
		}
		ArrayList<Book> bst=li.query("三体");
		for(Book s:bst)
		{
			System.out.println(s.getBookAuthor()+" "+s.getId());
		}
		Book s3 = li.queryById(123);
		System.out.println(s3.getBookAuthor()+" "+s3.getId());
		ArrayList<Book> bst1 = li.query();
		for(Book s:bst1)
		{
			System.out.println(s.getBookAuthor()+" "+s.getId());
		}
		//更新测试部分
//		b.setBorrowed(true);
//		b.setUserId(213170240);
//		b.setUserType(0);
//		}
//		try {
//			li.update(b);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//删除测试部分代码
//		try {
//			li.delete(b);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//借书测试
//		StudentDaoImpl s=new StudentDaoImpl();
//		Student st=s.query(213170240);
		TeacherDaoImpl t = new TeacherDaoImpl();
		Teacher tc = t.query(233);
//		try {
//			li.borrow(tc.getId(), 1, "三体");
//			li.borrow(tc.getId(), 1,"战争与和平");
//		} catch (OutOfLimitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoAuthorityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch(AdminException e)
//		{
//			e.printStackTrace();
//		}
//		ArrayList<Book> list=li.query();
//		for(Book m:list)
//		{
//			System.out.println(m.getBookName());
//		}
//		//还书测试
//		try {
//			li.returnBook(tc.getId(),1,"三体");
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch(AdminException e)
//		{
//			e.printStackTrace();
//		}
//		//全退还测试
		try {
			li.returnAllBook(tc, 1);
			System.out.println("输出成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	}


	@Override
	public boolean returnAllBook(User user, int type) throws AdminException,RecordNotFoundException {
		// TODO Auto-generated method stub
			ArrayList<Book> list = query();
			int num=0;
			if(list!=null)
			{for(int i=0;i<list.size();i++)
			{
				if(list.get(i).getUserId()==user.getId()&&type==list.get(i).getUserType())
				{
					if(type==1)
					{
						Teacher tc = teacher.query(user.getId());
						if(tc==null) throw new RecordNotFoundException();
						tc.setBorrowNum(tc.getBorrowNum()-1);
						teacher.update(tc);
						Book b=list.get(i);
						b.setUserId(0);
						b.setUserType(-1);
						b.setBorrowed(false);
						update(b);
					}
					else if(type==0)
					{
						Student st = student.query(user.getId());
						if(st==null) throw new RecordNotFoundException();
						st.setBorrowNum(st.getBorrowNum()-1);
						student.update(st);
						Book b=list.get(i);
						b.setUserId(0);
						b.setUserType(-1);
						b.setBorrowed(false);
						update(b);
					}
					else if(type==2) {
						throw new AdminException();
					}
				}
				else
				{
					num++;
				}
			}
			if(num==list.size())
			{
				throw new RecordNotFoundException();
			}
			}

		return true;
	}

	@Override
	public ArrayList<Book> query() {
		// TODO Auto-generated method stub
		try
		{
			String sql="SELECT * FROM tblLibrary";
			stmt  =access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				return rsToBookList();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Book> queryByUser(int userId,int userType) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM tblLibrary WHERE BorrowedID="+"'"+userId+"'"
					+" AND BorrowedType="+"'"+userType+"'";
			stmt = access.connection.prepareStatement(sql);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rsToBorrowedList();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
