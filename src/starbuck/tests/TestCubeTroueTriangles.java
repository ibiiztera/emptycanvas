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
package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.Camera;
import be.ibiiztera.md.pmatrix.pushmatrix.Cube;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Polygone;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.awt.Color;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestCubeTroueTriangles extends TestObjet {

    @Override
    public void testScene() {

        Cube c = new Cube(1, Point3D.X.mult(3));
        scene().add(c);
        c = new Cube(1, Point3D.X.mult(-3));
        scene().add(c);
        c = new Cube(1, Point3D.Y.mult(3));
        scene().add(c);
        c = new Cube(1, Point3D.Y.mult(-3));
        scene().add(c);
        c = new Cube(1, Point3D.Z.mult(3));
        scene().add(c);
        

        description = "5 cubes";
    }

    public static void main(String[] args) {
        TestCubeTroueTriangles testObjet = new TestCubeTroueTriangles();
        testObjet.camera(new Camera(new Point3D(0,0,-2), new Point3D(0,0,0), new Point3D(0,0,-1.7)));
        testObjet.run();
    }
}
