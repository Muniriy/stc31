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
                    "cpu varchar(20) NOT NULL, ram integer NOT NULL," +
                    "screenSize float NOT NULL);" +
                    "\n" +
                    "CREATE TABLE customers (id bigserial primary key," +
                    "name varchar(20) NOT NULL, birthday date NOT NULL," +
                    "basket varchar(20), payment_card_id bigint);" +
                    "\n" +
                    "CREATE TABLE payment_cards (id bigserial primary key," +
                    "card_number bigint NOT NULL, issue date NOT NULL," +
                    "cvc smallint NOT NULL, card_holder varchar(40) NOT NULL);" +
                    "\n" +
                    "INSERT INTO customers VALUES (1, 'Peter', '2000-01-01', " +
                    "'MacBook Air M1 2020', 123);" +
                    "\n" +
                    "INSERT INTO customers VALUES (2, 'Tom', '1990-05-08', " +
                    "'MacBook Pro M1 2020');" +
                    "\n" +
                    "INSERT INTO customers VALUES (3, 'Mick', '1995-12-30');" +
                    "\n" +
                    "INSERT INTO customers (id, name, birthday, payment_card_id) " +
                    "VALUES (4, 'Alice', '1997-10-09', 536);" +
                    "\n" +
                    "INSERT INTO customers VALUES (5, 'Amanda', '2001-06-06', " +
                    "'Lenovo Idea', 919);" +
                    "\n" +
                    "INSERT INTO customers VALUES " +
                    "(6, 'Vicky', '2002-03-16', 'Asus Zenbook', 493);"
            );
        }
    }
}
