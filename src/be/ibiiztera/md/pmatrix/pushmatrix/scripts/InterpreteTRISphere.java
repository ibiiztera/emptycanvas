package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.generator.TRISphere;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author DAHMEN Manuel
 *
 * dev
 *
 * @date 23-mars-2012
 */
public class InterpreteTRISphere implements Interprete {
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
	private int pos;
	public Object interprete(String text, int pos) throws InterpreteException {
		Point3D ps = null;

		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

			InterpretePoint3D pp = new InterpretePoint3D();
			ps = (Point3D) pp.interprete(text, pos);
			pos = pp.getPosition();

		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
			ib = new InterpretesBase();
			ib.compile(pattern);
			ib.read(text, pos);
			pos = ib.getPosition();

		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.DECIMAL);
		pattern.add(ib.BLANK);
		ib = new InterpretesBase();
		ib.compile(pattern);
		Double r = (Double)ib.read(text, pos).get(1);
		pos = ib.getPosition();


                InterpreteNomFichier pc = new InterpreteNomFichier();
		File f = (File) pc.interprete(text, pos);
		pos = pc.getPosition();


                pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib = new InterpretesBase();
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();

                this.pos = pos;

                TRISphere s = new TRISphere(ps, r);
        try {
            s.map(ImageIO.read(f), f.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(InterpreteTRISphere.class.getName()).log(Level.SEVERE, null, ex);
             throw new InterpreteException("TRISphere", ex);
        }
           return s;
        }
   public int getPosition() {
        return pos;
    }

    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
