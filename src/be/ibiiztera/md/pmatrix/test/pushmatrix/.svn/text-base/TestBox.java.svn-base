package be.ibiiztera.md.pmatrix.test.pushmatrix;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.NumberFormat;

import be.ibiiztera.md.pmatrix.pushmatrix.generator.GUI;

public class TestBox extends Thread {

	protected BufferedImage image;
	private ZBuffer b;
	private String filename;
	public void testBox() throws IOException {
		Scene s = new Scene();
		TRIObject o = new TRIObject();
		o.add(new TRI(new Point3D(1, 0, 0), new Point3D(1, 1, 0),
				new Point3D(1, 1, 1), Color.red));
		o.add(new TRI(new Point3D(1, 0, 0), new Point3D(1, 0, 1),
				new Point3D(1, 1, 1), Color.red));
		o.add(new TRI(new Point3D(0, 0, 0), new Point3D(0, 1, 0),
				new Point3D(0, 1, 1), Color.red));
		o.add(new TRI(new Point3D(0, 0, 0), new Point3D(0, 0, 1),
				new Point3D(0, 1, 1), Color.red));

		o.add(new TRI(new Point3D(0, 1, 0), new Point3D(1, 1, 0),
				new Point3D(1, 1, 1), Color.green));
		o.add(new TRI(new Point3D(0, 1, 0), new Point3D(0, 1, 1),
				new Point3D(1, 1, 1), Color.green));
		o.add(new TRI(new Point3D(0, 0, 0), new Point3D(1, 0, 0),
				new Point3D(1, 0, 1), Color.green));
		o.add(new TRI(new Point3D(0, 0, 0), new Point3D(0, 0, 1),
				new Point3D(1, 0, 1), Color.green));

		
		o.add(new TRI(new Point3D(0, 0, 0), new Point3D(0, 1, 0),
				new Point3D(1, 1, 0), Color.blue));
		o.add(new TRI(new Point3D(0, 0, 0), new Point3D(1, 0, 0),
				new Point3D(1, 1, 0), Color.blue));
		
		o.add(new TRI(new Point3D(0, 0, 1), new Point3D(0, 1, 1),
				new Point3D(1, 1, 1), Color.blue));
		o.add(new TRI(new Point3D(0, 0, 1), new Point3D(1, 0, 1),
				new Point3D(1, 1, 1), Color.blue));
		
		// Box2D b = new Box2D(s);
		/*
		 * System.out.println("minx : " + b.rectangle().getMinX());
		 * System.out.println("miny : " + b.rectangle().getMinY());
		 * System.out.println("l    : " + b.rectangle().getWidth());
		 * System.out.println("h    : " + b.rectangle().getHeight());
		 */

		for (int i = 0; i < 1000; i++) {
			@SuppressWarnings("deprecation")
			TRIObject op = ((TRIObject)o.clone()).rotationObjet(0.01*i, 0.001*i);
			s.add(op);
			
			NumberFormat nf = NumberFormat.getIntegerInstance();
			nf.setMinimumIntegerDigits(5);
			nf.setMaximumFractionDigits(0);
			
			b = new ZBufferImpl(300, 300);
			b.scene(s);
                        b.dessinerContours();
			filename = "op11/2testZBuffer" + nf.format(i) + ".jpg";
			System.out.println(filename);
			this.image = b.image();
			//ImageIO.write((RenderedImage) b.image(), "jpeg", new File(filename));
			
			
			/*
			b = new ZBuffer(300, 300);
			b.dessinerSilhouette3D(s);
			filename = "op11/1SCENEtestZBuffer" + nf.format(i) + ".jpg";
			System.out.println(filename);
			this.image = b.image();
			//ImageIO.write((RenderedImage) image, "jpeg", new File(filename));
			*/
			
			s.remove(op);
		}
	}
	public void run()
	{
		try {
			testBox();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] argsd) {
		/*
		 * System.out.println("TestZBuffer");
		TestZBbuffer tz = new TestZBbuffer();
		try {
			tz.testBox();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		TestBox tz = new TestBox();
		GUI gui = new GUI("ZBuffer Test", tz);
		new Thread(tz).start();
		gui.run();
		
		
	}
	public Image image() {
		return image;
	}
}
