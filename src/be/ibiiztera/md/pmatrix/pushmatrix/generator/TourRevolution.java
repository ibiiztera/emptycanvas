package be.ibiiztera.md.pmatrix.pushmatrix.generator;

import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Manuel DAHMEN
 */
public class TourRevolution implements TRIObjetGenerateur
{
    private ApproximationFonction1D ame;
    private ApproximationFonction2D base;

    public TourRevolution(ApproximationFonction1D ame, ApproximationFonction2D base) {
        this.ame = ame;
        this.base = base;
    }

    @Override
    public String mapFichier() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void map(BufferedImage bi, String nomFichier) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void couleur(Color c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMaxX(int maxX) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getMaxX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMaxY(int maxX) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getMaxY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCirculaireX(boolean cx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCirculaireY(boolean cy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean getCirculaireX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean getCirculaireY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Point3D coordPoint3D(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void getTris(int numX, int numY, TRI[] tris_LEFT_NORD) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Point3D getPoint3D(TRI[] tris, int numX, int numY, double ratioX, double ratioY) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String id() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
