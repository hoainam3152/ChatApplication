package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.UserAccountPOJO;

public class UserAccountDAO {
	public static int login(String userId, String password) throws SQLException {
		int x = 0;
		SQLDataConnect conn = null;
		ResultSet rs = null;
		try {
			conn = new SQLDataConnect();
			conn.open();
			String sql = String.format("SELECT * FROM UserAccount where phoneNumber='%s' and password='%s'", userId, password);
			rs = conn.excuteQuery(sql);
	    	if (rs.next()) {
	    		if (rs.getInt("status") == 1) {
					return -1;
				}
	    		updateStatus(conn, userId, true);
	    		x = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
	    	conn.close();
		}
		return x;
	}
	
	public static int register(String name, String phoneNumber, String password) throws SQLException {
		int x = 0;
		SQLDataConnect conn = null;
		ResultSet rs = null;
		try {
			conn = new SQLDataConnect();
			conn.open();
			
			String sqlCheckPhoneNumber = String.format("SELECT * FROM UserAccount WHERE phoneNumber='%s'", phoneNumber);
			rs = conn.excuteQuery(sqlCheckPhoneNumber);
			
			if (rs.next()) {
	            return -1;
	        }
			
			String sql = String.format("insert into UserAccount (phoneNumber, password, name) "
					+ "values ('%s','%s',N'%s')", phoneNumber, password, name);
			x = conn.excuteUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
	            rs.close();
	        }
	    	conn.close();
		}
		return x;
	}
	
//	private static ResultSet checkPhoneNumber(SQLDataConnect conn, String phoneNumber) {
//		String sqlUpdate = String.format("SELECT * FROM UserAccount WHERE phoneNumber=%s", phoneNumber);
//		return conn.excuteQuery(sqlUpdate);
//	}
	
	public static int logout(String userId) throws SQLException {
		int x = 0;
		SQLDataConnect conn = null;
		try {
			conn = new SQLDataConnect();
			conn.open();
			x = updateStatus(conn, userId, false);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	    	conn.close();
		}
		return x;
	}
	
	private static int updateStatus(SQLDataConnect conn, String userId, boolean b) {
		String sqlUpdate;
		if (b) {
			sqlUpdate = String.format("UPDATE UserAccount SET status=1 WHERE phoneNumber=%s", userId);
		} else {
			sqlUpdate = String.format("UPDATE UserAccount SET status=0 WHERE phoneNumber=%s", userId);
		}
		return conn.excuteUpdate(sqlUpdate);
	}
	
	public static List<UserAccountPOJO> getUserAccounts(String phoneNumber) throws SQLException {
		List<UserAccountPOJO> userList = new ArrayList<UserAccountPOJO>();
		SQLDataConnect conn = null;
		ResultSet rs = null;
		try {
			conn = new SQLDataConnect();
			conn.open();
			String sql = String.format("SELECT * FROM UserAccount WHERE phoneNumber "
					+ "NOT IN (SELECT phoneNumber FROM UserAccount where phoneNumber = '%s')", phoneNumber);
			rs = conn.excuteQuery(sql);
			UserAccountPOJO user;
			while (rs.next()) {
				user = new UserAccountPOJO();
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setImageUrl(rs.getString("imageUrl"));
				user.setStatus(Integer.parseInt(rs.getString("status")));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
	    	conn.close();
		}
		return userList;
	}
	
	public static UserAccountPOJO getUserAccount(String userId) throws SQLException {
		UserAccountPOJO user = new UserAccountPOJO();
		SQLDataConnect conn = null;
		ResultSet rs = null;
		try {
			conn = new SQLDataConnect();
			conn.open();
			String sql = String.format("SELECT * FROM UserAccount where phoneNumber='%s'", userId);
			rs = conn.excuteQuery(sql);
			rs.next();
			user.setPhoneNumber(rs.getString("phoneNumber"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setImageUrl(rs.getString("imageUrl"));
			user.setStatus(Integer.parseInt(rs.getString("status")));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
	    	conn.close();
		}
		return user;
	}
	
	public static String getImage(String userId) throws SQLException {
		SQLDataConnect conn = null;
		ResultSet rs = null;
		String imageUrl = null;
		try {
			conn = new SQLDataConnect();
			conn.open();
			String sql = String.format("SELECT imageUrl FROM UserAccount where phoneNumber='%s'", userId);
			rs = conn.excuteQuery(sql);
			rs.next();
			imageUrl = rs.getString("imageUrl");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
	    	conn.close();
		}
		return imageUrl;
	}
}
