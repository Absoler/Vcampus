package server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import net.ucanaccess.console.Main;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.Admin;

public class AdminDaoImpl implements AdminDao {
	private Access access=new Access();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;

	@Override
	public boolean insert(Admin admin) throws RecordAlreadyExistException {
		try
		{
			Admin ad = query(admin.getId());
			if(ad!=null)throw new RecordAlreadyExistException();
			String sql = "INSERT INTO tblAdmin (AdminID, Password) VALUES ( '"
			+admin.getId()
			+"' , '"+admin.getPwd()
			+"' )";
			stmt = access.connection.prepareStatement(sql);
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}

		return true;
		
	}

	@Override
	public boolean delete(Admin admin) throws RecordNotFoundException {
		// TODO Auto-generated method stub
        try {
			
			Admin ad = query(admin.getId());
			if(ad==null) throw new RecordNotFoundException();
			String sql = "DELETE * FROM tblAdmin WHERE AdminID=?";
			//String sql = "DELETE * FROM tblStudent";
			stmt = access.connection.prepareStatement(sql);
			stmt.setInt(1, admin.getId());
			stmt.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean update(Admin admin) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		try
		{
			Admin ad = query(admin.getId());
			if(ad==null) throw new RecordNotFoundException();
			String sql="UPDATE tblAdmin SET Password=?"
					+ "WHERE AdminID=?";
			stmt = access.connection.prepareStatement(sql);
			stmt.setString(1, admin.getPwd());
			stmt.setInt(2, admin.getId());
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
	public Admin query(int adminID) {
		// TODO Auto-generated method stub
		try
		{
			String sql="select * from tblAdmin where AdminID ="+"'"+adminID+"'";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next())
			{
				return rsToAdmin();
			}
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public Admin rsToAdmin()
	{
		try {
		Admin admin = new Admin();
		admin.setUserType(2);
		admin.setId(rs.getInt("AdminID"));
		admin.setPwd(rs.getString("Password"));
		return admin;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
    public static void main(String[] args) {
		
		Admin adm = new Admin();
		adm.setId(111);
		adm.setUserType(2);
		adm.setPwd("110");
		
		AdminDaoImpl a = new AdminDaoImpl();
		
		//插入测试部分
		try {
			a.insert(adm);
		} catch (RecordAlreadyExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//查询测试部分
		Admin ad = new Admin();
		ad = a.query(111);
	    System.out.println(ad.getId()+" "+ad.getPwd());
	    
		//更新测试部分
	    Admin ad1 = a.query(111);
	    ad1.setPwd("233");

		try {
			a.update(ad1);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//删除测试部分
		try {
			a.delete(adm);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
