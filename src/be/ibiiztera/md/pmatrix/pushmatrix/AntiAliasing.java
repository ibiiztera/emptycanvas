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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class AntiAliasing {
	public static Image antiAliasing(Image image, int echelle) {
		return image;
	}

	public static Image antiAliasing(ZBuffer z, int echelle, int id) {
		int dimx = z.resX();
		int dimy = z.resY();
                BufferedImage img = z.image();
		Image scaled = new BufferedImage(dimx / echelle, dimy / echelle,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = scaled.getGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, dimx / echelle, dimy / echelle);

		int[][] colors = new int[dimx * dimy / echelle / echelle][5];

		for (int i = 0; i < dimx; i++)
			for (int j = 0; j < dimy; j++) {
				colors[(dimx / echelle) * (j / echelle) + (i / echelle)][0] += 
						new Color(img.getRGB(i, j)).getRed();
				colors[(dimx / echelle) * (j / echelle) + (i / echelle)][1] += 
						new Color(img.getRGB(i, j)).getGreen();
				colors[(dimx / echelle) * (j / echelle) + (i / echelle)][2] += 
						new Color(img.getRGB(i, j)).getBlue();
				// colors[(dimx/echelle)*(j/echelle)+(i/echelle)][4] ++;
			}

		g = scaled.getGraphics();

		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < 3; j++) {
				// colors[i][j] /= colors[i][4];
				colors[i][j] /= (echelle * echelle);

			}
			g.setColor(new Color(colors[i][0], colors[i][1], colors[i][2]));
			g.drawLine(i % (dimx / echelle), i / (dimx / echelle), i
					% (dimx / echelle), i / (dimx / echelle));
		}
		return scaled;
	}
}
