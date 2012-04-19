package be.ibiiztera.md.pmatrix.pushmatrix.generator;

import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import java.awt.image.BufferedImage;

public class Plan3D extends TRIObjetGenerateurAbstract {

    private Point3D p0 = new Point3D(0.0, 0.0, 0.0);
    private Point3D vX = new Point3D(1.0, 0.0, 0.0);
    private Point3D vY = new Point3D(0.0, 1.0, 0.0);

    public void pointOrigine(Point3D p0) {
        this.p0 = p0;
    }

    public void pointXExtremite(Point3D vX) {
        this.vX = vX;
    }

    public void pointYExtremite(Point3D vY) {
        this.vY = vY;
    }

    public Point3D pointOrigine() {
        return p0;
    }

    public Point3D pointXExtremite() {
        return vX;
    }

    public Point3D pointYExtremite() {
        return vY;
    }
//Implements TRIObjetGenerateurAbstract.coordPoint3D

    public Point3D coordPoint3D(int x, int y) {
        return p0.plus(vX.mult(1.0 * x / getMaxX())).plus(vY.mult(1.0 * y / getMaxY()));
    }

    public String id() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return "Plan (\n\t"+p0.toString()+
                "\n\t"+vX.toString()+
                "\n\t"+vY.toString()+
                "\n\t\""+mapFichier().toString()+
                "\"\n)\n";
    }

 

}
