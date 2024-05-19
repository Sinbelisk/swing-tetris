package tetris.gui;

import tetris.gui.loadResources.InitSoundtrack;
import tetris.gui.loadResources.LoadSoundtrack;
import tetris.gui.panel.GamePanel;
import tetris.gui.panel.InfoLayer;
import tetris.gui.panel.MainLayer;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public static final String APP_NAME = "Tetris With Swing";
    private static final ImageIcon ICON = new ImageIcon("resources/icon.png");
    public static final int MAIN_MENU_WIDTH = 265;
    public static final int MAIN_MENU_LENGTH = 535;
    private static final String MAIN_PANEL = "MainPanel";
    private static final String INFO_PANEL = "InfoPanel";
    private static final String GAME_PANEL = "GamePanel";
    private static MainLayer mainPanel = new MainLayer();
    private static InfoLayer infoPanel = new InfoLayer();
    private static GamePanel gamePanel = new GamePanel();
    private static CardLayout cardLayout;
    private static JPanel mainContainer;
    public MainMenu() {
        init();
    }

    public static void showMainLayer() {
        cardLayout.show(mainContainer, MAIN_PANEL);
    }

    public static void showInfoPanel() {
        cardLayout.show(mainContainer, INFO_PANEL);
    }

    public static void showGame() {
        cardLayout.show(mainContainer, GAME_PANEL);
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(APP_NAME);
        setIconImage(ICON.getImage());
        setSize(MAIN_MENU_WIDTH, MAIN_MENU_LENGTH);
        setLocationRelativeTo(null);
        setFocusable(false);

        mainContainer = new JPanel();
        cardLayout = new CardLayout();
        mainContainer.setLayout(cardLayout);

        mainContainer.add(mainPanel, MAIN_PANEL);
        mainContainer.add(infoPanel, INFO_PANEL);
        mainContainer.add(gamePanel, GAME_PANEL);

        add(mainContainer);

        setVisible(true);
    }

    public static void initGame() {
        Game game = new Game();
        gamePanel.add(game, BorderLayout.CENTER);
        gamePanel.setFocusable(false);
        gamePanel.setVisible(true);

        game.startGameLoop();
    }
}
