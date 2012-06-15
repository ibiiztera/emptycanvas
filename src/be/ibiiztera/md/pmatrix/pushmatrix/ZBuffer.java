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

import java.awt.*;
import java.awt.image.*;

public interface ZBuffer
{
    public ZBuffer getInstance(int x, int y);
    public int resX();
    public int resY();
    public Scene scene();
    public void scene(Scene s);
    public void couleurDeFond(Color c);
    public void zoom(float z);
	@Deprecated
    public void dessinerContours();
	@Deprecated
    public void dessinerSilhouette();
    public void dessinerSilhouette3D();
    public BufferedImage image();
    public void plotPoint(Point3D p, Color c);
    public void testPoint(Point3D p, Color c);
    public void testPoint(Point3D point3D);
    public void suivante();
    public void isometrique();
    public void perspective();
    public Point coordonneesPoint2D(Point3D p);
    public double distanceCamera(Point3D p);
    public void camera(Camera c);
    public Camera camera();

}
