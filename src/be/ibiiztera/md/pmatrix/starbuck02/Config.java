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

package be.ibiiztera.md.pmatrix.starbuck02;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel DAHMEN
 */
class Config implements Serializable {

    private Properties p = new Properties();

    void langue(String selectedValue) {
        this.p.put(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/starbuck02/Bundle").getString("LANGUE"), selectedValue);
    }

    void save() {
        try {
            String appName = java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/starbuck02/Bundle").getString("APPNAME");

            File configFile = new File(System.getProperty("user.home") + File.separator + "." + appName);
            FileOutputStream os = null;
            try {
                os = new FileOutputStream(configFile);
                p.store(os, "");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() {
        try {
            String appName = java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/starbuck02/Bundle").getString("APPNAME");

            File configFile = new File(System.getProperty("user.home") + File.separator + "." + appName);
            if (configFile.exists()) {
                FileInputStream is = new FileInputStream(configFile);
                p.load(is);
            }
            else
                System.out.println("Fichier non trouvé: " + configFile.toString());

        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void saveProperty(String langue, String langue0) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static Config getConfig() {

        return new Config();
    }

    public String toString() {
        String t = "";
        Set<Object> k = p.keySet();
        Iterator<Object> i = k.iterator();
        while (i.hasNext()) {
            Object key = i.next();
            t += key.toString() + "=" + p.get(key).toString();
        }

        return t;
    }

    public Object getProperty(Object key) {
        return p.get(key);
    }
}
