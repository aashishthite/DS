///////////////////////////////////////////////////////////////////////////////
//
//Main Class file:		MovieDBMain.java
//File:				MovieDB.java
//Semester:			CS367 Summer 2013
//
//Author:			Aashish Thite
//CS Login:			aashish
///////////////////////////////////////////////////////////////////////////////



import java.util.*;
import java.io.*;

public class MovieDB
{
	List<Movie> list_of_movies;//List to hold the database

	//Constructor
	public MovieDB()
	{
		//Create an empty list
		list_of_movies = new ArrayList<Movie>();
	}

	public void addMovie(String t, int r) throws IllegalArgumentException
	{
		//Add to movie lis
		if(this.containsMovie(t))
		{
			return;
		}
		if( r < 0 || r > 100)
		{
			throw new IllegalArgumentException();
		}	
		list_of_movies.add(new Movie(t,r));
	}

	public void addActor(String n, String t)
	{
		//Look for movie
		if(!containsMovie(t))
		{
			throw new IllegalArgumentException();	//throw exception if not found
		}	
		//look if actor exists
		if(!isFeatured(n,t))
		{
			Iterator<Movie> itr = list_of_movies.iterator();
			while(itr.hasNext())
			{
				Movie temp_movie = itr.next();
				if(temp_movie.getTitle().equals(t))
				{
					List<String> temp_cast = temp_movie.getCast();
					temp_cast.add(n);
					break;
				}
			}
		}
	
	}

	public boolean removeMovie(String t)
	{
		//Look for the movie
		//remove
		Iterator<Movie> itr = list_of_movies.iterator();
		while(itr.hasNext())
		{
			Movie temp_movie = itr.next();
			if(temp_movie.getTitle().equals(t))
			{
				return list_of_movies.remove(temp_movie);
			}
		}
		return false;
	}

	public boolean updateRating(String t, int r)
	{
		//look for the movie
		//check if the rating is valid
		if(r<0 || r>100 || !containsMovie(t))
		{
			return false;
		}
		//update rating
		Iterator<Movie> itr = list_of_movies.iterator();
		while(itr.hasNext())
		{
			Movie temp_movie = itr.next();
			if(temp_movie.getTitle().equals(t))
			{
				temp_movie.setRating(r);
				break;
			}
			
		}
		return true;
	}

	public boolean containsMovie(String t)
	{
		//check if movie is present
		Iterator<Movie> itr = list_of_movies.iterator();
		while(itr.hasNext())
		{
			if(itr.next().getTitle().equals(t))
			{
				return true;
			}
		}
		return false;
	}

	public boolean containsActor(String n)
	{
		//look for cast of every movie in the data base
		Iterator<Movie> itr = list_of_movies.iterator();
		while(itr.hasNext())
		{
			Movie temp_movie = itr.next();
			List<String> temp_cast = temp_movie.getCast();
			if( temp_cast.contains(n))
			{
				return true;
			}		
		}
		return false;
	}

	public boolean isFeatured(String n, String t)
	{
		//look for movie
		if(!containsMovie(t))
		{
			return false;
		} 
		//check cast
		Iterator<Movie> itr = list_of_movies.iterator();
		while(itr.hasNext())
		{
			Movie temp_movie = itr.next();
			if(temp_movie.getTitle().equals(t))
			{
				List<String> temp_cast = temp_movie.getCast();
				if( temp_cast.contains(n))
				{
					return true;
				}
				break;
			}
		}
	

		return false;
	}

	public List<String> getCast(String t)
	{
		//look for the movie
		//return cast for the movie\
		Iterator<Movie> itr = list_of_movies.iterator();
		while(itr.hasNext())
		{
			Movie temp_movie = itr.next();
			if(temp_movie.getTitle().equals(t))
			{
				return temp_movie.getCast();
			}
		}
		return null;
	}

	public List<String> getMovies(String n)
	{
		//create a list to return
		List<String> movies_of_the_actor = new ArrayList<String>();
		//look through the movie list and check if actor n is present
		Iterator<Movie> itr = list_of_movies.iterator();
		while(itr.hasNext())
		{
			Movie temp_movie = itr.next();
			if(temp_movie.getCast().contains(n))
			//if yes add the movie title to the list
			{
				movies_of_the_actor.add(temp_movie.getTitle());
			}		
		}		
		//return the list
		return movies_of_the_actor;
	}

	public double getActorRating(String n)
	{
		//look through the movie list to check if the actor n is present
		Iterator<Movie> itr = list_of_movies.iterator();
		double actor_rating = 0;
		int num = 0;
		while(itr.hasNext())
		{
			Movie temp_movie = itr.next();
			if(temp_movie.getCast().contains(n))
			//if yes accumulate the movie rating 
			{
				actor_rating += temp_movie.getRating();
				num++;
			}		
		}	
		//if yes accumulate the movie rating
		//divie by the number of of accumulated values
		actor_rating = actor_rating/num;
		return actor_rating;
	}
	public Iterator<Movie> iterator()
	{
		return list_of_movies.iterator();
	}
	public int size()
	{
		return list_of_movies.size();
	}
	
}
