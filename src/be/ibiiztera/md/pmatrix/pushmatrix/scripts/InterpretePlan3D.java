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

import be.ibiiztera.md.pmatrix.pushmatrix.generator.Plan3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author DAHMEN Manuel
 *
 * dev
 *
 * @date 22-mars-2012
 */
public class InterpretePlan3D implements Interprete {
    private int position;
    private String répertoire;

    public Object interprete(String text, int pos) throws InterpreteException {
        try {
            Plan3D plan = new Plan3D();
            InterpretesBase ib = null;
            ArrayList<Integer> pattern;
            InterpretePoint3D pp;
            InterpreteNomFichier is;
            ib = new InterpretesBase();
            pattern = new ArrayList<Integer>();
            pattern.add(ib.BLANK);
            pattern.add(ib.LEFTPARENTHESIS);
            pattern.add(ib.BLANK);
            ib.compile(pattern);
            ib.read(text, pos);
            pos = ib.getPosition();
            pp = new InterpretePoint3D();
            plan.pointOrigine((Point3D) pp.interprete(text, pos));
            pos = pp.getPosition();
            pp = new InterpretePoint3D();
            plan.pointXExtremite((Point3D) pp.interprete(text, pos));
            pos = pp.getPosition();
            pp = new InterpretePoint3D();
            plan.pointYExtremite((Point3D) pp.interprete(text, pos));
            pos = pp.getPosition();
            is = new InterpreteNomFichier();
            is.setRépertoire(répertoire);
            File f = (File) is.interprete(text, pos);
            plan.map(ImageIO.read(f), f.getAbsolutePath());
            pos = is.getPosition();
            this.position = pos;
            return plan;
        } catch (IOException ex) {
            Logger.getLogger(InterpretePlan3D.class.getName()).log(Level.SEVERE, null, ex);
            throw new InterpreteException("Fichier non trouvé . ");
        }
    }

    public int getPosition() {
        return position;
    }

    public InterpreteConstants constant() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setConstant(InterpreteConstants c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
    
}
