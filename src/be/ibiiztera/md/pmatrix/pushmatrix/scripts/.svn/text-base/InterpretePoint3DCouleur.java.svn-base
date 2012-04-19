package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.util.ArrayList;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import java.awt.Color;

public class InterpretePoint3DCouleur implements Interprete {

    private InterpreteConstants c;
    private int pos;

    public InterpretePoint3DCouleur() {
    }

    public Object interprete(String text, int pos) throws InterpreteException {
        InterpretesBase ib = new InterpretesBase();
        ParsePoint pp;
        ParseColor pc;
        Point3D p = null;

        ArrayList<Integer> pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);


        ib.compile(pattern);
        ArrayList<Object> os = ib.read(text, pos);

        pos = ib.getPosition();

        pp = new ParsePoint();
        p = (Point3D) pp.interprete(text, pos);

        pos = pp.getPosition();

        pc = new ParseColor();
        Color cc = (Color) pc.interprete(text, pos);

        pos = pc.getPosition();

        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);

        ib = new InterpretesBase();
        ib.compile(pattern);
        os = ib.read(text, pos);

        pos = ib.getPosition();

        p.setC(cc);

        this.pos = pos;

        return p;
    }

    public static void main(String[] args) {
        InterpretePoint3DCouleur pp = new InterpretePoint3DCouleur();
        InterpretePoint3D pp2 = new InterpretePoint3D();
        try {
            System.out.println("LONG FORMAT");
            String s = "(( 0.0, 1.0, 2.0) (255, 0, 0))\n";
            System.out.println(s);
            Point3D p = (Point3D) pp.interprete(s, 0);
            System.out.println("Interpretation : " + s.substring(0,pp.getPosition()));
            System.out.println("Resultat : " + p.toLongString());
            System.out.println("SHORT FORMAT");
            s = "( 0.0, 1.0, 2.0)\n";
            System.out.println(s);
            p = (Point3D) pp2.interprete(s, 0);
            System.out.println("Interpretation : " + s.substring(0,pp2.getPosition()));
            System.out.println("Resultat : " + p.toString());

        } catch (InterpreteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public InterpreteConstants constant() {
        return c;
    }

    @Override
    public void setConstant(InterpreteConstants c) {
        this.c = c;
    }

    @Override
    public int getPosition() {
        return pos;
    }
}
