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
	public double x, y;
	private double radius;
	
	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	public double getArea ( )
	{
		// pi * r^2
		return (Math.atan(1) * 4) * radius * radius;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double dist(double cx, double cy) {
		return Math.sqrt((cx-x)*(cx-x)+(cy-y)*(cy-y));
	}
	public boolean intersects(Circle c) {
		return dist(c.x, c.y) < radius+c.radius;
	}
	public double getOptimal(Circle c) {
		return dist(c.x, c.y) - c.radius;
	}
}