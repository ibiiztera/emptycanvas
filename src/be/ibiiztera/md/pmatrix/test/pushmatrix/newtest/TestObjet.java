/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
       properties.put("name", this.getClass().getCanonicalName()); 
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
