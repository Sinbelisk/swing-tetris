package tetris.gui.panel;

import tetris.Main;
import tetris.gui.Game;
import tetris.gui.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainLayer extends JPanel {
    private final Image background = MainMenu.BACKGROUND.getImage();
    public static final String START_TEXT = "Start";
    public static final String CONTROLS_TEXT = "Controls";
    public static final String SCORES_TEXT = "Scores";
    public static final String QUIT_TEXT = "Quit";
    public static JButton start;
    public static JButton controls;
    public static JButton scores;
    public static JButton quit;

    public MainLayer() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel label = new JLabel("Tetris");
        panel.add(label);

        start = new JButton(new MainMenuHandler(START_TEXT));
        controls = new JButton(new MainMenuHandler(CONTROLS_TEXT));
        scores = new JButton(new MainMenuHandler(SCORES_TEXT));
        quit = new JButton(new MainMenuHandler(QUIT_TEXT));

        Dimension buttonSize = new Dimension(200, 50);
        start.setPreferredSize(buttonSize);
        controls.setPreferredSize(buttonSize);
        scores.setPreferredSize(buttonSize);
        quit.setPreferredSize(buttonSize);

        panel.add(start);
        panel.add(controls);
        panel.add(scores);
        panel.add(quit);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 0);
        panel.add(start, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(controls, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(scores, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(quit, constraints);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static class MainMenuHandler extends AbstractAction {
        public MainMenuHandler(String name) {
            putValue(AbstractAction.NAME, name);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (getValue(AbstractAction.NAME) == START_TEXT) {
                MainMenu.showGame();
                //MainMenu.initGame();
            } else if (getValue(AbstractAction.NAME) == CONTROLS_TEXT) {
                MainMenu.showInfoPanel();
            } else if (getValue(AbstractAction.NAME) == SCORES_TEXT) {

            } else if (getValue(AbstractAction.NAME) == QUIT_TEXT) {
                System.exit(0);
            }
        }
    }
}
