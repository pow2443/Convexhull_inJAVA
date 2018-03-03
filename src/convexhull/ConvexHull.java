package convexhull;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Collection;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Class: ConvexHullGUI
* File: ConvexHull.java
* rate, and time of loan
* @author: Edgar Hyeongwoo Park
* Environment: PC, Windows 7, jdk1.8, NetBeans 7.0
* Date: 10/05/2015
* @version 1.0
* @see javax.swing.JFrame
* History Log: 10/06/2015
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public interface ConvexHull {
	public void addPoint(int x, int y);
	public void addPoint(java.awt.Point point);
	public void start();
	public Polygon getConvexHullPolygon();
	public Collection <? extends Point> getPoints();
	public void setGraphics(Graphics graphics);
	/**
	 * speed of showing process painting convex hull
	 * line/sec= 1000 milisec/speed
	 * @param speed
	 */
	public void setSpeed(int speed);
}
