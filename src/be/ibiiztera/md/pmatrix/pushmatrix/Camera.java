/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.pushmatrix;

import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

/**
 *
 * @author Atelier
 */
public class Camera implements Representable
{
    private Point3D camera;
    private Point3D lookat;
    private Point3D planproj;
    private DoubleMatrix2D matrice;

    public Camera(Point3D camera, Point3D lookat, Point3D planproj) {
        this.camera = camera;
        this.lookat = lookat;
        this.planproj = planproj;
    }
    public Camera(Point3D camera, Point3D lookat, double distancePlan) {
        this.camera = camera;
        this.lookat = lookat;
        this.planproj = camera.mult(1-distancePlan).plus(lookat.mult(distancePlan));
    }
    public void calculerMatrice()
    {
        DoubleMatrix2D m = new DenseDoubleMatrix2D(3,3);
        for(int j=0; j<3; j++)
        m.set(j, 2, lookat.moins(camera).norme1().get(j));
        for(int j=0; j<3; j++)
        m.set(j, 0, lookat.moins(camera).norme1().prodVect(Point3D.Y).get(j));
        for(int j=0; j<3; j++)
        m.set(j, 1, lookat.moins(camera).norme1().prodVect(lookat.moins(camera).norme1().prodVect(Point3D.Y)).norme1().get(j));
        this.matrice = m;
    }
    public Point3D calculerPointDansRepere(Point3D p)
    {
        DoubleMatrix1D p2 = new DenseDoubleMatrix1D(p.moins(camera).getDoubleArray());
        DoubleMatrix1D r2 = new Algebra().mult(matrice, p2);
        Point3D ret = new Point3D(r2.get(0),r2.get(1),r2.get(2));
        return ret;
    }
    
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

    public Point3D pointFocal() {
        return planproj;
    }
}
