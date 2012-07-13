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

public class TrajectoirePoint implements Trajectoire
{
	private Point3D [] points;
	private int ind = 0;
	private double indD = 0;
	private double [] duree;
	private double d =0.0;
	private int nbrFrames = 0;
	private int FPS = 25;
	private int f = 0;
	private double t = 0;
        private double tDebut;
        private double tFin;
	public TrajectoirePoint(Point3D[] point, double[] duree)
	{
		this.points = points;
		this.duree = duree;
		
		for(int i=0; i<duree.length; i++)
		{
			d += duree[i];
		}
		nbrFrames = (int) d*FPS;
		indD = duree[0];
		
		
	}
	public int frame()
	{
		return f;
	}
	public void frame(int f)
	{
		this.f = f;
	}
	public void tDebut(double t)
	{
		this.tDebut = tDebut;
		this.t = tDebut;
	}
	public double tDebut()
	{
		return tDebut;
	}
	public void tFin(double t)
	{
		this.tFin = tFin;
	}
	public double tFin()
	{
		return tDebut;
	}
	/***
	** DEPRECATED
	**/
	public double t()
	{
		return 0.0;
	}
	/***
	** DEPRECATED
	**/
	public void t(double t)
	{
	
	}
	
	public Point3D point()
	{
		double dureeEcoulee = t - tDebut;
		if(dureeEcoulee > indD)
		{
			ind++;
			indD+= duree[ind];
		}

		t += 1.0/FPS;
		indD += 1.0/FPS;
		
		return points[ind+1].mult(indD-t).plus(points[ind].mult(duree[ind]-(indD-t)));
	}
	
	public boolean asuivant()
	{
		if(ind >= points.length -1)
			return false;
			
		if(t>tFin)
			return false;
                return true;
	}

}