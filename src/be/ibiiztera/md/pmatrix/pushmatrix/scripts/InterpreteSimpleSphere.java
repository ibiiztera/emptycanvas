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

import java.awt.Color;
import java.util.ArrayList;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.extras.SimpleSphere;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 21 oct. 2011
 *
 */
public class InterpreteSimpleSphere implements Interprete {
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
		Point3D c =null;
		double r=1;
		Color col = Color.black;
		
		InterpretesBase ib;
		InterpretePoint3DBAK ip;
		InterpreteCouleur pc;
		ArrayList<Integer> patt = null;
		
		ib = new InterpretesBase();
		patt = new ArrayList<Integer>();
		patt.add(ib.BLANK);
		patt.add(ib.LEFTPARENTHESIS);
		patt.add(ib.BLANK);
		ib.compile(patt);
		
		ib.read(text, pos);
		pos = ib.getPosition();
		
		ip = new InterpretePoint3DBAK();
		c = (Point3D) ip.interprete(text, pos);
		pos = ip.getPosition();
		
		ib = new InterpretesBase();
		patt = new ArrayList<Integer>();
		patt.add(ib.BLANK);
		patt.add(ib.DECIMAL);
		patt.add(ib.BLANK);
		ib.compile(patt);
		
		
		ib.read(text, pos);
		pos = ib.getPosition();
		r = (Double) ib.get().get(1);
		
		pc = new InterpreteCouleur();
		col = (Color) pc.interprete(text, pos);
		pos = pc.getPosition();
		
		ib = new InterpretesBase();
		patt = new ArrayList<Integer>();
		patt.add(ib.BLANK);
		patt.add(ib.RIGHTPARENTHESIS);
		patt.add(ib.BLANK);
		ib.compile(patt);
		
		ib.read(text, pos);
		pos = ib.getPosition();
		
		
		this.pos = pos;
		return new SimpleSphere(c, r, col);
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InterpreteSimpleSphere iss = new InterpreteSimpleSphere();
		try {
			SimpleSphere ss = (SimpleSphere) iss.interprete("((0.0, 0.0, 0.0) 10.0 (255, 0, 0 ))", 0);
		} catch (InterpreteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
