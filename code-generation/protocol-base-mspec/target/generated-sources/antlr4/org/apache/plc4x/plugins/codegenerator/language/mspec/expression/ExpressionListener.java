// Generated from org\apache\plc4x\plugins\codegenerator\language\mspec\expression\Expression.g4 by ANTLR 4.9.2
package org.apache.plc4x.plugins.codegenerator.language.mspec.expression;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#expressionString}.
	 * @param ctx the parse tree
	 */
	void enterExpressionString(ExpressionParser.ExpressionStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#expressionString}.
	 * @param ctx the parse tree
	 */
	void exitExpressionString(ExpressionParser.ExpressionStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitShiftExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitShiftExpression(ExpressionParser.BitShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitShiftExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitShiftExpression(ExpressionParser.BitShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpression(ExpressionParser.BoolExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpression(ExpressionParser.BoolExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitOrExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitOrExpression(ExpressionParser.BitOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitOrExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitOrExpression(ExpressionParser.BitOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpression(ExpressionParser.NumberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpression(ExpressionParser.NumberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(ExpressionParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(ExpressionParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(ExpressionParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(ExpressionParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(ExpressionParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(ExpressionParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryMinusExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpression(ExpressionParser.UnaryMinusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryMinusExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpression(ExpressionParser.UnaryMinusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code powerExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPowerExpression(ExpressionParser.PowerExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code powerExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPowerExpression(ExpressionParser.PowerExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqExpression(ExpressionParser.EqExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqExpression(ExpressionParser.EqExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(ExpressionParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(ExpressionParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpression(ExpressionParser.StringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpression(ExpressionParser.StringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitAndExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitAndExpression(ExpressionParser.BitAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitAndExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitAndExpression(ExpressionParser.BitAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionExpression(ExpressionParser.ExpressionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionExpression(ExpressionParser.ExpressionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(ExpressionParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(ExpressionParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompExpression(ExpressionParser.CompExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompExpression(ExpressionParser.CompExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNullExpression(ExpressionParser.NullExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNullExpression(ExpressionParser.NullExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultExpression(ExpressionParser.MultExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultExpression(ExpressionParser.MultExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIfExpression(ExpressionParser.IfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExpression}
	 * labeled alternative in {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIfExpression(ExpressionParser.IfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#identifierSegment}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierSegment(ExpressionParser.IdentifierSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#identifierSegment}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierSegment(ExpressionParser.IdentifierSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#identifierSegmentArguments}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierSegmentArguments(ExpressionParser.IdentifierSegmentArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#identifierSegmentArguments}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierSegmentArguments(ExpressionParser.IdentifierSegmentArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#identifierSegmentIndexes}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierSegmentIndexes(ExpressionParser.IdentifierSegmentIndexesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#identifierSegmentIndexes}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierSegmentIndexes(ExpressionParser.IdentifierSegmentIndexesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#identifierSegmentRest}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierSegmentRest(ExpressionParser.IdentifierSegmentRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#identifierSegmentRest}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierSegmentRest(ExpressionParser.IdentifierSegmentRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(ExpressionParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(ExpressionParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#indexes}.
	 * @param ctx the parse tree
	 */
	void enterIndexes(ExpressionParser.IndexesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#indexes}.
	 * @param ctx the parse tree
	 */
	void exitIndexes(ExpressionParser.IndexesContext ctx);
}