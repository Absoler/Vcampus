package server.socket;

import java.io.BufferedInputStream;


import java.io.BufferedReader;
import test.testConnection;
import util.*;
import vo.Book;
import vo.BookInfo;
import vo.Borrow;
import vo.Choose;
import vo.Course;
import vo.Login;
import vo.Product;
import vo.Purchase;
import vo.Restore;
import vo.Return;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import org.apache.commons.lang.NullArgumentException;

import server.biz.ChooseCourseServiceImpl;
import server.biz.LibraryService;
import server.biz.LibraryServiceImpl;
import server.biz.ShopService;
import server.biz.ShopServiceImpl;
import server.biz.UserManageImpl;
import server.dao.AdminDaoImpl;
import server.dao.CourseDaoImpl;
import server.dao.LibraryDaoImpl;
import server.dao.ShopDao;
import server.dao.ShopDaoImpl;
import server.dao.StudentDaoImpl;
import server.exception.AdminException;
import server.exception.NoAuthorityException;
import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import server.exception.TimeClashException;
import util.Teacher;

public class ServerSocketThread implements Runnable
{
	private Socket clientSocket;
	private UserManageImpl daos = new UserManageImpl();
	private LibraryServiceImpl lb = new LibraryServiceImpl();
	private ShopServiceImpl shop = new ShopServiceImpl();
	private LibraryDaoImpl ls = new LibraryDaoImpl();
	private ShopDaoImpl sds=new ShopDaoImpl();
	private ChooseCourseServiceImpl cs= new ChooseCourseServiceImpl();
	private CourseDaoImpl cd=new CourseDaoImpl();
	private StudentDaoImpl sd=new StudentDaoImpl();
	private AdminDaoImpl ad=new AdminDaoImpl();


	ServerSocketThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.clientSocket = socket;
	}


	@Override
	public synchronized void run() 
	{
		try {
			ObjectInputStream message = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			Message object = (Message)message.readObject();
            System.out.println("已接收到客户端连接"+ ",当前客户端ip为："
                    + clientSocket.getInetAddress().getHostAddress());
            System.out.println(object.getMessageType());
            Message serverResponse = new Message();
            switch(object.getMessageType())
            {
            /**
             * 用户功能连接具体实现
             */
            /**
             *登录OK
             */
            case Constants.userLogin:
            	try
            {
            		
            	Login lg = (Login)object.getData();
            	if(daos.checkPwd(lg.getId(), lg.getPwd(), lg.getUserType()))
            	{
            		
            		serverResponse.setLastOperState(true);
            		serverResponse.setMessageType(Constants.operFeedback);
            		if(lg.getUserType()==1) 
            		{
            			Teacher tc = (Teacher)daos.getUserInfo(lg.getUserType(), lg.getId());
            			serverResponse.setData(tc);
            		}
            		else if(lg.getUserType()==0)
            		{
            			Student tc = (Student)daos.getUserInfo(lg.getUserType(), lg.getId());
            			serverResponse.setData(tc);
            		}
            		else if(lg.getUserType()==2){
            			Admin ac=(Admin)ad.query(lg.getId());
            			serverResponse.setData(ac);
					}
            		
            	}
            	else
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setErrorMessage("Password is wrong");
            		serverResponse.setMessageType(Constants.operFeedback);
            	}
            }catch(RecordNotFoundException e)
            {
            	e.printStackTrace();
            	serverResponse.setLastOperState(false);
        		serverResponse.setErrorMessage("用户资料不存在");
        		serverResponse.setMessageType(Constants.operFeedback);
            }
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * 无用的方法
            	 */
            case Constants.userLoginByNickName:
            	try
                {
                	Login lg = (Login)object.getData();
                	int id=daos.findId(lg.getNickName(),lg.getUserType());
                	if(daos.checkPwd(id, lg.getPwd(), lg.getUserType()))
                	{
                		
                		serverResponse.setLastOperState(true);
                		serverResponse.setMessageType(Constants.operFeedback);
                		if(lg.getUserType()==1) 
                		{
                			Teacher tc = (Teacher)daos.getUserInfo(lg.getUserType(), id);
                			serverResponse.setData(tc);
                		}
                		else if(lg.getUserType()==0)
                		{
                			Student tc = (Student)daos.getUserInfo(lg.getUserType(), id);
                			serverResponse.setData(tc);
                		}
                		else {
							
						}
                	}
                	else
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setErrorMessage("Password is wrong");
                		serverResponse.setMessageType(Constants.operFeedback);
                	}
                }catch(RecordNotFoundException e)
                {
                	e.printStackTrace();
                	serverResponse.setLastOperState(false);
            		serverResponse.setErrorMessage("用户资料不存在");
            		serverResponse.setMessageType(Constants.operFeedback);
                }
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * 获取用户信息正常
            	 */
            	
            case Constants.getUserInfo:
            	try
                {
                	serverResponse.setLastOperState(true);
                	serverResponse.setMessageType(Constants.operFeedback);
                	if(object.getUserType()==1) 
                	{
                		Teacher tc = (Teacher)daos.getUserInfo(object.getUserType(), object.getUserId());
                		serverResponse.setData(tc);
                	}
                	else if(object.getUserType()==0)
                	{
                		Student tc = (Student)daos.getUserInfo(object.getUserType(), object.getUserId());
                		serverResponse.setData(tc);
                	}
                	else {
						serverResponse.setData(null);
					}
                }
                	catch(RecordNotFoundException e)
                {
                	e.printStackTrace();
                	serverResponse.setLastOperState(false);
            		serverResponse.setErrorMessage("用户资料不存在");
            		serverResponse.setMessageType(Constants.operFeedback);
                }
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	
            	/**
            	 * 更新功能正常
            	 */
            case Constants.updateUserInfo:
            	
            	try
                {
                	serverResponse.setLastOperState(true);
                	serverResponse.setMessageType(Constants.operFeedback);
                	if(object.getData() instanceof ArrayList<?>)
                	{
                		ArrayList<User> ls=(ArrayList<User>)object.getData();
                		for(User u:ls)
                		{
                			daos.updateInfo(u, u.getUserType());
                		}
                	}
                	else {
						daos.updateInfo((User)object.getData(), object.getUserType());
					}
               	}
            	catch(RecordNotFoundException e)
                {
                	e.printStackTrace();
                	serverResponse.setLastOperState(false);
            		serverResponse.setErrorMessage("用户资料不存在");
            		serverResponse.setMessageType(Constants.operFeedback);
                }
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
/**
 * 注册功能正常
 */
            	
            case Constants.register:
            	try
                {
                	serverResponse.setLastOperState(true);
                	serverResponse.setMessageType(Constants.operFeedback);
            		User u=(User)object.getData();
            		//Login u=(Login)object.getData();

                	if(!daos.newUser(u.getNickName(), u.getPwd(), u.getUserType()))
                		throw new RecordAlreadyExistException();
                	serverResponse.setData(daos.findId(u.getNickName(), u.getUserType()));
               	}
            	catch(RecordAlreadyExistException e)
                {
                	//e.printStackTrace();
                	serverResponse.setLastOperState(false);
            		serverResponse.setErrorMessage("用户已经存在");
            		serverResponse.setMessageType(Constants.operFeedback);
                }
            	catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setErrorMessage("不知名的注册错误");
            		serverResponse.setMessageType(Constants.operFeedback);
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * 处分查询正常
            	 */
            case Constants.punishQUERY:
            	try {
					serverResponse.setLastOperState(true);
					serverResponse.setMessageType(Constants.operFeedback);
					ArrayList<Student> ls=sd.queryAll();
					if(ls!=null)
					{
						serverResponse.setData(ls);
					}
					else {
						serverResponse.setData(new ArrayList<Student>());
					}
				} 
            	catch(Exception e)
            	{
            		e.printStackTrace();
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("未知的惩罚查询错误");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            /**
             * 图书馆连接功能具体实现部分
             */
            	/**
            	 * 查询功能已改正待测试
            	 */
            case Constants.libraryQuery:
            	try
            	{
            		serverResponse.setLastOperState(true);
            		serverResponse.setMessageType(Constants.libraryInit);
            		ArrayList<BookInfo> ls = lb.listBook();

            		if(ls!=null)
            		serverResponse.setData(ls);
            		else {
						ArrayList<BookInfo> lstArrayList=new ArrayList<BookInfo>();
						serverResponse.setData(lstArrayList);
					}
            	}catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("不确定的图书馆初始化错误");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
//            case Constants.libraryInit:
//            	try
//            	{
//            		Book b = (Book)object.getData();
//            		ArrayList<Book> ls = lb.queryBook(b.getBookName());
//            		serverResponse.setLastOperState(true);
//            		serverResponse.setMessageType(Constants.operFeedback);
//            		serverResponse.setData(ls);
//            	}catch(Exception e)
//            	{
//            		serverResponse.setLastOperState(false);
//            		serverResponse.setMessageType(Constants.operFeedback);
//            		serverResponse.setErrorMessage("不确定的图书馆查询错误");
//            	}
//            	finally {
//            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
//                	response.writeObject(serverResponse);
//            	}
//            	break;
            	/**
            	 * 借书功能应该正常
            	 */
            case Constants.Borrow:
            	try {
            		serverResponse.setLastOperState(true);
            		serverResponse.setMessageType(Constants.operFeedback);
            		Borrow b = (Borrow)object.getData();
            		ls.borrow(b.getId(), b.getUserType(), b.getBookName());

            	}catch(OutOfLimitException e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("馆内没有足够的书籍");
            	}
            	catch(RecordNotFoundException e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("没有查找到这本书");
            	}
            	catch(NoAuthorityException e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("同种书只能借一本");
            	}
            	catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("不知名的图书馆借阅服务错误");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * 还书功能应该没有问题
            	 */
            case Constants.Return:
            	try {
            		serverResponse.setLastOperState(true);
            		serverResponse.setMessageType(Constants.operFeedback);
            		ArrayList<Book> ls=(ArrayList<Book>)object.getData();
            		//Array rn = (Return)object.getData();
            		if(ls==null)
            		{
            			System.out.println("没有数据的样子");
            		}
            		if(ls!=null)
            		{for(Book rn:ls)
            			{
            			System.out.println(rn.getBookName());
            			lb.returnBook(rn.getBookName(), rn.getUserId(), rn.getUserType());
            			}
            		}

            		
            	}catch(RecordNotFoundException e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("没有查询到借书信息");
            	}
            	catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("不知名的图书馆还书错误");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * 添加书的功能没有问题
            	 */
            case Constants.add_book:
            	try {
					serverResponse.setLastOperState(true);
					serverResponse.setMessageType(Constants.operFeedback);
            		Book book=(Book)object.getData();
					lb.addBook(book);

				} catch (RecordAlreadyExistException e) {
					// TODO: handle exception
					e.printStackTrace();
					serverResponse.setLastOperState(false);
					serverResponse.setMessageType(Constants.operFeedback);
					serverResponse.setErrorMessage("该ID的书籍已经存在");
				}
            	catch (Exception e) {
					// TODO: handle exception
            		e.printStackTrace();
					serverResponse.setLastOperState(false);
					serverResponse.setMessageType(Constants.operFeedback);
					serverResponse.setErrorMessage("不知名的添加图书错误");
				}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            case Constants.returnQuery:
            	try {
					serverResponse.setLastOperState(true);
					serverResponse.setMessageType(Constants.returnInit);
					ArrayList<Book> ls=lb.listBorrowed(object.getUserId(), object.getUserType());
					serverResponse.setData(ls);
				} catch (Exception e) {
					// TODO: handle exception
					serverResponse.setLastOperState(false);
					serverResponse.setMessageType(Constants.operFeedback);
					serverResponse.setErrorMessage("不知名的还书查询错误");
				}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            /**
           	 * 商店连接功能具体实现部分
           	 */
            	/**
            	 * 商店初始化修改完待测试
            	 */
            case Constants.shopQuery:
            	//Message serverResponse = new Message();
            	try{
            		serverResponse.setLastOperState(true);
            		serverResponse.setMessageType(Constants.shopInit);
            		ArrayList<Product> ls=shop.listProducts();

            		if(ls!=null)
            		serverResponse.setData(ls);
            		else {
						ArrayList<Product> lst=new ArrayList<Product>();
						serverResponse.setData(lst);
					}

            	}
//            	}catch(RecordNotFoundException e)
//            	{
//            		serverResponse.setLastOperState(false);
//            		serverResponse.setMessageType(Constants.operFeedback);
//            		serverResponse.setErrorMessage("查询不到该商品");
//            	}
            	catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("不知名的商店服务错误");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	
//            case Constants.shopInit:
//            	//Message serverResponse = new Message();
//            	try{
//            		ArrayList<Product> allProducts = shop.listProducts();
//            		serverResponse.setLastOperState(true);
//            		serverResponse.setMessageType(Constants.operFeedback);
//            		serverResponse.setData(allProducts);	
//            	}
//            	/*catch(OutOfLimitException e)
//            	{
//            		serverResponse.setLastOperState(false);
//            		serverResponse.setMessageType(Constants.operFeedback);
//            		serverResponse.setErrorMessage("商品已售罄");
//            	}catch(NoAuthorityException e)
//            	{
//            		serverResponse.setLastOperState(false);
//            		serverResponse.setMessageType(Constants.operFeedback);
//            		serverResponse.setErrorMessage("限购商品，您已购买过一次");
//            	}*/
//            	catch(Exception e)
//            	{
//            		serverResponse.setLastOperState(false);
//            		serverResponse.setMessageType(Constants.operFeedback);
//            		serverResponse.setErrorMessage("不知名的商店服务错误");
//            	}
//            	finally {
//            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
//                	response.writeObject(serverResponse);
//            	}
//            	break;
//            	
            	/**
            	 * 购买功能完整
            	 */
            case Constants.Buy:
            	//Message serverResponse = new Message();
            	try{
            		serverResponse.setLastOperState(true);
            		serverResponse.setMessageType(Constants.operFeedback); 		
            		ArrayList<Purchase> p1 = (ArrayList<Purchase>)object.getData();
            		for(Purchase p:p1) {
            			shop.buyProduct(p.getProductName(), p.getUserId(), p.getUserType(),p.getBuyNum());//String pdId, int id,boolean userType, int num
            		}
            	}catch(OutOfLimitException e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("商品购买量不合法或者余额不足");
            	}
            	catch(RecordNotFoundException e){
            		e.printStackTrace();
                	serverResponse.setLastOperState(false);
            		serverResponse.setErrorMessage("商品不存在");
            		serverResponse.setMessageType(Constants.operFeedback);
            	}
            	catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("不知名的商店服务错误");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * 更新库存功能
            	 */
            case Constants.Restore:
            	try {
					serverResponse.setLastOperState(true);
					serverResponse.setMessageType(Constants.operFeedback);
					Restore rs=(Restore)object.getData();
					shop.restockProduct(rs.getProName(), rs.getAddStockNum(),rs.getProPrice());

					
				} catch (RecordNotFoundException e) {
					// TODO: handle exception
					serverResponse.setLastOperState(false);
					serverResponse.setMessageType(Constants.operFeedback);
					serverResponse.setErrorMessage("未查询到该商品");
					
				}catch(OutOfLimitException e)
            	{
					serverResponse.setLastOperState(false);
					serverResponse.setMessageType(Constants.operFeedback);
					serverResponse.setErrorMessage("添加的商品数可能不合法");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            case Constants.add_product:
            	try {
					serverResponse.setLastOperState(true);
					serverResponse.setMessageType(Constants.operFeedback);
					Product pd=(Product)object.getData();
					shop.addProduct(pd);

            	}catch(RecordAlreadyExistException e)
            	{
            		serverResponse.setErrorMessage("商品已经存在");
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            	}catch(OutOfLimitException e)
            	{
            		serverResponse.setErrorMessage("商品数量不合法");
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setLastOperState(false);
            	}
					
            	/**
                 * 选课功能实现
                 */
                case Constants.course_query://给学生或者老师发送他们自己的课表
                	try
                	{
                    		if(object.getUserType()==1)
                    		{
                    			serverResponse.setLastOperState(true);
                        		serverResponse.setMessageType(Constants.courseinit);
                    			ArrayList<Course>ls = cd.query(object.getUserId());
                    			System.out.println(object.getUserId());

                        		if(ls!=null)
                        		serverResponse.setData(ls);
                        		else {
                        			System.out.println("没有课表的老师");
									serverResponse.setData(new ArrayList<Course>());
								}
                    		}
                    		else if(object.getUserType()==0)
                    		{
                    			//Student st = (Student) object.getData();
                    			//ArrayList<Course> ls = cs.queryCourseList(st.getId());
                    			ArrayList<Course>ls =cs.queryCourseList(object.getUserId());
//                    			if(ls==null)
//                    			{
//                    				throw new NullPointerException();
//                    			}
//                    			for(int i=0;i<ls.size();i++)
//                    			{
//                    				System.out.println(ls.get(i).getCourseName());
//                    			}
                        		serverResponse.setLastOperState(true);
                        		serverResponse.setMessageType(Constants.operFeedback);
                        		if(ls!=null)
                        		serverResponse.setData(ls);
                        		else {
									serverResponse.setData(new ArrayList<Course>());
								}
                    		}
                    		else
                    		{
                    			throw new AdminException();
                    		}
                	}
                	catch(Exception e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("不确定的课程服务错误");
                	}
                	finally {
                		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                    	response.writeObject(serverResponse);
                	}
                	break;
                case Constants.choose_query: 	//学生发出选课申请（发送系统中所有课程）
                	try{
                		serverResponse.setLastOperState(true);
                		serverResponse.setMessageType(Constants.choose_init);//信息类型为libraryInit
                		ArrayList<Course> ls = cs.queryAllClasses();
                		
                		if(ls!=null)
                		serverResponse.setData(ls);
                		else {
							serverResponse.setData(new ArrayList<Course>());
						}
                	}
                	catch(Exception e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("不知名的课程服务错误");
                	}
                	finally {
                		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                    	response.writeObject(serverResponse);
                	}
                	break;
                case Constants.course_choose://学生的选课信息（在coursechoose中加课）
                	try{
                		serverResponse.setLastOperState(true);
                		serverResponse.setMessageType(Constants.operFeedback);
                		Choose choose = (Choose)object.getData();
                		cs.addClass(choose);
                	}
                	catch(OutOfLimitException e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("课程已满员");
                	}
                	catch(RecordAlreadyExistException e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("不能重复选择课程");
                	}
                	catch(TimeClashException e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("时间冲突的情况");
                	}
                	catch(RecordNotFoundException e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("选择了不存在的课");
                	}
                	catch(Exception e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("不知名的课程服务错误");
                	}
                	finally {
                		//System.out.println(serverResponse.isLastOperState());
                		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                    	response.writeObject(serverResponse);
                	}
                	break;
                case Constants.course_add:		//教师向服务端发送添加课的申请（在course中加课）
                	try{
                		serverResponse.setLastOperState(true);
                		serverResponse.setMessageType(Constants.operFeedback);
                		Course course = (Course)object.getData();
//                		System.out.println(course.getCourseName());
//                		System.out.println(course.getCourseID());
//                		System.out.println(course.getTeacherId());
                		System.out.println(course.getTime1());
                		System.out.println(course.getTime2());
                		System.out.println(course.getTime3());
                		cd.insert(course);

                	}
                	catch(RecordAlreadyExistException e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("课程已经存在");
                	}
                	catch(TimeClashException e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("与上课时间冲突");
                	}
                	catch (OutOfLimitException e) {
						// TODO: handle exception
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("课程时间不合法");
					}
                	catch(Exception e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("不知名的课程服务错误");
                	}
                	finally {
                		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                    	response.writeObject(serverResponse);
                	}
                	break;      
                	/**
                	 * 退课
                	 */
                case Constants.course_delete:
                	try {
						Choose choose=(Choose)object.getData();
						cs.returnClass(choose);
						serverResponse.setLastOperState(true);
						serverResponse.setMessageType(Constants.operFeedback);
					} catch (RecordNotFoundException e) {
						serverResponse.setLastOperState(false);
						serverResponse.setMessageType(Constants.operFeedback);
						serverResponse.setErrorMessage("课程不存在");
						// TODO: handle exception
					}catch (Exception e) {
						// TODO: handle exception
						serverResponse.setLastOperState(false);
						serverResponse.setMessageType(Constants.operFeedback);
						serverResponse.setErrorMessage("不清楚的退课错误");
					}
                	finally {
                		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                    	response.writeObject(serverResponse);
					}
                	break;

            /**
             * 聊天具体连接功能实现
             */
//            case ConstantsChatI:
//            	break;
//            case Constants.ChatQuery:
//            	break;
//            case Constants.Chat:
//            	break;
            	default:
            		;
		}
	}
	catch (Exception e) {
       e.printStackTrace();
       }
		}
	}
