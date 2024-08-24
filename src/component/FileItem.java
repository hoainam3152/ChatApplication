package component;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import support.DateTimeChat;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class FileItem extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel lbTime;
	/**
	 * Create the panel.
	 */
//	public FileItem(File file) {
	public FileItem(String fileName, String mb) {
		setPreferredSize(new Dimension(400, 80));
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		
		JLayeredPane lpFileIcon = new JLayeredPane();
		add(lpFileIcon, BorderLayout.WEST);
		lpFileIcon.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FileItem.class.getResource("/icon/icons8-document-70.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lpFileIcon.add(lblNewLabel, BorderLayout.CENTER);
		
		JLayeredPane lpFile = new JLayeredPane();
		add(lpFile, BorderLayout.CENTER);
		lpFile.setLayout(new BoxLayout(lpFile, BoxLayout.Y_AXIS));
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		lpFile.add(layeredPane_2);
		
		JLabel lbFileName = new JLabel(fileName);
		lbFileName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lbFileName.setBounds(10, 11, 300, 29);
		layeredPane_2.add(lbFileName);
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		lpFile.add(layeredPane_3);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(220, 0, 40, 40);
		layeredPane_3.add(lblNewLabel_3);
		
		JLabel lbMB = new JLabel(mb);
		lbMB.setForeground(new Color(128, 128, 128));
		lbMB.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbMB.setBounds(10, 11, 200, 18);
		layeredPane_3.add(lbMB);
		
		JButton btnDownload = new JButton("") {
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
		btnDownload.setIcon(new ImageIcon(FileItem.class.getResource("/icon/icons8-downloading-updates-20.png")));
		btnDownload.setBounds(270, 5, 30, 30);
		btnDownload.setMargin(new Insets(0, 0, 0, 0));
		btnDownload.setBorderPainted(false); 
		btnDownload.setContentAreaFilled(false); 
		btnDownload.setFocusPainted(false); 
		layeredPane_3.add(btnDownload);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		add(layeredPane, BorderLayout.SOUTH);
		
		lbTime = new JLabel("Time");
		lbTime.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbTime.setForeground(new Color(105, 105, 105));
		lbTime.setText(DateTimeChat.getTimeCurrent());
		layeredPane.add(lbTime);
	}
}
