/*
	Notes on Deletion.  To delete a TreeNode node:
	(1)  If node has no children, then replace node with null.
	(2)  If node has one (and only one) child, then "promote"
		 that child to node's place.
	(3)  If node has two children, then identify its successor
		 as the minimum value in the right subtree.  Call this
		 node with the minimum value in the right subtree node2.
		 Put node2 to node's place.  Treat the loss of node2
		 in its original position as a deletion, and follow
		 rules (1) and (2) above, since the original position
		 of node2 can only be a leaf or have one child.
*/

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
	 * Get the root of BST
	 * @return the root of BST. 
	 */
	public TreeNode getRoot()
	{
		return root;
	}

	/**
	 * Set the root of BST
	 * @param node is the root of BST to be set.
	 */
	public void setRoot(TreeNode node)
	{
		root = node;
	}

	/**
	 * Clear and set an empty BST.
	 */
	public void clear ( )
	{
		root = null;
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
	 * Get the minimum number in the BST.
	 * @return the minimum number in the BST.
	 * Precondition: root cannot be null. 
	 */
	public Integer min()
	{
		return min(root);
	}

	/**
	 * Get the minimum number in the specified node tree. 
	 * @param node: the specified node tree. 
	 * @return the minimum number in the specified node tree. 
	 */
	private Integer min(TreeNode node) 
	{
		if (node.getLeft() == null)
			return (Integer)node.getValue();
		
		return min(node.getLeft());
	}

	/**
	 * Get the maximum number in the BST. 
	 * @return the maximum number in the BST. 
	 * Precondition: root cannot be null. 
	 */
	public Integer max()
	{
		return max(root);
	}

	/**
	 * Get the maximum number in the specified node tree. 
	 * @param node: the specified node tree. 
	 * @return the maximum number in the specified node tree. 
	 */
	private Integer max(TreeNode node) 
	{
		if (node.getRight() == null)
			return (Integer)node.getValue();
		
		return max(node.getRight());
	}

	/**
	 * Find the node with the specified value.
	 * @param value: the specified value.
	 * @return the node with the specified value.
	 */
	public TreeNode find(Object value) 
	{
		return find(root, value);
	}
	
	/**
	 * Find the node with the specified value.
	 * @param node: the specified node tree. 
	 * @param value: the specified value. 
	 * @return the node with the specified value. 
	 */
	private TreeNode find(TreeNode node, Object value)
	{
		if (node == null)
			return null;
		
		// Assuming the object is Integer object. 
		int comp = (Integer)value - (Integer)node.getValue();
		
		if (comp == 0)
			return node;
		else if (comp < 0)
			return find(node.getLeft(), value);
		else
			return find(node.getRight(), value);
	}
	
	/**
	 * Get rank of the BST with the specified value. 
	 * @param value: the specified value. 
	 * @return the zero-based rank. -1 means the value is null. 
	 */
	public int rank(Integer value)
	{
		if (value != null)
		{
			return rank(value, root);
		}
		return -1;
	}

	/**
	 * Get rank of a node tree with the specified value.
	 * @param value: the specified value.
	 * @param node: the specified node tree. 
	 * @return the zero-based rank. 
	 */
	private int rank(Integer value, TreeNode node)
	{
		if (node == null)
			return 0;
		
		int comp = (Integer)value - (Integer)node.getValue();
		
		if (comp == 0)
			return countNodes(node.getLeft());
		else if (comp > 0)
			return countNodes(node.getLeft()) + 1 + rank(value, node.getRight());
		else	// comp < 0
			return rank(value, node.getLeft());
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
		
		// Assuming the object is Integer object. 
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
				// Assuming the object is Integer object. 
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
	
	/**
	 * Delete the specified value from the BST.
	 * @param value: the specified value. 
	 */
	public void delete(Object value) 
	{
		root = delete(root, value);
	}

	/**
	 * Delete the value from the specified node tree. 
	 * @param node: the specified note tree.
	 * @param value: the value. 
	 * @return the new node.
	 */
	private TreeNode delete(TreeNode node, Object value) 
	{
		if (node == null)
			return null;
		
		// Assuming the object is Integer object. 
		int comp = (Integer)value - (Integer)node.getValue();

		if (comp > 0)
			node.setRight(delete(node.getRight(), value));
		else if (comp < 0)
			node.setLeft(delete(node.getLeft(), value));
		else
		{
			// Node with only one child or no child
			if (node.getLeft() == null)
				return node.getRight();
			else if (node.getRight() == null)
				return node.getLeft();
			
			// Node with two children
			
			// Get the inorder successor (smallest in the right subtree)
			TreeNode temp = node.getRight();
			Integer min_value = (Integer)temp.getValue();
			
			while (temp.getLeft() != null)
			{
				temp = temp.getLeft();
				min_value = (Integer)temp.getValue();
			}
			
			node.setValue(min_value);
			
			// Delete the inorder successor
			node.setRight(delete(node.getRight(), node.getValue()));
		}
		
		return node;
	}
	
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
		helper(result, root, 0, 0, size - 1);
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

	public void helper(List<List<String>> result, TreeNode node, int level, int left, int right)
	{
		if(node == null)
		{
			return;
		}
		int index = (left + right) / 2;
		result.get(level).set(index, (Integer)(node.getValue())+"");
		helper(result, node.getLeft(), level+1, left, index-1);
		helper(result, node.getRight(), level+1, index+1, right);
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
