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
	public int size ( )
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
	 * Finds and returns the length of the linked list (Based on points).
	 * @return   length of the linked list. 
	 */
	public double length ( )
	{
		if(size() == 0 || size() == 1)
			return 0;
		else
		{
			double length = 0;

			ListNode node1 = first;
			ListNode node2 = node1;
			while(node1 != null && node2 != null) 
			{
				Point p1 = (Point)node1.getValue();
				Point p2 = (Point)node2.getValue();
				length += calcDistance(p1, p2);

				node1 = node2;
				node2 = node2.getNext();

				if(node2 == null)
				{
					Point fp = (Point)first.getValue();
					length += calcDistance(fp, p2);
				}
			}
			
			return length;
		}
	}

	/**
	 * Calculates the distance between two Nodes by getting the value. 
	 * @param p1   The first ListNode
	 * @param p2   The second ListNode
	 * @return     The distance between the two points. 
	 */
	public double calcDistance (Point p1, Point p2)
	{
		double x1 = p1.getX(); 
		double y1 = p1.getY();
		double x2 = p2.getX();
		double y2 = p2.getY();

		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
	
	/**
	 * Calculates and relink the point to its nearest neighbor point. 
	 * Not the shortest Path!
	 * @param p   Point that's going to be linked
	 */
	public void insertPointAtNearestNeighbor(Point p)
	{		
		if(size() == 0)
			first = new ListNode(p, null);
		else if(size() == 1)
			first = new ListNode(p, first);
		else
		{
			ListNode min = null;
			ListNode node1 = first; 
			
			Point c = (Point)node1.getValue();
			double distance = calcDistance(p, c);

			min = node1;

			while(node1.getNext() != null)
			{
				node1 = node1.getNext();
				c = (Point)node1.getValue();
				if(calcDistance(p, c) < distance)
				{
					distance = calcDistance(p, c);
					min = node1;
				}
			}

			ListNode temp = min.getNext();
			min.setNext(new ListNode(p, temp)); 
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
		if(size() == 0)
			first = new ListNode(p, null);
		else if(size() == 1)
			first = new ListNode(p, first);
		else
		{
			ListNode min = null;
			ListNode node1 = first; 
			ListNode shortest = null;

			min = node1; //All case
			shortest = node1; //Best case

			//Set Default Temp Link
			ListNode temp = min.getNext();
			min.setNext(new ListNode(p, temp)); 

			//Get length
			double modelLength = length();
			double shortestLength = modelLength; 

			while(node1.getNext().getNext() != null)
			{
				//Detach link
				if(min.getNext() == null)
					min.setNext(null);
				else
					min.setNext(min.getNext().getNext());

				//Incrementation 
				node1 = node1.getNext();
				min = node1; 

				//Temp link
				temp = min.getNext();
				min.setNext(new ListNode(p, temp)); 

				//Comparsion
				shortestLength = length();
				if(shortestLength < modelLength)
				{
					modelLength = shortestLength;
					shortest = min;
				}
			}

			//Detach Link
			if(min.getNext() == null)
				min.setNext(null);
			else
				min.setNext(min.getNext().getNext());

			//Final Link
			temp = shortest.getNext();
			shortest.setNext(new ListNode(p, temp)); 
		}

	}
	
	/**
	 * Draw the model by taking in two points, labeling them red 
	 * and drawing a blue line. 
	 */
	public void draw ( )
	{
		if (first == null)
			return;

		ListNode node1 = first;
		ListNode node2 = node1;

		while(node1 != null && node2 != null) 
		{
			Point p1 = (Point)node1.getValue();
			double x1 = p1.getX();
			double y1 = p1.getY();

			Point p2 = (Point)node2.getValue();
			double x2 = p2.getX();
			double y2 = p2.getY();

			StdDraw.setPenColor(new Color(0,0,255));
			StdDraw.line(x1,y1,x2,y2);
			StdDraw.setPenColor(new Color(255,0,0));
			StdDraw.filledCircle(x1,y1,4);

			node1 = node2;
			node2 = node2.getNext();

			if(node2 == null)
			{
				StdDraw.setPenColor(new Color(0,0,255));
				Point fp = (Point)first.getValue();
				double x = fp.getX();
				double y = fp.getY();
				StdDraw.line(x2,y2,x,y);
				StdDraw.setPenColor(new Color(255,0,0));
				StdDraw.filledCircle(x2,y2,4);
			}
		}
			

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
			count++;
		}
		return result;
    }
}