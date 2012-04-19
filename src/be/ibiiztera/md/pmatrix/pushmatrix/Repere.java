/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.pushmatrix;

import be.ibiiztera.md.pmatrix.pushmatrix.math.matrix.Matrix;

/**
 *
 * @author Manuel
 */
public class Repere {
    private Point3D translation;
    private Matrix rotation;
	public Point3D getTranslation() {
		return translation;
	}
	public void setTranslation(Point3D translation) {
		this.translation = translation;
	}
	public Matrix getRotation() {
		return rotation;
	}
	public void setRotation(Matrix rotation) {
		this.rotation = rotation;
	}
}
