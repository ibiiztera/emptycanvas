package be.ibiiztera.md.pmatrix.starbuck02;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.Image;
import java.io.File;

/**
 *
 * @author manuel
 */
public interface PreviewControleur {

    public ZBuffer zbuffer();
    public Image preview();

    public void HACHE();
    public void WAIT(String msg);
    public void RELACHE_HACHE();

    public boolean modifierModele(String text);
    public void asssignerVue(RenderPreviewPanel rpv);

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


}
