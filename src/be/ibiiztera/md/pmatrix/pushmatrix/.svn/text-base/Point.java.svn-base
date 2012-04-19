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
