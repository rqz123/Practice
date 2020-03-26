public class ListTester
{
	public static void main (String [] args)
	{
		ListTester run = new ListTester();
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
	}
	
	public void printNodes ( )
	{
		ListNode list;
		list = new ListNode(13, null);
		System.out.println("\n\n\nThe node contains: " + list.getValue());

		list.setValue(17);
		System.out.println("\nThe node contains: " + list.getValue());
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

}
