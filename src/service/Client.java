package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import pojo.ChatPOJO;
import pojo.UserAccountPOJO;


public class Client {
	private static Client instance;
	Socket client;
	private final int PORT_NUMBER = 9999;
	private final String IP = "localhost";
	
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String phoneNumber;
	
	private UserAccountPOJO user;

	public static Client getInstance() {
	    if (instance == null) {
	        instance = new Client();
	    }
	    return instance;
	}
	
	  private Client() {
  
	  }
	  
	  public void start() {
		  try {
		  		client = new Socket(IP, PORT_NUMBER);
	            System.out.println("You has connected to server!");
			} catch (Exception e) {
					e.printStackTrace();
			}
	}
	  
	  public void start(String phoneNumber) {
		  try {
			  	client = new Socket(IP, PORT_NUMBER);
			  	System.out.println("Has connected server port: " + PORT_NUMBER);
				this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				this.bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				this.phoneNumber = phoneNumber;
			} catch (IOException e) {
				closeEverything(client, bufferedReader, bufferedWriter);
			}
	}
	  
	  public void stopServer() {
	  		try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	  }
	  
	  public int sendMessage(ChatPOJO chatContent) {
		  int x = 0;
			try {
				bufferedWriter.write(phoneNumber);
				bufferedWriter.newLine();
				bufferedWriter.flush();
				
				x = Server.getInstance().sendMessage(chatContent);
				if (client.isConnected()) {
					String messageToSend = chatContent.getMessage();
					String receiver = chatContent.getReceiver_id();				
					bufferedWriter.write(phoneNumber + " : " + messageToSend + " " + receiver);
					listenForMessage();
					bufferedWriter.newLine();
					bufferedWriter.flush();
				}
				
			} catch (Exception e) {
				closeEverything(client, bufferedReader, bufferedWriter);
			}
			return x;
		}
		
		public void listenForMessage() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					String messageFromGroupChat;
					
					if (client.isConnected()) {
						try {
							messageFromGroupChat = bufferedReader.readLine();
							System.out.println(messageFromGroupChat);
						} catch (IOException e) {
							closeEverything(client, bufferedReader, bufferedWriter);
						}
					}
				}
			}).start();
		}
		
		public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	public int logout(String userId) throws SQLException {
		int x = Server.getInstance().logout(userId);
		return x;
	}
	
	public List<UserAccountPOJO> getUserAccounts(String phoneNumber) throws SQLException {
		return Server.getInstance().getUserAccounts(phoneNumber);
	}

	public UserAccountPOJO getUserAccount(String phoneNumber) throws SQLException {
		return Server.getInstance().getUserAccount(phoneNumber);
	}
	
	public UserAccountPOJO getUser() {
		return user;
	}

	public void setUser(UserAccountPOJO user) {
		this.user = user;
	}
	
	//chat
	public List<ChatPOJO> getContentChats(String senderId, String receiverId) throws SQLException {
		return Server.getInstance().getContentChats(senderId, receiverId);
	}
	
	public void sendFile(File fileSender) throws IOException {
		try {
			FileInputStream fis = new FileInputStream(fileSender.getAbsoluteFile());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			
			String fileName = fileSender.getName();
			byte[] fileNameBytes = fileName.getBytes();
			
			byte[] fileContentBytes = new byte[(int) fileSender.length()];
			fis.read(fileContentBytes);
			
			dos.writeInt(fileNameBytes.length);
			dos.write(fileNameBytes);
			
			dos.writeInt(fileContentBytes.length);
			dos.write(fileContentBytes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//end-chat
}
