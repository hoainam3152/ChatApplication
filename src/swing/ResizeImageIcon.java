package swing;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ResizeImageIcon {
	public static ImageIcon resizeImageIcon(ImageIcon imageIcon, int width, int height) {
        Image originalImage = imageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
