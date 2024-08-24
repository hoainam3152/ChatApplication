package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.UserAccountDAO;
import service.Client;

public class LoginGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPhone;
	private JTextField txtPassword;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui frame = new LoginGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("NineLo");
		lblNewLabel_1.setForeground(new Color(0, 128, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(137, 30, 171, 49);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Đăng nhập tài khoản");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(112, 104, 221, 29);
		getContentPane().add(lblNewLabel_2);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(40, 185, 370, 30);
		txtPhone.setColumns(10);
		txtPhone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		getContentPane().add(txtPhone);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtPassword.setBounds(40, 261, 370, 30);
		getContentPane().add(txtPassword);
		
		JButton btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnDangNhapActionPerformed();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDangNhap.setBounds(40, 316, 110, 29);
		getContentPane().add(btnDangNhap);
		
		JLabel lblNewLabel = new JLabel("Số điện thoại:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(40, 150, 225, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Mật khẩu");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(40, 226, 117, 24);
		contentPane.add(lblNewLabel_3);
		
		JButton btnDangKy = new JButton("Đăng ký");
		btnDangKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGui form = new RegisterGui();
				form.setVisible(true);
				dispose();
			}
		});
		btnDangKy.setBounds(300, 319, 110, 29);
		contentPane.add(btnDangKy);
	}

	private void btnDangNhapActionPerformed() throws SQLException {
		int x = UserAccountDAO.login(txtPhone.getText().trim(), txtPassword.getText().trim());
		if (x == 1) {
			Client.getInstance().start(txtPhone.getText());
			Client.getInstance().setUser(Client.getInstance().getUserAccount(txtPhone.getText()));
			HomeGui frame = new HomeGui(txtPhone.getText());
			frame.setVisible(true);
			dispose();
		} else if (x == -1) {
			JOptionPane.showMessageDialog(getContentPane(), "Tài khoản đăng được đăng nhập!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
	}
}
