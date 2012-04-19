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
