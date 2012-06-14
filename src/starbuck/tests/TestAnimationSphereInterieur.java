/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.Camera;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.pushmatrix.generator.TRISphere;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Atelier
 */
public class TestAnimationSphereInterieur extends TestObjet{

    private static Point3D coordSphere(double a, double b, double radius) {
         return new Point3D(0 + Math.cos(a) * Math.cos(b) * radius,
                0 + Math.cos(a) * Math.sin(b) * radius,
                0 + Math.sin(a) * radius);
 
    }
        @Override
    public void testScene()
    {
        try {
            TRISphere ts =  new TRISphere(new Point3D(0,0,0), 100);
            
            ts.map(ImageIO.read(getClass().getResource("Coucherdesoleil.jpg")), "Coucherdesoleil.jpg");
            scene().add(ts);
            
            description("Textured sphere seen from inside");
        } catch (IOException ex) {
            Logger.getLogger(TestAnimationSphereInterieur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String [] args)
    {
        double a = Math.PI;
        double b = 0;
        Point3D pos = coordSphere(a, b, 10);
        for(int i=0; i<2000; i++)
        {
            TestAnimationSphereInterieur to = new TestAnimationSphereInterieur();
            to.setResx(320);
            to.setResy(200);
            to.camera(new Camera(pos, new Point3D(0,0,0), pos.mult(0.9)));
            to.setFilename("image_"+(i+10000));
            to.publishResult(false);
            to.run();
            
            a += (Math.random()-0.5)*Math.PI/36;
            b += (Math.random()-0.5)*Math.PI/36/2;
            pos = coordSphere(a, b, 10);
            
            System.gc();
        }
    }


}
