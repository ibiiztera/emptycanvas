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

import be.ibiiztera.md.pmatrix.pushmatrix.generator.TRIGenerable;

public class Tetraedre implements Representable, TRIGenerable {
	private String id;
	private Point3D[] points;
	private TRIObject obj;
	private Color color;

	public Tetraedre(Point3D[] points) {
		super();
		this.points = points;
		obj = new TRIObject();
	}

	/**
	 * @param ps
	 * @param c
	 */
	public Tetraedre(Point3D[] ps, Color c) {
		super();
		this.points = ps;
		this.color = c;
		obj = new TRIObject();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Point3D[] getPoints() {
		return points;
	}

	public void setPoints(Point3D[] points) {
		this.points = points;
	}

	public TRIObject getObj() {
		return obj;
	}

	public void setObj(TRIObject obj) {
		this.obj = obj;
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

	@Override
	public TRIObject generate() {
		int i, j, k;
		obj = new TRIObject();
		i = 0;
		j = 1;
		k = 2;
		obj.add(new TRI(points[i], points[j], points[k], color));
		i = 0;
		j = 1;
		k = 3;
		obj.add(new TRI(points[i], points[j], points[k], color));
		i = 0;
		j = 2;
		k = 3;
		obj.add(new TRI(points[i], points[j], points[k], color));
		i = 1;
		j = 2;
		k = 3;
		obj.add(new TRI(points[i], points[j], points[k], color));
		return obj;
	}

	public String toString() {
		return "tetraedre(\n\n" + points[0] + " " + points[1] + " " + points[2] +" "+points[3]
				+ "\n\n)\n";
	}
   public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
