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

package be.ibiiztera.md.pmatrix.pushmatrix.base;

import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import be.ibiiztera.md.pmatrix.pushmatrix.generator.TRIObjetGenerateurAbstract;

/**
 *
 * @author Manuel DAHMEN
 */
public class CylindreDeRevolution extends TRIObjetGenerateurAbstract
{
    private final Point3D pBase;
    private final Point3D pHaut;
    private final double radius;
    private Point3D p2;
    private Point3D p3;
    public CylindreDeRevolution(Point3D pBase, Point3D pHaut, double radius)
    {
        this.pBase=pBase;
        this.pHaut=pHaut;
        this.radius=radius;

        
        Point3D p2 = null;
        if(pBase.moins(pHaut).norme1().prodVect(Point3D.X).norme()<0.1)
            if(pBase.moins(pHaut).norme1().prodVect(Point3D.Y).norme()<0.1)
                if(pBase.moins(pHaut).norme1().prodVect(Point3D.Z).norme()<0.1)
                    throw new NullPointerException("Cylindre de révolution : axe invalide");
                else
                    p2 = pBase.moins(pHaut).prodVect(Point3D.Z).norme1();
            else
                p2 = pBase.moins(pHaut).prodVect(Point3D.Y).norme1();
        else
           p2 = pBase.moins(pHaut).prodVect(Point3D.X).norme1();
        Point3D p3 = pBase.moins(pHaut).norme1().prodVect(p2).norme1();
    }
    protected Point3D axe(double a)
    {
        return pBase.mult(1-a).plus(pHaut.mult(a));
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

    @Override
    public Point3D coordPoint3D(int x, int y) {
        Point3D p = axe(1.0*x/getMaxX()-2);
        if(x>0&&x<getMaxX()-1)
            return p.plus(p2.mult(Math.cos(1.0*y/getMaxY()))).plus(p3.mult(Math.sin(1.0*y/getMaxY())));
        else if(x==0)
            return pBase;
        else if(x==getMaxX()-1)
            return pHaut;
        return null;
    }
    
}
