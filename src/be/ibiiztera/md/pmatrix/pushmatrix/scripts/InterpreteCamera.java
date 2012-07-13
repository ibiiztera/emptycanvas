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
package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.Camera;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel DAHMEN @date
 */
public class InterpreteCamera implements Interprete {

    private int pos;

    @Override
    public void setRépertoire(String r) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object interprete(String text, int pos) throws InterpreteException {
        Camera camera = new Camera();

        InterpretesBase ib = null;
        ArrayList<Integer> pattern = null;

        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.LEFTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();

        InterpretePoint3D pp = new InterpretePoint3D();
        Point3D cpos = (Point3D) pp.interprete(text, pos);
        pos = pp.getPosition();

        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();


        pp = new InterpretePoint3D();
        Point3D lookat = (Point3D) pp.interprete(text, pos);
        pos = pp.getPosition();

        double ax;
        double ay;
        
        
        try {
            ib = new InterpretesBase();
            pattern = new ArrayList<Integer>();
            pattern.add(ib.BLANK);
            pattern.add(ib.DECIMAL);
            pattern.add(ib.BLANK);
            pattern.add(ib.DECIMAL);
            pattern.add(ib.BLANK);
            ib.compile(pattern);
            ArrayList<Object> a = ib.read(text, pos);

            ax = (Double) a.get(1);
            ay = (Double) a.get(3);
            
            camera = new Camera(cpos, lookat);
            camera.angleXY(ax, ay);
            pos = ib.getPosition();
        } catch (InterpreteException ex) {
            camera = new Camera(cpos, lookat);
        }


        ib = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        pattern.add(ib.RIGHTPARENTHESIS);
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        ib.read(text, pos);
        pos = ib.getPosition();
        
        this.pos = pos;
        return camera;
    }

    @Override
    public int getPosition() {
        return pos;
    }

    @Override
    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        try {
            String str = "( (0.0, 0.0E-20, -10) (0.0, 0.0, 0.0) )\n\n"
                +"( (0.0, 0.0E-20, -10) (0.0, 0.0, 0.0) )";
            InterpreteCamera ic = new InterpreteCamera();

            Camera c = (Camera) ic.interprete(str, 0);
            c = (Camera) ic.interprete(str, ic.getPosition());

            System.out.println(c.toString() 
                    + "\n\nPOS = " +ic.getPosition());
        } catch (InterpreteException ex) {
            Logger.getLogger(InterpreteCamera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
