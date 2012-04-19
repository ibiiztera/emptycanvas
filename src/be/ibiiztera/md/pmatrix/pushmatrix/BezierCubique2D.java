package be.ibiiztera.md.pmatrix.pushmatrix;


public class BezierCubique2D implements Representable {
	private String id;
	private final int ordreU = 3;
	private final int ordreV = 3;
	private final int ordre = 3;
	/***
	 * 11 12 13 14 0123 21 22 23 24 4567 31 32 33 34 8901 41 42 43 44 2345
	 */
	private Point3D[][] controle;

	private TRIObject plane;

	public BezierCubique2D(Point3D[][] controle) {
		this.controle = controle;


	}

	public BezierCubique2D() {
	}

	public int getOrdre() {
		return ordre;
	}

	public Point3D[][] getControle() {
		return controle;
	}

	public void setControle(int l, int c, Point3D p) {
		controle[l][c] = p;
	}

	public Point3D calculerPoint3D(double tx, double ty) {
	    return bernstein(tx, ty);
	}
    private int factorielle(int n)
    {
	int ret = 1;
	for(int i = 1; i<n; i++)
	    ret*= i;
	return ret;
    }
    private double b(double u, int n, int i)
    {
	return factorielle(n)/factorielle(i)/factorielle(n-i) 
	    * Math.exp(Math.log(u)*i)*Math.exp(Math.log(1-u)*(n-i)); 
    }
    private Point3D bernstein(double u, double v)
	
    {
	int n=ordreU, m=ordreV;
	Point3D p = new Point3D(0,0,0);
	for(int i=0; i<ordre; i++)
	    for(int j=0; j<ordre; j++)
		p = p.plus(controle[i][j].mult(b(u, n, i) * b(v, m, j) ));
	return p;
    }
    public String getId() {
	return id;
    }

    @Override
	public void setId(String id) {
		this.id = ID.GEN(this);
	}

	@Override
	public String id() {
		return id;
	}

    @Override
	public String toString() {
		String s = "bezier2d ( ";
		for (int l = 0; l < 4; l++)
			for (int c = 0; c < 4; c++)
				s += controle[l][c].toString() + " ";
		return s + " )\n";
	}

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
