package tetris.gui.panel;

import javax.swing.*;
import java.awt.*;

public class MainLayer extends JPanel {
    public static final String START_TEXT = "Start";
    public static final String CONTROLS_TEXT = "Controls";
    public static final String QUIT_TEXT = "Quit";
    public static JButton start;
    public static JButton controls;
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

        start = new JButton(START_TEXT);
        controls = new JButton(CONTROLS_TEXT);
        quit = new JButton(QUIT_TEXT);

        Dimension buttonSize = new Dimension(200, 50);
        start.setPreferredSize(buttonSize);
        controls.setPreferredSize(buttonSize);
        quit.setPreferredSize(buttonSize);

        panel.add(start);
        panel.add(controls);
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
        panel.add(quit, constraints);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
}
