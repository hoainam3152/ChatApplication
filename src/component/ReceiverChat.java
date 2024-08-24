package component;

import javax.swing.JTextArea;

import support.DateTimeChat;
import swing.ImageAvatar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class ReceiverChat extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	JTextArea txtMessage;
	JLabel lbTime;
	JLayeredPane pnContent, pnAvatar;
	ImageAvatar avatar;
	
	public ReceiverChat() {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.WHITE);
		
		pnAvatar = new JLayeredPane();
		pnAvatar.setPreferredSize(new Dimension(70, HEIGHT));
		add(pnAvatar, BorderLayout.WEST);
		
		avatar = new ImageAvatar();
		avatar.setBounds(10, 0, 50, 50);
		avatar.setBorderSize(0);
		pnAvatar.add(avatar);
		
		pnContent = new JLayeredPane() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(getBackground());
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
				super.paintComponent(g);
			}
		};
		pnContent.setLayout(new BoxLayout(pnContent, BoxLayout.PAGE_AXIS));
		add(pnContent, BorderLayout.CENTER);
	}
	
	public void setAvatar(String imageName) {
		avatar.setImage(new ImageIcon(ReceiverChat.class.getResource("/icon/" + imageName)));
	}
	
	public void setText(String text) {
		txtMessage = new JTextArea();
		txtMessage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMessage.setBackground(new Color(255, 255, 255));

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
		
		pnContent.add(txtMessage);
		setTime(DateTimeChat.getTimeCurrent());
	}
	
	public void setText(String text, Date timeStamp) {
		txtMessage = new JTextArea();
		txtMessage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMessage.setBackground(new Color(255, 255, 255));

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
		
		pnContent.add(txtMessage);
		setTime(timeStamp);
	}
	
	public void setWitdhTextArea(int witdh) {
		Dimension preferredSize = txtMessage.getPreferredSize();
		txtMessage.setSize(new Dimension(witdh, preferredSize.height));
		pnContent.setSize(new Dimension(witdh, preferredSize.height));
	}
	
	public void setTime(String time) {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		pnContent.add(layeredPane);
		
		lbTime = new JLabel("Time");
		lbTime.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbTime.setForeground(new Color(105, 105, 105));
		lbTime.setText(time);
		layeredPane.add(lbTime);
	}
	
	public void setTime(Date timeStamp) {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		pnContent.add(layeredPane);
		
		lbTime = new JLabel("Time");
		lbTime.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbTime.setForeground(new Color(105, 105, 105));
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String formattedTimeStamp = formatter.format(timeStamp);
		
		lbTime.setText(formattedTimeStamp);
		layeredPane.add(lbTime);
	}
	
	public void setImage(ImageIcon imageIcon) {
		JLayeredPane lpImage = new JLayeredPane();
		lpImage.setLayout(new BorderLayout(0, 0));
		
		PictureBox imagePanel = new PictureBox();
		imagePanel.addImage(imageIcon);
		lpImage.add(imagePanel, BorderLayout.CENTER);
		
		pnContent.add(lpImage);
		setBackground(null);
	}
	
	
	
	public void setFile(File file) {
		JLayeredPane lpFile = new JLayeredPane();
		lpFile.setLayout(new BorderLayout());
		
//		FileItem fi = new FileItem(file);
//		lpFile.add(fi);
		
//		pnContent.add(fi);
	}
	
	public void setFile(String fileName, String mb) {
		JLayeredPane lpFile = new JLayeredPane();
		lpFile.setLayout(new BorderLayout(0, 0));
		
		FileItem fi = new FileItem(fileName, mb);
		lpFile.add(fi, BorderLayout.CENTER);
		
		pnContent.add(lpFile);
	}
}
