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

/**
 *
 * @author manuel
 */
public class CouleurOutils {

    public static String toStringColor(Color couleur) {
        return "(" + couleur.getRed() + ", " + couleur.getGreen() + ", "
                + couleur.getBlue() + ")";
    }

    public String couleurLongID() {
        return "Couleur";
    }

    public static String couleurID() {
        return "c";
    }

    public static Color couleurFactio(Color c1, Color cFond, TRI t, Point3D lumiere, boolean plus) {
        Point3D v1 = t.normale().norme1();
        Point3D v2 = lumiere.norme1();

        double cos = v1.prodScalaire(v2);
        int signe = 1;
        if(!plus)
            signe = -1;
        int [] cFondC = new int[] {cFond.getRed(),cFond.getGreen(), cFond.getBlue()};
        int [] res = new int[] {c1.getRed(),c1.getGreen(), c1.getBlue()};

        for(int i=0; i<3; i++)
        {
            res[i]+=signe*(int)(Math.abs(cFondC[i]*cos));
            if(res[i]<0)
                res[i] = 0;
            if(res[i]>255)
                res[i] = 255;
        }
        return new Color(res[0],res[1],res[2]);
    }
}
