/*

    Copyright (C) 2010-2012  DAHMEN, Manuel, Daniel

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
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

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
