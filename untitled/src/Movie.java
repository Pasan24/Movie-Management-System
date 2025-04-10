import java.util.ArrayList;

public class Movie {

    private int ID;
    private String name;
    private  String  Language;
    private  String Genre;
    private  int RunningTime;
    private  String Starring;
    private String Rating;
    private ArrayList <Show> shows;

    public  Movie(){
        shows = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getRunningTime() {
        return RunningTime;
    }

    public void setRunningTime(int runningTime) {
        RunningTime = runningTime;
    }

    public String getStarring() {
        return Starring;
    }

    public void setStarring(String starring) {
        Starring = starring;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public void setShows(ArrayList<Show> shows) {
        this.shows = shows;
    }

    public void print(){
        System.out.println(ID+"\t"+name+"\t"+Language+"\t\t"+Genre+"\t\t"+RunningTime+"\t\t\t"+Starring+"\t\t"+Rating);


    }

}
