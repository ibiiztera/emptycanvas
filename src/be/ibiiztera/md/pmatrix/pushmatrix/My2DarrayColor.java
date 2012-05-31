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

public class My2DarrayColor{
	private Color [] el;
	private int dimx;
	private int dimy;
	private Color bg;
	
	public My2DarrayColor(int dimx, int dimy, Color backgroundColor) {
		this.dimx = dimx;
		this.dimy = dimy;
		this.bg = backgroundColor;
		el = new Color[dimx*dimy];
		for(int i=0; i<dimx*dimy; i++)
		{
			el[i] = bg; 
		}
	}
	
	public void setElement(int x, int y, Color c)
	{
		el[x+dimx*y] = c;
	}
	public void setElementWC(int x, int y, Color c)
	{
		if(x>=0 && y>=0 && x<dimx && y<dimy)
			el[x+dimx*y] = c;
	}
	
	public Color getElement(int x, int y)
	{
		return el[x+dimx*y];
	}
	public Color getElementWC(int x, int y)
	{
		if(x>=0 && y>=0 && x<dimx && y<dimy)
			return el[x+dimx*y];
		return bg;
	}
}
