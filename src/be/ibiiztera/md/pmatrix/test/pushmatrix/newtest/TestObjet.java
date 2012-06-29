package be.ibiiztera.md.pmatrix.test.pushmatrix.newtest;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.ExtensionFichierIncorrecteException;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.VersionNonSupportéeException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestObjet implements Test {

    private int version = 1;
    private String template = "";
    private String type = "png";
    private String filenameZIP = "tests";
    private String fileextZIP = "diapo";
    private File file = null;
    private int resx = 1600;
    private int resy = 1200;
    Properties properties = new Properties();
    private File dir = null;
    protected Scene scene;
    protected String description;
    private Camera c = new Camera(new Point3D(0, 0, -10), Point3D.O0, 0.1);
    private BufferedImage ri;
    private String filename = "frame";
    private String fileExtension;
    private boolean publish = true;
    private boolean isometrique = false;
    private boolean loop = false;
    private int maxFrames = 5000;
    private String text = "scene";
    private File fileScene;
    private boolean saveTxt = true;
    private String binaryExtension = "bmood";
    public int getMaxFrames() {
        return maxFrames;
    }

    public void setMaxFrames(int maxFrames) {
        this.maxFrames = maxFrames;
    }

    public void isometrique(boolean isISO) {
        isometrique = isISO;
    }

    @Override
    public Scene scene() {
        return scene;
    }

    public void setFilename(String fn) {
        this.filename = fn;
    }

    public void setFileExtesion(String ext) {
        this.fileExtension = ext;
    }

    @Override
    public void init() {
        ResourceBundle bundle1 = ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/test/pushmatrix/newtest/Bundle");

        File dirl = new File(bundle1.getString("testpath"));
        if (!dirl.exists()) {
            dirl.mkdirs();
        }
        this.dir = new File(dirl.getAbsolutePath() + File.separator + this.getClass().getName());
        if (!this.dir.exists()) {
            this.dir.mkdirs();
        }
		else
		{
			System.err.println("Le chemin existe : fin du test : ECHEC");
			System.exit(1);
		}

        if (filename == null) {
            filename = bundle1.getString("src");
        }
        if (fileExtension == null) {
            fileExtension = bundle1.getString("type");
        }
        
        template = bundle1.getString("template");

        properties.put("name", this.getClass().getCanonicalName());
        properties.put("version", version);

        resx = Integer.parseInt(bundle1.getString("resx"));
        resy = Integer.parseInt(bundle1.getString("resy"));
        scene = new Scene();
        
        binaryExtension = bundle1.getString("binaryExtension");
    }

    public void setResx(int resx) {
        this.resx = resx;
    }

    public void setResy(int resy) {
        this.resy = resy;
    }

    public int getResx() {
        return resx;
    }

    public int getResy() {
        return resy;
    }

    /**
     * Definir la scène scene().add(*)
     */
    @Override
    public void testScene() {
    }

    @Override
    public void testScene(File f) {
        if (f.getAbsolutePath().endsWith("mood") || f.getAbsolutePath().endsWith("moo")) {
            new Loader().loadIF(f, scene);
        } else {
            System.err.println("Erreur: extension incorrecte");
            System.exit(1);

        }
    }

    public void publishResult(boolean publish) {
        this.publish = publish;
    }

    @Override
    public void publishResult() {
        if (publish) {
            new ShowTestResult(ri).run();
        }
    }
    protected int frame = 0;

    @Override
    public void run() {
        init();
        while (nextFrame()) {
            testScene();
            try {
                ZBuffer z = ZBufferFactory.instance(resx, resy);
                z.scene(scene);
                if (isometrique) {
                    z.isometrique();
                } else {
                    z.perspective();
                }
                if (c != null) {
                    z.camera(c);
                    c.calculerMatrice();
                }
                
                if(z instanceof ZBufferImpl)
                    ((ZBufferImpl)z).setColoration(true);

                z.dessinerSilhouette3D();

                ri = z.image();
                FileOutputStream fileOutputStream = new FileOutputStream(fileScene);
                fileOutputStream.write(scene.toString().getBytes());
                fileOutputStream.close();
                
                
                Graphics g = ri.getGraphics();
                g.setColor(Color.black);
                g.drawString(description, 0, 1100);
                boolean write = ImageIO.write(((RenderedImage) ri), type, file);
                System.out.println(file.getAbsolutePath());
                System.out.println(scene.toString());


            } catch (IOException ex) {
                Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }

        if(isSaveBMood())
        {
            try {
                File foutm = new File(this.dir.getAbsolutePath() + File.separator + filename + ".bmood");
                new Loader().saveBin(foutm, scene);
            } catch (VersionNonSupportéeException ex) {
                Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExtensionFichierIncorrecteException ex) {
                Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        publishResult();
    }

    @Override
    public String getTemplate() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return "";
    }

    @Override
    public String applyTemplate(String template, Properties properties) {
        return "";
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public File getFile() {
        return file;
    }

    public void scene(Scene load) {
        scene = load;
    }

    @Override
    public void camera(Camera c) {
        this.c = c;
    }

    @Override
    public Camera camera() {
        return c;
    }

    public void description(String d) {
        description = d;
    }

    @Override
    public boolean loop() {
        return loop;
    }

    @Override
    public void loop(boolean isLooping) {
        this.loop = isLooping;
    }

    @Override
    public boolean nextFrame() {
        frame++;
        
        file = new File(this.dir.getAbsolutePath() + File.separator + filename+(1000000+frame) + "." + fileExtension);
        fileScene = new File(this.dir.getAbsolutePath() + File.separator + text+(1000000+frame) + "." + binaryExtension);

        
        if (loop() && frame > maxFrames || (frame>1 && !loop())) {
            return false;
        }
        return true;
    }
    
    public void saveBMood(boolean b) {
        saveTxt = b;
    }

    private boolean isSaveBMood() {
        return saveTxt;
    }

}
