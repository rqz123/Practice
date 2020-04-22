import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree
{
	private TreeNode root;
	
	/**
	 * Constructor.
	 */
	public BinarySearchTree ( )
	{
		root = null;
	}
	
	/**
	 * Clear and set an empty BST.
	 */
	public void clear ( )
	{
		root = null;
	}

	/**
	 * Adds value to the BST
	 * @param value: the object to be added 
	 * @return true if value has been added; otherwise returns false. 
	 */
	public boolean add(Object value)
	{
		if(contains(value))
		{
			return false;
		}
		root = add(root, value);
		return true;
	}
	
	/**
	 * Check if this BST contains the value.
	 * @param value
	 * @return true if this BST contains the value; otherwise returns false.
	 */
	public boolean contains(Object value)
	{
		return contains(root,value);
	}
	
	/**
	 * Check if a node including child node contains the value. 
	 * @param node: a specified node tree in the BST. 
	 * @param value: the object to be checked. 
	 * @return true if this BST contains specified value, otherwise returns false.
	 */
	private boolean contains(TreeNode node, Object value)
	{
		if (node == null)
			return false;
		
		// Using Object Comparable interface to compare two objects. 
		//int comp = ((Comparable<Object>)value).compareTo(node.getValue());
		// Or assuming the object is Integer object. 
		int comp = (Integer)value - (Integer)node.getValue();
		
		if (comp == 0)
			return true;
		else if (comp < 0)
			return contains(node.getLeft(), value);
		else
			return contains(node.getRight(), value);
	}
	
	/**
	 * Add the value to the specified node tree. 
	 * @param node: a specified node tree in the BST. 
	 * @param value: the object to be added. 
	 * @return the newly added node.
	 */
	private TreeNode add(TreeNode node, Object value)
	{
		if (node == null)
			node = new TreeNode(value);
		else 
		{
			// Check if the BST already contain the value.
			if (!contains(node, value))
			{	
				// Using Object Comparable interface to compare two objects. 
				//int comp = ((Comparable<Object>)value).compareTo(node.getValue());
				// Or assuming the object is Integer object. 
				int comp = (Integer)value - (Integer)node.getValue();
				
				if (comp < 0)
					node.setLeft(add(node.getLeft(), value));
				else
					node.setRight(add(node.getRight(), value));
			}
		}
		
		return node;
	}
	
	/**
	 * Find number of node in the BST. 
	 * @return the total number of the nodes in the BST. 
	 */
	public int countNodes ( )
	{
		return countNodes(root);
	}
	
	/**
	 * Find the number of node starting from the specified node tree. 
	 * @param node: the specified node tree. 
	 * @return the total number of the nodes. 
	 */
	public int countNodes(TreeNode node)
	{
		if (node == null)
			return 0;
		
		return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
	}
	
	/**
	 * Return the maximum level of the BST. 
	 * @return the maximum level of the BST. 
	 */
	public int levelCount ( ) 
	{
		return(maxDepth(root));
	}
	
	/**
	 * Return the maximum depth of tree.
	 * @param node: starting from the specified node tree. 
	 * @return the maximum depth of the tree. 
	 */
	private int maxDepth(TreeNode node) 
	{
		if (node == null)
			return 0;
		
		int left = getHeight(node.getLeft());
		int right = getHeight(node.getRight());
		
		return (left < right) ? right + 1 : left + 1;
	}
	
//  The 6 methods that follow are not necessary for BSTTester1
		
	public TreeNode find(Object value) 
	{
		return find(root, value);
	}
	
	public TreeNode find(TreeNode node, Object value)
	{
		return node;
	}
	
	private TreeNode smallestNode(TreeNode node)
	{
		return node;	
	}
	
	private TreeNode largestNode(TreeNode node)
	{
		return node;	
	}
	
	public void delete(Object value) 
	{
		root = delete(root, value);
	}

	private TreeNode delete(TreeNode node, Object value) 
	{
		return node;
    }
	
//  Most of the methods above are incomplete, and need to be completed.

//  The methods below are complete.  Do not change them.

	public String toString ( )
	{
		return print(root);
	}
	
	public String print(TreeNode node)
	{
		if (node != null) 
		{
			return print(node.getLeft()) + String.format("%4d",((Integer)node.getValue()).intValue()) + print(node.getRight());
		}
		return "";
	}

	public void printInFullForm ( )
	{
		int height = getHeight(root);
		int size = (int)Math.pow(2, height)-1;
		List<List<String>> result = new ArrayList<List<String>>();
		for(int i = 0; i < height; i++)
		{
			List<String> list = new ArrayList<String>();
			for(int j = 0; j < size; j++)
			{
				list.add("  ");
			}
			result.add(list);
		}
		printHelper(result, root, 0, 0, size - 1);
		System.out.println();
		for(List<String> line : result)
		{
			for(String numberOrSpaces : line)
			{
				System.out.print(numberOrSpaces);
			}
			System.out.println("\n");
		}
	}

	public void printHelper(List<List<String>> result, TreeNode node, int level, int left, int right)
	{
		if(node == null)
		{
			return;
		}
		int index = (left + right) / 2;
		result.get(level).set(index, (Integer)(node.getValue())+"");
		printHelper(result, node.getLeft(), level+1, left, index-1);
		printHelper(result, node.getRight(), level+1, index+1, right);
	}

	public int getHeight(TreeNode node)
	{
		if(node == null)
		{
			return 0;
		}
		return Math.max(1 + getHeight(node.getLeft()), 1 + getHeight(node.getRight()));
	}
}

