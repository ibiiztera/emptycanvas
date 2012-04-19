package be.ibiiztera.md.pmatrix.pushmatrix.generator;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;

public abstract class BaseGenerator {
	private int x;
	private int y;
	protected BufferedImage buffer;

	protected Component comp;
	protected BufferedImage image;
	
	public BaseGenerator(int dx,int dy, Component c)
	{
		x=dx;
		y=dy;
		comp = c;
	}
	public Image getBUFFER()
	{
		return buffer;
	}
	public Graphics getGraphicsEcran()
	{
		return comp.getGraphics();
	}
	public Graphics getGraphicsDisque()
	{
		return image.getGraphics();
	}
	public void paint()
	{
		comp.getGraphics().drawImage(buffer, 0, 0, x, y, null);
		image.getGraphics().drawImage(buffer, 0, 0, x, y, null);
	}
	public void save()
	{
	}
	public void renew()
	{
		image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
	}
	public void initFrame() {
		// TODO Auto-generated method stub
		
	}
	public void computeFrame() {
		// TODO Auto-generated method stub
		
	}
	public void showFrame() {
		// TODO Auto-generated method stub
		
	}
	public void setParams(Params params) {
		// TODO Auto-generated method stub
		
	}
	public void setConfig(Config params) {
		for(Entry<String, Object> entry : params.entrySet())
		{
			if(entry.getKey().startsWith("global") | entry.getKey().startsWith(this.getClass().getSimpleName()))
			{
				setField(entry.getKey().substring(
						entry.getKey().indexOf(".")+1,
						entry.getKey().indexOf("=")),
						entry.getKey().substring(
						entry.getKey().indexOf("="))
						);
			}
		}
		
	}
	private void setField(String key, String stringvalue) {
		
	}
}
