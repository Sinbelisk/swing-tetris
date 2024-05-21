package tetris.gui;

import tetris.gui.loadResources.InitSoundtrack;
import tetris.gui.panel.*;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public static final String APP_NAME = "Tetris With Swing";
    private static final ImageIcon ICON = new ImageIcon("resources/images/icon.png");
    public static final int MAIN_MENU_WIDTH = 510;
    public static final int MAIN_MENU_LENGTH = 535;
    private static final String MAIN_PANEL = "MainPanel";
    private static final String OPTION_PANEL = "OptionPanel";
    private static final String DB_PANEL = "DBPanel";
    private static final String GAME_PANEL = "GamePanel";
    private static final String PAUSE_PANEL = "PausePanel";
    public static final Game game = new Game();
    public GamePanel gamePanel = new GamePanel(this, game);
    public MainLayer mainPanel = new MainLayer(this, game);
    public OptionLayer infoPanel = new OptionLayer(this);
    public DBPanel dbPanel = new DBPanel(this);
    public PausePanel pausePanel = new PausePanel(this, game);
    private CardLayout cardLayout;
    private JPanel mainContainer;
    public MainMenu() {
        init();
    }

    public void showMainLayer() {
        InitSoundtrack.GAME_SOUNDTRACK.stop();
        InitSoundtrack.MENU_SOUNDTRACK.play();
        cardLayout.show(mainContainer, MAIN_PANEL);
    }

    public void showOptionPanel() {
        cardLayout.show(mainContainer, OPTION_PANEL);
    }

    public void showGame() {
        InitSoundtrack.MENU_SOUNDTRACK.stop();
        InitSoundtrack.GAME_SOUNDTRACK.play();
        cardLayout.show(mainContainer, GAME_PANEL);
    }

    public void showDBLayer() {
        cardLayout.show(mainContainer, DB_PANEL);
    }

    public void showPauseLayer() {
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
        mainContainer.setLayout(cardLayout);

        mainContainer.add(mainPanel, MAIN_PANEL);
        mainContainer.add(infoPanel, OPTION_PANEL);
        mainContainer.add(pausePanel, PAUSE_PANEL);
        mainContainer.add(dbPanel, DB_PANEL);
        mainContainer.add(gamePanel, GAME_PANEL);

        add(mainContainer);

        setVisible(true);
    }
}
