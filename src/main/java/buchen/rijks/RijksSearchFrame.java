package buchen.rijks;

import com.andrewoid.ApiKey;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

public class RijksSearchFrame extends JFrame {
    private final JTextField searchField;
    private final JPanel imagePanel;
    private int page = 0;
    RijksService service = new RijksServiceFactory().create();

    public RijksSearchFrame() {
        setSize(700, 800);
        setTitle("Rijks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        searchField = new JTextField();
        searchField.setText("Rembrandt van Rijn");

        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton searchButton = new JButton ("Search");

        nextButton.addActionListener(e -> {
            page++;
            update();
        });
        prevButton.addActionListener(e -> {
            if (page > 0) {
                page--;
                update();
            }
        });
        searchButton.addActionListener(e -> {
            update();
        });

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(prevButton, BorderLayout.WEST);
        searchPanel.add(nextButton, BorderLayout.EAST);
        searchPanel.add(searchButton, BorderLayout.AFTER_LAST_LINE);

        imagePanel = new JPanel(new GridLayout(0, 5));
        add(searchPanel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.CENTER);

        update();
    }

    private void update() {
        SwingUtilities.invokeLater(() -> {
            ApiKey apiKey = new ApiKey();

            if (!searchField.getText().isEmpty()) {
                service.query(apiKey.get(), page, searchField.getText())
                        .subscribeOn(Schedulers.io())
                        .observeOn(SwingSchedulers.edt())
                        .subscribe(this::handleResponse,
                                Throwable::printStackTrace);
           }
            else {
                service.page(apiKey.get(), page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(SwingSchedulers.edt())
                        .subscribe(this::handleResponse,
                                Throwable::printStackTrace);}
        });
    }
    private void handleResponse(ArtObjects collection) {
        imagePanel.removeAll();

        ArtObject[] artObjects = collection.artObjects;
        imagePanel.setLayout(new GridLayout(2, 5));

        for (ArtObject artObject : artObjects) {
            String imageUrl = artObject.webImage.url;
            String title = artObject.title;
            String artist = artObject.principalOrFirstMaker;
            try {
                BufferedImage originalImage = ImageIO.read(new URL(imageUrl));
                Image scaledImage = originalImage.getScaledInstance(200, -1, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImage);
                JLabel label = new JLabel(icon);
                label.setToolTipText("<html><b>Title:</b> " + title + "<br><b>Artist:</b> "
                        + artist + "</html>");
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        new ImageFrame(icon, title, artist);
                    }
                });
                imagePanel.add(label);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        imagePanel.revalidate();
    }

}