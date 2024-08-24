package component;

import javax.swing.JPanel;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import event.PublicEvent;
import pojo.UserAccountPOJO;
import service.Client;
import swing.ImageAvatar;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ItemPeople extends JPanel {

	private static final long serialVersionUID = 1L;
	private final UserAccountPOJO user;
	private boolean mouseOver;

	Color color;

	public ItemPeople(UserAccountPOJO user) {
		this.user = user;
		initComponents();
		initMouseEvent();
    }
	
	public void initComponents() {
		setPreferredSize(new Dimension(320, 80)); // Đặt chiều cao 100 cho các JPanel con
	    Random random = new Random();

        // Tạo ba số nguyên ngẫu nhiên trong phạm vi 0 đến 255 cho các thành phần Red, Green và Blue
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // Tạo một đối tượng Color từ các giá trị Red, Green và Blue
        color = new Color(red, green, blue);
	    setBackground(color);
	    setLayout(null);
	    
	    ImageAvatar avatar = new ImageAvatar();
		avatar.setPreferredSize(new Dimension(52, 52));
		avatar.setImage(new ImageIcon(ChatTitle.class.getResource("/icon/" + user.getImageUrl())));
		avatar.setBorderSize(0);
		avatar.setBounds(10, 11, 58, 58);
	    add(avatar);
	    
	    JLabel lbName = new JLabel(user.getName());
	    lbName.setFont(new Font("Times New Roman", Font.BOLD, 24));
	    lbName.setBounds(78, 11, 200, 27);
	    add(lbName);
	    
	    JLabel lbMassage = new JLabel("Message");
	    lbMassage.setFont(new Font("Times New Roman", Font.BOLD, 18));
	    lbMassage.setBounds(78, 49, 200, 20);
	    add(lbMassage);
	}
	
	public void initMouseEvent() {
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(230, 230, 230));
				mouseOver = true;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Color(242, 242, 242));
				mouseOver = false;
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (mouseOver) {
					UserAccountPOJO userSelected = null;
					try {
						userSelected = Client.getInstance().getUserAccount(user.getPhoneNumber());
						PublicEvent.getInstance().getEventHome().selectUser(userSelected);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
