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
