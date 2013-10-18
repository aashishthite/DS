///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StockDictionary.java
// File:             BSTSortedListIterator.java
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


import java.util.*;

/**
 * BSTSortedListIterator implements an iterator for a binary search tree (BST)
 * implementation of a Sorted List.
 *
 * <p>Bugs: none
 *
 * @author Chris Fernandez & Aashish Thite
 */

public class BSTSortedListIterator<K extends Comparable<K>> implements Iterator<K> {

	private Stack<BSTnode<K>> stack; //for keeping track of nodes
	
	/**
	 * Iterator constructor
	 *
	 * @param root: the root node for the given BSTSortedList to be sent to the
	 * recursive ordered stack method
	 */	

	public BSTSortedListIterator(BSTnode<K> root) 
	{
		this.stack = new Stack<BSTnode<K>>();
		//calls recursive method to sort the BST stack
		recursiveSoln(root);
	} 

	/**
	 * Checks if stack has another item in the stack 
	 *
	 * @return: true if stack is not empty, false if it is 
	 */	

    public boolean hasNext() 
    {
	return !stack.empty();    	
    	
    }

	/**
	 * Obtains the next item in the stack 
	 *
	 * @return: the item that was poped off the top of the stack 
	 */	

    public K next() 
    {
	try
	{
		//temp variable stores poped node and key 
    		BSTnode<K> temp;
    		temp = stack.pop();
    		return temp.getKey();

	}
	catch(EmptyStackException e)
    	{
    		throw new NoSuchElementException();
    	}
    }

    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }
    
	/**
	 * A recursive solution for creating a sorted BST stack 
	 *
	 * @param n: the node to be added to the stack
	 */	

    private void recursiveSoln(BSTnode<K> n)
    {
    	//base case
    	if(n == null)
		return;
    	if (n.getRight() == null && n.getLeft() == null)
    	{
		//pushes node if it is a leaf
    		stack.push(n);
    	
    	}
    	else 
    	{
		//calls this method on right child
    		recursiveSoln(n.getRight());
		//pushes current node onto stack
    		stack.push(n);
		//calls this method on left child 
		recursiveSoln(n.getLeft());
    	}
    }
}
