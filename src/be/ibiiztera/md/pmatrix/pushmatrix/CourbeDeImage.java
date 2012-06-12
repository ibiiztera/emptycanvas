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
import java.util.Hashtable;
import java.util.Set;

public class CourbeDeImage {

    private BufferedImage image;
    private Hashtable<Point2D, Color> points;

    public CourbeDeImage(BufferedImage image) {
        super();
        this.image = image;
        this.points = new Hashtable<Point2D, Color>();

        anayliserImage();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Hashtable<Point2D, Color> getPoints() {
        return points;
    }

    public void setPoints(Hashtable<Point2D, Color> points) {
        this.points = points;
    }

    public Set<Point2D> getPointsList() {
        return points.keySet();
    }

    public void anayliserImage() {
        for (int i = 0; i < image.getWidth(); i++) {
            int x0 = i;
            int y0 = -1;
            int y1 = -1;
            for (int j = 0; j < image.getHeight(); j++) {
                if (!new Color(image.getRGB(i, j)).equals(Color.white)) {
                    y0 = y1;
                    y1 = j;
                    if (y0 == -1 || (y1 > y0 + 1)) {
                        points.put(new Point2D(i, j), new Color(image.getRGB(i, j)));
                        break;
                    }
                }

            }
        }
    }

    public void classerPoints() {
    }
}
