package be.ibiiztera.md.pmatrix.test.pushmatrix;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.Color;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;

import be.ibiiztera.md.pmatrix.pushmatrix.generator.GUI;

public class TestZBbufferNEW extends TestBox {
	@SuppressWarnings("deprecation")
	public Scene objet(int i) {
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
		s.add(o);
		
		o.rotationObjet(0.01*i,0.1*i);
		return s;
	}

	public void testBox() throws IOException {

		// Box2D b = new Box2D(s);
		/*
		 * System.out.println("minx : " + b.rectangle().getMinX());
		 * System.out.println("miny : " + b.rectangle().getMinY());
		 * System.out.println("l    : " + b.rectangle().getWidth());
		 * System.out.println("h    : " + b.rectangle().getHeight());
		 */

		for (int i = 0; i < 1000; i++) {
			Scene s = objet(i);
			NumberFormat nf = NumberFormat.getIntegerInstance();
			nf.setMinimumIntegerDigits(5);
			nf.setMaximumFractionDigits(0);

			ZBuffer b = new ZBufferImpl(300, 300);
			b.scene(s);
                        b.dessinerSilhouette3D();
			String filename = "op12/2testZBuffer" + nf.format(i) + ".jpg";
			File dir = new File("op12");
			if (!dir.exists()) {
				dir.mkdir();
			}
			System.out.println(filename);
			this.image = b.image();
			ImageIO.write((RenderedImage) image, "jpeg", new File(filename));
			/*
			 * b = new ZBuffer(300, 300); b.calcul(s); filename =
			 * "op5/1SCENEtestZBuffer" + nf.format(i) + ".jpg";
			 * System.out.println(filename); ImageIO.write((RenderedImage)
			 * b.image(), "jpeg", new File(filename));
			 */

		}
	}

	public static void main(String[] argsd) {
		/*
		 * System.out.println("TestZBuffer"); TestZBbuffer tz = new
		 * TestZBbuffer(); try { tz.testBox(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		TestZBbufferNEW tz = new TestZBbufferNEW();
		GUI gui = new GUI("ZBuffer Test", tz);
		new Thread(tz).start();
		gui.run();

	}
}
