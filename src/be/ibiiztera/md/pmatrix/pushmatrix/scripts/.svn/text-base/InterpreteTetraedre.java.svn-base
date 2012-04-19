package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.awt.Color;
import java.util.ArrayList;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Tetraedre;

public class InterpreteTetraedre implements Interprete {
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
			ParsePoint pp = new ParsePoint();
			ps[i] = (Point3D) pp.interprete(text, pos);
			pos = pp.getPosition();

			ib = new InterpretesBase();
			ib.compile(pattern);
			ib.read(text, pos);
			pos = ib.getPosition();
		}
		
		ParseColor pc = new ParseColor();
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
