/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.Camera;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import be.ibiiztera.pmatrix.extras.Colliseum;

/**
 *
 * @author Atelier
 */
public class TestCollisee extends TestObjet{
    @Override
    public void testScene() {
        setResx(640);
        setResy(480);
        scene().add(new Colliseum());
        
    }

    public static void main(String [] args)
    {
        TestCollisee to = new TestCollisee();
        to.camera(new Camera(new Point3D(0, 0, -10), Point3D.O0, 0.1));
        to.description("cylindre");
        to.run();
    }
}
