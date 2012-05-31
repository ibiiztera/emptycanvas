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
	public class MODTranslation 
	{
		private Point3D vecteur;
		public Point3D vecteur() {return vecteur;}
		public void vecteur(Point3D v) {this.vecteur=v;}
		public Point3D translation(Point3D src)
		{
			return new Point3D(src.getX()+vecteur.getX(),src.getY()+vecteur.getY(),src.getZ()+vecteur.getZ());
		}
	}
