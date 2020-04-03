public class ListTester3
{
	public static void main (String [] args)
	{
		ListTester3 run = new ListTester3();
		run.methods();
	}
	
	public void methods ( )
	{
		printNodes();
		SinglyLinkedList mylist = new SinglyLinkedList();
		testAddFirst(mylist);
		testAddLast(mylist);
		testDelete1(mylist);
		testInsert(mylist);
		testDelete2(mylist);
		testSwap(mylist);
		testReverse(mylist);
		testSort(mylist);
	}
	
	public void printNodes ( )
	{
		ListNode list;
		list = new ListNode(13, null);
		System.out.println("\n\n\nThe node contains: " + (Integer)list.getValue());

		list.setValue(17);
		System.out.println("\nThe node contains: " + (Integer)list.getValue());
	}
	
	public void testAddFirst(SinglyLinkedList mylist)
	{
		System.out.println("\n\n");
		System.out.println(" TESTING THE METHOD addFirst:\n");
		for (int i = 0; i < 10; i++)
		{
			mylist.addFirst(i*i);
		}
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testAddLast(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD addLast:\n");
		for (int i = 1; i < 6; i++)
		{
			mylist.addLast(i);
		}
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testDelete1(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD delete (at an index):\n");
		System.out.println(" Delete at index 0:");
		mylist.deleteIndex(0);
		mylist.printList();
		System.out.println("\n Delete at index 7:");
		mylist.deleteIndex(7);
		mylist.printList();
		System.out.println("\n Delete at index 12:");
		mylist.deleteIndex(12);
		mylist.printList();
		System.out.println("\n Delete at index 11:");
		mylist.deleteIndex(11);
		mylist.printList();
		System.out.println("\n Delete at index 11:");
		mylist.deleteIndex(11);
		mylist.printList();
		System.out.println("\n Delete at index 20:");
		mylist.deleteIndex(20);
		mylist.printList();
		System.out.println("\n Delete at index 0:");
		mylist.deleteIndex(0);
		mylist.printList();
		System.out.println("\n Delete at index 9:");
		mylist.deleteIndex(9);
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testInsert(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD insert:\n");
		System.out.println(" Insert 37 at index 0:");
		mylist.insert(37,0);
		mylist.printList();
		System.out.println("\n Insert 43 at index 10:");
		mylist.insert(43,10);
		mylist.printList();
		System.out.println("\n Insert 51 at index 6:");
		mylist.insert(51,6);
		mylist.printList();
		System.out.println("\n Insert 51 at index 1:");
		mylist.insert(51,1);
		mylist.printList();
		System.out.println("\n Insert 51 at index 13:");
		mylist.insert(51,13);
		mylist.printList();
		System.out.println("\n Insert 51 at index 8:");
		mylist.insert(51,8);
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testDelete2(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD delete (a specific value):\n");
		System.out.println(" Delete the value 9:");
		mylist.deleteValue(9);
		mylist.printList();
		System.out.println("\n Delete the value 37:");
		mylist.deleteValue(37);
		mylist.printList();
		System.out.println("\n Delete the value 43:");
		mylist.deleteValue(43);
		mylist.printList();
		System.out.println("\n Delete the value 99:");
		mylist.deleteValue(99);
		mylist.printList();
		System.out.println("\n Delete the value 51:");
		mylist.deleteValue(51);
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testSwap(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD swap (at two given indices):\n");
		System.out.println(" Swap at indices 2 and 5:");
		mylist.swap(2,5);
		mylist.printList();
		System.out.println("\n Swap at indices 3 and 7:");
		mylist.swap(3,7);
		mylist.printList();
		System.out.println("\n Swap at indices 4 and 4:");
		mylist.swap(4,4);
		mylist.printList();
		System.out.println("\n Swap at indices 0 and 2:");
		mylist.swap(0,2);
		mylist.printList();
		System.out.println("\n Swap at indices 2 and 0:");
		mylist.swap(2,0);
		mylist.printList();
		System.out.println("\n Swap at indices 4 and 5:");
		mylist.swap(4,5);
		mylist.printList();
		System.out.println("\n Swap at indices 2 and 6:");
		mylist.swap(2,6);
		mylist.printList();
		System.out.println("\n Swap at indices 0 and 4:");
		mylist.swap(0,4);
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testReverse(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD reverse:\n");
		System.out.println(" Reverse:");
		mylist.reverse();
		mylist.printList();
		System.out.println("\n Reverse:");
		mylist.reverse();
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testSort(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD sort:\n");
		System.out.println(" Unsorted List:");
		mylist.printList();
		System.out.println("\n Sorted:");
		mylist.selectionSort();
		mylist.printList();
		for(int i = 0; i < 12; i++)
		{
			mylist.addLast((int)(1 + Math.random() * 100));
		}
		System.out.println("\n Unsorted List:");
		mylist.printList();
		System.out.println("\n Sorted:");
		mylist.selectionSort();
		mylist.printList();
		for(int i = 0; i < 20; i++)
		{
			mylist.deleteIndex(0);
		}
		for(int i = 0; i < 18; i++)
		{
			mylist.addLast((int)(1 + Math.random() * 100));
		}
		System.out.println("\n Unsorted List:");
		mylist.printList();
		System.out.println("\n Sorted:");
		mylist.selectionSort();
		mylist.printList();
		System.out.println("\n\n");
	}
}
