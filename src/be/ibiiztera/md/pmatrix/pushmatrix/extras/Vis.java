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
package be.ibiiztera.md.pmatrix.pushmatrix.extras;

import java.util.ArrayList;
import be.ibiiztera.md.pmatrix.pushmatrix.*;

public class Vis implements Representable {

    private String id = "Vis";
    private ArrayList<Point3D> points;
    private double toursParCourbe;

    public Vis(ArrayList<Point3D> points, double toursParCourbe) {
        this.points = points;
        this.toursParCourbe = toursParCourbe;
    }

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
   public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
