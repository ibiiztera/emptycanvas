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

package be.ibiiztera.md.pmatrix.pushmatrix;

import java.io.Serializable;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 17 nov. 2011
 *
 */
public class Matrix33 implements Serializable {

    private double[] d;
    public static final Matrix33 XYZ;
    public static final Matrix33 YZX;
    public static final Matrix33 ZXY;

    static {
        XYZ = new Matrix33(new double[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
        YZX = new Matrix33(new double[]{0, 1, 0, 0, 0, 1, 1, 0, 0});
        ZXY = new Matrix33(new double[]{0, 0, 1, 1, 0, 0, 0, 1, 0});
    }

    public Matrix33() {
        d = new double[9];
    }

    public Matrix33(double[] d) {
        this.d = d;
    }

    public double get(int i, int j) {
        return d[i * 3 + j];
    }

    public void set(int i, int j, double d0) {
        d[i * 3 + j] = d0;
    }

    public Matrix33(Point3D[] p) {
    }

    public Matrix33 uniteH() {
        return this;
    }

    public Matrix33 uniteV() {
        return this;
    }

    public Matrix33 tild() {
        Matrix33 m = new Matrix33();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m.set(i, j, get(j, i));
            }
        }
        return m;
    }

    public Matrix33 inverse() {
        return this;
    }

    public Matrix33 mult(Matrix33 mult) {
        Matrix33 r = new Matrix33();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                double d0 = 0;
                for (int k = 0; k < 3; k++) {
                    d0 += get(i, k) * get(k, j);
                }
                r.set(i, j, d0);
            }
        }
        return r;
    }

    public Point3D mult(Point3D p) {
        return rotation(p);
    }

    public Point3D rotation(Point3D p) {
        Point3D pa = new Point3D();
        for (int i = 0; i < 3; i++) {
            double d0 = 0;
            for (int j = 0; j < 3; j++) {
                d0 += this.get(i, j) * p.get(j);
            }
            pa.set(i, d0);
        }
        return pa;
    }

    public void set(int i, Point3D p) {
        for(int j = 0; j<3; j++)
            set(i, j, p.get(j));
    }

    public double[][] getDoubleArray() {
        double [][] d2 = new double[3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                d2[i][j] = get(i,j);
        return d2;
    }
}
