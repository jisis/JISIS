package org.unesco.jisis.wizards.connopen;

import java.awt.Component;
import java.awt.Dialog;
import java.io.IOException;
import java.text.MessageFormat;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.RepaintManager;


import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.awt.StatusDisplayer;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;
import org.openide.windows.WindowManager;
import org.unesco.jisis.corelib.client.ConnectionNIO;
import org.unesco.jisis.corelib.client.ConnectionPool;
import org.unesco.jisis.corelib.common.Global;
import org.unesco.jisis.corelib.common.IConnection;
import org.unesco.jisis.corelib.exceptions.DbException;
import org.unesco.jisis.jisisutils.threads.IdeCursor;
import org.unesco.jisis.windows.connection.ConnTopComponent;

// An example action demonstrating how the wizard could be called from within
// your code. You can copy-paste the code below wherever you need.
public final class connOpenWizardAction extends CallableSystemAction {

   private WizardDescriptor.Panel<org.openide.WizardDescriptor>[] panels;

   public void performAction() {
      WizardDescriptor wizardDescriptor = new WizardDescriptor(getPanels());
      // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
      wizardDescriptor.setTitleFormat(new MessageFormat("{0}"));
      wizardDescriptor.setTitle(NbBundle.getMessage(connOpenWizardAction.class, "CTL_ConnOpenWizardPanel"));
      Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
      dialog.setVisible(true);
      dialog.toFront();
      boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
      if (!cancelled) {
         finished(wizardDescriptor);
      }
   }

   /**
    * Initialize panels representing individual wizard's steps and sets various
    * properties for them influencing wizard appearance.
    */
   private WizardDescriptor.Panel[] getPanels() {
      if (panels == null) {
         panels = new WizardDescriptor.Panel[]{
            new connOpenWizardPanel1()
         };
         String[] steps = new String[panels.length];
         for (int i = 0; i < panels.length; i++) {
            Component c = panels[i].getComponent();
            // Default step name to component name of panel. Mainly useful
            // for getting the name of the target chooser to appear in the
            // list of steps.
            steps[i] = c.getName();
            if (c instanceof JComponent) { // assume Swing components
               JComponent jc = (JComponent) c;
               // Sets step number of a component
               jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i));
               // Sets steps names for a panel
               jc.putClientProperty("WizardPanel_contentData", steps);
               // Turn on subtitle creation on each step
               jc.putClientProperty("WizardPanel_autoWizardStyle", Boolean.TRUE);
               // Show steps on the left side with the image on the background
               jc.putClientProperty("WizardPanel_contentDisplayed", Boolean.TRUE);
               // Turn on numbering of all steps
               jc.putClientProperty("WizardPanel_contentNumbered", Boolean.TRUE);
            }
         }
      }
      return panels;
   }

   private void finished(WizardDescriptor wd) {
      final String hostname = (String) wd.getProperty("hostname");
      final String port = (String) wd.getProperty("port");
      final String username = (String) wd.getProperty("username");
      final String password = (String) wd.getProperty("password");

      int i = ConnectionPool.findConnection(hostname, Integer.parseInt(port));
       if (i != -1) {
            String errorMsg = "Connection to [" + hostname + "] on port ["+port+"] already established! ";
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(errorMsg));
            return;
        }
      final JFrame mainWin = (JFrame) WindowManager.getDefault().getMainWindow();
      try {
         StatusDisplayer.getDefault().setStatusText("Connection to Server Please Wait ...");
         RepaintManager.currentManager(mainWin).paintDirtyRegions();
         IdeCursor.changeCursorWaitStatus(true);
         IConnection connection = null;
         if (Global.useNIO_) {
            connection =
                    new ConnectionNIO(hostname, Integer.parseInt(port), username, password);
         } else {
//              connection =
//               new Connection(hostname, Integer.parseInt(port), username, password);
            throw new RuntimeException("Connection doesn't use NIO!!");
         }
         ConnectionPool.addConnection(connection);
      } catch (DbException ex) {
         String errorMsg = ex.getMessage();
         DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(errorMsg));

      } catch (IOException ex) {
         String errorMsg = ex.getMessage();
         DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(errorMsg));
      } finally {
          // clear status text
          StatusDisplayer.getDefault().setStatusText(""); // NOI18N
          RepaintManager.currentManager(mainWin).paintDirtyRegions();
          IdeCursor.changeCursorWaitStatus(false);
      }
      ConnTopComponent.findInstance().refresh();
      //return connections;
   }

   public String getName() {
      return NbBundle.getMessage(connOpenWizardAction.class, "CTL_ConnOpenWizardAction");
   }

   public String iconResource() {
      return "org/unesco/jisis/wizards/connopen/network.png";
   }

   public HelpCtx getHelpCtx() {
      return HelpCtx.DEFAULT_HELP;
   }

   protected boolean asynchronous() {
      return false;
   }
}
