/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelDocumentManager.java
 *
 * Created on 9 févr. 2010, 18:53:51
 */

package org.unesco.jisis.dbservercontentmanager;

import javax.swing.DefaultListModel;
import org.unesco.jisis.jisiscore.client.ClientDatabaseProxy;

/**
 *
 * @author jcd
 */
public class PanelDocumentManager extends javax.swing.JPanel {
    private ClientDatabaseProxy db_;
    private DefaultListModel modelServerList_;


    /** Creates new form PanelDocumentManager */
    public PanelDocumentManager(ClientDatabaseProxy db) {
       db_ = db;
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

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 400, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 300, Short.MAX_VALUE)
      );
   }// </editor-fold>//GEN-END:initComponents


   // Variables declaration - do not modify//GEN-BEGIN:variables
   // End of variables declaration//GEN-END:variables

}
