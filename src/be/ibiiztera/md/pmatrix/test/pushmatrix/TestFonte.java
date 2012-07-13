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
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 17 oct. 2011
 *
 */
public class TestFonte extends Test {

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.test.pushmatrix.Test#testAnime()
	 */
	@Override
	public void testAnime() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.test.pushmatrix.Test#testSerie()
	 */
	@Override
	public void testSerie() {
		// TODO Auto-generated method stub
		ZBuffer z = new ZBufferImpl(1000,1000);
		Scene s = new Scene();
		
		new Loader().loadTEST("fonte.txt", s);
		z.scene(s);
		z.dessinerSilhouette3D();
		
		try {
			ImageIO.write((RenderedImage)z.image(), "png", new File(name()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.test.pushmatrix.Test#testSingle()
	 */
	@Override
	public void testSingle() {
		// TODO Auto-generated method stub
		
	}

}
