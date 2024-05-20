package tetris.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testConnection {
    public  Connection connect() {
        Connection conn = null;
        try {
            // Especifica la ruta completa al archivo de la base de datos
            String url = "jdbc:sqlite:src/tetris/db/tetrisDB.db";
            // Crea una conexión a la base de datos
            conn = DriverManager.getConnection(url);
            System.out.println("Conexión a SQLite establecida.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        testConnection connection =new  testConnection();
        connection.connect();
    }


}
