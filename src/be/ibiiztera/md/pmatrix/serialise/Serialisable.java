package be.ibiiztera.md.pmatrix.serialise;

import java.io.OutputStream;

/**
 *
 * @author Manuel DAHMEN
 */
public interface Serialisable {
    public String type();
    public int taille();
    public String serialise() throws Exception;
    public void serialise(OutputStream out) throws Exception;
    public Serialisable lecture(String s);
}
