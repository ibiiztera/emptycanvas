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
package be.ibiiztera.md.pmatrix.pushmatrix.generator;

import javax.swing.JButton;
import javax.swing.JFrame;

import be.ibiiztera.md.pmatrix.test.pushmatrix.TestBox;


public class GUI extends JFrame implements Runnable {

	/**
	 * 
	 */
	JButton button;
	MovieGenerator mg;
	private TestBox test;
	private static final long serialVersionUID = 6513230272364726039L;

	public GUI(String titre) {
		super(titre);
		button = new JButton(titre) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5270299240539371493L;

			@Override
			public void repaint() {
				button.getGraphics().drawImage(test.image(), 0, 0, 300, 300,
						null);
			}

		};
		add(button);
		mg = new MovieGenerator(100, 100, button);
		setVisible(true);
	}

	public GUI(String string, TestBox tz) {
		super(string);
		this.test = tz;
		button = new JButton("ZBuffer Demo");
		add(button);
		mg = new MovieGenerator(100, 100, button);
		setVisible(true);
	}

	@Override
	public void run() {
		while (true) {
			button.repaint();
			button.getGraphics().drawImage(test.image(), 0, 0, 300, 300,
					null);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		GUI gui = new GUI("Démos");
		gui.run();
	}
}
