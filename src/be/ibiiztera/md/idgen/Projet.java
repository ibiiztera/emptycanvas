/**
 * 
 */
package be.ibiiztera.md.idgen;

import java.awt.image.RenderedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 31 oct. 2011
 *
 */
public class Projet {
	private int projet_id;
	private String projet_nom;
	private ArrayList<RenderingInstance> instances;

	private Scene scene;
	private int version =1;
	/***
	 * EX "c:\Users\Mary\PMMATRIX.DATA\1.6\PROJET_NOM\1.1\"
	 * Textures
	 * Scenes
	 * Output\\FRAMES\\RenderedID\\
	 * Output\\MOVIES\\RenderedID\\
	 */
	public void sauvegarder()
	{
		this.saveProject(System.getProperty("user.home")+File.separator+"PMMATRIX.DATA"+File.separator+Version.VERSION+File.separator+projet_nom+File.separator+version+File.separator+"PROJET.INDEX");
	}
	/**
	 * @param string
	 */
	private void saveProject(String string) {
		DataOutputStream oo = null;
		try {
			oo = new DataOutputStream(new FileOutputStream(string));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			oo.writeUTF(scene.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			oo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void charger(String proj_nom, int version)
	{
		this.loadProject(System.getProperty("user.home")+File.separator+"PMMATRIX.DATA"+File.separator+Version.VERSION+File.separator+proj_nom+File.separator+(version>=0?version:0)+File.separator+"PROJET.INDEX");
		this.version = version;
	}
	/**
	 * @param string
	 */
	private void loadProject(String string) {
		try {
			new Loader().loadFObject(new File(string), scene);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void modifier()
	{
		
	}
	public void addInstance(RenderingInstance i)
	{
		instances.add(i);
	}
	public void delInstance(RenderingInstance i)
	{
		instances.remove(i);
	}
	public void renderInstance(String Inom, int shot)
	{
		RenderingInstance ri = instances.get(0);
		while(iexists(ri, shot))
		{
			shot++;
		}
	}
	/**
	 * @param ri
	 * @param shot
	 * @return
	 */
	private boolean iexists(RenderingInstance ri, int shot) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @return
	 */
	public String outputdir() {
		return System.getProperty("user.home")+File.separator+"PMMATRIX.DATA"+File.separator+Version.VERSION+File.separator+"RESULTATS"+File.separator+projet_nom+File.separator+version;
	}
}
