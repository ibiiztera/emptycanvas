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
public class CollineModèle1 implements TRIGenerable, Representable {

    private TRIObject tris = new TRIObject();
    Random r = new Random();
    private String id;
    private double deltaInterne = 100;

    public CollineModèle1(int numTRIS) {

        Point3D p0 = new Point3D(0, 0, 0);
        Color c0 = new Color(128, 0, 255);

        for (int i = 0; i < numTRIS; i++) {
            Point3D[] p = new Point3D[3];

            p[0] = p0.plus(new Point3D(aleatoireSigne(deltaInterne),
                    aleatoireSigne(deltaInterne), aleatoireSigne(deltaInterne)));
            p[1] = p[0].plus(new Point3D(aleatoireSigne(deltaInterne),
                    aleatoireSigne(deltaInterne), aleatoireSigne(deltaInterne)));
            p[2] = p[1].plus(new Point3D(aleatoireSigne(deltaInterne),
                    aleatoireSigne(deltaInterne), aleatoireSigne(deltaInterne)));

            p0 = p[2];

            TRI t = new TRI(p[0], p[1], p[2], c0);

            tris.add(t);
        }
    }

    private double aleatoireSigne(double maxAmpl) {

        return (r.nextDouble() - 0.5)*maxAmpl;

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
