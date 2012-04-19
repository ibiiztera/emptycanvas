package be.ibiiztera.md.pmatrix.pushmatrix.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;

public class Editor extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel parent;
	private int XY = 0;
	private int YZ = 1;
	private int ZX = 2;
	private int OTHER = 3;
	private View view;
	private int orientation;
	private Point3D vector;

	public Editor(JPanel parent, View tview) {
		//setLayout(new BorderLayout());
		this.view = tview;
		this.parent = parent;
		parent.add(this);
		JButton xy = new JButton("Axe XY");
		JButton yz = new JButton("Axe YZ");
		JButton zx = new JButton("Axe ZX");
		JLabel orient = new JLabel("Orientation");
		final JTextField nx = new JTextField("0");
		final JTextField ny = new JTextField("0");
		final JTextField nz = new JTextField("1");
		JButton update = new JButton("Update");
		JButton newPt1 = new JButton("Point 1");
		JButton newPt2 = new JButton("Point 2");
		JButton newPt3 = new JButton("Point 3");
		//JColorChooser cc = new JColorChooser();
		JButton addBtn = new JButton("Add Triangle");

		xy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				orientation = XY;
				view.setRotationAxe(orientation);
			}
		});
		yz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				orientation = YZ;
				view.setRotationAxe(orientation);
			}
		});
		zx.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				orientation = ZX;
				view.setRotationAxe(orientation);
			}
		});
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				orientation = OTHER;
				view.setRotationAxe(orientation);

				setVector(new Point3D(Double.parseDouble(nx.getText()), Double
						.parseDouble(ny.getText()), Double.parseDouble(nz
						.getText())));

			}
		});

		add(xy, BorderLayout.CENTER);
		add(yz, BorderLayout.CENTER);
		add(zx, BorderLayout.CENTER);
		add(orient, BorderLayout.CENTER);
		add(nx, BorderLayout.CENTER);
		add(ny, BorderLayout.CENTER);
		add(nz, BorderLayout.CENTER);
		add(update, BorderLayout.CENTER);
		add(newPt1, BorderLayout.CENTER);
		add(newPt2, BorderLayout.CENTER);
		add(newPt3, BorderLayout.CENTER);
		add(addBtn, BorderLayout.CENTER);
		//add(cc, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void remove() {
		parent.remove(this);
		this.setVisible(false);
	}

	public void setVector(Point3D vector) {
		this.vector = vector;
	}

	public Point3D getVector() {
		return vector;
	}
}
