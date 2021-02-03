package task1;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    /**
     * This method renews the database so that each
     * new run begins from the same state
     *
     * @param connection the connection object
     * @throws SQLException the SQL exception
     */
    public static void renewDatabase(Connection connection) throws SQLException {

        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS laptops;" +
                    "\n" +
                    "DROP TABLE IF EXISTS customers;" +
                    "\n" +
                    "DROP TABLE IF EXISTS payment_cards;" +
                    "\n" +
                    "CREATE TABLE laptops (itemId bigserial primary key," +
                    "title varchar(30) NOT NULL, brand varchar(15) NOT NULL," +
                    "manufacturer varchar(20) NOT NULL, os varchar(10) NOT NULL, " +
                    "cpu varchar(20) NOT NULL, ram varchar(10) NOT NULL," +
                    "screenSize integer NOT NULL);" +
                    "\n" +
                    "CREATE TABLE customers (id bigserial primary key," +
                    "name varchar(20) NOT NULL, birthday date NOT NULL," +
                    "basket varchar(20), payment_card_id integer);" +
                    "\n" +
                    "CREATE TABLE payment_cards (id bigserial primary key," +
                    "card_number bigint NOT NULL, issue date NOT NULL," +
                    "cvc smallint NOT NULL, card_holder varchar(40) NOT NULL)");
        }
    }
}
