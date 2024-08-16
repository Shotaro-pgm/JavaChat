package javabean;

import java.io.Serializable;

public class ChatroomBean implements Serializable {
	private String id;
	private String content;
	private String sender;
	private String recipient;
	private String sendTime;
	
	public ChatroomBean() {}
	public ChatroomBean(String sender, String content) {
		this.sender = sender;
		this.content = content;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	

}
