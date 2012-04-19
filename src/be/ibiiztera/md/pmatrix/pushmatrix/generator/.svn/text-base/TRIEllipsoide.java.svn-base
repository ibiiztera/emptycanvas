/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.ibiiztera.md.pmatrix.pushmatrix.generator;

import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;

/**
 *
 * @author DAHMEN Manuel
 *
 * dev
 *
 * @date 22-mars-2012
 */
public class TRIEllipsoide extends TRIObjetGenerateurAbstract {
    private Point3D radius;
    private Point3D centre;

    public TRIEllipsoide(Point3D radius, Point3D centre) {
        this.radius = radius;
        this.centre = centre;
        setCirculaireY(true);;
        setCirculaireX(true);;
    }

    public Point3D getCentre() {
        return centre;
    }

    public Point3D getRadius() {
        return radius;
    }

    public void setCentre(Point3D centre) {
        this.centre = centre;
    }

    public void setRadius(Point3D radius) {
        this.radius = radius;
    }

    @Override
    public Point3D coordPoint3D(int x, int y) {
		double a = 1.0*x/getMaxX()*Math.PI-Math.PI/2;
		double b = 1.0*y/getMaxY()*2*Math.PI;
            return new Point3D(centre.getX() + radius.getX()*Math.cos(a) * Math.cos(b),
                centre.getY() + radius.getX()*Math.cos(a) * Math.sin(b),
                centre.getZ() + radius.getX()*Math.sin(a));
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
         return "Ellipsoide (\n\t"+centre.toString()+
                "\n\t"+radius.toString()+
                "\n\t\""+mapFichier().toString()+
                "\"\n)\n";
   }

}
