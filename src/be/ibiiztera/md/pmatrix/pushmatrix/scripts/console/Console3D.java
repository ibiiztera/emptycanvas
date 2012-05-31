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
