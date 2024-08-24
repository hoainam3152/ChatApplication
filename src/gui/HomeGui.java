package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import component.ChatTitle;
import component.ItemPeople;
import dao.UserAccountDAO;
import event.EventHome;
import event.PublicEvent;
import net.miginfocom.swing.MigLayout;
import pojo.UserAccountPOJO;
import service.Client;
import swing.ImageAvatar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HomeGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ChatGui pnChat;
	JPanel pnBackGround;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public HomeGui(String phoneNumber) throws SQLException {
		UserAccountPOJO userInfo = Client.getInstance().getUser();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1222, 700);
		setTitle("NineLo - " + userInfo.getName());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// Đặt vị trí của cửa sổ so với trung tâm của màn hình
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
//		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.setLayout(new MigLayout("fillx, filly", "[]0[]0[grow]", "[grow,fill]"));
		
		JPanel pnTools = new JPanel();
		pnTools.setPreferredSize(new Dimension(46, 2));
		pnTools.setBackground(new Color(95, 78, 250));
		contentPane.add(pnTools, "cell 0 0");
		
		ImageAvatar avatar = new ImageAvatar();
		avatar.setPreferredSize(new Dimension(50, 50));
		avatar.setImage(new ImageIcon(ChatTitle.class.getResource("/icon/" + userInfo.getImageUrl())));
		avatar.setBorderSize(0);
		
		pnTools.add(avatar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(LoginGui.class.getResource("/icon/icons8-chat-36.png")));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		pnTools.add(btnNewButton);
		
		JPanel pnHome = new JPanel();
		pnHome.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnHome.setPreferredSize(new Dimension(340, 2));
		contentPane.add(pnHome, "cell 1 0");
		pnHome.setLayout(null);
		
		int widthPnHome = pnHome.getPreferredSize().width;
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, widthPnHome, 92);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnHome.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 92, widthPnHome, 561);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //khong xuat hien thanh cuon ngang
		pnHome.add(scrollPane);
		
		JLayeredPane pnListFriends = new JLayeredPane();
		pnListFriends.setLayout(new MigLayout("fillx", "0[]0", "1[]1"));
		scrollPane.setViewportView(pnListFriends);
		
		List<UserAccountPOJO> userList = Client.getInstance().getUserAccounts(phoneNumber);
		
		for (UserAccountPOJO user : userList) {
			pnListFriends.add(new ItemPeople(user), "wrap");
		}	
		
		pnChat = new ChatGui();
		pnChat.setPreferredSize(new Dimension(800, 2));
		contentPane.add(pnChat);
		pnChat.setVisible(false);
		
		
		initEvent();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Client.getInstance().logout(phoneNumber);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Client.getInstance().stopServer();
			}
		});
	}

	private void initEvent() {
		PublicEvent.getInstance().setEventHome(new EventHome() {
			
			@Override
			public void updateUser(UserAccountPOJO user) throws SQLException {
				pnChat.setReceiver(user);
			}
			
			@Override
			public void selectUser(UserAccountPOJO user) throws SQLException {
				pnChat.setReceiver(user);
				pnChat.setVisible(true);
			}
		});
	}
}
