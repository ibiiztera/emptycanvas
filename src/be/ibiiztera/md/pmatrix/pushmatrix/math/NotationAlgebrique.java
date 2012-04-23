/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.pushmatrix.math;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Manuel DAHMEN
 */
public class NotationAlgebrique {
    public class Bloc
    {
        public TypeBloc type;
        public String chaine;
        public int start;
        public int end;
        public ArrayList<Bloc> contenu = new ArrayList<Bloc>();
        public String fonction;
    }
    public String polonaise(String algebrique)
    {
                int l = algebrique.length();
        ArrayList<Bloc> blocs = new ArrayList<Bloc>();
        TreeMap<String, String> arbre = new TreeMap<String, String>();
        String polonaise = "";
        /// rechercher les parentheses et fonctions
        
        int [] startParentheses = new int[algebrique.length()/2];
        int []   endParentheses = new int[algebrique.length()/2];
        int  startParenthesesCpt = 0;
        int    endParenthesesCpt = 0;
        for(int i=0; i<l; i++)
        {
            // recherche de fonction et de parenthese
            if(algebrique.charAt(i)=='(')
                startParentheses[startParenthesesCpt++] = i;
            else if(algebrique.charAt(i)==')')
            {
                endParentheses[endParenthesesCpt++] = i;
                int start = i;
                int end = i-1;
                int cpt = 1;
                while(end>=0)
                {
                    if(algebrique.charAt(i)==')')
                        cpt++;
                    if(algebrique.charAt(i)=='(')
                        cpt--;
                    if(cpt==0)
                    {
                        Bloc b = new Bloc();
                        b.chaine = algebrique.substring(end, start);
                        b.contenu = null;
                        b.start = end;
                        b.end = start;
                        if(end==0)
                            b.type = TypeBloc.PARENTHESE;
                        else
                        {
                            if(Character.isLetter(algebrique.charAt(end-1)))
                            {
                                b.type = TypeBloc.FONCTION;
                                int cptFct = 0;
                                while(end-1+cptFct>=0 && Character.isLetter(algebrique.charAt(end-1+cptFct)))
                                    cptFct--;
                                b.fonction = algebrique.substring(end+cptFct, end-1);
                            }
                            
                        }
                    }
                    i--;
                }
            }
            char c =algebrique.charAt(i);
            switch(c)
                {
                case '*':
                    if(algebrique.charAt(i+1) =='*')
                        c='^';
                case '+':
                case '-':
                case '/':
                case '^':
                    Bloc b = new Bloc();
                    b.chaine = ""+c;
                    b.contenu = null;
                    b.start = i;
                    b.end = c=='^'?i+2:i+1;
                    if(c=='^') i++;
                    b.type = TypeBloc.OPERATEUR;
                    break;
            }
            // Recherche des variables
            if(Character.isLetter(algebrique.charAt(i)))
            {
                int cpt = i;
                boolean fct = false;
                while(cpt<l && Character.isLetter(cpt))
                    cpt++;
                if(algebrique.charAt(cpt)=='(')
                    fct = true;
                
                Bloc b = new Bloc();
                b.chaine = algebrique.substring(i, cpt-1);
                b.contenu = null;
                b.end = cpt-1;
                b.start = i;
                b.type = fct?TypeBloc.FONCTION:TypeBloc.VARIABLE;
            }
        } 
        // Construire l'arbre à partir de la notation algébrique
        // TODO: 
        
        return polonaise;
    }
    
    public static void main(String [] args)
{
    String [] str = new String []
    {
        "a*b+4", "a b * 4 +"
    };
    
}
}
