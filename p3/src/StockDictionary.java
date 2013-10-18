import java.util.*;
import java.io.*;

/**
 * The StockDictionary creates and manipulates a sorted list of Stock objects.  
 */
public class StockDictionary {
    /**
     * The main method provides the user interface as described in the program 
     * write-up.  You will need to add to the code given here.
     * 
     * @param args the command-line arguments that determine how input and 
     * output is done in the game:
     * <ul>
     *   <li>if there are no command-line arguments, then console input and 
     *   console output are used</li>
     *   <li>if there is one command-line argument, then it is treated as the
     *   name of the file from which to get input and output is sent to the
     *   console</li>
     *   <li>if there are two command-line arguments, then the first is the name
     *   of the file from which to get the input and the second is the name of 
     *   the file to which to sent the output</li>
     * </ul>
     * 
     * You may add additional static variables or methods as needed.
     */
	
    public static void main(String[] args) throws IOException {
        Scanner in = null;         // for input
        PrintStream out = null;    // for output
        SortedListADT<Stock> dictionary = new BSTSortedList<Stock>();  
                                   // the Stock dictionary
        boolean echo = false;	   // whether or not to echo the user input
        
        // Set up where to send input and output
        switch (args.length) {
        case 0: 
        	//Add code here
        	in = new Scanner(System.in);
		out = System.out;
		echo =false;
            break;

        case 1:
        	//Add code here
        	File inputFile = new File(args[0]);
		if(inputFile.exists() && inputFile.isFile() && inputFile.canRead())
		{
			try
			{
				in = new Scanner(inputFile);
			}
			catch(FileNotFoundException e)
			{
				System.out.println("File not found.");
			}
		}
		else
		{
			System.out.println("File not found.");
		}
		out = System.out;
		echo =true;
            break;

        case 2: 
        	//Add code here
		 inputFile = new File(args[0]);
		if(inputFile.exists() && inputFile.isFile() && inputFile.canRead())
		{
			try
			{
				in = new Scanner(inputFile);
			}
			catch(FileNotFoundException e)
			{
				System.out.println("File not found.");
			}
		}
		else
		{
			System.out.println("File not found.");
		}
		
		try
		{
			out = new PrintStream(new FileOutputStream(args[1]));
		}
		catch(IOException e)
		{
			System.out.println("Unable to write to the file");
		}
		echo = true;
            break;

        default:
            System.err.println("Invalid command-line arguments");
            System.exit(0);
        }

        boolean again = true;
        while (again)
	 {
            out.print("enter choice (a, d, f, p, q): ");
            out.flush();
            String input = in.nextLine();
            if (echo) out.println(input);
            if (input.length() == 0)
	    {
                out.println("invalid input");
            }

            else {
                // We will have our program be case-insensitive, so we'll 
                // convert the first character to lower-case.
                char choice = input.substring(0, 1).toLowerCase().charAt(0);
                String remainder = "";  // used to hold the remainder of input
                // trim off any leading or trailing spaces
                remainder = input.substring(1).trim();

                switch (choice) {
                
                // add a Stock
                // format: a symbol:name:price
                case 'a':  
                	//Add code here
			Scanner lineScanner = new Scanner(remainder);
			lineScanner.useDelimiter(":");
			String symbol="",name="";
			double price = 0;
			if(lineScanner.hasNext())
			{
				symbol = lineScanner.next();
				if(lineScanner.hasNext())
				{
					name = lineScanner.next();
					if(lineScanner.hasNext())
					{
						try
						{
							price = Double.parseDouble(lineScanner.next());
							dictionary.insert(new Stock(symbol,name,price));
						}
						catch(Exception e)
						{
							out.println("Invalid Input");
						}
					}
					else
					{
						out.println("Invalid Input");
					}
				}
				else
				{
					out.println("Invalid Input");
				}
			}
			else
			{
				out.println("Invalid Input");
			}
                    break;

                // delete a Stock
                // format: d symbol
                case 'd':  
                	//Add code here
                	if(!dictionary.delete(new Stock(remainder, "dummy", 100.0)))
			{
				out.println("Not Present");		
			}
                	break;
                    
                // find an Stock
                // format: f symbol
                case 'f':   
			Stock temp = dictionary.lookup(new Stock(remainder, "dummy" , 100.0));
			if(temp == null)
			{
				out.println("Not Present");
			}
			else
			{
				out.println(temp.toString());
			}
                    //Add code here
                	
                    break;
                    
                // print the contents of the dictionary in sorted order
                // format: p
                case 'p':  
                    //Add code here 
                	Iterator<Stock> itr = dictionary.iterator();
			while(itr.hasNext())
			{
				out.println(itr.next().toString());
			}
                    break;
                    
                // quit - this does not need to be changed
                // format: q
                case 'q':  
                    again = false;
                    out.println("done");
		    if(args.length ==2)
			out.close();
                    break;

                default:   // anything else is invalid
                    out.println("invalid choice");
                } // end switch
            } // end else
        } // end while
    } // end main
}
