/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.ibiiztera.md.pmatrix.starbuck02;

import be.ibiiztera.md.pmatrix.pushmatrix.BezierCubique;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import java.awt.Rectangle;

/**
 *
 * @author DAHMEN Manuel
 *
 * dev
 *
 * @date 31-mars-2012
 */
public class EditerBezierCubique implements SelectBezierControleur {
    public static final int STATE_NULL = -1;
    public static final int STATE_ACTIVE = 0;
    public static final int STATE_POINTSELECTED = 1;
    public static final int STATE_POINTINMOVE = 2;
    public static final int STATE_POINTMOVED = 3;
    public static final int STATE_OK = 4;
    public static final int STATE_CANCEL = 5;
    public static final int STATE_DISPOSED = 6;


    private BezierCubique original;
    private Point3D[] transformee = new Point3D[4];
    private int state = STATE_NULL;
    private int selected = 0;
    public void activate(BezierCubique bc) {
        original = bc;
        for(int i=0; i<4;i++)
        {
            transformee[i] = bc.get(i);
        }
        state = STATE_ACTIVE;
    }

    public void end() {
        state = STATE_OK;
    }

    public void cancel() {
        state = STATE_CANCEL;
    }

    public void selectPoint(Rectangle r) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void selectPoint(int p) {
        selected = p;
    }

    public boolean isActive() {
        return state >=STATE_ACTIVE && state<STATE_OK;
    }

    public boolean isDisposed() {
        return state==STATE_OK||state==STATE_CANCEL||state==STATE_DISPOSED;
    }

    public boolean isPointSelected() {
        return state==STATE_POINTSELECTED||state==STATE_POINTMOVED||state==STATE_POINTINMOVE;
    }

    public int whichPointSelected() throws Exception {
        if(isPointSelected())
            return selected;
        else
            throw new Exception("NO POINT SELECTED");
    }

    public boolean movePoint(Point3D newPosition) {
        transformee[selected] = newPosition;
        return true;
        /// ZOOM ADAPTATION
    }

    public void majPoint() {
        throw new UnsupportedOperationException("Not supported yet.");
        /// METTRE A JOUR MODELE
    }

    
}
