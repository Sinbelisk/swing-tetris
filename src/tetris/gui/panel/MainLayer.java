package tetris.gui.panel;

import tetris.gui.Game;
import tetris.gui.MainMenu;
import tetris.gui.loadResources.InitSoundtrack;
import tetris.gui.loadResources.InitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainLayer extends JPanel {
    private final MainMenu menu;
    private final Game game;
    public static final String START_TEXT = "Start";
    public static final String SCORES_TEXT = "Scores";
    public static final String OPTIONS_TEXT = "Options";
    public static final String QUIT_TEXT = "Quit";
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

        start = new JButton(new MainMenuHandler(START_TEXT));
        scores = new JButton(new MainMenuHandler(SCORES_TEXT));
        options = new JButton(new MainMenuHandler(OPTIONS_TEXT));
        quit = new JButton(new MainMenuHandler(QUIT_TEXT));

        Dimension buttonSize = new Dimension(200, 50);
        start.setPreferredSize(buttonSize);
        scores.setPreferredSize(buttonSize);
        options.setPreferredSize(buttonSize);
        quit.setPreferredSize(buttonSize);

        panel.add(start);
        panel.add(scores);
        panel.add(options);
        panel.add(quit);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 0);
        panel.add(start, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(scores, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(options, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(quit, constraints);

        add(panel, BorderLayout.CENTER);

        InitSoundtrack.MENU_SOUNDTRACK.play();

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = InitImage.BACKGROUND.getImage();
        super.paintComponent(g);
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
            if (getValue(AbstractAction.NAME) == START_TEXT) {
                menu.showGame();
                game.startGameLoop();
            } else if (getValue(AbstractAction.NAME) == SCORES_TEXT) {

            } else if (getValue(AbstractAction.NAME) == OPTIONS_TEXT) {
                menu.showOptionPanel();
            } else if (getValue(AbstractAction.NAME) == QUIT_TEXT) {
                System.exit(0);
            }
        }
    }
}
