/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
class Config implements Serializable{
    private Properties p;

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
                os.write(toString().getBytes());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class getConfig extends Config {

        public Config getConfig() {
            return new Config();
        }
    }
    public String toString()
    {
        String t = "";
        Set<Object> k = p.keySet();
        Iterator<Object> i = k.iterator();
        while(i.hasNext())
        {
            Object key = i.next();
            t+= key.toString()+"="+p.get(key).toString();
        }
             
        return t;
    }
}
