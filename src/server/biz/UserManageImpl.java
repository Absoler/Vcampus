package server.biz;

import java.util.Random;

import javax.net.ssl.SSLContext;

import server.dao.AdminDaoImpl;
import server.dao.StudentDaoImpl;
import server.dao.TeacherDaoImpl;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.Admin;
import util.Student;
import util.Teacher;
import util.User;


public class UserManageImpl implements UserManage{

	private	TeacherDaoImpl daot=new TeacherDaoImpl();
	private StudentDaoImpl daos=new StudentDaoImpl();
	private AdminDaoImpl daoa= new AdminDaoImpl();
	@Override
	public int findId(String nickName, int userType) throws RecordNotFoundException {
		if(userType==1) {
			Teacher aim = daot.queryByNickName(nickName);
			if(aim==null) throw new RecordNotFoundException();
			return aim.getId();
			}
		else if(userType==0){
			Student aim=daos.queryByNickName(nickName);
			if(aim==null) throw new RecordNotFoundException();
			return aim.getId();
		}
		else {
			{
				//此处添加admin处理
				return 0;
			}
		}
		// TODO Auto-generated method stub
		//return 0;
	}

	@Override
	public boolean checkPwd(int id, String pwd,int userType) throws RecordNotFoundException
	{
		if(userType==1) {
			Teacher aim=daot.query(id);
			if(aim==null) throw new RecordNotFoundException();
			if(aim.getPwd().equals(pwd))
				return true;
			else
				return false;
		}
		else if(userType==0){
			Student aim=daos.query(id);
			if(aim==null) throw new RecordNotFoundException();
			if(aim.getPwd().equals(pwd))
				return true;
			else
				return false;
		}
		else if(userType==2){
			/**
			 * 此处添加admin信息
			 */
			Admin aim=daoa.query(id);
			if(aim==null) throw new RecordNotFoundException();
			if(aim.getPwd().equals(pwd))
				return true;
			else
				return false;
		}
		else {
			return false;
		}
		// TODO Auto-generated method stub
		//return false;
	}

	@Override
	public boolean updateInfo(User user,int userType) throws RecordNotFoundException {
		if(userType==1) 
		{
			Teacher aim=(Teacher)user;
			if(daot.queryByNickName(user.getNickName())==null) throw new RecordNotFoundException();
			daot.update(aim);
			return true;
		}
		else if(userType==0){
			Student aim=(Student)user;
			if(daos.queryByNickName(user.getNickName())==null) throw new RecordNotFoundException();
			daos.update(aim);
			return true;
		}
		else if(userType==2)
		{
			Admin aim=(Admin)user;
			if(daoa.query(aim.getId())==null) throw new RecordNotFoundException();
			daoa.update(aim);
			return true;
		}
		//添加admin信息
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserInfo(int userType, int id) throws RecordNotFoundException{
		if(userType==1) {
			Teacher aim=daot.query(id);
			if(aim==null) throw new RecordNotFoundException();
			return aim;
		}
		else if(userType==0){
			Student aim=daos.query(id);
			if(aim==null) throw new RecordNotFoundException();
			return aim;
		}
		else {
			/**
			 * admin
			 */
			return null;
		}
		// TODO Auto-generated method stub
		//return null;
	}
	
	@Override
	public boolean newUser(String nickName,String pwd,int userType) throws RecordAlreadyExistException {
		int i;
		if(userType==1) {
			if(daot.queryByNickName(nickName)!=null)
				throw new RecordAlreadyExistException();
			else {
				Teacher aim=new Teacher();
				aim.setNickName(nickName);
				aim.setPwd(pwd);
				do{
					Random r = new Random();
				int number = r.nextInt(10000000);
				String id = "090"+number;
				i = Integer.parseInt(id);
				}
				while(daot.query(i)!=null);
				aim.setId(i);
				aim.setUserType(1);
				daot.insert(aim);
			}
			return true;
		}
		else if(userType==0){
			if(daos.queryByNickName(nickName)!=null)
				throw new RecordAlreadyExistException();
			else {
				Student aim=new Student();
				aim.setNickName(nickName);
				aim.setPwd(pwd);
				aim.setUserType(0);
				do{
					Random r = new Random();
				int number = r.nextInt(10000000);
				String id = "213"+number;
				i = Integer.parseInt(id);
				}
				while(daos.query(i)!=null);
				aim.setId(i);
				daos.insert(aim);
			}
			return true;
		}
		else {
			/**
			 * add admin part
			 */
			return false;
		}
	}
	public static void main(String[] args) {
		UserManageImpl uml=new UserManageImpl();
		//检查密码功能测试
//		try {
//			boolean result=uml.checkPwd(213170240, "123456", 0);
//			System.out.println(result);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//查询id功能测试
//		try {
//			int id=uml.findId("zhuzhuzhu", 0);
//			System.out.println(id);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//获取用户资料
//		try {
//			Student stu=(Student)uml.getUserInfo(0, 213170240);
//			System.out.println(stu.getId());
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//创建新用户
//		try {
//			boolean result=uml.newUser("电磁波", "123456a", 1);
//			System.out.println(result);
//		} catch (RecordAlreadyExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//更新用户信息
//		try {
//			Student stu=new Student();
//			stu=(Student)uml.getUserInfo(0, 213170240);
//			stu.setDeposit(10000);
//			uml.updateInfo(stu, 0);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		//插入管理员测试
		try {
			uml.newAdmin(246, "465465");
		} catch (RecordAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean newAdmin(int id, String pwd) throws RecordAlreadyExistException {
		// TODO Auto-generated method stub
			Admin admin=new Admin();
			if(daoa.query(id)!=null) throw new RecordAlreadyExistException();
			admin.setId(id);
			admin.setPwd(pwd);
			daoa.insert(admin);
		return true;
	}

}
