/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class InterpreteModdificateur implements Interprete {
    private int pos;

    public Object interprete(String text, int pos) throws InterpreteException {
        boolean set_mr = false;
        boolean set_mh = false;
        boolean set_mt = false;
        MODHomothetie mh = new MODHomothetie();
        MODRotation mr = new MODRotation();
        MODTranslation mt = new MODTranslation();

        ArrayList<Integer> pattern;
        InterpretesBase ib;
        MODObjet mo = new MODObjet();

        // Interprete homothetie
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.MULTIPLICATION);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        if(ib.read(text, pos).get(1) instanceof InterpretesBase.CODE)
        {
            pos = ib.getPosition();
            ib = new InterpretesBase();
            pattern = new ArrayList<Integer>();
            pattern.add(ib.BLANK);
            pattern.add(ib.LEFTPARENTHESIS);
            pattern.add(ib.BLANK);
            ib.compile(pattern);
            if(ib.read(text, pos).get(1) instanceof InterpretesBase.CODE)
            {
                pos = ib.getPosition();
                ParsePoint pp = new ParsePoint();
                mh.centre((Point3D)pp.interprete(text, pos));
                
                pos = pp.getPosition();
                
                ib = new InterpretesBase();
                pattern = new ArrayList<Integer>();
                pattern.add(ib.BLANK);
                pattern.add(ib.DECIMAL);
                pattern.add(ib.BLANK);
                pattern.add(ib.RIGHTPARENTHESIS);
                pattern.add(ib.BLANK);

                ib.compile(pattern);
                ib.read(text, pos);
                pos = ib.getPosition();


                mh.facteur((Double)ib.read(text, pos).get(1));

                set_mh = true;
            }



        }

        // Interprete rotation
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.DIESE);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        if(ib.read(text, pos).get(1) instanceof InterpretesBase.CODE)
        {
            pos = ib.getPosition();
            ib = new InterpretesBase();
            pattern = new ArrayList<Integer>();
            pattern.add(ib.BLANK);
            pattern.add(ib.LEFTPARENTHESIS);
            pattern.add(ib.BLANK);
            ib.compile(pattern);
            if(ib.read(text, pos).get(1) instanceof InterpretesBase.CODE)
            {
                pos = ib.getPosition();
                Point3D vAxe [] = new Point3D[3];
                for(int vecteur = 0; vecteur < 3; vecteur++){
                    ParsePoint pp = new ParsePoint();
                    vAxe[vecteur] = (Point3D)pp.interprete(text, pos);

                    pos = pp.getPosition();
                }

                mr.matrice(new Matrix33(vAxe));

                ParsePoint pp = new ParsePoint();
                mr.centre((Point3D)pp.interprete(text, pos));


                ib = new InterpretesBase();
                pattern = new ArrayList<Integer>();
                pattern.add(ib.BLANK);
                pattern.add(ib.RIGHTPARENTHESIS);
                pattern.add(ib.BLANK);

                ib.compile(pattern);
                ib.read(text, pos);
                pos = ib.getPosition();

                set_mr = true;
            }

        }

        // Interprete translation
        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.AROBASE);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        if(ib.read(text, pos).get(1) instanceof InterpretesBase.CODE)
        {
            pos = ib.getPosition();
            ib = new InterpretesBase();
            pattern = new ArrayList<Integer>();
            pattern.add(ib.BLANK);
            pattern.add(ib.LEFTPARENTHESIS);
            pattern.add(ib.BLANK);
            ib.compile(pattern);
            if(ib.read(text, pos).get(1) instanceof InterpretesBase.CODE)
            {
                pos = ib.getPosition();
                ParsePoint pp = new ParsePoint();
                mt.translation((Point3D)pp.interprete(text, pos));


                ib = new InterpretesBase();
                pattern = new ArrayList<Integer>();
                pattern.add(ib.BLANK);
                pattern.add(ib.RIGHTPARENTHESIS);
                pattern.add(ib.BLANK);

                ib.compile(pattern);
                ib.read(text, pos);
                pos = ib.getPosition();

                set_mt = true;
            }

        }
        mo.modificateurs(mr, mt, mh);

        this.pos = pos;

        return mo;
    }



    public int getPosition() {
        return pos;
    }

    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    }

    


