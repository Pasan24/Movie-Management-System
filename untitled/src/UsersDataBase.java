import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDataBase {

    public static boolean IsEmailUsed(String email, Database database){

        boolean isUsed =  false ;

        try{
            ResultSet rs = database.getStatement().executeQuery(" SELECT `ID`, `firstName`, `lastName`, `phoneNumber`, " +
                    "`email`, `password`  FROM `visitors` WHERE  `email` = '" +email +"' ;");
            isUsed = rs.next();

        }catch (Exception e){
            e.printStackTrace();
        }

        return isUsed ;
    }

    public static ArrayList<Visitor> getAllVisitors(Database database
    ){

        ArrayList<Visitor> visitors =  new ArrayList<>();
        try{
            ResultSet rs = database.getStatement().executeQuery("SELECT * FROM `visitors` ;");

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

    } catch (
    SQLException e) {
        e.printStackTrace();  // Log properly in real applications
    }

    return visitors;

    }

    public static int getNextVisitorID(Database database){
        int ID = 0 ;
        ArrayList <Visitor> visitors = getAllVisitors(database);
        if (visitors.size()!=0){
        int lastRow = visitors.size()-1;
        Visitor lastVisitor = visitors.get(lastRow);
        ID= lastVisitor.getID()+1;

        }



        return ID;
    }


    public static void addVisitor (Visitor v , Database database){

        String insert="INSERT INTO `visitors`(`ID`, `firstName`, `lastName`, `phoneNumber`, `email`, `password`) " +
                "VALUES ('" +v.getID()+"','" +v.getFirstName()+"','" +v.getLastName()+"','"+v.getEmail()+"'," +
                "'"+v.getPhoneNumber()+"','"+v.getPassword()+"');";

        try {
            database.getStatement().execute(insert);
            System.out.println("USer Created Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
