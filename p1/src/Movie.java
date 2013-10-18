import java.util.*;
/**
 * The Movie class is used to represent a single movie that keeps track of its
 * title, rating, and cast (a list of actors).
 * 
 * @author John Culhane, CS 367 TA, copyright 2013
 */
public class Movie {
    private String title;       // the movie title
    private int rating;         // the movie rating
    private List<String> cast;  // the list of actors in the movie
    
    /**
     * Constructs a movie with the given title, rating, and an empty roster.
     * 
     * @param title the title of this movie
     * @param rating the rating of this movie
     */
    public Movie(String title, int rating) {
        this.title = title;
        this.rating = rating;
        cast = new ArrayList<String>();
    }
    
    /**
     * Returns the title of this movie.
     * 
     * @return the title of this movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the rating of this movie.
     * 
     * @return the rating of this movie
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating of this movie.
     * 
     * @param newRating the new rating of this movie
     */
    public void setRating(int newRating) {
        this.rating = newRating;
    }
    
    /**
     * Returns the cast of this movie (i.e., the list of actors in this movie).
     * 
     * @return the cast for this movie
     */
    public List<String> getCast() {
        return cast;
    }
}
