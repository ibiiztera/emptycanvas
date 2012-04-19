package be.ibiiztera.md.pmatrix.pushmatrix.extras;

import java.util.ArrayList;
import java.util.Iterator;

import be.ibiiztera.md.pmatrix.pushmatrix.Axe;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIObject;
import be.ibiiztera.md.pmatrix.pushmatrix.Objet3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;

public class Spirale implements ISpirale {
	private ArrayList<TRI> triangles;
	private Axe axe;
	private double angle;
	private double radius;
	private TRIObject obj;

	public Spirale(Axe axe, double radius) {
		this.axe = axe;
		this.setRadius(radius);

		/*
		 * Tour tour = new Tour(axe.getP1(), axe.getP2(), new
		 * Tour.IColorFunction() {
		 * 
		 * @Override public Color getColor(double axeCoordinate, double theta) {
		 * return new Color(255, 0, 0); } }, new Tour.IPoint3DFunction() {
		 * 
		 * @Override public double getDiameter(double axeCoordinate, double
		 * theta) {
		 * 
		 * return 100; }
		 * 
		 * @Override public int getNbrPoints() { return 20; }
		 * 
		 * @Override public void setNbrPoints() {
		 * 
		 * 
		 * }
		 * 
		 * @Override public int getNbrRotations() { return 360; }
		 * 
		 * @Override public void setNbrRotation() { } });
		 * 
		 * FObjet o = tour.generate(); this.obj = o;
		 */
	}

	@Override
	public void addToScene(Scene sc) {
		Iterator<TRI> it = triangles.iterator();
		TRIObject o = new TRIObject();
		while (it.hasNext()) {
			TRI t = it.next();
			o.add(t);
		}
		sc.add(o);
	}

	@Override
	public void rotate() {
		Iterator<TRI> it = triangles.iterator();
		while (it.hasNext()) {
			TRI t = it.next();

			Point3D[] sommet = new Point3D[3];
			for (int i = 0; i < 3; i++) {
				 sommet[i] = t.getSommet()[i].rotatePoint(axe, angle);
			}
			t.setSommet(sommet);

		}

	}

	@Override
	public void rotate(double deg) {
		angle = deg;
		rotate();

	}

	@Override
	public Point3D getObjectDeviation(Point3D position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point3D getObjectRotation(Point3D position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point3D getObjectDeviation(Point3D position, Point3D speed,
			Point3D rotation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point3D getObjectRotation(Point3D position, Point3D speed,
			Point3D rotation) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setObj(TRIObject obj) {
		this.obj = obj;
	}

	public TRIObject getObj() {
		return obj;
	}

}
