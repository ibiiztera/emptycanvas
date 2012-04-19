package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.awt.Color;
import java.util.ArrayList;

import be.ibiiztera.md.pmatrix.pushmatrix.BezierCubique2D;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;

public class InterpreteBezier2D implements Interprete{
	private int pos;
	@Override
	public Object interprete(String text, int pos) throws InterpreteException{
		
		
		
		Point3D [][] points = new Point3D[4][4];
		
		
		ArrayList<Integer> pattern = null;
		InterpretesBase ib = null;
		ib = new  InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		
		
		
		pos = ib.getPosition();
		
		
		ParseColor pc = new ParseColor();
		Color c = (Color) pc.interprete(text, pos);
		pos = pc.getPosition();
		
		ib = new  InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		
		pos = ib.getPosition();
		
		int i=0;
		while(i<4)
		{
			InterpreteIdentifier ii  = new InterpreteIdentifier();
			String ligne = (String) ii.interprete(text, pos);
			pos = ii.getPosition();
			if("ligne".equals(ligne))
			{
				
				ib = new  InterpretesBase();
				pattern = new ArrayList<Integer>();
				pattern.add(ib.BLANK);
				pattern.add(ib.LEFTPARENTHESIS);
				pattern.add(ib.BLANK);
				ib.compile(pattern);
				ib.read(text, pos);
				
				pos = ib.getPosition();
				
				for(int j = 0; j<4; j++)
				{
					InterpretePoint3D ip = new InterpretePoint3D();
					points[i][j] = (Point3D) ip.interprete(text, pos);
					points[i][j].setC(c);
					pos = ip.getPosition();
					
					
					ib = new  InterpretesBase();
					pattern = new ArrayList<Integer>();
					pattern.add(ib.BLANK);
					ib.compile(pattern);
					ib.read(text, pos);
					pos = ib.getPosition();
				}
				ib = new  InterpretesBase();
				pattern = new ArrayList<Integer>();
				pattern.add(ib.RIGHTPARENTHESIS);
				pattern.add(ib.BLANK);
				ib.compile(pattern);
				ib.read(text, pos);
				pos = ib.getPosition();
			}
			i++;
		}
		this.pos = pos;
		return new BezierCubique2D(points);
	}
	@Override
	public int getPosition() {
		return pos;
	}
	@Override
	public InterpreteConstants constant() {
		return null;
	}
	@Override
	public void setConstant(InterpreteConstants c) {
		
	}
}
