/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.pushmatrix.newobjets;

import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import java.util.HashMap;

/**
 *
 * @author Atelier
 */
public interface BaseObjet {
    /***
     * 
     * @param parametres paramètres de l'objet
     * @return objet instancié
     */
    public BaseObjet instancier(HashMap<String, BaseObjet> parametres);
    /***
     * 
     * @return map de paramètres avec description textuelle
     */
    public HashMap<String, String> documentationParametres();
    /***
     * 
     * @param tcolor Couleur ou texture
     * @return objet
     */
    public BaseObjet colorer(TColor tcolor);
    /***
     * 
     * @param position position (Point3D)
     * @return objet
     */
    public BaseObjet positionner(Point3D position);
    /***
     * 
     * @param matriceRotation matrice de rotation
     * @return objet
     */
    public BaseObjet orienter(DenseDoubleMatrix2D matriceRotation);
    
    public TColor texture();
    
}
