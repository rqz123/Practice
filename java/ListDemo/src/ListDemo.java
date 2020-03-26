public class ListDemo
{
	public static void main (String [] args)
	{
		ListNode list;
		list = new ListNode(13, null);
		System.out.println("\n\n\nThe node contains: " + (Integer)list.getValue());

		list.setValue(17);
		System.out.println("\n\nThe node contains: " + (Integer)list.getValue());

		SinglyLinkedList mylist;
		mylist = new SinglyLinkedList();
		for (int i = 0; i < 10; i++)
		{		
//			mylist.printList(); System.out.println();
			mylist.addFirst(i*i);
		}
		System.out.println("\n\n");
		mylist.printList();
		System.out.println("\n\n\n");
	}
}
