import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MoviesDatabase {

    public static void addNewMovie(Database database, Scanner s  ) {
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
                "_Shows` (ID int , showTime text, capacity int , " +
                "availableSeats int,place text);";


        try{
            database.getStatement().execute(insert);
            database.getStatement().execute(create);
            System.out.println("Movie added successfully ! \n");

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

    public static  void updateMovie(Database database,Scanner s){
        System.out.println("Enter Movie ID(int) (-1 to show all movies) ");
        int ID = s.nextInt();
        while(ID==-1){
            showMovies(database);
            System.out.println("Enter Movie ID(int) (-1 to show all movies) ");
            ID = s.nextInt();

        }

        Movie movie = getMovie (ID,database);

        System.out.println("Enter Movie Name : (-1 to keep old value)");
        String name = s.next();
        if(!name.equals("-1")){
            movie.setName(name);
        }

        System.out.println("Enter Movie language : (-1 to keep old value)");
        String language = s.next();

        if(!language.equals("-1")){
            movie.setLanguage(language);
        }

        System.out.println("Enter Movie Genre : (-1 to keep old value)");
        String genre = s.next();
        if(!genre.equals("-1")){
            movie.setGenre(genre);
        }

        System.out.println("Enter Movie running time : (-1 to keep old value)");
        int runningTime = s.nextInt();
        if(runningTime!=-1){
            movie.setRunningTime(runningTime);
        }

        System.out.println("Enter Movie Starring : (-1 to keep old value)");
        String starring = s.next();
        if(!starring.equals("-1")){
            movie.setStarring(starring);
        }

        System.out.println("Enter Movie Rating : (-1 to keep old value)");
        String rating = s.next();
        if(!rating.equals("-1")){
            movie.setRating(rating);
        }

        String update = "UPDATE `movies` SET `Name` = '"+movie.getName()+
                "', `Language` = '"+ movie.getLanguage()+
                "', `Genre` = '"+movie.getGenre()+
                "', `Running Time` = "+ movie.getRunningTime()+
                ", `Starring` = '"+ movie.getStarring()+
                "', `Rating` = '"+ movie.getRating()+
                "' WHERE `ID` = "+movie.getID()+";";

        try {
            database.getStatement().execute(update);
            System.out.println("Movie updated Successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showMovies (Database database){
        System.out.println("--------------------------------------------------------");
        System.out.println("ID\tName\tLanguage\tGenre\tRunning Time\tStarring\tRating");
        System.out.println();
        for (Movie m : getAllMovies(database)){
            m.print();
        }
        System.out.println("--------------------------------------------------------");

    }

    public static  Movie getMovie(int ID , Database database){

        Movie movie = new Movie();
        String select = "SELECT `ID`, `Name`, `Language`, `Genre`, `Running Time`, `Starring`, `Rating` FROM" +
                " `movies` WHERE `ID`= "+ID+" ;";


        try {
            ResultSet rs = database.getStatement().executeQuery(select);
            if (rs.next()) {  // Check if result set has any rows
                movie.setID(rs.getInt("ID"));
                movie.setName(rs.getString("Name"));
                movie.setLanguage(rs.getString("Language"));
                movie.setGenre(rs.getString("Genre"));
                movie.setRunningTime(rs.getInt("Running Time"));
                movie.setStarring(rs.getString("Starring"));
                movie.setRating(rs.getString("Rating"));
            } else {
                throw new RuntimeException("No movie found with ID: " + ID);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movie;
    }


    public static  void deleteMovie(Database database , Scanner s){

        System.out.println("Enter Movie ID(int) (-1 to show all movies) ");
        int ID= s.nextInt();
        while(ID==-1){
            showMovies(database);
            System.out.println("Enter Movie ID(int) (-1 to show all movies) ");
            ID = s.nextInt();

        }

        String delete = "DELETE FROM `movies` WHERE `ID` = "+ID+";";
        String drop = "DROP TABLE `Movie"+ID+" _Shows`;"; //space needs to be there

        try {
            database. getStatement().execute(delete);
            database. getStatement().execute(drop);
            System.out.println("Movie deleted Successfully !");
        }catch(SQLException e){
            e.printStackTrace();

        }
    }


}