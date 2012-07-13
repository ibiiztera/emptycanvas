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

import java.awt.*;
public class Perspective
{
	public static final int P_CUBIQUE_ISOMETRIQUE=0;
	public static final int P_CUBIQUE_LINEAIRE=1;
	public static final int P_CUBIQUE_FONCTION=2;
	private int type = P_CUBIQUE_ISOMETRIQUE;
	
	private Perspective(int t)
	{
		this.type = t;
	}
	protected double f(double z)
	{
		return z;
	}
	public static final Perspective getInstance(int type)
	{
		return new Perspective(type);
	}
	java.awt.Point coordonneeEcran(Point3D p)
	{
		if(type==P_CUBIQUE_ISOMETRIQUE)
		{
			return new java.awt.Point((int)p.getX(), (int)p.getY());
		}
		else if(type==P_CUBIQUE_LINEAIRE)
		{
			if(p.getZ()==0)
				return new java.awt.Point(0, 0);
			else
				return new java.awt.Point((int)(p.getX()/p.getZ()),
                                        (int)(p.getY()/p.getZ()));
		}
		else if(type==P_CUBIQUE_FONCTION)
		{
			return new java.awt.Point(
                                (int)(p.getX()/f(p.getZ())),
                                (int)(p.getY()/f(p.getZ())));
		}
		return null;
	}
}