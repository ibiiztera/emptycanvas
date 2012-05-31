/*

    Copyright (C) 2010-2012  DAHMEN, Manuel, Daniel

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

*/
package be.ibiiztera.md.pmatrix.pushmatrix;

public class Point {
	private double x;
	private double y;

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point() {
		super();
	}

	public Point(Point p1) {
		x = p1.getX();
		y = p1.getY();
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

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param p2a
	 * @param d
	 * @param e
	 * @return
	 */
	public boolean distanceEntre(Point p2a, double d, double e) {
		double distance = Math.sqrt((x-p2a.getX())*(x-p2a.getX())+(y-p2a.getY())*(y-p2a.getY()));
		return distance>d && distance<e;
	}
	public double distance(Point p2a) {
		double distance = Math.sqrt((x-p2a.getX())*(x-p2a.getX())+(y-p2a.getY())*(y-p2a.getY()));
		return distance;
	}

}
