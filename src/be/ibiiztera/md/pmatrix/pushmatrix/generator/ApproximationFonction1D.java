package be.ibiiztera.md.pmatrix.pushmatrix.generator;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Manuel DAHMEN
 */
public class ApproximationFonction1D implements Courbe{

    @Override
    public String id() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public class Point
    {
        public double x;
        public double y;
    }
    public static final int TYPE_DROITE = 0;
    public static final int TYPE_BEZIER = 1;
    private final ArrayList<Point3D> points;
    private int type_last;
    private ArrayList<Representable> objets;
    private HashMap<Double, Representable> r;
    public ApproximationFonction1D()
    {
        points = new ArrayList<Point3D> ();
        objets = new ArrayList<Representable>();
        r = new HashMap<Double, Representable>();
    }
    public void add(int type, double x, double y)
    {
        points.add(new Point3D(x, y, 0));
        if(type==TYPE_DROITE)
        {
            if(points.size()==2)
            {
                SegmentDroite sd = new SegmentDroite(points.get(0), points.get(1));
                objets.add(sd);
                r.put(points.get(0).get(0), (Representable) sd);
                points.clear();
            }
        }
        else if(type==TYPE_BEZIER)
        {
            if(points.size()==4)
            {
                BezierCubique bc = new BezierCubique();
                objets.add(bc);
                r.put(points.get(0).get(0), (Representable) bc);
                bc.add(points.get(0));
                bc.add(points.get(1));
                bc.add(points.get(2));
                bc.add(points.get(3));
                 points.clear();
            }
        }
    }

    public ArrayList<Representable> getObjets() {
        return objets;
    }
    
}
