/*

    Copyright (C) 2010-2012  DAHMEN, Manuel, Daniel

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

*/
package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Enumeration;


public class CercleReseau implements Representable, PGenerator, TRIGenerable {

	private String id;
	private ArrayList<CourbeDeImage> curves;
	private int numAngles;
	private PObjet points;

	public CercleReseau(int numAngles) {
		curves = new ArrayList<CourbeDeImage>();
		this.numAngles = numAngles;
	}

	public void updateCurve(int i, CourbeDeImage ci) {
		curves.set(i, ci);
	}

	private void addCurve(CourbeDeImage courbeDeImage) {
		curves.add(courbeDeImage);

	}

	public PObjet generatePO() {
		int numPT = 1;

		points = new PObjet();

		for (int i = 0; i < curves.size(); i++) {
			Enumeration<Point> en = curves.get(i).getPoints().keys();
			while (en.hasMoreElements()) {
				Point p2d = en.nextElement();

				/*
				 * for (double a = 0.0 - 2 * Math.PI * i / numAngles / 2; a <
				 * 0.0 + 2 * Math.PI * i / numAngles / 2; a += 2 * Math.PI /
				 * numAngles / numPT) {
				 */
				// double a = 2 * Math.PI * i / numAngles;
				double a = 1.0 * i / numAngles;
				Point3D p = new Point3D(p2d.getX() * Math.cos(a),
						-p2d.getY() * 2, -p2d.getX() * Math.sin(a));
				p.setC(curves.get(i).getPoints().get(p2d));
				points.add(p);

				/* } */

			}
		}
		return points;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = ID.GEN(this);
	}

	@Override
	public String id() {
		return id;
	}

	public static void main(String[] argss) {/*
		try {
			System.out.println(new File(".").getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File[] input = new File[] { new File("im1.jpg"), new File("im2.jpg")};
		CercleReseau cr = new CercleReseau(input.length);
		for (int i = 0; i < input.length; i++) {
			Image m = null;
			try {
				m = ImageIO.read(input[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cr.addCurve(new CourbeDeImage((BufferedImage) m));

		}
		TRIObject o = cr.generate();

		ZBuffer z = new ZBuffer(500, 500);
		Scene s = new Scene();
		s.add(o);
		z.dessinerSilhouette3D(s);
		try {
			ImageIO.write((RenderedImage) z.antiAlias(), "png",
					ImageIO.createImageOutputStream(new File("result.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}*/ 
	}

	@Override
	public TRIObject generate() {
		TRIObject o = new TRIObject();
		int numPT =1;

		points = new PObjet();

		System.out.println(curves.size());
		
		for (int i = 0; i < curves.size(); i++) {
			System.out.println(curves.get(i).getPoints().size());
			
			Enumeration<Point> en = curves.get(i).getPoints().keys();
			while (en.hasMoreElements()) {
				Point p2d = en.nextElement();

				

				for (double a = 2 * Math.PI * i / numAngles ; a < 0.0
						+ 2 * Math.PI * (i+1) / numAngles; a += 2 * Math.PI
						/ numAngles / numPT) {
					System.out.println(a);
					// double a = 2 * Math.PI * i / numAngles;

					Point3D p = new Point3D(-p2d.getX() * Math.cos(a)/100,
							-p2d.getY() * 2/100, p2d.getX() * Math.sin(a)/100);
					p.setC(curves.get(i).getPoints().get(p2d));

					Point3D p1 = p, p2 = new Point3D(p), p3 = new Point3D(p);
					p2.setX(0);
					p3.setZ(0);
					TRI t = new TRI(p1, p2, p3, Color.red);
					o.add(t);

				}

			}
		}

		return o;
	}

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
