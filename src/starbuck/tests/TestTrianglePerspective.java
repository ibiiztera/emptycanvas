package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.awt.Color;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestTrianglePerspective extends TestObjet{
    @Override
    public void testScene()
    {
        
        scene().add(new TRI(new Point3D(0,0,0),new Point3D(1,0,0), new Point3D(0,1,0), Color.green));
        scene().add(new TRI(new Point3D(1,1,0),new Point3D(1,0,0), new Point3D(0,1,0), Color.red));
        
        
        description = "Two triangles . One green down left . One red upper right";
    }
    
    public static void main(String [] args)
    {
        TestObjet testTriangle = new TestTrianglePerspective();
        testTriangle.setPerspective(true);
        testTriangle.run();
    }
}
