package be.ibiiztera.md.pmatrix.pushmatrix.generator;

import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;
import java.util.ArrayList;

import be.ibiiztera.md.pmatrix.pushmatrix.BezierCubique;
import be.ibiiztera.md.pmatrix.pushmatrix.ID;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;

public class PointWire implements IFct1D3D, Representable {
	private String id;

	private ArrayList<BezierCubique> beziers;

	public PointWire(ArrayList<Point3D> base) {
		beziers = new ArrayList<BezierCubique>();

		base.add(0, base.get(0));
		base.add(base.get(base.size() - 1));

		for (int i = 0; i < base.size() - 1; i++) {

			BezierCubique bc = new BezierCubique();

			bc.add(base.get(i).plus(base.get(i + 1)).mult(0.5));
			bc.add(base.get(i + 1));
			bc.add(base.get(i + 1));
			bc.add(base.get(i + 1).plus(base.get(i + 2)).mult(0.5));

			beziers.add(bc);

		}
	}

	@Override
	public Point3D value(double t) {
		return beziers.get((int) t).calculerPoint3D(t - (int) t);
	}

	public Point3D tangente(double t) {
		return beziers
				.get((int) (0.0001 + t))
				.calculerPoint3D((0.0000001 + t) - (int) (0.0001 + t))
				.plus(beziers.get((int) t).calculerPoint3D(t - (int) t)
						.mult(-1));
	}

	public Point3D normale(double t) {
		return tangente(t + 0.00001).plus(tangente(t).mult(-1));
	}

	@Override
	public int iteres() {
		return 1000;
	}

	public double maxValue() {
		return beziers.size();
	}

	 @Override
	public String id() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = ID.GEN(this);	
	}

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
