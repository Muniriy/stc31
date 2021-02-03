package task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

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
        workWithDatabase();
    }

    /**
     * This method creates connection and renews the
     * database and then makes modifications, insertions
     * and updates into tables showing work of statements,
     * prepared statements, batching, manual commits and
     * savepoints
     */
    private static void workWithDatabase() {
        String url = "jdbc:postgresql://localhost:5432/online_store";
        String user = "postgres";
        String pass = "postgress";

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            log.info("DB is successfully connected");
            DBUtils.renewDatabase(connection);
            log.info("DB is renewed");
            makePreparedStatement(connection);
            makeBatching(connection);
            makeManualCommits(connection);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
        }
    }

    /**
     * This method allows to do manual commits to DB
     * with using savepoint
     *
     * @param connection the DB connection object
     * @throws SQLException the SQL exception
     */
    private static void makeManualCommits(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate("INSERT INTO laptops VALUES (111, " +
                    "'MacBook Air M1 2020', 'Apple', 'China', 'macOS', 'M1', " +
                    "8, 13)");
            log.info("Manual commit is performed executed successfully");

            Savepoint savepoint = connection.setSavepoint();
            statement.executeUpdate("INSERT INTO laptops VALUES (222, " +
                    "'MacBook Pro M1 2020', 'Apple', 'China', 'macOS', 'M1', " +
                    "16, 13)");
            connection.rollback(savepoint);
            connection.commit();

        } catch (SQLException throwables) {
            connection.rollback();
            log.error(throwables.getMessage());
        }
    }

    /**
     * This method calls a Prepared statement query
     * and uses batching to add gift payment card to
     * users without cards
     *
     * @param connection the DB connection object
     * @throws SQLException the SQL exception
     */
    private static void makeBatching(Connection connection) throws SQLException {
        Integer[] idArgs = new Integer[]{1, 2, 3};
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE customers SET payment_card_id=999 " +
                        "WHERE payment_card_id IS NULL " +
                        "AND id = ?")) {
            for (Integer id : idArgs) {
                preparedStatement.setInt(1, id);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            log.info("Batching executed successfully");
        }
    }

    /**
     * This method calls a Prepared statement query
     * and puts 2 values
     *
     * @param connection the DB connection object
     */
    private static void makePreparedStatement(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM customers " +
                        "WHERE basket IS NOT NULL " +
                        "AND birthday >= ? " +
                        "AND name <> ?")) {
            preparedStatement.setDate(1, Date.valueOf("2000-01-01"));
            preparedStatement.setString(2, "Amanda");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                StringBuilder result = new StringBuilder("The result of a query is\n");
                while (resultSet.next()) {
                    result.append("id=");
                    result.append(resultSet.getInt("id"));
                    result.append("; name=");
                    result.append(resultSet.getString("name"));
                    result.append("; birthday=");
                    result.append(resultSet.getDate("birthday"));
                    result.append("; basket=");
                    result.append(resultSet.getString("basket"));
                    result.append("; payment_card_id=");
                    result.append(resultSet.getInt("payment_card_id"));
                    result.append("\n");
                }
                log.info(result);
            }
        }
    }

}
