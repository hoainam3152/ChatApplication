package component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import support.DateTimeChat;

public class SenderChat extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextArea txtMessage;
	
	public SenderChat() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(new Color(218, 240, 247));
		setOpaque(false);
	}
	
	public void setText(String text) {
		txtMessage = new JTextArea();
		txtMessage.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		//Xác định tính trong suốt của một thành phần
		//Khi setOpaque() được đặt thành false, 
		//thành phần sẽ không vẽ nền của nó và nền của thành phần cha sẽ hiển thị qua.
		txtMessage.setOpaque(false);
		
		//Tao khoảng trống bên trong để tạo khoảng cách giữa văn bản và viền.
		txtMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		txtMessage.setEditable(false);		//khong cho phep chinh sua
		txtMessage.setLineWrap(true);		//tự động xuống dòng cho văn bản
		txtMessage.setWrapStyleWord(true);	// Xuống dòng theo từ.
		
		txtMessage.setText(text);
		add(txtMessage);
		setTime();
	}
	
	public void setText(String text, Date timeStamp) {
		txtMessage = new JTextArea();
		txtMessage.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		//Xác định tính trong suốt của một thành phần
		//Khi setOpaque() được đặt thành false, 
		//thành phần sẽ không vẽ nền của nó và nền của thành phần cha sẽ hiển thị qua.
		txtMessage.setOpaque(false);
		
		//Tao khoảng trống bên trong để tạo khoảng cách giữa văn bản và viền.
		txtMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		txtMessage.setEditable(false);		//khong cho phep chinh sua
		txtMessage.setLineWrap(true);		//tự động xuống dòng cho văn bản
		txtMessage.setWrapStyleWord(true);	// Xuống dòng theo từ.
		
		txtMessage.setText(text);
		add(txtMessage);
		setTime(timeStamp);
	}
	
	public void setWitdhTextArea(int width) {
		Dimension preferredSize = txtMessage.getPreferredSize();
		txtMessage.setSize(new Dimension(width, preferredSize.height));
		setSize(new Dimension(width, preferredSize.height));
	}
	
	public void setImage(ImageIcon imageIcon) {
		JLayeredPane lpImage = new JLayeredPane();
		lpImage.setLayout(new BorderLayout(0, 0));
		
		PictureBox imagePanel = new PictureBox();
		imagePanel.addImage(imageIcon);
		lpImage.add(imagePanel, BorderLayout.CENTER);
		add(lpImage);
		setTime();
		
		setBackground(null);
	}
	
	public void setTime() {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		add(layeredPane);
		
		JLabel lbTime = new JLabel("Time");
		lbTime.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbTime.setForeground(new Color(105, 105, 105));
		lbTime.setText(DateTimeChat.getTimeCurrent());
		layeredPane.add(lbTime);
	}
	
	private void setTime(Date timeStamp) {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		add(layeredPane);
		
		JLabel lbTime = new JLabel();
		lbTime.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbTime.setForeground(new Color(105, 105, 105));
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String formattedTimeStamp = formatter.format(timeStamp);
		lbTime.setText(formattedTimeStamp);
		layeredPane.add(lbTime);
	}
	
	public void setFile(String fileName, String mb) {
		JLayeredPane lpFile = new JLayeredPane();
		lpFile.setLayout(new BorderLayout(0, 0));
		
		FileItem fi = new FileItem(fileName, mb);
		lpFile.add(fi, BorderLayout.CENTER);
		
		add(lpFile);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
		super.paintComponent(g);
	}
}
