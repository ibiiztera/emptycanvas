
/*
 * NEWMain.java
 *
 * @author DAHMEN Manuel
 *
 * Created on 17-mars-2012, 12:16:55
 */

package be.ibiiztera.md.pmatrix.starbuck02;

import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import javax.swing.JFileChooser;

/**
 *
 * @author manuel
 */
public class NEWMain extends javax.swing.JFrame {
    private String bundlePackage = "be/ibiiztera/md/pmatrix/starbuck02/";
    private RenderPreviewPanel rpp = null;
    private PreviewControleur pc = new PreviewControleurConcrete();
    protected String txtCHEMIN;
     /** Creates new form NEWMain */
    public NEWMain() {
        initComponents();
	//        String h = System.getProperty(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/starbuck02/Bundle").getString("USER_HOME"));
        //String p = System.getProperty(java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/starbuck02/Bundle").getString("FILE_SEPARATOR"));
        //txtCHEMIN = h + p + java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/starbuck02/Bundle").getString("PMMATRIX_DATA") + p + java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/starbuck02/Bundle").getString("TESTSCRIPTS");

        rpp = new RenderPreviewPanel();
        rpp.setView(pc);
        this.splitEdtorView.setLeftComponent(rpp);
        rpp.run();

        pc.asssignerVue(rpp);
        pc.definirModele(new Scene());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitEdtorView = new javax.swing.JSplitPane();
        jScrollPaneEditor = new javax.swing.JScrollPane();
        controlsEditor1 = new be.ibiiztera.md.pmatrix.starbuck02.ControlsEditor();
        previewEditor1 = new be.ibiiztera.md.pmatrix.starbuck02.PreviewEditor();
        cg_edt = new javax.swing.JButton();
        sv_edt = new javax.swing.JButton();
        jButtonRenduFichier = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFichier = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuEdite = new javax.swing.JMenu();
        jCheckBoxMenuItemEditerLaScene = new javax.swing.JCheckBoxMenuItem();
        jMenuOptions = new javax.swing.JMenu();
        jMenuItemLangue = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("Form"); // NOI18N

        splitEdtorView.setName("splitEdtorView"); // NOI18N

        jScrollPaneEditor.setName("jScrollPaneEditor"); // NOI18N

        controlsEditor1.setName("controlsEditor1"); // NOI18N
        jScrollPaneEditor.setViewportView(controlsEditor1);

        splitEdtorView.setRightComponent(jScrollPaneEditor);

        previewEditor1.setName("previewEditor1"); // NOI18N

        javax.swing.GroupLayout previewEditor1Layout = new javax.swing.GroupLayout(previewEditor1);
        previewEditor1.setLayout(previewEditor1Layout);
        previewEditor1Layout.setHorizontalGroup(
            previewEditor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        previewEditor1Layout.setVerticalGroup(
            previewEditor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        splitEdtorView.setLeftComponent(previewEditor1);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/starbuck02/Bundle"); // NOI18N
        cg_edt.setText(bundle.getString("ChargerEditeur_btn")); // NOI18N
        cg_edt.setName("cg_edt"); // NOI18N
        cg_edt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cg_edtActionPerformed(evt);
            }
        });

        sv_edt.setText(bundle.getString("updateModel_btn")); // NOI18N
        sv_edt.setName("sv_edt"); // NOI18N
        sv_edt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sv_edtActionPerformed(evt);
            }
        });

        jButtonRenduFichier.setText(bundle.getString("RENDUFICHIER")); // NOI18N
        jButtonRenduFichier.setName("jButtonRenduFichier"); // NOI18N
        jButtonRenduFichier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRenduFichierActionPerformed(evt);
            }
        });

        jButton4.setText(bundle.getString("btn4")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        jButton5.setText(bundle.getString("btn5")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N

        jButton6.setText(bundle.getString("btn6")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N

        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("be/ibiiztera/md/pmatrix/starbuck01/Bundle"); // NOI18N
        jTextField1.setText(bundle1.getString("textfield1")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel1.setText(null);
        jLabel1.setName("jLabel1"); // NOI18N

        jFormattedTextField1.setText(null);
        jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenuFichier.setText(bundle.getString("MainMenuFile_menu")); // NOI18N
        jMenuFichier.setName("jMenuFichier"); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText(bundle.getString("MenuFichierOuvrir_menuitem")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText(bundle.getString("FileMenuSave_menuitem")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItem2);

        jMenuBar1.add(jMenuFichier);

        jMenuEdite.setText(bundle.getString("EditMenu_menu")); // NOI18N
        jMenuEdite.setName("jMenuEdite"); // NOI18N

        jCheckBoxMenuItemEditerLaScene.setSelected(true);
        jCheckBoxMenuItemEditerLaScene.setText(bundle.getString("EDITER_MENU_EDITSCENE")); // NOI18N
        jCheckBoxMenuItemEditerLaScene.setName("jCheckBoxMenuItemEditerLaScene"); // NOI18N
        jCheckBoxMenuItemEditerLaScene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemEditerLaSceneActionPerformed(evt);
            }
        });
        jMenuEdite.add(jCheckBoxMenuItemEditerLaScene);

        jMenuBar1.add(jMenuEdite);

        jMenuOptions.setText(bundle.getString("OPTIONS")); // NOI18N
        jMenuOptions.setName("jMenuOptions"); // NOI18N

        jMenuItemLangue.setText(bundle.getString("LANGUE")); // NOI18N
        jMenuItemLangue.setName("jMenuItemLangue"); // NOI18N
        jMenuOptions.add(jMenuItemLangue);

        jMenuBar1.add(jMenuOptions);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(splitEdtorView, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(cg_edt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sv_edt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRenduFichier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cg_edt)
                    .addComponent(sv_edt)
                    .addComponent(jButtonRenduFichier)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(splitEdtorView, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
                JFileChooser chooser = new JFileChooser(txtCHEMIN);
                chooser.setDialogType(JFileChooser.OPEN_DIALOG);
                chooser.showOpenDialog(this);
                if (chooser.getSelectedFile() != null) {
                    pc.chargerModele(chooser.getSelectedFile());
                    pc.modeleModifie();
                    controlsEditor1.setText(pc.modeleTXT());
                }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

                private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
                                 JFileChooser chooser = new JFileChooser(
                        txtCHEMIN);
                chooser.setDialogType(JFileChooser.SAVE_DIALOG);
                chooser.showOpenDialog(this);
                if (chooser.getSelectedFile() != null) {
                    pc.sauvegarderModele(chooser.getSelectedFile());
                }
                

                }//GEN-LAST:event_jMenuItem2ActionPerformed

                private void cg_edtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cg_edtActionPerformed
                    controlsEditor1.setText(pc.modeleTXT());
                }//GEN-LAST:event_cg_edtActionPerformed

                private void sv_edtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sv_edtActionPerformed
                    if(pc.modifierModele(controlsEditor1.getText()))
                    {
                        pc.modeleModifie();
                        controlsEditor1.setText(pc.modeleTXT());
                        jFormattedTextField1.setText(java.util.ResourceBundle.getBundle(bundlePackage+"Bundle").getString("NOUVEAU MODELE OK"));
                    }
                    else
                    {
                        jFormattedTextField1.setText(java.util.ResourceBundle.getBundle(bundlePackage+"Bundle").getString("ERREUR"));

                    }
                }//GEN-LAST:event_sv_edtActionPerformed

                private void jButtonRenduFichierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRenduFichierActionPerformed
                    pc.renduFichier();
                }//GEN-LAST:event_jButtonRenduFichierActionPerformed

                private void jCheckBoxMenuItemEditerLaSceneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemEditerLaSceneActionPerformed
                   if(jCheckBoxMenuItemEditerLaScene.isSelected())
                   {
                       System.out.println("IS SELECTED: TRUE");
                   }
                   else
                   {
                       System.out.println("IS NOT SELECTED");
                   }
                   controlsEditor1.setControls(jCheckBoxMenuItemEditerLaScene.isSelected());
                }//GEN-LAST:event_jCheckBoxMenuItemEditerLaSceneActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NEWMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cg_edt;
    private be.ibiiztera.md.pmatrix.starbuck02.ControlsEditor controlsEditor1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonRenduFichier;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemEditerLaScene;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdite;
    private javax.swing.JMenu jMenuFichier;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemLangue;
    private javax.swing.JMenu jMenuOptions;
    private javax.swing.JScrollPane jScrollPaneEditor;
    private javax.swing.JTextField jTextField1;
    private be.ibiiztera.md.pmatrix.starbuck02.PreviewEditor previewEditor1;
    private javax.swing.JSplitPane splitEdtorView;
    private javax.swing.JButton sv_edt;
    // End of variables declaration//GEN-END:variables

}
