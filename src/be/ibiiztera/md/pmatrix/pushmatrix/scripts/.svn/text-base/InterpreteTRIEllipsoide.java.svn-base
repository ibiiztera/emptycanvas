package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.generator.TRIEllipsoide;
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
public class InterpreteTRIEllipsoide implements Interprete {
	private int pos;
	public Object interprete(String text, int pos) throws InterpreteException {
 		Point3D[] ps = new Point3D[2];

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
		for (int i = 0; i < 2; i++) {
			ParsePoint pp = new ParsePoint();
			ps[i] = (Point3D) pp.interprete(text, pos);
			pos = pp.getPosition();

			ib = new InterpretesBase();
			ib.compile(pattern);
			ib.read(text, pos);
			pos = ib.getPosition();
		}

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

                TRIEllipsoide e = new TRIEllipsoide(ps[1], ps[0]);

        try {
            e.map(ImageIO.read(f), f.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(InterpreteTRIEllipsoide.class.getName()).log(Level.SEVERE, null, ex);
            throw new InterpreteException("TRIEllipsoide", ex);
        }
           return e;
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
