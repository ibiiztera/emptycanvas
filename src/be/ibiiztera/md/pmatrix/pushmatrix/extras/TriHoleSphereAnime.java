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

package be.ibiiztera.md.pmatrix.pushmatrix.extras;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import be.ibiiztera.md.pmatrix.pushmatrix.Animation;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIObject;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 27 déc. 2011
 *
 */
public class TriHoleSphereAnime extends Animation {
	TriHole th ;
	int n=0;
	/**
	 * @param s
	 */
	public TriHoleSphereAnime(Scene s) {
		super(s);
	}
	public static void main(String[] args) {
		Scene s = new Scene();
		ArrayList<Point3D> p = new ArrayList<Point3D>();
		p.add(new Point3D(0,0,0));
		
		TriHole th = new TriHole(p);
		s.add(th);
		TriHoleSphereAnime a = new TriHoleSphereAnime(s);
		a.repertoire("TRIHOLE_SPHERE");
		a.nom("THR002");

		try {
			while (a.suivant()) {
				try {
					a.modifier(th);
					a.ecrireImage();
					System.out.print(a.scene().toString());
					System.out.print("+");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private Point3D pcurrent = new Point3D(0,0,0);
	/**
	 * @param th2
	 */
	private void modifier(TriHole th2) {
		th2.deleteAll();
		double a = 0, b=0, R=10;
		n++;
		for(a = 0; a<Math.PI; a+=2.0*Math.PI/n)
			for(b=-Math.PI; b<Math.PI; b+=Math.PI/n)
		{
				pcurrent = new Point3D(R*Math.cos(a)*Math.cos(b),R* Math.cos(a)*Math.sin(b), R*Math.sin(a));
		
				th2.add(pcurrent);
	}
	}

}
