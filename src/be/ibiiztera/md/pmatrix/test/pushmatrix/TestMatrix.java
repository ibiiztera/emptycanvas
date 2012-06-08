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

package be.ibiiztera.md.pmatrix.test.pushmatrix;

import be.ibiiztera.md.pmatrix.pushmatrix.Matrix33;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;

/**
 *
 * @author Manuel DAHMEN
 * @date
 */
public class TestMatrix {
    public static void main(String [] args)
    {
        Matrix33 m ;
        m = new Matrix33(
                new double [] {
                    1,0,0,0,1,0,0,0,1
                }
                );
        Point3D p;
        p = Point3D.O0;
        System.out.println("I*O " +m.mult(p).toString());
        p = Point3D.X;
        System.out.println("I*X " +m.mult(p).toString());
        p = Point3D.Y;
        System.out.println("I*Y " +m.mult(p).toString());
        p = Point3D.Z;
        System.out.println("I*Z " +m.mult(p).toString());
    }
}
