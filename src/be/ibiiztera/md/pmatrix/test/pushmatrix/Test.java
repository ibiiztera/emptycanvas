/**
 * 
 */
package be.ibiiztera.md.pmatrix.test.pushmatrix;

import java.io.File;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 17 oct. 2011
 *
 */
public abstract class Test {
	private String name;
	private int id = 0;
	protected void loadTestFile(String filename, Scene scene)
	{
		
	}
	protected String getFolderName() {
		return System.getProperty("user.home")+File.separator+name+File.separator;
	}
	protected void setFolderName(String name) {
		this.name = name;
	}
	public String name()
	{
		return getFolderName()+animeID()+".png";
	}
	protected String animeID()
	{
		return ""+(id++);
	}
	/***
	 * Generate anime sequence
	 */
	public abstract void testAnime();
	/***
	 * Generate serie or unanination sequence
	 */
	public abstract void testSerie();
	/***
	 * Generate single frame
	 */
	public abstract void testSingle();
}
