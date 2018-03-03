package convexhull.dnq;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Class: Point
* File: Point.java
* Description: input and return points
* @author: Edgar Hyeongwoo Park
* Environment: MacBook, Mac OS El Capitan, jdk1.8, NetBeans 7.0
* Date: 25/04/2016
* @version 1.0
* @see javax.swing.JFrame
* History Log: 25/04/2016
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Point extends java.awt.Point {
	private static final long serialVersionUID = 1L;
	private boolean isConvexHull=true;
	public final int x,y;
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
		// TODO Auto-generated constructor stub
	}
	public Point(java.awt.Point point)
	{
		this.x=point.x;
		this.y=point.y;
	}
	public boolean isConvexHull()
	{
		return isConvexHull;
	}
	
	public void setNotConvexHull()
	{
		isConvexHull=false;
	}
	public String toString()
	{
		return "x="+x+", y="+y+", isConvexHull="+isConvexHull;
	}
}
