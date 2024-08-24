package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import pojo.ChatPOJO;

public class SQLDataConnect {
	private Connection connection;
	public void open() {
		String strServer = "Admin\\SQLEXPRESS";
		String strDatabase = "ChatApplication";
		try {
			String connectionURL = "jdbc:sqlserver://" + strServer
					+ ":1434;databaseName=" + strDatabase
					+ ";user=sa;password=123;"
					+ "encrypt=true;trustServerCertificate=true";
			connection = DriverManager.getConnection(connectionURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			this.connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet excuteQuery(String sql) {
		ResultSet rs = null;
		try {
			Statement sm = connection.createStatement();
			rs = sm.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int excuteUpdate(String sql) {
		int n = -1;
		try {
			Statement sm = connection.createStatement();
			n = sm.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public int prepareStatement(String sql, ChatPOJO contentChat) {
		int n = -1;
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, contentChat.getSender_id());
            pstmt.setString(2, contentChat.getReceiver_id());

            // Convert java.util.Date to java.sql.Timestamp for SQL Server
            pstmt.setTimestamp(3, new Timestamp(contentChat.getTimestamp().getTime()));

            pstmt.setString(4, contentChat.getMessage());

            n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
}
