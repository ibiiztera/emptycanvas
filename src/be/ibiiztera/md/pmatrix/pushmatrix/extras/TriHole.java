/**
 * 
 */
package be.ibiiztera.md.pmatrix.pushmatrix.extras;

import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;
import java.awt.Color;
import java.util.ArrayList;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIConteneur;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIGeneratorUtil;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIObject;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 27 déc. 2011
 *
 */
public class TriHole implements TRIConteneur, Representable{
	private TRIObject tris = new TRIObject();
	private Point3D lumiere = new Point3D(0,0,1);
	private ArrayList<Point3D> points;
	public TriHole(ArrayList<Point3D> points)
	{
		this.points = points;
		for(int a=0; a<points.size();a++)
		{
			Point3D pa = points.get(a);
			for(int b=0; b<points.size();b++)
			{
				Point3D pb = points.get(b);
				for(int c=0; c<points.size();c++)
				{
					Point3D pc = points.get(c);
					if(pa!=pb && pb!=pc && pc!=pa)
					{
						tris.add(new TRI(pa, pb, pc, new Color(0, (int)(128+127*(lumiere.prodScalaire(Point3D.vecteur(pa, pb).prodVect(Point3D.vecteur(pa,  pc)).norme1()))), 255)));
					}
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.TRIConteneur#iterable()
	 */
	@Override
	public Iterable<TRI> iterable() {
		return tris.getTriangles();
	}

	/* (non-Javadoc)
	 * @see be.ibiiztera.md.pmatrix.pushmatrix.TRIConteneur#getObj()
	 */
	@Override
	public Representable getObj() {
		return tris;
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
	 * @param tri
	 */
	public void add(Point3D p) {
		points.add(p);
		tris = new TRIObject();
		for(int a=0; a<points.size();a++)
		{
			Point3D pa = points.get(a);
			for(int b=0; b<points.size();b++)
			{
				Point3D pb = points.get(b);
				for(int c=0; c<points.size();c++)
				{
					Point3D pc = points.get(c);
					if(pa!=pb && pb!=pc && pc!=pa)
					{
						tris.add(new TRI(pa, pb, pc, new Color(0, (int)(128+127*(lumiere.prodScalaire(Point3D.vecteur(pa, pb).prodVect(Point3D.vecteur(pa,  pc)).norme1()))), 255)));
					}
				}
			}
		}
		
	}

	/**
	 * 
	 */
	public void deleteAll() {
		points.clear();
		
	}
	public void delete(Point3D p)
	{
		points.remove(p);
	}
	
   public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
