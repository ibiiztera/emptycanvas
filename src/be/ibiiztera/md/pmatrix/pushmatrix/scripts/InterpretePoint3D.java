package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.util.ArrayList;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;

public class InterpretePoint3D implements Interprete {
	private InterpreteConstants c;
	private int pos;
	public Object interprete(String point, int pos) throws InterpreteException {
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		pattern.add(ib.DECIMAL);
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		pattern.add(ib.DECIMAL);
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		pattern.add(ib.DECIMAL);
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);

		ib.compile(pattern);
		ArrayList<Object> os = ib.read(point, pos);
		this.pos = ib.getPosition();
		
		return new Point3D((Double) os.get(3), (Double) os.get(7),
				(Double) os.get(11));
	}

	public static void main(String[] args) {
		ParsePoint pp = new ParsePoint();
		try {
			Point3D p = (Point3D) pp.interprete(" \t ( 0, 1.0, 2) \n", 0);
			System.out.println("(" + p.getX() + "," + p.getY() + "," + p.getZ()
					+ ")");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public InterpreteConstants constant() {
		return c;
	}

	@Override
	public void setConstant(InterpreteConstants c) {
		this.c = c;
	}

	@Override
	public int getPosition() {
		return pos;
	}


}
