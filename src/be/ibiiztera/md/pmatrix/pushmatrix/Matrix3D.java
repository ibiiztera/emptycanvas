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

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         8 nov. 2011
 * 
 */
public class Matrix3D {
	private Point3D axe1;
	private Point3D axe2;
	private Point3D origine;

	/**
	 * @param axe1
	 * @param axe2
	 * @param origine
	 */
	public Matrix3D(Point3D axe1, Point3D axe2, Point3D origine) {
		super();
		this.axe1 = axe1;
		this.axe2 = axe2;
		this.origine = origine;
	}

	private Point3D mult(Point3D p) {
		Point3D pr = new Point3D(p);
		return pr;

	}

	private Point3D multInv(Point3D p) {

		Point3D pr = new Point3D(p);
		return pr;
	}

	private Point3D plus(Point3D p) {
		Point3D pr = new Point3D(p);
		return pr;

	}

	private Point3D moins(Point3D p) {
		Point3D pr = new Point3D(p);
		return pr;

	}

	public Point3D changementAxe(Point3D p, double angle) {
		Point3D pr = new Point3D(p);
		return multInv(moins(rotation(plus(mult(pr)))));

	}

	private Point3D rotation(Point3D p) {
		Point3D pr = new Point3D(p);
		return pr;
	}
}
