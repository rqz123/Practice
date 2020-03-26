import java.awt.Color;

/**
 * Circle.java
 *
 * Provide a description of the class here.
 *
 * @author YOUR NAME HERE
 * @version 1.0
 * @since 10/25/2019
 */

public class Circle
{
	private double x, y;
	private double radius;
	
	Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getArea ( )
	{
		return radius * radius * Math.PI;	
	}
	
	public void drawSelf()
	{
		StdDraw.setPenColor(new Color((int)(255 * Math.random()), (int)(255 * Math.random()), (int)(255 * Math.random()))); 
		StdDraw.filledCircle(x, y, radius);
		//StdDraw.show();
	}

	public double getInterval(Circle c)
	{
		return getDistance(c) - c.radius;
	}
	
	public double getDistance(Circle c)
	{
		return Math.sqrt((c.y - y) * (c.y - y) + (c.x - x) * (c.x - x));
	}
	
	public double getDistance(double x, double y)
	{
		return Math.sqrt((y - this.y) * (y - this.y) + (x - this.x) * (x - this.x));
	}
}
