package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.ChatPOJO;

public class ChatDAO {
	public static List<ChatPOJO> getContentChats(String senderId, String receiverId) throws SQLException {
		List<ChatPOJO> chatConents = new ArrayList<ChatPOJO>();
		SQLDataConnect conn = null;
		ResultSet rs = null;
		try {
			conn = new SQLDataConnect();
			conn.open();
			String sql = String.format("select * from Chat "
					+ "where sender_id IN ('%s', '%s') and receiver_id IN ('%s', '%s')"
					+ "Order by timestamp", senderId, receiverId, senderId, receiverId);
			rs = conn.excuteQuery(sql);
			ChatPOJO user;
			while (rs.next()) {
				user = new ChatPOJO();
				user.setSender_id(rs.getString("sender_id"));
				user.setReceiver_id(rs.getString("receiver_id"));
				user.setMessage(rs.getString("message"));
				user.setTimestamp(rs.getTimestamp("timestamp"));
				chatConents.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
	    	conn.close();
		}
		return chatConents;
	}
	
	public static int sendMessage(ChatPOJO contentChat) {
		SQLDataConnect conn = null;
		int x = 0;
		try {
			conn = new SQLDataConnect();
			conn.open();
			String sql = "insert into Chat(sender_id, receiver_id, timestamp, message) "
	                   + "values (?, ?, ?, ?);";

			x = conn.prepareStatement(sql, contentChat);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return x;
	}
}
