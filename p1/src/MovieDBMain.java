///////////////////////////////////////////////////////////////////////////////
//
//Title: 		MovieDBMain
//Files:		MovieDBMain.java, MovieDB.java
//Semester: 		CS367 Summer 2013
//
//Author:		Aashish Thite
//Email:		thite@wisc.edu
//CS Login:		aashish
//
///////////////////////////////////////////////////////////////////////////////






import java.util.*;
import java.io.*;

public class MovieDBMain {
    public static void main(String[] args) {

        // *** Add code for steps 1 - 3 of the main method ***
       String S;//String to store the filename
	//System.out.println(args.length);
        Scanner stdin = new Scanner(System.in);  // for reading console input
        boolean done = false;
	MovieDB movie_database = new MovieDB();//new instance for the movie database
	if(args.length==1)
	{
		S=args[0];//Copy the command line argument to a variable
		File input_file = new File(S);//Create a file object
		
		if(input_file.exists() && input_file.isFile() && input_file.canRead())//check if the file exists and is readable
		{
			try
			{
				Scanner infile = new Scanner(input_file);
				//movie_database = new MovieDB();
				while(infile.hasNextLine())//read file line by line
				{
					String actor_name;
					String line = infile.nextLine();
				//	System.out.println(line);
					Scanner linescanner = new Scanner(line);
					linescanner.useDelimiter(",");
					//Read line part by part delimited by commas
					{
						actor_name = linescanner.next();//Add actor name to list
					}
					while(linescanner.hasNext())
					{
						String movie_name = linescanner.next();
						String movie_rating = linescanner.next();
						if(isInteger(movie_rating))
						{
							movie_database.addMovie(movie_name.toLowerCase(), Integer.parseInt(movie_rating));//add movie with rating
							movie_database.addActor(actor_name.toLowerCase(), movie_name.toLowerCase());//add actor to the movie
						}
						else//if correct file is not provided
						{
							System.out.println("Wrong file");
							done = true;
						}
							
					}
				}
			}
			catch(FileNotFoundException e)//handle file IO  exception
			{
				System.out.println("Exception caught: File not found");
			}
		}
		else
		{
			System.out.println("Error: Cannot access input file");
			done=true;
		}
			
	}
	else
	{
		System.out.println("Usage: java MovieDBMain FileName");
		done=true;
	}
	


	while (!done) {
            System.out.print("Enter option ( rdpcusx ): ");
            String input = stdin.nextLine();

            // only do something if the user enters at least one character
            if (input.length() > 0) {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                if (input.length() > 1) { // if there is an argument
                    // trim off any leading or trailing spaces
                    remainder = input.substring(1).trim(); 

	                switch (choice) { // the commands that have arguments

	
	                case 'r':
				 // *** Add code to implement this option ***
	                   	if(movie_database.containsMovie(remainder.toLowerCase()))//check if movie is present in the database
				{
					if(movie_database.removeMovie(remainder.toLowerCase()))
						System.out.println("movie removed");
					//System.out.println(toTitleCase(remainder.toLoweCase()));
				}
				else
				{
					System.out.println("movie not found");
				}
	                    break;
	
	                case 'p':
	                  	  // *** Add code to implement this option **
	                   	if(movie_database.containsActor(remainder.toLowerCase()))
				{
					List<String> movies_of_actor = movie_database.getMovies(remainder.toLowerCase());//temporary list to hold the movie titles the actor has featured in
					Iterator<String> itr = movies_of_actor.iterator();
					while(itr.hasNext())//print all movies by the actor separated by commas 
					{
						System.out.print(toTitleCase(itr.next()));
						if(itr.hasNext())
						System.out.print(", ");
					}
					System.out.print("\n");
				}
				else
				{
					System.out.println("actor not found");
				}
			
	                    break;
	
	                case 'c':
	                    // *** Add code to implement this option ***
	                    if(movie_database.containsMovie(remainder.toLowerCase()))
				{
					List<String> cast = movie_database.getCast(remainder.toLowerCase());//get the cast from the database
					if(cast.isEmpty())
					{
						System.out.println("none");
					}
					else//print it out separated by commas
					{
						Iterator<String> itr = cast.iterator();
						while(itr.hasNext())
						{
							System.out.print(toTitleCase(itr.next()));
							if(itr.hasNext())
								System.out.print(", ");
		
						}
						System.out.print("\n");
					}
				}
				else
				{
					System.out.println("movie not found");
				}
	                    break;
	
	                case 'u':
	                    // The following code reads in a comma-separated sequence 
	                    // of a string followed by an integer.  If there are not
                            // exactly two elements in the sequence or if the second
                            // element is not an integer an error message is printed.
	                    String[] tokens = remainder.split("[,]+");
	                    if (tokens.length != 2) {
	                        System.out.println("wrong number of arguments");
	                    }
                            else if (!isInteger(tokens[1].trim())) {
                                System.out.println("second argument must be an int");
                            }
	                    else {
	                        String title = tokens[0].trim();
	                        int newRating = Integer.parseInt(tokens[1].trim());
	                        
	                        // *** Add more code to implement this option ***
	                        if(movie_database.containsMovie(title))
				{
	                       		if(movie_database.updateRating(title.toLowerCase(),newRating))//check return value bye the update function
					{
						System.out.println("update successful");
					}
					else
					{
						System.out.println("invalid rating");
					}
				}
				else
				{
					System.out.println("movie not found");
				}
	                    }
	                    break;

	                case 's':
	                    // The following code reads in a comma-separated sequence 
	                    // of strings.  If there are exactly two strings in the 
	                    // sequence, the strings are assigned to name1 and name2.
	                    // Otherwise, an error message is printed.
	                    String[] tokens1 = remainder.split("[,]+");
	                    if (tokens1.length != 2) {
	                        System.out.println("need to provide exactly two names");
	                    }
	                    else {
	                        String name1 = tokens1[0].trim();
	                        String name2 = tokens1[1].trim();
	                        
	                        // *** Add more code to implement this option **
	                        if( movie_database.containsActor(name1.toLowerCase()) && movie_database.containsActor(name2.toLowerCase()))//check if both actors are present in the database or not
				{
					List<String> common_movies = new ArrayList<String>();//List to store movies comman to both
					Iterator<Movie> itr = movie_database.iterator();
					
					while( itr.hasNext())//Check every movie and see if both have acted in it or not
					{
						Movie temp = itr.next();
						List<String> cast = movie_database.getCast(temp.getTitle());
						if(cast.contains(name1.toLowerCase()) && cast.contains(name2.toLowerCase()))
						{
							common_movies.add(temp.getTitle());
						}
						
					}
					if(common_movies.isEmpty())//print none if the list is empty
					{
						System.out.println("none");
					}
					else//print the common movies in the list
					{
						Iterator<String> itr1 = common_movies.iterator();
						
						while(itr1.hasNext())
						{
							System.out.print(toTitleCase(itr1.next()));
							if(itr1.hasNext())
								System.out.print(", ");
		
						}
						System.out.print("\n");	
					}
				}
				else	
				{
					System.out.println("none");
				}
	                    }
	                    break;
	
	                default: // ignore any unknown commands
                    	System.out.println("Incorrect command.");
	                	break;
	                
	                } // end switch
                } // end if
                else { //if there is no argument
                	switch (choice) { // the commands without arguments
                	
                	case 'd': 
	                    // *** Add code to implement this option ***
				Iterator<Movie> itr = movie_database.iterator();
				List<String> actor_list = new ArrayList<String>();//list to store unique actors
				List<String> most_popular_movies = new ArrayList<String>();//List to store one or more most popular movies
				int max_rating = 0;
				int max_actors = 0, min_actors = 100, num = 0;
				double average_actors = 0;
				while(itr.hasNext())//Iterate over all movies
				{
					Movie temp_movie = itr.next();
					List<String> cast = movie_database.getCast(temp_movie.getTitle());
					Iterator<String> itr1 = cast.iterator();
					while(itr1.hasNext())//Iterate over all the cast of the movie
					{
						String actor = itr1.next();
						if(!actor_list.contains(actor))//If a new actor is found add it to the list
							actor_list.add(actor);
					}
					//Keep a track of max actors min actors and average actors per movie
					if(max_actors < cast.size())
						max_actors = cast.size();
					if(min_actors > cast.size())
						min_actors = cast.size();
					average_actors += cast.size();
					num++;
					//Find the max rating for any movie  in the database
					if(temp_movie.getRating() > max_rating)
					{
						max_rating = temp_movie.getRating();
					}

					
				}
				average_actors = average_actors / Math.max(1, num);//Compute the average rating.....used max to avoid dividing by zero
				
				
				System.out.print("Movies: " + movie_database.size() + " Actors: " + actor_list.size() + "\n");
				System.out.print("# of actors/movie: most " + max_actors + ", least " + min_actors  + ", average " + Math.round(average_actors) + "\n");
				//Look for Movies with rating equal to max rating
				itr = movie_database.iterator();
				while(itr.hasNext())
				{
					Movie temp_movie = itr.next();
					if(temp_movie.getRating() == max_rating)
					{
						most_popular_movies.add(temp_movie.getTitle());
					}
				}
				System.out.print("Most Popular Movie(s): ");
				Iterator<String> itr2 = most_popular_movies.iterator();
				while(itr2.hasNext())//Print all movies with max rating
				{
					String temp_mov = itr2.next();
					System.out.print(toTitleCase(temp_mov));
					if( itr2.hasNext())
						System.out.print(", ");
				}
				System.out.print(" [" + max_rating + "]\n");
				//Look for actors with highest average rating
				Iterator<String> itr3 = actor_list.iterator();
				List<String> popular_actors = new ArrayList<String>();//list to store one or more most popular actors
				long max_actor_rating = 0;
				while(itr3.hasNext())//get the max actor rating in the database
				{
					String temp_actor = itr3.next();
					if(max_actor_rating <  Math.round( movie_database.getActorRating(temp_actor)))
						max_actor_rating = Math.round(movie_database.getActorRating(temp_actor));
				}
				itr3 = actor_list.iterator();
				while(itr3.hasNext())//Look for actor having the max rating
				{
					String temp_actor = itr3.next();
					if(max_actor_rating == Math.round(movie_database.getActorRating(temp_actor)))
						popular_actors.add(temp_actor);

				}
				//Print out actors with highest rating
				System.out.print("Most Popular Actor(s): ");
				Iterator<String> itr4 = popular_actors.iterator();
				while(itr4.hasNext())
				{
					String temp_actor = itr4.next();
					System.out.print(toTitleCase(temp_actor));
					if(itr4.hasNext())
						System.out.print(", ");
	
				}
				System.out.print(" [" + Math.round(max_actor_rating) + "]\n");

	                    break;
	                    
                	case 'x':
	                    done = true;
	                    System.out.println("exit");
	                    break;
	                    
                	default:  // a command with no argument
                		System.out.println("Incorrect command.");
	                    break;
                	} // end switch
                } // end else  
           } // end if
        } // end while
    } // end main

    /**
     * Checks if a given string can be parsed to an int.
     * 
     * @param s the string to check
     * @return true if s can be cast to an int, false otherwise
     */
    private static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        }
        return true;
    }
	//Refered stackoverflow for title case conversion: stackoverflow.com/questions/1086123/titlecase-conversion
	private static String toTitleCase(String input)
	{
		StringBuilder titleCase = new StringBuilder();
		boolean nextTitleCase = true;
		for (char c : input.toCharArray())
		{
			if(Character.isSpaceChar(c))
			{
				nextTitleCase = true;
			}
			else if (nextTitleCase)
			{
				c = Character.toTitleCase(c);
				nextTitleCase = false;
			}
			titleCase.append(c);

		}
		return titleCase.toString();
	}

}
