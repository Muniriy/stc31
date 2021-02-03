package task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OnlineStoreJdbc {

    private static final Logger log = LogManager.getLogger(OnlineStoreJdbc.class);

    /**
     * The entry point of OnlineStoreJdbc program.
     * It connects to PostgreSQL and creates 3 tables
     * considered in lesson 14 by safely dropping
     * previous table versions during each new run
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        try {
            connectDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates connection and renews the
     * database
     *
     * @throws ClassNotFoundException exception of not founding
     *                                a class
     * @throws SQLException           the SQL exception
     */
    private static void connectDatabase() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/online_store";
        String user = "postgres";
        String pass = "postgress";

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            log.info("DB is successfully connected");
            DBUtils.renewDatabase(connection);
            log.info("DB is renewed");
        }
    }

}
