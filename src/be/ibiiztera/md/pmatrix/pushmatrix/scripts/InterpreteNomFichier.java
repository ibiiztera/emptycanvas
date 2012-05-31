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

import java.io.File;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 27 oct. 2011
 *
 */
public class InterpreteNomFichier implements Interprete {
	private int pos;
    private String répertoire;
    @Override
    public void setRépertoire(String r)
    {
        this.répertoire = r;
    }
	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#interprete(java.lang.String, int)
	 */
	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		int pos1 = text.indexOf(File.separator, pos);
		int pos2 = text.indexOf(File.separator, pos1+1);
		
		this.pos = pos2+1;
		
		return new File(répertoire+File.separator+text.substring(pos1+1, pos2));
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#getPosition()
	 */
	@Override
	public int getPosition() {
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
	public static void main(String [] arfg)
	{
		InterpreteNomFichier inf = new InterpreteNomFichier();
		File f = null;
		try {
			f = (File) inf.interprete("\"c:\\Users\\mary\\im1.jpg\"", 0);
		} catch (InterpreteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(f.getName());
	}
}
