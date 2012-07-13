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

import be.ibiiztera.md.pmatrix.pushmatrix.extras.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class RandomSpheres extends Animation {

    private Point3D[] ran;
    private Point3D[] next;
    private int n = 10;
    private float t = 0;
    private Random r = new Random();

    public RandomSpheres(Scene s) {
        super(s);
        resX = 1000;
        resY = 1000;
        n = 10;
        ran = new Point3D[n];
        next = new Point3D[n];
        for (int i = 0; i < n; i++) {
            ran[i] = new Point3D(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);
            next[i] = new Point3D(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);

        }

    }

    @Override
    public void modifier() {
        Scene s = new Scene();
        t += 0.01f;
        if (t > 1) {
            t = 0;
            for (int i = 0; i < n; i++) {
                ran[i] = next[i];
                next[i] = new Point3D(r.nextDouble() * 100, r.nextDouble() * 100, r.nextDouble() * 100);

            }
        }
        for (int i = 0; i < n; i++) {
            SimpleSphere ss = new SimpleSphere(ran[i].mult(1 - t).plus(next[i].mult(t)), i, Color.white);
            s.add(ss);
        }
        scene(s);
    }


    public static void main(String[] args) {
        Scene s = new Scene();
        RandomSpheres a = new RandomSpheres(s);
        a.repertoire("RANDOMSPHERES");
        a.nom("RS001");

        a.lancer();
    }
}
