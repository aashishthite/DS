import java.util.*;

/**
 * BSTSortedListIterator implements an iterator for a binary search tree (BST)
 * implementation of a Sorted List.
 */
public class BSTSortedListIterator<K extends Comparable<K>> implements Iterator<K> {

	private Stack<BSTnode<K>> stack; //for keeping track of nodes
	
	public BSTSortedListIterator(BSTnode<K> root) 
	{
		this.stack = new Stack<BSTnode<K>>();
		recursiveSoln(root);
		//Iterator<BSTnode<K>> itr = stack.iterator();
	} 
    public boolean hasNext() 
    {
	return !stack.empty();    	
    	
    }

    public K next() 
    {
	try
	{
    		BSTnode<K> temp;
    		temp = stack.pop();
    		return temp.getKey();

	}
	catch(EmptyStackException e)
    	{
    		throw new NoSuchElementException();
    	}
    	//Hint: Remember you are using pre-order traversal. The next node is at 
    	//the top of the stack. Pop it, then push its right child. Iteratively 
    	//push all its left children onto the stack as done in the constructor.
    	//Finally, return the key from the popped node. Don't forget to 
    	//throw a NoSuchElementException if there is no next node.
    }

    public void remove() {
        // DO NOT CHANGE: you do not need to implement this method
        throw new UnsupportedOperationException();
    }
    
    private void recursiveSoln(BSTnode<K> n)
    {
    	//base cas
    	if(n == null)
		return;
    	if (n.getRight() == null && n.getLeft() == null)
    	{
    		stack.push(n);
    	
    	}
    	else 
    	{
    		recursiveSoln(n.getRight());
    		stack.push(n);
		recursiveSoln(n.getLeft());
    	}
    }
}
