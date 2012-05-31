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

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import java.util.ArrayList;

public class InterpretePoint3D implements Interprete {
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }

    private InterpreteConstants c;
    private int pos;

    @Override
    public Object interprete(String point, int pos) throws InterpreteException {
        try {
            InterpretesBase ib = new InterpretesBase();
            ArrayList<Integer> pattern = new ArrayList<Integer>();
            pattern.add(ib.BLANK);
            pattern.add(ib.LEFTPARENTHESIS);
            pattern.add(ib.BLANK);
            pattern.add(ib.DECIMAL);
            pattern.add(ib.BLANK);
            pattern.add(ib.COMA);
            pattern.add(ib.BLANK);
            pattern.add(ib.DECIMAL);
            pattern.add(ib.BLANK);
            pattern.add(ib.COMA);
            pattern.add(ib.BLANK);
            pattern.add(ib.DECIMAL);
            pattern.add(ib.BLANK);
            pattern.add(ib.RIGHTPARENTHESIS);

            ib.compile(pattern);
            ArrayList<Object> os = null;
            os = ib.read(point, pos);
            this.pos = ib.getPosition();

            return new Point3D((Double) os.get(3), (Double) os.get(7),
                    (Double) os.get(11));
        } catch (NullPointerException ex1) {
            throw new InterpreteException(ex1);
        } catch (Exception ex) {
            throw new InterpreteException(ex);
        }

    }

    public static void main(String[] args) {
        InterpretePoint3D pp = new InterpretePoint3D();
        try {
            Point3D p = (Point3D) pp.interprete(" \t ( 0, 1.0, 2) \n", 0);
            System.out.println("(" + p.getX() + "," + p.getY() + "," + p.getZ()
                    + ")");
            Point3D p2 = (Point3D) pp.interprete("(0,1.0,2)", 0);
            System.out.println("(" + p2.getX() + "," + p2.getY() + "," + p2.getZ()
                    + ")");
        } catch (Exception e) {
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
