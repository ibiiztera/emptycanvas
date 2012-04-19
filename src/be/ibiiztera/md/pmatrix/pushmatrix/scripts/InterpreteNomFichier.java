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
	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#interprete(java.lang.String, int)
	 */
	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		int pos1 = text.indexOf("\"", pos);
		int pos2 = text.indexOf("\"", pos1+1);
		
		this.pos = pos2+1;
		
		return new File(text.substring(pos1+1, pos2));
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
