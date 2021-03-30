package server.biz;

import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.User;

/**总务查询服务
 * 
 * @author 24989
 *
 */
public interface UserManage {
	public int findId(String nickName,int userType) throws RecordNotFoundException;
	public boolean checkPwd(int id,String pwd,int userType) throws RecordNotFoundException;
	public boolean updateInfo(User user,int userType) throws RecordNotFoundException;
	public User getUserInfo(int userType,int id) throws RecordNotFoundException;
	public boolean newUser(String nickName,String pwd,int userType) throws RecordAlreadyExistException;
	public boolean newAdmin(int id,String pwd) throws RecordAlreadyExistException;

}
