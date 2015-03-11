/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unesco.jisis.FmtManager;

/**
 *
 * @author jc_dauphin
 */
import java.awt.*;
import java.awt.event.*;


import javax.swing.JEditorPane;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ComponentUI;
import org.unesco.jisis.corelib.common.Global;


//import org.openide.text.CloneableEditorSupport;
// import org.netbeans.modules.db.sql.editor.SQLEditorKit;

// public class QueryBuilderSqlTextArea extends JTextPane
public class FmtEditor extends JTextPane implements  DocumentListener {

   private boolean DEBUG = false;
   private boolean stringIsParsed = false;
   private FmtManagerTopComponent fmtManager_;
   private JPopupMenu fmtPopupMenu_;
   String lastGoodFmt_ = null;
   private JMenuItem showFormattingMenuItem_;
   private JMenuItem parseFormatMenuItem_;
   private boolean formatChanged_ = false;
   private String keysTyped;
   // This flag is used to decide whether the focusLost is caused
   // when the popup (Parse Query, Run Query) is displayed.
   // In the case where the focusLost is generated by the popup menu
   // we do not need to parse the query.
   // This is added to fix the problem of it is no longer possible to
   // execute an arbitrary query.  That is, if the user types in a query
   // and then tries to run it, the query editor will always parse/generate
   // it, which may transform the query formulation.  That makes it harder
   // to test out minor re-formulations in syntax.
   //

   // After removing the focus listener this may not be required
   // please remove later.
   private boolean _maybeShowPopup = false;

   // private JPopupMenu _tableColumnPopup;
//     private DefaultStyledDocument dsDocument   =  (DefaultStyledDocument) getStyledDocument();
   // TODO JFB:  sntax checking.   turned off, turn on when fixed.
   public static final boolean SYNTAX_HIGHLIGHT = true;
   /** The dirty flag indicating whether the document being edited
    * (<code>doc</code>) was modified or not. If it is true, it means
    * that there are unsaved changes; otherwise, no such changes
    * exist.
    */
   private /*@ spec_public @*/ boolean docModified_ = false;

   public boolean documentHasChanged() {
      return docModified_;
   }

   public void setDocumentUnchanged() {
      docModified_ = false;
   }

    // Override getScrollableTracksViewportWidth
    // to preserve the full width of the text
    @Override
    public boolean getScrollableTracksViewportWidth() {
        Component parent = getParent();
        ComponentUI ui = getUI();

        return parent != null ? (ui.getPreferredSize(this).width <= parent
                .getSize().width) : true;
    }
   // Constructor
    public FmtEditor(FmtManagerTopComponent fmtManager) {
        super();

        Font font = new Font("Monospaced", Font.PLAIN, 12);

        if (Global.getApplicationFont() != null) {
            font = Global.getApplicationFont();
        }

        putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        setFont(new Font("Arial", Font.BOLD, 13));

        fmtManager_ = fmtManager;

        CaretListenerSelection caretListenerSelection = new CaretListenerSelection();
        addCaretListener(caretListenerSelection);
        putClientProperty("print.printable", Boolean.TRUE); // NOI18N
   }

  

   public void setParseFormatMenuEnabled(boolean onOff) {
      parseFormatMenuItem_.setEnabled(onOff);
   }

   public void setRunFormatMenuEnabled(boolean onOff) {
      showFormattingMenuItem_.setEnabled(onOff);
   }

   public void insertUpdate(DocumentEvent arg0) {
      docModified_ = true;
   }

   public void removeUpdate(DocumentEvent arg0) {
      docModified_ = true;
   }

   /**
     * Gives notification that an attribute or set of attributes changed.
     *
     * @param e the document event
     */
   public void changedUpdate(DocumentEvent arg0) {
      // Do nothing ! docModified_ = true;
   }


   // Inner classes, for menus
   class fmtlTextListener extends MouseAdapter {

      JPopupMenu popup;

      fmtlTextListener(JPopupMenu popupMenu) {
         popup = popupMenu;
      }

      @Override
      public void mousePressed(MouseEvent e) {
         maybeShowPopup(e);
      }

      @Override
      public void mouseReleased(MouseEvent e) {
         mousePressed(e);
      }

      private void maybeShowPopup(MouseEvent e) {
         if (e.isPopupTrigger()) {
            // this check is added to fix a bug where the popup menu
            // remains disabled when the user has removed the last table
            // from the graph and then added (typed/pasted) text query.
            // First check if the text is not just white spaces.
            if (((JEditorPane) (e.getComponent())).getText().trim().length() != 0) {
               parseFormatMenuItem_.setEnabled(true);
               showFormattingMenuItem_.setEnabled(true);
            } else {
               // user may have just typed white spaces or may have typed
               // a wrong query which got restored after he 'cancel'ed
               // the warning dialog about non-standard query, which
               // restored the previous 'blank' query.
               parseFormatMenuItem_.setEnabled(false);
               showFormattingMenuItem_.setEnabled(false);
            }
            _maybeShowPopup = true;
            popup.show(e.getComponent(), e.getX(), e.getY());
         }
      }
   }

   public boolean formatChanged() {
      return (!this.getText().equals(lastGoodFmt_));
   }


 

   /**
    * Sets the text of this TextComponent  to the specified text. Also records it
    * for possible reset later.
    */
   public void setFormatText(String str) {

      // to make sure the last part of the incoming string
      // is highlighted.

      String text = str + " ";

      super.setText(text);

      if (DEBUG) {
         System.out.println("setFormatText called with " + str + "\n"); // NOI18N
      }


      if (!text.trim().equals(lastGoodFmt_)) {
         lastGoodFmt_ = text;
         formatChanged_ = true;
      } else {
         formatChanged_ = false;
      }
      _maybeShowPopup = false;
   }

   /**
    *  Restore the last good format.
    */
   public void restoreLastGoodFormat() {
      super.setText(lastGoodFmt_);
   }

   /**
    *  Save the last good format.
    */
   public void saveLastGoodFormat(String query) {
      lastGoodFmt_ = query;
   }

   /**
    * Clears the text area
    */
   void clear() {
      this.setFormatText(""); // NOI18N
   }

   /**
    * Resets the text to the last parsed format
    */
   void reset() {
      this.setFormatText(lastGoodFmt_);
   }

   /**
    * Class used to update the paste/cut item
    */
   protected class CaretListenerSelection implements CaretListener {

      public void caretUpdate(CaretEvent e) {
         updateMenuItems(e.getDot(), e.getMark());
      }

      //This method can be invoked from any thread.  It
      //invokes the setText and modelToView methods, which
      //must run in the event dispatching thread. We use
      //invokeLater to schedule the code for execution
      //in the event dispatching thread.
      protected void updateMenuItems(final int dot,
              final int mark) {
         SwingUtilities.invokeLater(new Runnable() {

            public void run() {
               if (dot == mark) {  // no selection
                  fmtManager_.disableCutCopy();
               } else {
                  fmtManager_.enableCutCopy();
               }
            }
         });
      }
   }
}