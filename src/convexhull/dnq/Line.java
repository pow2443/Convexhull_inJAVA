package convexhull.dnq;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Class: Line
* File: Line.java
* Description: This compares the position of dot with line and save the positions
* @author: Edgar Hyeongwoo Park
* Environment: MacBook, Mac OS El Capitan, jdk1.8, NetBeans 7.0
* Date: 25/04/2016
* @version 1.0
* @see javax.swing.JFrame
* History Log: 25/04/2016
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Line {
	private int x, y;
	private int dx, dy;

	public Line(Point p1, Point p2) {
		dy = p2.y - p1.y;
		dx = p2.x - p1.x;
		if (dx < 0) {
			dx *= -1;
			dy *= -1;
		}
		x = p1.x;
		y = p1.y;
	}

	/**
	 * This compares the position of dot with line 
	 * 
	 * @param p
	 * @return if point p is over the line, this is positive number<br>
	 *          if point p is under the line, this is negative number<br>
	 *         if point p and the line is overlap, this is 0.<br>
	 */
	int compare(Point p) {
		return dx * (p.y - y) - dy * (p.x - x);
	}
}