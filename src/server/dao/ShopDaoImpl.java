package server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hsqldb.lib.Iterator;
import org.hsqldb.types.UserTypeModifier;

import server.exception.AdminException;
import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import util.Student;
import util.Teacher;
import util.User;
import vo.Product;

public class ShopDaoImpl implements ShopDao{
	
	private Access access=new Access();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	private StudentDaoImpl student = new StudentDaoImpl();
	private TeacherDaoImpl teacher = new TeacherDaoImpl();
	
	public ArrayList<Product> rsToProductList()
	{
		try
		{
			ArrayList<Product> list = new ArrayList<Product>();
			
			do
			{
				Product p=new Product();
				p.setProductID(rs.getInt("ProductID"));
				p.setProductName(rs.getString("ProductName"));
				p.setProductPrice(rs.getDouble("Price"));
				p.setStockNum(rs.getInt("Stock"));
				list.add(p);
			}while(rs.next());
			return list;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Product rsToProduct()
	{
		try
		{
			Product p = new Product();
			p.setProductName(rs.getString("ProductName"));
			p.setProductID(rs.getInt("ProductID"));
			p.setProductPrice(rs.getDouble("Price"));
			p.setStockNum(rs.getInt("Stock"));
			return p;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insert(Product product) throws RecordAlreadyExistException,OutOfLimitException {
		// TODO Auto-generated method stub
		try
		{
			Product p =query(product.getProductName());
			if(p!=null) throw new RecordAlreadyExistException();
			if(product.getStockNum()<0)
				throw new OutOfLimitException();
			String sql = "INSERT INTO tblShop (ProductID, ProductName,Price,Stock)"
					+" VALUES ( '"
			+product.getProductID()
			+"' , '"+product.getProductName()
			+"' , '"+product.getProductPrice()
			+"' , '"+product.getStockNum()
			+"' )";
			stmt = access.connection.prepareStatement(sql);
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
	public boolean delete(Product product) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		try
		{
			Product p = query(product.getProductName());
			if(p==null) throw new RecordNotFoundException();
			String sql = "DELETE * FROM tblShop WHERE ProductName = ?";
			stmt =access.connection.prepareStatement(sql);
			stmt.setString(1,product.getProductName());
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean update(Product product) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		try
		{
			Product p = query(product.getProductName());
			if(p==null) throw new RecordNotFoundException();
			String sql = "UPDATE tblShop SET Price=?,Stock=?"
					+ "WHERE ProductName=?";
			stmt = access.connection.prepareStatement(sql);
			stmt.setDouble(1, product.getProductPrice());
			stmt.setInt(2, product.getStockNum());
			stmt.setString(3, product.getProductName());;
			stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Product query(String name) {
		// TODO Auto-generated method stub
		try
		{
			String sql="SELECT * FROM tblShop WHERE ProductName ="+"'"+name+"'";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				return rsToProduct();
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean buyProduct(int userID, int type, String proName, int num) throws RecordNotFoundException,OutOfLimitException
	{
		// TODO Auto-generated method stub
			ShopDaoImpl pro = new ShopDaoImpl();
		Product p = query(proName);
		if(p==null) throw new RecordNotFoundException();
		if(num<0||num>p.getStockNum())
		{
			throw new OutOfLimitException();
		}
		if(type==1)
		{
			Teacher tc=teacher.query(userID);
			if(tc==null)
				throw new RecordNotFoundException();
			if(tc.getDeposit()<num*p.getProductPrice())
			{
				throw new OutOfLimitException();
			}
			p.setStockNum(p.getStockNum()-num);
			tc.setDeposit(tc.getDeposit()-num*p.getProductPrice());
			teacher.update(tc);
			pro.update(p);
		}
		else if(type == 0)
		{
			Student st = student.query(userID);
			if(st==null)
				throw new RecordNotFoundException();
			if(st.getDeposit()<num*p.getProductPrice())
			{
				throw new OutOfLimitException();
			}
			p.setStockNum(p.getStockNum()-num);
			st.setDeposit(st.getDeposit()-num*p.getProductPrice());
			student.update(st);
			pro.update(p);
		}
		else {
			return false;
		}
		
		return true;
		
	}
	public static void main(String[] args) {
//		//初始化部分
//		Product product=new Product();
//		product.setProductID(111);
//		product.setProductName("柠檬茶");
//		product.setProductPrice(2.5);
//		product.setStockNum(200);
		ShopDaoImpl s = new ShopDaoImpl();
//		//添加部分
//		try {
//			s.insert(product);
//			} catch (RecordAlreadyExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();}
//		catch(OutOfLimitException e){
//			e.printStackTrace();
//		}
//		//查询部分
//		Product p1=s.query("柠檬茶");
//		System.out.println(p1.getProductID());
//		ArrayList<Product> list = s.queryAll();
//		if(list!=null)
//		{for(Product p:list)
//		{
//			System.out.println(p.getProductName());
//		}}
//		//额外 购买操作
//		StudentDaoImpl m= new StudentDaoImpl();
//		Student stu1 = m.query(213170240);
//		try {
//			s.buyProduct(213170240,0, "柠檬茶", 15);
//			System.out.println("购买成功");
//			System.out.println(stu1.getDeposit()+" "+p1.getStockNum());
//		} catch (RecordNotFoundException | OutOfLimitException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		//更新测试部分
//		product.setStockNum(20);
//		product.setProductPrice(3);
//		try {
//			s.update(product);
//		} catch (RecordNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		//删除测试部分
//		try {
//			s.delete(product);
//		} catch (RecordNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
		ArrayList<Product>sl=s.queryAll();
		for(Product p:sl) {
			System.out.println(p.getProductName());
			
		}
	}
	

	@Override
	public ArrayList<Product> queryAll() {
		// TODO Auto-generated method stub
		try
		{
			
			String sql = "SELECT * FROM tblShop";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
				
			{
				return rsToProductList();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product query(int id) {
		// TODO Auto-generated method stub
		try
		{
			
			String sql = "SELECT * FROM tblShop WHERE ProductID="+"'"+id+"'";
			stmt = access.connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
				
			{
				return rsToProduct();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
