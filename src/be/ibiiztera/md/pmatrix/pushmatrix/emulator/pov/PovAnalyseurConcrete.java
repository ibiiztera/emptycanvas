/*

    Copyright (C) 2010-2012  DAHMEN, Manuel, Daniel

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

*/
package be.ibiiztera.md.pmatrix.pushmatrix.emulator.pov;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import java.io.File;
import java.util.HashMap;

/**
 *
 * @author Manuel DAHMEN
 */
public class PovAnalyseurConcrete implements PovAnalyseur {

    private HashMap<String, Object> objetsDéclarés;
    private String rep;
    
    @Override
    public String povVersion() {
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }
    
    protected String suppressionDesCommentaires(String pCode) {
        String MyCommentsRegex = "(?://.*)|(/\\*(?:.|[\\n\\r])*?\\*/)";
        return pCode.replaceAll(MyCommentsRegex, " ");
    }
    
    @Override
    public void analyse(File povfile, Scene scene) {
        this.rep = povfile.getParent();
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }
    
    @Override
    public void analyse(String povstring, Scene scene) {
        int position = 0;

        // Suppression des commentaires
        povstring = suppressionDesCommentaires(povstring);
        // Eliminer les blancs
        povstring = suppressionDesBlancs(povstring);
        // Lecture du fichier
        
        while (position < povstring.length()) {
            String ligne = povstring.substring(position, povstring.indexOf(Character.LINE_SEPARATOR, position));
            String trim = ligne.trim();
            // Préprocesseur
            if (trim.startsWith("#include")) {
                analyse(new File(rep + trim.substring(ligne.indexOf("\"") + 1, trim.lastIndexOf("\""))), scene);                
            } else if (trim.startsWith("#declare")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#ifndef")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#ifdef")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#version")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#version")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#end")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#macro")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#local")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#switch")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#range")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#if")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            } else if (trim.startsWith("#else")) {
                throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
                
            }            
            
            position += ligne.length();
        }
        throw new UnsupportedOperationException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/emulator/pov/Bundle").getString("NOT SUPPORTED YET"));
    }
    
    protected String suppressionDesBlancs(String povstring) {
        return povstring;
    }
}
