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
import java.awt.image.BufferedImage;

/**
 *
 * Implémentations requises: TRIGenerable, TourDeRevolution, Tubulaire, Spheres
 * @author manuel
 */
public interface TRIObjetGenerateur extends Representable{
    public String mapFichier();
    public void map(BufferedImage bi, String nomFichier);
    public void couleur(Color c);
    
    public void setMaxX(int maxX);
    public int getMaxX();
    public void setMaxY(int maxX);
    public int getMaxY();
    public void setCirculaireX(boolean cx);
    public void setCirculaireY(boolean cy);
    public boolean getCirculaireX();
    public boolean getCirculaireY();
    
	public Point3D coordPoint3D(int x, int y);
    /***
     *
     * @param numX numéro de valeur de x par rapport à maxX
     * @param numY numéro de valeur de y par rapport à maxY
     * @param tris_LEFT_NORD
     */
    public void getTris(int numX, int numY, TRI [] tris_LEFT_NORD);
    public Point3D getPoint3D(TRI[] tris, int numX, int numY, double ratioX, double ratioY);
}
