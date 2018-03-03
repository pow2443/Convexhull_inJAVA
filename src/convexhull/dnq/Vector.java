package convexhull.dnq;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Class: Vector
* File: Vector.java
* Description: checking rotation of point 2
* @author: Edgar Hyeongwoo Park
* Environment: MacBook, Mac OS El Capitan, jdk1.8, NetBeans 7.0
* Date: 25/04/2016
* @version 1.0
* @see javax.swing.JFrame
* History Log: 25/04/2016
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Vector
{
	private Point p1,p0;
	public Vector(Point p0,Point p1)
	{
		this.p0=p0;
		this.p1=p1;
	}
	public int getX()
	{
		return p1.x-p0.x;
	}
	public int getY()
	{
		return p1.y-p0.y;
	}
	public void setPoint1(Point p1)
	{
		this.p1=p1;
	}
	/**
	 * 
	 * @param v1 p1's vector from standard point
	 * @param v2 p2's vector from standard point
	 * @return if p2 is clockwise rotation from p1, it is positive number<br>
	 * 	    if p2 is not clockwise rotation from p1, it is negative number
	 */
	public static int crossProduct(Vector v1, Vector v2)
	{
		
		return v1.getX()*v2.getY()-v2.getX()*v1.getY();
	}
}