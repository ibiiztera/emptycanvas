package be.ibiiztera.md.pmatrix.test.pushmatrix.newtest;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import java.util.Properties;

/**
 *
 * @author Manuel DAHMEN
 */
public interface Test {
    public Scene scene();
    
    public void init();
    public void testScene();
    
    public void run();
    
    String getTemplate();
    String applyTemplate(String template, Properties properties);
    public void publishResult();
}
