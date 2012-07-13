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

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBuffer;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author manuel
 */
public interface PreviewControleur extends Runnable{

    public ZBuffer zbuffer(RenderPreviewPanel vue);
    public Image preview(RenderPreviewPanel vue);

    public void HACHE();
    public void WAIT(String msg);
    public void RELACHE_HACHE();

    public boolean modifierModele(String text);
    public void assignerVue(RenderPreviewPanel rpv);

    public void definirModele(Scene scene);
    public void chargerModele(File fichier);
    public void sauvegarderModele(File fichier);

    public void modeleModifie();
    public void affichageDemarre();
    public boolean isUPTODATE();

    public String modeleTXT();


    public void besoinRafraichirAffichage(boolean b);

    public void renduFichier();

    public Scene modele();

    public Point getPoint2D(Point3D p);
    
    public BufferedImage getImage();

    public void experimentale(boolean experimental);
    
    public boolean lock(ZBuffer z);
    public boolean unlock(ZBuffer z);
}
