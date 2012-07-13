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
import java.awt.image.BufferedImage;

/**
 *
 * @author Atelier
 */
public class TColor {
    public static final int TYPE_TEXTURE = 1;
    public static final int TYPE_COULEUR = 0;
    public int type = 0;
    private Color couleur = Color.BLACK;
    private BufferedImage image = null;
    private String nom = "texture";

    public int type() {
        return type;
    }

    public void type(int t) {
        type = t;
    }

    public Color couleur(double rx, double ry) {
        if (type == TYPE_TEXTURE && image != null) {
            int x = (int) (rx * image.getWidth());
            int y = (int) (ry * image.getHeight());
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            if (x >= image.getWidth()) {
                x = image.getWidth() - 1;
            }
            if (y >= image.getHeight()) {
                y = image.getHeight() - 1;
            }
            return new Color(image.getRGB(x, y));
        } else if (type == TYPE_COULEUR) {
            return couleur;
        }
        return couleur;
    }

    public TColor(BufferedImage i) {
        this.image = i;
        type = TYPE_TEXTURE;
    }

    public TColor(Color c) {
        this.couleur = c;
        type = TYPE_COULEUR;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Color getCouleur() {
        return couleur;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    
}
