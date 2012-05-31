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
package be.ibiiztera.md.pmatrix.pushmatrix.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Window extends JPanel implements PictureComposant {

    private Scene scene = new Scene();
    private Scene modified;
    private View view;
    private JPanel bitmap;
    private double[][] map;
    private int viewAxe = 0;
	private BufferedImage bi;
    public void setView(View v) {
        view = v;
    }

    public Window() {
        z = ZBufferFactory.instance(1000, 1000);
        scene = new Scene();
        modified = new Scene();
        bitmap = new JPanel();
	bitmap.setMinimumSize(new Dimension(500, 500));
        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        add(bitmap, BorderLayout.CENTER);
	add(new JButton("VOID BOUTON Change couleur d'arriere plan"), BorderLayout.SOUTH);
        bitmap.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent arg0) {
                getGraphics().setColor(Color.WHITE);
                if (arg0.getX() >= 0 & arg0.getX() < 500 & arg0.getY() >= 0
                        & arg0.getY() < 500 & map != null) {
                    String s = "Z: " + map[arg0.getX()][arg0.getY()];
                    getGraphics().drawString(s, arg0.getX(), arg0.getY());
                }
                getGraphics().setColor(Color.RED);
                getGraphics().drawLine(arg0.getX() - 10, arg0.getY(),
                        arg0.getX() + 10, arg0.getY());
                getGraphics().drawLine(arg0.getX(), arg0.getY() - 10,
                        arg0.getX(), arg0.getY() + 10);
            }

            @Override
            public void mouseDragged(MouseEvent arg0) {
            }
        });
		
    }

    public void setOriginalScene(Scene s) {
        scene = s;
    }

    public void setRotationAxe(int rot) {
        this.viewAxe = rot;
    }

    private void modifier() {
        view.HACHE();
        view.WAIT("MODIFIER SCENE ROTATION -- WAITING");

        new Thread() {

            @Override
            public void run() {

                modified = new Scene();

                Iterator<Representable> it = scene.iterator();
                while (it.hasNext()) {
                    modified.add(it.next());
                }
            }
        }.start();
        view.RELACHE_HACHE();
    }
    private ZBuffer z;
    public void render() {
        modifier();
        view.HACHE();
        view.WAIT("DRAWING");
        z.suivante();
        switch (view.getRender()) {
            case 0:
                z.scene(modified);
                z.dessinerSilhouette3D();
                break;
            case 1:
                z.scene(modified);
                z.dessinerSilhouette();
                break;
            case 2:
                z.scene(modified);
                z.dessinerContours();
                break;
        }
        System.out.println(".");
        this.bi = z.image();
        view.RELACHE_HACHE();;
    }

    public class ThreadTimer extends Thread {

        boolean runningFICHIER = true;
        //private StringBuilder sb;

        public void stopT() {
            runningFICHIER = false;
        }

        public void setString(StringBuilder sb) {
            //this.sb = sb;
        }

        @Override
        public void run() {
            long time = System.currentTimeMillis();
            while (runningFICHIER) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.print("O");
                sb.append("O");
            }
            System.out.println();
            sb.append("\n");

            System.out.println("TEMPS : " + ((1.0 * System.currentTimeMillis() - time) / 1000) + " secondes ecoulees");
            sb.append("TEMPS : ").append((1.0 * System.currentTimeMillis() - time) / 1000).append(" secondes ecoulees");


        }
    }
    StringBuilder sb = new StringBuilder();

    public void renderAndSave() {
        view.HACHE();
        view.WAIT("Rendu vers fichier");
        new Thread() {

            @Override
            public void run() {
                int RESX = 2000, RESY = 2000;
                String NOMFICHIER;
                NOMFICHIER = System.getProperty("user.home") + File.separator + "renduFichier_"+(new Random().nextInt(10000)) + ".png";
                while(new File(NOMFICHIER).exists())
                    NOMFICHIER = System.getProperty("user.home") + File.separator + (new Random().nextInt(10000)) + ".png";
                ThreadTimer time = new ThreadTimer();
                time.setString(sb);
                time.start();
                System.out.println(".RENDU VERS FICHIER ---------:::" + NOMFICHIER);
                System.out.println("----------------------------->>> DEBUT\n<>");
                sb.append(".RENDU VERS FICHIER:").append(NOMFICHIER).append("\n").append("\nREsX = " + RESX + "\nReSY = " + RESY);

                System.out.println(".RENDU VERS FICHIER ETAPE 1/4:::" + NOMFICHIER);
                System.out.println("----------------------------->>> ROTATIONS ET COPIE DE LA SCENE");
                sb.append(".RENDU VERS FICHIER ETAPE 1/4\nROTATIONS ET COPIE DE LA SCENE\n");

                modifier();

                System.out.println(".RENDU VERS FICHIER ETAPE 2/4:::" + NOMFICHIER);
                System.out.println("----------------------------->>> ALLOCATION MEMOIRE");
                sb.append(".RENDU VERS FICHIER ETAPE 2/4:\nALLOCATION MEMOIRE\n");

                ZBuffer z = ZBufferFactory.instance(RESX, RESY);
                System.out.println(".RENDU VERS FICHIER ETAPE 3/4:::" + NOMFICHIER);
                System.out.println("----------------------------->>> EXECUTION DE L ALGORITHME");
                sb.append(".RENDU VERS FICHIER ETAPE 3/4:\nEXECUTION DE L ALGORITHME\n");
                z.scene(modified);
                z.dessinerSilhouette3D();
                try {
                    System.out.println(".RENDU VERS FICHIER ETAPE 4/4:::" + NOMFICHIER);
                    System.out.println("----------------------------->>> ECRITURE DU FICHIER");
                    sb.append(".RENDU VERS FICHIER ETAPE 4/4:\nECRITURE DU FICHIER\n");

                    ImageIO.write((BufferedImage) z.image(), "png", new File(NOMFICHIER));
                } catch (IOException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(".RENDU VERS FICHIER ETAPE ---:::" + NOMFICHIER);
                System.out.println("----------------------------->>>\nFIN DU RENDU []");
                sb.append("----------------------------->>> FIN DU RENDU []" + "\n");

                //map = z.image();

                time.stopT();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                }
                JFrame f = new JFrame("Rendu fichier");
                f.setContentPane(new JTextArea(sb.toString()));
                f.pack();
                f.setVisible(true);

            }
        }.start();

        view.RELACHE_HACHE();
    }

    public void dump() {
    }

    public void run() {
        Thread preview = new Thread()
		{
			public void run()
			{
				bi = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
				while(true)
				{
					bitmap.getGraphics().drawImage(bi, 0, 0, 500, 500, null);
					try
					{
						Thread.sleep(10);
					}
					catch(InterruptedException ex)
					{
						break;
					}
				}
			}
		};
		preview.start();
		new Thread() {

            @Override
            public void run() {
                while (true) {
                    render();
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public Scene getScene() {
        return scene;
    }

    void newScene() {
        scene = new Scene();
    }
}
