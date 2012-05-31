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
