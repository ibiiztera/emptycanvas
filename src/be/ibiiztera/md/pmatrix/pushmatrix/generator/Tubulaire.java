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
package be.ibiiztera.md.pmatrix.pushmatrix.generator;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.Color;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;

public class Tubulaire implements Representable, TRIGenerable, TRIConteneur {

    private Color couleur = Color.BLUE;
    private String id;
    private ArrayList<Point3D> points;
    //private double ratio;
    private ArrayList<BezierCubique> beziers;
    private double diam = 3.0f;
    private float TAN_FCT = 0.00005f;
    private float NORM_FCT = 0.0005f;
    public float PERCENT = 0.05f;
    private int N_TOURS = 40;
    private TRIObject tris = null;

    public Tubulaire() {
        this.points = new ArrayList<Point3D>();
        //this.ratio = 1.0;

    }

    public void diam(float diam) {
        this.diam = diam;
    }

    public void nbrAnneaux(int n) {
        this.PERCENT = 1.0f * beziers.size() / n;
    }

    public void radius(double d) {
        diam = d;
    }

    public void nbrRotations(int r) {
        this.N_TOURS = r;
    }

    public void addPoint(Point3D p) {
        points.add(p);
    }

    public Point3D cerclePerp(Point3D vect, double angle) {
        return null;
    }

    public PObjet calculPoints(IFct1D3D funct, double value, double angle) {
        return null;
    }

    public void generateWire() {
        System.out.println("WIRE SIZE " + points.size());
        beziers = new ArrayList<BezierCubique>();

        for (int i = 0; i < points.size() - 3; i += 4) {
            BezierCubique bc = new BezierCubique();
            bc.add(points.get(i));
            bc.add(points.get(i + 1));
            bc.add(points.get(i + 2));
            bc.add(points.get(i + 3));
            /*bc.add(points.get(i));
            //bc.add((points.get(i).mult(ratio)).plus((points.get(i + 1).mult(1 - ratio))));
            //bc.add((points.get(i + 2).mult(ratio)).plus((points.get(i + 1).mult(1 - ratio))));
            //bc.add(points.get(i + 2));
             */
            beziers.add(bc);
        }

        System.out.println("Beziers = " + beziers.size());
    }

    private ArrayList<Point3D> vectPerp(double t) {
        ArrayList<Point3D> vecteurs = new ArrayList<Point3D>();

        Point3D p = calculerPoint(t);
        Point3D tangente = calculerTangente(t);

        Point3D ref1 = new Point3D(0, 0, 1);
        Point3D ref2 = new Point3D(1, 0, 0);
        Point3D ref3 = new Point3D(0, 1, 0);

        tangente = tangente.norme1();

        if (tangente != null) {
            Point3D px = calculerNormale(t);///tangente.prodVect(ref1);

            if (px.norme() == 0) {
                px = tangente.prodVect(ref2);
            }
            if (px.norme() == 0) {
                px = tangente.prodVect(ref3);
            }

            Point3D py = px.prodVect(tangente);

            px = px.norme1();
            py = py.norme1();
            //System.out.println("px.py: " +px.prodScalaire(py)+"px.tg: "+px.prodScalaire(tangente)+"py.tg "+py.prodScalaire(tangente));
            vecteurs.add(px);
            vecteurs.add(py);
            vecteurs.add(tangente);

            for (int i = 0; i < N_TOURS+1; i++) {
                double angle = 2.0f * Math.PI * i / N_TOURS;
                vecteurs.add(p.plus(px.mult(Math.cos(angle)*diam)).plus(
                        py.mult(Math.sin(angle)*diam)));
            }
        }
        return vecteurs;
    }


    public double tMax() {
        return (double) beziers.size();
    }

    public Point3D calculerPoint(double t) {
        return beziers.get((int) t).calculerPoint3D(t - (int) t);
    }

    public Point3D calculerTangente(double t) {
        if (t < beziers.size() - TAN_FCT) {

            return calculerPoint(t + TAN_FCT).moins(calculerPoint(t));
        } else {
            return null;
        }
    }
    public Point3D calculerNormale(double t) {
        if (t < beziers.size() - NORM_FCT) {

            return calculerTangente(t + NORM_FCT).moins(calculerTangente(t));
        } else {
            return null;
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

    public boolean add(Point3D e) {
        return points.add(e);
    }

    public void clear() {
        points.clear();
    }

    @Override
    public TRIObject generate() {
        if (tris == null) {
            tris = new TRIObject();

            generateWire();

            int length = beziers.size();

            ArrayList<Point3D> tour0 = vectPerp(0);
            for (double t = 0; t < length; t += PERCENT) {
                ArrayList<Point3D> tour1 = vectPerp(t);
                for (int i = 3; i < tour1.size() - 1; i++) {
                    TRI t1 = new TRI(tour0.get(i), tour1.get(i), tour1.get(i + 1), couleur);
                    TRI t2 = new TRI(tour0.get(i), tour0.get(i + 1), tour1.get(i + 1), couleur);
                    t1.setCouleur(CouleurOutils.couleurFactio(couleur, Color.white, t1, new Point3D(0, 0, 1), false));
                    t2.setCouleur(CouleurOutils.couleurFactio(couleur, Color.white, t1, new Point3D(0, 0, 1), false));
                    t1.setCouleur(CouleurOutils.couleurFactio(couleur, Color.white, t1, new Point3D(0, 0, 1), false));
                    t1.setCouleur(couleur);
                    t2.setCouleur(couleur);
                    tris.add(t1);
                    tris.add(t2);
                }

                tour0 = tour1;
            }
        }
        return tris;
    }

    /*public void ratio(double r) {
    ratio = r;
    }*/
    protected String toStringColor() {
        return "(" + couleur.getRed() + ", " + couleur.getGreen() + ", "
                + couleur.getBlue() + ")";
    }

    @Override
    public String toString() {
        String s = "tubulaire (\n\t(";
        Iterator<Point3D> it = points.iterator();
        while (it.hasNext()) {
            s += "\n\t" + it.next().toString();
        }
        s += "\n\n)\n\t" + diam + "\n\t" + toStringColor() + "\n)\n";
        return s;
    }

    @Override
    public Iterable<TRI> iterable() {
        generate();
        return tris.getTriangles();
    }

    @Override
    public Representable getObj() {
        generate();
        return tris;
    }

    public static void main(String[] args) {
        ZBuffer z = new ZBufferImpl(1000, 1000);
        Scene scene = new Scene();
        try {
            String fichier = System.getProperty("user.home") + "\\PMMATRIX.data\\testscripts\\tubulaire.txt";
            System.out.println(fichier);
            new Loader().loadIF(new File(fichier), scene);
            System.out.println(scene.get(0));
            z.scene(scene);
            z.dessinerSilhouette3D();
            ImageIO.write((RenderedImage) z.image(), "png", new File(System.getProperty("user.home") + "\\tubulaire001.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void couleur(Color c) {
        this.couleur = c;

    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
