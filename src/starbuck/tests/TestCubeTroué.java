package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Polygone;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.awt.Color;
import java.util.Arrays;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestCubeTroué extends TestObjet{
    @Override
    public void testScene()
    {
        
        Polygone p = new Polygone(Color.red);
        p.setPoints(new Point3D[] { new Point3D(-0.5,-0.5,-0.5),new Point3D(-0.5,-0.5,0.5), new Point3D(0.5,-0.5,0.5), new Point3D(0.5,-0.5,-0.5) });
        scene().add(p);
         p = new Polygone(Color.blue);
        p.setPoints(new Point3D[] { new Point3D(-0.5,-0.5,-0.5),new Point3D(-0.5,-0.5,0.5), new Point3D(-0.5,0.5, 0.5), new Point3D(-0.5,0.5,-0.5) });
        scene().add(p);
         p = new Polygone(Color.green);
        p.setPoints(new Point3D[] { new Point3D(0.5,0.5,-0.5),new Point3D(0.5,0.5,0.5), new Point3D(0.5,-0.5,0.5), new Point3D(0.5,-0.5,-0.5) });
        scene().add(p);
         p = new Polygone(Color.magenta);
        p.setPoints(new Point3D[] { new Point3D(0.5,0.5,-0.5),new Point3D(0.5,0.5,0.5), new Point3D(-0.5,0.5,0.5), new Point3D(-0.5,0.5,-0.5) });
        scene().add(p);
        
        
        description = "Cube troué au centre en perspective";
    }
    
    public static void main(String [] args)
    {
        TestCubeTroué testObjet = new TestCubeTroué();
        testObjet.setPerspective(-5, -2);
        testObjet.run();
    }

 
}
