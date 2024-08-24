package gui;

import java.awt.Color;
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

public class RegisterGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPhone;
	private JTextField txtPassword;
	private JTextField txtTen;
	/**
	 * Create the panel.
	 */
	public RegisterGui() {
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
		
		JLabel lblNewLabel_2 = new JLabel("Đăng ký tài khoản");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(112, 104, 221, 29);
		getContentPane().add(lblNewLabel_2);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(38, 266, 370, 30);
		txtPhone.setColumns(10);
		txtPhone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		getContentPane().add(txtPhone);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtPassword.setBounds(38, 342, 370, 30);
		getContentPane().add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("Số điện thoại:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(38, 231, 225, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Mật khẩu");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(38, 307, 117, 24);
		contentPane.add(lblNewLabel_3);
		
		JButton btnDangKy = new JButton("Đăng ký");
		btnDangKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {				
					btnDangKyActionPerformed();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDangKy.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnDangKy.setBounds(38, 398, 110, 37);
		contentPane.add(btnDangKy);
		
		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		txtTen.setBounds(38, 190, 370, 30);
		contentPane.add(txtTen);
		
		JLabel lblTnNgiDng = new JLabel("Tên người dùng:");
		lblTnNgiDng.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTnNgiDng.setBounds(38, 155, 225, 24);
		contentPane.add(lblTnNgiDng);
		
		JButton btnTroLai = new JButton("Trở lại");
		btnTroLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGui form = new LoginGui();
				form.setVisible(true);
				dispose();
			}
		});
		btnTroLai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTroLai.setBounds(298, 398, 110, 37);
		contentPane.add(btnTroLai);
	}

		private void btnDangKyActionPerformed() throws SQLException {
			if (txtTen.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(getContentPane(), "Bạn chưa nhập tên người dùng!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if (txtPhone.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(getContentPane(), "Bạn chưa nhập số điện thoại!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if (txtPassword.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(getContentPane(), "Bạn chưa nhập mật khẩu!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			int x = UserAccountDAO.register(txtTen.getText().trim(), txtPhone.getText().trim(), txtPassword.getText().trim());
			if (x > 0) {
				Client.getInstance().start(txtPhone.getText());
				Client.getInstance().setUser(Client.getInstance().getUserAccount(txtPhone.getText()));
				HomeGui frame = new HomeGui(txtPhone.getText());
				frame.setVisible(true);
				dispose();
			} else if (x == -1) {
				JOptionPane.showMessageDialog(getContentPane(), "Số điện thoại đã tồn tại", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Thất bại!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
}
