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
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Polygone;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.awt.Color;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestCubeTroue extends TestObjet {

    @Override
    public void testScene() {

        Polygone p = new Polygone(Color.red);
        p.setPoints(new Point3D[]{new Point3D(-0.5, -0.5, -0.5), new Point3D(-0.5, -0.5, 0.5), new Point3D(0.5, -0.5, 0.5), new Point3D(0.5, -0.5, -0.5)});
        scene().add(p);
        p = new Polygone(Color.blue);
        p.setPoints(new Point3D[]{new Point3D(-0.5, -0.5, -0.5), new Point3D(-0.5, -0.5, 0.5), new Point3D(-0.5, 0.5, 0.5), new Point3D(-0.5, 0.5, -0.5)});
        scene().add(p);
        p = new Polygone(Color.green);
        p.setPoints(new Point3D[]{new Point3D(0.5, 0.5, -0.5), new Point3D(0.5, 0.5, 0.5), new Point3D(0.5, -0.5, 0.5), new Point3D(0.5, -0.5, -0.5)});
        scene().add(p);
        p = new Polygone(Color.magenta);
        p.setPoints(new Point3D[]{new Point3D(0.5, 0.5, -0.5), new Point3D(0.5, 0.5, 0.5), new Point3D(-0.5, 0.5, 0.5), new Point3D(-0.5, 0.5, -0.5)});
        scene().add(p);
        p = new Polygone(Color.black);
        p.setPoints(new Point3D[]{new Point3D(-0.5, -0.5, 0.5), new Point3D(0.5, -0.5, 0.5), new Point3D(0.5, 0.5, 0.5), new Point3D(-0.5, 0.5, 0.5)});
        scene().add(p);


        description = "Cube troué au centre en perspective";
    }

    public static void main(String[] args) {
        TestCubeTroue testObjet = new TestCubeTroue();
        testObjet.camera(new Camera(new Point3D(0,0,-10), new Point3D(0,0,0), new Point3D(0,0,-9)));
        testObjet.run();
    }
}
