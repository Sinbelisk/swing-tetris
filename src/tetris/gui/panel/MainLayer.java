package tetris.gui.panel;

import tetris.Main;
import tetris.gui.Game;
import tetris.gui.MainMenu;
import tetris.gui.loadResources.InitSoundtrack;
import tetris.gui.loadResources.InitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainLayer extends JPanel {
    private final MainMenu menu;
    private Game game;
    public static final String START_TEXT = "Start";
    public static final String SCORES_TEXT = "Scores";
    public static final String OPTIONS_TEXT = "Options";
    public static final String QUIT_TEXT = "Quit";
    public static final String PABLO_NAME = "Pablo Martínez Pedrosa";
    public static final String RAFAEL_NAME = "Rafael Francisco Jimenez Rayo";
    public static final String PEDRO_NAME = "Pedro Garcia Odero";
    public static final String NAMES = PABLO_NAME + " | " + RAFAEL_NAME + " | " + PEDRO_NAME;
    public JButton start;
    public JButton scores;
    public JButton options;
    public JButton quit;

    public MainLayer(MainMenu menu, Game game) {
        this.menu = menu;
        this.game = game;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel(MainMenu.APP_NAME);
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel pabloLabel = new JLabel(PABLO_NAME);
        JLabel rafaelLabel = new JLabel(RAFAEL_NAME);
        JLabel pedroLabel = new JLabel(PEDRO_NAME);

        pabloLabel.setForeground(Color.WHITE);
        rafaelLabel.setForeground(Color.WHITE);
        pedroLabel.setForeground(Color.WHITE);

        start = new JButton(new MainMenuHandler(START_TEXT));
        scores = new JButton(new MainMenuHandler(SCORES_TEXT));
        options = new JButton(new MainMenuHandler(OPTIONS_TEXT));
        quit = new JButton(new MainMenuHandler(QUIT_TEXT));

        Dimension buttonSize = new Dimension(200, 50);
        start.setPreferredSize(buttonSize);
        scores.setPreferredSize(buttonSize);
        options.setPreferredSize(buttonSize);
        quit.setPreferredSize(buttonSize);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.insets = new Insets(10, 0, 0, 0);

        constraints.gridy = 1;
        panel.add(start, constraints);

        constraints.gridy = 2;
        panel.add(scores, constraints);

        constraints.gridy = 3;
        panel.add(options, constraints);

        constraints.gridy = 4;
        panel.add(quit, constraints);

        constraints.gridy = 5;
        panel.add(pabloLabel, constraints);

        constraints.gridy = 6;
        panel.add(rafaelLabel, constraints);

        constraints.gridy = 7;
        panel.add(pedroLabel, constraints);

        add(panel, BorderLayout.CENTER);

        InitSoundtrack.MENU_SOUNDTRACK.play();

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = InitImage.BACKGROUND.getImage();
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private class MainMenuHandler extends AbstractAction {
        public MainMenuHandler(String name) {
            putValue(AbstractAction.NAME, name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (getValue(AbstractAction.NAME).equals(START_TEXT)) {
                menu.showGame();
                game.startGameLoop();
            } else if (getValue(AbstractAction.NAME).equals(SCORES_TEXT)) {
                menu.showDBLayer();
            } else if (getValue(AbstractAction.NAME).equals(OPTIONS_TEXT)) {
                menu.showOptionPanel();
            } else if (getValue(AbstractAction.NAME).equals(QUIT_TEXT)) {
                System.exit(0);
            }
        }
    }
}
