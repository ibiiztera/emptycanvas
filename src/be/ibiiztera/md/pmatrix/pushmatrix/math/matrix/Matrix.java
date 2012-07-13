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

package be.ibiiztera.md.pmatrix.pushmatrix.math.matrix;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;

/**
 *
 * @author Manuel
 */
public class Matrix {

    public double[][] el;
    private int m;
    private int n;

    public Matrix(int m, int n) {
        this.m = m;

        this.n = n;
        this.el = new double[m][n];
    }

    public Matrix(Point3D vectX, Point3D vectY, Point3D vectZ) {
        this.m = this.n = 3;
    	this.el  =new  double[m][n];
    	el[0][0] = vectX.getX();
    	el[1][0] = vectX.getY();
    	el[2][0] = vectX.getZ();
    	el[0][1] = vectY.getX();
    	el[1][1] = vectY.getY();
    	el[2][1] = vectY.getZ();
    	el[0][2] = vectZ.getX();
    	el[1][2] = vectZ.getY();
    	el[2][2] = vectZ.getZ();
        }

    public Matrix tild() {
        Matrix m2 = new Matrix(m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    m2.el[i][j] = el[j][i];
                }
            }
        }
        return m2;
    }
    public Point3D mult(Point3D p)
    {
        Point3D p2 = new Point3D();
            for(int j=0; j<3; j++)
            {
                p2.setX(p.getX()*el[0][0]+p.getY()*el[0][1]+p.getZ()*el[0][2]);
                p2.setY(p.getX()*el[1][0]+p.getY()*el[1][1]+p.getZ()*el[1][2]);
                p2.setZ(p.getX()*el[2][0]+p.getY()*el[2][1]+p.getZ()*el[2][2]);
            }
        return p2;
    }
}
