package vo;

import java.io.Serializable;

public class MsgChat implements Serializable {
	private static final long serialVersionUID = 50000;
//	private int senderId;
	private String senderName;
//	private int recipientId;
	private String recipientName;
	public MsgChat() {
//		senderId=-1;
		senderName="系统";
//		recipientId=-1;
		recipientName="全体";
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	private String content;
	
	
//	public int getSenderId() {
//		return senderId;
//	}
//	public void setSenderId(int senderId) {
//		this.senderId = senderId;
//	}
//	public int getRecipientId() {
//		return recipientId;
//	}
//	public void setRecipientId(int recipientId) {
//		this.recipientId = recipientId;
//	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
