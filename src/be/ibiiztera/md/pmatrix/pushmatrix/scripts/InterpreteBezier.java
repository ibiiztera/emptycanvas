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
package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.Color;
import java.awt.image.RenderedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class InterpreteBezier implements Interprete
{
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
	private int pos=0;
	private int numPoints=0;
	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		BezierCubique b = new  BezierCubique();
		
		
		InterpretesBase ib =  new InterpretesBase();
		ArrayList<Integer> pattern;
		pattern = new  ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		pos = ib.getPosition();
		
		/*InterpreteString is = new InterpreteString();
		String type = (String) is.interprete(text, pos);
		if(!type.equals("bspline"))
		{
			throw new InterpreteException();
		}
		pos = is.getPosition();		
		*/
		
		InterpreteCouleur pc = new InterpreteCouleur();
		Color c = (Color) pc.interprete(text, pos);
		b.setColor(c);
		pos = pc.getPosition();

		boolean ok = true;
		while(ok)
		{
			InterpretePoint3D ifa = new InterpretePoint3D();
			try
			{
			b.add((Point3D)ifa.interprete(text, pos));
			if(ifa.getPosition()>pos)
			{
				pos = ifa.getPosition();
				numPoints ++;
			}
			}
			catch(Exception ex)
			{
				ok = false;
			}
			
		}
		System.out.println(numPoints);
		pattern = new  ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		this.pos = ib.getPosition();
		return b;
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
	@SuppressWarnings("deprecation")
	public static void main(String  [] args)
	{
		File file = new File("C:\\Users\\administrateur\\Documents\\workspace\\PMMatrix\\SCRIPTS\\CUBE.TXT");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataInputStream ds = new DataInputStream(fis);
		String text = "";
		String t = "";
		try {
			while((text=ds.readLine()) != null) {
				t+=text;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InterpreteListeTriangle ilf = new InterpreteListeTriangle();
		TRIObject fo = null;
		try {
			fo = (TRIObject) ilf.interprete(t, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ZBuffer z = new ZBufferImpl(600,600);
		Scene s = new Scene();
		s.add(fo);
                z.scene(s);
		z.dessinerSilhouette3D();
		try {
			ImageIO.write((RenderedImage)z.image(), "jpeg", new File("c:\\Users\\administrateur\\Documents\\rendu.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
