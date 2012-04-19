package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.util.ArrayList;
import javax.swing.JDialog;

/**
 *
 * @author MANUEL DAHMEN
 * @since 2012-04-15EUR_DISQUE = 100002;
    public final int TYPE_ERR
 */
public class GestionnaireErreur {
    public final int PHASE_INIT = 10000;
    public final int PHASE_NEWJOB = 10001;
    public final int PHASE_RUN = 10002;
    public final int PHASE_END = 10003;
    public final int GET_CONTEXT = 100;
    public final int ADD_CONTEXT = 101;
    public final int CTX_CONSOLE_BATCH_SINGLE = 1000;
    public final int CTX_CONSOLE_BATCH_VIDEO = 1001;
    public final int CTX_CONSOLE_SINGLE = 1002;
    public final int CTX_CONSOLE_VIDEO = 1003;
    public final int CTX_GUI_TEXT_ENCODING_LOAD = 1010;
    public final int CTX_GUI_TEXT_ENCODING_UPDATE = 1011;
    public final int CTX_GUI_TEXT_ENCODING_SUBMIT = 1012;
    public final int GET_COURANT = 0;
    public final int GET_DERNIER = 1;
    public final int SET_COURANT = 10;
    public final int SET_DERNIER = 11;
    public final int DEL_COURANT = 20;
    public final int DEL_DERNIER = 21;
    // DISQUE
    public final int TYPE_FICHIER_NO_TROUVE = 100001;
    public final int TYPE_ERREUR_DISQUE = 100002;
    public final int TYPE_ERREUR_LOAD_IMGE = 100003;
    // SYNTAXE FICHIER SCRIPT
    public final int TYPE_NOMBRE_VALIDE= 100004;
    public final int TYPE_PARENTHESE_GAUCHE_MANQUANTE= 100005;
    public final int TYPE_PARENTHESE_GAUCHE_NON_ATTENDUE= 100006;
    public final int TYPE_PARENTHESE_DROITE_MANQUANTE= 100007;
    public final int TYPE_PARENTHESE_DROITE_NON_ATTENDUE= 100008;
    public final int TYPE_ID_STRING= 100009;
    public final int TYPE_VIRGULE_MANQUANTE= 100010;
    public final int TYPE_VIRGULE_NON_ATTENDUE= 100011;
    public final int TYPE_ESPACE_REQUIS= 100012;
    public final int TYPE_PARENTHESE= 100013;
    public final int TYPE_ELEMENT_NON_ATTENDU = 100014;
    // 
    
    private ArrayList<Erreur> liste = new ArrayList<Erreur> ();
    private GestionnaireErreur ge;
    private int phase = PHASE_INIT;
    private int context =  CTX_CONSOLE_BATCH_SINGLE;
            
    public class Erreur
    {
        private Exception ex;
        
        public int phase;
        public int context;
        public boolean solved;
        public int datetime;
        public int type;        
        public boolean estCatchee()
        {
            return ex!=null;
        }
        public Exception catchee()
        {
            return ex;
        }
        public void catchee(Exception ex)
        {
            this.ex = ex;
        }
        public ListeDialog solutionGUI()
        {
            return null;
        }
        public Text solutionText()
        {
            return new Text();
        }
    }
    public class Text 
    {
        public String html = "";
        public String text = "";
        public boolean isHTML = false;
        public String getString()
        {
            return isHTML ? html : text;
        }
        public void setString(String t)
        {
            if(isHTML)
                html = t;
            else
                text = t;
        }
    }
    private GestionnaireErreur()
    {
        ge = this;
    }
    public GestionnaireErreur ge()
    {
       if(ge==null)
           ge = new GestionnaireErreur();
       return ge;
    }
}
