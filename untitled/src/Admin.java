import java.util.Scanner;

public class Admin extends User {

    private Scanner scanner ;

    public Admin() {
        super();
        scanner = new Scanner(System.in);
    }

    @Override
    public void showList(){
        System.out.println(" 1. Add New Movie ");
        System.out.println(" 2. Update Movie");
        System.out.println(" 3. Show Movies");
        System.out.println(" 4. Delete Movie");
        System.out.println(" 5. Add Show Time");
        System.out.println(" 6. Update Show Time");
        System.out.println(" 7. Display Show Time");
        System.out.println(" 8. Delete Show Time");
        System.out.println(" 9. Add New Admin");
        System.out.println(" 10. Quit");
        int i = scanner.nextInt();
        switch (i){
            case 9:
                createNewAccount();
                break;
        }
    }

    public static void createNewAccount() {


        System.out.println("Enter Your firstName: ");
        String firstName = scanner.next();

        System.out.println("Enter Your LastName:");
        String lastName = scanner.next();

        System.out.println("Enter Your Email: ");
        String email = scanner.next();

        System.out.println("Enter Your phoneNumber:");
        String phoneNumber = scanner.next();

        System.out.println("Enter Your password:");
        String password = scanner.next();

        System.out.println("Confirm password:");
        String confirmPassword = scanner.next();

        while (!password.equals(confirmPassword)) {
            System.out.println("Password doesn't match");
            System.out.println();

            System.out.println("Enter Your password:");
            password = scanner.next();

            System.out.println("Enter correct Confirm password:");
            confirmPassword = scanner.next();
        }

        while (UsersDataBase.IsEmailUsed(email, database)) {
            System.out.println("This email is already Used!");
            System.out.println("Enter your email:");
            email = scanner.next();
        }

        Visitor visitor = new Visitor();
        visitor.setID(UsersDataBase.getNextVisitorID(database));
        // FIXED: Changed string literals to actual variables
        visitor.setFirstName(firstName);  // Changed from "firstName" to firstName
        visitor.setLastName(lastName);    // Changed from "lastName" to lastName
        visitor.setPhoneNumber(phoneNumber); // Changed from "phoneNumber" to phoneNumber
        visitor.setEmail(email);          // Changed from "email" to email
        visitor.setPassword(password);    // Changed from "password" to password

        UsersDataBase.addVisitor(visitor, database);
        visitor.showList();
    }

}
