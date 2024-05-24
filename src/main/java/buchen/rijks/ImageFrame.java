package buchen.rijks;

import javax.swing.*;
import java.awt.*;

public class ImageFrame extends JFrame {
    public ImageFrame(ImageIcon imageIcon, String title, String artist) {
        setSize(800, 600);
        setTitle(title + " by " + artist);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel imageLabel = new JLabel(imageIcon);
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
