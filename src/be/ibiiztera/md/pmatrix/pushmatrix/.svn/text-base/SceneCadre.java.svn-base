/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
