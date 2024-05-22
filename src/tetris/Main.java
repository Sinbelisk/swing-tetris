package tetris;

import tetris.db.DBManager;
import tetris.gui.MainMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        if(DBManager.createDatabaseIfNotExists() != 0) System.out.println("Table created");
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
