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
/**
 * 
 */
package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.util.ArrayList;
import java.util.Hashtable;

import be.ibiiztera.md.pmatrix.pushmatrix.BezierCubique;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import be.ibiiztera.md.pmatrix.pushmatrix.SegmentDroite;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         15 oct. 2011
 * 
 */
public class InterpreteFontes implements Interprete {
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
	private int pos;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#interprete(java
	 * .lang.String, int)
	 */
	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		Hashtable<Character, ArrayList<Representable>> fonte = new Hashtable<Character, ArrayList<Representable>>();
		
		ArrayList<Representable> objets;

		Character c;
		SegmentDroite s;
		BezierCubique bc;
		Point3D p;
		boolean eof1 = false;
		while (eof1) {

			eof1 = true;

			objets = new ArrayList<Representable>();

			InterpreteSegment is = new InterpreteSegment();
			InterpreteBezier ib = new InterpreteBezier();
			InterpretePoint3DBAK ip = new InterpretePoint3DBAK();

			InterpretesBase isb;
			ArrayList<Integer> pattern;
			isb = new InterpretesBase();
			pattern = new ArrayList<Integer>();
			pattern.add(isb.BLANK);
			pattern.add(isb.CARACTERE);
			pattern.add(isb.BLANK);
			pattern.add(isb.LEFTPARENTHESIS);
			pattern.add(isb.BLANK);
			isb.compile(pattern);
			
			ArrayList<Object> o = isb.read(text, pos);
			pos = isb.getPosition();
			
			if (o.size() != 5)
				break;
			c = (Character) o.get(1);

			
			if (eof1)
				try {
					s = (SegmentDroite) is.interprete(text, pos);
					pos = is.getPosition();
					objets.add(s);
					eof1 = false;
					continue;
				} catch (InterpreteException e) {
				}
			if (eof1)
				try {
					bc = (BezierCubique) ib.interprete(text, pos);
					pos = ib.getPosition();
					objets.add(bc);
					eof1 = false;
					continue;
				} catch (InterpreteException e) {

				}
			if (eof1)
				try {
					p = (Point3D) ip.interprete(text, pos);
					pos = ip.getPosition();
					objets.add(p);
					eof1 = false;
					continue;
				} catch (InterpreteException e) {

				}

			isb = new InterpretesBase();
			pattern = new ArrayList<Integer>();
			pattern.add(isb.BLANK);
			pattern.add(isb.RIGHTPARENTHESIS);
			pattern.add(isb.BLANK);
			isb.compile(pattern);
			c = (Character) isb.read(text, pos).get(1);
			pos = isb.getPosition();

			
			fonte.put(c, objets);
		}

		return fonte;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#getPosition()
	 */
	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return pos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#constant()
	 */
	@Override
	public InterpreteConstants constant() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#setConstant(be.
	 * ibiiztera.md.pmatrix.pushmatrix.scripts.InterpreteConstants)
	 */
	@Override
	public void setConstant(InterpreteConstants c) {
		// TODO Auto-generated method stub

	}

}
