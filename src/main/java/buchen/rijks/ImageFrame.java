package buchen.rijks;

import javax.swing.*;
import java.awt.*;

public class ImageFrame extends JFrame {
    public ImageFrame(ImageIcon imageIcon, String title, String artist) {
        setSize(700, 800);
        setTitle(title + " by " + artist);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Image originalImage = imageIcon.getImage();

        Image scaledImage = originalImage.getScaledInstance(800, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledImageIcon);
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}