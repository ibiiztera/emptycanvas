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
