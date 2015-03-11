/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HitSortFilePanel.java
 *
 * Created on Jan 14, 2009, 5:52:37 PM
 */

package org.unesco.jisis.gui;

import org.unesco.jisis.jisiscore.client.GuiGlobal;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultComboBoxModel;
import org.unesco.jisis.corelib.common.Global;


/**
 *
 * @author jc_dauphin
 */
public class HitSortFilePanel extends javax.swing.JPanel implements Observer {

   protected String selectedHitSortFileName_ = null;

   /** Creates new form HitSortFilePanel */
    public HitSortFilePanel() {
        initComponents();
        String[] hitSortFileNames = null;

         hitSortFileNames = getHitSortFileNames();

         cmbSelectHitSortFile.setModel(new DefaultComboBoxModel(hitSortFileNames));
         selectedHitSortFileName_ = hitSortFileNames[0];
         /** Register to be notified when there is a change in Hit Sort Files */
         GuiGlobal.addNewHitSortFileObserver(this);
    }

    private String[] getHitSortFileNames() {
        File     file    = new File(Global.getClientWorkPath());
        String[] tmpHitSortFileNames = file.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (name.endsWith(Global.HIT_SORT_FILE_EXT)) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        String[]  hitSortFileNames = new String[tmpHitSortFileNames.length+1];
        hitSortFileNames[0] = "<none>";
        for (int i = 0; i < tmpHitSortFileNames.length; i++) {
            hitSortFileNames[i+1] = tmpHitSortFileNames[i].substring(0,
                tmpHitSortFileNames[i].length() - Global.HIT_SORT_FILE_EXT.length());
        }

        return hitSortFileNames;
    }

   @Override
    public void setEnabled(boolean flag) {
       cmbSelectHitSortFile.setEnabled(flag);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      lblHitSortFile = new javax.swing.JLabel();
      cmbSelectHitSortFile = new javax.swing.JComboBox();

      lblHitSortFile.setFont(new java.awt.Font("Tahoma", 1, 11));
      lblHitSortFile.setText(org.openide.util.NbBundle.getMessage(HitSortFilePanel.class, "HitSortFilePanel.lblHitSortFile.text")); // NOI18N

      cmbSelectHitSortFile.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
      cmbSelectHitSortFile.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmbSelectHitSortFileActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblHitSortFile)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cmbSelectHitSortFile, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(lblHitSortFile)
               .addComponent(cmbSelectHitSortFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
      );
   }// </editor-fold>//GEN-END:initComponents

    private void cmbSelectHitSortFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectHitSortFileActionPerformed
       int index = cmbSelectHitSortFile.getSelectedIndex();
       if (index < 0)
          return;
       selectedHitSortFileName_ = (String) cmbSelectHitSortFile.getSelectedItem();
       GuiGlobal.setHitSortFileName(selectedHitSortFileName_);
      System.out.println("HitsortFileName="+GuiGlobal.getHitSortFileName());
    }//GEN-LAST:event_cmbSelectHitSortFileActionPerformed

    public String getSelectedHitSortFileName() {
       return selectedHitSortFileName_;
    }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JComboBox cmbSelectHitSortFile;
   private javax.swing.JLabel lblHitSortFile;
   // End of variables declaration//GEN-END:variables

   public void update(Observable arg0, Object arg1) {
      String[] hitSortFileNames = getHitSortFileNames();

      cmbSelectHitSortFile.setModel(new DefaultComboBoxModel(hitSortFileNames));
      selectedHitSortFileName_ = hitSortFileNames[0];
   }

}
