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

package ibiztetra20;

import be.ibiiztera.md.pmatrix.pushmatrix.ConsoleMain;
import be.ibiiztera.md.pmatrix.pushmatrix.FormatSource;
import be.ibiiztera.md.pmatrix.pushmatrix.gui.View;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    private static int resx = 320, resy = 200;
    private static String imageType= "png";
    public static void main(String[] args) {
        if (args.length > 1) {
            boolean sub = false;
            String dir = System.getProperty("user.home")
                    + File.separator + "PMMATRIX.OUTPUT"
                    + File.separator + "html" + File.separator;
            int index = 0;
            while (index < args.length) {
                if (args[index].equals("--liste")) {
                    liste(args[index + 1], args[index+2]);
                    index+=2;
                }
                if (args[index].equals("--subdirectory")) {
                    dir += args[index + 1] + File.separator;
                    new File(dir).mkdirs();
                    index += 2;
                    sub = true;
                } else if (args[index].equals("--png")) {
                    imageType = "png";
                    index += 1;
                } else if (args[index].equals("--jpg")) {
                    imageType = "jpg";
                    index += 1;
                } else if (args[index].equals("--jpeg")) {
                    imageType = "jpg";
                    index += 1;
                } else if (args[index].equals("--resx")) {
                    resx = Integer.parseInt(args[index + 1]);
                    index += 2;
                } else if (args[index].equals("--resy")) {
                    resy = Integer.parseInt(args[index + 1]);
                    index += 2;
                } else if (args[index].equals("--html")) {
                    String png = (sub ? dir : "") + args[index + 2];
                    String html = (sub ? dir : "") + args[index + 2].substring(0, args[index + 2].indexOf(".")) + ".html";
                    ConsoleMain.display(resx, resy,imageType,
                            new File(args[index + 1]), new File(png));
                    FormatSource.format(
                            new File(args[index + 1]), new File(html), new File(png));
                    index += 3;
                } else {
                    System.err.println("Rien a faire");
                    return;
                }
            }
        } else {
            View v = new View();
            v.setVisible(true);
            v.load();
            v.draw();
        }
    }

    private static void liste(String directory_in, String directory_out) {
        FileInputStream fis = null;
        ArrayList<File> htmls = new ArrayList<File>();
        if(new File(directory_in).isDirectory())
        {
            File[] fichiers = new File(directory_in).listFiles();
            int index = 0;
            while(index<fichiers.length)
            {
                File f = fichiers[index];
                if(f.getName().endsWith(".txt"))
                {
                    String out_nom = f.getName().substring(0, f.getName().lastIndexOf("."));

                    File jpg = new File(directory_out+File.separator
                            +out_nom+File.separator+out_nom+"."+imageType);
                    File html = new File(directory_out+File.separator
                            +out_nom+File.separator+out_nom+".html");
                    File txt_cp = new File(directory_out+File.separator
                            +out_nom+File.separator+out_nom+".txt");
                    htmls.add(html);

                    new File(directory_out+File.separator+out_nom).mkdirs();

                   ConsoleMain.display(resx, resy, imageType,f, jpg);
                    FormatSource.format(f, html, jpg);

                    copyFile(f, txt_cp);
                }
                System.gc();
                index++;
            }
       }
        FormatSource.index(htmls, directory_out);
    }

    public static boolean copyFile(File in, File out)
    {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(out);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        byte [] buffer = new byte[1024];
        int read = 0;
        try {
            while ((read = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, read);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            fis.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}


