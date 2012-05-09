package be.ibiiztera.md.pmatrix.test.pushmatrix.newtest;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import java.io.File;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestObjet implements Test{
    private int version = 1;
    private String template ="";
    
    Properties properties = new Properties();
    private File dir = null;
    private Scene scene;
    @Override
    public Scene scene() {
        return scene;
    }

    @Override
    public void init() {
        ResourceBundle bundle1 = ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/test/pushmatrix/newtest/Bundle.properties");
        
        File dir = new File(bundle1.getString("testpath"));
        if(!dir.exists())
            dir.mkdirs();
        this.dir = new File(dir.getAbsolutePath()+File.separator+this.getClass().getName());
        if(!this.dir.exists())
            this.dir.mkdirs();
       
        template = bundle1.getString("template");
        
        properties.put("name", this.getClass().getCanonicalName());
        properties.put("version", version);
        
    }

    @Override
    public void testScene() {
        throw new UnsupportedOperationException("Not supported yet.");
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
