package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import be.ibiiztera.md.pmatrix.pushmatrix.*;
import be.ibiiztera.md.pmatrix.pushmatrix.generator.*;
import be.ibiiztera.md.pmatrix.pushmatrix.extras.*;

import java.util.ArrayList;

public class Loader implements SceneLoader {

    private int pos;

    public void loadFObject(String data, Scene sc) throws Exception {
        interprete(data, sc);
    }

    @SuppressWarnings("deprecation")
    public void loadFObject(File file, Scene sc) throws Exception {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DataInputStream ds = new DataInputStream(fis);
        String text = "";
        String t = "";
        try {
            while ((text = ds.readLine()) != null) {
                t += text;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        interprete(t, sc);
    }

    private void interprete(String t, Scene sc) {
        InterpreteListeTriangle ilf = new InterpreteListeTriangle();
        InterpreteBSpline ib = new InterpreteBSpline();
        TRIObject fo = null;
        BSpline b = null;
        try {
            fo = (TRIObject) ilf.interprete(t, 0);
            ilf.getPosition();
            sc.add(fo);
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                b = (BSpline) ib.interprete(t, 0);
                ib.getPosition();
                sc.add(b);
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }

        return;
    }

    public boolean loadIF(File file, Scene sc) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DataInputStream ds = new DataInputStream(fis);
        String text = "";
        String t = "";
        try {
            while ((text = ds.readLine()) != null) {
                t += text;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return loadIF(t, sc);
    }

    public boolean loadIF(String t, Scene sc) {
        boolean failed = false;
        boolean end = false;
        InterpreteFacade interpeteH = new InterpreteFacade(t, 0);




        String id = "";

        while (interpeteH.getPosition() < t.length() && !end && !failed) {
            if (interpeteH.parseEND().equals(")")) {
                end = true;
                continue;
            }
            failed = true;
            try {
                id = interpeteH.interpreteIdentifier();

                id = id.toLowerCase();


                pos = interpeteH.getPosition();
            } catch (InterpreteException e1) {
                failed = true;
                e1.printStackTrace();
            }
            if ("scene".equals(id)) {
                InterpretesBase ib = new InterpretesBase();
                ArrayList<Integer> p = new ArrayList<Integer>();
                ib.compile(p);
                p.add(ib.BLANK);
                p.add(ib.LEFTPARENTHESIS);
                p.add(ib.BLANK);
                try {
                    ib.read(t, pos);
                    pos = ib.getPosition();
                    interpeteH.setPosition(pos);
                    failed = false;
                } catch (Exception ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if ("bezier".equals(id)) {
                BezierCubique bc = null;
                try {
                    bc = interpeteH.interpreteBezier();
                    sc.add(bc);
                    failed = false;

                } catch (InterpreteException e) {
                    failed = true;
                    e.printStackTrace();
                }
            } else if ("p".equals(id)) {
                Point3D p = null;
                try {
                    p = interpeteH.interpretePoint3DAvecCouleur();
                    sc.add(p);
                    failed = false;

                } catch (InterpreteException e) {
                    failed = true;
                    e.printStackTrace();
                }
            } else if ("poly".equals(id)) {
                Polygone p = null;
                try {
                    p = interpeteH.interpretePolygone();
                    sc.add(p);
                    failed = false;

                } catch (InterpreteException e) {
                    failed = true;
                    e.printStackTrace();
                }
            } else if ("droite".equals(id)) {
                SegmentDroite p = null;
                try {
                    p = interpeteH.intepreteSegmentDroite();
                    sc.add(p);
                    failed = false;

                } catch (InterpreteException e) {
                    failed = true;
                    e.printStackTrace();
                }
            } else if ("bezier2d".equals(id)) {
                BezierCubique2D bc = null;
                try {
                    bc = interpeteH.interpreteBezier2d();
                    sc.add(bc);
                    failed = false;

                } catch (InterpreteException e) {
                    failed = true;
                    e.printStackTrace();
                }
            } else if ("cube".equals(id)) {
                Cube c = null;
                try {
                    c = interpeteH.interpreteCube();
                    sc.add(c);
                    failed = false;

                } catch (InterpreteException e) {
                    failed = true;
                    e.printStackTrace();
                }
            } else if ("tris".equals(id)) {
                try {
                    TRIObject tris = interpeteH.interpreteTriangles();
                    sc.add(tris);
                    failed = false;
                } catch (InterpreteException e) {
                    failed = true;
                    e.printStackTrace();
                }
            } else if ("bspline".equals(id)) {
                try {
                    BSpline b = interpeteH.interpreteBSpline();
                    sc.add(b);
                    failed = false;
                } catch (InterpreteException e) {
                    failed = true;
                    e.printStackTrace();
                }
            } else if ("tourbillon".equals(id)) {
                sc.add(interpeteH.intepreteTourbillon());
                failed = false;
                break;
            } else if ("colline".equals(id)) {
                Colline c = (Colline) interpeteH.intepreteColline();
                sc.add(c);
                failed = false;
                break;
            } else if ("attracteuretrange".equals(id)) {
                try {
                    AttracteurEtrange ae = interpeteH.intepreteAttracteurEtrange();
                    sc.add(ae);
                    failed = false;
                    break;
                } catch (InterpreteException ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                    failed = true;
                }
            } else if ("tubulaire".equals(id)) {
                try {
                    Tubulaire ae = interpeteH.intepreteTubulaire();
                    sc.add(ae);
                    failed = false;
                    break;
                } catch (InterpreteException ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                    failed = true;
                }
            } else if ("simplesphere".equals(id)) {
                try {
                    SimpleSphere ss = interpeteH.intepreteSimpleSphere();
                    sc.add(ss);
                    failed = false;
                } catch (InterpreteException ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                    failed = true;
                }
            } else if ("simplespheretexture".equals(id)) {
                try {
                    SimpleSphereAvecTexture ss = interpeteH.interpreteSimpleSphereAvecTexture();
                    sc.add(ss);
                    failed = false;
                } catch (InterpreteException ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                    failed = true;
                }
            } else if ("tetraedre".equals(id)) {
                try {
                    Tetraedre t2 = interpeteH.interpreteTetraedre();
                    sc.add(t2);
                    failed = false;

                } catch (InterpreteException ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("plan".equals(id)) {
                try {
                    Plan3D t2 = interpeteH.interpretePlan3D();
                    sc.add(t2);
                    failed = false;

                } catch (InterpreteException ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("ellipsoide".equals(id)) {
                try {
                    TRIEllipsoide t2 = interpeteH.interpreteTRIEllipsoide();
                    sc.add(t2);
                    failed = false;

                } catch (InterpreteException ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("sphere".equals(id)) {
                try {
                    TRISphere t2 = interpeteH.interpreteTRISphere();
                    sc.add(t2);
                    failed = false;

                } catch (InterpreteException ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            interpeteH.interpreteBlank();
        }
        return !failed;
    }

    /**
     * @param string
     * @param s
     */
    public void loadTEST(String string, Scene s) {
        String h = System.getProperty("user.home");
        String p = System.getProperty("file.separator");
        String txtCHEMIN = h + p + "PMMatrix.data" + p + "testscripts" + p;

        this.loadIF(new File(txtCHEMIN + string), s);

    }
}