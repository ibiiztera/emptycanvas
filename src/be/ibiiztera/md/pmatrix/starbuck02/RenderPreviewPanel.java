package be.ibiiztera.md.pmatrix.starbuck02;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBuffer;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBufferFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RenderPreviewPanel extends JPanel {

    private Scene scene = new Scene();
    private Scene modified;
    private PreviewControleur previewController;
    private double[][] map;
    private BufferedImage bi;

    public void setView(PreviewControleur v) {
        previewController = v;
    }

    public RenderPreviewPanel() {
        setMinimumSize(new Dimension(500, 500));
        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        //add(new JButton("VOID BOUTON Change couleur d'arriere plan"), BorderLayout.SOUTH);
        addMouseMotionListener(new MouseMotionListener() {

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

    public void render() {
        BufferedImage i = (BufferedImage) previewController.preview();
        if(i!=null)
            this.bi = i;
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
                    Logger.getLogger(RenderPreviewPanel.class.getName()).log(Level.SEVERE, null, ex);
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
        previewController.HACHE();
        previewController.WAIT("Rendu vers fichier");
        new Thread() {

            @Override
            public void run() {
                int RESX = 2000, RESY = 2000;
                String NOMFICHIER;
                NOMFICHIER = System.getProperty("user.home") + File.separator + "renduFichier_" + (new Random().nextInt(10000)) + ".png";
                while (new File(NOMFICHIER).exists()) {
                    NOMFICHIER = System.getProperty("user.home") + File.separator + (new Random().nextInt(10000)) + ".png";
                }
                ThreadTimer time = new ThreadTimer();
                time.setString(sb);
                time.start();
                System.out.println(".RENDU VERS FICHIER ---------:::" + NOMFICHIER);
                System.out.println("----------------------------->>> DEBUT\n<>");
                sb.append(".RENDU VERS FICHIER:").append(NOMFICHIER).append("\n").append("\nREsX = " + RESX + "\nReSY = " + RESY);
                System.out.println(".RENDU VERS FICHIER ETAPE 1/4:::" + NOMFICHIER);
                System.out.println("----------------------------->>> ROTATIONS ET COPIE DE LA SCENE");
                sb.append(".RENDU VERS FICHIER ETAPE 1/4\nROTATIONS ET COPIE DE LA SCENE\n");
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
                    Logger.getLogger(RenderPreviewPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(".RENDU VERS FICHIER ETAPE ---:::" + NOMFICHIER);
                System.out.println("----------------------------->>>\nFIN DU RENDU []");
                sb.append("----------------------------->>> FIN DU RENDU []" + "\n");

                //map = z.image();

                time.stopT();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RenderPreviewPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                JFrame f = new JFrame("Rendu fichier");
                f.setContentPane(new JTextArea(sb.toString()));
                f.pack();
                f.setVisible(true);

            }
        }.start();

        previewController.RELACHE_HACHE();
    }

    public void dump() {
    }

    public void run() {
        setSize(getParent().getSize());
        Thread preview = new Thread() {

            @Override
            public void run() {
                bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                while (true) {
                    if(bi!=null)
                        getGraphics().drawImage(bi, 0, 0, getWidth(), getHeight(), null);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
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
