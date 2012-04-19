/**
 * 
 */
package be.ibiiztera.md.pmatrix.test.pushmatrix;

import java.awt.Color;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBuffer;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBufferImpl;
import be.ibiiztera.md.pmatrix.pushmatrix.extras.SimpleSphere;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 18 oct. 2011
 *
 */
public class TestSimpleSphere {
	public static void main(String [] args)
	{
		ZBuffer z = new ZBufferImpl(1000, 1000);
		
		SimpleSphere ss0 = new SimpleSphere(new Point3D(0.5,0.5,0), 3, new Color(0, 255, 255));
		SimpleSphere ss1 = new SimpleSphere(new Point3D(0,0,0), 3, new Color(255, 0, 255));
		SimpleSphere ss2 = new SimpleSphere(new Point3D(1,0,0), 3, new Color(100, 0, 150));
		SimpleSphere ss3 = new SimpleSphere(new Point3D(0,1,0), 3, new Color(100, 0, 200));
		SimpleSphere ss4 = new SimpleSphere(new Point3D(1,1,0), 3, new Color(100, 0, 255));
		
		Scene sc = new Scene();
		sc.add(ss0);
		sc.add(ss1);
		sc.add(ss2);
		sc.add(ss3);
		sc.add(ss4);
                
                z.scene(sc);
		z.dessinerSilhouette3D();
		
		try {
			ImageIO.write((RenderedImage)z.image(), "png", new File(System.getProperty("user.home")+File.separator+"Mes Documents\\manu\\test Simple Sphere Multi 008.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
