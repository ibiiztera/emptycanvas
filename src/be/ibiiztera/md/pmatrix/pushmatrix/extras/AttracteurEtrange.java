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

package be.ibiiztera.md.pmatrix.pushmatrix.extras;

import be.ibiiztera.md.pmatrix.pushmatrix.ID;
import be.ibiiztera.md.pmatrix.pushmatrix.POConteneur;
import be.ibiiztera.md.pmatrix.pushmatrix.PObjet;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;

/**
 * 
 * @author manuel
 */
@SuppressWarnings("serial")
public final class AttracteurEtrange implements Representable, POConteneur {

	private PObjet po;
	public static int NOMBRE_POINTS = 600000;
	private final double A;
	private final double B;
	private final double C;
	private final double D;
	private String id;

	public Point3D formule(Point3D precedent) {

		return new Point3D(Math.sin(A * precedent.getY()) - precedent.getZ()
				* Math.cos(B * precedent.getX()), precedent.getZ()
				* (Math.sin(C * precedent.getX()) - Math.cos(D
						* precedent.getY())), Math.sin(precedent.getX()));
	}

	public AttracteurEtrange(double A, double B, double C, double D) {
		this.A = A;
		this.B = B;
		this.C = C;
		this.D = D;

		po = new PObjet();
		Point3D actuel = new Point3D(1, 2, 3);
		Point3D precedent;
		int i = 0;
		while (i < NOMBRE_POINTS) {
			precedent = actuel;
			actuel = formule(precedent);
			po.add(actuel);
			i++;
		}
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = ID.GEN(this);
	}

	@Override
	public Iterable<Point3D> iterable() {
		return po.iterable();
	}

	@Override
	public String toString() {
		return "attracteurEtrange ( " + A + " " + B + " " + " " + C + " " + D
				+ ")\n";
	}
   public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
