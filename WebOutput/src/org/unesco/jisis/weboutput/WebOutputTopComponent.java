/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unesco.jisis.weboutput;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;
import org.apache.commons.lang3.StringUtils;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.StatusDisplayer;
import org.openide.cookies.PrintCookie;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.CookieSet;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.slf4j.LoggerFactory;
import org.unesco.jisis.corelib.client.ConnectionInfo;
import org.unesco.jisis.corelib.client.ConnectionPool;
import org.unesco.jisis.corelib.common.FormattedRecord;
import org.unesco.jisis.corelib.common.IDatabase;
import org.unesco.jisis.corelib.exceptions.DbException;
import org.unesco.jisis.corelib.exceptions.RecordNotFoundException;
import org.unesco.jisis.corelib.record.IRecord;
import org.unesco.jisis.fxbrowser.SwingFXWebView;
import org.unesco.jisis.jisiscore.client.ClientDatabaseProxy;


/**
 * Top component which displays something.
 */
final public class WebOutputTopComponent extends TopComponent {

   private static WebOutputTopComponent instance;
   /**
    * path to the icon used by the component and its open action
    */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
   private static final String PREFERRED_ID = "WebOutputTopComponent";
   private static ClientDatabaseProxy db_;
   private IRecord currentRecord_ = null;
   
   private final String MODE = "output"; // NOI18N
   private String pftName_ = null;
   private String pftFormat_ = null;
 
   private SwingFXWebView swingFXWebView = null;
   private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WebOutputTopComponent.class);

   public WebOutputTopComponent(IDatabase db) {
      if (db instanceof ClientDatabaseProxy) {
         db_ = (ClientDatabaseProxy) db;
      } else {
         throw new RuntimeException("RecordDataBrowserTopComponent: Cannot cast DB to ClientDatabaseProxy");
      }

      /* Register this TopComponent as attached to this DB */
      //db_.addWindow(this);
      initComponents();

      webPanel.setPreferredSize(new Dimension(700, 500));
      
       if (swingFXWebView == null) {
         swingFXWebView = new SwingFXWebView();
         webPanel.add(swingFXWebView, BorderLayout.CENTER);
      }
  

      setName(NbBundle.getMessage(WebOutputTopComponent.class, "CTL_WebOutputTopComponent"));
      setToolTipText(NbBundle.getMessage(WebOutputTopComponent.class, "HINT_WebOutputTopComponent"));

      associateLookup(new JisisPrintNode().getLookup());

//        setIcon(Utilities.loadImage(ICON_PATH, true));

   }

   public void printPage() {
       swingFXWebView.executeScript("window.print();");
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      tabbedPane = new javax.swing.JTabbedPane();
      htmlPanel = new javax.swing.JPanel();
      crtlPanel = new javax.swing.JPanel();
      txtMFN = new javax.swing.JTextField();
      btnCheckMFN = new javax.swing.JButton();
      btnFirst = new javax.swing.JButton();
      btnPrev = new javax.swing.JButton();
      btnNext = new javax.swing.JButton();
      btnLast = new javax.swing.JButton();
      btnReload = new javax.swing.JButton();
      lblFormat = new javax.swing.JLabel();
      txtPFT = new javax.swing.JTextField();
      webPanel = new javax.swing.JPanel();
      sourcePanel = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      srcTextArea = new javax.swing.JTextArea();

      htmlPanel.setMinimumSize(new java.awt.Dimension(100, 100));
      htmlPanel.setLayout(new java.awt.BorderLayout());

      crtlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

      txtMFN.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
      txtMFN.setText(org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.txtMFN.text")); // NOI18N
      txtMFN.setAutoscrolls(false);
      txtMFN.addKeyListener(new java.awt.event.KeyAdapter() {
         public void keyPressed(java.awt.event.KeyEvent evt) {
            txtMFNKeyPressed(evt);
         }
      });

      btnCheckMFN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/unesco/jisis/weboutput/check16x16.png"))); // NOI18N
      org.openide.awt.Mnemonics.setLocalizedText(btnCheckMFN, org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.btnCheckMFN.text")); // NOI18N
      btnCheckMFN.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCheckMFNActionPerformed(evt);
         }
      });

      btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/unesco/jisis/weboutput/first16x16.png"))); // NOI18N
      org.openide.awt.Mnemonics.setLocalizedText(btnFirst, org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.btnFirst.text")); // NOI18N
      btnFirst.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnFirstActionPerformed(evt);
         }
      });

      btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/unesco/jisis/weboutput/prev16x16.png"))); // NOI18N
      org.openide.awt.Mnemonics.setLocalizedText(btnPrev, org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.btnPrev.text")); // NOI18N
      btnPrev.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnPrevActionPerformed(evt);
         }
      });

      btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/unesco/jisis/weboutput/next16x16.png"))); // NOI18N
      org.openide.awt.Mnemonics.setLocalizedText(btnNext, org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.btnNext.text")); // NOI18N
      btnNext.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnNextActionPerformed(evt);
         }
      });

      btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/unesco/jisis/weboutput/last16x16x.png"))); // NOI18N
      org.openide.awt.Mnemonics.setLocalizedText(btnLast, org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.btnLast.text")); // NOI18N
      btnLast.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnLastActionPerformed(evt);
         }
      });

      btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/unesco/jisis/weboutput/reload16x16.png"))); // NOI18N
      org.openide.awt.Mnemonics.setLocalizedText(btnReload, org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.btnReload.text")); // NOI18N
      btnReload.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnReloadActionPerformed(evt);
         }
      });

      org.openide.awt.Mnemonics.setLocalizedText(lblFormat, org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.lblFormat.text")); // NOI18N

      txtPFT.setEditable(false);
      txtPFT.setText(org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.txtPFT.text")); // NOI18N

      javax.swing.GroupLayout crtlPanelLayout = new javax.swing.GroupLayout(crtlPanel);
      crtlPanel.setLayout(crtlPanelLayout);
      crtlPanelLayout.setHorizontalGroup(
         crtlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(crtlPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(txtMFN, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnCheckMFN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(lblFormat)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(txtPFT, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(58, 58, 58)
            .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(29, 29, 29)
            .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(631, Short.MAX_VALUE))
      );
      crtlPanelLayout.setVerticalGroup(
         crtlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(crtlPanelLayout.createSequentialGroup()
            .addGap(8, 8, 8)
            .addGroup(crtlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(crtlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(btnLast, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(btnPrev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(btnFirst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addGroup(crtlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(lblFormat)
                     .addComponent(txtPFT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addComponent(txtMFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(btnCheckMFN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGap(0, 13, Short.MAX_VALUE))
      );

      htmlPanel.add(crtlPanel, java.awt.BorderLayout.NORTH);

      webPanel.setMinimumSize(new java.awt.Dimension(100, 100));
      webPanel.setLayout(new java.awt.BorderLayout());
      htmlPanel.add(webPanel, java.awt.BorderLayout.CENTER);

      tabbedPane.addTab(org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.htmlPanel.TabConstraints.tabTitle"), htmlPanel); // NOI18N

      sourcePanel.setLayout(new java.awt.BorderLayout());

      srcTextArea.setColumns(20);
      srcTextArea.setRows(5);
      jScrollPane1.setViewportView(srcTextArea);

      sourcePanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

      tabbedPane.addTab(org.openide.util.NbBundle.getMessage(WebOutputTopComponent.class, "WebOutputTopComponent.sourcePanel.TabConstraints.tabTitle"), sourcePanel); // NOI18N

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1285, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
      );
   }// </editor-fold>//GEN-END:initComponents

   private void btnCheckMFNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckMFNActionPerformed
      long mfn = Long.parseLong(txtMFN.getText());
            try {
               FormattedRecord formattedRecord = db_.getRecordCursorFmt(mfn, pftName_);
                displayRecord(formattedRecord);
            } catch (DbException ex) {
                new RecordNotFoundException(ex).displayWarning();
            }
   }//GEN-LAST:event_btnCheckMFNActionPerformed

   private void displayFirstRecord() {

      try {
         FormattedRecord formattedRecord = db_.getFirstFmt(pftName_);
         displayRecord(formattedRecord);
      } catch (DbException ex) {
         new RecordNotFoundException(ex).displayWarning();
      }
   }
   private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
      displayFirstRecord();
   }//GEN-LAST:event_btnFirstActionPerformed

   private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
      try {
         FormattedRecord formattedRecord = db_.getPrevFmt(pftName_);
         displayRecord(formattedRecord);
      } catch (DbException ex) {
         new RecordNotFoundException(ex).silent();
      }
   }//GEN-LAST:event_btnPrevActionPerformed

   private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
      try {
         FormattedRecord formattedRecord = db_.getNextFmt(pftName_);
         displayRecord(formattedRecord);
      } catch (DbException ex) {
         new RecordNotFoundException(ex).silent();
      }
   }//GEN-LAST:event_btnNextActionPerformed

   private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
      try {
         FormattedRecord formattedRecord = db_.getLastFmt(pftName_);
         displayRecord(formattedRecord);
      } catch (DbException ex) {
         new RecordNotFoundException(ex).displayWarning();
      }
   }//GEN-LAST:event_btnLastActionPerformed

   private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
      try {
         FormattedRecord formattedRecord = db_.getCurrentFmt(pftName_);
         displayRecord(formattedRecord);
      } catch (DbException ex) {
         new RecordNotFoundException(ex).displayWarning();
      }
   }//GEN-LAST:event_btnReloadActionPerformed

   private void txtMFNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMFNKeyPressed

      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         long mfn = Long.parseLong(txtMFN.getText());
         try {
            FormattedRecord formattedRecord = db_.getRecordCursorFmt(mfn, pftName_);
            displayRecord(formattedRecord);
         } catch (DbException ex) {
            new RecordNotFoundException(ex).displayWarning();
         }
      }
   }//GEN-LAST:event_txtMFNKeyPressed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnCheckMFN;
   private javax.swing.JButton btnFirst;
   private javax.swing.JButton btnLast;
   private javax.swing.JButton btnNext;
   private javax.swing.JButton btnPrev;
   private javax.swing.JButton btnReload;
   private javax.swing.JPanel crtlPanel;
   private javax.swing.JPanel htmlPanel;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JLabel lblFormat;
   private javax.swing.JPanel sourcePanel;
   private javax.swing.JTextArea srcTextArea;
   private javax.swing.JTabbedPane tabbedPane;
   private javax.swing.JTextField txtMFN;
   private javax.swing.JTextField txtPFT;
   private javax.swing.JPanel webPanel;
   // End of variables declaration//GEN-END:variables

   /**
    * Gets default instance. Do not use directly: reserved for *.settings files only,
    * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
    * To obtain the singleton instance, use {@link #findInstance}.
    */
    public static synchronized WebOutputTopComponent getDefault() {

        if (instance != null) {
           db_.deleteWindow(instance);
            instance.close();
            instance = null;
        }
        ConnectionInfo connectionInfo = ConnectionPool.getDefaultConnectionInfo();
        if (connectionInfo.getDefaultDatabase() != null && instance == null) {
            instance = new WebOutputTopComponent(connectionInfo.getDefaultDatabase());
        }
        return instance;
    }

   /**
    * Obtain the WebOutputTopComponent instance. Never call {@link #getDefault} directly!
    */
   public static synchronized WebOutputTopComponent findInstance() {
      TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
      if (win == null) {
         Logger.getLogger(WebOutputTopComponent.class.getName()).warning(
                 "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
         return getDefault();
      }
      ConnectionInfo connectionInfo = ConnectionPool.getDefaultConnectionInfo();
      if (win instanceof WebOutputTopComponent && connectionInfo.getDefaultDatabase().equals(db_)) {
         return (WebOutputTopComponent) win;
      } 
      Logger.getLogger(WebOutputTopComponent.class.getName()).warning(
              "There seem to be multiple components with the '" + PREFERRED_ID +
              "' ID. That is a potential source of errors and unexpected behavior.");
      return getDefault();
   }

   @Override
   public void open() {
      Mode m = WindowManager.getDefault().findMode(MODE);
      m.dockInto(this);
      super.open();
      displayFirstRecord();

   }
    @Override
    protected void componentActivated() {

        super.componentActivated();
        System.out.println("FmtManagerTopComponent componentactivated pftName_="+pftName_);
//        try {
//            this.toFront();
//            JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
//            frame.getRootPane().updateUI();
//            FormattedRecord formattedRecord = db_.getCurrentFmt(pftName_);
//            displayRecord(formattedRecord);
//
//        } catch (DbException ex) {
//            Exceptions.printStackTrace(ex);
//        }

    }
   @Override
   public int getPersistenceType() {
      return TopComponent.PERSISTENCE_NEVER;
   }

   @Override
   public void componentOpened() {
      // TODO add custom code on component opening
      db_.addWindow(this);
   }

   @Override
   public void componentClosed() {
      db_.deleteWindow(this);
      
   }

   /** replaces this in object stream */
   @Override
   public Object writeReplace() {
      return new ResolvableHelper();
   }

   @Override
   protected String preferredID() {
      return PREFERRED_ID;
   }
   
   public void setRecordByMFN(long MFN) {
        try {
            FormattedRecord formattedRecord = db_.getRecordCursorFmt(MFN, pftName_);
            displayRecord(formattedRecord);
        } catch (DbException ex) {
            Exceptions.printStackTrace(ex);
        }

    }
    private void setDatabase(IDatabase db) {
       
      if (db instanceof ClientDatabaseProxy) {
         if (db_ != null && db_.equals(db)) {
            return;
         }
         db_ = (ClientDatabaseProxy) db;
      } else {
         throw new RuntimeException("RecordDataBrowserTopComponent: Cannot cast DB to ClientDatabaseProxy");
      }

      /* Register this TopComponent as attached to this DB */
      db_.addWindow(this);
   }
   public void setPft(IDatabase db, String pftName, String pftFormat) {
      //setDatabase(db);
        System.out.println("FmtManagerTopComponent setPft pftName="+pftName+"\npftFormat="+pftFormat);
      pftName_ = pftName;
      if (StringUtils.isEmpty(pftName) || StringUtils.isBlank(pftName)) {
         String msg= "Please give a name to the PFT!";

         DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(msg));
      }
    
      pftFormat_ = pftFormat;
      try {
         db_.savePrintFormat(pftName, pftFormat);
      } catch (DbException ex) {
         Exceptions.printStackTrace(ex);
      }

   }

   private void doDisplayRecord(final String content) {
     
      Runnable displayRun = new Runnable() {
         public void run() {

            if (!SwingUtilities.isEventDispatchThread()) {
               try {
                  swingFXWebView.loadContent(content);
               } catch (Exception ex) {
                  Exceptions.printStackTrace(ex);
               } finally {
                  // clear status text
                  StatusDisplayer.getDefault().setStatusText(""); // NOI18N
                  // clear wait cursor
                  SwingUtilities.invokeLater(this);
               }

            }
         }
      };
      RequestProcessor.Task loadTask = RequestProcessor.getDefault().post(displayRun);
      loadTask.waitFinished();
   }
   
    private void displayRecord(FormattedRecord formattedRecord) {
       if (formattedRecord == null) {
         return;
      }
      
      final String content = formattedRecord.getRecord();
     
      if (content == null) {
         return;
      }
      long mfn = formattedRecord.getMfn();
//      System.out.println("HTML:");
//      System.out.println(content);
     
      final JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
      StatusDisplayer.getDefault().setStatusText("Starting browser display ...");
      RepaintManager.currentManager(frame).paintDirtyRegions();
      
      srcTextArea.setText(content);
      doDisplayRecord(content);  
      txtPFT.setText(pftName_);
     
      
      txtMFN.setText(mfn+ "");
      
    }
   

   final static class ResolvableHelper implements Serializable {

      private static final long serialVersionUID = 1L;

      public Object readResolve() {
         return WebOutputTopComponent.getDefault();
      }
   }
   class JisisPrintCookie implements PrintCookie {

    @Override
    public void print() {
        JOptionPane.showMessageDialog(null, "I am printing...");
        printPage();
    }

}
   class JisisPrintNode  extends AbstractNode {
    public JisisPrintNode() {
        super(Children.LEAF);
        CookieSet cookies = getCookieSet();
        cookies.add(new JisisPrintCookie());
    }
   }
}
