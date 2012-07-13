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
package be.ibiiztera.md.pmatrix.starbuck02;

import be.ibiiztera.md.pmatrix.pushmatrix.BezierCubique;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import java.awt.Rectangle;
/**
 *
 * @author manuel
 *
 * date 31-03-2012
 *
 *
 */
public interface SelectBezierControleur {

    public void activate(BezierCubique bc);
    public void end();
    public void cancel();
    
    public void selectPoint(Rectangle r);
    public void selectPoint(int p);

    public boolean isActive();
    public boolean isDisposed();
    public boolean isPointSelected();
    
    public int whichPointSelected() throws Exception;
    public boolean movePoint(Point3D newPosition);
    public void majPoint();
}
