/**
 * 
 */
package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 15 oct. 2011
 *
 */
public class SegmentDroite implements Representable{
	private Point3D origine;
	private Point3D extremite;
        private Color c;
	public SegmentDroite(Point3D p1, Point3D p2)
	{
		this.setOrigine(p1);
		this.setExtremite(p2);
	}
	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.Representable#id()
	 */
	@Override
	public String id() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.Representable#setId(java.lang.String)
	 */
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return the origine
	 */
	public Point3D getOrigine() {
		return origine;
	}
	/**
	 * @param origine the origine to set
	 */
	public void setOrigine(Point3D origine) {
		this.origine = origine;
	}
	/**
	 * @return the extremite
	 */
	public Point3D getExtremite() {
		return extremite;
	}
	/**
	 * @param extremite the extremite to set
	 */
	public void setExtremite(Point3D extremite) {
		this.extremite = extremite;
	}

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Droite (\n\t" + origine.toString()+"\n\t"+extremite.toString()+"\n\t( "+c.getRed()+" , "+c.getGreen()+" , "+c.getBlue()+" )\n)\n";
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
