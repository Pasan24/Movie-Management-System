import java.sql.*;

public class Database {

    private String user = "user" ;

    private String password = "1@2@3@" ;

    private String url = "jdbc:mysql://localhost/movieticketbookingsystem2 " ;

    private Statement  statement;

    public  Database () {

        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

        public Statement getStatement(){
            return statement ;
        }
    }



