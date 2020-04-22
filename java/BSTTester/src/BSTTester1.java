
public class BSTTester1
{
	public static void main (String [] args)
	{
		BSTTester1 run = new BSTTester1();
		run.methods();
	}
	
	public void methods ( )
	{
		printTreeNodes();
		BinarySearchTree myTree = new BinarySearchTree();
		testAddTreeNodes(myTree);
		testCountNodes(myTree);
		testCountLevels(myTree);
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
}