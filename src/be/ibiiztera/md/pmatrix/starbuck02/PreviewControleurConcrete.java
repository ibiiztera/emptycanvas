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

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import be.ibiiztera.md.pmatrix.pushmatrix.*;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;
import be.ibiiztera.md.pmatrix.pushmatrix.ui.ModeleIO;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author manuel
 */
public class PreviewControleurConcrete implements PreviewControleur {

    private Scene scene;
    private int enAttente = 0;
    private int traite = 0;
    private int idW = 0;
    private ArrayList<Integer> file = new ArrayList<Integer>();
    private long tempsMOD = 1;
    private long tempsAFF = 0;
    private boolean besoinRA = true;
    private ZBuffer z;
    private boolean libere = false;
    private BufferedImage image;
    private boolean experimental;
    private boolean zbuffer_colorationActive;

    public PreviewControleurConcrete() {
    }

    public void assignerModele(Scene s) {
        scene = s;
        modeleModifie();
    }

    public void enregistrerVue(RenderPreviewPanel rvp) {
        //this.vue = rvp;
    }

    @Override
    public ZBuffer zbuffer(RenderPreviewPanel vue) {
        ZBuffer zz = ZBufferFactory.instance(vue.getWidth(), vue.getHeight());
        if (zz instanceof ZBufferImpl && experimental && zbuffer_colorationActive) {
            ((ZBufferImpl) zz).setColoration(true);
        }
        return zz;
    }

    /*
     * public boolean modifierModele(Scene scene, ViewAction act) { HACHE();
     * WAIT("MODIFIER MODELE"); // Modifier scene... modeleModifie();
     * RELACHE_HACHE(); return false; }
     */
    @Override
    public Image preview(RenderPreviewPanel vue) {
        if (scene == null) {
            scene = new Scene();
        }
        if (vue != null && (!isUPTODATE() || besoinRA)) {
            z = zbuffer(vue);
            if (z.lock()) {
                HACHE();
                WAIT("DESSINE SCENE");
                affichageDemarre();
                besoinRafraichirAffichage(false);
                if(experimental&&z instanceof ZBufferImpl)
                    ((ZBufferImpl)z).setColoration(zbuffer_colorationActive);
                
                z.scene(scene);
                z.suivante();
                z.dessinerSilhouette3D();
                RELACHE_HACHE();
                Image i = z.image();
                if(z.unlock())
                    return i;
                else
                    System.exit(-1);

            }
        }
        return null;
    }

    @Override
    public void HACHE() {
    }

    public synchronized boolean librePour(int id) {
        if (file.isEmpty()) {
            return true;
        }
        if (file.size() == 1) {
            file.remove(0);
            return true;
        }
        int idCurr = file.get(0);
        if (id == idCurr) {
            return true;
        } else {
            return false;
        }


    }

    @Override
    public void WAIT(String msg) {
        System.out.println("WAITING ...");
        /*
         * enAttente++; idW++; int id = idW; file.add(idW); while
         * (!librePour(id)) { try { Thread.sleep(100); } catch
         * (InterruptedException e) { e.printStackTrace(); }
         * System.out.println("\t" + msg + "\n\t\tNOMBRE D'OPERATIONS EN
         * ATTENTE: " + enAttente + "\n\t\tNOMBRE D'OPERATIONS EN COURS : " +
         * traite); } libere = false; traite++; enAttente--;
         * System.out.println("\t\tGO!!");
         *
         */
    }

    @Override
    public void RELACHE_HACHE() {
        traite--;
    }

    @Override
    public void assignerVue(RenderPreviewPanel rpv) {
        //this.vue = rpv;
    }

    @Override
    public void definirModele(Scene scene) {
        this.scene = scene;
        modeleModifie();
        besoinRafraichirAffichage(true);
    }
    private String nomFichier = "scene";

    @Override
    public void chargerModele(File fichier) {
        this.nomFichier = fichier.getName();
        HACHE();
        WAIT("CHARGER FICHIER");

        try {
            Scene sceneTMPREF = new Scene();
            new Loader().loadIF(fichier, sceneTMPREF);
            scene = sceneTMPREF;
        } catch (Exception e) {
            System.out.println("ERREUR CHARGER FICHIER (TXT)");
        }
        RELACHE_HACHE();
        modeleModifie();
        besoinRafraichirAffichage(true);
    }

    @Override
    public void sauvegarderModele(File fichier) {
        HACHE();
        WAIT("SAUVEGARDE");

        try {
            ModeleIO.sauvergarderTXT(scene, fichier);
        } catch (Exception e) {
            System.out.println("ERREUR SAUVEGARDE FICHIER (TXT)");
        }
        RELACHE_HACHE();
    }

    @Override
    public void modeleModifie() {
        this.tempsMOD = System.currentTimeMillis();
    }

    @Override
    public void affichageDemarre() {
        this.tempsAFF = System.currentTimeMillis();
    }

    @Override
    public boolean isUPTODATE() {
        if (tempsMOD < tempsAFF) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void besoinRafraichirAffichage(boolean b) {
        besoinRA = b;
    }

    @Override
    public String modeleTXT() {
        return scene != null ? scene.toString() : new Scene().toString();
    }

    @Override
    public boolean modifierModele(String text) {
        Scene s2 = new Scene();
        if (new Loader().loadIF(text, s2)) {
            scene = s2;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void renduFichier() {
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("starbuck01/Bundle");
        int width = Integer.parseInt(bundle.getString("WIDTH"));
        int height = Integer.parseInt(bundle.getString("HEIGHT"));
        File f = new File(bundle.getString("results") + File.separator + nomFichier + "_" + new java.util.Random().nextInt(10000) + ".jpg");
        ZBuffer zzf = ZBufferFactory.instance(width, height);

        if (zzf.lock()) {

            zzf.scene(scene);

            if (experimental && zbuffer_colorationActive) {
                ((ZBufferImpl) zzf).setColoration(true);
            }

            zzf.suivante();
            zzf.dessinerSilhouette3D();
            Image i = zzf.image();
            zzf.unlock();
            try {
                ImageIO.write((RenderedImage) i, "jpg", f);
            } catch (IOException ex) {
                Logger.getLogger(PreviewControleurConcrete.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public Scene modele() {
        return scene;
    }

    @Override
    public Point getPoint2D(Point3D p) {
        return z.coordonneesPoint2D(p);
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void run() {
    }

    @Override
    public void experimentale(boolean experimental) {
        this.experimental = experimental;
        this.zbuffer_colorationActive = experimental;
    }

    @Override
    public boolean lock(ZBuffer z) {
        return z.lock();
    }

    @Override
    public boolean unlock(ZBuffer z) {
        return z.lock();
    }
}
