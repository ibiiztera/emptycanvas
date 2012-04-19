package be.ibiiztera.md.pmatrix.pushmatrix.gui.editeurs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.PolygonObjet;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.Polygon3D;
import be.ibiiztera.md.pmatrix.pushmatrix.ui.ModeleIO;

public class CubeEditeur extends Thread {
	protected static final int COMM_AJOUTERPOINT = 0;
	private final int COMM_CANCEL = -1;
	private final int COMM_NULL = 0;
	private final int COMM_CONFIRMER = 1;
	private final int COMM_DEPLACERGRILLE = 2;
	private final int COMM_CHOISIRPOINT_XZ = 3;
	private final int COMM_SELECTIONPOINT = 4;
	private final int COMM_AJOUTTRIANGLE = 5;
	private final int COMM_SELECTIONNERTRIANGLE = 6;
	private final int COMM_SUPPRIMERPOINT = 7;
	private final int COMM_SUPPRIMERTRIANGLE = 8;
	private final int COMM_ENREGISRER = 9;
	private boolean ouvert;
	private boolean active;
	private JPanel panel;
	private int largeur;
	private int hauteur;
	private int command = 0;
	private double zgrille = 0;
	private JFrame parent;
	private Point3D currentPoint = new Point3D();
	private ArrayList<Point3D> points;
	private ArrayList<Polygon3D> triangles;

	public CubeEditeur(JPanel panel) {
		this.panel = panel;
		ouvrir();
	}

	public CubeEditeur(JPanel p, JFrame f) {
		this.panel = p;
		this.parent = f;
		points = new ArrayList<Point3D>();
		triangles = new ArrayList<Polygon3D>();
		ouvrir();
	}

	public void activer() {
		this.active = true;
	}

	public void ouvrir() {
		this.ouvert = true;
	}

	public void fermer() {
		this.active = false;
	}

	public synchronized void run() {
		zgrille = 0;
		command = 0;

		attendreCommande();
		while (ouvert) {
			if (active) {
				panel.getGraphics().setColor(Color.black);
				panel.getGraphics().drawString("COMM" + command, 0, 0);
				drawGrille(Color.black, 0);
				drawGrille(Color.blue, zgrille);

				Point3D pG = new Point3D(currentPoint);
				pG.setX((int) currentPoint.getX());
				pG.setY((int) currentPoint.getY());
				pG.setZ((int) currentPoint.getZ());
				drawPoint(pG, Color.green);

				for (Polygon3D t : triangles) {
					drawPolyObjet(t);
				}
				for (Point3D p : points)
					drawPoint(p, Color.yellow);

				switch (command) {
				case COMM_NULL:
					break;

				case COMM_DEPLACERGRILLE:
					// System.out.println(".");
					break;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Graphics g = panel.getGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, largeur, hauteur);
			}
		}
	}

	private int old;
	protected int currInd = 0;

	private int attendreCommande() {
		System.out.println("ECOUTE");
		parent.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent k) {
				switch (old) {
				case COMM_DEPLACERGRILLE:
					System.out.println("RETIRER ECOUTE DEPLACER GRILLE");
					retirerEcoutes_DeplacerGrille();
					old = COMM_NULL;
					break;
				case COMM_SELECTIONPOINT:
					System.out.println("RETIRER ECOUTE SELECTIOn GRILLE");
					retirerEcoutes_Selectionpoint();
					old = COMM_NULL;
					break;
				}
				switch (k.getKeyChar()) {
				case 'h':
					System.out.println("HAUTEUR");
					definirEcoutes_DeplacerGrille();
					old = command = COMM_DEPLACERGRILLE;
					break;
				case 'c':
					System.out.println("ANNULER");
					old = command = COMM_CANCEL;
					break;
				case 'C':
					System.out.println("CLEAR");
					triangles.clear();
					points.clear();
					currInd = 0;
					currentPoint = new Point3D();
					old = command = COMM_CANCEL;
					break;
				case 'm':
					System.out.println("MODIFIER");
					definirEcoutes_Selectionpoint();
					old = command = COMM_SELECTIONPOINT;
					break;
				case 'o':
					System.out.println("CONFIRMER");
					if (old == COMM_SELECTIONPOINT) {
						System.out.println("POINT AJOUTE");
						points.add(currentPoint);
					}
					old = command = COMM_CONFIRMER;
					break;
				case 'p':
					System.out.println("CONFIRMER");
					if (old == COMM_AJOUTERPOINT) {
						System.out.println("POINT AJOUTE"
								+ currentPoint.toString());
						points.add(currentPoint);
					}
					old = command = COMM_CONFIRMER;
					break;
				case 't':
					if (currInd + 4 <= points.size()) {
						Polygon3D t;
						t = new Polygon3D(points.get(currInd++), points
								.get(currInd++), points.get(currInd++), points.get(currInd++),
								Color.pink);
						triangles.add(t);
						System.out.println("PolyObjetANGLE AJOUTE" + t.toString());
					}
					break;
				case 's':
					break;
				case 'S':
					Scene sc = new  Scene();
					PolygonObjet o = new PolygonObjet(triangles);
					sc.add(o);
					ModeleIO.sauvergarder(o, new File("c:\\scene.moodls"));
					break;
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		return 0;
	}

	private void drawGrille(Color c, double z) {
		largeur = panel.getWidth();
		hauteur = panel.getHeight();
		Graphics g = panel.getGraphics();
		g.setColor(c);
		for (double i = 0; i < 20; i += 1)
			for (double j = 0; j < 20; j += 1) {
				g.drawPolygon(
						new int[] { GRILLEpositionX(i, j, z),
								GRILLEpositionX(i + 1, j, z),
								GRILLEpositionX(i + 1, j + 1, z),
								GRILLEpositionX(i, j + 1, z) },
						new int[] { GRILLEpositionY(i, j, z),
								GRILLEpositionY(i + 1, j, z),
								GRILLEpositionY(i + 1, j + 1, z),
								GRILLEpositionY(i, j + 1, z) }, 4);
			}

	}

	private void drawPoint(Point3D p, Color green) {
		double i = p.getX(), j = p.getY(), z = p.getZ();
		Graphics g = panel.getGraphics();
		g.setColor(green);
		g.drawPolygon(
				new int[] { GRILLEpositionX(i, j, z),
						GRILLEpositionX(i + 1, j, z),
						GRILLEpositionX(i + 1, j + 1, z),
						GRILLEpositionX(i, j + 1, z) },
				new int[] { GRILLEpositionY(i, j, z),
						GRILLEpositionY(i + 1, j, z),
						GRILLEpositionY(i + 1, j + 1, z),
						GRILLEpositionY(i, j + 1, z) }, 4);
	}

	private void drawPolyObjet(Polygon3D tri) {
		Graphics g = panel.getGraphics();
		g.setColor(Color.green);
			g.fillPolygon(
					new int[] {
							GRILLEpositionX(tri.getSommet()[0].getX(),
									tri.getSommet()[0].getY(),
									tri.getSommet()[0].getZ()),
							GRILLEpositionX(tri.getSommet()[1].getX(),
									tri.getSommet()[1].getY(),
									tri.getSommet()[1].getZ()),
							GRILLEpositionX(tri.getSommet()[2].getX(),
									tri.getSommet()[2].getY(),
									tri.getSommet()[2].getZ()),
							GRILLEpositionX(tri.getSommet()[3].getX(),
									tri.getSommet()[3].getY(),
									tri.getSommet()[3].getZ() )

					},
					new int[] {
							GRILLEpositionY(tri.getSommet()[0].getX(),
									tri.getSommet()[0].getY(),
									tri.getSommet()[0].getZ()),
							GRILLEpositionY(tri.getSommet()[1].getX(),
									tri.getSommet()[1].getY(),
									tri.getSommet()[1].getZ()),
							GRILLEpositionY(tri.getSommet()[2].getX(),
									tri.getSommet()[2].getY(),
									tri.getSommet()[2].getZ()),
							GRILLEpositionY(tri.getSommet()[3].getX(),
									tri.getSommet()[3].getY(),
									tri.getSommet()[3].getZ()) }, 4);
	}

	private int GRILLEpositionX(double i, double j, double z) {
		return largeur / 4 + (int) (i * 10 + j * 10);
	}

	private int GRILLEpositionY(double i, double j, double z) {
		return (int) (hauteur / 2 + i * 5 - j * 5 + z);
	}

	private int GRILLEpositionY(Point3D p) {
		double i = p.getX(), j = p.getY(), z = p.getZ();
		return GRILLEpositionY(i, j, z);
	}

	private double GRILLEpositionX(Point3D p) {
		double i = p.getX(), j = p.getY(), z = p.getZ();
		return GRILLEpositionX(i, j, z);
	}

	private Point3D getXY(Point p) {
		return new Point3D(
				(p.getX() / 2 + p.getY() - zgrille - largeur / 4 / 2 - hauteur / 2) / 10,
				(p.getX() / 2 - p.getY() + zgrille - largeur / 4 / 2 + hauteur / 2) / 10,
				zgrille);
	}

	private MouseListener mlDEPL;
	private KeyListener klDEPL;
	private MouseMotionListener mmDEPL;
	private MouseListener mlSELP;
	private MouseMotionListener mmSELP;
	private KeyListener klSELP;
	private MouseListener ml;
	private KeyListener kl;
	private MouseMotionListener mm;
	private double z0 = 0;
	protected boolean dragging;

	private void definirEcoutes_Selectionpoint() {
		panel.addMouseMotionListener(mmSELP = new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				panel.getGraphics().setColor(Color.green);
				currentPoint = getXY(e.getPoint());
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		panel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				selectionnerPoint(e.getPoint());
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void retirerEcoutes_Selectionpoint() {
		if (mmSELP != null) {
			panel.removeMouseMotionListener(mmSELP);
			mmSELP = null;
		}
		if (mlSELP != null) {
			panel.removeMouseListener(mlSELP);
			mlSELP = null;
		}
	}
	private void selectionnerPoint(Point p0)
	{
		Point3D p = getXY(p0);
		for(Polygon3D p3 : triangles)
		{
			for(Point3D p2 : p3.getSommet())
			{
				if(new Point((int) GRILLEpositionX(p2), GRILLEpositionY(p2)).distance(p0)<3)
					currentPoint = p2;
			}
		}
	}
	private void definirEcoutes_DeplacerGrille() {
		if (mmDEPL == null && mlDEPL == null) {
			panel.addMouseMotionListener(mmDEPL = new MouseMotionListener() {

				@Override
				public void mouseMoved(MouseEvent e) {
					if (dragging)
						drawGrille(Color.red, e.getPoint().getY() - z0);

				}

				@Override
				public void mouseDragged(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			panel.addMouseListener(mlDEPL = new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					stopDrag(e.getPoint().getY());
					drawGrille(Color.blue, zgrille);
					dragging = false;
				}

				@Override
				public void mousePressed(MouseEvent e) {
					startDrag(e.getPoint().getY());
					panel.getGraphics().setColor(Color.blue);
					drawGrille(Color.red, zgrille);
					dragging = true;
				}

				private void stopDrag(double y) {
					zgrille = -(z0 - y);
					panel.getGraphics().drawString("" + zgrille, 0, 0);
				}

				private void startDrag(double y) {
					z0 = y;
					zgrille = 0;
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	private void retirerEcoutes_DeplacerGrille() {
		if (mlDEPL != null) {
			panel.removeMouseListener(mlDEPL);
			mlDEPL = null;
		}
		if (klDEPL != null) {
			panel.removeKeyListener(kl);
			klDEPL = null;
		}
		if (mmDEPL != null) {
			panel.removeMouseMotionListener(mmDEPL);
			mmDEPL = null;
		}
	}

	public Point3D selectionnerPoint3D(MouseEvent e) {
		for (Point3D p : points)
			if (Point.distance(GRILLEpositionX(p), GRILLEpositionY(p), e
					.getPoint().getX(), e.getPoint().getY()) < 5)
				return p;
		return null;
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] a) {
		JFrame f = new JFrame();
		f.setMinimumSize(new Dimension(600, 600));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel();
		f.add(p);
		f.pack();
		CubeEditeur ce = new CubeEditeur(p, f);
		ce.activer();

		ce.start();
		f.show(true);
	}
}
