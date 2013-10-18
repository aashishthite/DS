///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            p2
// Files:            TaskQueueMain.java, LinkedList.java, 
//                   InvalidPositionException.java
// Semester:         CS367 Summer 2013
//
// Author:           Aashish Thite 
// Email:            thite@wisc.edu
// CS Login:         aashish
// Lecturer's Name:  Bryan Gibson
// Lab Section:      N/A
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Chris Fernandez
// Email:            crfernandez@wisc.edu
// CS Login:         fernande
// Lecturer's Name:  Bryan Gibson
// Lab Section:      N/A
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Scanner;
import java.util.*;
import java.io.*;

/**
 * The TaskQueueMain class creates and uses a LinkedList data structure to 
 * process and represent a queue of Task objects. This class contains add,
 * load, move, print, remove, save, total, and update commands for user
 * interaction with the list of tasks. 
 * <p>Bugs: 
 *
 * @ Aashish Thite, Chris Fernandez 
 */

public class TaskQueueMain {
	
	 /**
	 * The TaskQueueMain method accepts user commands to execute the following functions:
	 * Add a task, load a past task queue, move the order of tasks, print the list of tasks, remove specified tasks, 
	 * save the list of tasks, total the length of time it takes to complete tasks, and update the time it takes to complete tasks 
	 */

	public static void main(String args[]) {
		
		LinkedList<Task> taskQueue = new LinkedList<Task>();
		//** You may also add additional variables as needed **//
		
		Scanner stdin = new Scanner(System.in);  // for reading console input
        	boolean done = false;
        	while (!done) {
			// loops until a valid input is provided
            		System.out.print("Enter option - a, l, m, p, r, s, t, u, or x: ");
            		String input = stdin.nextLine();
            		char choice = 'w';
			if(input.length() > 0)
			{
		       	 	choice = input.charAt(0);  // strip off option character
			}
			
		        String remainder = "";  // used to hold the remainder of input
            		if (input.length() > 0) {
		        	// trim off any leading or trailing spaces
		        	remainder = input.substring(1).trim();
			}
		        
		        switch (choice) {
		        
		        case 'a' :
			// adds a task to the current Task Queue
		        	
				if(remainder != "")
				//opens the file to read 
				{
					File inputFile = new File(remainder.toLowerCase() + ".txt");
					if(inputFile.exists() && inputFile.canRead())
					{
						try
						{	
							Scanner inFile = new Scanner(inputFile);
							ArrayList<String> tasklist = new ArrayList<String>();
	
							while(inFile.hasNextLine())
							{
								tasklist.add(inFile.nextLine());		
							}	
							for(int i = 1; i<= tasklist.size(); i++)
							// prints the contents of the input file 
							{
								System.out.println(i + " " +  tasklist.get(i-1));
							}	
							boolean done_adding = false;
							while(!done_adding)
							{
							// ask for user input and adds the specified task to the list 
								System.out.println("Please enter a number between 1 and " + tasklist.size() +", followed by the task duration in minutes");
								String task_num_duration = stdin.nextLine();
								Scanner lineScanner = new Scanner(task_num_duration);
								lineScanner.useDelimiter(" ");
								if(lineScanner.hasNext())
								{	
									String task_num = lineScanner.next();
									if(isInteger(task_num))
									{
										int task_num_int = Integer.parseInt(task_num);
										if(lineScanner.hasNext() && task_num_int > 0 && task_num_int <=tasklist.size())
										{
											String task_dur = lineScanner.next();
											if(lineScanner.hasNext())
											{
												continue;
											}	
											if(isInteger(task_dur))
											{
												int task_dur_int = Integer.parseInt(task_dur);
												if(task_dur_int > 0)
												{
													taskQueue.add(new Task(tasklist.get(task_num_int - 1),task_dur_int));
													System.out.println("Added " + tasklist.get(task_num_int - 1) + " to queue.");
													done_adding = true;
												}
											}
										}	
									}				
								}			
							}
						}
						catch(FileNotFoundException e)
						{
							System.out.println("Exception caught: File not found.");
						}
					}
					else
					{
						System.out.println("Cannot find the specified file.");
					}
				}
				else
				{
					System.out.println("Invalid argument.");
				}

		        	
		        	break;
		        
		        case 'l' :
		        	// loads a file specified by user input

		        	if(remainder !="")
				{
					// opens the file, validates the contents, and adds the text to the Task Queue as task objects 
					File inputFile = new File(remainder);
					if(inputFile.exists() && inputFile.canRead())
					{
						try
						{	
							Scanner inFile = new Scanner(inputFile);
							ArrayList<String> tasklist = new ArrayList<String>();
							taskQueue = new LinkedList<Task>();
	
							while(inFile.hasNextLine())
							{
								String temp_line = inFile.nextLine();
								Scanner lineReader = new Scanner(temp_line);
								lineReader.useDelimiter(", ");
								if(lineReader.hasNext())
								{
									String temp_task = lineReader.next();
									if(lineReader.hasNext())
									{
										String temp_dur = lineReader.next();
										if(isInteger(temp_dur))
										{
											int temp_dur_int = Integer.parseInt(temp_dur);
											taskQueue.add(new Task(temp_task, temp_dur_int));
										}
										else
										{
											System.out.println("Invalid file.");
											break;
										}
									}
									else
									{
										System.out.println("Invalid file.");
										break;
									}
								}
								else
								{
									System.out.println("Invalid file.");
									break;
								}
							}
							System.out.println(taskQueue.print(true));
						}
						catch(IOException e)
						{
							System.out.println("Cannot find specified file.");
							
						}
					}
					else
					{
						System.out.println("Cannot find specified file.");
					}

				}
				else
				{
					System.out.println("Invalid argument.");	
				}	
		        	break;
		        	
		        case 'm' :
		        	
		        	// moves a task at a position in the list to the front position
		       		if(remainder != "" && isInteger(remainder))
				{
					try
					{
						Task temp = taskQueue.remove(Integer.parseInt(remainder) - 1);
						taskQueue.add(0,temp);
						System.out.println("Task " + temp.getName() + " moved to front of queue.");
					}
					catch(InvalidListPositionException e)
					{
						System.out.println("Invalid line number.");
					}
				}
				else
				{
					System.out.println("Invalid line number.");
				}	
		        	break;
		        	
		        case 'p' :
		        	
		        	// prints the task queue in a specified format 
				if(taskQueue.size() == 0)
				{
					System.out.println("Empty.");
				}
				else
				{
					System.out.println(taskQueue.print(true));
				}
		        	
		        	break;
		        	
		        case 'r' :
		        	
		        	// removes the task at a specified position in the list 
		        	if(remainder!="" && isInteger(remainder))
				{
					try
					{
						Task temp = taskQueue.remove(Integer.parseInt(remainder) - 1);
						System.out.println("Removed " + temp.getName() + " from queue.");
					}
					catch(InvalidListPositionException e)
					{
						System.out.println("Invalid line number.");
					}
				}
				else
				{
					System.out.println("Invalid line number.");
				}
		        	break;
		        	
		        case 's' :
		        	
		        	// saves the current Task Queue
		        	if(remainder != "")
				{
					if(!taskQueue.isEmpty())
					{

		        			try
						{
							FileWriter out_file = new FileWriter(remainder);
							BufferedWriter out = new BufferedWriter(out_file);
							out.write(taskQueue.print(false));
							out.close();
							System.out.println("Saved.");
						}					
						catch(IOException e)
						{
							System.out.println("Cannot write to the specified file.");
						}
					}
					else
					{
						System.out.println("Cannot write to file, task queue is empty.");
					}
				}
				else
				{
					System.out.println("Invalid argument.");
				}
		        	break;
		        	
		        case 't' :
				// prints the total duration of a user specified number of tasks 
		        	if(remainder!= "" && isInteger(remainder))
				{
					int num = Integer.parseInt(remainder);
					if(num > 0)
					{
						int sum = 0;
						for(int i = 0; i< Math.min(num, taskQueue.size()); i++)
						{
							try
							{
							sum = sum + taskQueue.get(i).getDuration();
							}
							catch(InvalidListPositionException e)
							{
								System.out.println("Invalid argument.");
							}
						}
						int hr = sum/60;
						int minutes = sum % 60;
						System.out.println("Time to complete next " + num + " tasks: " + hr + " hours, " +  minutes + " minutes");
					}
					else
					{
						System.out.println("Invalid argument.");
					}
				}
				else
				{
					System.out.println("Invalid argument.");
				}
		        	
		        	break;

		        case 'u' :
		        	
		        	// updates the duration of a specified task per user input 
				Scanner remainderScanner = new Scanner(remainder);
				remainderScanner.useDelimiter(" ");
				if(remainderScanner.hasNext())
				{
					String line_num = remainderScanner.next();
					if(isInteger(line_num))
					{
						int line_num_int = Integer.parseInt(line_num);
						if(remainderScanner.hasNext())
						{
							String new_dur = remainderScanner.next();
							if(remainderScanner.hasNext())
							{
								System.out.println("Invalid argument.");
							}
							else
							{
								if(isInteger(new_dur))
								{
									int new_dur_int = Integer.parseInt(new_dur);
									if(new_dur_int > 0)
									{
										try
										{
											Task temp = taskQueue.get(line_num_int - 1);
											int old_dur = temp.getDuration();
											temp.setDuration(new_dur_int);
											System.out.println("Updated the duration of " + temp.getName() + " from " + old_dur + " to " + new_dur_int + ".");
										}
										catch(InvalidListPositionException e)
										{
											System.out.println("Invalid line number.");
										}
									}
									else
									{
										System.out.println("Invalid duration value.");
									}
								}
								else
								{
									System.out.println("Invalid duration value.");
								}
							}
						}
						else
						{
							System.out.println("Invalid argument.");
						}

					}								
				}
				if(remainder == "")
				{
					System.out.println("Invalid argument.");
				}
		        	break;
		        	
		        case 'x' :
		        	//Exit the program. This command is already implemented.
		        	done = true;
		            	System.out.println("exit");
		            	break;
		        	
		        default :
		        	System.out.println("Unknown Command");
		        	break;
                	}
		}
	}

	/**
	 * The is Integer method checks to see if a given string s can be recognized as an integer. 
	 *
	 * @param s
	 * @return true if String s can be recognized as an int
	 */

	//Picked up this part from project 1
	private static boolean isInteger(String s)
	{
		try
		{
			Integer.parseInt(s);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}
}
