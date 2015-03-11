/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.unesco.jisis.importexport;

/**
 *
 * @author jcd
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.IllegalAddException;
import org.marc4j.marc.Leader;
import org.marc4j.marc.Record;
import org.marc4j.marc.VariableField;
import org.marc4j.marc.impl.LeaderImpl;
import org.marc4j.marc.impl.Verifier;

/**
 * Represents a MARC record.
 * 
 * @author Bas Peters
 * @version $Revision: 1.5 $
 */
public class NoControlFieldsRecord implements Record {

    private Long id;

    private Leader leader;

    private final List controlFields;

    private final List dataFields;

    private String type;
    
    
    public static NoControlFieldsRecord newRecord() {
        NoControlFieldsRecord record = new NoControlFieldsRecord();
        record.setLeader(new LeaderImpl("00000nam a2200000 a 4500"));
        return record;

    }

    /**
     * Creates a new <code>Record</code>.
     */
    public NoControlFieldsRecord() {
        controlFields = new ArrayList();
        dataFields = new ArrayList();
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public Leader getLeader() {
        return leader;
    }

    /**
     * Adds a <code>VariableField</code> being a <code>ControlField</code>
     * or <code>DataField</code>.
     * 
     * If the <code>VariableField</code> is a control number field (001) and
     * the record already has a control number field, the field is replaced with
     * the new instance.
     * 
     * @param field
     *            the <code>VariableField</code>
     * @throws IllegalAddException
     *             when the parameter is not a <code>VariableField</code>
     *             instance
     */
    public void addVariableField(VariableField field) {
//        if (!(field instanceof VariableField))
//            throw new IllegalAddException("Expected VariableField instance");
//
//        String tag = field.getTag();
//        if (Verifier.isControlNumberField(tag)) {
//            if (Verifier.hasControlNumberField(controlFields))
//                controlFields.set(0, field);
//            else
//                controlFields.add(0, field);
//            Collections.sort(controlFields);
//        } else if (Verifier.isControlField(tag)) {
//            controlFields.add(field);
//            Collections.sort(controlFields);
//        } else {
            dataFields.add(field);
            Collections.sort(dataFields);
        //}

    }

    public void removeVariableField(VariableField field) {
        String tag = field.getTag();
        if (Verifier.isControlField(tag))
            controlFields.remove(field);
        else
            dataFields.remove(field);
    }

    /**
     * Returns the control number field or <code>null</code> if no control
     * number field is available.
     * 
     * @return ControlField - the control number field
     */
    public ControlField getControlNumberField() {
        if (Verifier.hasControlNumberField(controlFields))
            return (ControlField) controlFields.get(0);
        else
            return null;
    }

    public List getControlFields() {
        return controlFields;
    }

    public List getDataFields() {
        return dataFields;
    }

    public VariableField getVariableField(String tag) {
        Iterator i;
        if (Verifier.isControlField(tag))
            i = controlFields.iterator();
        else
            i = dataFields.iterator();
        while (i.hasNext()) {
            VariableField field = (VariableField) i.next();
            if (field.getTag().equals(tag))
                return field;
        }
        return null;
    }

    public List getVariableFields(String tag) {
        List fields = new ArrayList();
        Iterator i;
        if (Verifier.isControlField(tag))
            i = controlFields.iterator();
        else
            i = dataFields.iterator();
        while (i.hasNext()) {
            VariableField field = (VariableField) i.next();
            if (field.getTag().equals(tag))
                fields.add(field);
        }
        return fields;
    }

    public List getVariableFields() {
        List fields = new ArrayList();
        Iterator i;
        i = controlFields.iterator();
        while (i.hasNext())
            fields.add(i.next());
        i = dataFields.iterator();
        while (i.hasNext())
            fields.add(i.next());
        return fields;
    }

    public String getControlNumber() {
        ControlField f = getControlNumberField();
        String result = (f == null || f.getData() == null) ? null : new String(f.getData());
        return(result);
    }

    public List getVariableFields(String[] tags) {
        List list = new ArrayList();
        for (int i = 0; i < tags.length; i++) {
            String tag = tags[i];
            List fields = getVariableFields(tag);
            if (fields.size() > 0)
                list.addAll(fields);
        }
        return list;
    }

    /**
     * Returns a string representation of this record.
     * 
     * <p>
     * Example:
     * 
     * <pre>
     *     
     *      LEADER 00714cam a2200205 a 4500 
     *      001 12883376 
     *      005 20030616111422.0
     *      008 020805s2002 nyu j 000 1 eng 
     *      020   $a0786808772 
     *      020   $a0786816155 (pbk.) 
     *      040   $aDLC$cDLC$dDLC 
     *      100 1 $aChabon, Michael. 
     *      245 10$aSummerland /$cMichael Chabon. 
     *      250   $a1st ed. 
     *      260   $aNew York :$bMiramax Books/Hyperion Books for Children,$cc2002. 
     *      300   $a500 p. ;$c22 cm. 
     *      650  1$aFantasy. 
     *      650  1$aBaseball$vFiction. 
     *      650  1$aMagic$vFiction.
     *      
     * </pre>
     * 
     * @return String - a string representation of this record
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("LEADER ");
        sb.append(getLeader().toString());
        sb.append('\n');
        Iterator i = getVariableFields().iterator();
        while (i.hasNext()) {
            VariableField field = (VariableField) i.next();
            sb.append(field.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

    public List find(String pattern) {
        List result = new ArrayList();
        Iterator i = controlFields.iterator();
        while (i.hasNext()) {
            VariableField field = (VariableField) i.next();
            if (field.find(pattern))
                result.add(field);
        }
        i = dataFields.iterator();
        while (i.hasNext()) {
            VariableField field = (VariableField) i.next();
            if (field.find(pattern))
                result.add(field);
        }
        return result;
    }

    public List find(String tag, String pattern) {
        List result = new ArrayList();
        Iterator i = getVariableFields(tag).iterator();
        while (i.hasNext()) {
            VariableField field = (VariableField) i.next();
            if (field.find(pattern))
                result.add(field);
        }
        return result;
    }

    public List find(String[] tag, String pattern) {
        List result = new ArrayList();
        Iterator i = getVariableFields(tag).iterator();
        while (i.hasNext()) {
            VariableField field = (VariableField) i.next();
            if (field.find(pattern))
                result.add(field);
        }
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}