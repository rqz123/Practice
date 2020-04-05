/**
 * ShortestRouteM.java
 *
 * The ShortestRouteM.java file is an instance of a SinglyLinkedList
 * to model the Nearest Neighbor Huristic
 *
 * @author Joseph Zhang
 * @version 1.2
 * @since 4/4/2020
 */

import java.awt.Color;

public class ShortestRouteM
{
	//Head Node
	private ListNode first;

	//Constructs the head node 
	public ShortestRouteM()
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
			length += p1.getDist(p2);

			node1 = node2;
			node2 = node2.getNext();

			if (node2 == null)	// the last node
			{
				p1 = (Point)first.getValue();
				length += p1.getDist(p2);
			}
		} while (node2 != null);
		
		return length;
	}

	/**
	 * Calculates and relink the point to its nearest neighbor point. 
	 * Not the shortest Path!
	 * @param p   Point that's going to be linked
	 */
	public void insertPointAtNearestNeighbor(Point p)
	{
		if (first == null)
		{
			first = new ListNode(p, null);
		}
		/*
		else if (first.getNext() == null)
		{
			first.setNext(new ListNode(p, null));
		}
		*/
		else
		{
			ListNode node = first;
			ListNode min = first;
			
			double dist = 0.0;
			double nearest = Double.MAX_VALUE;
			
			do {
				dist = p.getDist((Point)node.getValue());
				
				if (dist < nearest)
				{
					nearest = dist;
					min = node;
				}
				
				node = node.getNext();
			} while (node != null);

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
		else
		{
			ListNode node = first;
			ListNode next = null;
			ListNode min = null;
			
			ListNode temp = new ListNode(p, null);
			double nearest = Double.MAX_VALUE;
			
			double len, len1, len2;
			Point p1, p2;
			
			do {
				next = node.getNext();
				p1 = (Point)node.getValue();
				
				if (next != null)
					p2 = (Point)next.getValue();
				else
					p2 = (Point)first.getValue();
					
				// The distance between current node and next node
				len = p1.getDist(p2);
				
				// The distance between current node and the new node
				len1 = p1.getDist(p);
				
				// The distance between the next node and the new node
				len2 = p2.getDist(p);
				
				len = len1 + len2 - len;
				
				if (len < nearest) 
				{
					nearest = len;
					min = node;
				}
				
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
