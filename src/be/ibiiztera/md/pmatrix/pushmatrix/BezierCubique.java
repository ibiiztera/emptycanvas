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
import java.util.ArrayList;
import java.util.Iterator;

public class BezierCubique implements Representable {

    private String id;
    private ArrayList<Point3D> controls = new ArrayList<Point3D>();
    private Color color;

    public void add(int arg0, Point3D arg1) {
        if (size() < 4) {
            controls.add(arg0, arg1);
        }
    }

    public boolean add(Point3D arg0) {
        if (size() < 4) {
            return controls.add(arg0);
        }
        return false;
    }

    public Point3D get(int arg0) {
        return controls.get(arg0);
    }

    public Point3D remove(int arg0) {
        return controls.remove(arg0);
    }

    public Point3D set(int arg0, Point3D arg1) {
        return controls.set(arg0, arg1);
    }

    public int size() {
        return controls.size();
    }

    private Point3D calculerPi1(int i, double t) {
        return get(i).mult(1 - t).plus(get(i + 1).mult(t));
    }

    private Point3D calculerPi2(int i, double t) {
        return calculerPi1(i, t).mult(1 - t).plus(calculerPi1(i + 1, t).mult(t));
    }

    private Point3D calculerPi3(double t) {
        return calculerPi2(0, t).mult(1 - t).plus(calculerPi2(1, t).mult(t));
    }

    public Point3D calculerPoint3D(double t) {
        return calculerPi3(t);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Iterator<Point3D> iterator() {
        return controls.iterator();
    }

    @Override
    public String toString() {
        String s = "bezier (\n\t";
        s+= CouleurOutils.toStringColor(color)+"\n\t";
        Iterator<Point3D> ps = iterator();
        while (ps.hasNext()) {
            s += "\n\t" + ps.next().toString() + "\n";
        }
        return s + ")\n";
    }

    public void setId(String id) {
        id = ID.GEN(this);
    }

    @Override
    public String id() {
        return id;
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
