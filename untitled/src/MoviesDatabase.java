import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MoviesDatabase {

    public static void addNewMovie(Database database, Scanner s) {
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

        String insert = "INSERT INTO `movies`(`ID`, `Name`, `Language`, `Genre`, `Running Time`, `Starring`, `Rating`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]','[value-5]','[value-6]','[value-7]')";


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



}