import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDataBase {

    public static boolean IsEmailUsed(String email, Database database) {
        boolean isUsed = false;

        try {
            // FIXED: Added null check to prevent NullPointerException
            if (database.getStatement() == null) {
                System.err.println("Database statement is null. Check database connection.");
                return false;
            }

            ResultSet rs = database.getStatement().executeQuery("SELECT `ID`, `firstName`, `lastName`, `phoneNumber`, " +
                    "`email`, `password` FROM `visitors` WHERE `email` = '" + email + "';");
            isUsed = rs.next();

        } catch (Exception e) {
            System.err.println("Error checking email: " + e.getMessage());
            e.printStackTrace();
        }

        return isUsed;
    }

    public static ArrayList<Visitor> getAllVisitors(Database database) {
        ArrayList<Visitor> visitors = new ArrayList<>();

        try {
            // FIXED: Added null check to prevent NullPointerException
            if (database.getStatement() == null) {
                System.err.println("Database statement is null. Check database connection.");
                return visitors; // Return empty list instead of null
            }

            ResultSet rs = database.getStatement().executeQuery("SELECT * FROM `visitors`;");

            while (rs.next()) {
                Visitor visitor = new Visitor();
                visitor.setID(rs.getInt("ID"));
                visitor.setFirstName(rs.getString("firstName"));
                visitor.setLastName(rs.getString("lastName"));
                visitor.setPhoneNumber(rs.getString("phoneNumber"));
                visitor.setEmail(rs.getString("email"));
                visitor.setPassword(rs.getString("password"));

                visitors.add(visitor);
            }

        } catch (SQLException e) {
            System.err.println("Error getting visitors: " + e.getMessage());
            e.printStackTrace();
        }

        return visitors;
    }

    public static int getNextVisitorID(Database database) {
        int ID = 0;
        ArrayList<Visitor> visitors = getAllVisitors(database);
        if (visitors.size() != 0) {
            int lastRow = visitors.size() - 1;
            Visitor lastVisitor = visitors.get(lastRow);
            ID = lastVisitor.getID() + 1;
        }

        return ID;
    }

    public static void addVisitor(Visitor v, Database database) {
        // FIXED: Fixed SQL query - had email and phoneNumber swapped
        String insert = "INSERT INTO `visitors`(`ID`, `firstName`, `lastName`, `phoneNumber`, `email`, `password`) " +
                "VALUES ('" + v.getID() + "','" + v.getFirstName() + "','" + v.getLastName() + "','" +
                v.getPhoneNumber() + "','" + v.getEmail() + "','" + v.getPassword() + "');";

        try {
            // FIXED: Added null check to prevent NullPointerException
            if (database.getStatement() == null) {
                System.err.println("Database statement is null. Check database connection.");
                return;
            }

            database.getStatement().execute(insert);
            System.out.println("User Created Successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding visitor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}