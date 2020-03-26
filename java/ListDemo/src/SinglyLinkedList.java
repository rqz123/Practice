import java.util.NoSuchElementException;

public class SinglyLinkedList
{
	private ListNode first;
	private ListNode last;

	//Delete one element: first = last = null;
	
	public SinglyLinkedList()
	{
		first = null;
		last = null;
	}

	public Object getFirst()
	{
		if (first == null)
		{
			throw new NoSuchElementException();
		}
		else
			return first.getValue();
	}

	public void addFirst(Object value) 
	{
		if (first == null)
		{
			first = last = new ListNode(value, null);
		}
		else
			first = new ListNode(value, first);
	}
	
	public Object getLast()
	{
		if (last == null)	// RZ
		{
			throw new NoSuchElementException();
		}
		else
			return last.getValue();
	}
	
	public void addLast(Object value)
	{
		if (last == null)
		{
			first = last = new ListNode(value, null);
		}
		else
		{
			ListNode temp = new ListNode(value, null);
			last.setNext(temp);
			last = temp;
		}
	}
	
	public int getSize()
	{
		ListNode temp = first;
		int index = 0;
		
		while(temp != null)
		{
			temp = temp.getNext();
			index++;
		}
		
		return index;
	}
	
	public void deleteIndex(int index)
	{
		// RZ
		if (first == null)
			return;
		
		if (index == 0)
		{
			first = first.getNext();
		}
		else 
		{
			ListNode cur = first.getNext();
			ListNode pre = first;
			int count = 1;
			
			while (cur != null)
			{
				if (count == index)
				{
					cur = cur.getNext();
					pre.setNext(cur);
					
					if (cur == null)	// the last node
					{
						last = pre;
					}
					break;
				}
				else 
				{
					pre = cur;
					cur = cur.getNext();
					count ++;
				}
			}
		}
		/*
		if(index == 0)
		{
			ListNode front1 = first;
			first = front1.getNext();
		}
		else if(index == getSize() - 1)
		{
			ListNode front1 = first;
			
			for(int i = 0; i < index - 1; i++)
			{
				front1 = front1.getNext();
			}
			
			front1.setNext(null);
		}
		else if(index < getSize())
		{
			ListNode front1 = first;
			
			for(int i = 0; i < index - 1; i++)
			{
				front1 = front1.getNext();
			}
			
//			System.out.println("debug: " + front1.getValue() + ":" + front1.getNext().getNext().getValue());
			front1.setNext(front1.getNext().getNext());
		}
		*/
	}
	
	public void insert(int value, int index)
	{
		// RZ
		if (index == 0)
		{
			ListNode pre = new ListNode(value, first);
			
			if (first == null)
			{
				last = pre;
			}
			first = pre;
		}
		else 
		{
			ListNode cur = first.getNext();
			ListNode pre = first;
			int count = 1;
			
			while (cur != null)
			{
				if (count == index)
				{
					cur = new ListNode(value, cur.getNext());
					pre.setNext(cur);
					
					break;
				}
				else 
				{
					pre = cur;
					cur = cur.getNext();
					count ++;
				}
			}
		}
		/*
		if(index == 0)
		{
			ListNode front1 = first;
			ListNode add = new ListNode(value,front1);
			first = add; 
		}
		else if(index == getSize()) 
		{
			ListNode front1 = first;
			
			for(int i = 0; i < index - 1; i++)
			{
				front1 = front1.getNext();
			}
			ListNode add = new ListNode(value, null);
			front1.setNext(add);
			last = add;
			
		}
		else if(index <= getSize())
		{
			ListNode front1 = first;
			
			for(int i = 0; i < index - 1; i++)
			{
				front1 = front1.getNext();
			}
			
			ListNode add = new ListNode(value,front1.getNext());
			front1.setNext(add);
		}
		*/	
	}
	
	public void deleteValue(int value)
	{
		ListNode cur = first; 
		ListNode pre = null;
		
		while (cur != null)
		{
			if (cur.getValue() == (Object)value)
			{
				if (cur == first) 
				{
					cur = cur.getNext();
					first = cur;
				}
				else
				{
					cur = cur.getNext();
					pre.setNext(cur);
				
					if (cur == null)
						last = pre;
				}
			}
			else	
			{
				pre = cur;
				cur = cur.getNext();
			}
		}
	}	
		/*
		int listIndex = 0;
		int [] indexStorage = new int[count];
		int arrayIndex = 0;
		temp = first; 
		while (temp != null) 
		{
			if(temp.getValue() == (Object)value)
			{
				indexStorage[arrayIndex] = listIndex;
//				System.out.println("indexStorage[" + arrayIndex + "]" + " = " + listIndex);
				arrayIndex++;
			}
			listIndex++;
			temp = temp.getNext(); 
		}
		
//		System.out.println(indexStorage.length);
		
		for(int i = 0; i < indexStorage.length; i++)
		{
			deleteIndex(indexStorage[i]);
			for(int j = 0; j < indexStorage.length; j++)
			{
				if(i != j)
					if(indexStorage[j] > indexStorage[i])
						indexStorage[j]-=1;
			}
		}
		
		*/

	public void printList()
	{
		ListNode temp = first; // start at the first node
		while (temp != null) 
		{
			System.out.print(temp.getValue() + " ");
			temp = temp.getNext(); // go to next node
		}
	}
}
