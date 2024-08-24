package component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import pojo.ChatPOJO;
import pojo.UserAccountPOJO;
import service.Client;

public class ChatBody extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel body;
	
	UserAccountPOJO receiver;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public ChatBody() throws SQLException {
		JScrollPane sp = new JScrollPane();
		setPreferredSize(new Dimension(800, 600));
		sp.setVerticalScrollBar(new JScrollBar());
		sp.getVerticalScrollBar().setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(sp);
		
		body = new JPanel();
		body.setLayout(new MigLayout("fillx","", "5[bottom]5"));
		body.setBackground(new Color(240, 240, 240));
		sp.setViewportView(body);
	}
	
	
	public void setReceiver(UserAccountPOJO receiver) {
		this.receiver = receiver;
	}
	
	public UserAccountPOJO getReceiver() {
		return receiver;
	}
	
	public void showMessages() throws SQLException {
			UserAccountPOJO sender = Client.getInstance().getUser();
			List<ChatPOJO> conentChats = Client.getInstance().getContentChats(
					sender.getPhoneNumber(), 
					receiver.getPhoneNumber()
			);
			
			for (ChatPOJO message : conentChats) {
				if (message.getSender_id().equals(sender.getPhoneNumber())) {
					showContentRight(message.getMessage(), message.getTimestamp());
				} else {
					showContentLeft(message.getMessage(), message.getTimestamp(), receiver.getImageUrl());
				}
			}
	}

	private void updateGUIBody() {
		body.repaint();		//vẽ lại một thành phần đồ họa (GUI) trên màn hình
		body.revalidate();	//kiểm tra xem kích thước hoặc vị trí của một thành phần đồ họa có thay đổi hay không
	}
	
	//receiver
	public void showContentLeft(String text, Date timeStamp, String imageName) {
		ReceiverChat content = new ReceiverChat();
		content.setText(text, timeStamp);
		content.setAvatar(imageName);
		content.setWitdhTextArea(getPreferredSize().width);
		body.add(content, "wrap, w ::80%");
		updateGUIBody();
	}
	
	public void showContentLeft(String text, Date timeStamp) {
		ReceiverChat content = new ReceiverChat();
		content.setText(text, timeStamp);
		content.setAvatar("Truong_Ngoc_Anh_1.jpg");
		content.setWitdhTextArea(getPreferredSize().width);
		body.add(content, "wrap, w ::80%");
		updateGUIBody();
	}
	
	private void addContentLeft(String text) {
		ReceiverChat content = new ReceiverChat();
		content.setText(text);
		content.setWitdhTextArea(getPreferredSize().width);
		body.add(content, "wrap, w ::80%");
		updateGUIBody();
	}
	//end-receiver
	
	//sender
	public void showContentRight(String text, Date timeStamp) {
		SenderChat content = new SenderChat();
		content.setText(text, timeStamp);
		content.setWitdhTextArea(getPreferredSize().width);
		body.add(content, "wrap, al right, w ::80%");
		updateGUIBody();
	}
	
	public void addContentRight(String text) {
		SenderChat content = new SenderChat();
		content.setText(text);
		content.setWitdhTextArea(getPreferredSize().width);
		body.add(content, "wrap, al right, w ::80%");
		updateGUIBody();
	}
	//end-sender
	
	private void addDate(String date) {
		DateItem itemDate = new DateItem();
		itemDate.setDate(date);
		body.add(itemDate, "wrap, al center");
		updateGUIBody();
	}
	
	private void addImageLeft() {	
		ReceiverChat content = new ReceiverChat();
//		content.setAvatar();
		content.setImage(new ImageIcon(ReceiverChat.class.getResource("/icon/Truong_Ngoc_Anh_1.jpg")));
		content.setTime("15:50");
		
		body.add(content, "wrap, w ::80%");
		updateGUIBody();
	}
	
	public void addImageRight(ImageIcon imageIcon) {
		SenderChat content = new SenderChat();
		content.setImage(imageIcon);
		
		body.add(content, "wrap, al right, w ::80%");
		updateGUIBody();
	}
	
	private void btnAddFilesLeft(String fileName, String mb) {
		ReceiverChat content = new ReceiverChat();
//		content.setAvatar();
		content.setFile(fileName, mb);
		
		body.add(content, "wrap, w ::60%");
		updateGUIBody();
	}
	
	public void addFilesRight(String fileName, String mb) {
		SenderChat content = new SenderChat();
		content.setFile(fileName, mb);
		
		body.add(content, "wrap, al right, w ::60%");
		updateGUIBody();
	}
	
	public void refreshBody() {
		body.removeAll();
		updateGUIBody();
	}
}
