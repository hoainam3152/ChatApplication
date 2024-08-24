package service;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import dao.ChatDAO;

import dao.UserAccountDAO;
import pojo.ChatPOJO;
import pojo.UserAccountPOJO;

	public class Server{
		private static Server instance;
	    private ServerSocket server;
	    private final int PORT_NUMBER = 9999;
	    
	    Thread t;
	    
	    public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new Server().start();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	
	    public static Server getInstance() {
	        if (instance == null) {
	            instance = new Server();
	        }
	        return instance;
	    }
	    
	    private Server() {
	      	
	    }
	    
	    public void start() {
	        try {
	          server = new ServerSocket(PORT_NUMBER);
	          System.out.println("Server started on port: " + PORT_NUMBER);
	
	          while (!server.isClosed()) {
	        	  	Socket socket = server.accept();
					System.out.println("A new client has connected!");
					ClientHandler clientHandler = new ClientHandler(socket);
					
					Thread thread = new Thread(clientHandler);
					thread.start();
	          }
	      } catch (IOException e) {
	          e.printStackTrace();
	          System.exit(1);
	      }
		}
	    
	    public void closeServerSocket() throws IOException {
			if (server != null) {
				server.isClosed();
			}
		}
	
	public int logout(String userId) throws SQLException {
		int x = UserAccountDAO.logout(userId);
		if (x == 1) {
			System.out.println("Client " + userId + " has outed: " + "\n");
			System.out.println(userId + " has logouted.");
		}
		return x;
	}
	
	public List<UserAccountPOJO> getUserAccounts(String phoneNumber) throws SQLException {
		return UserAccountDAO.getUserAccounts(phoneNumber);
	}
	
	public UserAccountPOJO getUserAccount(String phoneNumber) throws SQLException {
		return UserAccountDAO.getUserAccount(phoneNumber);
	}
	
	//chat
	public List<ChatPOJO> getContentChats(String senderId, String receiverId) throws SQLException {
		return ChatDAO.getContentChats(senderId, receiverId);
	}
	
	public int sendMessage(ChatPOJO chatContent) throws SQLException {
		int x = ChatDAO.sendMessage(chatContent);
		return x;
	}
	//end-chat
}
