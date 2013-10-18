///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TaskQueueMain.java
// File:             InvalidListPositionException.java
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

/**
 * This class extends the Exception class to support InvalidListPositionExecptions in this program.  
 * <p>Bugs: 
 *
 * @ Aashish Thite, Chris Fernandez 
 */

public class InvalidListPositionException extends Exception
{

	 /**
	 * InvalidLIstPositionException constructor method constructs an exception for invalid list positions. 
	 */

	public InvalidListPositionException()
	{
		super();
	}

	 /**
	 * InvalidLIstPositionException constructor method constructs an exception for invalid list positions. 
	 * @param message: message to be displayed when the get message method on InvalidLIstPositionException is called. 
	 */

	public InvalidListPositionException(String message)
	{
		super(message);
	}
}



