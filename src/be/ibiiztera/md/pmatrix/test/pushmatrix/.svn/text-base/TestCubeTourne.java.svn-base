/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.test.pushmatrix;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Manuel
 */
public class TestCubeTourne
{
    
    public void testCubeTourne()
    {
        for(int i=0; i<360; i++)
        {
            try {
                ZBuffer b = new ZBufferImpl(500,500);
                Scene sc = new Scene();
                TRIObject t = new Cube().generate();
                t.rotationY(i);
                t.rotationX(i/10.0);
                sc.add(t);
                b.scene(sc);
                b.dessinerSilhouette3D();
                ImageIO.write(b.image(), "jpeg", new File("testCUBETOURNE"+i+".JPEG"));
                System.out.println(new File("testCUBETOURNE"+i+".JPEG").getAbsolutePath());           
            } catch (IOException ex) {
                Logger.getLogger(TestCubeTourne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void main(String [] args)
    {
        TestCubeTourne t = new TestCubeTourne();
        t.testCubeTourne();
    }
}
