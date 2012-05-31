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

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 15 oct. 2011
 *
 */
public class SegmentDroite implements Representable{
	private Point3D origine;
	private Point3D extremite;
        private Color c;
	public SegmentDroite(Point3D p1, Point3D p2)
	{
		this.setOrigine(p1);
		this.setExtremite(p2);
	}
	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.Representable#id()
	 */
	@Override
	public String id() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.Representable#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return the origine
	 */
	public Point3D getOrigine() {
		return origine;
	}
	/**
	 * @param origine the origine to set
	 */
	public void setOrigine(Point3D origine) {
		this.origine = origine;
	}
	/**
	 * @return the extremite
	 */
	public Point3D getExtremite() {
		return extremite;
	}
	/**
	 * @param extremite the extremite to set
	 */
	public void setExtremite(Point3D extremite) {
		this.extremite = extremite;
	}

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Droite (\n\t" + origine.toString()+"\n\t"+extremite.toString()+"\n\t( "+c.getRed()+" , "+c.getGreen()+" , "+c.getBlue()+" )\n)\n";
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Point3D calculerPoint3D(double d) {
        return origine.plus(extremite.moins(origine).mult(d));
    }

}
