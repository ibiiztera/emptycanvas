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
public class TestSphèreInterieur extends TestObjet{
        @Override
    public void testScene()
    {
        try {
            setResx(320);
            setResy(200);
            TRISphere ts =  new TRISphere(new Point3D(0,0,0), 100);
            
            ts.map(ImageIO.read(getClass().getResource("Coucherdesoleil.jpg")), "Coucherdesoleil.jpg");
            scene().add(ts);
            scene().add(new TRI(new Point3D(1,1,0),new Point3D(1,0,0), new Point3D(0,1,0), Color.red));
            
            camera(new Camera(new Point3D(0,0,-10), new Point3D(0,0,0), new Point3D(0,0,-9)));
            description = "Textured sphere seen from inside";
        } catch (IOException ex) {
            Logger.getLogger(TestSphèreInterieur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String [] args)
    {
        TestSphèreInterieur to = new TestSphèreInterieur();
        to.run();
    }


}
