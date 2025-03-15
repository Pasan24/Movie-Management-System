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


            if(!isUsed){
                ResultSet rs2 = database.getStatement().executeQuery("SELECT `ID`, `firstName`, `lastName`, `phoneNumber`, " +
                        "`email`, `password` FROM `admins` WHERE `email` = '" + email + "';");
                isUsed = rs2.next();

            }


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

    public  static boolean login (String email , String password , Database database){
        boolean login = false;
        ArrayList<User> users = new ArrayList<>();
        users.addAll(getAllVisitors(database));
        users.addAll(getAllAdmins(database));


        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
               login = true;
                break;
            }
        }


        return login;
    }


    public static User getUser (String email , String password , Database database) {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(getAllVisitors(database));
        users.addAll(getAllAdmins(database));
        User user = new Visitor();
        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }

        return user;


    }

    public static  Visitor login(String email , Database database){
        Visitor visitor =  new Visitor();

        try {

            ResultSet rs = database.getStatement().executeQuery("SELECT `ID`, `firstName`, `lastName`, `phoneNumber`, " +
                    "`email`, `password` FROM `visitors` WHERE `email` = '" + email + "';");
            while(rs.next()){
                if(rs.getString("email").equals(email) ){
                    visitor.setID(rs.getInt("ID"));
                    visitor.setFirstName(rs.getString("firstName"));
                    visitor.setLastName(rs.getString("lastName"));
                    visitor.setPhoneNumber(rs.getString("phoneNumber"));
                    visitor.setEmail(rs.getString("email"));
                    visitor.setPassword(rs.getString("password"));
                        break;
                }
            }


        }catch(SQLException e){

            e.printStackTrace();

        }

        return visitor;
    }


    //for admins

    public static ArrayList<Admin > getAllAdmins(Database database) {
        ArrayList<Admin> admins = new ArrayList<>();

        try {
            // FIXED: Added null check to prevent NullPointerException
            if (database.getStatement() == null) {
                System.err.println("Database statement is null. Check database connection.");
                return admins; // Return empty list instead of null
            }

            ResultSet rs = database.getStatement().executeQuery("SELECT * FROM `visitors`;");

            while (rs.next()) {
               Admin admin = new  Admin();
              admin.setID(rs.getInt("ID"));
                admin.setFirstName(rs.getString("firstName"));
                admin.setLastName(rs.getString("lastName"));
                admin.setPhoneNumber(rs.getString("phoneNumber"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));

                admins.add(admin);
            }

        } catch (SQLException e) {
            System.err.println("Error getting visitors: " + e.getMessage());
            e.printStackTrace();
        }

        return  admins ;
    }

    public static int getNextAdminID(Database database) {
        int ID = 0;
        ArrayList<Admin> admins= getAllAdmins(database);
        if (admins.size() != 0) {
            int lastRow = admins.size() - 1;
            Admin lastAdmin = admins.get(lastRow);
            ID = lastAdmin.getID() + 1;
        }

        return ID;
    }


    public static void addAdmin(Admin a, Database database) {
        // FIXED: Fixed SQL query - had email and phoneNumber swapped
        String insert = "INSERT INTO `admins`(`ID`, `firstName`, `lastName`, `phoneNumber`, `email`, `password`) " +
                "VALUES ('" + a.getID() + "','" + a.getFirstName() + "','" + a.getLastName() + "','" +
                a.getPhoneNumber() + "','" + a.getEmail() + "','" + a.getPassword() + "');";

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
