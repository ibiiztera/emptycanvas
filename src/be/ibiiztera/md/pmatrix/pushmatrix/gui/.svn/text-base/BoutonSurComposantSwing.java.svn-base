package be.ibiiztera.md.pmatrix.pushmatrix.gui;

import java.awt.*;
import javax.swing.*;

public class BoutonSurComposantSwing extends JPanel {

    private Thread action;
    private int x;
    private int y;
    private String texte;
    private boolean actif;
    private boolean enfonce;
    private int millisEnfonce = 100;
    private JComponent parent;
    private Color cBordure = Color.black;
    private Color cFond = Color.white;
    private Color cTexte = Color.black;
    private Color cEnfonce = Color.red;
    private Color cInactif = Color.yellow;

    public BoutonSurComposantSwing(int px, int py, String pt) {
        x = px;
        y = py;
        texte = pt;
        setMinimumSize(new Dimension(40,20));
    }
/*
    @Override
    public Graphics getGraphics() {
        return parent.getGraphics();
    }
*/
    @Override
    public void paintComponent(Graphics g2) {
        Color pBordure = cBordure;
        Color pFond = actif ? cFond : cInactif;
        Color pTexte = cTexte;
        g2.setColor(pFond);
        g2.fillRect(x, y, x + 40, y + 20);
        g2.setColor(pBordure);
        g2.drawRect(x, y, x + 40, y + 20);
        g2.setColor(pTexte);
        g2.drawString(texte, x + 5, y + 5);
    }
}
