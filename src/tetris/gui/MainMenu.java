package tetris.gui;

import tetris.gui.loadResources.InitSoundtrack;
import tetris.gui.loadResources.LoadSoundtrack;
import tetris.gui.panel.GamePanel;
import tetris.gui.panel.OptionLayer;
import tetris.gui.panel.MainLayer;
import tetris.gui.panel.PausePanel;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public static final String APP_NAME = "Tetris With Swing";
    private static final ImageIcon ICON = new ImageIcon("resources/images/icon.png");
    public static final int MAIN_MENU_WIDTH = 265;
    public static final int MAIN_MENU_LENGTH = 535;
    private static final String MAIN_PANEL = "MainPanel";
    private static final String OPTION_PANEL = "OptionPanel";
    private static final String GAME_PANEL = "GamePanel";
    private static final String PAUSE_PANEL = "PausePanel";
    public static final Game game = new Game();
    public static GamePanel gamePanel = new GamePanel();
    public static MainLayer mainPanel = new MainLayer();
    public static OptionLayer infoPanel = new OptionLayer();
    public static PausePanel pausePanel = new PausePanel();
    private static CardLayout cardLayout;
    private static JPanel mainContainer;
    public MainMenu() {
        init();
    }

    public static void showMainLayer() {
        InitSoundtrack.GAME_SOUNDTRACK.stop();
        InitSoundtrack.MENU_SOUNDTRACK.play();
        cardLayout.show(mainContainer, MAIN_PANEL);
    }

    public static void showOptionPanel() {
        cardLayout.show(mainContainer, OPTION_PANEL);
    }

    public static void showGame() {
        InitSoundtrack.MENU_SOUNDTRACK.stop();
        InitSoundtrack.GAME_SOUNDTRACK.play();
        cardLayout.show(mainContainer, GAME_PANEL);
    }

    public static void showPauseLayer() {
        InitSoundtrack.GAME_SOUNDTRACK.stop();
        cardLayout.show(mainContainer, PAUSE_PANEL);
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
        pausePanel = new PausePanel();
        mainContainer.setLayout(cardLayout);

        mainContainer.add(mainPanel, MAIN_PANEL);
        mainContainer.add(infoPanel, OPTION_PANEL);
        mainContainer.add(pausePanel, PAUSE_PANEL);
        mainContainer.add(gamePanel, GAME_PANEL);

        add(mainContainer);

        setVisible(true);
    }

    public static void initGame() {
        gamePanel.add(game, BorderLayout.CENTER);
        gamePanel.setFocusable(false);
        gamePanel.setVisible(true);

        game.startGameLoop();
    }
}
