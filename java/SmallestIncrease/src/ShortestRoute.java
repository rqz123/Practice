/**
 * ShortestRoute.java
 *
 * The ShortestRoute.java file is an instance of a SinglyLinkedList
 * to model tbe Nearest Neighbor Huristic
 *
 * @author Joseph Zhang
 * @version 1.2
 * @since 3/22/2019
 */

import java.awt.Color;

public class ShortestRoute
{
	//Head Node
	private ListNode first;

	//Constructs the head node 
	public ShortestRoute()
	{
		first = null;
	}
	
	/**
	 * Finds and returns the size of the linked list.
	 * @return   size of the linked list. 
	 */
	public int size()
	{
		ListNode temp = first;
		int index = 0;
		
		while (temp != null)
		{
			temp = temp.getNext();
			index ++;
		}
		
		return index;
	}
	
	/**
	 * Finds and returns the length of the linked list (Based on points).
	 * @return   length of the linked list. 
	 */
	public double length()
	{
		if (first == null || first.getNext() == null)
			return 0.0;
		
		double length = 0.0;

		ListNode node1 = first;
		ListNode node2 = node1.getNext();
		
		do {
			Point p1 = (Point)node1.getValue();
			Point p2 = (Point)node2.getValue();
			length += calcDistance(p1, p2);

			node1 = node2;
			node2 = node2.getNext();

			if (node2 == null)	// the last node
			{
				p1 = (Point)first.getValue();
				length += calcDistance(p1, p2);
			}
		} while (node2 != null);
		
		return length;
	}

	/**
	 * Calculates the distance between two Nodes by getting the value. 
	 * @param p1   The first ListNode
	 * @param p2   The second ListNode
	 * @return     The distance between the two points. 
	 */
	public double calcDistance(Point p1, Point p2)
	{
		double x1 = p1.getX(); 
		double y1 = p1.getY();
		double x2 = p2.getX();
		double y2 = p2.getY();

		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	/**
	 * Calculates and relink the point to its nearest neighbor point. 
	 * Not the shortest Path!
	 * @param p   Point that's going to be linked
	 */
	public void insertPointAtNearestNeighbor(Point p)
	{		
		if (first == null)
			first = new ListNode(p, null);
		else if (first.getNext() == null)
			first.setNext(new ListNode(p, null));
		else
		{
			ListNode node = first;
			ListNode min = null;
			double nearest = Double.MAX_VALUE;
			
			do {
				Point c = (Point)node.getValue();
				double dist = calcDistance(p, c);
				
				if (dist < nearest)
				{
					nearest = dist;
					min = node;
				}
				
				node = node.getNext();
			} while (node != null);

			if (min != null)
				min.setNext(new ListNode(p, min.getNext())); 
		}
	}

	/**
	 * Tries all the possible combinations of linkedLists to
	 * determine which one is the shortest. 
	 * Still not the best possible algorithm! 
	 * @param p   Point that's going to be linked
	 */
	public void insertPointAtSmallestIncrease(Point p)
	{
		if (first == null)
			first = new ListNode(p, null);
		else if (first.getNext() == null)
			first.setNext(new ListNode(p, null));
		else
		{
			ListNode node = first;
			ListNode min = null;
			
			ListNode temp = new ListNode(p, null);
			double nearest = Double.MAX_VALUE;
			
			do {
				// Insert
				temp.setNext(node.getNext());
				node.setNext(temp);
				
				// Calculate length
				double len = length();
				if (len < nearest) 
				{
					nearest = len;
					min = node;
				}
				
				// Detech
				node.setNext(temp.getNext());
				temp.setNext(null);
				
				node = node.getNext();
			} while (node != null);
			
			if (min != null)
			{
				temp.setNext(min.getNext());
				min.setNext(temp); 
			}
		}
	}
	
	/**
	 * Draw the model by taking in two points, labeling them red 
	 * and drawing a blue line. 
	 */
	public void draw()
	{
		if (first == null || first.getNext() == null)
			return;

		ListNode node1 = first;
		ListNode node2 = first.getNext();

		Point p1, p2;;
		double x1, y1, x2, y2;
		
		do
		{
			p1 = (Point)node1.getValue();
			x1 = p1.getX();
			y1 = p1.getY();

			p2 = (Point)node2.getValue();
			x2 = p2.getX();
			y2 = p2.getY();

			StdDraw.setPenColor(new Color(0,0,255));
			StdDraw.line(x1,y1,x2,y2);
			StdDraw.setPenColor(new Color(255,0,0));
			StdDraw.filledCircle(x1,y1,4);
			
			node1 = node2;
			node2 = node2.getNext();
		} while (node2 != null);

		StdDraw.setPenColor(new Color(0,0,255));
		p1 = (Point)first.getValue();
		x1 = p1.getX();
		y1 = p1.getY();
		
		StdDraw.line(x2,y2,x1,y1);
		StdDraw.setPenColor(new Color(255,0,0));
		StdDraw.filledCircle(x2,y2,4);
	}
	
	/**
	 * Returns the string format of the point. 
	 * @return   string format of the point
	 */
    public String toString()
    {
		int count = 0;
		ListNode node = first;
		String result = new String("");
		
		while(node != null)
		{
			result += String.format("%4d: %s%n",count,(Point)node.getValue());
			node = node.getNext();
			count ++;
		}
		
		return result;
    }
}