///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TaskQueueMain.java
// File:             LinkedList.java
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
 * The LinkedList class is used to implement a ListADT interface 
 * and contains methods to add, remove, get, check if empty, check size, and print the contents of the list. 
 * <p>Bugs: 
 *
 * @ Aashish Thite, Chris Fernandez 
 */

class LinkedList<E> implements ListADT<E>
{
	ListNode<E> head;
	int num_items;
	
	/**
	 * Linked List constructor method constructs a linked list data structure. 
	 */
	
	//Constructor
	LinkedList()
	{
		head = null;
		num_items = 0;
	}

	/**
	 * Adds a generic type item to the list after the last node of the list 
	 *
	 * @param item: an item to be added to the LinkedList 
	 */
	
	public void add(E item)
	{
		ListNode<E> new_node = new ListNode<E>(item);
		if(head == null)
		{
			head = new_node;
			num_items++;
			return;
		}
		else
		{
			ListNode<E> temp = head;
			while(temp.getNext() != null)
			// gets the last position in the list and creates a new node 
			{
				temp = temp.getNext();
			}
			temp.setNext(new_node);
			num_items++;
		}
				
	}

	 /**
	 * Adds a generic type item to the list at a specified position in the list 
	 *
	 * @param item: an item to be added to the LinkedList 
	 * @param pos: the position on the list that the item is added to 
	 */
	
	public void add(int pos, E item) throws InvalidListPositionException
	{
		if(pos >= 0 && pos <= num_items)
		{
			if(head == null)
			{
				this.add(item);
				return;
			}
			if(pos == num_items)
			{
				this.add(item);
				return;
			}
			else if(pos == 0)
			{
				head = new ListNode<E> (item, head);
				num_items++;
				return;			
			}
			else
			{
				ListNode<E> temp = head;
				for( int i = 0; i< pos-1; i++)
				{
					temp = temp.getNext();
				}
				temp.setNext(new ListNode<E>(item, temp.getNext()));
				num_items++;
				return;
			}
		}
		else
		{
			throw new InvalidListPositionException();
		}
	}

	 /**
	 * Removes a generic type item from a specified position in the list
	 *
	 * @param pos: the position on the list that the item is removed from
	 * @returns the data at the position which is getting removed 
	 */
	
	
	public E remove(int pos) throws InvalidListPositionException
	{
		if(pos >= 0 && pos < num_items)
		{
			if(pos == 0)
			{
				E ret_val = head.getData();
				head = head.getNext();
				num_items--;
				return ret_val;
			}
			else if( pos == num_items-1)
			{
				ListNode<E> temp = head;
				for(int i = 1; i < num_items-1 ;i++)
				{
					temp = temp.getNext();
				}
				E ret_val = temp.getNext().getData();
				temp.setNext(null);
				num_items--;
				return ret_val;
				
			}
			else
			{

				ListNode<E> temp = head;
				for(int i = 0; i < pos - 1; i++)
				{
					temp = temp.getNext();
				}	
				E ret_val = temp.getNext().getData();
				temp.setNext(temp.getNext().getNext());
				num_items--;
				return ret_val;
			}
		}
		else
		{
			throw new InvalidListPositionException();
		}
	}
	
 	 /**
	 * Returns the data contained at a specified position in the list
	 *
	 * @param pos: the position on the list that the data is obtained from
	 * @returns the data at the specified position 
	 */

	public E get(int pos) throws InvalidListPositionException
	{
		if(pos >= 0 && pos < num_items)
		{
			ListNode<E> temp = head;
			for(int i = 0; i < pos; i++)
			{
				temp = temp.getNext();
			}
			return temp.getData();			
		}
		else
		{
			throw new InvalidListPositionException();
		}
	}

	 /**
	 * Checks if the list contains any items 
	 *
	 * @returns true if num_items is equal to 0 (i.e. the list is empty)
	 */
	
	public boolean isEmpty()
	{
		return num_items == 0;
	}
	
	 /**
	 * Obtains the size of the list
	 *
	 * @returns the current number of items in the list 
	 */

	public int size()
	{
		return num_items;
	}
	
	/**
	 * Returns a string with the given contents of the list, in one of two formats.
	 *
	 * @param lineNumbers: true sets the format of the list to be returned to contain line numbers, and false to contain no line numbers. 
	 * @returns a string with the contents of the list, in order. 
	 */

	public String print(boolean lineNumbers)
	{
		String ret_val = "";
		if(lineNumbers)
		// returns a string containing line numbers if line numbers is true 
		{
			ListNode<E> temp = head;
			for(int i = 1; i<num_items; i++)
			{
				Task temp_task = (Task) temp.getData();
				ret_val += i + " " + temp_task.toString() + "\n";
				temp = temp.getNext();
			}
			ret_val += num_items + " " + temp.getData().toString();
		}
		else
		// returns a string without line numbers 
		{
			ListNode<E> temp = head;
			for(int i = 1; i<num_items; i++)
			{
				E temp_data =  temp.getData();
					ret_val += temp_data.toString() + "\n";
					temp = temp.getNext();
			}
			ret_val += num_items + " " + temp.getData().toString();

		}
		return ret_val;
	}
	
}
