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

import be.ibiiztera.md.pmatrix.pushmatrix.Animation;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIObject;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         28 déc. 2011
 * 
 */
public class TrianguleSphereAnimation extends Animation {
	private TRIObject o;
	int n = 2;

	public TrianguleSphereAnimation(Scene s) {
		super(s);
	}

	public void modifier() {
		o = new TRIObject();
		double a = 0, b = 0, R = 10;
		n++;
		Point3D[][] pcurrent = null;
		if (n > 1) {
			pcurrent = new Point3D[n][n];

			int i = 0;
			for (b = 0; b < Math.PI; b += Math.PI / n) {
				int j = 0;
				for (a = -Math.PI; a < Math.PI; a += Math.PI / n) {
					if (i < n && j < n)
						pcurrent[i][j] = new Point3D(R * Math.cos(a)
								* Math.cos(b), R * Math.cos(a) * Math.sin(b), R
								* Math.sin(a));
					j++;

				}
				i++;
			}
		}

		for (int i1 = 0; i1 < n - 1; i1++)
			for (int j = 0; j < n - 1; j++) {
				o.add(new TRI(pcurrent[i1][j], pcurrent[i1][j + 1],
						pcurrent[i1 + 1][j + 1], Color.black));
				o.add(new TRI(pcurrent[i1 + 1][j], pcurrent[i1 + 1][j],
						pcurrent[i1 + 1][j + 1], Color.black));
			}
		Scene s = new Scene();
		s.add(o);
		scene(s);
	}

	public static void main(String[] args) {
		Scene s = new Scene();
		ArrayList<Point3D> p = new ArrayList<Point3D>();
		p.add(new Point3D(0, 0, 0));

		TrianguleSphereAnimation a = new TrianguleSphereAnimation(s);
		a.repertoire("TRIANGLES_SPHERE");
		a.nom("THR002");

		try {
			while (a.suivant()) {
				try {
					a.modifier();
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

}
