package tetris.gui.panel;

import tetris.gui.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InfoLayer extends JPanel {
    public static final String BACK_TEXT = "Back";
    public static JButton back;
    public static JLabel controls;

    public InfoLayer() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        back = new JButton(new BackHandler(BACK_TEXT));

        controls = new JLabel("so");

        Dimension buttonSize = new Dimension(200, 50);
        back.setPreferredSize(buttonSize);

        panel.add(controls);
        panel.add(back);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 0);
        panel.add(controls, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(back, constraints);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static class BackHandler extends AbstractAction {
        public BackHandler(String name) {
            putValue(AbstractAction.NAME, name);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            MainMenu.showMainLayer();
        }
    }
}
