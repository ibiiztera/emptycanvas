package be.ibiiztera.md.pmatrix.pushmatrix.emulator.pov;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import java.io.File;
import java.util.HashMap;

/**
 *
 * @author Manuel DAHMEN
 */
public class PovAnalyseurConcrete implements PovAnalyseur{
    private HashMap<String, Object> objets;
    @Override
    public String povVersion() {
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }

    @Override
    public void analyse(File povfile) {
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }

    @Override
    public void analyse(String povstring, Scene scene) {
        int position = 0;
        
        // Suppression des commentaires
        
        // Lecture du fichier
        
        while(position < povstring.length())
        {
            String ligne = povstring.substring(position, povstring.indexOf(Character.LINE_SEPARATOR, position));
            if(ligne.startsWith("#include"))
            {
               analyse(new File(ligne.substring(ligne.indexOf("\"")+1, ligne.lastIndexOf("\""))));        
            }
            else if(ligne.startsWith("#declare"))
            {
                
            }
            
            position += ligne.length();
        }
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }
    
}
