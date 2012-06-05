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
package be.ibiiztera.md.emptycanvas.base;


public class Axe {
	private Point3D p1;
	private Point3D p2;
	public Point3D getP1() {
		return p1;
	}
	public void setP1(Point3D p1) {
		this.p1 = p1;
	}
	public Point3D getP2() {
		return p2;
	}
	public void setP2(Point3D p2) {
		this.p2 = p2;
	}
	public Axe(Point3D p1, Point3D p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}
	public Point3D getVectAxe()
	{
		return p2.plus(p1.mult(-1));
	}
	public Point3D rotation(double angle, Point3D point)
	{
		return point;
		}
}
