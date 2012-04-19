package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Polygone;
import java.awt.Color;
import java.util.ArrayList;

public class InterpretePolygone implements Interprete {

    private int pos = 0;

    @Override
    public Object interprete(String text, int pos) throws InterpreteException {
        ArrayList<Point3D> points = new ArrayList<Point3D>();

        InterpretesBase ib = null;
        ArrayList<Integer> pattern = null;

        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();

        boolean md5 = true;
        while (md5) {
            ParsePoint pp = new ParsePoint();
            try {
                Point3D p = (Point3D) pp.interprete(text, pos);
                if (pp.getPosition() > pos) {
                    pos = pp.getPosition();
                    points.add(p);
                }
            } catch (Exception ex) {
                md5 = false;
            }
        }
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();
        ParseColor pc = new ParseColor();
        Color c = (Color) pc.interprete(text, pos);
        pos = pc.getPosition();
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();
        this.pos = pos;

        
        Polygone p = new Polygone(c);
        p.setPoints(points);
        return p;
    }

    @Override
    public int getPosition() {
        return pos;
    }

    @Override
    public InterpreteConstants constant() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setConstant(InterpreteConstants c) {
        // TODO Auto-generated method stub
    }

    public static void main(String[] args) {
        InterpretePolygone ilp = new InterpretePolygone();
        String text = "(\n(0.0, 10.0, 0.0) (10.0, 0.0, 0.0) (10.0, 10.0, 0.0) (0.0, 0.0, 0.0)\n)\n";
        ArrayList<Point3D> points = new ArrayList<Point3D>();
        try {
            points = (ArrayList<Point3D>) ilp.interprete(text, 0);
        } catch (InterpreteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(text + "\n\n\t" + points.size());

    }
}
