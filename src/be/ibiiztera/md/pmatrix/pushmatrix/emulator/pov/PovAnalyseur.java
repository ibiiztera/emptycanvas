package be.ibiiztera.md.pmatrix.pushmatrix.emulator.pov;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import java.io.File;

/**
 *
 * @author Manuel DAHMEN
 */
public interface PovAnalyseur {
    public String povVersion();
    public void analyse(File povfile, Scene scene);
    public void analyse(String povstring, Scene scene);
}
