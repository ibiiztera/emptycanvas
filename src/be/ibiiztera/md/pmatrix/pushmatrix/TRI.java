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

import java.awt.Color;

public class TRI implements Representable {

    private String id;
    private Point3D[] sommet;
    private Color couleur;

    public TRI(Point3D point3d, Point3D point3d2, Point3D point3d3,
            Color red) {
        sommet = new Point3D[3];
        sommet[0] = point3d;
        sommet[1] = point3d2;
        sommet[2] = point3d3;
        couleur = red;
    }

    public TRI(Point3D coordPoint3D, Point3D coordPoint3D0, Point3D coordPoint3D1) {
        this(coordPoint3D, coordPoint3D0, coordPoint3D1, Color.black);
    }

    @Override
    public Object clone() {
        TRI t = new TRI((Point3D) sommet[0].clone(), (Point3D) sommet[1].clone(), (Point3D) sommet[2].clone(),
                couleur);
        return t;

    }

    public void setSommet(Point3D[] sommet) {
        this.sommet = sommet;
    }

    public Point3D[] getSommet() {
        return sommet;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        String t = "(";
        for (int i = 0; i < 3; i++) {
            t += "\n\t\t(" + sommet[i].getX() + ", " + sommet[i].getY() + ", " + sommet[i].getZ() + "), ";
        }
        return t + "\n\t\t(" + couleur.getRed() + ", " + couleur.getGreen() + ", " + couleur.getBlue() + ")\n\t)\n";
    }

    public String getId() {
        return id;
    }

    public Point3D normale() {
        return sommet[1].moins(sommet[0]).prodVect(sommet[2].moins(sommet[0]));
    }

    @Override
    public void setId(String id) {
        this.id = ID.GEN(this);
    }

    @Override
    public String id() {
        return id;
    }

    public void rotation(Axe axe, double degres) {
        for (Point3D p : sommet) {
            p.rotatePoint(axe, degres);
        }
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
