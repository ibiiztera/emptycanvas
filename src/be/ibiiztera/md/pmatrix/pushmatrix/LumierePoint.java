package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;

/**
 *
 * @author Atelier
 */
public interface LumierePoint extends Lumiere{
    public Color getCouleur(TColor base, Point3D p, Point3D n);
}
