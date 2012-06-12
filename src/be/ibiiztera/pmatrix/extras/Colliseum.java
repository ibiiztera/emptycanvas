/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.pmatrix.extras;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import be.ibiiztera.md.pmatrix.pushmatrix.generator.TRIObjetGenerateurAbstract;
import java.awt.Color;

/**
 *
 * @author Atelier
 */
public final class Colliseum extends RepresentableConteneur
{
    private int cotésBase = 100;
    private int côtésHaut = 100;
    
    private double hauteur1 = 10;
    private double rayon = 5;
    
    public Colliseum()
    {
        for(int i = 0; i<cotésBase; i++)
            for(int j = 0; j<côtésHaut; j++)
            {
                add(
                        new Polygone(new Point3D[] {
                            coordCylindre(Point3D.O0, hauteur1*j/côtésHaut, 1.0*i/cotésBase*2*Math.PI),
                            coordCylindre(Point3D.O0, hauteur1*(j+1)/côtésHaut, 1.0*i/cotésBase*2*Math.PI),
                            coordCylindre(Point3D.O0, hauteur1*(j+1)/côtésHaut, 1.0*(i+1)/cotésBase*2*Math.PI),
                            coordCylindre(Point3D.O0, hauteur1*(j)/côtésHaut, 1.0*(i+1)/cotésBase*2*Math.PI)
                        },
                            Color.YELLOW));
            }
    }
    protected Point3D coordCylindre(Point3D base, double hauteur, double angle)
    {
        Point3D p =  base.plus(
                new Matrix33(
                    new double[]{
                        Math.cos(angle),0, Math.sin(angle),
                        0,              1, 0,
                        Math.sin(angle),0, Math.cos(angle)
                    }
                ).mult(Point3D.X).mult(rayon)).plus(Point3D.Y.mult(hauteur));
        //System.out.println(p.toString());
        return p;
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
