package be.ibiiztera.md.pmatrix.pushmatrix.composants;

import java.awt.Point;

import be.ibiiztera.md.pmatrix.pushmatrix.Axe;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;

public class Vecteur {
	private static Toolkit toolkit;
	
	public static void initToolkit(String path)
	{
		toolkit = new Toolkit(path);
	}
	protected Point location;
	
	private Point3D vecteur;

	private Instance i;

	public void setLocation(Point loc)
	{
		this.location = loc;
	}
	public void display()
	{
		i = toolkit.instance("builder.Vecteur");
	}
	public Input input()
	{
		Input input = toolkit.instance(i).input();
		input.onBoutonGauche(new MouseInput() {

			private String i;

			@Override
			public void onBoutonGauche() {
				Axe axe = (Axe) toolkit.instance(i).validerChoix("builder.Vecteur:entree:choixAxe:valider");
			}

			@Override
			public void onBoutonDroit() {
				// TODO Auto-generated method stub
				
			}
			
		});
		return input;
	}
	public Vecteur(Point3D v)
	{
		vecteur = v;
	}

	
}
