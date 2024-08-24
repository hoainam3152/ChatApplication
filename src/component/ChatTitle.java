package component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import pojo.UserAccountPOJO;
import swing.ImageAvatar;
import javax.swing.JPanel;

public class ChatTitle extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	private UserAccountPOJO receiver;
	JLabel lbName, lbActive;
	ImageAvatar avatar;
	/**
	 * Create the panel.
	 */
	public ChatTitle() {
		setBackground(new Color(255, 255, 255));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(WIDTH, 76));
		setLayout(new BorderLayout(0, 0));
		setOpaque(true);
		
		JPanel pnAvatar = new JPanel();
		pnAvatar.setPreferredSize(new Dimension(76, 76));
		add(pnAvatar, BorderLayout.WEST);
		pnAvatar.setLayout(null);
		pnAvatar.setBackground(null);
		
		avatar = new ImageAvatar();
		avatar.setBounds(8, 7, 60, 60);
		avatar.setPreferredSize(new Dimension(76, 76));
		avatar.setBorderSize(0);
		pnAvatar.add(avatar);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		add(layeredPane_1, BorderLayout.CENTER);
		layeredPane_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		lbName = new JLabel();
		lbName.setFont(new Font("Times New Roman", Font.BOLD, 22));
		layeredPane_1.add(lbName);
		
		lbActive = new JLabel();
		lbActive.setForeground(new Color(105, 105, 105));
		lbActive.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		layeredPane_1.add(lbActive);
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane, BorderLayout.EAST);
	}
	
	public void setReceiver(UserAccountPOJO receiver) {
		this.receiver = receiver;
		lbName.setText(receiver.getName());
		avatar.setImage(new ImageIcon(ChatTitle.class.getResource("/icon/" + receiver.getImageUrl())));
		setStatus();
		updateGUIBody();
	}
	
	private void updateGUIBody() {
		repaint();		//vẽ lại một thành phần đồ họa (GUI) trên màn hình
		revalidate();	//kiểm tra xem kích thước hoặc vị trí của một thành phần đồ họa có thay đổi hay không
	}
	
	private void setStatus() {
		if (receiver.getStatus() == 1) {
			lbActive.setText("Đang truy cập");
		} else {
			lbActive.setText("Đang offline");
		} 
	}
}
