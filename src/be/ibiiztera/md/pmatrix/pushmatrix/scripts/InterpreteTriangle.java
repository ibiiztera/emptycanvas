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
package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import java.awt.Color;
import java.util.ArrayList;

public class InterpreteTriangle implements Interprete{
    private String répertoire;
	    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
private int pos = 0;
	private static TRI tri;
	
	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		ArrayList <Object> objects=  new  ArrayList<Object>();
		InterpretePoint3D pp = new InterpretePoint3D();
		InterpreteCouleur pc = new InterpreteCouleur();
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
                pattern.add(ib.LEFTPARENTHESIS);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		objects.add(pp.interprete(text, pos));
		pos = pp.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		objects.add(pp.interprete(text, pos));
		pos = pp.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		objects.add(pp.interprete(text, pos));
		pos = pp.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		objects.add(pc.interprete(text, pos));
		pos = pc.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		this.pos = pos;
		return new TRI((Point3D)objects.get(0), (Point3D)objects.get(1), (Point3D)objects.get(2), (Color)objects.get(3));
	}

	@Override
	public InterpreteConstants constant() {
		return null;
	}

	@Override
	public void setConstant(InterpreteConstants c) {
		
	}

	@Override
	public int getPosition() {
		return pos;
	}
	public static void main(String [] args)
	{
		String text = "((1, 0, 0), (1, 1, 0),(1, 1, 1), (255,0,0) ) ";
		InterpreteTriangle ifa = new InterpreteTriangle();
		try {
			setTri((TRI) ifa.interprete(text, 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void setTri(TRI tri) {
		InterpreteTriangle.tri = tri;
	}

	public static TRI getTri() {
		return tri;
	}
	

}
