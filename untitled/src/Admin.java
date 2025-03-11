public class Admin extends User {

    public Admin() {
        super();
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
    }

}
