/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
