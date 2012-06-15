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
package be.ibiiztera.md.pmatrix.pushmatrix;


/**
 *
 * @author Atelier
 */
public class Camera extends CameraBox implements Representable
{
    private Point3D camera;
    private Point3D lookat;
    private Point3D planproj;
    private Matrix33 matrice;

    public Camera() {
        this.camera = new Point3D(0,0,-100);
        this.lookat = Point3D.O0;
        this.planproj = new Point3D(0,0,-99);
    }
    
    
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

    public Point3D position() {
        return camera;
    }
	
	public String toString()
	{
		return "camera (\n\teye : " +camera.toString() + "\n\tlookat" + lookat.toString()+"\n)";
	}
}
