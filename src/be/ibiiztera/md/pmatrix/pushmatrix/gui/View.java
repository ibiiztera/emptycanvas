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
package be.ibiiztera.md.pmatrix.pushmatrix.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIObject;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;
import be.ibiiztera.md.pmatrix.pushmatrix.ui.ModeleIO;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.Action;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

public class View extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 198300101L;
    private int enAttente = 0;
    private int traite = 0;
    private boolean libere = false;
    private ArrayList<Integer> file = new ArrayList<Integer>();
    protected String txtCHEMIN;
    protected String modeleCHEMIN;
    private View view = this;
    private Window window;
    private JPanel viewPanel;
    private JPanel controls;
    private JSplitPane split;
    private JComboBox combo;
    /*
     * private JSlider rotateSliderX; private JSlider rotateSliderY; private
     * JSlider rotateSliderZ;
     */
    private JTextField rotateSliderX;
    private JTextField rotateSliderY;
    private JTextField rotateSliderZ;
    private JTextField degresTF;
    private int degreesX = 0;
    private int degreesY = 0;
    private int degreesZ = 0;
    private double degres;
    private int render = 0;
    private boolean isSetEditor = false;
    private Editor editor;
    private ZBufferInfo zinfo = new ZBufferInfo();
    private JTextArea tf;
    private Console console;
    private int dimxo = 2000;
    private int dimyo = 2000;

    public View() {
        super("Modeling view");

        String h = System.getProperty("user.home");
        String p = System.getProperty("file.separator");
        txtCHEMIN = h + p + "PMMatrix.data" + p + "testscripts";
        modeleCHEMIN = h + p + "PMMatrix.data" + p + "testscripts";

        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();
        JPanel tab3 = new JPanel();
        JPanel tab4 = new JPanel();
        JPanel tab5 = new JPanel();
        JPanel tab6 = new JPanel();

        System.out.println(txtCHEMIN);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewPanel = new JPanel();
        controls = new JPanel();
        rotateSliderX = new JTextField();
        // rotateSliderX.setExtent(100);
        rotateSliderX.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    degreesX = Integer.parseInt(rotateSliderX.getText());

                } catch (Exception ex) {
                }
            }
        });
        rotateSliderY = new JTextField();
        // rotateSliderY.setExtent(100);
        rotateSliderY.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    degreesY = Integer.parseInt(rotateSliderY.getText());

                } catch (Exception ex) {
                }
            }
        });
        rotateSliderZ = new JTextField();
        // rotateSliderZ.setExtent(100);
        rotateSliderZ.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    degreesZ = Integer.parseInt(rotateSliderZ.getText());
                } catch (Exception ex) {
                }
            }
        });
        degresTF = new JTextField();
        degresTF.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    degres = Integer.parseInt(degresTF.getText());
                } catch (Exception ex) {
                }
            }
        });
        JButton wired = new JButton("Type de rendu");
        wired.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                render = (render + 1) % 3;

            }
        });
        rotateSliderX.setText("0.0");
        rotateSliderY.setText("0.0");
        rotateSliderZ.setText("0.0");
        degresTF.setText("0.0");
        combo = new JComboBox();

        JButton load = new JButton("Charger texte");
        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser(txtCHEMIN);
                chooser.setDialogType(JFileChooser.OPEN_DIALOG);
                chooser.showOpenDialog(window);
                if (chooser.getSelectedFile() != null) {
                    load(chooser.getSelectedFile());
                    console.setOriginalScene(window.getScene());
                }
            }
        });
        JButton loadBIN = new JButton("Charger modele");
        loadBIN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser(modeleCHEMIN);
                chooser.setDialogType(JFileChooser.OPEN_DIALOG);
                chooser.showOpenDialog(window);
                if (chooser.getSelectedFile() != null) {
                    loadBIN(chooser.getSelectedFile());
                    console.setOriginalScene(window.getScene());
                }
            }
        });

        JButton editorBtn = new JButton("Editeur");
        editorBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!isSetEditor) {
                    editor = new Editor(controls, view);
                } else {
                    editor.remove();
                }
                isSetEditor = !isSetEditor;

            }
        });
        JButton s = new JButton("Sauvegarder");
        s.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                JFileChooser chooser = new JFileChooser(
                        txtCHEMIN);
                chooser.setDialogType(JFileChooser.SAVE_DIALOG);
                chooser.showOpenDialog(window);
                if (chooser.getSelectedFile() != null) {
                    ModeleIO.sauvergarder(window.getScene(),
                            chooser.getSelectedFile());
                }
            }
        });
        tf = new JTextArea("");
        tf.setSize(300, 200);
        tf.setColumns(40);
        tf.setRows(20);
        /*
         * JButton info = new JButton("Info"); info.addActionListener(new
         * ActionListener() {
         *
         * @Override public void actionPerformed(ActionEvent arg0) {
         * tf.setText(zinfo.toString()); } });
         */
        JButton dumpF = new JButton("Scene");
        dumpF.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dump();
            }
        });
        JButton rs = new JButton("Rendu Fichier");
        rs.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                window.renderAndSave();
            }
        });
        JButton maj = new JButton("OK");
        maj.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loadTXT();
            }
        });
        console = new Console();

        final JList list = new JList();

        JButton majL = new JButton();
        majL.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                list.setListData(window.getScene().liste());
            }
        });

        JPopupMenu listElemMenu = new JPopupMenu();
        listElemMenu.add(new JMenuItem(new Action() {

            private Hashtable<String, Object> values = new Hashtable<String, Object>();
            private PropertyChangeListener pcl;

            @Override
            public Object getValue(String key) {
                return values.get(key);
            }

            @Override
            public void putValue(String key, Object value) {
                if (pcl != null) {
                    pcl.propertyChange(new PropertyChangeEvent(this, key,
                            values.get(key), value));
                }
                values.put(key, value);
            }

            @Override
            public void setEnabled(boolean b) {
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(
                    PropertyChangeListener listener) {
                pcl = listener;
            }

            @Override
            public void removePropertyChangeListener(
                    PropertyChangeListener listener) {
                pcl = null;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ACTION : EDIT NOT YET IMPLEMENTED");
            }
        }));
        list.add(listElemMenu);

        tab3.add(load);
        tab3.add(loadBIN);
        tab4.add(s);
        tab1.add(rs);
        tab1.add(new JButton("Rendu"));
        controls.add(wired);
        tab2.add(rotateSliderX);
        tab2.add(rotateSliderY);
        tab2.add(rotateSliderZ);
        tab2.add(degresTF);
        // controls.add(combo);
        tab2.add(editorBtn);
        tab5.add(tf);
        tab5.add(dumpF);
        tab5.add(maj);
        // controls.add(info);
        tab5.add(console);
        tab6.add(list);
        tab6.add(majL);
        setBounds(0, 0, 640, 480);
        window = new Window();
        window.setView(this);
        viewPanel.add(window);
        window.setSize(dimxo, dimyo);
        setMinimumSize(new Dimension(600, 600));
        // window.setMinimumSize(new Dimension( 800,500));
        JTabbedPane panes = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);

        panes.addTab("Controles de rendu", null, tab1);

        panes.addTab("Controles de placement", tab2);
        panes.addTab("Controles d ouverture fichier", tab3);
        panes.addTab("Controles d enregistrement fichier", tab4);
        panes.addTab("Edition des Elements de la scene", tab5);
        panes.addTab("Elements de la scene - listings", tab6);

        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, window, panes);
        this.add(split);
        pack();
    }

    public void draw() {
        window.run();
    }

    public void setRotationAxe(int rot) {
        this.window.setRotationAxe(rot);
    }

    public void load() {
        HACHE();
        WAIT("WAITING FOR TIME TO LOAD");
        new Thread() {

            @Override
            public void run() {
                TRIObject fo = new TRIObject();
                fo.add(new TRI(new Point3D(0, 0, 0), new Point3D(0, 1, 0),
                        new Point3D(1, 1, 0), Color.RED));
                window.getScene().add(fo);
                console.setOriginalScene(window.getScene());

            }
        }.start();
        RELACHE_HACHE();
    }
    private int idW = 0;

    public synchronized boolean librePour(int id) {
        if (file.size() == 0) {
            return true;
        }
        if (file.size() == 1) {
            file.remove(0);
            return true;
        }
        int idCurr = file.get(0);
        if (id == idCurr) {
            return true;
        } else {
            return false;
        }


    }

    public void WAIT(String msg) {
        System.out.println("WAITING ...");
        enAttente++;
        idW++;
        int id = idW;
        file.add(idW);
        while (!librePour(id)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\t" + msg
                    + "\n\t\tNOMBRE D'OPERATIONS EN ATTENTE: " + enAttente
                    + "\n\t\tNOMBRE D'OPERATIONS EN COURS  : " + traite);
        }
        libere = false;
        traite++;
        enAttente--;
        System.out.println("\t\tGO!!");
    }

    public void RELACHE_HACHE() {
        traite--;
    }

    public void HACHE() {
    }

    public void dump() {
        HACHE();
        WAIT("DESCRIPTIONS SCENE -- WAITING");
        new Thread() {

            public void run() {
                tf.setText(window.getScene().toString());

            }
        }.start();
        RELACHE_HACHE();
    }

    private void load(final File selectedFile) {
        HACHE();
        WAIT("LOAD -- WAITING");

        new Thread() {

            public void run() {
                try {
                    new Loader().loadIF(selectedFile, window.getScene());
                } catch (Exception e) {
                    System.out.println("ERROR LOADING/+o PARSING FILE (TXT)");
                }
            }
        }.start();
        RELACHE_HACHE();

    }

    private void loadTXT() {
        HACHE();
        WAIT("LOAD TXT -- WAITING");
        new Thread() {

            @Override
            public void run() {

                try {
                    window.newScene();
                    new Loader().loadIF(tf.getText(), window.getScene());
                } catch (Exception e) {
                    System.out.println("ERROR LOADING/+o PARSING FILE (TXT)");
                }
            }
        }.start();
        RELACHE_HACHE();
    }

    protected void loadBIN(final File selectedFile) {
        HACHE();
        WAIT("LOAD BIN -- WAITING");
        new Thread() {

            @Override
            public void run() {
                try {

                    window.setOriginalScene(new ModeleIO().charger(
                            window.getScene(), selectedFile));
                } catch (Exception e) {
                    System.out.println("ERROR LOADING/+o PARSING FILE (BIN)");
                }
                RELACHE_HACHE();
            }
        }.start();
    }

    public static void main(String[] args) {
        View v = new View();
        v.setVisible(true);
        v.load();
        v.draw();
    }
    public void LAUNCHINTERFACE()
    {
        main(new String [] {});
    }
    public int getRender() {

        return render;
    }

    public int getRotationX() {
        return degreesX;
    }

    public int getRotationY() {
        return degreesY;
    }

    public int getRotationZ() {
        return degreesZ;
    }

    public void setCombo() {
        combo.removeAllItems();
    }

    public JComboBox getCombo() {
        return combo;
    }

    public void updateZBufferInfo(ZBufferInfo i) {
        this.zinfo = i;
        tf.setText(zinfo.toString());
    }

    public double getDegres() {
        return degres;
    }
}
