package be.ibiiztera.md.pmatrix.pushmatrix.extras;

import java.awt.Color;

import be.ibiiztera.md.pmatrix.pushmatrix.ID;
import be.ibiiztera.md.pmatrix.pushmatrix.PObjet;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIConteneur;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIGeneratorUtil;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIObject;
import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;

public class Tourbillon implements Representable /* , POConteneur */,
		TRIConteneur {

	private String id;
	private double diametre;
	private double hauteur;
	// private Axe axe;
	private PObjet obj;
	private TRIObject tri;
	private double tours;

	public Tourbillon() {
		this.diametre = 1.0;
		this.hauteur = 1.0;
		// this.axe = new Axe(new Point3D(0, 0, 0), new Point3D(0, 1, 0));
		this.obj = new PObjet();
		this.tours = 4.0;
		Color[] colors = new Color[] { Color.red, Color.green, Color.blue,
				Color.orange, Color.cyan, Color.darkGray, Color.black,
				Color.gray, Color.lightGray, Color.magenta, Color.pink,
				Color.yellow };

		double angle = 0.0;

		int dimx = 100;
		int dimy = colors.length;

		Point3D[] points = new Point3D[dimx * dimy];

		for (int j = 0; j < dimy; j++) {

			Color c = colors[j];

			angle += 2.0 * Math.PI / (dimy - 1);

			for (int i = 0; i < dimx; i++) {

				double h = hauteur * i / dimx;
				double d = h * h * diametre;
				Point3D p = new Point3D(-d
						* Math.sin(2 * Math.PI * tours * h + angle), -h, d
						* Math.cos(2 * Math.PI * tours * h + angle));
				p.setC(c);

				obj.add(p);

				points[dimx * j + i] = p;

			}
		}
		tri = TRIGeneratorUtil.P32DTriQuad(points, dimx, dimy);
	}

	/*
	 * public Iterable<Point3D> iterable() { return obj.getPoints(); }
	 */
	@Override
	public Iterable<TRI> iterable() {
		return tri.getTriangles();
	}

	@Override
	public Representable getObj() {
		return tri;
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = ID.GEN(this);

	}

	@Override
	public String toString() {
		return "tourbillon()";
		// return
		// "tourbillon(\n\n  diametre("+diametre+")\n\n  hauteur(\n\n"+hauteur+")\n\n  triobjet  (\n\n"+tri.toString()+"\n\n)\n\n\n)\n";
	}
   public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
