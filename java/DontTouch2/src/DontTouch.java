/**
 * DontTouch.java
 *
 * Provide a description of the program here.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 * @since 10/25/2019
 */

import java.awt.Color;
import java.awt.Font;

public class DontTouch
{
	/**
	 * RZ: debugging only 
	 */
	private double c_area = 0.0;
	private double r_area= 12.0 * 8.0;

	/**  The array for the Circles to be drawn.                         */
	private Circle[] circles;

	/**  Constructs the size (1000) for the array of Circles.           */
	public DontTouch ( )
	{
		circles = new Circle[1000];
	}
	
	public static void main(String [] args)
	{
		DontTouch run = new DontTouch();
		run.setUpCanvas();
		long start = System.currentTimeMillis();
		run.drawCircles();
		long end = System.currentTimeMillis();
		run.drawAxes();
		run.printArea();
		System.out.println("Total Run-time: " + (end - start) / 1000f + " seconds");
	}
	
	/** 
	 *  Sets up the canvas, using methods from StdDraw.  This includes
	 *  setting up the canvas size, the horizontal scale (Xscale), and
	 *  the vertical scale (Yscale).  
	 */
	public void setUpCanvas ( )
	{
		final int WIDTH = 1200;
		final int HEIGHT = 800;
		StdDraw.setCanvasSize(WIDTH, HEIGHT);
		StdDraw.setXscale(-6, 6);
		StdDraw.setYscale(-4, 4);
		StdDraw.clear(new Color(255,255,255));
		
		StdDraw.enableDoubleBuffering();
	}
	
	/**
	 *  Creates the Circles in the array of Circles.  Draws the Circles.
	 */
	public void drawCircles ( )
	{
		double thresh = 1.0;
		
		//  Your code here.
		for(int i = 0; i < 1000; i++) {
			StdDraw.setPenColor(new Color((int)(255 * Math.random()), (int)(255 * Math.random()), (int)(255 * Math.random())));
			
			boolean found = false;
			//find a valid starting position
			double x, y;
			x = Math.random() * 12 - 6;
			y = Math.random() * 8 - 4;
			//thresh tweaks
			// if(i > 900) thresh = 0.01;

			int count = 0;
			while(!found) {
				boolean bad = false;

				for(int j = i - 1; j >= 0; j--) {
					if(circles[j].dist(x, y) < circles[j].getRadius()) {
						bad = true;
						break;
					}
				}
				if(!bad) {
					double bound = Integer.MAX_VALUE;
					bound = Math.min(bound, Math.abs(x - (-6)));
					bound = Math.min(bound, Math.abs(x - (6)));
					bound = Math.min(bound, Math.abs(y - (-4)));
					bound = Math.min(bound, Math.abs(y - (4)));

					circles[i] = new Circle(x, y, Math.min(1, bound));

					for(int j = i - 1; j >= 0; j--) {
						double interval = circles[i].getOptimal(circles[j]);
						if(circles[i].getRadius() > interval)
							circles[i].setRadius(interval);
					}
					
					if(circles[i].getRadius() >= thresh) {	// RZ: cannot be the max radius
						StdDraw.filledCircle(x, y, circles[i].getRadius());
						//StdDraw.show();
						
						//c_area += circles[i].getArea();
						//System.out.println("Total circle is " + (i+1) + ", area coverage rate is " + c_area / r_area);
						
						found = true;
					}
				}

				if(!found) {
					x = Math.random() * 12 - 6;
					y = Math.random() * 8 - 4;
					count++;


					if(count == 300) {
						count = 0;
						if(thresh > 0.3) thresh -= 0.001;
						else if(thresh > 0.1) thresh -= 0.0001;
						else thresh -= 0.000009;
					} 
				}
			}

			
		}
		StdDraw.show();
		
	}
	
	
	
	
	
	//  You may need to write a helper method or two.
	
	
	
	
	
	
	/**
	 *  Draws a pair of axes, over the drawn Circles.  Grid lines are drawn and
	 *  the scale is shown, to help the viewer see the size of the Circles.
	 */
	public void drawAxes ( )
	{
		Font font = new Font("Arial", Font.PLAIN, 18);
		StdDraw.setFont(font);
		StdDraw.setPenColor(new Color(220,220,220)); 
		for(double integers = -6; integers <= 6; integers++)
		{
			StdDraw.line(integers,-4,integers,4);
			StdDraw.line(-6,integers,6,integers);
			StdDraw.setPenColor(new Color(0,0,0)); 	
			StdDraw.text(integers,-0.4,"" + (int)integers);
			StdDraw.text(-0.3,integers-0.05,"" + (int)integers);
		}
		StdDraw.show();
	}

	/**
	 *  Adds the area of each circle to a total area.  Prints this total 
	 *  area to the terminal window.
	 */
	public void printArea ( )
	{	
		double area = 0.0;
		for(int i = 0; i < 1000; i++) {
			area += circles[i].getArea();
		}
		//  Your code here.
		
		System.out.println("\n\n\nTotal Area: " + area + "\n\n\n");
	}
}
