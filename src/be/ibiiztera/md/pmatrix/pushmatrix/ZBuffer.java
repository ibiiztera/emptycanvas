package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.*;
import java.awt.image.*;

public interface ZBuffer
{
    public ZBuffer getInstance(int x, int y);
    public java.awt.Point coordonneesPointEcran(Point3D p);
    public java.awt.Point coordonneesPointEcranPerspective(Point3D x3d);
    public int resX();
    public int resY();
    public Scene scene();
    public void scene(Scene s);
    public void couleurDeFond(Color c);
    public void zoom(float z);
    public void dessinerContours();
    public void dessinerSilhouette();
    public void dessinerSilhouette3D();
    public BufferedImage image();
    public void plotPoint(Point3D p, Color c);
    public void testPoint(Point3D p, Color c);
    public void testPoint(Point3D point3D);
    public void suivante();
    public void isometrique();
    public void perspective(double camera, double planproj);
}
