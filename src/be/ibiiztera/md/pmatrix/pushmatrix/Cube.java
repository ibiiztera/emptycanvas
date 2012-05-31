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

import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;
import java.util.ResourceBundle;
import java.awt.Color;
import java.util.Iterator;

public class Cube implements TRIGenerable, Representable {

    private String id;
    private double mlc = 1.0;
    private TRIObject cube;
    private Point3D position = new Point3D(0.0, 0.0, 0.0);
    private String data;
    private Color couleur = null;

    public Cube() {
        super();
        generate();
    }

    public Cube(Color c) {
        super();
        this.couleur = c;
        generate();
    }

    public Cube(double mlc, Point3D position) {
        super();
        this.mlc = mlc;
        this.position = position;
    }

    public double getMlc() {
        return mlc;
    }

    public void setMlc(double mlc) {
        this.mlc = mlc;
    }

    public TRIObject getCube() {
        return cube;
    }

    public void setCube(TRIObject cube) {
        this.cube = cube;
    }

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = ID.GEN(this);
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public TRIObject generate() {
        Loader l = new Loader();
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/Cube");
        data = bundle.getString("CUBEDEFTRIS");
        Scene sc = new Scene();
        try {
            l.loadIF(data, sc);
        } catch (Exception e) {
            System.err.println(data);
            e.printStackTrace();
            return new TRIObject();
        }
        TRIObject t = (TRIObject) sc.get(0);
        Iterator<TRI> it = t.getTriangles().iterator();
        while (it.hasNext()) {
            TRI tt = it.next();
            tt.setCouleur(couleur == null ? tt.getCouleur() : couleur);
            tt.setSommet(new Point3D[]{
                        tt.getSommet()[0].mult(mlc).plus(position),
                        tt.getSommet()[1].mult(mlc).plus(position),
                        tt.getSommet()[2].mult(mlc).plus(position)
                    });
        }
        return t;
    }

    @Override
    public String toString() {
        return "cube(\n\t" + position.toString() + "\n\t" + mlc + "\n)\n";
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
