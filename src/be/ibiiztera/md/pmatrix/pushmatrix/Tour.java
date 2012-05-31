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

import java.awt.Color;

public class Tour implements Generator, Representable {
	private String id;

	public interface IPoint3DFunction {
		public double getDiameter(double axeCoordinate, double theta);

		public int getNbrPoints();

		public void setNbrPoints();

		public int getNbrRotations();

		public void setNbrRotation();
	}

	public interface IColorFunction {
		public Color getColor(double axeCoordinate, double theta);
	}

	private Point3D orig;
	private Point3D dest;
	private IColorFunction color;
	private IPoint3DFunction points;

	public Tour(Point3D orig, Point3D dest, IColorFunction color,
			IPoint3DFunction points) {
		super();
		this.orig = orig;
		this.dest = dest;
		this.color = color;
		this.points = points;
	}

	public Tour(Point3D orig2, Point3D dest2, Function function,
			ColorFunction colorFunction) {
	}

	public Point3D getOrig() {
		return orig;
	}

	public void setOrig(Point3D orig) {
		this.orig = orig;
	}

	public Point3D getDest() {
		return dest;
	}

	public void setDest(Point3D dest) {
		this.dest = dest;
	}

	public IColorFunction getColor() {
		return color;
	}

	public void setColor(IColorFunction color) {
		this.color = color;
	}

	public IPoint3DFunction getPoints() {
		return points;
	}

	public void setPoints(IPoint3DFunction points) {
		this.points = points;
	}

	private Point3D rotation(Axe axe, double angle, Point3D point) {
		return point.rotatePoint(axe, angle);
	}

	public TRIObject generate() {
		TRIObject tour = new TRIObject();

		Point3D[][] p = new Point3D[this.points.getNbrPoints()][points
				.getNbrRotations()];

		for (int axe = 0; axe < this.points.getNbrPoints(); axe++) {
			int a = 0;
			for (double angle = 0; angle < 360; angle += 1.0 / points
					.getNbrRotations()) {
				Point3D p0 = orig.plus(orig.mult(-1).plus(dest)
						.mult(1.0 * axe / points.getNbrPoints()));
				Point3D p1 = p0.plus(orig.mult(-1).plus(dest)
						.prodVect(new Point3D(0, 0, 1)).norme1()
						.mult(points.getDiameter(axe, angle)));
				p[axe][a] = rotation(new Axe(this.orig, this.dest), angle, p1);
				a++;
			}
		}

		for (int i = 0; i < p.length; i++)
			for (int j = 0; j < p[0].length; j++) {
				double axeCoordinate = 1.0 * i / points.getNbrPoints();
				double theta = 2 * Math.PI * j / points.getNbrRotations();
				tour.add(new TRI(p[i][j], p[i + 1][j], p[i][j + 1], color
						.getColor(axeCoordinate, theta)));
				tour.add(new TRI(p[i][j + 1], p[i + 1][j], p[i][j + 1], color
						.getColor(axeCoordinate, theta)));
			}

		return tour;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = ID.GEN(this);
	}

	@Override
	public String id() {
		return id;
	}
  public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
