package component;

import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BorderLayout;

public class DateItem extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	JLabel lblNewLabel;
	
	public DateItem() {
		setBackground(new Color(192, 192, 192));
		setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Date");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(240, 240, 240));
		lblNewLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(lblNewLabel);
	}

	public void setDate(String date) {
		lblNewLabel.setText(date);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
		super.paintComponent(g);
	}
}
