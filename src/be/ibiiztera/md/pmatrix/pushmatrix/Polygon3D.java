package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;
@Deprecated
public class Polygon3D implements Representable{


	private String id;
	private static final long serialVersionUID = 198300003L;
	private Point3D [] sommet;
	private Color couleur;
	public Polygon3D(Point3D point3d, Point3D point3d2, Point3D point3d3, Point3D p4,
			Color red) {
		sommet = new Point3D[4];
		sommet[0] = point3d;
		sommet[1] = point3d2;
		sommet[2] = point3d3;
		sommet[3] = p4;
		couleur = red;
	}
    @Override
	public Object clone()
	{
		Polygon3D t = new Polygon3D((Point3D)sommet[0].clone(),(Point3D)sommet[1].clone(),(Point3D)sommet[2].clone(), (Point3D)sommet[3].clone(), 
				couleur);
		return t;
		
	}
	public void setSommet(Point3D [] sommet) {
		this.sommet = sommet;
	}
	public Point3D [] getSommet() {
		return sommet;
	}
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	public Color getCouleur() {
		return couleur;
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
	public String toString()
	{
		return "POLYGON ( "+sommet[0]+" , "+sommet[1]+" , "+sommet[2]+" , "+sommet[3]+" )\n";	
	}

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

