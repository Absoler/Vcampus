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
            System.out.println("�ѽ��յ��ͻ�������"+ ",��ǰ�ͻ���ipΪ��"
                    + clientSocket.getInetAddress().getHostAddress());
            System.out.println(object.getMessageType());
            Message serverResponse = new Message();
            switch(object.getMessageType())
            {
            /**
             * �û��������Ӿ���ʵ��
             */
            /**
             *��¼OK
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
        		serverResponse.setErrorMessage("�û����ϲ�����");
        		serverResponse.setMessageType(Constants.operFeedback);
            }
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * ���õķ���
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
            		serverResponse.setErrorMessage("�û����ϲ�����");
            		serverResponse.setMessageType(Constants.operFeedback);
                }
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * ��ȡ�û���Ϣ����
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
            		serverResponse.setErrorMessage("�û����ϲ�����");
            		serverResponse.setMessageType(Constants.operFeedback);
                }
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	
            	/**
            	 * ���¹�������
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
            		serverResponse.setErrorMessage("�û����ϲ�����");
            		serverResponse.setMessageType(Constants.operFeedback);
                }
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
/**
 * ע�Ṧ������
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
            		serverResponse.setErrorMessage("�û��Ѿ�����");
            		serverResponse.setMessageType(Constants.operFeedback);
                }
            	catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setErrorMessage("��֪����ע�����");
            		serverResponse.setMessageType(Constants.operFeedback);
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * ���ֲ�ѯ����
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
            		serverResponse.setErrorMessage("δ֪�ĳͷ���ѯ����");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            /**
             * ͼ������ӹ��ܾ���ʵ�ֲ���
             */
            	/**
            	 * ��ѯ�����Ѹ���������
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
            		serverResponse.setErrorMessage("��ȷ����ͼ��ݳ�ʼ������");
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
//            		serverResponse.setErrorMessage("��ȷ����ͼ��ݲ�ѯ����");
//            	}
//            	finally {
//            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
//                	response.writeObject(serverResponse);
//            	}
//            	break;
            	/**
            	 * ���鹦��Ӧ������
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
            		serverResponse.setErrorMessage("����û���㹻���鼮");
            	}
            	catch(RecordNotFoundException e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("û�в��ҵ��Ȿ��");
            	}
            	catch(NoAuthorityException e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("ͬ����ֻ�ܽ�һ��");
            	}
            	catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("��֪����ͼ��ݽ��ķ������");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * ���鹦��Ӧ��û������
            	 */
            case Constants.Return:
            	try {
            		serverResponse.setLastOperState(true);
            		serverResponse.setMessageType(Constants.operFeedback);
            		ArrayList<Book> ls=(ArrayList<Book>)object.getData();
            		//Array rn = (Return)object.getData();
            		if(ls==null)
            		{
            			System.out.println("û�����ݵ�����");
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
            		serverResponse.setErrorMessage("û�в�ѯ��������Ϣ");
            	}
            	catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("��֪����ͼ��ݻ������");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * �����Ĺ���û������
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
					serverResponse.setErrorMessage("��ID���鼮�Ѿ�����");
				}
            	catch (Exception e) {
					// TODO: handle exception
            		e.printStackTrace();
					serverResponse.setLastOperState(false);
					serverResponse.setMessageType(Constants.operFeedback);
					serverResponse.setErrorMessage("��֪�������ͼ�����");
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
					serverResponse.setErrorMessage("��֪���Ļ����ѯ����");
				}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            /**
           	 * �̵����ӹ��ܾ���ʵ�ֲ���
           	 */
            	/**
            	 * �̵��ʼ���޸��������
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
//            		serverResponse.setErrorMessage("��ѯ��������Ʒ");
//            	}
            	catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("��֪�����̵�������");
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
//            		serverResponse.setErrorMessage("��Ʒ������");
//            	}catch(NoAuthorityException e)
//            	{
//            		serverResponse.setLastOperState(false);
//            		serverResponse.setMessageType(Constants.operFeedback);
//            		serverResponse.setErrorMessage("�޹���Ʒ�����ѹ����һ��");
//            	}*/
//            	catch(Exception e)
//            	{
//            		serverResponse.setLastOperState(false);
//            		serverResponse.setMessageType(Constants.operFeedback);
//            		serverResponse.setErrorMessage("��֪�����̵�������");
//            	}
//            	finally {
//            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
//                	response.writeObject(serverResponse);
//            	}
//            	break;
//            	
            	/**
            	 * ����������
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
            		serverResponse.setErrorMessage("��Ʒ���������Ϸ���������");
            	}
            	catch(RecordNotFoundException e){
            		e.printStackTrace();
                	serverResponse.setLastOperState(false);
            		serverResponse.setErrorMessage("��Ʒ������");
            		serverResponse.setMessageType(Constants.operFeedback);
            	}
            	catch(Exception e)
            	{
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setErrorMessage("��֪�����̵�������");
            	}
            	finally {
            		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                	response.writeObject(serverResponse);
            	}
            	break;
            	/**
            	 * ���¿�湦��
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
					serverResponse.setErrorMessage("δ��ѯ������Ʒ");
					
				}catch(OutOfLimitException e)
            	{
					serverResponse.setLastOperState(false);
					serverResponse.setMessageType(Constants.operFeedback);
					serverResponse.setErrorMessage("��ӵ���Ʒ�����ܲ��Ϸ�");
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
            		serverResponse.setErrorMessage("��Ʒ�Ѿ�����");
            		serverResponse.setLastOperState(false);
            		serverResponse.setMessageType(Constants.operFeedback);
            	}catch(OutOfLimitException e)
            	{
            		serverResponse.setErrorMessage("��Ʒ�������Ϸ�");
            		serverResponse.setMessageType(Constants.operFeedback);
            		serverResponse.setLastOperState(false);
            	}
					
            	/**
                 * ѡ�ι���ʵ��
                 */
                case Constants.course_query://��ѧ��������ʦ���������Լ��Ŀα�
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
                        			System.out.println("û�пα����ʦ");
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
                		serverResponse.setErrorMessage("��ȷ���Ŀγ̷������");
                	}
                	finally {
                		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                    	response.writeObject(serverResponse);
                	}
                	break;
                case Constants.choose_query: 	//ѧ������ѡ�����루����ϵͳ�����пγ̣�
                	try{
                		serverResponse.setLastOperState(true);
                		serverResponse.setMessageType(Constants.choose_init);//��Ϣ����ΪlibraryInit
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
                		serverResponse.setErrorMessage("��֪���Ŀγ̷������");
                	}
                	finally {
                		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                    	response.writeObject(serverResponse);
                	}
                	break;
                case Constants.course_choose://ѧ����ѡ����Ϣ����coursechoose�мӿΣ�
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
                		serverResponse.setErrorMessage("�γ�����Ա");
                	}
                	catch(RecordAlreadyExistException e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("�����ظ�ѡ��γ�");
                	}
                	catch(TimeClashException e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("ʱ���ͻ�����");
                	}
                	catch(RecordNotFoundException e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("ѡ���˲����ڵĿ�");
                	}
                	catch(Exception e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("��֪���Ŀγ̷������");
                	}
                	finally {
                		//System.out.println(serverResponse.isLastOperState());
                		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                    	response.writeObject(serverResponse);
                	}
                	break;
                case Constants.course_add:		//��ʦ�����˷�����ӿε����루��course�мӿΣ�
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
                		serverResponse.setErrorMessage("�γ��Ѿ�����");
                	}
                	catch(TimeClashException e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("���Ͽ�ʱ���ͻ");
                	}
                	catch (OutOfLimitException e) {
						// TODO: handle exception
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("�γ�ʱ�䲻�Ϸ�");
					}
                	catch(Exception e)
                	{
                		serverResponse.setLastOperState(false);
                		serverResponse.setMessageType(Constants.operFeedback);
                		serverResponse.setErrorMessage("��֪���Ŀγ̷������");
                	}
                	finally {
                		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                    	response.writeObject(serverResponse);
                	}
                	break;      
                	/**
                	 * �˿�
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
						serverResponse.setErrorMessage("�γ̲�����");
						// TODO: handle exception
					}catch (Exception e) {
						// TODO: handle exception
						serverResponse.setLastOperState(false);
						serverResponse.setMessageType(Constants.operFeedback);
						serverResponse.setErrorMessage("��������˿δ���");
					}
                	finally {
                		ObjectOutputStream response = new ObjectOutputStream(clientSocket.getOutputStream());
                    	response.writeObject(serverResponse);
					}
                	break;

            /**
             * ����������ӹ���ʵ��
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
