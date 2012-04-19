package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.generator.Tubulaire;

public class InterpreteTubulaire implements Interprete {

    private int pos = 0;

    @Override
    public Object interprete(String text, int pos) throws InterpreteException {
        try {
            ArrayList<Point3D> points = new ArrayList<Point3D>();

            InterpretesBase ib = null;
            ArrayList<Integer> pattern = null;
            InterpreteListePoints ilp = null;
            ParseColor pc = null;

            ib = new InterpretesBase();
            pattern = new ArrayList<Integer>();
            pattern.add(ib.BLANK);
            pattern.add(ib.LEFTPARENTHESIS);
            pattern.add(ib.BLANK);
            ib.compile(pattern);
            ib.read(text, pos);
            pos = ib.getPosition();

            ilp = new InterpreteListePoints();
            points = (ArrayList<Point3D>) ilp.interprete(text, pos);
            pos = ilp.getPosition();

            ib = new InterpretesBase();
            pattern = new ArrayList<Integer>();
            pattern.add(ib.BLANK);
            pattern.add(ib.DECIMAL);
            pattern.add(ib.BLANK);
            ib.compile(pattern);
            ArrayList<Object> os = ib.read(text, pos);
            pos = ib.getPosition();

			double radius = (Double) os.get(1);

            pc = new ParseColor();
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

            Tubulaire t = new Tubulaire();
            t.couleur(c);
			t.radius(radius);
            Iterator<Point3D> it = points.iterator();
            while (it.hasNext()) {
                t.add(it.next());
            }

            return t;
        } catch (NullPointerException ex) {
            throw new InterpreteException(ex);
        }
    }

    @Override
    public int getPosition() {
        // TODO Auto-generated method stub
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
        InterpreteTubulaire ilp = new InterpreteTubulaire();
        String text = "(\n"
                + "( (10.0, 0.0, 0.0) (0.0, 0.0, 0.0) (0.0, 10.0, 0.0) )\n"
                + "(0, 0, 255)\n" + ")";
        Tubulaire points = null;
        try {
            points = (Tubulaire) ilp.interprete(text, 0);
        } catch (InterpreteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(text + "\n\n\t" + points.toString());

    }
}
