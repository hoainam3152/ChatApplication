package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import component.ChatBody;
import component.ChatBottom;
import component.ChatTitle;
import event.EventChat;
import event.PublicEvent;
import pojo.ChatPOJO;
import pojo.UserAccountPOJO;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ChatGui extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel body;
	JLabel lbName, lbActive;
	ChatTitle pnChatTitle;
	ChatBody pnChatBody;
	ChatBottom pnChatBottom;
	UserAccountPOJO receiver;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public ChatGui() throws SQLException {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		pnChatTitle = new ChatTitle();
		add(pnChatTitle, BorderLayout.NORTH);

		pnChatBody = new ChatBody();
		add(pnChatBody, BorderLayout.CENTER);
		
		pnChatBottom = new ChatBottom();
		add(pnChatBottom, BorderLayout.SOUTH);
		
		PublicEvent.getInstance().addEventChat(new EventChat() {	
			@Override
			public void sendMessage(ChatPOJO message) {
				pnChatBody.showContentRight(message.getMessage(), message.getTimestamp());
			}
			
			@Override
			public void receiveMessage(ChatPOJO message) throws SQLException {

			}

			@Override
			public void sendImage(ImageIcon imageIcon) {
				pnChatBody.addImageRight(imageIcon);
			}

			@Override
			public void sendFile(String fileName, String mb) {
				pnChatBody.addFilesRight(fileName, mb);
			}
		});
	}	
	
	public void setReceiver(UserAccountPOJO receiver) throws SQLException {
		this.receiver = receiver;
		pnChatTitle.setReceiver(receiver);
		pnChatBody.setReceiver(receiver);
		pnChatBody.refreshBody();
		pnChatBody.showMessages();
		pnChatBottom.setReceiver(receiver);
	}
}