

public class Stock implements Comparable<Stock>
{
	//members
	private String symbol;
	private String name;
	private double price;
	//Constructor
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
	
	public int compareTo(Stock other)
	{
	//	if(other != null)
			return this.symbol.compareTo(other.getSymbol().toUpperCase());
		//else
		//	return -1;
	}
	
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
	
	public String getSymbol()
	{
		return this.symbol;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public void setSymbol(String newSymbol) throws IllegalArgumentException
	{
		if(isValidString(newSymbol))
			this.symbol = newSymbol;
		else
			throw new IllegalArgumentException();
	}

	public void setName(String newName) throws IllegalArgumentException
	{
		if(isValidString(newName))
			this.name = newName;
		else
			throw new IllegalArgumentException();
	}
	
	public void setPrice(double newPrice) throws IllegalArgumentException
	{
		if(newPrice > 0)
			this.price = newPrice;
		else
			throw new IllegalArgumentException();
	}

	@Override
	public String toString()
	{
		return this.symbol + "(" + this.name + "): $" + this.price;		
	}
	
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
