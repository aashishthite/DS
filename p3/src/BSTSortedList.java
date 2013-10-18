import java.util.Iterator;
import java.util.List;

public class BSTSortedList<K extends Comparable<K>> implements SortedListADT<K> {
    private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the sorted list
	
    // TO DO:
    // Add a no-argument constructor
    // Add your code to implement the Sorted List ADT operations using a binary
    // search tree.
    // You may use any code given in the on-line reading on BSTs.
    // You may handle duplicates in any way you like. Your code will not 
    // be tested with duplicate values.
    
    public BSTSortedList()
    {
    	root = null;
    }
    
	public void insert(K key) 
	{
		root = insert(root, key);
		numItems++;
	}
	
	private BSTnode<K> insert(BSTnode<K> n, K key)
	{
		if (n == null) 
		{
			return new BSTnode<K>(key, null, null);
		}
     
		if (n.getKey().equals(key)) 
		{
			return null; 
		}
    
		if (key.compareTo(n.getKey()) < 0) 
		{
			// add key to the left subtree
			n.setLeft( insert(n.getLeft(), key) );
			return n;
		}
		else 
		{
			// add key to the right subtree
			n.setRight( insert(n.getRight(), key) );
			return n;
    	}
	}
	
	public boolean delete(K key) 
	{
		//BSTnode<K> temp;
		//temp = delete(root, key);
		//if (temp == null)
		int temp = numItems;
		//root = delete(root, key);
		if(root == null)
			return false;	

		if (lookup(key) != null)
		{
			root = delete(root,key);
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	private BSTnode<K> delete(BSTnode<K> n, K key)
	{
		if (n == null) 
		{
			return null;
		}
		    
		if (key.equals(n.getKey())) 
		{
		    // n is the node to be removed
			if (n.getLeft() == null && n.getRight() == null) 
			{
				numItems--;
				return null;
			}
		      
		    if (n.getLeft() == null) 
		    {
				numItems--;
	            return n.getRight();
		    }
		        
		    if (n.getRight() == null) 
		    {
				numItems--;
		    	return n.getLeft();
		    }
		       
		    // if we get here, then n has 2 children
		    K smallVal = smallest(n.getRight());
		    n.setKey(smallVal);
		    n.setRight( delete(n.getRight(), smallVal) );
		//	numItems--;
		    return n; 
		}
		else if (key.compareTo(n.getKey()) < 0) 
		{
			n.setLeft( delete(n.getLeft(), key) );
			return n;
		}
		else 
		{
			n.setRight( delete(n.getRight(), key) );
			return n;
		}	
	}

	public K lookup(K key) 
	{
		return lookup(root, key);
	}
	
	private K lookup(BSTnode<K> n, K key) 
	{
		if (n == null) 
		{
	        return null;
	    }
	    if (n.getKey().equals(key)) 
	    {
	        return n.getKey();
	    }	    
	    if (key.compareTo(n.getKey()) < 0) 
	    {
	        return lookup(n.getLeft(), key);
	    }
	    else 
	    {
	        return lookup(n.getRight(), key);
	    }
	}

	public int size() 
	{
		return numItems;
	}

	public boolean isEmpty() 
	{
		if (numItems == 0)
		{
			return true; 
		}
		else 
		{
			return false;
		}
	}

	public Iterator<K> iterator() 
	{
		// TODO Auto-generated method stub
		Iterator<K> itr = new BSTSortedListIterator<K>(root);
		return itr;
	    //return new BSTSortedListIterator<K>(root);
	}
	
	private K smallest(BSTnode<K> n)
	{
	    if (n.getLeft() == null) 
	    {
	    	return n.getKey();
	    } 
	    else 
	    {
	        return smallest(n.getLeft());
	    }
	}
}
