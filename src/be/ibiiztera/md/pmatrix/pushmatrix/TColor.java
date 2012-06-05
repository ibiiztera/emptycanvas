/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Atelier
 */
public class TColor {

    public int type = 0;
    public Color couleur = Color.BLACK;
    public BufferedImage image = null;
    public static final int TYPE_TEXTURE = 1;
    public static final int TYPE_COULEUR = 0;

    public int type() {
        return type;
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
    }
}
