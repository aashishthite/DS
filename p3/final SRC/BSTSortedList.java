///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StockDictionary.java
// File:             BSTSortedList.java
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
 * BSTSortedList implements the Sorted List ADT operations using a binary search tree (BST)
 *
 * <p>Bugs: none
 *
 * @author Chris Fernandez & Aashish Thite
 */

import java.util.Iterator;
import java.util.List;

public class BSTSortedList<K extends Comparable<K>> implements SortedListADT<K> {
    private BSTnode<K> root;  // the root node
    private int numItems;     // the number of items in the sorted list
	
	/**
	 * BSTSortedList constructor
	 *
	 */
    
    	public BSTSortedList()
   	 {
    		root = null;
   	 }

	/**
	 * Inserts a new BSTnode with a given key into the BSTSortedList
	 * at the correct position
	 *
	 * @param key: the key of the new node to be inserted
	 */
	    
	public void insert(K key) 
	{
		//calls overidden private insert method
		root = insert(root, key);
		numItems++;
	}
	
	private BSTnode<K> insert(BSTnode<K> n, K key)
	{
		if (n == null) 
		{
			//if list is empty 
			return new BSTnode<K>(key, null, null);
		}
     
		if (n.getKey().equals(key)) 
		{
			//if key is duplicate
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

	/**
	 * Deletes a BSTnode with a given key from the BSTSortedList
	 * and arranges children to their the correct positions
	 *
	 * @param key: the key of the node to be deleted
	 * @return: true if node deleted, false if no node contains the given key 
	 */	

	public boolean delete(K key) 
	{

		int temp = numItems;
		//if there is no BSTnode
		if(root == null)
			return false;	

		//if the BST list contains the given key 
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
			//n has no children 
			if (n.getLeft() == null && n.getRight() == null) 
			{
				numItems--;
				return null;
			}
		    //n has one child  
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
		       
		    //n has 2 children, reorganizes children using smallest method 
		    K smallVal = smallest(n.getRight());
		    n.setKey(smallVal);
		    n.setRight( delete(n.getRight(), smallVal) );
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

	/**
	 * Iterates through BSTSortedList to check if it contains a BSTnode with a given key
	 *
	 * @param key: the key of the node to be looked up 
	 * @return: null if key is not found, the key of the specified node if found 
	 */

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

	/**
	 * Returns the size of the list
	 *
	 * @return: number of items in the list 
	 */

	public int size() 
	{
		return numItems;
	}

	/**
	 * Checks if the BSTSortedList is empty 
	 *
	 * @return: true if the list contains no items, false otherwise
	 */

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

	/**
	 * Calls the BSTSortedListIterator contructor and returns a sorted list iterator
	 *
	 * @return: an iterator for iterating through the sorted BST list
	 */

	public Iterator<K> iterator() 
	{
		Iterator<K> itr = new BSTSortedListIterator<K>(root);
		return itr;
	}
	
	/**
	 * Determines the smallest child on the right subtree of a given node
	 *
	 * @param n: a BSTnode on the BSTSortedList
	 * @returns the smallest right child for a given node
	 */

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
