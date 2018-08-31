/*
 * This file is generated by jOOQ.
 */
package test.generated;


import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import test.generated.tables.Answer;
import test.generated.tables.Answerlinks;
import test.generated.tables.Ask;
import test.generated.tables.Attribute;
import test.generated.tables.Baseentity;
import test.generated.tables.BaseentityAttribute;
import test.generated.tables.BaseentityBaseentity;
import test.generated.tables.Context;
import test.generated.tables.Gps;
import test.generated.tables.Question;
import test.generated.tables.QuestionQuestion;
import test.generated.tables.Rule;
import test.generated.tables.Template;
import test.generated.tables.Validation;
import test.generated.tables.records.AnswerRecord;
import test.generated.tables.records.AnswerlinksRecord;
import test.generated.tables.records.AskRecord;
import test.generated.tables.records.AttributeRecord;
import test.generated.tables.records.BaseentityAttributeRecord;
import test.generated.tables.records.BaseentityBaseentityRecord;
import test.generated.tables.records.BaseentityRecord;
import test.generated.tables.records.ContextRecord;
import test.generated.tables.records.GpsRecord;
import test.generated.tables.records.QuestionQuestionRecord;
import test.generated.tables.records.QuestionRecord;
import test.generated.tables.records.RuleRecord;
import test.generated.tables.records.TemplateRecord;
import test.generated.tables.records.ValidationRecord;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>gennydb</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AnswerRecord, Long> IDENTITY_ANSWER = Identities0.IDENTITY_ANSWER;
    public static final Identity<AskRecord, Long> IDENTITY_ASK = Identities0.IDENTITY_ASK;
    public static final Identity<AttributeRecord, Long> IDENTITY_ATTRIBUTE = Identities0.IDENTITY_ATTRIBUTE;
    public static final Identity<BaseentityRecord, Long> IDENTITY_BASEENTITY = Identities0.IDENTITY_BASEENTITY;
    public static final Identity<ContextRecord, Long> IDENTITY_CONTEXT = Identities0.IDENTITY_CONTEXT;
    public static final Identity<GpsRecord, Long> IDENTITY_GPS = Identities0.IDENTITY_GPS;
    public static final Identity<QuestionRecord, Long> IDENTITY_QUESTION = Identities0.IDENTITY_QUESTION;
    public static final Identity<RuleRecord, Long> IDENTITY_RULE = Identities0.IDENTITY_RULE;
    public static final Identity<TemplateRecord, Long> IDENTITY_TEMPLATE = Identities0.IDENTITY_TEMPLATE;
    public static final Identity<ValidationRecord, Long> IDENTITY_VALIDATION = Identities0.IDENTITY_VALIDATION;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AnswerRecord> KEY_ANSWER_PRIMARY = UniqueKeys0.KEY_ANSWER_PRIMARY;
    public static final UniqueKey<AnswerlinksRecord> KEY_ANSWERLINKS_PRIMARY = UniqueKeys0.KEY_ANSWERLINKS_PRIMARY;
    public static final UniqueKey<AskRecord> KEY_ASK_PRIMARY = UniqueKeys0.KEY_ASK_PRIMARY;
    public static final UniqueKey<AttributeRecord> KEY_ATTRIBUTE_PRIMARY = UniqueKeys0.KEY_ATTRIBUTE_PRIMARY;
    public static final UniqueKey<AttributeRecord> KEY_ATTRIBUTE_UK1774SHFID1UAOPL9TU8AM19FQ = UniqueKeys0.KEY_ATTRIBUTE_UK1774SHFID1UAOPL9TU8AM19FQ;
    public static final UniqueKey<AttributeRecord> KEY_ATTRIBUTE_UK_1774SHFID1UAOPL9TU8AM19FQ = UniqueKeys0.KEY_ATTRIBUTE_UK_1774SHFID1UAOPL9TU8AM19FQ;
    public static final UniqueKey<BaseentityRecord> KEY_BASEENTITY_PRIMARY = UniqueKeys0.KEY_BASEENTITY_PRIMARY;
    public static final UniqueKey<BaseentityRecord> KEY_BASEENTITY_UK_1I09VXA3DGNCFONFIR3RGPV0L = UniqueKeys0.KEY_BASEENTITY_UK_1I09VXA3DGNCFONFIR3RGPV0L;
    public static final UniqueKey<BaseentityAttributeRecord> KEY_BASEENTITY_ATTRIBUTE_PRIMARY = UniqueKeys0.KEY_BASEENTITY_ATTRIBUTE_PRIMARY;
    public static final UniqueKey<BaseentityBaseentityRecord> KEY_BASEENTITY_BASEENTITY_PRIMARY = UniqueKeys0.KEY_BASEENTITY_BASEENTITY_PRIMARY;
    public static final UniqueKey<ContextRecord> KEY_CONTEXT_PRIMARY = UniqueKeys0.KEY_CONTEXT_PRIMARY;
    public static final UniqueKey<GpsRecord> KEY_GPS_PRIMARY = UniqueKeys0.KEY_GPS_PRIMARY;
    public static final UniqueKey<QuestionRecord> KEY_QUESTION_PRIMARY = UniqueKeys0.KEY_QUESTION_PRIMARY;
    public static final UniqueKey<QuestionRecord> KEY_QUESTION_UKJYULUDYQFHB5B2AQE4D18WCBJ = UniqueKeys0.KEY_QUESTION_UKJYULUDYQFHB5B2AQE4D18WCBJ;
    public static final UniqueKey<QuestionRecord> KEY_QUESTION_UK_JYULUDYQFHB5B2AQE4D18WCBJ = UniqueKeys0.KEY_QUESTION_UK_JYULUDYQFHB5B2AQE4D18WCBJ;
    public static final UniqueKey<QuestionQuestionRecord> KEY_QUESTION_QUESTION_PRIMARY = UniqueKeys0.KEY_QUESTION_QUESTION_PRIMARY;
    public static final UniqueKey<RuleRecord> KEY_RULE_PRIMARY = UniqueKeys0.KEY_RULE_PRIMARY;
    public static final UniqueKey<RuleRecord> KEY_RULE_UKJTUMKUXYGWBGSUGGJL85SIP5X = UniqueKeys0.KEY_RULE_UKJTUMKUXYGWBGSUGGJL85SIP5X;
    public static final UniqueKey<RuleRecord> KEY_RULE_UK_JTUMKUXYGWBGSUGGJL85SIP5X = UniqueKeys0.KEY_RULE_UK_JTUMKUXYGWBGSUGGJL85SIP5X;
    public static final UniqueKey<TemplateRecord> KEY_TEMPLATE_PRIMARY = UniqueKeys0.KEY_TEMPLATE_PRIMARY;
    public static final UniqueKey<TemplateRecord> KEY_TEMPLATE_UK_7C4UIA1PMVXB1BTRW9I1K4EG8 = UniqueKeys0.KEY_TEMPLATE_UK_7C4UIA1PMVXB1BTRW9I1K4EG8;
    public static final UniqueKey<ValidationRecord> KEY_VALIDATION_PRIMARY = UniqueKeys0.KEY_VALIDATION_PRIMARY;
    public static final UniqueKey<ValidationRecord> KEY_VALIDATION_UKH7LRVRSCKUHDA9BJBAF27QM8H = UniqueKeys0.KEY_VALIDATION_UKH7LRVRSCKUHDA9BJBAF27QM8H;
    public static final UniqueKey<ValidationRecord> KEY_VALIDATION_UK_H7LRVRSCKUHDA9BJBAF27QM8H = UniqueKeys0.KEY_VALIDATION_UK_H7LRVRSCKUHDA9BJBAF27QM8H;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AnswerRecord, AttributeRecord> FKS5DOQAVR9YY08B1RQE4NDNT4N = ForeignKeys0.FKS5DOQAVR9YY08B1RQE4NDNT4N;
    public static final ForeignKey<AnswerlinksRecord, AttributeRecord> FKKOGSSGA01SXQUGJ97QMO71CED = ForeignKeys0.FKKOGSSGA01SXQUGJ97QMO71CED;
    public static final ForeignKey<AnswerlinksRecord, BaseentityRecord> FK9D7JG67OJPI3VTXSMT0IUTR8B = ForeignKeys0.FK9D7JG67OJPI3VTXSMT0IUTR8B;
    public static final ForeignKey<AnswerlinksRecord, BaseentityRecord> FKQD07YWJCH02UBS8Q5WMHN2JPS = ForeignKeys0.FKQD07YWJCH02UBS8Q5WMHN2JPS;
    public static final ForeignKey<AskRecord, QuestionRecord> FK3GB9GHT32CV27AJQS6YTICBMI = ForeignKeys0.FK3GB9GHT32CV27AJQS6YTICBMI;
    public static final ForeignKey<BaseentityAttributeRecord, AttributeRecord> FKAEDPN6CSUWK6UWM5KQH73TIWD = ForeignKeys0.FKAEDPN6CSUWK6UWM5KQH73TIWD;
    public static final ForeignKey<BaseentityAttributeRecord, BaseentityRecord> FKMQRQCXSQU49B0CLIY2TYMJOAE = ForeignKeys0.FKMQRQCXSQU49B0CLIY2TYMJOAE;
    public static final ForeignKey<BaseentityBaseentityRecord, AttributeRecord> FKGSPD8IJKXTSN0ROH902VVPQLC = ForeignKeys0.FKGSPD8IJKXTSN0ROH902VVPQLC;
    public static final ForeignKey<BaseentityBaseentityRecord, BaseentityRecord> FK5WX1AVQNHBIGUV2X4A2350HKJ = ForeignKeys0.FK5WX1AVQNHBIGUV2X4A2350HKJ;
    public static final ForeignKey<ContextRecord, BaseentityRecord> FKCM47NXEMLMM987FU8A6IW64AA = ForeignKeys0.FKCM47NXEMLMM987FU8A6IW64AA;
    public static final ForeignKey<ContextRecord, QuestionRecord> FKLDPYD6JX52JNIDTL6YX2OAS8 = ForeignKeys0.FKLDPYD6JX52JNIDTL6YX2OAS8;
    public static final ForeignKey<QuestionRecord, AttributeRecord> FKC8891U9MG0DOEMNWFXOV4E1W1 = ForeignKeys0.FKC8891U9MG0DOEMNWFXOV4E1W1;
    public static final ForeignKey<QuestionQuestionRecord, QuestionRecord> FK9NEX8VYNU3G1W30V78HE4KPWD = ForeignKeys0.FK9NEX8VYNU3G1W30V78HE4KPWD;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<AnswerRecord, Long> IDENTITY_ANSWER = Internal.createIdentity(Answer.ANSWER, Answer.ANSWER.ID);
        public static Identity<AskRecord, Long> IDENTITY_ASK = Internal.createIdentity(Ask.ASK, Ask.ASK.ID);
        public static Identity<AttributeRecord, Long> IDENTITY_ATTRIBUTE = Internal.createIdentity(Attribute.ATTRIBUTE, Attribute.ATTRIBUTE.ID);
        public static Identity<BaseentityRecord, Long> IDENTITY_BASEENTITY = Internal.createIdentity(Baseentity.BASEENTITY, Baseentity.BASEENTITY.ID);
        public static Identity<ContextRecord, Long> IDENTITY_CONTEXT = Internal.createIdentity(Context.CONTEXT, Context.CONTEXT.ID);
        public static Identity<GpsRecord, Long> IDENTITY_GPS = Internal.createIdentity(Gps.GPS, Gps.GPS.ID);
        public static Identity<QuestionRecord, Long> IDENTITY_QUESTION = Internal.createIdentity(Question.QUESTION, Question.QUESTION.ID);
        public static Identity<RuleRecord, Long> IDENTITY_RULE = Internal.createIdentity(Rule.RULE, Rule.RULE.ID);
        public static Identity<TemplateRecord, Long> IDENTITY_TEMPLATE = Internal.createIdentity(Template.TEMPLATE, Template.TEMPLATE.ID);
        public static Identity<ValidationRecord, Long> IDENTITY_VALIDATION = Internal.createIdentity(Validation.VALIDATION, Validation.VALIDATION.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AnswerRecord> KEY_ANSWER_PRIMARY = Internal.createUniqueKey(Answer.ANSWER, "KEY_answer_PRIMARY", Answer.ANSWER.ID);
        public static final UniqueKey<AnswerlinksRecord> KEY_ANSWERLINKS_PRIMARY = Internal.createUniqueKey(Answerlinks.ANSWERLINKS, "KEY_answerlinks_PRIMARY", Answerlinks.ANSWERLINKS.ATTRIBUTE_ID, Answerlinks.ANSWERLINKS.SOURCE_ID, Answerlinks.ANSWERLINKS.TARGET_ID);
        public static final UniqueKey<AskRecord> KEY_ASK_PRIMARY = Internal.createUniqueKey(Ask.ASK, "KEY_ask_PRIMARY", Ask.ASK.ID);
        public static final UniqueKey<AttributeRecord> KEY_ATTRIBUTE_PRIMARY = Internal.createUniqueKey(Attribute.ATTRIBUTE, "KEY_attribute_PRIMARY", Attribute.ATTRIBUTE.ID);
        public static final UniqueKey<AttributeRecord> KEY_ATTRIBUTE_UK1774SHFID1UAOPL9TU8AM19FQ = Internal.createUniqueKey(Attribute.ATTRIBUTE, "KEY_attribute_UK1774shfid1uaopl9tu8am19fq", Attribute.ATTRIBUTE.CODE);
        public static final UniqueKey<AttributeRecord> KEY_ATTRIBUTE_UK_1774SHFID1UAOPL9TU8AM19FQ = Internal.createUniqueKey(Attribute.ATTRIBUTE, "KEY_attribute_UK_1774shfid1uaopl9tu8am19fq", Attribute.ATTRIBUTE.CODE);
        public static final UniqueKey<BaseentityRecord> KEY_BASEENTITY_PRIMARY = Internal.createUniqueKey(Baseentity.BASEENTITY, "KEY_baseentity_PRIMARY", Baseentity.BASEENTITY.ID);
        public static final UniqueKey<BaseentityRecord> KEY_BASEENTITY_UK_1I09VXA3DGNCFONFIR3RGPV0L = Internal.createUniqueKey(Baseentity.BASEENTITY, "KEY_baseentity_UK_1i09vxa3dgncfonfir3rgpv0l", Baseentity.BASEENTITY.CODE);
        public static final UniqueKey<BaseentityAttributeRecord> KEY_BASEENTITY_ATTRIBUTE_PRIMARY = Internal.createUniqueKey(BaseentityAttribute.BASEENTITY_ATTRIBUTE, "KEY_baseentity_attribute_PRIMARY", BaseentityAttribute.BASEENTITY_ATTRIBUTE.ATTRIBUTE_ID, BaseentityAttribute.BASEENTITY_ATTRIBUTE.BASEENTITY_ID);
        public static final UniqueKey<BaseentityBaseentityRecord> KEY_BASEENTITY_BASEENTITY_PRIMARY = Internal.createUniqueKey(BaseentityBaseentity.BASEENTITY_BASEENTITY, "KEY_baseentity_baseentity_PRIMARY", BaseentityBaseentity.BASEENTITY_BASEENTITY.ATTRIBUTE_ID, BaseentityBaseentity.BASEENTITY_BASEENTITY.SOURCE_ID, BaseentityBaseentity.BASEENTITY_BASEENTITY.TARGETCODE);
        public static final UniqueKey<ContextRecord> KEY_CONTEXT_PRIMARY = Internal.createUniqueKey(Context.CONTEXT, "KEY_context_PRIMARY", Context.CONTEXT.ID);
        public static final UniqueKey<GpsRecord> KEY_GPS_PRIMARY = Internal.createUniqueKey(Gps.GPS, "KEY_gps_PRIMARY", Gps.GPS.ID);
        public static final UniqueKey<QuestionRecord> KEY_QUESTION_PRIMARY = Internal.createUniqueKey(Question.QUESTION, "KEY_question_PRIMARY", Question.QUESTION.ID);
        public static final UniqueKey<QuestionRecord> KEY_QUESTION_UKJYULUDYQFHB5B2AQE4D18WCBJ = Internal.createUniqueKey(Question.QUESTION, "KEY_question_UKjyuludyqfhb5b2aqe4d18wcbj", Question.QUESTION.CODE);
        public static final UniqueKey<QuestionRecord> KEY_QUESTION_UK_JYULUDYQFHB5B2AQE4D18WCBJ = Internal.createUniqueKey(Question.QUESTION, "KEY_question_UK_jyuludyqfhb5b2aqe4d18wcbj", Question.QUESTION.CODE);
        public static final UniqueKey<QuestionQuestionRecord> KEY_QUESTION_QUESTION_PRIMARY = Internal.createUniqueKey(QuestionQuestion.QUESTION_QUESTION, "KEY_question_question_PRIMARY", QuestionQuestion.QUESTION_QUESTION.SOURCE_ID, QuestionQuestion.QUESTION_QUESTION.SOURCECODE, QuestionQuestion.QUESTION_QUESTION.TARGETCODE);
        public static final UniqueKey<RuleRecord> KEY_RULE_PRIMARY = Internal.createUniqueKey(Rule.RULE, "KEY_rule_PRIMARY", Rule.RULE.ID);
        public static final UniqueKey<RuleRecord> KEY_RULE_UKJTUMKUXYGWBGSUGGJL85SIP5X = Internal.createUniqueKey(Rule.RULE, "KEY_rule_UKjtumkuxygwbgsuggjl85sip5x", Rule.RULE.CODE);
        public static final UniqueKey<RuleRecord> KEY_RULE_UK_JTUMKUXYGWBGSUGGJL85SIP5X = Internal.createUniqueKey(Rule.RULE, "KEY_rule_UK_jtumkuxygwbgsuggjl85sip5x", Rule.RULE.CODE);
        public static final UniqueKey<TemplateRecord> KEY_TEMPLATE_PRIMARY = Internal.createUniqueKey(Template.TEMPLATE, "KEY_template_PRIMARY", Template.TEMPLATE.ID);
        public static final UniqueKey<TemplateRecord> KEY_TEMPLATE_UK_7C4UIA1PMVXB1BTRW9I1K4EG8 = Internal.createUniqueKey(Template.TEMPLATE, "KEY_template_UK_7c4uia1pmvxb1btrw9i1k4eg8", Template.TEMPLATE.CODE);
        public static final UniqueKey<ValidationRecord> KEY_VALIDATION_PRIMARY = Internal.createUniqueKey(Validation.VALIDATION, "KEY_validation_PRIMARY", Validation.VALIDATION.ID);
        public static final UniqueKey<ValidationRecord> KEY_VALIDATION_UKH7LRVRSCKUHDA9BJBAF27QM8H = Internal.createUniqueKey(Validation.VALIDATION, "KEY_validation_UKh7lrvrsckuhda9bjbaf27qm8h", Validation.VALIDATION.CODE);
        public static final UniqueKey<ValidationRecord> KEY_VALIDATION_UK_H7LRVRSCKUHDA9BJBAF27QM8H = Internal.createUniqueKey(Validation.VALIDATION, "KEY_validation_UK_h7lrvrsckuhda9bjbaf27qm8h", Validation.VALIDATION.CODE);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<AnswerRecord, AttributeRecord> FKS5DOQAVR9YY08B1RQE4NDNT4N = Internal.createForeignKey(test.generated.Keys.KEY_ATTRIBUTE_PRIMARY, Answer.ANSWER, "FKs5doqavr9yy08b1rqe4ndnt4n", Answer.ANSWER.ATTRIBUTE_ID);
        public static final ForeignKey<AnswerlinksRecord, AttributeRecord> FKKOGSSGA01SXQUGJ97QMO71CED = Internal.createForeignKey(test.generated.Keys.KEY_ATTRIBUTE_PRIMARY, Answerlinks.ANSWERLINKS, "FKkogssga01sxqugj97qmo71ced", Answerlinks.ANSWERLINKS.ATTRIBUTE_ID);
        public static final ForeignKey<AnswerlinksRecord, BaseentityRecord> FK9D7JG67OJPI3VTXSMT0IUTR8B = Internal.createForeignKey(test.generated.Keys.KEY_BASEENTITY_PRIMARY, Answerlinks.ANSWERLINKS, "FK9d7jg67ojpi3vtxsmt0iutr8b", Answerlinks.ANSWERLINKS.TARGET_ID);
        public static final ForeignKey<AnswerlinksRecord, BaseentityRecord> FKQD07YWJCH02UBS8Q5WMHN2JPS = Internal.createForeignKey(test.generated.Keys.KEY_BASEENTITY_PRIMARY, Answerlinks.ANSWERLINKS, "FKqd07ywjch02ubs8q5wmhn2jps", Answerlinks.ANSWERLINKS.SOURCE_ID);
        public static final ForeignKey<AskRecord, QuestionRecord> FK3GB9GHT32CV27AJQS6YTICBMI = Internal.createForeignKey(test.generated.Keys.KEY_QUESTION_PRIMARY, Ask.ASK, "FK3gb9ght32cv27ajqs6yticbmi", Ask.ASK.QUESTION_ID);
        public static final ForeignKey<BaseentityAttributeRecord, AttributeRecord> FKAEDPN6CSUWK6UWM5KQH73TIWD = Internal.createForeignKey(test.generated.Keys.KEY_ATTRIBUTE_PRIMARY, BaseentityAttribute.BASEENTITY_ATTRIBUTE, "FKaedpn6csuwk6uwm5kqh73tiwd", BaseentityAttribute.BASEENTITY_ATTRIBUTE.ATTRIBUTE_ID);
        public static final ForeignKey<BaseentityAttributeRecord, BaseentityRecord> FKMQRQCXSQU49B0CLIY2TYMJOAE = Internal.createForeignKey(test.generated.Keys.KEY_BASEENTITY_PRIMARY, BaseentityAttribute.BASEENTITY_ATTRIBUTE, "FKmqrqcxsqu49b0cliy2tymjoae", BaseentityAttribute.BASEENTITY_ATTRIBUTE.BASEENTITY_ID);
        public static final ForeignKey<BaseentityBaseentityRecord, AttributeRecord> FKGSPD8IJKXTSN0ROH902VVPQLC = Internal.createForeignKey(test.generated.Keys.KEY_ATTRIBUTE_PRIMARY, BaseentityBaseentity.BASEENTITY_BASEENTITY, "FKgspd8ijkxtsn0roh902vvpqlc", BaseentityBaseentity.BASEENTITY_BASEENTITY.ATTRIBUTE_ID);
        public static final ForeignKey<BaseentityBaseentityRecord, BaseentityRecord> FK5WX1AVQNHBIGUV2X4A2350HKJ = Internal.createForeignKey(test.generated.Keys.KEY_BASEENTITY_PRIMARY, BaseentityBaseentity.BASEENTITY_BASEENTITY, "FK5wx1avqnhbiguv2x4a2350hkj", BaseentityBaseentity.BASEENTITY_BASEENTITY.SOURCE_ID);
        public static final ForeignKey<ContextRecord, BaseentityRecord> FKCM47NXEMLMM987FU8A6IW64AA = Internal.createForeignKey(test.generated.Keys.KEY_BASEENTITY_PRIMARY, Context.CONTEXT, "FKcm47nxemlmm987fu8a6iw64aa", Context.CONTEXT.ATTRIBUTE_ID);
        public static final ForeignKey<ContextRecord, QuestionRecord> FKLDPYD6JX52JNIDTL6YX2OAS8 = Internal.createForeignKey(test.generated.Keys.KEY_QUESTION_PRIMARY, Context.CONTEXT, "FKldpyd6jx52jnidtl6yx2oas8", Context.CONTEXT.LIST_ID);
        public static final ForeignKey<QuestionRecord, AttributeRecord> FKC8891U9MG0DOEMNWFXOV4E1W1 = Internal.createForeignKey(test.generated.Keys.KEY_ATTRIBUTE_PRIMARY, Question.QUESTION, "FKc8891u9mg0doemnwfxov4e1w1", Question.QUESTION.ATTRIBUTE_ID);
        public static final ForeignKey<QuestionQuestionRecord, QuestionRecord> FK9NEX8VYNU3G1W30V78HE4KPWD = Internal.createForeignKey(test.generated.Keys.KEY_QUESTION_PRIMARY, QuestionQuestion.QUESTION_QUESTION, "FK9nex8vynu3g1w30v78he4kpwd", QuestionQuestion.QUESTION_QUESTION.SOURCE_ID);
    }
}
