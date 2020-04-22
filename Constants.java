//Elena Voinu
// Table with String constants
import javax.naming.InsufficientResourcesException;
import java.sql.*;

public class Constants {
    private static final String DB_NAME = "test1.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
    private static final String TABLE_CONTACTS = "contacts";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    " (" + COLUMN_NAME + " text, "
                    + COLUMN_PHONE + " integer, "
                    + COLUMN_EMAIL + " text"
                    + ")" );

            //method call to insert the data
            insertContact(statement, "Tim", 334447, "tim@email.com");

            //insert data command line
            statement.execute("INSERT INTO " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + ", "
                    + COLUMN_PHONE + ", "
                    + COLUMN_EMAIL + ")"
                    + "VALUES('ELENA', 2233445, 'LENA@EMAIL.COM')");

            statement.execute("INSERT INTO " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + ", "
                    + COLUMN_PHONE + ", "
                    + COLUMN_EMAIL + ")"
                    + "VALUES('Jane', 11112222, 'Jane@EMAIL.COM')");

            statement.execute("INSERT INTO " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + ", "
                    + COLUMN_PHONE + ", "
                    + COLUMN_EMAIL + ")"
                    + "VALUES('Joe', 33334444, 'Joe@EMAIL.COM')");

            statement.execute("INSERT INTO " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + ", "
                    + COLUMN_PHONE + ", "
                    + COLUMN_EMAIL + ")"
                    + "VALUES('Fido', 1111166666, 'Fido@EMAIL.COM')");

            statement.execute("UPDATE " + TABLE_CONTACTS + " SET "
                    + COLUMN_PHONE + "=557789"
                    + " WHERE " + COLUMN_NAME + "='Jane'");

            statement.execute("DELETE FROM " + TABLE_CONTACTS
                    + " WHERE " +COLUMN_NAME + "='Joe'");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while (resultSet.next()){
                System.out.println(resultSet.getString(COLUMN_NAME ) + " "
                                 + resultSet.getInt(COLUMN_PHONE) + " "
                                + resultSet.getString(COLUMN_EMAIL));
            }

            //close the resources
            resultSet.close();

            //statement must be closed before we close the connection
            statement.close();

            //close the connection
            connection.close();


        }

        catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //method to insert data into our database
    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL +
                " ) " +
                "VALUES('" + name + "', " + phone + ", '" + email + "')");
    }
 }
