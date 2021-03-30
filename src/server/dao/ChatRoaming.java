package server.dao;

import java.util.ArrayList;

import vo.MsgChat;

public interface ChatRoaming {
	boolean insert(MsgChat msgChat);
	ArrayList<MsgChat> roamingInit();
}
