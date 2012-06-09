/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.pushmatrix;


/**
 *
 * @author Atelier
 */
public class Camera implements Representable
{
    private Point3D camera;
    private Point3D lookat;
    private Point3D planproj;
    private Matrix33 matrice;

    public Camera(Point3D camera, Point3D lookat, Point3D planproj) {
        this.camera = camera;
        this.lookat = lookat;
        this.planproj = planproj;
    }
    public Camera(Point3D camera, Point3D lookat, double distancePlan) {
        this.camera = camera;
        this.lookat = lookat;
        this.planproj = camera.mult(distancePlan).plus(lookat.mult(1-distancePlan));
    }
    public void calculerMatrice()
    {
        Matrix33 m = new Matrix33();
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
        return matrice.mult(p.moins(camera));
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

    Point3D position() {
        return camera;
    }
}
