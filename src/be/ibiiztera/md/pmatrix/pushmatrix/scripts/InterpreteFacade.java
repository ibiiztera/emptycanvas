package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.generator.*;
import be.ibiiztera.md.pmatrix.pushmatrix.*;
import be.ibiiztera.md.pmatrix.pushmatrix.extras.*;
import java.awt.Color;
import java.util.ArrayList;

public class InterpreteFacade {

    public final int FILETYPE_MODEL_TO = 0;
    public final int BLANK = 0;
    public final int LEFTPARENTHESIS = 1;
    public final int RIGHTPARENTHESIS = 2;
    public final int LEFTSKETCH = 3;
    public final int RIGHTSKETCH = 4;
    public final int ALPHA_WORD = 5;
    public final int POINT3D = 100;
    public final int COLOR = 101;
    public final int INTEGER = 102;
    public final int DOUBLE = 103;
    public final int TRIANGLE = 104;
    public final int LIST_TRIANGLES = 105;
    public final int BSPLINE = 106;
    public final int BEZIER = 107;
    private String text;
    private int pos;
    private boolean okay;
    private String répertoire;

    public InterpreteFacade(String text, int pos) {
        super();
        this.text = text;
        this.pos = pos;
    }

    public String interpreteBlank() {
        InterpretesBase ib = new InterpretesBase();
        ArrayList<Integer> pattern = new ArrayList<Integer>();
        pattern.add(ib.BLANK);
        ib.compile(pattern);
        try {
            ib.read(text, pos);
            pos = ib.getPosition();
        } catch (Exception e) {
            return "";
        }
        return java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString(" ");
    }

    @Deprecated
    public String parseEND() {
        InterpretesBase ib = new InterpretesBase();
        ArrayList<Integer> pattern = new ArrayList<Integer>();
        pattern.add(ib.RIGHTPARENTHESIS);
        ib.compile(pattern);
        try {
            ib.read(text, pos);
            pos = ib.getPosition();
        } catch (Exception e) {
            return "";
        }
        return java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString(")");
    }

    @Deprecated
    public String parseWord() throws InterpreteException {
        InterpreteString is = new InterpreteString();
        String s = "";
        try {
            s = (String) is.interprete(text, pos);
            pos = is.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));
        }
        return s;
    }

    public String interpreteIdentifier() throws InterpreteException {
        InterpreteString is = new InterpreteString();
        String s = "";
        try {
            s = (String) is.interprete(text, pos);
            pos = is.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));
        }/*
         * while (success) { interpreteBlank(); try { s += (String)
         * is.interprete(text, pos); pos = is.getPosition(); } catch (Exception
         * e) { throw new InterpreteException("Erruer"); } }
         */
        return s;
    }

    public Integer interpreteInteger() throws InterpreteException {
        InterpretesBase ib = new InterpretesBase();
        ArrayList<Integer> pattern = new ArrayList<Integer>();
        pattern.add(ib.INTEGER);
        ib.compile(pattern);
        try {
            ib.read(text, pos);
            pos = ib.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));

        }
        return (Integer) ib.get().get(0);
    }

    public Double interpreteDouble() throws InterpreteException {
        InterpretesBase ib = new InterpretesBase();
        ArrayList<Integer> pattern = new ArrayList<Integer>();
        pattern.add(ib.DECIMAL);
        ib.compile(pattern);
        try {
            ib.read(text, pos);
            pos = ib.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));

        }
        return (Double) ib.get().get(0);
    }

    public Point3D interpretePoint3D() throws InterpreteException {
        InterpretePoint3D pp = new InterpretePoint3D();
        Point3D c = new Point3D();
        try {
            c = (Point3D) pp.interprete(text, pos);
            pos = pp.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));

        }
        return c;
    }

    public Point3D interpretePoint3DAvecCouleur() throws InterpreteException {
        InterpretePoint3DCouleur ipp = new InterpretePoint3DCouleur();
        Point3D p = null;
        try {
            p = (Point3D) ipp.interprete(text, pos);
            pos = ipp.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));

        }
        return p;

    }

    public Color interpreteColor() throws InterpreteException {
        InterpreteCouleur pc = new InterpreteCouleur();
        Color c = Color.BLACK;
        try {
            c = (Color) pc.interprete(text, pos);
            pos = pc.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));

        }
        return c;
    }

    public TRI interpreteTriangle() throws InterpreteException {
        InterpreteTriangle interpreteH = new InterpreteTriangle();
        TRI t = null;
        try {
            t = (TRI) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));

        }
        return t;
    }

    public TRIObject interpreteTriangles() throws InterpreteException {
        InterpreteListeTriangle interpreteH = new InterpreteListeTriangle();
        TRIObject fo = null;
        try {
            fo = (TRIObject) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERREUR"));

        }
        return fo;
    }

    public BSpline interpreteBSpline() throws InterpreteException {
        InterpreteBSpline interpreteH = new InterpreteBSpline();
        BSpline b = null;
        try {
            b = (BSpline) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));

        }
        return b;
    }

    public Tourbillon intepreteTourbillon() {
        return new Tourbillon();

    }

    public BezierCubique interpreteBezier() throws InterpreteException {
        InterpreteBezier interpreteH = new InterpreteBezier();
        BezierCubique b = null;
        try {
            b = (BezierCubique) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));

        }
        return b;
    }

    public BezierCubique2D interpreteBezier2d() throws InterpreteException {
        InterpreteBezier2D interpreteH = new InterpreteBezier2D();
        BezierCubique2D b = null;
        try {
            b = (BezierCubique2D) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));

        }
        return b;
    }

    public Tubulaire interpreteTubulaire() throws InterpreteException {
        InterpreteBezier2D interpreteH = new InterpreteBezier2D();
        Tubulaire t = null;
        try {
            t = (Tubulaire) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ERRUER"));

        }
        return t;

    }

    public void parse(int filetype) {
    }

    public Object getParsedObject() {
        return null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPosition() {
        return pos;
    }

    public void setPosition(int pos) {
        this.pos = pos;
    }

    public boolean isFailed() {
        return true;
    }

    public void setOkay(boolean okay) {
        this.okay = okay;
    }

    public boolean isOkay() {
        return okay;
    }

    Representable intepreteColline() {
        return new Colline(1000);
    }

    public AttracteurEtrange intepreteAttracteurEtrange()
            throws InterpreteException {
        InterpreteAttracteurEtrange interpreteH = new InterpreteAttracteurEtrange();
        AttracteurEtrange t = null;
        try {
            t = (AttracteurEtrange) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("ATTRACTEUR ETRANGE ERRUER D'ANALYSE SYNTAXIQUE"));

        }
        return t;

    }

    public Tubulaire intepreteTubulaire()
            throws InterpreteException {
        InterpreteTubulaire interpreteH = new InterpreteTubulaire();
        Tubulaire t = null;
        try {
            t = (Tubulaire) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(e);

        }
        return t;

    }

    /**
     * *
     *
     * @return segment de droite
     * @throws InterpreteException
     */
    public SegmentDroite intepreteSegmentDroite()
            throws InterpreteException {

        InterpreteSegment interpreteH = new InterpreteSegment();
        SegmentDroite t = null;
        try {
            t = (SegmentDroite) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(e);

        }
        return t;

    }

    /**
     * @return @throws InterpreteException
     */
    public SimpleSphere intepreteSimpleSphere() throws InterpreteException {
        InterpreteSimpleSphere interpreteH = new InterpreteSimpleSphere();
        SimpleSphere t = null;
        try {
            t = (SimpleSphere) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (InterpreteException e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("SIMPLE SPHERE:  ERREUR D'ANALYSE SYNTAXIQUE"));

        }
        return t;

    }

    public SimpleSphereAvecTexture interpreteSimpleSphereAvecTexture() throws InterpreteException {
        InterpreteSimpleSphereTexture interpreteH = new InterpreteSimpleSphereTexture();
        SimpleSphereAvecTexture t = null;
        try {
            t = (SimpleSphereAvecTexture) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(e);

        }
        return t;


    }

    /**
     * @return @throws InterpreteException
     */
    public Tetraedre interpreteTetraedre() throws InterpreteException {
        InterpreteTetraedre interpreteH = new InterpreteTetraedre();
        Tetraedre t = null;
        try {
            t = (Tetraedre) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("TETRAEDRE :  ERREUR D'ANALYSE SYNTAXIQUE"), e);

        }
        return t;


    }

    public Cube interpreteCube() throws InterpreteException {
        InterpreteCube interpreteH = new InterpreteCube();
        Cube c = null;
        try {
            c = (Cube) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("CUBE :  ERREUR D'ANALYSE SYNTAXIQUE"), e);
        }
        return c;
    }

    public Plan3D interpretePlan3D() throws InterpreteException {
        InterpretePlan3D interpreteH = new InterpretePlan3D();
        Plan3D c = null;
        try {
            c = (Plan3D) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (Exception e) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("PLAN3D :  ERREUR D'ANALYSE SYNTAXIQUE "), e);
        }
        return c;
    }

    public TRIEllipsoide interpreteTRIEllipsoide() throws InterpreteException {
        InterpreteTRIEllipsoide interpreteH = new InterpreteTRIEllipsoide();
        TRIEllipsoide e = new TRIEllipsoide(new Point3D(0, 0, 0), new Point3D(1, 2, 3));
        try {
            e = (TRIEllipsoide) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (InterpreteException ex) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("TRIELLIPSOIDE :  ERREUR D'ANALYSE SYNTAXIQUE "), ex);
        }
        return e;
    }

    public TRISphere interpreteTRISphere() throws InterpreteException {
        InterpreteTRISphere interpreteH = new InterpreteTRISphere();
        TRISphere s = new TRISphere(new Point3D(0, 0, 0), 1.0);
        try {
            s = (TRISphere) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (InterpreteException ex) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("TRISPHERE :  ERREUR D'ANALYSE SYNTAXIQUE "), ex);
        }
        return s;
    }

    public void interpreteMODTranslation(Representable r) {
    }

    public void interpreteMODRotation(Representable r) {
    }

    public void interpreteMODHomothetie(Representable r) {
    }

    Polygone interpretePolygone() throws InterpreteException {
        InterpretePolygone interpreteH = new InterpretePolygone();
        Polygone s = null;
        try {
            s = (Polygone) interpreteH.interprete(text, pos);
            pos = interpreteH.getPosition();
        } catch (InterpreteException ex) {
            throw new InterpreteException(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/pushmatrix/scripts/InterpreteLangage").getString("TRISPHERE :  ERREUR D'ANALYSE SYNTAXIQUE "), ex);
        }
        return s;
    }

    void setRépertoire(String répertoire) {
        this.répertoire = répertoire;
    }
}