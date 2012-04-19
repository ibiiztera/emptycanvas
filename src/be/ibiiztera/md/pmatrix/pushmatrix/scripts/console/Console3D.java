package be.ibiiztera.md.pmatrix.pushmatrix.scripts.console;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;

public class Console3D {
	public CommandResultat executeCommande(String command, Scene sc) {
		CommandResultat cm = new CommandResultat();
		try {
			new Loader().loadIF(command, sc);
			cm.setResultat(true);
		} catch (Exception ex) {
			
			System.err.println("ERREUR D INTERPRETATION DE COMMANDE");
			ex.printStackTrace();
			
			cm.setResultat(false);
		}
		return cm;
	}
}
