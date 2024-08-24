package pojo;

import java.util.Date;

public class ChatPOJO {
	private String sender_id, receiver_id, message;
	private Date timestamp;

	public ChatPOJO() {
		super();
	}
	
	public ChatPOJO(String sender_id, String receiver_id, String message, Date timestamp) {
		super();
		this.sender_id = sender_id;
		this.receiver_id = receiver_id;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public String getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
