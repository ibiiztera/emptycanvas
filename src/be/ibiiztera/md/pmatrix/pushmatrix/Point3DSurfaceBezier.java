package be.ibiiztera.md.pmatrix.pushmatrix;

public class Point3DSurfaceBezier implements Representable{
	private String id;
	private Point3D[] matrice; // dimx*dimy
	private Point3D[] controle; // dimx*dimy*4
	private int dimx;
	private int dimy;

	public Point3DSurfaceBezier(int dimx, int dimy) {
		super();
		this.dimx = dimx;
		this.dimy = dimy;

		matrice = new Point3D[dimx * dimy];
		controle = new Point3D[dimx * dimy * 4];
	}

	public void setPoint(int x, int y, Point3D p) {
		if (p != null)
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0:
					if (x >= 0 && y >= 0 && x < dimx && y < dimy) {
						matrice[x + y * dimx] = p;
					}
					break;
				case 1:
					if (x >= 0 && y >= 0 && x < dimx && y < dimy - 1) {
						matrice[x + (y + 1) * dimx] = p;
					}
					break;
				case 2:
					if (x >= 0 && y >= 0 && x < dimx - 1 && y < dimy) {
						matrice[(x + 1) + y * dimx] = p;
					}
					break;
				case 3:
					if (x >= 0 && y >= 0 && x < dimx - 1 && y < dimy - 1) {
						matrice[(x + 1) + (y + 1) * dimx] = p;
					}
					break;
				}
			}
	}

	public void setControle(int x, int y, Point3D p11, Point3D p12,
			Point3D p21, Point3D p22) {

		if (x >= 0 && y >= 0 && x < dimx && y < dimy) {
			controle[0 + (x + y * dimx) * 4] = p11;
			controle[1 + (x + y * dimx) * 4] = p12;
			controle[2 + (x + y * dimx) * 4] = p21;
			controle[3 + (x + y * dimx) * 4] = p22;
		}
	}

	public int getDimx() {
		return dimx;
	}

	public int getDimy() {
		return dimy;
	}

	public void calculer(int x, int y, double xd, double yd) {

	}

	public void calculer(double x, double y) {

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

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
