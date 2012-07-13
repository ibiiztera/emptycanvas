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

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Polygone;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestPolygones extends TestObjet {

    @Override
    public void testScene() {
        super.testScene();
        description = "octogone jaune";
        Polygone p = new Polygone(Color.yellow);
        ArrayList<Point3D> arrayList = new ArrayList<Point3D>();
        for (int i = 0; i < 8; i++) {
            arrayList.add(new Point3D(Math.cos(i / 8.0 * Math.PI * 2), Math.sin(i / 8.0 * Math.PI * 2), 0));
        }
        p.setPoints(arrayList);
        scene.add(p);
    }

    public static void main(String[] argd) {
        TestPolygones tp = new TestPolygones();
        tp.run();

    }
}
