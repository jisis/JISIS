/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DatabaseTemplatePanel.java
 *
 * Created on Mar 6, 2009, 4:51:24 PM
 */

package org.unesco.jisis.wizards.dbcreate;

/**
 *
 * @author jc_dauphin
 */
public class DatabaseTemplatePanel extends javax.swing.JPanel {

    /** Creates new form DatabaseTemplatePanel */
    public DatabaseTemplatePanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      buttonGroup1 = new javax.swing.ButtonGroup();
      jLabel1 = new javax.swing.JLabel();
      rdbManual = new javax.swing.JRadioButton();
      rdbMarc21 = new javax.swing.JRadioButton();
      rdbUnimarc = new javax.swing.JRadioButton();
      rdbMarc21Lite = new javax.swing.JRadioButton();
      rdbExistingDb = new javax.swing.JRadioButton();

      jLabel1.setText(org.openide.util.NbBundle.getMessage(DatabaseTemplatePanel.class, "DatabaseTemplatePanel.jLabel1.text")); // NOI18N

      buttonGroup1.add(rdbManual);
      rdbManual.setSelected(true);
      rdbManual.setText(org.openide.util.NbBundle.getMessage(DatabaseTemplatePanel.class, "DatabaseTemplatePanel.rdbManual.text")); // NOI18N

      buttonGroup1.add(rdbMarc21);
      rdbMarc21.setText(org.openide.util.NbBundle.getMessage(DatabaseTemplatePanel.class, "DatabaseTemplatePanel.rdbMarc21.text")); // NOI18N

      buttonGroup1.add(rdbUnimarc);
      rdbUnimarc.setText(org.openide.util.NbBundle.getMessage(DatabaseTemplatePanel.class, "DatabaseTemplatePanel.rdbUnimarc.text")); // NOI18N

      buttonGroup1.add(rdbMarc21Lite);
      rdbMarc21Lite.setText(org.openide.util.NbBundle.getMessage(DatabaseTemplatePanel.class, "DatabaseTemplatePanel.rdbMarc21Lite.text")); // NOI18N

      rdbExistingDb.setText(org.openide.util.NbBundle.getMessage(DatabaseTemplatePanel.class, "DatabaseTemplatePanel.rdbExistingDb.text")); // NOI18N

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jLabel1))
               .addGroup(layout.createSequentialGroup()
                  .addGap(103, 103, 103)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(rdbMarc21)
                     .addComponent(rdbUnimarc)
                     .addComponent(rdbManual)
                     .addComponent(rdbMarc21Lite)
                     .addComponent(rdbExistingDb))))
            .addContainerGap(180, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(78, 78, 78)
            .addComponent(jLabel1)
            .addGap(34, 34, 34)
            .addComponent(rdbManual)
            .addGap(18, 18, 18)
            .addComponent(rdbMarc21)
            .addGap(18, 18, 18)
            .addComponent(rdbUnimarc)
            .addGap(18, 18, 18)
            .addComponent(rdbMarc21Lite)
            .addGap(18, 18, 18)
            .addComponent(rdbExistingDb)
            .addContainerGap(101, Short.MAX_VALUE))
      );
   }// </editor-fold>//GEN-END:initComponents


   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.ButtonGroup buttonGroup1;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JRadioButton rdbExistingDb;
   private javax.swing.JRadioButton rdbManual;
   private javax.swing.JRadioButton rdbMarc21;
   private javax.swing.JRadioButton rdbMarc21Lite;
   private javax.swing.JRadioButton rdbUnimarc;
   // End of variables declaration//GEN-END:variables

}
