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


package ibiztetra20;

import java.awt.Color;
import java.awt.Font;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import javax.swing.undo.UndoableEdit;

/**
 *
 * @author manuel
 */
public class MainGui extends JFrame{
    private String docString = "Scene(\n\t\n)\n";
    private String erreur = "";
    private class SceneDocument implements StyledDocument
    {
        private Properties props = new Properties();
        
        public Style addStyle(String nm, Style parent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void removeStyle(String nm) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Style getStyle(String nm) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setCharacterAttributes(int offset, int length, AttributeSet s, boolean replace) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setParagraphAttributes(int offset, int length, AttributeSet s, boolean replace) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setLogicalStyle(int pos, Style s) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Style getLogicalStyle(int p) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Element getParagraphElement(int pos) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Element getCharacterElement(int pos) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Color getForeground(AttributeSet attr) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Color getBackground(AttributeSet attr) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Font getFont(AttributeSet attr) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int getLength() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void addDocumentListener(DocumentListener listener) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void removeDocumentListener(DocumentListener listener) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void addUndoableEditListener(UndoableEditListener listener) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void removeUndoableEditListener(UndoableEditListener listener) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Object getProperty(Object key) {
            return props.get(key);
        }

        public void putProperty(Object key, Object value) {
            props.put(key, value);
        }

        public void remove(int offs, int len) throws BadLocationException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public String getText(int offset, int length) throws BadLocationException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void getText(int offset, int length, Segment txt) throws BadLocationException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Position getStartPosition() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Position getEndPosition() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Position createPosition(int offs) throws BadLocationException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Element[] getRootElements() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Element getDefaultRootElement() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void render(Runnable r) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public MainGui()
    {
        super("Fenetre principale");
        SceneDocument d = new SceneDocument();
        add(new JTextPane(d));
        pack();
    }

    public static void main(String [] args)
    {
        new MainGui().setVisible(true);
    }
}
