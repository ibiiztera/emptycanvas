/**
 * 
 */
package be.ibiiztera.md.pmatrix.test.pushmatrix;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBuffer;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBufferImpl;
import be.ibiiztera.md.pmatrix.pushmatrix.generator.Plan3D;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 18 oct. 2011
 *
 */
public class TestPlan3D {
	public static void main(String [] args)
	{
		ZBuffer z = new ZBufferImpl(1000, 1000);
		
                Plan3D p = new Plan3D();
		try {
                    p.map(ImageIO.read(new File("c:\\dev\\maps\\mary.jpg")), "c:\\dev\\maps\\mary.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene sc = new Scene();
		
                sc.add(p);
                
                z.scene(sc);
		z.dessinerSilhouette3D();
		
		try {
			ImageIO.write((RenderedImage)z.image(), "png", new File("c:\\dev\\results\\plan.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
