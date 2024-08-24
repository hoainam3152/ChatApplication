package component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import event.PublicEvent;
import gui.ChatGui;
import pojo.ChatPOJO;
import pojo.UserAccountPOJO;
import service.Client;

public class ChatBottom extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextArea txtChat;
	private UserAccountPOJO receiver;

	public ChatBottom() {
		setBackground(new Color(255, 255, 255));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(800, 100));
		setLayout(new BorderLayout(0, 0));
		
		JLayeredPane lpTools = new JLayeredPane();
		lpTools.setPreferredSize(new Dimension(WIDTH, 30));
		lpTools.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		add(lpTools, BorderLayout.NORTH);
		
		JButton btnSendImages = new JButton();
		customTools(btnSendImages, new ImageIcon(ChatGui.class.getResource("/icon/icons8-picture-26.png")));
		btnSendImages.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSendImagesActionPerformed();
			}
		});
		lpTools.add(btnSendImages);
		
		JButton btnSendFiles = new JButton();
		customTools(btnSendFiles, new ImageIcon(ChatGui.class.getResource("/icon/icons8-clip-26.png")));
		btnSendFiles.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnSendFilesActionPerformed();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		lpTools.add(btnSendFiles);
		
		JPanel lpChat = new JPanel();
		lpChat.setPreferredSize(new Dimension(70, 70));
		lpChat.setBackground(Color.WHITE);
		add(lpChat);
		lpChat.setLayout(new BorderLayout(0, 0));
		
		JButton btnSent = new JButton();
		customTools(btnSent, new ImageIcon(ChatGui.class.getResource("/icon/icons8-sent-50.png")));
		btnSent.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = txtChat.getText().trim();
				if (!text.isEmpty()) {
					ChatPOJO contentChat = new ChatPOJO(Client.getInstance().getUser().getPhoneNumber(), receiver.getPhoneNumber(), text, new Date());
					int x = Client.getInstance().sendMessage(contentChat);
					if (x > 0) {
						PublicEvent.getInstance().getEventChat().sendMessage(contentChat);
						txtChat.setText("");
						txtChat.grabFocus();
						validate();
					} else {
						JOptionPane.showMessageDialog(getParent(), "Lỗi gửi", "Thông báo", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					txtChat.grabFocus();
				}
			}
		});
		lpChat.add(btnSent, BorderLayout.EAST);
//		add(lpChat);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		lpChat.add(scrollPane, BorderLayout.CENTER);
		
		txtChat = new JTextArea();
		txtChat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtChat.setLineWrap(true);
		txtChat.setWrapStyleWord(true);
		txtChat.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		scrollPane.setViewportView(txtChat);
	}

	private void customTools(JButton btn, ImageIcon imageIcon) {
		btn.setIcon(imageIcon);
		
		//loại bỏ bất kỳ khoảng cách mặc định nào xung quanh nội dung của JButton
		btn.setMargin(new Insets(0, 0, 0, 0));
		//Vô hiệu hóa việc vẽ đường viền nút mặc định.
		btn.setBorderPainted(false); 
		//Làm cho khu vực nội dung của nút trở nên trong suốt.
		btn.setContentAreaFilled(false); 
		//Vô hiệu hóa việc vẽ chỉ báo tiêu điểm khi nút có tiêu điểm.
		btn.setFocusPainted(false); 
		//Đặt độ mờ của nút thành false, làm cho nó hoàn toàn trong suốt
		btn.setOpaque(false);
	}
	
	public void setReceiver(UserAccountPOJO receiver) {
		this.receiver = receiver;
	}
	
	private void btnSendImagesActionPerformed() {
		JFileChooser fileChooser = new JFileChooser();
        // Cài đặt chế độ chọn tệp để cho phép chọn nhiều tệp
        fileChooser.setMultiSelectionEnabled(true);
        // Cài đặt bộ lọc để chỉ chọn tệp hình ảnh
        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            for (File file : fileChooser.getSelectedFiles()) {
                try {
                    ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                    PublicEvent.getInstance().getEventChat().sendImage(imageIcon);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
	}
	
	private void btnSendFilesActionPerformed() throws IOException {
            JFileChooser fileChooser = new JFileChooser();
            
            fileChooser.setMultiSelectionEnabled(true);
            
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
//                Client.getInstance().sendFile(selectedFile);
                long fileSizeInBytes = selectedFile.length();
                double sizeInKB = fileSizeInBytes / 1024.0;
                double sizeInMB = sizeInKB / 1024.0;
                double sizeInGB = sizeInMB / 1024.0;
                
                if (sizeInMB < 1) {
                	PublicEvent.getInstance().getEventChat().sendFile(selectedFile.getName(), formattedSize(sizeInKB) + " KB");
				} else if (sizeInGB < 1) {
					PublicEvent.getInstance().getEventChat().sendFile(selectedFile.getName(), formattedSize(sizeInMB) + " MB");
				} else {
					PublicEvent.getInstance().getEventChat().sendFile(selectedFile.getName(), formattedSize(sizeInGB) + " GB");
				}
            }
	}
	
	private String formattedSize (double size) {
		DecimalFormat df = new DecimalFormat("#.##");
		String fs = df.format(size);
		return fs;
	}
}
