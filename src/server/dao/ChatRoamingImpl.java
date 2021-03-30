package server.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MsgChat;

public class ChatRoamingImpl implements ChatRoaming {

	private Access access=new Access();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	@Override
	public boolean insert(MsgChat msgChat) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tblMsgSave (senderName,  recipientName,  msgContent)"
				+ "VALUES ( '" + msgChat.getSenderName()
				+ "' , '"+msgChat.getRecipientName()
				+ "' , '"+msgChat.getContent()+" ' )";
		try {
			stmt = access.connection.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public ArrayList<MsgChat> rsToMsgChatlist() {
		ArrayList<MsgChat> list=new ArrayList<MsgChat>();
		try {
			do {
				MsgChat tmp = new MsgChat();
				tmp.setContent(rs.getString("msgContent"));
				tmp.setRecipientName(rs.getString("recipientName"));
				tmp.setSenderName(rs.getString("senderName"));
				list.add(tmp);
			}while(rs.next());
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	@Override
	public ArrayList<MsgChat> roamingInit() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblMsgSave";
		ArrayList<MsgChat> list=null;
		try {
			stmt=access.connection.prepareStatement(sql);
			rs=stmt.executeQuery();
			if(rs.next()) {
				list=rsToMsgChatlist();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
//	public static void main(String[] args) {
//		ArrayList<MsgChat> history=(new ChatRoamingImpl()).roamingInit();
//		System.out.println(history.size());
//		for(MsgChat i:history) {
//			System.out.println(i.getContent());
//		}
//	}
}
