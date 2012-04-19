package be.ibiiztera.md.pmatrix.pushmatrix.generator;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * Implémentations requises: TRIGenerable, TourDeRevolution, Tubulaire, Spheres
 * @author manuel
 */
public interface TRIObjetGenerateur extends Representable{
    public String mapFichier();
    public void map(BufferedImage bi, String nomFichier);
    public void couleur(Color c);
    
    public void setMaxX(int maxX);
    public int getMaxX();
    public void setMaxY(int maxX);
    public int getMaxY();
    public void setCirculaireX(boolean cx);
    public void setCirculaireY(boolean cy);
    public boolean getCirculaireX();
    public boolean getCirculaireY();
    
	public Point3D coordPoint3D(int x, int y);
    /***
     *
     * @param numX numéro de valeur de x par rapport à maxX
     * @param numY numéro de valeur de y par rapport à maxY
     * @param tris_LEFT_NORD
     */
    public void getTris(int numX, int numY, TRI [] tris_LEFT_NORD);
    public Point3D getPoint3D(TRI[] tris, int numX, int numY, double ratioX, double ratioY);
}
