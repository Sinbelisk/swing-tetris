package tetris.swing;

import tetris.swing.panel.MainLayer;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public static final String APP_NAME = "Tetris With Swing";
    public static final int MAIN_MENU_WIDTH = 600;
    public static final int MAIN_MENU_LENGTH = 800;
    public MainMenu() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(APP_NAME);
        setSize(MAIN_MENU_WIDTH, MAIN_MENU_LENGTH);
        setLocationRelativeTo(null);


        MainLayer mainLayer = new MainLayer();
        add(mainLayer);

        setVisible(true);
    }
}
