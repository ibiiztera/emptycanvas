package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;

public abstract class Texture {
	public Color triangle_texture(TRI t) {return t.getCouleur();}
	public Color point3d__texture(Point3D p) {return p.getC();}
	public abstract PObjet p3D_objet_texture(POConteneur p);
	public abstract TRIObject t3d_objet_texture(TRIGenerable p);
}
