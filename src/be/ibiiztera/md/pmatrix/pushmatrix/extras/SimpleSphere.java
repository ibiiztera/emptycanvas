
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

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.Color;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 17 oct. 2011
 *
 */
public class SimpleSphere implements Representable, TRIGenerable {

    protected PObjet po;
    protected double radius;
    protected Point3D centre;
    protected Color color;
    private String id;
    private double incrLat;
    private double incrLong;
    protected int numLatQuad = 20;
    protected int numLongQuad = 20;

    public SimpleSphere(Point3D c, double r, Color col) {
        this.radius = r;
        this.centre = c;
        this.color = col;
    }

    /*
     * (non-Javadoc)
     *
     * @see be.ibiiztera.md.pmatrix.pushmatrix.Representable#id()
     */
    @Override
    public String id() {
        return id;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * be.ibiiztera.md.pmatrix.pushmatrix.Representable#setId(java.lang.String)
     */
    @Override
    public void setId(String id) {
        this.id = ID.GEN(this);
    }

    @Override
    public String toString() {
        return "\nSimpleSphere(\n\t" + centre.toString() + "\n\t" + radius + " \n\t" + "(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")\n)\n";
    }

    /*
     * (non-Javadoc)
     *
     * @see be.ibiiztera.md.pmatrix.pushmatrix.PGenerator#generatePO()
     */
    public Point3D CoordPoint(double a, double b) {
        return new Point3D(centre.getX() + Math.cos(a) * Math.cos(b) * radius,
                centre.getY() + Math.cos(a) * Math.sin(b) * radius,
                centre.getZ() + Math.sin(a) * radius);
    }
    Color map[][];
    double[][] zmap;
    Point3D[][] pmap;
    public static float DMIN = 0.5f;
    public static float DMAX = 1.5f;

    @Override
    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TRIObject generate() {
        TRIObject t = new TRIObject();
        po = new PObjet();

        double a = -Math.PI / 2;
        double b;
        Point3D[] pCur = new Point3D[4];

        incrLat = 2 * Math.PI / numLatQuad;
        while (a < Math.PI / 2) {
            incrLong = 2 * Math.PI * Math.cos(a) / numLongQuad;
            b = 0;
            while (b < 2 * Math.PI && incrLong > 0.0001) {
                //System.out.println("a;b " + a +";"+b);
                pCur[0] = CoordPoint(a, b);
                pCur[1] = CoordPoint(a + incrLat, b);
                pCur[2] = CoordPoint(a, b + incrLong);
                pCur[3] = CoordPoint(a + incrLat, b + incrLong);
                t.add(new TRI(pCur[0], pCur[1], pCur[3], color));
                t.add(new TRI(pCur[0], pCur[2], pCur[3], color));

                b += incrLong;
            }
            a += incrLat;
        }
        return t;
    }

    public int getNumLatQuad() {
        return numLatQuad;
    }

    public int getNumLongQuad() {
        return numLongQuad;
    }

    public void setNumLatQuad(int numLatQuad) {
        this.numLatQuad = numLatQuad;
    }

    public void setNumLongQuad(int numLongQuad) {
        this.numLongQuad = numLongQuad;
    }
}
