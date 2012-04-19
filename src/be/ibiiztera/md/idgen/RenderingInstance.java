/**
 * 
 */
package be.ibiiztera.md.idgen;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBuffer;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBufferImpl;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         31 oct. 2011
 * 
 */
public class RenderingInstance {
	private int l=1000,h=1000;
	
	private int perspective = ZBufferImpl.PERSPECTIVE_ISOM;
	private String renderID = "TEST 1";
	private int shot = 0; 
	private Scene scene;
	private boolean serie = false;
	private Projet p;
	private int serieNO = 0;
	public void hauteur(){}
	public void largeur(){}
	
	public void render() throws IOException
	{
		ZBuffer z = new ZBufferImpl(l, h);
                
		//z.perspective(perspective);
		z.scene(scene);
		z.dessinerSilhouette3D();
		ImageIO.write((RenderedImage)z.image(), "png", new File(p.outputdir()+File.separator+renderID+"__"+shot+(shot==-1?""+File.separator+"IMG__"+serieNO:"")+".png"));
		while(scene.updateTime())
		{
			serieNO++;
			z.dessinerSilhouette3D();
			;
			ImageIO.write((RenderedImage)z.image(), "png", new File(p.outputdir()+File.separator+renderID+"__"+shot+(shot==-1?""+File.separator+"IMG__"+serieNO:"")+".png"));
		}
	}
}
