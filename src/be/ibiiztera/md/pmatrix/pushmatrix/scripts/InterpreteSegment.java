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
import java.awt.Color;
import javax.swing.text.Segment;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.SegmentDroite;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 15 oct. 2011
 *
 */
public class InterpreteSegment implements Interprete {
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
	private int pos;
	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#interprete(java.lang.String, int)
	 */
	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		InterpretesBase isb;
		ArrayList<Integer> pattern;
		isb = new InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(isb.BLANK);
		pattern.add(isb.LEFTPARENTHESIS);
		pattern.add(isb.BLANK);
		isb.compile(pattern);
		isb.read(text, pos);
		pos = isb.getPosition();

		InterpretePoint3DBAK ip3 = new InterpretePoint3DBAK();
		Point3D p1 = (Point3D) ip3.interprete(text, pos);
		pos = ip3.getPosition();
		ip3 = new InterpretePoint3DBAK();
		Point3D p2 = (Point3D) ip3.interprete(text, pos);
		pos = ip3.getPosition();
		
		InterpreteCouleur ic = new InterpreteCouleur();
		Color c = (Color) ic.interprete(text, pos);
		pos = ic.getPosition();

		isb = new InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(isb.BLANK);
		pattern.add(isb.RIGHTPARENTHESIS);
		pattern.add(isb.BLANK);
		isb.compile(pattern);
		isb.read(text, pos);
		pos = isb.getPosition();

		this.pos = pos;
		
		SegmentDroite sd =  new SegmentDroite(p1, p2);
		sd.setC(c);

		return sd;
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#getPosition()
	 */
	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return pos;
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#constant()
	 */
	@Override
	public InterpreteConstants constant() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#setConstant(be.ibiiztera.md.pmatrix.pushmatrix.scripts.InterpreteConstants)
	 */
	@Override
	public void setConstant(InterpreteConstants c) {
		// TODO Auto-generated method stub

	}

}
