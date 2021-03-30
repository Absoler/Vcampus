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
	
    //��ʼ������������response��data�м���
	@Override
	public ArrayList<Product> listProducts() {
		return dao.queryAll();
		// TODO Auto-generated method stub
	}
	
	
	//������񣬻�ȡPurchase���󣬻�ȡ��Ҫ����Ϣ�����ü���
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
	

	//�����Ʒ�Ĳ����������Ƿ���˵����񣬲�����Socket��ʵ��
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

	//���¿��Ĳ�������ȡ����Ϣ��ΪRestore
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
		return null;//���ؿյ�����£�����product�Ƿ�Ϊ�գ�������Ϣ����Ϊû���ҵ���Ʒ
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
		pd.setProductName("���巽����");
		pd.setProductPrice(6);
		pd.setStockNum(200);
		//�����Ʒ���ܲ���
//		try {
//			ssl.addProduct(pd);
//		} catch (RecordAlreadyExistException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OutOfLimitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//������Ʒ���ܲ���
//		try {
//			ssl.buyProduct("���巽����", 213170240, 0, 10);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OutOfLimitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//�����Ӻͼ۸��޸Ĵ���
//		try {
//			ssl.restockProduct("���巽����", 100, 5);
//		} catch (RecordNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OutOfLimitException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//�г�������Ʒ
		ArrayList<Product> ls=ssl.listProducts();
		if(ls!=null)
		{
			for(Product p:ls)
			{
				System.out.println(p.getProductID());
			}
		}
		//��Ʒ���ϲ�ѯ
		Product psd=ssl.query("���巽����");
		if(psd!=null) System.out.println(psd.getStockNum());
		//ɾ����Ʒ���ܲ���
		try {
			ssl.deleteProduct(pd);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
