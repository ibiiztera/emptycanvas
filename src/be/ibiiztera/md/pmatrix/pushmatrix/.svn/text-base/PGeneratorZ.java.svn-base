/**
 * 
 */
package be.ibiiztera.md.pmatrix.pushmatrix;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 17 oct. 2011
 *
 */
public abstract class PGeneratorZ {
	public int hauteurImage(ZBuffer z)
	{
		return z.resY();
	}
	public int largeurImage(ZBuffer z)
	{
		return z.resX();
	}
	public java.awt.Point point(ZBuffer z, Point3D p)
	{
		return z.coordonneesPointEcran(p);
	}
	
	public abstract void generate(ZBuffer z);

	public abstract void dessine(ZBuffer z);
}
