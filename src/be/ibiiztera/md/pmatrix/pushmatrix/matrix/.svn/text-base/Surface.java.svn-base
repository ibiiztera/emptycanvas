package be.ibiiztera.md.pmatrix.pushmatrix.matrix;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;

public class Surface {
	private Point3D [] point;
	private Point3D value;
	{
		point = new Point3D[4];
		point[0] = new Point3D();
		point[1] = new Point3D();
		point[2] = new Point3D();
		point[3] = new Point3D();
	}
	public void setPoint(int i, Point3D value)
	{
		point[i] = value;
	}
	public Point3D getValue() {
		return value;
	}

	public void setValue(Point3D value) {
		this.value = value;
	}

	public Point3D get(double x, double y)
	{
		return point[0].plus(point[1].plus(point[0].mult(-1)).mult(x)).plus(
		point[0].plus(point[2].plus(point[0].mult(-1)).mult(y))
		);
	}
}
