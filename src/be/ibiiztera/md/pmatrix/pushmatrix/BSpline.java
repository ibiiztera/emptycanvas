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
import java.util.ArrayList;
import java.util.Iterator;


public class BSpline implements Representable
{
	private String id;
	private ArrayList<Point3D> controls = new ArrayList<Point3D>();
	private int degree = 4;
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public void add(int arg0, Point3D arg1) {
		controls.add(arg0, arg1);
	}

	public boolean add(Point3D arg0) {
		return controls.add(arg0);
	}

	public Point3D get(int arg0) {
		return controls.get(arg0);
	}

	public Point3D remove(int arg0) {
		return controls.remove(arg0);
	}

	public Point3D set(int arg0, Point3D arg1) {
		return controls.set(arg0, arg1);
	}

	public int size() {
		return controls.size();
	}

	public Iterator<Point3D> iterator() {
		return controls.iterator();
	}

	public Point3D boor(double t, int i, int r)
	{
		int k =getDegree();
		
		if(r==0)
		{
			return get(i);
		}
		else
		{
			double [] T = new double[getDegree()+size()-1];
			for(int incr = 0; incr<T.length; incr++)
			{
				T[incr] = incr;
			}
			/*
			ArrayList<Integer> is = new ArrayList<Integer>();
			int ii = It-(getDegree()-1);
			while(ii >= It-r)
			{
				is.add(ii--);
			}
			Iterator<Integer> it = is.iterator();
			while(it.hasNext())
			{
				i = it.next();
			}
			*/
			return boor(t, i, r-1).mult((T[i+k]-t)/(T[i+k]-T[i+r])).plus(
					boor(t, i+1, r-1).mult((t-T[i+r])/(T[i+k]-T[i+r])));
		}
	}
	
	public Point3D calculerPoint3D(double t)
	{
            throw new UnsupportedOperationException("Not implemented yet");
            //return boor(t, (int)t-(getDegree()-1), getDegree()-1);
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
	
	public String toString()
	{
		String s = "bezier \n(\n\n";
		Iterator<Point3D> ps = iterator();
		while(ps.hasNext())
			s+="\n"+ps.next().toString()+"\n";
		return s;
	}

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
