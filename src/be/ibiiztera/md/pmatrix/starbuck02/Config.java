/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.starbuck02;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author Manuel DAHMEN
 */
class Config implements Serializable{
    private Properties p;

    void langue(String selectedValue) {
        this.p.put("langue", selectedValue);
   }

    void save() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static class getConfig extends Config {

        public Config getConfig() {
            return new Config();
        }
    }
    public String toString()
    {
        String t = "PROPERTIES\n";
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
