/**
 * SinglyLinkedList.java
 *
 * A Linear data structure that consists of objects connected 
 * by references.
 * This linked list has head and tail reference access. 
 *
 * @author Joseph Zhang
 * @version 1.0
 * @since 3/23/20
 */

import java.util.NoSuchElementException;

public class SinglyLinkedList
{
	//Head Node
	private ListNode first;
	
	//Tail Node
	private ListNode last;

	//Delete one element: first = last = null;
	
	//Constructs the head and tail node
	public SinglyLinkedList()
	{
		first = null;
		last = null;
	}

	/**
	 *  Returns the first object of the linked list. 
	 */
	public Object getFirst()
	{
		if (first == null)
		{
			throw new NoSuchElementException();
		}
		else
			return first.getValue();
	}

	/**
	 *  Adds an object in front of the linked list.
	 *  
	 *  @param value - the object that want to be added.
	 */
	public void addFirst(Object value) 
	{
		if (first == null)
		{
			first = last = new ListNode(value, null);
		}
		else
			first = new ListNode(value, first);
	}
	
	/**
	 *  Returns the last object of the linked list. 
	 */
	public Object getLast()
	{
		if (last == null)
		{
			throw new NoSuchElementException();
		}
		else
			return last.getValue();
	}
	
	/**
	 *  Adds an object in the back of the linked list. 
	 *  
	 *  @param value - the object that want to be added. 
	 */
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
	
	/**
	 * Returns the size of the linked list. 
	 * 
	 */
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
	
	/**
	 * Delete an object at a specific index in the 
	 * linked list.
	 * 
	 * @param index  the index of the object
	 * 
	 */
	public void deleteIndex(int index)
	{
		if (first == null)
		{
			last = null;
			return;
		}
		
		if (index == 0)
		{
			first = first.getNext();
			if(first == null) //case if the last index is 0 
				last = null;  //and there is only one element. 
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
					
					if (cur == null)
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
	}
	
	/**
	 * Insert an object at a specific index in the 
	 * linked list.
	 * 
	 * @param value  the value of the object
	 * @param index  the index of the object
	 * 
	 */
	public void insert(int value, int index)
	{
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
					cur = new ListNode(value, pre.getNext());
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
			
			if (cur == null && count == index)
			{
				cur = new ListNode(value, null);
				pre.setNext(cur);
				last = cur;
			}
		}
	}
	
	/**
	 * Delete all instances of objects with the value 
	 * in the linked list.
	 * 
	 * @param value  the value of the object
	 * 
	 */
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
	
	/**
	 * Swap two objects with their respective indexes
	 * 
	 * @param index1  the index of the first object
	 * @param index2  the index of the second object
	 * 
	 */
	public void swap(int index1, int index2)
	{
		if (index1 == index2)
			return;
		
		ListNode node1 = first;
		ListNode pre1 = null;
		ListNode node2 = first;
		ListNode pre2 = null;
		
		while (node1 != null && index1 > 0)
		{
			pre1 = node1;
			node1 = node1.getNext();
			index1 --;
		}
		
		while (node2 != null && index2 > 0)
		{
			pre2 = node2;
			node2 = node2.getNext();
			index2 --;
		}
		
		if (node1 == null || node2 == null)
			return;

		if (pre1 != null)
			pre1.setNext(node2);
		else
			first = node2;
		
		if (pre2 != null)
			pre2.setNext(node1);
		else
			first = node1;

		ListNode temp = node1.getNext(); 
        node1.setNext(node2.getNext()); 
        node2.setNext(temp); 

        if (node1.getNext() == null)
        	last = node1;
        else if (node2.getNext() == null)
        	last = node2;
	}
	
	/**
	 * Reverse the linked list. 
	 */
	public void reverse()
	{
		ListNode cur = first;
		ListNode prev = null; 
		ListNode next = null;
		
		while (cur != null)
		{
			next = cur.getNext();
			cur.setNext(prev);
			prev = cur;
			cur = next;
		}
		
		first = prev;
	}
	
	/**
	 * Sort the linked list using selection sort. 
	 */
	public void selectionSort()
	{
		for (ListNode node1 = first; node1 != null; node1 = node1.getNext()) 
		{
			ListNode min = node1;
			
			for (ListNode node2 = node1.getNext(); node2 != null; node2 = node2.getNext())
			{
				if ((int)min.getValue() > (int)node2.getValue())
				{
					min = node2;
				}
			}
			
			Object value = node1.getValue();
			node1.setValue(min.getValue());
			min.setValue(value);
		}
	}
	
	/**
	  * Print out the linked list in a single line. 
	  */
	 public void printList()
	 {
		 for(int i = 0; i < getSize(); i++)
			 System.out.printf("%-4d", i);
	  
		 System.out.println();
	  
		 ListNode temp = first; // start at the first node
		 while (temp != null) 
		 {
			 System.out.printf("%-4d", temp.getValue());
			 temp = temp.getNext(); // go to next node
		 }
	  
		 System.out.println();
	  
		 System.out.println("Number of elements: " + getSize());
	 }
}
