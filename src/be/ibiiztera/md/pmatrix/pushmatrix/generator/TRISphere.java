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
public class TRISphere extends TRIObjetGenerateurAbstract {
    private Point3D centre = new Point3D(0,0,0);
    private double radius = 1.0;

    public TRISphere(Point3D c, double r) {
        this.centre = c;
        this.radius = r;
        setCirculaireY(true);
        setCirculaireX(true);
    }

    public double getRadius() {
        return radius;
    }

    public Point3D getCentre() {
        return centre;
    }

    public void setCentre(Point3D centre) {
        this.centre = centre;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    @Override
    public Point3D coordPoint3D(int x, int y) {
		double a = 1.0*x/getMaxX()*Math.PI-Math.PI/2;
		double b = 1.0*y/getMaxY()*2*Math.PI;
         return new Point3D(centre.getX() + Math.cos(a) * Math.cos(b) * radius,
                centre.getY() + Math.cos(a) * Math.sin(b) * radius,
                centre.getZ() + Math.sin(a) * radius);
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
          return "Sphere (\n\t"+centre.toString()+
                "\n\t"+radius+
                "\n\t\""+mapFichier().toString()+
                "\"\n)\n";
  }

}
