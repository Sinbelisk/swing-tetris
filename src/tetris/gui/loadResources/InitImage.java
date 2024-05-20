package tetris.gui.loadResources;

import javax.swing.*;

public class InitImage {
    public static ImageIcon BACKGROUND;

    static {
        java.net.URL imgURL = InitImage.class.getClassLoader().getResource("images/imageBackground.jpg");
        if (imgURL != null) {
            BACKGROUND = new ImageIcon(imgURL);
        } else {
            throw new RuntimeException("Image not found!");
        }
    }
}

