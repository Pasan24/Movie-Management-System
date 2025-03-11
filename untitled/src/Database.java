import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private String user = "user" ;

    private String password = "1@2@3@" ;

    private String url = "jdbc:mysql://localhost/movieticketbookingsystem2 " ;

    private Statement  statement;

    public  Database () {

        try {
            Connection connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
