package be.ibiiztera.md.pmatrix.test.pushmatrix;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import be.ibiiztera.md.pmatrix.pushmatrix.BezierCubique;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;

public class TestBezierCubique 
 {
	public void testBSpline1() {
		BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		BezierCubique bs = new BezierCubique();
		bs.add(new Point3D(0,     0,   0));
		bs.add(new Point3D(500,   0,   0));
		bs.add(new Point3D(0,   500,   0));
		bs.add(new Point3D(500, 500,   0));
		Point3D p = new Point3D();
		for (double t = 0; t < 1; t += 0.01) {
			Point3D p0 = p;
			p = bs.calculerPoint3D(t);
			System.out.println("t: " + t + " ::Point3D ( " + p.getX() + ", "
					+ p.getY() + ", " + p.getZ() + " )");
			bi.getGraphics().drawLine((int)(p0.getX()), (int)(p0.getY()),
					(int)(p.getX()), (int)(p.getY()));
			
		}p = new Point3D();
		for (int i = 0; i<bs.size(); i++) {
			Point3D p0  = p;
			p = bs.get(i);
			bi.getGraphics().setColor(Color.red);
			bi.getGraphics().drawLine((int)(p.getX()), (int)(p.getY()),
					(int)(p0.getX()), (int)(p0.getY()));
		}
		try {
			ImageIO.write(bi, "jpeg", new File("BEZIERCUBIQUE.JPEG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TestBezierCubique tbs = new TestBezierCubique();
		tbs.testBSpline1();
	}
}