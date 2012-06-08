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
 * @author Manuel DAHMEN
 * @date
 */
public class CameraBox {
    private double angleX;
    private double angleY;
    public static final int PERSPECTIVE_ISOMETRIQUE = 1;
    public static final int PERSPECTIVE_POINTDEFUITE = 1;
    private int type = PERSPECTIVE_POINTDEFUITE;
    
    public void viserObjet(Representable r)
    {
        throw new UnsupportedOperationException("Non supportée");
    }
    public double angleX()
    {
        return angleX;
    }
    public double angleY()
    {
    return angleY;
    }
    public void angleXY(double angleX, double angleY)
    {
    this.angleX = angleX;
    this.angleY = angleY;
    }
    public void angleXr(double angleX, double ratioXY)
    {
    this.angleX = angleX;
    this.angleY = angleX/ratioXY;
    }
    public void angleYr(double angleY, double ratioXY)
    {
    this.angleY = angleY;
    this.angleX = angleY*ratioXY;
    }
            
    public void perspectiveIsometrique()
    {
        this.type = PERSPECTIVE_ISOMETRIQUE;
    }
    public void perspectivePointDeFuite()
    {
    this.type = PERSPECTIVE_POINTDEFUITE;
    }
    public int type()
    {
        return type;
    }
}
