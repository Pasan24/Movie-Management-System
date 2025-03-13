import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Scanner scanner =new Scanner(System.in);
    private static  Database database ; //static method can only access static variables and method

    public static void main(String[] args) {
        database = new Database();
        System.out.println(" Welcome to movies Ticket Booking System ");
        System.out.println("1. Login");
        System.out.println("2. Create  new Account");



        int i = scanner.nextInt();


        switch (i) {
            case 1:
                login();
                break;

            case 2:
                createNewAccount();
                break;


            default:
                System.out.println("Unvalid Input !");
                break;
        }


    }

    public static void login() {

        System.out.println("Enter Your Email : ");
        String email = scanner.next();

        System.out.println("Enter Your password :");
        String password = scanner.next();

    }

    public static void createNewAccount() {
        System.out.println("Enter Your firstName: ");
        String firstName = scanner.next();

        System.out.println("Enter Your LastName:");
        String lastName = scanner.next();

        System.out.println("Enter Your Email : ");
        String email = scanner.next();

        System.out.println("Enter Your phoneNumber :");
        String phoneNumber = scanner.next();

        System.out.println("Enter Your password :");
        String password = scanner.next();

        System.out.println("Confirm  password :");
        String confirmPassword = scanner.next();

        while (!password.equals(confirmPassword)) {

            System.out.println("Password doesn't match");
            System.out.println();

            System.out.println("Enter Your password :");
            password = scanner.next();

            System.out.println("Enter correct Confirm  password :");
            confirmPassword = scanner.next();


        }

        while (UsersDataBase.IsEmailUsed(email, database)) {
            System.out.println("This email is already USed !");
            System.out.println("Enter your email ");
            email = scanner.next();



        }

        Visitor visitor = new Visitor();
        visitor.setID(UsersDataBase.getNextVisitorID(database));
        visitor.setFirstName("firstName");
        visitor.setLastName("lastName");
        visitor.setPhoneNumber("phoneNumber");
        visitor.setEmail("email");
        visitor.setPassword("password");

        UsersDataBase.addVisitor(visitor,database);


    }
}