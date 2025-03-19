import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MoviesDatabase {

    public static void addNewMovie(Database database, Scanner s , Admin admin ) {
        System.out.println("Enter Movie Name : ");
        String name = s.next();

        System.out.println("Enter Movie language : ");
        String language = s.next();

        System.out.println("Enter Movie Genre : ");
        String genre = s.next();

        System.out.println("Enter Movie running time : ");
        int runningTime = s.nextInt();

        System.out.println("Enter Movie Starring : ");
        String starring = s.next();

        System.out.println("Enter Movie Rating : ");
        String rating = s.next();

        int ID = getNextID(database);

        String insert = "INSERT INTO `movies`(`ID`, `Name`, `Language`, `Genre`, `Running Time`, `Starring`, `Rating`) " +
                "VALUES (" + ID + ", '" + name + "', '" + language + "', '" + genre + "', " + runningTime + ", '"
                + starring + "', '" + rating + "')";

        String create = "CREATE TABLE `Movie"+ID+" " +
                "- Shows ` (ID int , showTime text, capacity int , " +
                "availableSeats int,place text);";


        try{
            database.getStatement().execute(insert);
            database.getStatement().execute(create);
            System.out.println("Movie added successfully ! \n");
            admin.showList(database);
        }catch(SQLException e){
            e.printStackTrace();
        }


    }

    public static ArrayList<Movie> getAllMovies(Database database) {
        ArrayList<Movie> movies = new ArrayList<>();
        String select = "SELECT * FROM `movies`;";
        try {
            ResultSet rs = database.getStatement().executeQuery(select);
            while (rs.next()) {
                Movie m = new Movie();
                m.setID(rs.getInt("ID"));
                m.setName(rs.getString("Name"));
                m.setLanguage(rs.getString("Language"));
                m.setGenre(rs.getString("Genre"));
                m.setRunningTime(rs.getInt("Running Time"));
                m.setStarring(rs.getString("Starring"));
                m.setRating(rs.getString("Rating"));
                movies.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static int getNextID (Database database){
        int ID = 0 ;
        ArrayList<Movie> movies = getAllMovies(database);
        int size =  movies.size();
        if(size!=0){
            Movie lastMovie= movies.get(size-1);
            ID = lastMovie.getID()+1;


        }
        return ID;
    }




}