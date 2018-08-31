/*
 * This file is generated by jOOQ.
 */
package test.generated.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import test.generated.Gennydb;
import test.generated.Indexes;
import test.generated.Keys;
import test.generated.tables.records.ContextRecord;


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
public class Context extends TableImpl<ContextRecord> {

    private static final long serialVersionUID = -1728352504;

    /**
     * The reference instance of <code>gennydb.context</code>
     */
    public static final Context CONTEXT = new Context();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ContextRecord> getRecordType() {
        return ContextRecord.class;
    }

    /**
     * The column <code>gennydb.context.id</code>.
     */
    public final TableField<ContextRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>gennydb.context.created</code>.
     */
    public final TableField<ContextRecord, Timestamp> CREATED = createField("created", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>gennydb.context.name</code>.
     */
    public final TableField<ContextRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>gennydb.context.realm</code>.
     */
    public final TableField<ContextRecord, String> REALM = createField("realm", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>gennydb.context.updated</code>.
     */
    public final TableField<ContextRecord, Timestamp> UPDATED = createField("updated", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>gennydb.context.attribute_id</code>.
     */
    public final TableField<ContextRecord, Long> ATTRIBUTE_ID = createField("attribute_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>gennydb.context.list_id</code>.
     */
    public final TableField<ContextRecord, Long> LIST_ID = createField("list_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>gennydb.context</code> table reference
     */
    public Context() {
        this(DSL.name("context"), null);
    }

    /**
     * Create an aliased <code>gennydb.context</code> table reference
     */
    public Context(String alias) {
        this(DSL.name(alias), CONTEXT);
    }

    /**
     * Create an aliased <code>gennydb.context</code> table reference
     */
    public Context(Name alias) {
        this(alias, CONTEXT);
    }

    private Context(Name alias, Table<ContextRecord> aliased) {
        this(alias, aliased, null);
    }

    private Context(Name alias, Table<ContextRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Context(Table<O> child, ForeignKey<O, ContextRecord> key) {
        super(child, key, CONTEXT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Gennydb.GENNYDB;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CONTEXT_FKCM47NXEMLMM987FU8A6IW64AA, Indexes.CONTEXT_FKLDPYD6JX52JNIDTL6YX2OAS8, Indexes.CONTEXT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ContextRecord, Long> getIdentity() {
        return Keys.IDENTITY_CONTEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ContextRecord> getPrimaryKey() {
        return Keys.KEY_CONTEXT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ContextRecord>> getKeys() {
        return Arrays.<UniqueKey<ContextRecord>>asList(Keys.KEY_CONTEXT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<ContextRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ContextRecord, ?>>asList(Keys.FKCM47NXEMLMM987FU8A6IW64AA, Keys.FKLDPYD6JX52JNIDTL6YX2OAS8);
    }

    public Baseentity baseentity() {
        return new Baseentity(this, Keys.FKCM47NXEMLMM987FU8A6IW64AA);
    }

    public Question question() {
        return new Question(this, Keys.FKLDPYD6JX52JNIDTL6YX2OAS8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Context as(String alias) {
        return new Context(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Context as(Name alias) {
        return new Context(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Context rename(String name) {
        return new Context(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Context rename(Name name) {
        return new Context(name, null);
    }
}
