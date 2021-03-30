package server.biz;

import java.util.ArrayList;
import java.util.Random;

import server.dao.ShopDaoImpl;
import server.exception.OutOfLimitException;
import server.exception.RecordAlreadyExistException;
import server.exception.RecordNotFoundException;
import vo.Product;

public class ShopServiceImpl implements ShopService{

    ShopDaoImpl dao=new ShopDaoImpl();
	
    //初始化服务，设置再response的data中即可
	@Override
	public ArrayList<Product> listProducts() {
		return dao.queryAll();
		// TODO Auto-generated method stub
	}
	
	
	//购买服务，获取Purchase对象，获取需要的信息，调用即可
	@Override
	public boolean buyProduct(String pdName, int id,int userType, int num) throws RecordNotFoundException,OutOfLimitException
	{
		try {
			dao.buyProduct(id, userType, pdName, num);
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}
	

	//添加商品的操作，这里是服务端的事务，不用再Socket中实现
	@Override
	public boolean addProduct(Product pd) throws RecordAlreadyExistException,OutOfLimitException
	{
		
			int r;
			Random random=new Random();
			do {
				r=100000+random.nextInt(10000);
			} while (dao.query(r)!=null);
			pd.setProductID(r);
			dao.insert(pd);
		// TODO Auto-generated method stub
		return true;
	}

	//更新库存的操作，获取的消息包为Restore
	@Override
	public boolean restockProduct(String pdId, int num,double price) throws RecordNotFoundException,OutOfLimitException{
		Product aim=dao.query(pdId);
		if(aim==null) throw new RecordNotFoundException();
		if(aim.getStockNum()+num<0)throw new OutOfLimitException();
		aim.setStockNum(aim.getStockNum()+num);
		aim.setProductPrice(price);
		try {
			dao.update(aim);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Product query(String pdName) {
		// TODO Auto-generated method stub
		Product pd = dao.query(pdName);
		if(pd!=null) return pd;
		return null;//返回空的情况下，测试product是否为空，返回信息设置为没有找到商品
	}


	@Override
	public boolean deleteProduct(Product pd) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		try {
		dao.delete(pd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		ShopServiceImpl ssl=new ShopServiceImpl();
		Product pd=new Product();
		pd.setProductID(12354);
		pd.setProductName("日清方便面");
		pd.setProductPrice(6);
		pd.setStockNum(200);
		//添加商品功能测试
//		try {
//			ssl.addProduct(pd);
//		} catch (RecordAlreadyExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OutOfLimitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//购买商品功能测试
//		try {
//			ssl.buyProduct("日清方便面", 213170240, 0, 10);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OutOfLimitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//库存添加和价格修改代码
//		try {
//			ssl.restockProduct("日清方便面", 100, 5);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OutOfLimitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//列出所有商品
		ArrayList<Product> ls=ssl.listProducts();
		if(ls!=null)
		{
			for(Product p:ls)
			{
				System.out.println(p.getProductID());
			}
		}
		//商品资料查询
		Product psd=ssl.query("日清方便面");
		if(psd!=null) System.out.println(psd.getStockNum());
		//删除商品功能测试
		try {
			ssl.deleteProduct(pd);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
