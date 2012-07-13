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

import java.awt.Color;
import java.util.ArrayList;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Tetraedre;

public class InterpreteTetraedre implements Interprete {
    private String répertoire;
	    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
private int pos;

	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		Point3D[] ps = new Point3D[4];

		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		for (int i = 0; i < 4; i++) {
			InterpretePoint3D pp = new InterpretePoint3D();
			ps[i] = (Point3D) pp.interprete(text, pos);
			pos = pp.getPosition();

			ib = new InterpretesBase();
			ib.compile(pattern);
			ib.read(text, pos);
			pos = ib.getPosition();
		}
		
		InterpreteCouleur pc = new InterpreteCouleur();
		Color c = (Color) pc.interprete(text, pos);
		pos = pc.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib = new InterpretesBase();
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

                this.pos = pos;

		return new Tetraedre(ps, c);
	}

	@Override
	public int getPosition() {
		return pos;
	}

	@Override
	public InterpreteConstants constant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConstant(InterpreteConstants c) {
		// TODO Auto-generated method stub

	}

}
