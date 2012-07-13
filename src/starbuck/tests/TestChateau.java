package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.Camera;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Polygone;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.awt.Color;

/**
 *
 * @author Atelier
 */
public class TestChateau extends TestObjet
{
    @Override
    public void testScene()
    {
        
    Polygone p;
    // FACADE AVANT
    p =new Polygone(new Point3D[]{
        new Point3D(-15,0,-5 ),
        new Point3D(15,0,-5),
        new Point3D(15,10,-5),
        new Point3D(-15,10,-5)
    }, Color.red);
    scene().add(p);
    // FACADE ARRIERE
    p =new Polygone(new Point3D[]{
        new Point3D(-15,0,5 ),
        new Point3D(15,0,5),
        new Point3D(15,10,5),
        new Point3D(-15,10,5)
    }, Color.red);
    scene().add(p);
    // FACADE GAUCHE
    p =new Polygone(new Point3D[]{
        new Point3D(-15,0,-5 ),
        new Point3D(-15,0,5),
        new Point3D(-15,10,5),
        new Point3D(-15,10,-5)
    }, Color.red);
    scene().add(p);
    // FACADE DROITE
    p =new Polygone(new Point3D[]{
        new Point3D(15,0,-5 ),
        new Point3D(15,0,5),
        new Point3D(15,10,5),
        new Point3D(15,10,-5)
    }, Color.red);
    scene().add(p);
    }
    
    public static void main(String [] args)
    {
        TestChateau tc = new TestChateau();
        tc.setResx(640);
        tc.setResy(480);
        tc.loop(false);
        tc.publishResult(false);
        
        tc.camera(new Camera(new Point3D(0,0 , -50), Point3D.O0));
        tc.setFilename("00--chateau--vue de face");
        tc.description("Test Chateau -- vue face");
        tc.run();
        
        
        
        
        tc = new TestChateau();
        tc.setResx(640);
        tc.setResy(480);
        tc.loop(false);
        tc.publishResult(false);
        
        tc.camera(new Camera(new Point3D(100,100 ,-50), Point3D.O0));
        tc.setFilename("01--chateau--vue oblique");
        tc.description("Test Chateau -- vue oblique");
        tc.run();
  }
}
