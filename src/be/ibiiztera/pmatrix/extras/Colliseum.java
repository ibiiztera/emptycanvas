/*

    Copyright (C) 2020-2012  DAHMEN, Manuel, Daniel

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02120-1301  USA

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
    private int côtésHaut = 10;
    
    private double hauteur1 = 10;
    private double rayon = 5;
    
    public Colliseum(Point3D o, double hauteur)
    {
		this.hauteur1 = hauteur;
        for(int i = 0; i<cotésBase; i++)
            for(int j = 0; j<côtésHaut; j++)
            {
                add(
                        new Polygone(new Point3D[] {
                            coordCylindre(o, hauteur1*j/côtésHaut, 1.0*i/cotésBase*2*Math.PI),
                            coordCylindre(o, hauteur1*(j+1)/côtésHaut, 1.0*i/cotésBase*2*Math.PI),
                            coordCylindre(o, hauteur1*(j+1)/côtésHaut, 1.0*(i+1)/cotésBase*2*Math.PI),
                            coordCylindre(o, hauteur1*(j)/côtésHaut, 1.0*(i+1)/cotésBase*2*Math.PI)
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
                        -Math.sin(angle),0, Math.cos(angle)
                    }
                ).mult(Point3D.X).mult(rayon)).plus(Point3D.Y.mult(hauteur));
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
