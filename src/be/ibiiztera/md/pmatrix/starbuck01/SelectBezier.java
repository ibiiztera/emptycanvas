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

package be.ibiiztera.md.pmatrix.starbuck01;

import be.ibiiztera.md.pmatrix.pushmatrix.BezierCubique;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author manuel
 */
public class SelectBezier extends BufferedImage {
/***
 * Ca n'ira pas comme cela
 */
    private int height = 40;
    private int pad = 10;
    private int width = (height+pad)*3;
    private ArrayList<BezierCubique> bc = new ArrayList<BezierCubique>();
    private int element = 0;
    private final JPanel p;

    public SelectBezier(JPanel p) {
        super(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.p  = p;
    }


    public void loadSelection(Scene sc) {
        bc = new ArrayList<BezierCubique>();
        Iterator<Representable> it = sc.iterator();
        while (it.hasNext()) {
            Representable testElem = it.next();
            if (testElem instanceof BezierCubique) {
                bc.add((BezierCubique) testElem);
            }
        }
        if (bc.size() > 0) {
            element = 0;
        }
        genererImage();
    }

    public void nextElement() {
        if (element < bc.size() - 1) {
            element++;
        }
        genererImage();
    }

    public void prevElement() {
        if (element > 0) {
            element--;
        }
        genererImage();
    }

    public BezierCubique selectionElement() {

        return fermerEditeur();
    }

    private void genererImage() {
        Graphics g = p.getGraphics();
        int totalWidth = p.getWidth();
        int totalHeight = p.getHeight();
        g.setColor(Color.RED);
        g.drawRect(0, totalHeight - height - pad, width / 3, totalHeight - pad);
        g.setColor(Color.RED);
        g.drawRect(width / 3, totalHeight - height - pad, 2 * width / 3, totalHeight - pad);
        g.setColor(Color.RED);
        g.drawRect(2 * width / 3, totalHeight - height - pad, width, totalHeight - pad);
    }

    public BezierCubique fermerEditeur()
    {
        return bc.get(element);
    }
}
