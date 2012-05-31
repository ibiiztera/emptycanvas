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
package be.ibiiztera.md.pmatrix.pushmatrix;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;

/**
 * cadre à la scène, avec possibilité d'élargir le cadre ou de ne pas en tenir compte
 * @author DAHMEN Manuel
 *
 * dev
 *
 * @date 24-mars-2012
 */
public class SceneCadre {
    private Point3D [] points = new Point3D[4];
    private boolean cadre = false;
    private boolean exterieur = false;
    public SceneCadre()
    {
        cadre = false;
    }
    public void setExterieur(boolean b)
    {
        this.exterieur = b;
    }
    public SceneCadre(Point3D[] p)
    {
        this.points = p;
        cadre = true;
    }
    public void set(int i, Point3D p)
    {
        this.points[i] = p;
    }
    public Point3D get(int i)
    {
        return points[i];
    }

    public boolean isCadre() {
        return cadre;
    }

    public boolean isExterieur() {
        return exterieur;
    }

}
