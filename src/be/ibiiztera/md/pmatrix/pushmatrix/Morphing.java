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

public class Morphing {
	private int noImage;
	private int nbrImages;
	private double ratioImage;
	private BezierCubiqueReseauSurface s1;
	private BezierCubiqueReseauSurface s2;
	
	public Morphing(BezierCubiqueReseauSurface s1,
			BezierCubiqueReseauSurface s2, int nbr)
	{
		this.s1 = s1;
		this.s2 = s2;
		this.nbrImages = nbr;
		noImage = 0;
	}
	
	public Point3D calculer(double s, double t, int i, int j)
	{
		return s1.calculer(s, t, i, j).mult(1-ratioImage)
		.plus(s2.calculer(s, t, i, j).mult(ratioImage));
	}
	public Color getColor(int i, int j)
	{
		return s1.getColor(i,j);
	}
	public boolean imageSuivante()
	{
		noImage ++;
		ratioImage = 1.0*noImage/nbrImages;
		return noImage<nbrImages;
	}
	
}
