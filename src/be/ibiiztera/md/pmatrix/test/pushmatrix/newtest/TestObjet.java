package be.ibiiztera.md.pmatrix.test.pushmatrix.newtest;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBuffer;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBufferFactory;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBufferImpl;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestObjet implements Test{
    private int version = 1;
    private String template ="";
    private String type = "png";
    private File file = null;
    private int resx = 1600;
    private int resy = 1200;
    Properties properties = new Properties();
    private File dir = null;
    protected Scene scene;
    protected String description;
    @Override
    public Scene scene() {
        return scene;
    }

    @Override
    public void init() {
        ResourceBundle bundle1 = ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/test/pushmatrix/newtest/Bundle");
        
        File dir = new File(bundle1.getString("testpath"));
        if(!dir.exists())
            dir.mkdirs();
        this.dir = new File(dir.getAbsolutePath()+File.separator+this.getClass().getName());
        if(!this.dir.exists())
            this.dir.mkdirs();
       
        file = new File(this.dir.getAbsolutePath()+File.separator+bundle1.getString("src")+"."+bundle1.getString("type"));
        
        template = bundle1.getString("template");
        
        properties.put("name", this.getClass().getCanonicalName());
        properties.put("version", version);
        
        resx = Integer.parseInt(bundle1.getString("resx"));
        resy = Integer.parseInt(bundle1.getString("resy")); 
        scene = new Scene();

    }

    @Override
    public void testScene() {
    }

    @Override
    public void publishResult() 
    {
        applyTemplate(getTemplate(), properties);
        
    }

    @Override
    public void run() {
        init();
        testScene();

        try {
            ZBuffer z = ZBufferFactory.instance(resx, resy);
            z.scene(scene);
            z.dessinerSilhouette3D();
            
            
            BufferedImage ri = z.image();
            Graphics g = ri.getGraphics();
            g.setColor(Color.black);
            g.drawString(description, 0, 1100);
            ImageIO.write(((RenderedImage)ri), type, file);
            System.out.println(file.getAbsolutePath());
            System.out.println(scene.toString());
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(TestObjet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        publishResult();
    }

    @Override
    public String getTemplate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String applyTemplate(String template, Properties properties) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
