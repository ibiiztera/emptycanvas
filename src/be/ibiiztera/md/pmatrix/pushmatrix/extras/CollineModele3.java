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

import be.ibiiztera.md.pmatrix.pushmatrix.ID;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIGenerable;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIObject;
import java.awt.Color;
import java.util.Random;
import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;

/**
 * 
 * @author Manuel
 */
public class CollineModele3 implements TRIGenerable, Representable {
	private TRIObject tris = new TRIObject();
	Random r = new Random();
	private String id;

	public CollineModele3(double altitudeMax) {
		int altMax = 10;
		int pMax = 100;

		Point3D p0 = new Point3D(0, 0, 0);
		Color c0 = new Color(128, 0, 255);

		Point3D[][] pA = new Point3D[altMax][pMax];

		Point3D[][] pAB = null;

		for (int alt = 1; alt < altMax; alt++) {
			for (int i = 0; i < pMax; i++) {
				Point3D[] p = new Point3D[3];

				p[0] = p0.plus(new Point3D(aleatoireSigne(alt),
						aleatoireSigne(alt), 0));
				p[1] = p[0].plus(new Point3D(aleatoireSigne(alt),
						aleatoireSigne(alt), 0));
				p[2] = p[1].plus(new Point3D(aleatoireSigne(alt),
						aleatoireSigne(alt), 0));

				p0 = p[2];

				pA[alt][i] = p0;

				TRI t = new TRI(p[0], p[1], p[2], c0);

				tris.add(t);

				if (alt > 1 & i > 0) {
					tris.add(new TRI(pA[alt - 1][i - 1], pA[alt][i - 1],
							pA[alt][i], c0));
					tris.add(new TRI(pA[alt][i - 1], pA[alt - 1][i - 1],
							pA[alt - 1][i], c0));
				}

			}

			c0 = new Color(128, 0, 255 - 10 * alt);

			// TRI t = new TRI(pA[alt][0].plus(new Point3D(0,-1,0)),
			// pA[alt][pMax/2].plus(new Point3D(0,-1,0)),
			// pA[alt][pMax-1].plus(new Point3D(0,-1,0)), c0);

			// tris.add(t);

			p0 = p0.plus(new Point3D(0, 0, -10));
		}
	}

	private double aleatoireSigne(double alt) {
		return (r.nextInt(1000) - 499.5) / 1000.0 * 100.0 / alt / alt;
	}

	@Override
	public TRIObject generate() {
		return tris;
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = ID.GEN(this);
	}

	@Override
	public String toString() {
		return "colline()\n";
	}
   public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
