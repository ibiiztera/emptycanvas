package be.ibiiztera.md.pmatrix.test.pushmatrix.newtest;

import be.ibiiztera.md.pmatrix.pushmatrix.Camera;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import java.io.File;
import java.util.Properties;

/**
 *
 * @author Manuel DAHMEN
 */
public interface Test {
    public Scene scene();
    public void camera(Camera c);
    public Camera camera();
    public void init();
    public void testScene();
    public void testScene(File f);
    
    public void run();
    public boolean loop();
    public boolean nextFrame();
    public void loop(boolean isLooping);
    String getTemplate();
    String applyTemplate(String template, Properties properties);
    public void publishResult();
}
