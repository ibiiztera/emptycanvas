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

public class TRIGeneratorUtil {
	public static TRIObject P32DTriQuad(Point3D[] points, int dimx, int dimy) {
		TRIObject tri = new TRIObject();
		
		for (int i = 0; i < dimx - 1; i++)
			for (int j = 0; j < dimy - 1; j++) {

				TRI t1 = new TRI(points[dimx * j + i], points[dimx * (j + 1)
						+ i], points[dimx * (j + 1) + (i + 1)], points[dimx * j
						+ i].getC());
				tri.add(t1);
				
				TRI t2 = new TRI(points[dimx * j + i], points[dimx * j
						+ (i + 1)], points[dimx * (j + 1) + (i + 1)],
						points[dimx * j + i].getC());
				tri.add(t2);

			}

		return tri;

	}

	public static TRIObject P32DTriQuad(Point3D[][] controle, int dimx, int dimy) {
		Point3D [] bis = new Point3D[dimx*dimy];
 		for(int i=0; i<dimx;i++)
			for(int j=0; j<dimy;j++)
			{
				bis[j*dimx+i] = controle[i][j];
			}
		return P32DTriQuad(bis, dimx, dimy);
	}

}
