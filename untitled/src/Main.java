import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Database database;

    public static void main(String[] args) {
        System.out.println("Starting program...");
        database = new Database();
        System.out.println("Database initialized...");
        System.out.println("Welcome to movies Ticket Booking System");
        System.out.println("1. Login");
        System.out.println("2. Create new Account");

        int i = scanner.nextInt();

        switch (i) {
            case 1:
                login();
                break;
            case 2:
                createNewAccount();
                break;
            default:
                System.out.println("Unvalid Input!");
                break;
        }


    }

    public static void login() {
        System.out.println("Enter Your Email: ");
        String email = scanner.next();

        System.out.println("Enter Your password:");
        String password = scanner.next();

        if (UsersDataBase.login(email,password,database)){
                User user = UsersDataBase.getUser(email,password,database);
                System.out.println("Welcome " + user.getFirstName()+ " "+user.getLastName());
                user.showList(database);
        }else{
            System.out.println("Incorrect email or Password");
            login();
        }

        // NOTE: Login functionality not implemented yet
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
        visitor.showList(database);
    }



}