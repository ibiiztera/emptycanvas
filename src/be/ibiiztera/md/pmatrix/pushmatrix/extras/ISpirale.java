package be.ibiiztera.md.pmatrix.pushmatrix.extras;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;

public interface ISpirale {
	public void addToScene(Scene sc);

	public void rotate();

	public void rotate(double deg);

	public Point3D getObjectDeviation(Point3D position);

	public Point3D getObjectRotation(Point3D position);

	public Point3D getObjectDeviation(Point3D position, Point3D speed,
			Point3D rotation);

	public Point3D getObjectRotation(Point3D position, Point3D speed,
			Point3D rotation);
}
