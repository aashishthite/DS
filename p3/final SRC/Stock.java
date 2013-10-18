///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StockDictionary.java
// File:             Stock.java
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
 * Methods and constructor to implement Stock objects including the stock's symbol, 
 * the company name, and a its current share price using the Comparable interface. 
 * <p>Bugs: none
 *
 * @author Chris Fernandez & Aashish Thite
 */


public class Stock implements Comparable<Stock>
{
	//members
	private String symbol;
	private String name;
	private double price;

	/**
	 * Stock object constructor
	 *
	 * @param symbol: the stock's symbol
	 * @param name: the company's name
	 * @param price: the stock's price
	 */
	public Stock(String symbol, String name, double price)
	{
		if( isValidString(symbol) && isValidString(name) && price > 0)
		{
			this.symbol = symbol.toUpperCase();
			this.name = name.toUpperCase();
			this.price = price;
		}
		else
			throw new IllegalArgumentException();
	}
	//methods
	
	/**
	 * Compares current stock to be compared 'other' stock symbol 
	 *
	 * @param other: a stock to be compared with the current stock
	 * @return -1 if other.symbol is less than this.symbol, 0 if equal, and 1 if greater
	 */

	public int compareTo(Stock other)
	{
		//compares current stock to 'other' 
		return this.symbol.compareTo(other.getSymbol().toUpperCase());
	}
	

	/**
	 * Checks if current stock is equal to 'other' stock symbol 
	 *
	 * @param other: a stock to be compared with the current stock
	 * @return true if stock symbols are equal, false if not
	 */

	@Override
	public boolean equals(Object other)
	{
		if(other != null)
		{
			try
			{
				if(((Stock)other).getSymbol().toUpperCase().equals(this.symbol))
					return true;
				else 
					return false;
			}
			catch(Exception e)
			{
				return false;
			}
		
		}
		else
			return false;
			
	}
	
	/**
	 * Obtains current stock symbol  
	 *
	 * @returns current stock symbol
	 */

	public String getSymbol()
	{
		return this.symbol;
	}
	
	/**
	 * Obtains current stock name  
	 *
	 * @returns current stock name
	 */

	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Obtains current stock price  
	 *
	 * @returns current stock price
	 */

	public double getPrice()
	{
		return this.price;
	}
	
	/**
	 * Updates current stock symbol  
	 *
	 * @param newSymbol: the updated stock symbol 
	 */

	public void setSymbol(String newSymbol) throws IllegalArgumentException
	{
		if(isValidString(newSymbol))
			this.symbol = newSymbol;
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Updates current stock name  
	 *
	 * @param newName: the updated stock name 
	 */

	public void setName(String newName) throws IllegalArgumentException
	{
		if(isValidString(newName))
			this.name = newName;
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Updates current stock price  
	 *
	 * @param newPrice: the updated stock price 
	 */

	public void setPrice(double newPrice) throws IllegalArgumentException
	{
		if(newPrice > 0)
			this.price = newPrice;
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Updates format of information contained in current stock object
	 *
	 * @returns symbol: (name): $price
	 */	

	@Override
	public String toString()
	{
		return this.symbol + "(" + this.name + "): $" + this.price;		
	}
	
	/**
	 * Checks if current string is in valid format
	 *
	 * @param myString: the string to be validated
	 */

	private boolean isValidString(String myString)
	{
		if(myString == "")
			return false;

		else if(myString.charAt(0) != ' ' && !myString.contains(":"))
			return true;

		else
			return false;
	}


}
