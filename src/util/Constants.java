package util;



public final class Constants {
	public Constants() {
		// TODO Auto-generated constructor stub
	}
	
	//Message-Type
	public static final String operFeedback = "OPER_FEEDBACK";
	//�û�����
	public static final String userLogin = "USER_LOGIN";
	public static final String userLoginByNickName = "USER_LOGIN_N";
	public static final String getUserInfo = "GET_USER_INFO";
	public static final String updateUserInfo = "UPDATE_USER_INFO";
	public static final String register = "REGISTER";
	public static final String punishQUERY = "PUNISH_QUERY";
	//public static final String register_state = "REGISTER_STATE"; ��ʱû�б�Ҫ�����oper_feedback��ͻ
	//ͼ���
	public static final String libraryQuery = "LIBRARY_QUERY";	//��ʼ�����ͻ��������˷�������
	public static final String libraryInit = "LIBRARY_INIT";		//�������ͻ��˷�����Ϣ
	public static final String Borrow = "BORROW";					//�ͻ��˷���������
	public static final String Return ="RETURN";
	public static final String addBook = "ADD_BOOK";
	public static final String returnQuery="return_query";
	public static final String returnInit="return_init";
	//�̵�
	public static final String shopQuery = "SHOP_QUERY";
	public static final String shopInit = "SHOP_INIT";
	public static final String Buy = "BUY";
	public static final String Restore = "RESTORE";
	//Chat-Constants
	public static final String chatLogin = "CHAT_LOGIN";	//������Ϣ
	public static final String chatInit = "CHAT_INIT";		//�ش���ʷ��Ϣ
	public static final String allMembers = "ȫ���Ա";
	public static final String chatQuery = "CHAT_QUERY";	//ѯ�����޸�����Ϣ
	public static final String chatSend = "CHAT_SEND";		//������Ϣ
	public static final String chat_msg = "CHAT_MSG";		//������ͨ��Ϣ
	public static final String sign_out = "SIGN_OUT";		//�˳�����
	public static final String update_ContactList = "UPDATE_CONTACT_LIST";
	//select a class
	public static final String course_query = "COURSE_QUERY";	//�ͻ���������
	public static final String courseinit = "COURSE_INIT";	//��ѧ��������ʦ�������пα�
	public static final String choose_query = "CHOOSE_QUERY";	//ѧ������ѡ������
	public static final String choose_init = "CHOOSE_INIT";	//��������ѧ�����ؽ�ʦ�ѿ��α�
	public static final String course_choose = "COURSE_CHOOSE";//ѧ����ѡ����Ϣ
	public static final String course_add = "COURSE_ADD";		//��ʦ�����˷�����ӿε�����
	public static final String course_delete = "COURSE_DELETE";
	//�����Ʒ��ͼ��Ĺ���Ա����
	public static final String add_product = "ADD_PRODUCT";	
	public static final String add_book = "ADD_BOOK";
	//Teacher-Title
	public static final String lecturer = "Lecturer";
	public static final String professor = "Professor";
	public static final String mentor = "Mentor";
	//Punish-Type
	public static final String none="None";
	public static final String warning = "Warning";
	public static final String demerit = "Demerit";
	public static final String expulsion = "Expulsion";
}