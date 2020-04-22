public class BSTTester2
{
	public static void main (String [] args)
	{
		BSTTester2 run = new BSTTester2();
		run.methods();
	}
	
	public void methods ( )
	{
		printTreeNodes();
		BinarySearchTree myTree = new BinarySearchTree();
		testAddTreeNodes(myTree);
		testCountNodes(myTree);
		testCountLevels(myTree);
		testFindAndRank(myTree);
		testMinAndMax(myTree);
		testDelete(myTree);
	}
	
	public void printTreeNodes ( )
	{
		TreeNode tNode;
		tNode = new TreeNode(new Integer(43));
		System.out.println("\n\n\nThe tree node contains: " + (Integer)tNode.getValue());

		tNode.setValue(new Integer(27));
		System.out.println("\nThe tree node contains: " + (Integer)tNode.getValue());
	}

	public void testAddTreeNodes(BinarySearchTree tree)
	{
		System.out.println("\n\n");
		System.out.println(" TESTING THE METHOD add:\n");
		tree.add(new Integer(63));
		tree.add(new Integer(37));
		tree.add(new Integer(75));
		tree.add(new Integer(68));
		tree.add(new Integer(56));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\n\n");
	}

	public void testCountNodes(BinarySearchTree tree)
	{
		System.out.println();
		System.out.println(" TESTING THE METHOD countNodes:\n");
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\nThe number of nodes in the tree above is: " + tree.countNodes());
		tree.add(new Integer(21));
		tree.add(new Integer(73));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\nThe number of nodes in the tree above is: " + tree.countNodes());
		System.out.println("\n\n");
	}
	
	public void testCountLevels(BinarySearchTree tree)
	{
		System.out.println();
		System.out.println(" TESTING THE METHOD countLevels:");
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\nThe number of levels in the tree above is: " + tree.levelCount());
		tree.add(new Integer(45));
		tree.add(new Integer(91));
		tree.add(new Integer(88));
		tree.add(new Integer(14));
		tree.add(new Integer(66));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\nThe number of levels in the tree above is: " + tree.levelCount());
		tree.add(new Integer(39));
		tree.add(new Integer(17));
		tree.add(new Integer(97));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\nThe number of levels in the tree above is: " + tree.levelCount());
		System.out.println("\nThe number of nodes in the tree above is: " + tree.countNodes());
		System.out.println("\n\n");
	}
	
	public void testFindAndRank(BinarySearchTree tree)
	{
		System.out.println(" TESTING THE METHODS find (a specific value) and rank:\n");
		System.out.println(tree);
		System.out.println(" Looking for the value 88:");
		if(tree.find(new Integer(88)) != null)
		{
			System.out.println(" YES, 88 is in the Binary Search Tree, at \"index\" " + tree.rank(new Integer(88)));
		}
		else
		{
			System.out.println(" NO, 88 is NOT in the Binary Search Tree");
		}
		System.out.println("\n" + tree);
		System.out.println(" Looking for the value 14:");
		if(tree.find(new Integer(14)) != null)
		{
			System.out.println(" YES, 14 is in the Binary Search Tree, at \"index\" " + tree.rank(new Integer(14)));
		}
		else
		{
			System.out.println(" NO, 14 is NOT in the Binary Search Tree");
		}
		System.out.println("\n" + tree);
		System.out.println(" Looking for the value 97:");
		if(tree.find(new Integer(97)) != null)
		{
			System.out.println(" YES, 97 is in the Binary Search Tree, at \"index\" " + tree.rank(new Integer(97)));
		}
		else
		{
			System.out.println(" NO, 97 is NOT in the Binary Search Tree");
		}
		System.out.println("\n" + tree);
		System.out.println(" Looking for the value 11:");
		if(tree.find(new Integer(11)) != null)
		{
			System.out.println(" YES, 11 is in the Binary Search Tree, at \"index\" " + tree.rank(new Integer(11)));
		}
		else
		{
			System.out.println(" NO, 11 is NOT in the Binary Search Tree");
		}
		System.out.println("\n" + tree);
		System.out.println(" Looking for the value 54:");
		if(tree.find(new Integer(54)) != null)
		{
			System.out.println(" YES, 54 is in the Binary Search Tree, at \"index\" " + tree.rank(new Integer(54)));
		}
		else
		{
			System.out.println(" NO, 54 is NOT in the Binary Search Tree");
		}
		System.out.println("\n");
		
	}
	
	public void testMinAndMax(BinarySearchTree tree)
	{
		System.out.println(" TESTING THE METHODS max and min:\n");
		System.out.println(tree);
		System.out.println("The minimum value in this tree is: " + tree.min());
		System.out.println("The maximum value in this tree is: " + tree.max());
		tree.add(new Integer(98));
		tree.add(new Integer(10));
		System.out.println("\n" + tree);
		System.out.println("The minimum value in this tree is: " + tree.min());
		System.out.println("The maximum value in this tree is: " + tree.max());
		System.out.println("\n");
	}
	
	public void testDelete(BinarySearchTree tree)
	{
		System.out.println(" TESTING THE METHOD delete (a specific value):");
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\n Delete the value 37:");
		tree.delete(new Integer(37));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\n\n Delete the value 45:");
		tree.delete(new Integer(45));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\n\n Delete the value 63:");
		tree.delete(new Integer(63));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\n\n Delete the value 21:");
		tree.delete(new Integer(21));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\n\n Delete the value 91:");
		tree.delete(new Integer(91));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\n\n Delete the value 66:");
		tree.delete(new Integer(66));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\n\n Delete the value 70:");
		tree.delete(new Integer(70));
		tree.printInFullForm();
		System.out.print("--------------------------------------------------\nOR   ");
		System.out.println(tree);
		System.out.println("\n\n");
	}
}
