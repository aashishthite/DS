
class LinkedList<E> implements ListADT<E>
{
	ListNode<E> head;
	int num_items;
	
	
	//Constructor
	LinkedList()
	{
		head = null;
		num_items = 0;
	}
	
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
			{
				temp = temp.getNext();
			}
			temp.setNext(new_node);
			num_items++;
		}
				
	}
	
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
	
	public boolean isEmpty()
	{
		return num_items == 0;
	}
	
	public int size()
	{
		return num_items;
	}
	
	public String print(boolean lineNumbers)
	{
		String ret_val = "";
		if(lineNumbers)
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
		{
			ListNode<E> temp = head;
			for(int i = 1; i<num_items; i++)
			{
				E temp_data =  temp.getData();
				//try
				//{
					ret_val += temp_data.toString() + "\n";
					temp = temp.getNext();
				//}
				//catch(NoSuchMethodException e)
				//{
				//	System.out.println("Invalid data in the list");
				//}
			}
			ret_val += num_items + " " + temp.getData().toString();

		}
		return ret_val;
	}
	
}
