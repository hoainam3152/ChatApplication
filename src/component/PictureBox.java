package component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import swing.ResizeImageIcon;
import javax.swing.BoxLayout;

public class PictureBox extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PictureBox() {
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}

	public void addImage(ImageIcon imageIcon) {
		int width = imageIcon.getIconWidth();
		int height = imageIcon.getIconHeight();
		
		if (width > height) {
			width = (int) (imageIcon.getIconWidth() * 0.8);
			height = (int) (imageIcon.getIconHeight() * 0.8);
		} else {
			width = (int) (imageIcon.getIconWidth() * 0.6);
			height = (int) (imageIcon.getIconHeight() * 0.6);
		}

		ImageIcon resizedImageIcon = ResizeImageIcon.resizeImageIcon(imageIcon, width, height);
		JLabel imageLabel = new JLabel(resizedImageIcon);
        add(imageLabel);
	}
}
