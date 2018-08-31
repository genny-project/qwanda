/*
 * This file is generated by jOOQ.
 */
package test.generated.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;

import test.generated.tables.Validation;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ValidationRecord extends UpdatableRecordImpl<ValidationRecord> implements Record11<String, Long, Timestamp, String, String, Timestamp, String, Boolean, Boolean, String, String> {

    private static final long serialVersionUID = 1389039282;

    /**
     * Setter for <code>gennydb.validation.dtype</code>.
     */
    public void setDtype(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>gennydb.validation.dtype</code>.
     */
    public String getDtype() {
        return (String) get(0);
    }

    /**
     * Setter for <code>gennydb.validation.id</code>.
     */
    public void setId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>gennydb.validation.id</code>.
     */
    public Long getId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>gennydb.validation.created</code>.
     */
    public void setCreated(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>gennydb.validation.created</code>.
     */
    public Timestamp getCreated() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>gennydb.validation.name</code>.
     */
    public void setName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>gennydb.validation.name</code>.
     */
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>gennydb.validation.realm</code>.
     */
    public void setRealm(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>gennydb.validation.realm</code>.
     */
    public String getRealm() {
        return (String) get(4);
    }

    /**
     * Setter for <code>gennydb.validation.updated</code>.
     */
    public void setUpdated(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>gennydb.validation.updated</code>.
     */
    public Timestamp getUpdated() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>gennydb.validation.code</code>.
     */
    public void setCode(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>gennydb.validation.code</code>.
     */
    public String getCode() {
        return (String) get(6);
    }

    /**
     * Setter for <code>gennydb.validation.multiAllowed</code>.
     */
    public void setMultiallowed(Boolean value) {
        set(7, value);
    }

    /**
     * Getter for <code>gennydb.validation.multiAllowed</code>.
     */
    public Boolean getMultiallowed() {
        return (Boolean) get(7);
    }

    /**
     * Setter for <code>gennydb.validation.recursiveGroup</code>.
     */
    public void setRecursivegroup(Boolean value) {
        set(8, value);
    }

    /**
     * Getter for <code>gennydb.validation.recursiveGroup</code>.
     */
    public Boolean getRecursivegroup() {
        return (Boolean) get(8);
    }

    /**
     * Setter for <code>gennydb.validation.regex</code>.
     */
    public void setRegex(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>gennydb.validation.regex</code>.
     */
    public String getRegex() {
        return (String) get(9);
    }

    /**
     * Setter for <code>gennydb.validation.selection_grp</code>.
     */
    public void setSelectionGrp(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>gennydb.validation.selection_grp</code>.
     */
    public String getSelectionGrp() {
        return (String) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<String, Long, Timestamp, String, String, Timestamp, String, Boolean, Boolean, String, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row11<String, Long, Timestamp, String, String, Timestamp, String, Boolean, Boolean, String, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Validation.VALIDATION.DTYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return Validation.VALIDATION.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return Validation.VALIDATION.CREATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Validation.VALIDATION.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Validation.VALIDATION.REALM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return Validation.VALIDATION.UPDATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Validation.VALIDATION.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field8() {
        return Validation.VALIDATION.MULTIALLOWED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field9() {
        return Validation.VALIDATION.RECURSIVEGROUP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return Validation.VALIDATION.REGEX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return Validation.VALIDATION.SELECTION_GRP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component1() {
        return getDtype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component2() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component3() {
        return getCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getRealm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component6() {
        return getUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component8() {
        return getMultiallowed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component9() {
        return getRecursivegroup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getRegex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getSelectionGrp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getDtype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getRealm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value8() {
        return getMultiallowed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value9() {
        return getRecursivegroup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getRegex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getSelectionGrp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value1(String value) {
        setDtype(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value2(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value3(Timestamp value) {
        setCreated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value4(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value5(String value) {
        setRealm(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value6(Timestamp value) {
        setUpdated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value7(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value8(Boolean value) {
        setMultiallowed(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value9(Boolean value) {
        setRecursivegroup(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value10(String value) {
        setRegex(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord value11(String value) {
        setSelectionGrp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationRecord values(String value1, Long value2, Timestamp value3, String value4, String value5, Timestamp value6, String value7, Boolean value8, Boolean value9, String value10, String value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ValidationRecord
     */
    public ValidationRecord() {
        super(Validation.VALIDATION);
    }

    /**
     * Create a detached, initialised ValidationRecord
     */
    public ValidationRecord(String dtype, Long id, Timestamp created, String name, String realm, Timestamp updated, String code, Boolean multiallowed, Boolean recursivegroup, String regex, String selectionGrp) {
        super(Validation.VALIDATION);

        set(0, dtype);
        set(1, id);
        set(2, created);
        set(3, name);
        set(4, realm);
        set(5, updated);
        set(6, code);
        set(7, multiallowed);
        set(8, recursivegroup);
        set(9, regex);
        set(10, selectionGrp);
    }
}
