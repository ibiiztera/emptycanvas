/**
 * 
 */
package be.ibiiztera.md.pmatrix.pushmatrix;

/**
 * @author MANUEL DAHMEN
 * 
 *         dev
 * 
 *         17 nov. 2011
 * 
 */
public class Matrix33 {
	private double [] d;
	public Matrix33() {
		d = new double[9];
	}
	public Matrix33(double[] d) {
	}
	public double get(int i, int j) {
		return d[i*3+j];
	}
	public void set(int i, int j, double d0) {
		d[i*3+j] = d0;
	}

	public Matrix33(Point3D[] p) {
	}

	public Matrix33 uniteH() {
		return this;
	}

	public Matrix33 uniteV() {
		return this;
	}

	public Matrix33 tild() {
		Matrix33 m = new Matrix33();
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				m.set(i,j, get(j,i));
			}
		}
		return m;
	}

	public Matrix33 inverse() {
		return this;
	}

	public Matrix33 mult(Matrix33 m) {
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				double d0 = 0;
				for(int k=0; k<3; k++)
				{
					d0 += get(i, k) * get(k, j);
				}
				set(i, j, d0);
			}
		}
		return this;
	}
	public Point3D mult(Point3D p)
	{
		return rotation(p);
	}
	public Point3D rotation(Point3D p) {
		Point3D pa = new Point3D();
		for(int i=0; i<3; i++)
		{
			double d0 = 0;
			for(int j=0; j<3; j++)
			{
				d0 += this.get(i, j) * p.get(j);
			}
			pa.set(i, d0);
		}
		return pa;
	}
}
