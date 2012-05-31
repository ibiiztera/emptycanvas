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


public class BezierCubiqueReseauSurface implements Representable{
	private String id;
	private final int ordre = 3;
	private Point3D[][] r;
	private int m, n;
	private Color[][] colors;

	public BezierCubiqueReseauSurface(int dim1, int dim2) {
		this.setDim1(dim1);
		this.setDim2(dim2);
		r = new Point3D[m][n];
		colors = new Color[m][n];
	}

	public void set(int i, int j, Point3D p) {
		r[i][j] = p;
	}

	public Point3D get(int i, int j) {
		return r[i][j];
	}

	public Point3D calculer11() {
		return null;

	}

	public Point3D calculer22() {
		return null;

	}

	public Point3D calculer(double s, double t, int i, int j) {
		return null;

	}

	public int getOrdre() {
		return ordre;
	}

	public int getDim1() {
		return m;
	}

	public int getDim2() {
		return n;
	}

	private void setDim1(int m) {
		this.m = m;
	}

	private void setDim2(int n) {
		this.n = n;
	}

	public Color getColor(int i, int j) {
		return colors[i][j];
	}

	public void setColor(int i, int j, Color c) {
		colors[i][j] = c;
	}

	public void loadPicture(BufferedImage im) {
		int[][][] c1 = new int[m / 4][n / 4][4];
		for (int i = 0; i < m / 4; i++)
			for (int j = 0; j < n / 4; j++) {
				c1[i][j][4] = -1;
			}
		for (int i = 0; i < im.getWidth(); i += im.getWidth())
			for (int j = 0; j < im.getHeight(); j += im.getHeight()) {
				int idxX = i / m * 4;
				int idxY = j / n * 4;
				if (c1[idxX][idxY][4] == -1) {
					c1[idxX][idxY][4] = 0;
				}
				int c = im.getRGB(i, j);
				c1[idxX][idxY][0] = c & 0xFF0000 >> 16;
				c1[idxX][idxY][1] = c & 0x00FF00 >> 8;
				c1[idxX][idxY][2] = c & 0x0000FF >> 0;
				c1[idxX][idxY][4]++;
			}
		for (int i = 0; i < m/4;i++)
			for (int j = 0; j < n/4; j++) {
				colors[i][j] = new Color(c1[i][j][0] / c1[i][j][4], c1[i][j][1]
						/ c1[i][j][4], c1[i][j][2] / c1[i][j][4]);
			}
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = ID.GEN(this);
		
	}

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
