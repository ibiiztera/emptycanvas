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

import java.awt.Point;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 17 oct. 2011
 *
 */
public abstract class PGeneratorZ {
	public int hauteurImage(ZBuffer z)
	{
		return z.resY();
	}
	public int largeurImage(ZBuffer z)
	{
		return z.resX();
	}
	public Point point(ZBuffer z, Point3D p)
	{
		return z.coordonneesPoint2D(p);
	}
	
	public abstract void generate(ZBuffer z);

	public abstract void dessine(ZBuffer z);
}
