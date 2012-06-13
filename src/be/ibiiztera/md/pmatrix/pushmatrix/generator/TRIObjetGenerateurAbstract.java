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

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 15 oct. 2011
 *
 */

public abstract class TRIObjetGenerateurAbstract implements TRIObjetGenerateur {
    //Overrides from TriObjetGenerateur

    private BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    protected String mapFichier ="";
    private Color couleur = Color.BLACK;
    private int maxX = 30;
    private int maxY = 30;
    private boolean cx = false;
    private boolean cy = false;

    public String mapFichier()
    {
        return mapFichier;
    }
    public void map(BufferedImage bi, String nomfichier) {
        this.bufferedImage = bi;
        this.mapFichier = nomfichier;
    }

    public void couleur(Color c) {
        this.couleur = c;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setCirculaireX(boolean cx) {
        this.cx = cx;
    }

    public void setCirculaireY(boolean cy) {
        this.cy = cy;
    }

    public boolean getCirculaireX() {
        return cx;
    }

    public boolean getCirculaireY() {
        return cy;
    }

    public abstract Point3D coordPoint3D(int x, int y);

    /***
     *
     * @param numX num�ro de valeur de x par rapport � maxX
     * @param numY num�ro de valeur de y par rapport � maxY
     * @param tris_LEFT_NORD 
     *        TRI[1] = ((x,y),(x+1,y),(x+1,y+1))
     *        TRI[2] = ((x,y),(x,y+1),(x+1,y+1))
     */
    public void getTris(int numX, int numY, TRI[] tris_LEFT_NORTH_FIRST) {
        int nextX = numX + 1;
        int nextY = numY + 1;
        if (numX == maxX && cx) {
            nextX = 0;
        }
        if (numX == maxX && cx) {
            nextY = 0;
        }

        tris_LEFT_NORTH_FIRST[0].setSommet( new Point3D [] {
                coordPoint3D(numX, numY),
                coordPoint3D(nextX, numY),
                coordPoint3D(nextX, nextY)});
        tris_LEFT_NORTH_FIRST[0].setCouleur(
                new Color(bufferedImage.getRGB(
                (int) ((1.0 * numX / maxX) * (bufferedImage.getWidth() - 1)),
                (int) ((1.0 * numY / maxY) * (bufferedImage.getHeight() - 1)))));
        tris_LEFT_NORTH_FIRST[1].setSommet( new Point3D [] {
                coordPoint3D(numX, numY),
                coordPoint3D(numX, nextY),
                coordPoint3D(nextX, nextY)});
        tris_LEFT_NORTH_FIRST[1].setCouleur(
                new Color(bufferedImage.getRGB(
                (int) ((1.0 * numX / maxX) * (bufferedImage.getWidth() - 1)),
                (int) ((1.0 * numY / maxY) * (bufferedImage.getHeight() - 1)))));
    }

    public Point3D getPoint3D(TRI[] tris, int numX, int numY, double ratioX, double ratioY) {
        if (ratioX > ratioY) {
            Point3D[] sommet = tris[0].getSommet();
            Point3D ret = sommet[0].plus(sommet[1].moins(sommet[0]).mult(ratioX)).plus(sommet[2].moins(sommet[1]).mult(ratioY));
            ret.setC(
                    new Color(bufferedImage.getRGB(
                    (int) (((numX + ratioX) / maxX) * (bufferedImage.getWidth() - 1)),
                    (int) (((numY + ratioY) / maxY) * (bufferedImage.getHeight() - 1)))));

            return ret;
        } else {
            Point3D[] sommet = tris[1].getSommet();
            Point3D ret = sommet[0].plus(sommet[1].moins(sommet[0]).mult(ratioY)).plus(sommet[2].moins(sommet[1]).mult(ratioX));
            ret.setC(
                    new Color(bufferedImage.getRGB(
                    (int) (((numX + ratioX) / maxX) * (bufferedImage.getWidth() - 1)),
                    (int) (((numY + ratioY) / maxY) * (bufferedImage.getHeight() - 1)))));
            return ret;
        }
    }
        public void draw(ZBuffer z)
                {
            Point3D INFINI = new Point3D(0,0,10000);
                TRI[] tris = new TRI[2];
                tris[0] = new TRI(INFINI, INFINI, INFINI);
                tris[1] = new TRI(INFINI, INFINI, INFINI);
                int borneX = getMaxX() - 1;
                int borneY = getMaxY() - 1;
                if(getCirculaireX())
                {
                    borneX++;
                }
                if(getCirculaireY())
                {
                    borneY++;
                }
                for (int numX = 0; numX < borneX; numX++) {
                    for (int numY = 0; numY < borneY; numY++) {
                        getTris(numX, numY, tris);

                        double incrMax = 100;
                        for (int t = 0; t < 2; t++) {
                            for (int c = 0; c < 3; c++) {
								
				Point p1 = z.coordonneesPoint2D(tris[t].getSommet()[c]);
				Point p2 = z.coordonneesPoint2D(tris[t].getSommet()[(c + 1) % 3]);
                                if(p1!=null && p2!=null)
                                {
                                    double incr = 1.0 / (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()));
                                    if (incr < incrMax) {
                                        incrMax = incr;
                                    }
                                }
                               }

                            }

                        for (double rx = 0; rx < 1.0; rx += incrMax) {
                            for (double ry = 0; ry < 1.0; ry += incrMax) {
                                z.testPoint(getPoint3D(tris, numX, numY, rx, ry));
                            }
                        }

                    }
                }

        }
}
