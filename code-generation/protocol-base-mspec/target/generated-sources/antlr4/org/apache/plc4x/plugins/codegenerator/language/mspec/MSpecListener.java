// Generated from org\apache\plc4x\plugins\codegenerator\language\mspec\MSpec.g4 by ANTLR 4.9.2
package org.apache.plc4x.plugins.codegenerator.language.mspec;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MSpecParser}.
 */
public interface MSpecListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MSpecParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(MSpecParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(MSpecParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#complexTypeDefinition}.
	 * @param ctx the parse tree
	 */
	void enterComplexTypeDefinition(MSpecParser.ComplexTypeDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#complexTypeDefinition}.
	 * @param ctx the parse tree
	 */
	void exitComplexTypeDefinition(MSpecParser.ComplexTypeDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#complexType}.
	 * @param ctx the parse tree
	 */
	void enterComplexType(MSpecParser.ComplexTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#complexType}.
	 * @param ctx the parse tree
	 */
	void exitComplexType(MSpecParser.ComplexTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#fieldDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFieldDefinition(MSpecParser.FieldDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#fieldDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFieldDefinition(MSpecParser.FieldDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#dataIoDefinition}.
	 * @param ctx the parse tree
	 */
	void enterDataIoDefinition(MSpecParser.DataIoDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#dataIoDefinition}.
	 * @param ctx the parse tree
	 */
	void exitDataIoDefinition(MSpecParser.DataIoDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(MSpecParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(MSpecParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#abstractField}.
	 * @param ctx the parse tree
	 */
	void enterAbstractField(MSpecParser.AbstractFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#abstractField}.
	 * @param ctx the parse tree
	 */
	void exitAbstractField(MSpecParser.AbstractFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#arrayField}.
	 * @param ctx the parse tree
	 */
	void enterArrayField(MSpecParser.ArrayFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#arrayField}.
	 * @param ctx the parse tree
	 */
	void exitArrayField(MSpecParser.ArrayFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#checksumField}.
	 * @param ctx the parse tree
	 */
	void enterChecksumField(MSpecParser.ChecksumFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#checksumField}.
	 * @param ctx the parse tree
	 */
	void exitChecksumField(MSpecParser.ChecksumFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#constField}.
	 * @param ctx the parse tree
	 */
	void enterConstField(MSpecParser.ConstFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#constField}.
	 * @param ctx the parse tree
	 */
	void exitConstField(MSpecParser.ConstFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#discriminatorField}.
	 * @param ctx the parse tree
	 */
	void enterDiscriminatorField(MSpecParser.DiscriminatorFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#discriminatorField}.
	 * @param ctx the parse tree
	 */
	void exitDiscriminatorField(MSpecParser.DiscriminatorFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#enumField}.
	 * @param ctx the parse tree
	 */
	void enterEnumField(MSpecParser.EnumFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#enumField}.
	 * @param ctx the parse tree
	 */
	void exitEnumField(MSpecParser.EnumFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#implicitField}.
	 * @param ctx the parse tree
	 */
	void enterImplicitField(MSpecParser.ImplicitFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#implicitField}.
	 * @param ctx the parse tree
	 */
	void exitImplicitField(MSpecParser.ImplicitFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#manualArrayField}.
	 * @param ctx the parse tree
	 */
	void enterManualArrayField(MSpecParser.ManualArrayFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#manualArrayField}.
	 * @param ctx the parse tree
	 */
	void exitManualArrayField(MSpecParser.ManualArrayFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#manualField}.
	 * @param ctx the parse tree
	 */
	void enterManualField(MSpecParser.ManualFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#manualField}.
	 * @param ctx the parse tree
	 */
	void exitManualField(MSpecParser.ManualFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#optionalField}.
	 * @param ctx the parse tree
	 */
	void enterOptionalField(MSpecParser.OptionalFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#optionalField}.
	 * @param ctx the parse tree
	 */
	void exitOptionalField(MSpecParser.OptionalFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#paddingField}.
	 * @param ctx the parse tree
	 */
	void enterPaddingField(MSpecParser.PaddingFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#paddingField}.
	 * @param ctx the parse tree
	 */
	void exitPaddingField(MSpecParser.PaddingFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#reservedField}.
	 * @param ctx the parse tree
	 */
	void enterReservedField(MSpecParser.ReservedFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#reservedField}.
	 * @param ctx the parse tree
	 */
	void exitReservedField(MSpecParser.ReservedFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#simpleField}.
	 * @param ctx the parse tree
	 */
	void enterSimpleField(MSpecParser.SimpleFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#simpleField}.
	 * @param ctx the parse tree
	 */
	void exitSimpleField(MSpecParser.SimpleFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#typeSwitchField}.
	 * @param ctx the parse tree
	 */
	void enterTypeSwitchField(MSpecParser.TypeSwitchFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#typeSwitchField}.
	 * @param ctx the parse tree
	 */
	void exitTypeSwitchField(MSpecParser.TypeSwitchFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#unknownField}.
	 * @param ctx the parse tree
	 */
	void enterUnknownField(MSpecParser.UnknownFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#unknownField}.
	 * @param ctx the parse tree
	 */
	void exitUnknownField(MSpecParser.UnknownFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#virtualField}.
	 * @param ctx the parse tree
	 */
	void enterVirtualField(MSpecParser.VirtualFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#virtualField}.
	 * @param ctx the parse tree
	 */
	void exitVirtualField(MSpecParser.VirtualFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#enumValueDefinition}.
	 * @param ctx the parse tree
	 */
	void enterEnumValueDefinition(MSpecParser.EnumValueDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#enumValueDefinition}.
	 * @param ctx the parse tree
	 */
	void exitEnumValueDefinition(MSpecParser.EnumValueDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#typeReference}.
	 * @param ctx the parse tree
	 */
	void enterTypeReference(MSpecParser.TypeReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#typeReference}.
	 * @param ctx the parse tree
	 */
	void exitTypeReference(MSpecParser.TypeReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(MSpecParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(MSpecParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(MSpecParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(MSpecParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(MSpecParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(MSpecParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(MSpecParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(MSpecParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MSpecParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MSpecParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#multipleExpressions}.
	 * @param ctx the parse tree
	 */
	void enterMultipleExpressions(MSpecParser.MultipleExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#multipleExpressions}.
	 * @param ctx the parse tree
	 */
	void exitMultipleExpressions(MSpecParser.MultipleExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#innerExpression}.
	 * @param ctx the parse tree
	 */
	void enterInnerExpression(MSpecParser.InnerExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#innerExpression}.
	 * @param ctx the parse tree
	 */
	void exitInnerExpression(MSpecParser.InnerExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MSpecParser#idExpression}.
	 * @param ctx the parse tree
	 */
	void enterIdExpression(MSpecParser.IdExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MSpecParser#idExpression}.
	 * @param ctx the parse tree
	 */
	void exitIdExpression(MSpecParser.IdExpressionContext ctx);
}