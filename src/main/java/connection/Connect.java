package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class Connect {

    private final Dotenv dotenv = Dotenv.load();
    private String driver = "org.postgresql.Driver";
    private String url = dotenv.get("DB_URL");
    private String user = dotenv.get("DB_USER");
    private String pass = dotenv.get("DB_PASS");
    private Connection connection;

    public Connect() {
        connection = makeConnection();
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = makeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas sprawdzania połączenia: " + e.getMessage());
        }
        return connection;
    }


    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException sqle) {
            System.err.println("Błąd przy zamykaniu połączenia: " + sqle.getMessage());
        }
    }


    public Connection makeConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Błąd podczas nawiązywania połączenia: " + e.getMessage());
            return null;
        }
    }
}
