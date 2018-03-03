/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convexhull.dnq;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.*;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* Class: DivideAndConquerAlg
* File: DivideAndConquerAlg.java
* Description: This is another O(nlogn) time algorithm , 
* which is based on the divide and conquer design technique. 
* It can be viewed as a generalization of the MergeSort sorting algorithm. 
* It begins by sorting the points by their x coordinate, in O(nlogn) time. 
* The remainder of the algorithmis shown below.
* If |P|<=3 then compute the convex hull by brute force in O(1) time and return.
* Otherwise, partition the point set P into two sets L and R, 
* where L consists of half the points with the lowest x coordinates and R consists of half of the points 
* with the highest x coordinates.
* Recursively compute HL = CH(L) and HR = CH(R).
* Merge the two hulls into a common convex hull, H, by computing the upper and lower tangents for HL and 
* HR and discarding all the points lying between these two tangents.
* @author: Edgar Hyeongwoo Park
* Environment: PC, Windows 7, jdk1.8, NetBeans 7.0
* Date: 25/Apr/2016
* @version 1.0
* @see javax.swing.JFrame
* History Log:  25/Apr/2016
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class DivideAndConquerAlg implements convexhull.ConvexHull {

	private final List<Point> list = new ArrayList<Point>();
	private boolean isConvexHull = false;
	private List<Point> convexHull;
	private Graphics graphics;
	private static final Color removeColor = Color.WHITE;
	private long sleepTimer = 200;
	private void sleep() {
		try {
			Thread.sleep(sleepTimer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawBlackLine(Point p1, Point p2) {
		if (graphics == null)
			return;
		//graphics.setColor(Color.black);
		graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
		sleep();
	}

	@SuppressWarnings("unused")
	private void drawRedLine(Point p1, Point p2) {
		if (graphics == null)
			return;
		graphics.setColor(Color.red);
		graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
		sleep();
	}

	private void removeLine(Point p1, Point p2) {
		if (graphics == null)
			return;
		//graphics.setColor(removeColor);
		graphics.drawLine(p1.x, p1.y, p2.x, p2.y);
		sleep();
	}

	@SuppressWarnings("unused")
	private void drawPoint(Point p) {
		if (graphics == null)
			return;
		graphics.fillRect(p.x - 1, p.y - 1, 3, 3);
		sleep();
	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		list.add(new Point(x, y));
		isConvexHull = false;
	}

	@Override
	public void addPoint(java.awt.Point p) {
		// TODO Auto-generated method stub
		list.add(new Point(p));
		isConvexHull = false;
	}

	@Override
	public void setGraphics(Graphics graphics) {
		// TODO Auto-generated method stub
		this.graphics = graphics;
		graphics.setColor(Color.black);
		graphics.setXORMode(removeColor);
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		if (isConvexHull)
			return;

		Collections.sort(list, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				int result = o1.x - o2.x;
				if (result == 0)
					return o1.y - o2.y;
				return result;
			}
		});
		divide(0, list.size() - 1);
		isConvexHull = true;

		Line line = new Line(list.get(0), list.get(list.size() - 1));
		convexHull = new ArrayList<Point>();
		// List<Point> result = new ArrayList<Point>();
		convexHull.add(list.get(0));
		Stack<Point> stack = new Stack<Point>();
		Iterator<Point> ite = list.iterator();
		ite.next();
		while (ite.hasNext()) {
			Point p = ite.next();
			if (p.isConvexHull())
				if (line.compare(p) > 0)
					convexHull.add(p);
				else
					stack.push(p);
		}
		while (!stack.isEmpty()) {
			convexHull.add(stack.pop());
		}

	}

	@Override
	public Polygon getConvexHullPolygon() {
		// TODO Auto-generated method stub
		if (isConvexHull) {
			Polygon result = new Polygon();
			for (Point p : getConvecHull()) {
				result.addPoint(p.x, p.y);
			}
			return result;
		} else {
			return null;
		}
	}

	public Polygon getConvexHullPolygon(int s, int e) {
		// TODO Auto-generated method stub
		Polygon result = new Polygon();
		Line line = new Line(list.get(s), list.get(e));
		Stack<Point> stack = new Stack<Point>();
		result.addPoint(list.get(s).x, list.get(s).y);

		for (int i = s + 1; i < e; i++) {
			Point p = list.get(i);
			if (p.isConvexHull()) {
				if (line.compare(p) > 0) {
					result.addPoint(p.x, p.y);
				} else {
					stack.add(p);
				}
			}
		}
		result.addPoint(list.get(e).x, list.get(e).y);
		while (!stack.isEmpty()) {
			Point p = stack.pop();
			result.addPoint(p.x, p.y);
		}
		return result;
	}

	@Override
	public List<? extends Point> getPoints() {
		// TODO Auto-generated method stub
		return list;
	}

	private List<Point> getConvecHull() {

		if (isConvexHull)
			return convexHull;
		else
			return null;
	}

	private void divide(int s, int e) {

		// System.out.println(11111111);
		if (e - s < 3) {
			if (graphics != null) {
				graphics.drawPolygon(getConvexHullPolygon(s, e));
			}
			return;
		}
		int m = (s + e) / 2;
		divide(s, m);
		divide(m + 1, e);

		merge(s, m, e);
		// System.out.println("s=" + s + ", m=" + m + ", e=" + e);
		for (int i = s; i <= e; i++) {
			// System.out.println(list.get(i));
		}

	}

	private void merge(int s, int m, int e) {

		/* Rightside whole */
		int right_up = m;
		int right_dn = m;
		boolean leftFlag = true;
		Line leftLine = new Line(list.get(s), list.get(m));
		/* left side whole*/
		int left_up = m + 1;
		int left_dn = m + 1;
		boolean rightFlag = true;
		Line rightLine = new Line(list.get(m + 1), list.get(e));

		/* this is just for test program */
		// int testCounter = 0;
		/* checking while function*/
		boolean breakFlag = true;

		drawBlackLine(list.get(right_up), list.get(left_up));
		while (breakFlag) {
			// System.out.println("test " + testCounter++);
			int breakCheck = right_up;
			// System.out.println("left Hull : right_u=" + right_up +
			// ", left_u=" + left_up);
			// System.out.println("start point : " + list.get(right_up));
			for (int i = right_up - 1; i >= s; i--) {
				// System.out.println("\n right_u" + right_up + ",i=" + i + "  "
				// + list.get(i) + "\n"+ leftLine.compare(list.get(i)));

				if (list.get(i).isConvexHull() && (leftLine.compare(list.get(i)) >= 0)) {
					//if p2 is clockwise rotation from p0, p1 wll be deleted  
					drawBlackLine(list.get(i), list.get(left_up));
					if (crossProduct(list.get(right_up), list.get(i), list.get(left_up)) < 0) {
						removeLine(list.get(right_up), list.get(left_up));
						removeLine(list.get(right_up), list.get(i));
						if (right_up == m && leftFlag) {
							leftFlag = false;
							right_up = i;
							continue;
						}
						// System.out.println("die " + list.get(right_up));
						list.get(right_up).setNotConvexHull();
						right_up = i;
					} else {
						removeLine(list.get(i), list.get(left_up));
						break;
					}
				}
			}
			breakFlag = (breakCheck == right_up);
			// System.out.println("\n right_u=" + right_up + "\n" + (breakCheck
			// == right_up) + " , " + breakFlag);
			breakCheck = left_up;
			// System.out.println("right Hull : left=" + left_up + ", right_u="
			// + right_up);
			// System.out.println("start point : " + list.get(left_up));
			for (int i = left_up + 1; i <= e; i++) {
				// System.out.println("\n left" + left_up + ",i=" + i + "  " +
				// list.get(i) + "\n"+ rightLine.compare(list.get(i)));
				if (list.get(i).isConvexHull() && (rightLine.compare(list.get(i)) >= 0)) {
					drawBlackLine(list.get(i), list.get(right_up));

					if (crossProduct(list.get(left_up), list.get(i), list.get(right_up)) > 0) {
						//if p2 is not clockwise rotation from p0, p1 wll be deleted 
						removeLine(list.get(left_up), list.get(right_up));
						removeLine(list.get(left_up), list.get(i));
						if (left_up == (m + 1) && rightFlag) {
							rightFlag = false;
							left_up = i;
							continue;
						}
						// System.out.println("die " + list.get(left_up));
						list.get(left_up).setNotConvexHull();
						left_up = i;
					} else {
						removeLine(list.get(i), list.get(right_up));
						break;
					}
				}
			}
			// System.out.println("left=" + left_up + "\n" + (breakCheck ==
			// left_up) + " , " + breakFlag);
			breakFlag = !(breakFlag && (breakCheck == left_up));

		}

		drawBlackLine(list.get(right_dn), list.get(left_dn));
		// System.out.println("================under===============");
		breakFlag = true;
		while (breakFlag) {
			// System.out.println("test " + testCounter++);
			int breakCkeck = right_dn;
			// System.out.println("left Hull : right_d=" + right_dn +
			// ", left_u=" + left_dn);
			// System.out.println("start point : " + list.get(right_dn));
			for (int i = right_dn - 1; i >= s; i--) {
				// System.out.println("\n right" + right_dn + ",i=" + i + "  " +
				// list.get(i) + "\n"+ leftLine.compare(list.get(i)));
				if (list.get(i).isConvexHull() && (leftLine.compare(list.get(i)) <= 0)) {
					//if p2 is not clockwise rotation from p0, p1 wll be deleted 
					drawBlackLine(list.get(i), list.get(left_dn));
					if (crossProduct(list.get(right_dn), list.get(i), list.get(left_dn)) > 0) {
						removeLine(list.get(right_dn), list.get(left_dn));
						removeLine(list.get(right_dn), list.get(i));
						if (right_dn == m && leftFlag) {
							leftFlag = false;
							right_dn = i;
							continue;
						}
						// System.out.println("die " + list.get(right_dn));
						list.get(right_dn).setNotConvexHull();
						right_dn = i;
					} else {
						removeLine(list.get(i), list.get(left_dn));
						break;
					}
				}
			}
			breakFlag = (breakCkeck == right_dn);
			// System.out.println("\n right=" + right_dn + "\n" + (breakCkeck ==
			// right_dn) + " , " + breakFlag);
			breakCkeck = left_dn;
			// System.out.println("right Hull : left_d=" + left_dn +
			// ", right_d=" + right_dn);
			// System.out.println("start point : " + list.get(left_dn));
			for (int i = left_dn + 1; i <= e; i++) {
				// System.out.println("\n left" + left_dn + ",i=" + i + "  " +
				// list.get(i) + "\n"+ rightLine.compare(list.get(i)));
				if (list.get(i).isConvexHull() && (rightLine.compare(list.get(i)) <= 0)) {
					//if p2 is clockwise rotation from p0, p1 wll be deleted 
					drawBlackLine(list.get(i), list.get(right_dn));
					if (crossProduct(list.get(left_dn), list.get(i), list.get(right_dn)) < 0) {
						removeLine(list.get(left_dn), list.get(right_dn));
						removeLine(list.get(left_dn), list.get(i));
						if (left_dn == (m + 1) && rightFlag) {
							rightFlag = false;
							left_dn = i;
							continue;
						}
						// //System.out.println("die " + list.get(left_dn));
						list.get(left_dn).setNotConvexHull();
						left_dn = i;
					} else {
						removeLine(list.get(i), list.get(right_dn));
						break;
					}
				}
			}
			// //System.out.println("left=" + left_dn + "\n" + (breakCkeck ==
			// left_dn) + " , " + breakFlag);
			breakFlag = !(breakFlag && (breakCkeck == left_dn));

		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * 
	 * @param p1
	 *            first dot
	 * @param p2
	 *            second dot
	 * @param p0
	 *            standard dot 
	 * @return Direction of p2 about p1 from p0 <br>
	 *         if it is bigger than 0, this is clockwise rotation   <br>
	 *        if it is smaller than 0, this is counter clock wise <br>
	 *         if it is equal with 0, it is on same line.
	 */
	private static int crossProduct(Point p1, Point p2, Point p0) {

		return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
                
	}

	@Override
	public void setSpeed(int speed) {
		// TODO Auto-generated method stub
		sleepTimer=1000/speed;
		
	}

}



