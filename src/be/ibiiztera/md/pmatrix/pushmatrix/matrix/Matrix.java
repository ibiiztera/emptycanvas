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

public class Matrix {
	private Surface [][] surfaces;
	private int dimX;
	private int dimY;
	
	public Matrix(int PdimX, int PdimY)
	{
		dimX = PdimX;
		dimY = PdimY;
		
		surfaces = new Surface[dimX][dimY];
		
		for(int x=0; x<dimX; x++)
			for(int y=0; y<dimY; y++)
			{
				surfaces[x][y] = new Surface();
				surfaces[x][y].setPoint(0, new Point3D(x,y,0));
				surfaces[x][y].setPoint(1, new Point3D(x+1,y,0));
				surfaces[x][y].setPoint(2, new Point3D(x,y+1,0));
				surfaces[x][y].setPoint(3, new Point3D(x+1,y+1,0));
			}
	}
	public int getDimX() {
		return dimX;
	}


	public int getDimY() {
		return dimY;
	}

	public void set(int x, int y, Point3D p)
	{
		surfaces[x][y].setValue(p);
	}
	public Point3D calculer(double x, double y)
	{
		return surfaces[(int)x][(int)y].get(x-(int)x, y-(int)y);
	}
}
