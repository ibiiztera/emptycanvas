/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.ibiiztera.md.pmatrix.pushmatrix.generator;

import be.ibiiztera.md.pmatrix.pushmatrix.BezierCubique2D;
import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;

/**
 *
 * @author DAHMEN Manuel
 *
 * dev
 *
 * @date 24-mars-2012
 */
public class SurfaceBezier extends TRIObjetGenerateurAbstract {

    BezierCubique2D bd = null;
    public SurfaceBezier(BezierCubique2D bd)
    {
        this.bd = bd;
    }
    
    @Override
    public Point3D coordPoint3D(int x, int y) {
        return bd.calculerPoint3D(1.0*x/getMaxX(), 1.0*y/getMaxY());
    }

    public String id() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
