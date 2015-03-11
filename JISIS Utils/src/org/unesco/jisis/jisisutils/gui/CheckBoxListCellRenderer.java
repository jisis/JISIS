 /*
                 * @(#)CheckBoxtListCellRenderer.java 5/11/2005
                 *
                 * Copyright 2002 - 2005 JIDE Software Inc. All rights reserved.
                 */
        
                package org.unesco.jisis.jisisutils.gui;
        
                import javax.swing.*;
                import javax.swing.border.Border;
                import javax.swing.border.EmptyBorder;
                import java.awt.*;
                import java.awt.event.MouseEvent;
                import java.io.Serializable;
        
                /**
                 * Renders an item in a list using JCheckBox.
                 */
                public class CheckBoxListCellRenderer extends JPanel implements 
                        ListCellRenderer, Serializable {
        
                    protected static Border noFocusBorder;
        
                    /**
                     * The checkbox that is used to paint the check box in cell renderer
                     */
                    protected JCheckBox _checkBox = new JCheckBox();
                    protected JLabel _label = new JLabel();
        
                    /**
                     * The label which appears after the check box.
                     */
                    protected ListCellRenderer _actualListRenderer;
        
                    public CheckBoxListCellRenderer(ListCellRenderer renderer) {
                        if (noFocusBorder == null) {
                            noFocusBorder = new EmptyBorder(1, 1, 1, 1);
                        }
                        setOpaque(true);
                        setBorder(noFocusBorder);
                        setLayout(new BorderLayout(0, 0));
                        add(_checkBox, BorderLayout.BEFORE_LINE_BEGINS);
                        _actualListRenderer = renderer;
                    }
        
                    /**
                     * Constructs a default renderer object for an item
                     * in a list.
                     */
                    public CheckBoxListCellRenderer() {
                        this (null);
                    }
        
                    public ListCellRenderer getActualListRenderer() {
                        return _actualListRenderer;
                    }
        
                    public void setActualListRenderer(
                            ListCellRenderer actualListRenderer) {
                        _actualListRenderer = actualListRenderer;
                    }
        
                    @Override
                    public String getToolTipText(MouseEvent event) {
                        if (_actualListRenderer instanceof  JComponent) {
                            Point p = event.getPoint();
                            p.translate(-_checkBox.getWidth(), 0);
                            MouseEvent newEvent = new MouseEvent(
                                    ((JComponent) _actualListRenderer), event.getID(),
                                    event.getWhen(), event.getModifiers(), p.x, p.y,
                                    event.getClickCount(), event.isPopupTrigger());
        
                            String tip = ((JComponent) _actualListRenderer)
                                    .getToolTipText(newEvent);
        
                            if (tip != null) {
                                return tip;
                            }
                        }
                        return super .getToolTipText(event);
                    }
        
                    public Component getListCellRendererComponent(JList list,
                            Object value, int index, boolean isSelected,
                            boolean cellHasFocus) {
        
                        _checkBox.setPreferredSize(new Dimension(_checkBox
                                .getPreferredSize().width, 0));
                        setComponentOrientation(list.getComponentOrientation());
        
                        Object actualValue;
                        if (list instanceof  CheckBoxList) {
                            CheckBoxListSelectionModel selectionModel = ((CheckBoxList) list)
                                    .getCheckBoxListSelectionModel();
                            if (selectionModel != null) {
                                boolean enabled = list.isEnabled()
                                        && ((CheckBoxList) list).isCheckBoxEnabled()
                                        && ((CheckBoxList) list)
                                                .isCheckBoxEnabled(index);
                                if (!enabled && !isSelected) {
                                    setForeground(getBackground().darker());
                                }
                                _checkBox.setEnabled(enabled);
                                _checkBox.setSelected(selectionModel
                                        .isSelectedIndex(index));
                            }
                            actualValue = value;
                        } else if (list instanceof  CheckBoxListWithSelectable) {
                            if (value instanceof  Selectable) {
                                _checkBox
                                        .setSelected(((Selectable) value).isSelected());
                                boolean enabled = list.isEnabled()
                                        && ((Selectable) value).isEnabled()
                                        && ((CheckBoxListWithSelectable) list)
                                                .isCheckBoxEnabled();
                                if (!enabled && !isSelected) {
                                    setForeground(getBackground().darker());
                                }
                                _checkBox.setEnabled(enabled);
                            } else {
                                boolean enabled = list.isEnabled();
                                if (!enabled && !isSelected) {
                                    setForeground(getBackground().darker());
                                }
                                _checkBox.setEnabled(enabled);
                            }
        
                            if (value instanceof  DefaultSelectable) {
                                actualValue = ((DefaultSelectable) value).getObject();
                            } else {
                                actualValue = value;
                            }
                        } else {
                            throw new IllegalArgumentException(
                                    "CheckBoxListCellRenderer should only be used for CheckBoxList.");
                        }
        
                        if (_actualListRenderer != null) {
                            JComponent listCellRendererComponent = (JComponent) _actualListRenderer
                                    .getListCellRendererComponent(list, value, index,
                                            isSelected, cellHasFocus);
                            if (list instanceof  CheckBoxList) {
                                if (!((CheckBoxList) list).isCheckBoxVisible(index)) {
                                    return listCellRendererComponent;
                                }
                            } else if (list instanceof  CheckBoxListWithSelectable) {
                                if (!((CheckBoxListWithSelectable) list)
                                        .isCheckBoxVisible(index)) {
                                    return listCellRendererComponent;
                                }
                            }
                            Border border = listCellRendererComponent.getBorder();
                            setBorder(border);
                            listCellRendererComponent.setBorder(BorderFactory
                                    .createEmptyBorder());
                            if (getComponentCount() == 2) {
                                remove(1);
                            }
                            add(listCellRendererComponent);
                            setBackground(listCellRendererComponent.getBackground());
                            setForeground(listCellRendererComponent.getForeground());
                        } else {
                            if (isSelected) {
                                setBackground(list.getSelectionBackground());
                                setForeground(list.getSelectionForeground());
                            } else {
                                setBackground(list.getBackground());
                                setForeground(list.getForeground());
                            }
                            if (getComponentCount() == 2) {
                                remove(1);
                            }
                            add(_label);
                            customizeDefaultCellRenderer(actualValue);
                            setFont(list.getFont());
                        }
        
                        return this ;
                    }
        
                    /**
                     * Customizes the cell renderer. By default, it will use toString to covert the object
                     * and use it as the text for the checkbox. You can subclass it to set an icon, change alignment etc.
                     * Since "this" is a JCheckBox, you can call all methods available on JCheckBox in the overridden method.
                     *
                     * @param value
                     */
                    protected void customizeDefaultCellRenderer(Object value) {
                        if (value instanceof  Icon) {
                            _label.setIcon((Icon) value);
                            _label.setText("");
                        } else {
                            _label.setIcon(null);
                            _label.setText((value == null) ? "" : value.toString());
                        }
                    }
        
                    /**
                     * A subclass of DefaultListCellRenderer that implements UIResource.
                     * DefaultListCellRenderer doesn't implement UIResource
                     * directly so that applications can safely override the
                     * cellRenderer property with DefaultListCellRenderer subclasses.
                     * <p/>
                     * <strong>Warning:</strong>
                     * Serialized objects of this class will not be compatible with
                     * future Swing releases. The current serialization support is
                     * appropriate for short term storage or RMI between applications running
                     * the same version of Swing.  As of 1.4, support for long term storage
                     * of all JavaBeans<sup><font size="-2">TM</font></sup>
                     * has been added to the <code>java.beans</code> package.
                     * Please see {@link java.beans.XMLEncoder}.
                     */
                    public static class UIResource extends CheckBoxListCellRenderer
                            implements  javax.swing.plaf.UIResource {
                    }
        
                }