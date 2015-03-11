package org.unesco.jisis.datadefinition.wks;

import java.awt.BorderLayout;
import java.util.MissingResourceException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.openide.util.Exceptions;
import org.openide.DialogDisplayer;
import org.openide.ErrorManager;
import org.openide.NotifyDescriptor;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.unesco.jisis.corelib.client.ConnectionInfo;
import org.unesco.jisis.corelib.client.ConnectionPool;
import org.unesco.jisis.corelib.common.IDatabase;
import org.unesco.jisis.corelib.common.WorksheetDef;
import org.unesco.jisis.corelib.exceptions.DbException;
import org.unesco.jisis.corelib.exceptions.DefaultDBNotFoundException;
import org.unesco.jisis.corelib.exceptions.GeneralDatabaseException;
import org.unesco.jisis.corelib.exceptions.ResourceNotFoundException;
import org.unesco.jisis.jisiscore.client.GuiGlobal;
import org.unesco.jisis.jisiscore.client.ClientDatabaseProxy;

/**
 * Top component which displays something.
 */
final class WKSEditTopComponent extends TopComponent implements TableModelListener {
    
    private static WKSEditTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "org/unesco/jisis/datadefinition/wks/wks.png";
    
    private static final String PREFERRED_ID = "WKSEditTopComponent";
    
    private WKSVisualPanel wksVisualPanel_ = null;
    private ClientDatabaseProxy db_ = null;

    private String currentWksName_;
    
    private boolean wksChanged_ = false;
    
    public WKSEditTopComponent(IDatabase db) {

      if (db instanceof ClientDatabaseProxy) {
         db_ = (ClientDatabaseProxy) db;
      } else {
         throw new RuntimeException("RecordDataBrowserTopComponent: Cannot cast DB to ClientDatabaseProxy");
      }
      /* Register this TopComponent as attached to this DB */
      db_.addWindow(this);

      initComponents();
      try {
         setName(NbBundle.getMessage(WKSEditTopComponent.class, "CTL_WKSEditTopComponent") 
                 + " (" +db.getDbHome()+"//"+ db.getDatabaseName() + ")");
      } catch (MissingResourceException ex) {
         new ResourceNotFoundException(ex).displayWarning();
      } catch (DbException ex) {
         new GeneralDatabaseException(ex).displayWarning();
      }
      setToolTipText(NbBundle.getMessage(WKSEditTopComponent.class, "HINT_WKSEditTopComponent"));
      setIcon(ImageUtilities.loadImage(ICON_PATH, true));

       try {
          String[] worksheetNames = db.getWorksheetNames();
          currentWksName_ = worksheetNames[0];

          cmbWorkSheets.setModel(new DefaultComboBoxModel(worksheetNames));
          wksVisualPanel_ = new WKSVisualPanel(db, cmbWorkSheets.getSelectedItem().toString());
          mainPanel.add(wksVisualPanel_, BorderLayout.CENTER);
       } catch (DbException ex) {
          new GeneralDatabaseException(ex).displayWarning();
       }
       wksChanged_ = false;
       btnSave.setEnabled(wksChanged_);
       wksVisualPanel_.getWKSModel().addTableModelListener((TableModelListener) this);
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        ctrlPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        wksLabel = new javax.swing.JLabel();
        cmbWorkSheets = new javax.swing.JComboBox();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnNew = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnDel = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnRefresh = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnSave = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        bntClose = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mainPanel.setLayout(new java.awt.BorderLayout());
        add(mainPanel, java.awt.BorderLayout.CENTER);

        ctrlPanel.setPreferredSize(new java.awt.Dimension(100, 40));

        jToolBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        org.openide.awt.Mnemonics.setLocalizedText(wksLabel, org.openide.util.NbBundle.getMessage(WKSEditTopComponent.class, "LBL_WKS")); // NOI18N
        jToolBar1.add(wksLabel);

        cmbWorkSheets.setToolTipText(org.openide.util.NbBundle.getMessage(WKSEditTopComponent.class, "HINT_WKSSelect")); // NOI18N
        cmbWorkSheets.setMaximumSize(new java.awt.Dimension(150, 32767));
        cmbWorkSheets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbWorkSheetsActionPerformed(evt);
            }
        });
        jToolBar1.add(cmbWorkSheets);
        jToolBar1.add(jSeparator5);

        org.openide.awt.Mnemonics.setLocalizedText(btnNew, org.openide.util.NbBundle.getMessage(WKSEditTopComponent.class, "BTN_NEW")); // NOI18N
        btnNew.setToolTipText("Create a new worksheet definition");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNew);
        jToolBar1.add(jSeparator1);

        org.openide.awt.Mnemonics.setLocalizedText(btnDel, org.openide.util.NbBundle.getMessage(WKSEditTopComponent.class, "BTN_DEL")); // NOI18N
        btnDel.setToolTipText("Delete the current worksheet definition");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDel);
        jToolBar1.add(jSeparator2);

        org.openide.awt.Mnemonics.setLocalizedText(btnRefresh, org.openide.util.NbBundle.getMessage(WKSEditTopComponent.class, "BTN_Refresh")); // NOI18N
        btnRefresh.setToolTipText("Restore last saved content");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRefresh);
        jToolBar1.add(jSeparator3);

        org.openide.awt.Mnemonics.setLocalizedText(btnSave, org.openide.util.NbBundle.getMessage(WKSEditTopComponent.class, "BTN_Save")); // NOI18N
        btnSave.setToolTipText("Save worksheet definition");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSave);
        jToolBar1.add(jSeparator4);

        org.openide.awt.Mnemonics.setLocalizedText(bntClose, org.openide.util.NbBundle.getMessage(WKSEditTopComponent.class, "BTN_Close")); // NOI18N
        bntClose.setToolTipText("Close current worksheet definition");
        bntClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCloseActionPerformed(evt);
            }
        });
        jToolBar1.add(bntClose);

        javax.swing.GroupLayout ctrlPanelLayout = new javax.swing.GroupLayout(ctrlPanel);
        ctrlPanel.setLayout(ctrlPanelLayout);
        ctrlPanelLayout.setHorizontalGroup(
            ctrlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctrlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );
        ctrlPanelLayout.setVerticalGroup(
            ctrlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctrlPanelLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        add(ctrlPanel, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void bntCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCloseActionPerformed
       close();
    }//GEN-LAST:event_bntCloseActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed

       String label = NbBundle.getMessage(WKSEditTopComponent.class, "MSG_DeleteWKSLabel");
       String title = NbBundle.getMessage(WKSEditTopComponent.class, "MSG_DeleteWKSDialogTitle");

       NotifyDescriptor d =
               new NotifyDescriptor.Confirmation(label, title,
               NotifyDescriptor.OK_CANCEL_OPTION);
       if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
          try {
             db_.removeWorksheetDef(cmbWorkSheets.getSelectedItem().toString());
             int i = cmbWorkSheets.getSelectedIndex();
             cmbWorkSheets.removeItemAt(i);

             String[] worksheetNames = db_.getWorksheetNames();
             cmbWorkSheets.setModel(new DefaultComboBoxModel(worksheetNames));
             cmbWorkSheets.setSelectedItem(0);
          } catch (DbException ex) {
             new GeneralDatabaseException(ex).displayWarning();
          }
       }



    }//GEN-LAST:event_btnDelActionPerformed
    
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        wksVisualPanel_.load(db_, cmbWorkSheets.getSelectedItem().toString());
        wksChanged_ = false;
        btnSave.setEnabled(wksChanged_);
        wksVisualPanel_.getWKSModel().addTableModelListener((TableModelListener) this);
        wksVisualPanel_.getWKSModel().resetChanged();
        wksVisualPanel_.getWKSModel().fireTableDataChanged();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private boolean checkWksSaved() {

      if (wksChanged_) {
         NotifyDescriptor nd = new NotifyDescriptor.Confirmation(
                 "The Current Worksheet was modified!\nDo you want to save it?",
                 NotifyDescriptor.YES_NO_CANCEL_OPTION,
                 NotifyDescriptor.QUESTION_MESSAGE);

         Object option = DialogDisplayer.getDefault().notify(nd);
         if (option == NotifyDescriptor.CANCEL_OPTION) {
            // Do nothing
            return false;

         } else if (option == NotifyDescriptor.YES_OPTION) {
            btnSaveActionPerformed(null);
         }
      }
      return true;
   }

     boolean wksNameExists(String name) {
      try {
         String[] wksNames = db_.getWorksheetNames();
         for (int i = 0; i < wksNames.length; i++) {
            if (wksNames[i].equals(name)) {
               return true;
            }
         }
         return false;
      } catch (DbException ex) {
         Exceptions.printStackTrace(ex);
      }
      return false;
   }

      private void setWksChanged(boolean changed) {
      if (changed) {
         wksChanged_ = true;
         btnSave.setEnabled(wksChanged_);
//         try {
//            this.setDisplayName("FST Manager" + " (" + db_.getDatabaseName() + ")" + "*");
//         } catch (DbException ex) {
//            Exceptions.printStackTrace(ex);
//         }
      } else {
         wksChanged_ = false;
         btnSave.setEnabled(wksChanged_);
//         try {
//            this.setDisplayName("FST Manager" + " (" + db_.getDatabaseName() + ")");
//         } catch (DbException ex) {
//            Exceptions.printStackTrace(ex);
//         }
      }
   }
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        
        String label = NbBundle.getMessage(WKSEditTopComponent.class, "MSG_NewWKSLabel");
        String title = NbBundle.getMessage(WKSEditTopComponent.class, "MSG_NewWKSDialogTitle");
        
        checkWksSaved();
      NotifyDescriptor.InputLine d =
              new NotifyDescriptor.InputLine(label, title);
      if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
         String wksName = d.getInputText();
         if (wksNameExists(wksName)) {
            NotifyDescriptor nd =
                   new NotifyDescriptor.Message(NbBundle.
                   getMessage( WKSEditTopComponent.class,"WKS_Name_Already_Exits"));
           DialogDisplayer.getDefault().notify(nd);
           return;
         }

         WorksheetDef wdf =  new WorksheetDef();

         try {
            /** Save the empty WKS on the server */
             wdf.setName(wksName);
            db_.saveWorksheetDef(wdf);

            /** Reload the list of format names from the server */

            String[] wksNames = db_.getWorksheetNames();
            /** Reset the model with the new list of format names */
            cmbWorkSheets.setModel(new DefaultComboBoxModel(wksNames));
            cmbWorkSheets.setSelectedItem(wksName);
            currentWksName_ = cmbWorkSheets.getSelectedItem().toString();
            
            wksVisualPanel_.load(db_,currentWksName_);
            wksChanged_ = false;
            btnSave.setEnabled(wksChanged_);
             wksVisualPanel_.getWKSModel().addTableModelListener((TableModelListener) this);
            
            //editorPane_.setText(currentPft_.getFormat());
         } catch (DbException ex) {
            new GeneralDatabaseException(ex).displayWarning();
         } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
         }
         setWksChanged(false);
//        NotifyDescriptor.InputLine d =
//                new NotifyDescriptor.InputLine(label, title);
//        if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {
//            WorksheetDef wd = new WorksheetDef(d.getInputText());
//            try {
//                db_.saveWorksheetDef(wd);
//                String[] worksheetNames = db_.getWorksheetNames();
//                cmbWorkSheets.setModel(new DefaultComboBoxModel(worksheetNames));
//            } catch (DbException ex) {
//                new GeneralDatabaseException(ex).displayWarning();
//            }
         }
    }//GEN-LAST:event_btnNewActionPerformed
    
    private void cmbWorkSheetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWorkSheetsActionPerformed
       wksVisualPanel_.load(db_, cmbWorkSheets.getSelectedItem().toString());
       wksChanged_ = false;
       btnSave.setEnabled(wksChanged_);
       wksVisualPanel_.getWKSModel().addTableModelListener((TableModelListener) this);
    }//GEN-LAST:event_cmbWorkSheetsActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
       try {
          WorksheetDef wdf = wksVisualPanel_.getWKSModel().getWorksheetDef();
          wdf.setRecordValidationFormat(wksVisualPanel_.getRecordValFormat());
          db_.saveWorksheetDef(wdf);
          wksChanged_ = false;
          btnSave.setEnabled(wksChanged_);
          GuiGlobal.output(NbBundle.getMessage(WKSEditTopComponent.class, "MSG_WksSuccessfullySaved"));
          wksVisualPanel_.getWKSModel().resetChanged();
       } catch (DbException ex) {
          new GeneralDatabaseException(ex).displayWarning();
       }
    }//GEN-LAST:event_btnSaveActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntClose;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbWorkSheets;
    private javax.swing.JPanel ctrlPanel;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel wksLabel;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized WKSEditTopComponent getDefault() throws DefaultDBNotFoundException {
        
        if (instance != null) {
            instance.close();
            instance = null;
        }
        ConnectionInfo connectionInfo = ConnectionPool.getDefaultConnectionInfo();
        if (connectionInfo.getDefaultDatabase() != null) {
            instance = new WKSEditTopComponent(connectionInfo.getDefaultDatabase());
        }
        
        return instance;
    }
    
    /**
     * Obtain the WKSEditTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized WKSEditTopComponent findInstance() throws DefaultDBNotFoundException {
        TopComponent win = getDefault();
        
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find WKSEdit component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof WKSEditTopComponent) {
            return (WKSEditTopComponent)win;
        }
        ErrorManager.getDefault().log(ErrorManager.WARNING, "There seem to be multiple components with the '" + PREFERRED_ID + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }
    
   @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }
    
   @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }
    
   @Override
    public void componentClosed() {
       db_.deleteWindow(this);
        
    }
    
    /** replaces this in object stream */
    /*
    public Object writeReplace() {
        return new ResolvableHelper();
    }
     */
   @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
   
   private boolean canCloseFdt() {

      if (wksVisualPanel_.isWksChanged()) {
         NotifyDescriptor d = new NotifyDescriptor.Confirmation("Worksheet was modified but not saved!" + "\nDo you want really to close without saving?", "WKS Editor",
                 NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.QUESTION_MESSAGE);
         if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.OK_OPTION) {

            return true;
         } else {
            return false;
         }
      }
      return true;
   }

   @Override
   public boolean canClose() {
      return canCloseFdt();

   }

   public void tableChanged(TableModelEvent arg0) {
       setWksChanged(true);
   }
    /*
    final static class ResolvableHelper implements Serializable {
        private static final long serialVersionUID = 1L;
        public Object readResolve() {
            try {
                return WKSEditTopComponent.getDefault();
            } catch (DBNotFoundException ex) {
                NotifyDescriptor d =
                        new NotifyDescriptor.Message("No Default Database selected. Please, select on from Databases Pool window.", NotifyDescriptor.WARNING_MESSAGE);
                DialogDisplayer.getDefault().notify(d);
                return null;
            }
        }
    }
     */
   
   private void createDefaultWorksheet() {
       String name = "Default worksheet";
       WorksheetDef wks = new WorksheetDef();
   }
    
}
