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
/*
 * ControlsEditor.java
 *
 * Created on 17-mars-2012, 12:23:00
 */

package be.ibiiztera.md.pmatrix.starbuck02;

/**
 *
 * @author manuel
 */
public class ControlsEditor extends javax.swing.JTextPane {
    private boolean controls = false;

    public void setControls(boolean controls) {
        this.controls = controls;
    }
    
    /** Creates new form ControlsEditor */
    public ControlsEditor() {
        initComponents();
        add(jPopupMenuEdit);
        //jPopupMenuEdit.setVisible(true);
        setInheritsPopupMenu(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuEdit = new javax.swing.JPopupMenu();
        jMenuItemCouper = new javax.swing.JMenuItem();
        jMenuItemCopier = new javax.swing.JMenuItem();
        jMenuItemColler = new javax.swing.JMenuItem();
        jMenuItemCompleter = new javax.swing.JMenuItem();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/starbuck02/Bundle"); // NOI18N
        jMenuItemCouper.setText(bundle.getString("EDITOR POPUP COUPER")); // NOI18N
        jMenuItemCouper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouperActionPerformed(evt);
            }
        });
        jPopupMenuEdit.add(jMenuItemCouper);

        jMenuItemCopier.setText(bundle.getString("EDITOR_POPUP_COPIER")); // NOI18N
        jMenuItemCopier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopierActionPerformed(evt);
            }
        });
        jPopupMenuEdit.add(jMenuItemCopier);

        jMenuItemColler.setText(bundle.getString("EDITOR_POPUP_COLLER")); // NOI18N
        jPopupMenuEdit.add(jMenuItemColler);

        jMenuItemCompleter.setText(bundle.getString("EDITOR_POPUP_COMPLETER")); // NOI18N
        jPopupMenuEdit.add(jMenuItemCompleter);

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCouperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCouperActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemCouperActionPerformed

    private void jMenuItemCopierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemCopierActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
    }//GEN-LAST:event_formKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItemColler;
    private javax.swing.JMenuItem jMenuItemCompleter;
    private javax.swing.JMenuItem jMenuItemCopier;
    private javax.swing.JMenuItem jMenuItemCouper;
    private javax.swing.JPopupMenu jPopupMenuEdit;
    // End of variables declaration//GEN-END:variables

}
