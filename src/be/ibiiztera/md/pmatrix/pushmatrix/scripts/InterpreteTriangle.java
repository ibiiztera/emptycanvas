package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import java.awt.Color;
import java.util.ArrayList;

public class InterpreteTriangle implements Interprete{
	private int pos = 0;
	private static TRI tri;
	
	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		ArrayList <Object> objects=  new  ArrayList<Object>();
		ParsePoint pp = new ParsePoint();
		ParseColor pc = new ParseColor();
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
                pattern.add(ib.LEFTPARENTHESIS);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		objects.add(pp.interprete(text, pos));
		pos = pp.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		objects.add(pp.interprete(text, pos));
		pos = pp.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		objects.add(pp.interprete(text, pos));
		pos = pp.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.COMA);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		objects.add(pc.interprete(text, pos));
		pos = pc.getPosition();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		this.pos = pos;
		return new TRI((Point3D)objects.get(0), (Point3D)objects.get(1), (Point3D)objects.get(2), (Color)objects.get(3));
	}

	@Override
	public InterpreteConstants constant() {
		return null;
	}

	@Override
	public void setConstant(InterpreteConstants c) {
		
	}

	@Override
	public int getPosition() {
		return pos;
	}
	public static void main(String [] args)
	{
		String text = "((1, 0, 0), (1, 1, 0),(1, 1, 1), (255,0,0) ) ";
		InterpreteTriangle ifa = new InterpreteTriangle();
		try {
			setTri((TRI) ifa.interprete(text, 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void setTri(TRI tri) {
		InterpreteTriangle.tri = tri;
	}

	public static TRI getTri() {
		return tri;
	}
	

}
