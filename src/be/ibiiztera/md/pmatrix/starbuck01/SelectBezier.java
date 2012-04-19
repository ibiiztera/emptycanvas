/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
