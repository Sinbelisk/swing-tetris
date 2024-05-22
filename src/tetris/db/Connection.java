package tetris.db;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    InputStream inputStream = getClass().getResourceAsStream("/tetris/db/tetrisDB.db");
    public java.sql.Connection connect() {
        java.sql.Connection conn = null;
        try {
            // Especifica la ruta completa al archivo de la base de datos
            String url = "jdbc:sqlite:"+inputStream;
            // Crea una conexión a la base de datos
            conn = DriverManager.getConnection(url);
            System.out.println("Connection is OK!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection connection = new Connection();
        connection.connect();
    }
}
