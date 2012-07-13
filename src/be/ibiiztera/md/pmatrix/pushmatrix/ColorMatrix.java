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

public class ColorMatrix<E> implements Representable{
	private String id;
	private Point3D [] nodes;
	private My2DarrayColor colors;
	private E [] elements;
	private Point3D [] interpolate;
	private int dimx;
	private int dimy;
	public ColorMatrix(int dimx, int dimy) {
		super();
		this.dimx = dimx;
		this.dimy = dimy;
	}
	public Point3D[] getNodes() {
		return nodes;
	}
	public void setNodes(Point3D[] nodes) {
		this.nodes = nodes;
	}
	public My2DarrayColor getColors() {
		return colors;
	}
	public void setColors(My2DarrayColor colors) {
		this.colors = colors;
	}
	public E[] getElements() {
		return elements;
	}
	public void setElements(E[] elements) {
		this.elements = elements;
	}
	public Point3D[] getInterpolate() {
		return interpolate;
	}
	public void setInterpolate(Point3D[] interpolate) {
		this.interpolate = interpolate;
	}
	public int getDimx() {
		return dimx;
	}
	public void setDimx(int dimx) {
		this.dimx = dimx;
	}
	public int getDimy() {
		return dimy;
	}
	public void setDimy(int dimy) {
		this.dimy = dimy;
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
