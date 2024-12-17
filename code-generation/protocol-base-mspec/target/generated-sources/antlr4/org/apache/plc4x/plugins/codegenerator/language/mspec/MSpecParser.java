// Generated from org\apache\plc4x\plugins\codegenerator\language\mspec\MSpec.g4 by ANTLR 4.9.2
package org.apache.plc4x.plugins.codegenerator.language.mspec;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MSpecParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, TICK=38, LBRACKET=39, 
		RBRACKET=40, LCBRACKET=41, RCBRACKET=42, BinaryOperator=43, ARRAY_LOOP_TYPE=44, 
		INTEGER_LITERAL=45, HEX_LITERAL=46, BOOLEAN_LITERAL=47, STRING_LITERAL=48, 
		IDENTIFIER_LITERAL=49, LINE_COMMENT=50, BLOCK_COMMENT=51, WS=52;
	public static final int
		RULE_file = 0, RULE_complexTypeDefinition = 1, RULE_complexType = 2, RULE_fieldDefinition = 3, 
		RULE_dataIoDefinition = 4, RULE_field = 5, RULE_abstractField = 6, RULE_arrayField = 7, 
		RULE_checksumField = 8, RULE_constField = 9, RULE_discriminatorField = 10, 
		RULE_enumField = 11, RULE_implicitField = 12, RULE_manualArrayField = 13, 
		RULE_manualField = 14, RULE_optionalField = 15, RULE_paddingField = 16, 
		RULE_reservedField = 17, RULE_simpleField = 18, RULE_typeSwitchField = 19, 
		RULE_unknownField = 20, RULE_virtualField = 21, RULE_enumValueDefinition = 22, 
		RULE_typeReference = 23, RULE_caseStatement = 24, RULE_dataType = 25, 
		RULE_argument = 26, RULE_argumentList = 27, RULE_expression = 28, RULE_multipleExpressions = 29, 
		RULE_innerExpression = 30, RULE_idExpression = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "complexTypeDefinition", "complexType", "fieldDefinition", "dataIoDefinition", 
			"field", "abstractField", "arrayField", "checksumField", "constField", 
			"discriminatorField", "enumField", "implicitField", "manualArrayField", 
			"manualField", "optionalField", "paddingField", "reservedField", "simpleField", 
			"typeSwitchField", "unknownField", "virtualField", "enumValueDefinition", 
			"typeReference", "caseStatement", "dataType", "argument", "argumentList", 
			"expression", "multipleExpressions", "innerExpression", "idExpression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'type'", "'discriminatedType'", "'enum'", "'dataIo'", "'abstract'", 
			"'array'", "'checksum'", "'const'", "'discriminator'", "'implicit'", 
			"'manualArray'", "'manual'", "'optional'", "'padding'", "'reserved'", 
			"'simple'", "'typeSwitch'", "'unknown'", "'virtual'", "'bit'", "'byte'", 
			"'int'", "'uint'", "'float'", "'.'", "'ufloat'", "'string'", "'time'", 
			"'date'", "'dateTime'", "','", "'('", "')'", "'?'", "':'", "'\"'", "'!'", 
			"'''", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "TICK", "LBRACKET", "RBRACKET", "LCBRACKET", "RCBRACKET", 
			"BinaryOperator", "ARRAY_LOOP_TYPE", "INTEGER_LITERAL", "HEX_LITERAL", 
			"BOOLEAN_LITERAL", "STRING_LITERAL", "IDENTIFIER_LITERAL", "LINE_COMMENT", 
			"BLOCK_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MSpec.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MSpecParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MSpecParser.EOF, 0); }
		public List<ComplexTypeDefinitionContext> complexTypeDefinition() {
			return getRuleContexts(ComplexTypeDefinitionContext.class);
		}
		public ComplexTypeDefinitionContext complexTypeDefinition(int i) {
			return getRuleContext(ComplexTypeDefinitionContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACKET) {
				{
				{
				setState(64);
				complexTypeDefinition();
				}
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(70);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComplexTypeDefinitionContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(MSpecParser.LBRACKET, 0); }
		public ComplexTypeContext complexType() {
			return getRuleContext(ComplexTypeContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(MSpecParser.RBRACKET, 0); }
		public ComplexTypeDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complexTypeDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterComplexTypeDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitComplexTypeDefinition(this);
		}
	}

	public final ComplexTypeDefinitionContext complexTypeDefinition() throws RecognitionException {
		ComplexTypeDefinitionContext _localctx = new ComplexTypeDefinitionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_complexTypeDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(LBRACKET);
			setState(73);
			complexType();
			setState(74);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComplexTypeContext extends ParserRuleContext {
		public IdExpressionContext name;
		public ArgumentListContext params;
		public TypeReferenceContext type;
		public EnumValueDefinitionContext enumValues;
		public DataIoDefinitionContext dataIoTypeSwitch;
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public TerminalNode LBRACKET() { return getToken(MSpecParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(MSpecParser.RBRACKET, 0); }
		public List<FieldDefinitionContext> fieldDefinition() {
			return getRuleContexts(FieldDefinitionContext.class);
		}
		public FieldDefinitionContext fieldDefinition(int i) {
			return getRuleContext(FieldDefinitionContext.class,i);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public List<EnumValueDefinitionContext> enumValueDefinition() {
			return getRuleContexts(EnumValueDefinitionContext.class);
		}
		public EnumValueDefinitionContext enumValueDefinition(int i) {
			return getRuleContext(EnumValueDefinitionContext.class,i);
		}
		public DataIoDefinitionContext dataIoDefinition() {
			return getRuleContext(DataIoDefinitionContext.class,0);
		}
		public ComplexTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complexType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterComplexType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitComplexType(this);
		}
	}

	public final ComplexTypeContext complexType() throws RecognitionException {
		ComplexTypeContext _localctx = new ComplexTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_complexType);
		int _la;
		try {
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				match(T__0);
				setState(77);
				((ComplexTypeContext)_localctx).name = idExpression();
				setState(82);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(78);
					match(LBRACKET);
					setState(79);
					((ComplexTypeContext)_localctx).params = argumentList();
					setState(80);
					match(RBRACKET);
					}
					break;
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LBRACKET) {
					{
					{
					setState(84);
					fieldDefinition();
					}
					}
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				match(T__1);
				setState(91);
				((ComplexTypeContext)_localctx).name = idExpression();
				setState(96);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(92);
					match(LBRACKET);
					setState(93);
					((ComplexTypeContext)_localctx).params = argumentList();
					setState(94);
					match(RBRACKET);
					}
					break;
				}
				setState(99); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(98);
					fieldDefinition();
					}
					}
					setState(101); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LBRACKET );
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(103);
				match(T__2);
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << IDENTIFIER_LITERAL))) != 0)) {
					{
					setState(104);
					((ComplexTypeContext)_localctx).type = typeReference();
					}
				}

				setState(107);
				((ComplexTypeContext)_localctx).name = idExpression();
				setState(112);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(108);
					match(LBRACKET);
					setState(109);
					((ComplexTypeContext)_localctx).params = argumentList();
					setState(110);
					match(RBRACKET);
					}
					break;
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(114);
					((ComplexTypeContext)_localctx).enumValues = enumValueDefinition();
					}
					}
					setState(117); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LBRACKET );
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(119);
				match(T__3);
				setState(120);
				((ComplexTypeContext)_localctx).name = idExpression();
				setState(125);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(121);
					match(LBRACKET);
					setState(122);
					((ComplexTypeContext)_localctx).params = argumentList();
					setState(123);
					match(RBRACKET);
					}
					break;
				}
				setState(127);
				((ComplexTypeContext)_localctx).dataIoTypeSwitch = dataIoDefinition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDefinitionContext extends ParserRuleContext {
		public MultipleExpressionsContext params;
		public List<TerminalNode> LBRACKET() { return getTokens(MSpecParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(MSpecParser.LBRACKET, i);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(MSpecParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(MSpecParser.RBRACKET, i);
		}
		public MultipleExpressionsContext multipleExpressions() {
			return getRuleContext(MultipleExpressionsContext.class,0);
		}
		public FieldDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterFieldDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitFieldDefinition(this);
		}
	}

	public final FieldDefinitionContext fieldDefinition() throws RecognitionException {
		FieldDefinitionContext _localctx = new FieldDefinitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fieldDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(LBRACKET);
			setState(132);
			field();
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(133);
				match(LBRACKET);
				setState(134);
				((FieldDefinitionContext)_localctx).params = multipleExpressions();
				setState(135);
				match(RBRACKET);
				}
			}

			setState(139);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataIoDefinitionContext extends ParserRuleContext {
		public MultipleExpressionsContext params;
		public List<TerminalNode> LBRACKET() { return getTokens(MSpecParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(MSpecParser.LBRACKET, i);
		}
		public TypeSwitchFieldContext typeSwitchField() {
			return getRuleContext(TypeSwitchFieldContext.class,0);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(MSpecParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(MSpecParser.RBRACKET, i);
		}
		public MultipleExpressionsContext multipleExpressions() {
			return getRuleContext(MultipleExpressionsContext.class,0);
		}
		public DataIoDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataIoDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterDataIoDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitDataIoDefinition(this);
		}
	}

	public final DataIoDefinitionContext dataIoDefinition() throws RecognitionException {
		DataIoDefinitionContext _localctx = new DataIoDefinitionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_dataIoDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(LBRACKET);
			setState(142);
			typeSwitchField();
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(143);
				match(LBRACKET);
				setState(144);
				((DataIoDefinitionContext)_localctx).params = multipleExpressions();
				setState(145);
				match(RBRACKET);
				}
			}

			setState(149);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public AbstractFieldContext abstractField() {
			return getRuleContext(AbstractFieldContext.class,0);
		}
		public ArrayFieldContext arrayField() {
			return getRuleContext(ArrayFieldContext.class,0);
		}
		public ChecksumFieldContext checksumField() {
			return getRuleContext(ChecksumFieldContext.class,0);
		}
		public ConstFieldContext constField() {
			return getRuleContext(ConstFieldContext.class,0);
		}
		public DiscriminatorFieldContext discriminatorField() {
			return getRuleContext(DiscriminatorFieldContext.class,0);
		}
		public EnumFieldContext enumField() {
			return getRuleContext(EnumFieldContext.class,0);
		}
		public ImplicitFieldContext implicitField() {
			return getRuleContext(ImplicitFieldContext.class,0);
		}
		public ManualArrayFieldContext manualArrayField() {
			return getRuleContext(ManualArrayFieldContext.class,0);
		}
		public ManualFieldContext manualField() {
			return getRuleContext(ManualFieldContext.class,0);
		}
		public OptionalFieldContext optionalField() {
			return getRuleContext(OptionalFieldContext.class,0);
		}
		public PaddingFieldContext paddingField() {
			return getRuleContext(PaddingFieldContext.class,0);
		}
		public ReservedFieldContext reservedField() {
			return getRuleContext(ReservedFieldContext.class,0);
		}
		public SimpleFieldContext simpleField() {
			return getRuleContext(SimpleFieldContext.class,0);
		}
		public TypeSwitchFieldContext typeSwitchField() {
			return getRuleContext(TypeSwitchFieldContext.class,0);
		}
		public UnknownFieldContext unknownField() {
			return getRuleContext(UnknownFieldContext.class,0);
		}
		public VirtualFieldContext virtualField() {
			return getRuleContext(VirtualFieldContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_field);
		try {
			setState(167);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				abstractField();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				arrayField();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				checksumField();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(154);
				constField();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(155);
				discriminatorField();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 6);
				{
				setState(156);
				enumField();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 7);
				{
				setState(157);
				implicitField();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 8);
				{
				setState(158);
				manualArrayField();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 9);
				{
				setState(159);
				manualField();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 10);
				{
				setState(160);
				optionalField();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 11);
				{
				setState(161);
				paddingField();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 12);
				{
				setState(162);
				reservedField();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 13);
				{
				setState(163);
				simpleField();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 14);
				{
				setState(164);
				typeSwitchField();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 15);
				{
				setState(165);
				unknownField();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 16);
				{
				setState(166);
				virtualField();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbstractFieldContext extends ParserRuleContext {
		public TypeReferenceContext type;
		public IdExpressionContext name;
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public AbstractFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abstractField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterAbstractField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitAbstractField(this);
		}
	}

	public final AbstractFieldContext abstractField() throws RecognitionException {
		AbstractFieldContext _localctx = new AbstractFieldContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_abstractField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__4);
			setState(170);
			((AbstractFieldContext)_localctx).type = typeReference();
			setState(171);
			((AbstractFieldContext)_localctx).name = idExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayFieldContext extends ParserRuleContext {
		public TypeReferenceContext type;
		public IdExpressionContext name;
		public Token loopType;
		public ExpressionContext loopExpression;
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public TerminalNode ARRAY_LOOP_TYPE() { return getToken(MSpecParser.ARRAY_LOOP_TYPE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterArrayField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitArrayField(this);
		}
	}

	public final ArrayFieldContext arrayField() throws RecognitionException {
		ArrayFieldContext _localctx = new ArrayFieldContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_arrayField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(T__5);
			setState(174);
			((ArrayFieldContext)_localctx).type = typeReference();
			setState(175);
			((ArrayFieldContext)_localctx).name = idExpression();
			setState(176);
			((ArrayFieldContext)_localctx).loopType = match(ARRAY_LOOP_TYPE);
			setState(177);
			((ArrayFieldContext)_localctx).loopExpression = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ChecksumFieldContext extends ParserRuleContext {
		public DataTypeContext type;
		public IdExpressionContext name;
		public ExpressionContext checksumExpression;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ChecksumFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checksumField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterChecksumField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitChecksumField(this);
		}
	}

	public final ChecksumFieldContext checksumField() throws RecognitionException {
		ChecksumFieldContext _localctx = new ChecksumFieldContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_checksumField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(T__6);
			setState(180);
			((ChecksumFieldContext)_localctx).type = dataType();
			setState(181);
			((ChecksumFieldContext)_localctx).name = idExpression();
			setState(182);
			((ChecksumFieldContext)_localctx).checksumExpression = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstFieldContext extends ParserRuleContext {
		public DataTypeContext type;
		public IdExpressionContext name;
		public ExpressionContext expected;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConstFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterConstField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitConstField(this);
		}
	}

	public final ConstFieldContext constField() throws RecognitionException {
		ConstFieldContext _localctx = new ConstFieldContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_constField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(T__7);
			setState(185);
			((ConstFieldContext)_localctx).type = dataType();
			setState(186);
			((ConstFieldContext)_localctx).name = idExpression();
			setState(187);
			((ConstFieldContext)_localctx).expected = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DiscriminatorFieldContext extends ParserRuleContext {
		public TypeReferenceContext type;
		public IdExpressionContext name;
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public DiscriminatorFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_discriminatorField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterDiscriminatorField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitDiscriminatorField(this);
		}
	}

	public final DiscriminatorFieldContext discriminatorField() throws RecognitionException {
		DiscriminatorFieldContext _localctx = new DiscriminatorFieldContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_discriminatorField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(T__8);
			setState(190);
			((DiscriminatorFieldContext)_localctx).type = typeReference();
			setState(191);
			((DiscriminatorFieldContext)_localctx).name = idExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumFieldContext extends ParserRuleContext {
		public TypeReferenceContext type;
		public IdExpressionContext name;
		public IdExpressionContext fieldName;
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public List<IdExpressionContext> idExpression() {
			return getRuleContexts(IdExpressionContext.class);
		}
		public IdExpressionContext idExpression(int i) {
			return getRuleContext(IdExpressionContext.class,i);
		}
		public EnumFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterEnumField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitEnumField(this);
		}
	}

	public final EnumFieldContext enumField() throws RecognitionException {
		EnumFieldContext _localctx = new EnumFieldContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_enumField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(T__2);
			setState(194);
			((EnumFieldContext)_localctx).type = typeReference();
			setState(195);
			((EnumFieldContext)_localctx).name = idExpression();
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TICK) {
				{
				setState(196);
				((EnumFieldContext)_localctx).fieldName = idExpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImplicitFieldContext extends ParserRuleContext {
		public DataTypeContext type;
		public IdExpressionContext name;
		public ExpressionContext serializeExpression;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ImplicitFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implicitField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterImplicitField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitImplicitField(this);
		}
	}

	public final ImplicitFieldContext implicitField() throws RecognitionException {
		ImplicitFieldContext _localctx = new ImplicitFieldContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_implicitField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(T__9);
			setState(200);
			((ImplicitFieldContext)_localctx).type = dataType();
			setState(201);
			((ImplicitFieldContext)_localctx).name = idExpression();
			setState(202);
			((ImplicitFieldContext)_localctx).serializeExpression = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ManualArrayFieldContext extends ParserRuleContext {
		public TypeReferenceContext type;
		public IdExpressionContext name;
		public Token loopType;
		public ExpressionContext loopExpression;
		public ExpressionContext parseExpression;
		public ExpressionContext serializeExpression;
		public ExpressionContext lengthExpression;
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public TerminalNode ARRAY_LOOP_TYPE() { return getToken(MSpecParser.ARRAY_LOOP_TYPE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ManualArrayFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_manualArrayField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterManualArrayField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitManualArrayField(this);
		}
	}

	public final ManualArrayFieldContext manualArrayField() throws RecognitionException {
		ManualArrayFieldContext _localctx = new ManualArrayFieldContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_manualArrayField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(T__10);
			setState(205);
			((ManualArrayFieldContext)_localctx).type = typeReference();
			setState(206);
			((ManualArrayFieldContext)_localctx).name = idExpression();
			setState(207);
			((ManualArrayFieldContext)_localctx).loopType = match(ARRAY_LOOP_TYPE);
			setState(208);
			((ManualArrayFieldContext)_localctx).loopExpression = expression();
			setState(209);
			((ManualArrayFieldContext)_localctx).parseExpression = expression();
			setState(210);
			((ManualArrayFieldContext)_localctx).serializeExpression = expression();
			setState(211);
			((ManualArrayFieldContext)_localctx).lengthExpression = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ManualFieldContext extends ParserRuleContext {
		public TypeReferenceContext type;
		public IdExpressionContext name;
		public ExpressionContext parseExpression;
		public ExpressionContext serializeExpression;
		public ExpressionContext lengthExpression;
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ManualFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_manualField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterManualField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitManualField(this);
		}
	}

	public final ManualFieldContext manualField() throws RecognitionException {
		ManualFieldContext _localctx = new ManualFieldContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_manualField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(T__11);
			setState(214);
			((ManualFieldContext)_localctx).type = typeReference();
			setState(215);
			((ManualFieldContext)_localctx).name = idExpression();
			setState(216);
			((ManualFieldContext)_localctx).parseExpression = expression();
			setState(217);
			((ManualFieldContext)_localctx).serializeExpression = expression();
			setState(218);
			((ManualFieldContext)_localctx).lengthExpression = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionalFieldContext extends ParserRuleContext {
		public TypeReferenceContext type;
		public IdExpressionContext name;
		public ExpressionContext condition;
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OptionalFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionalField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterOptionalField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitOptionalField(this);
		}
	}

	public final OptionalFieldContext optionalField() throws RecognitionException {
		OptionalFieldContext _localctx = new OptionalFieldContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_optionalField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(T__12);
			setState(221);
			((OptionalFieldContext)_localctx).type = typeReference();
			setState(222);
			((OptionalFieldContext)_localctx).name = idExpression();
			setState(223);
			((OptionalFieldContext)_localctx).condition = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PaddingFieldContext extends ParserRuleContext {
		public DataTypeContext type;
		public IdExpressionContext name;
		public ExpressionContext paddingValue;
		public ExpressionContext paddingCondition;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public PaddingFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paddingField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterPaddingField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitPaddingField(this);
		}
	}

	public final PaddingFieldContext paddingField() throws RecognitionException {
		PaddingFieldContext _localctx = new PaddingFieldContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_paddingField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			match(T__13);
			setState(226);
			((PaddingFieldContext)_localctx).type = dataType();
			setState(227);
			((PaddingFieldContext)_localctx).name = idExpression();
			setState(228);
			((PaddingFieldContext)_localctx).paddingValue = expression();
			setState(229);
			((PaddingFieldContext)_localctx).paddingCondition = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReservedFieldContext extends ParserRuleContext {
		public DataTypeContext type;
		public ExpressionContext expected;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReservedFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reservedField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterReservedField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitReservedField(this);
		}
	}

	public final ReservedFieldContext reservedField() throws RecognitionException {
		ReservedFieldContext _localctx = new ReservedFieldContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_reservedField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(T__14);
			setState(232);
			((ReservedFieldContext)_localctx).type = dataType();
			setState(233);
			((ReservedFieldContext)_localctx).expected = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleFieldContext extends ParserRuleContext {
		public TypeReferenceContext type;
		public IdExpressionContext name;
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public SimpleFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterSimpleField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitSimpleField(this);
		}
	}

	public final SimpleFieldContext simpleField() throws RecognitionException {
		SimpleFieldContext _localctx = new SimpleFieldContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_simpleField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(T__15);
			setState(236);
			((SimpleFieldContext)_localctx).type = typeReference();
			setState(237);
			((SimpleFieldContext)_localctx).name = idExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSwitchFieldContext extends ParserRuleContext {
		public MultipleExpressionsContext discriminators;
		public MultipleExpressionsContext multipleExpressions() {
			return getRuleContext(MultipleExpressionsContext.class,0);
		}
		public List<CaseStatementContext> caseStatement() {
			return getRuleContexts(CaseStatementContext.class);
		}
		public CaseStatementContext caseStatement(int i) {
			return getRuleContext(CaseStatementContext.class,i);
		}
		public TypeSwitchFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSwitchField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterTypeSwitchField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitTypeSwitchField(this);
		}
	}

	public final TypeSwitchFieldContext typeSwitchField() throws RecognitionException {
		TypeSwitchFieldContext _localctx = new TypeSwitchFieldContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_typeSwitchField);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(T__16);
			setState(240);
			((TypeSwitchFieldContext)_localctx).discriminators = multipleExpressions();
			setState(244);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(241);
					caseStatement();
					}
					} 
				}
				setState(246);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnknownFieldContext extends ParserRuleContext {
		public DataTypeContext type;
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public UnknownFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unknownField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterUnknownField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitUnknownField(this);
		}
	}

	public final UnknownFieldContext unknownField() throws RecognitionException {
		UnknownFieldContext _localctx = new UnknownFieldContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_unknownField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(T__17);
			setState(248);
			((UnknownFieldContext)_localctx).type = dataType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VirtualFieldContext extends ParserRuleContext {
		public TypeReferenceContext type;
		public IdExpressionContext name;
		public ExpressionContext valueExpression;
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VirtualFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_virtualField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterVirtualField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitVirtualField(this);
		}
	}

	public final VirtualFieldContext virtualField() throws RecognitionException {
		VirtualFieldContext _localctx = new VirtualFieldContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_virtualField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(T__18);
			setState(251);
			((VirtualFieldContext)_localctx).type = typeReference();
			setState(252);
			((VirtualFieldContext)_localctx).name = idExpression();
			setState(253);
			((VirtualFieldContext)_localctx).valueExpression = expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumValueDefinitionContext extends ParserRuleContext {
		public ExpressionContext valueExpression;
		public Token name;
		public MultipleExpressionsContext constantValueExpressions;
		public List<TerminalNode> LBRACKET() { return getTokens(MSpecParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(MSpecParser.LBRACKET, i);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(MSpecParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(MSpecParser.RBRACKET, i);
		}
		public TerminalNode IDENTIFIER_LITERAL() { return getToken(MSpecParser.IDENTIFIER_LITERAL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MultipleExpressionsContext multipleExpressions() {
			return getRuleContext(MultipleExpressionsContext.class,0);
		}
		public EnumValueDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumValueDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterEnumValueDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitEnumValueDefinition(this);
		}
	}

	public final EnumValueDefinitionContext enumValueDefinition() throws RecognitionException {
		EnumValueDefinitionContext _localctx = new EnumValueDefinitionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_enumValueDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(LBRACKET);
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TICK) {
				{
				setState(256);
				((EnumValueDefinitionContext)_localctx).valueExpression = expression();
				}
			}

			setState(259);
			((EnumValueDefinitionContext)_localctx).name = match(IDENTIFIER_LITERAL);
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACKET) {
				{
				setState(260);
				match(LBRACKET);
				setState(261);
				((EnumValueDefinitionContext)_localctx).constantValueExpressions = multipleExpressions();
				setState(262);
				match(RBRACKET);
				}
			}

			setState(266);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeReferenceContext extends ParserRuleContext {
		public Token complexTypeReference;
		public DataTypeContext simpleTypeReference;
		public TerminalNode IDENTIFIER_LITERAL() { return getToken(MSpecParser.IDENTIFIER_LITERAL, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TypeReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterTypeReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitTypeReference(this);
		}
	}

	public final TypeReferenceContext typeReference() throws RecognitionException {
		TypeReferenceContext _localctx = new TypeReferenceContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_typeReference);
		try {
			setState(270);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				((TypeReferenceContext)_localctx).complexTypeReference = match(IDENTIFIER_LITERAL);
				}
				break;
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__29:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				((TypeReferenceContext)_localctx).simpleTypeReference = dataType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseStatementContext extends ParserRuleContext {
		public MultipleExpressionsContext discriminatorValues;
		public Token name;
		public ArgumentListContext params;
		public List<TerminalNode> LBRACKET() { return getTokens(MSpecParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(MSpecParser.LBRACKET, i);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(MSpecParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(MSpecParser.RBRACKET, i);
		}
		public TerminalNode IDENTIFIER_LITERAL() { return getToken(MSpecParser.IDENTIFIER_LITERAL, 0); }
		public List<FieldDefinitionContext> fieldDefinition() {
			return getRuleContexts(FieldDefinitionContext.class);
		}
		public FieldDefinitionContext fieldDefinition(int i) {
			return getRuleContext(FieldDefinitionContext.class,i);
		}
		public MultipleExpressionsContext multipleExpressions() {
			return getRuleContext(MultipleExpressionsContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public CaseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterCaseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitCaseStatement(this);
		}
	}

	public final CaseStatementContext caseStatement() throws RecognitionException {
		CaseStatementContext _localctx = new CaseStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_caseStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(LBRACKET);
			setState(274);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TICK) {
				{
				setState(273);
				((CaseStatementContext)_localctx).discriminatorValues = multipleExpressions();
				}
			}

			setState(276);
			((CaseStatementContext)_localctx).name = match(IDENTIFIER_LITERAL);
			setState(281);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(277);
				match(LBRACKET);
				setState(278);
				((CaseStatementContext)_localctx).params = argumentList();
				setState(279);
				match(RBRACKET);
				}
				break;
			}
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACKET) {
				{
				{
				setState(283);
				fieldDefinition();
				}
				}
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(289);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataTypeContext extends ParserRuleContext {
		public Token base;
		public Token size;
		public Token exponent;
		public Token mantissa;
		public ExpressionContext length;
		public IdExpressionContext encoding;
		public List<TerminalNode> INTEGER_LITERAL() { return getTokens(MSpecParser.INTEGER_LITERAL); }
		public TerminalNode INTEGER_LITERAL(int i) {
			return getToken(MSpecParser.INTEGER_LITERAL, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitDataType(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_dataType);
		try {
			setState(313);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				((DataTypeContext)_localctx).base = match(T__19);
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				((DataTypeContext)_localctx).base = match(T__20);
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 3);
				{
				setState(293);
				((DataTypeContext)_localctx).base = match(T__21);
				setState(294);
				((DataTypeContext)_localctx).size = match(INTEGER_LITERAL);
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 4);
				{
				setState(295);
				((DataTypeContext)_localctx).base = match(T__22);
				setState(296);
				((DataTypeContext)_localctx).size = match(INTEGER_LITERAL);
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 5);
				{
				setState(297);
				((DataTypeContext)_localctx).base = match(T__23);
				setState(298);
				((DataTypeContext)_localctx).exponent = match(INTEGER_LITERAL);
				setState(299);
				match(T__24);
				setState(300);
				((DataTypeContext)_localctx).mantissa = match(INTEGER_LITERAL);
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 6);
				{
				setState(301);
				((DataTypeContext)_localctx).base = match(T__25);
				setState(302);
				((DataTypeContext)_localctx).exponent = match(INTEGER_LITERAL);
				setState(303);
				match(T__24);
				setState(304);
				((DataTypeContext)_localctx).mantissa = match(INTEGER_LITERAL);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 7);
				{
				setState(305);
				((DataTypeContext)_localctx).base = match(T__26);
				setState(306);
				((DataTypeContext)_localctx).length = expression();
				setState(308);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(307);
					((DataTypeContext)_localctx).encoding = idExpression();
					}
					break;
				}
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 8);
				{
				setState(310);
				((DataTypeContext)_localctx).base = match(T__27);
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 9);
				{
				setState(311);
				((DataTypeContext)_localctx).base = match(T__28);
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 10);
				{
				setState(312);
				((DataTypeContext)_localctx).base = match(T__29);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public TypeReferenceContext type;
		public IdExpressionContext name;
		public TypeReferenceContext typeReference() {
			return getRuleContext(TypeReferenceContext.class,0);
		}
		public IdExpressionContext idExpression() {
			return getRuleContext(IdExpressionContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitArgument(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			((ArgumentContext)_localctx).type = typeReference();
			setState(316);
			((ArgumentContext)_localctx).name = idExpression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentListContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitArgumentList(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			argument();
			setState(323);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				{
				setState(319);
				match(T__30);
				setState(320);
				argument();
				}
				}
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public InnerExpressionContext expr;
		public List<TerminalNode> TICK() { return getTokens(MSpecParser.TICK); }
		public TerminalNode TICK(int i) {
			return getToken(MSpecParser.TICK, i);
		}
		public InnerExpressionContext innerExpression() {
			return getRuleContext(InnerExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(TICK);
			setState(327);
			((ExpressionContext)_localctx).expr = innerExpression(0);
			setState(328);
			match(TICK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultipleExpressionsContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MultipleExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleExpressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterMultipleExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitMultipleExpressions(this);
		}
	}

	public final MultipleExpressionsContext multipleExpressions() throws RecognitionException {
		MultipleExpressionsContext _localctx = new MultipleExpressionsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_multipleExpressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			expression();
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				{
				setState(331);
				match(T__30);
				setState(332);
				expression();
				}
				}
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InnerExpressionContext extends ParserRuleContext {
		public TerminalNode BOOLEAN_LITERAL() { return getToken(MSpecParser.BOOLEAN_LITERAL, 0); }
		public TerminalNode ARRAY_LOOP_TYPE() { return getToken(MSpecParser.ARRAY_LOOP_TYPE, 0); }
		public TerminalNode IDENTIFIER_LITERAL() { return getToken(MSpecParser.IDENTIFIER_LITERAL, 0); }
		public List<TerminalNode> LBRACKET() { return getTokens(MSpecParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(MSpecParser.LBRACKET, i);
		}
		public List<InnerExpressionContext> innerExpression() {
			return getRuleContexts(InnerExpressionContext.class);
		}
		public InnerExpressionContext innerExpression(int i) {
			return getRuleContext(InnerExpressionContext.class,i);
		}
		public TerminalNode RBRACKET() { return getToken(MSpecParser.RBRACKET, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(MSpecParser.HEX_LITERAL, 0); }
		public List<TerminalNode> INTEGER_LITERAL() { return getTokens(MSpecParser.INTEGER_LITERAL); }
		public TerminalNode INTEGER_LITERAL(int i) {
			return getToken(MSpecParser.INTEGER_LITERAL, i);
		}
		public TerminalNode STRING_LITERAL() { return getToken(MSpecParser.STRING_LITERAL, 0); }
		public TerminalNode BinaryOperator() { return getToken(MSpecParser.BinaryOperator, 0); }
		public InnerExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterInnerExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitInnerExpression(this);
		}
	}

	public final InnerExpressionContext innerExpression() throws RecognitionException {
		return innerExpression(0);
	}

	private InnerExpressionContext innerExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		InnerExpressionContext _localctx = new InnerExpressionContext(_ctx, _parentState);
		InnerExpressionContext _prevctx = _localctx;
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_innerExpression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN_LITERAL:
				{
				setState(339);
				match(BOOLEAN_LITERAL);
				}
				break;
			case ARRAY_LOOP_TYPE:
				{
				setState(340);
				match(ARRAY_LOOP_TYPE);
				}
				break;
			case IDENTIFIER_LITERAL:
				{
				setState(341);
				match(IDENTIFIER_LITERAL);
				setState(354);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(342);
					match(T__31);
					setState(351);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__35) | (1L << T__36) | (1L << ARRAY_LOOP_TYPE) | (1L << INTEGER_LITERAL) | (1L << HEX_LITERAL) | (1L << BOOLEAN_LITERAL) | (1L << STRING_LITERAL) | (1L << IDENTIFIER_LITERAL))) != 0)) {
						{
						setState(343);
						innerExpression(0);
						setState(348);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__30) {
							{
							{
							setState(344);
							match(T__30);
							setState(345);
							innerExpression(0);
							}
							}
							setState(350);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(353);
					match(T__32);
					}
					break;
				}
				setState(360);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(356);
					match(LBRACKET);
					setState(357);
					innerExpression(0);
					setState(358);
					match(RBRACKET);
					}
					break;
				}
				}
				break;
			case T__31:
				{
				setState(362);
				match(T__31);
				setState(363);
				innerExpression(0);
				setState(364);
				match(T__32);
				}
				break;
			case T__35:
				{
				setState(366);
				match(T__35);
				setState(367);
				innerExpression(0);
				setState(368);
				match(T__35);
				}
				break;
			case T__36:
				{
				setState(370);
				match(T__36);
				setState(371);
				innerExpression(4);
				}
				break;
			case HEX_LITERAL:
				{
				setState(372);
				match(HEX_LITERAL);
				}
				break;
			case INTEGER_LITERAL:
				{
				setState(373);
				match(INTEGER_LITERAL);
				}
				break;
			case STRING_LITERAL:
				{
				setState(374);
				match(STRING_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(403);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(401);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
					case 1:
						{
						_localctx = new InnerExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_innerExpression);
						setState(377);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(378);
						match(T__24);
						setState(379);
						innerExpression(11);
						}
						break;
					case 2:
						{
						_localctx = new InnerExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_innerExpression);
						setState(380);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(381);
						match(BinaryOperator);
						setState(382);
						innerExpression(9);
						}
						break;
					case 3:
						{
						_localctx = new InnerExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_innerExpression);
						setState(383);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(384);
						match(T__33);
						setState(385);
						innerExpression(0);
						setState(386);
						match(T__34);
						setState(387);
						innerExpression(8);
						}
						break;
					case 4:
						{
						_localctx = new InnerExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_innerExpression);
						setState(389);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(391); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(390);
							match(LBRACKET);
							}
							}
							setState(393); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==LBRACKET );
						setState(396); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(395);
							match(INTEGER_LITERAL);
							}
							}
							setState(398); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==INTEGER_LITERAL );
						setState(400);
						match(RBRACKET);
						}
						break;
					}
					} 
				}
				setState(405);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IdExpressionContext extends ParserRuleContext {
		public Token id;
		public List<TerminalNode> TICK() { return getTokens(MSpecParser.TICK); }
		public TerminalNode TICK(int i) {
			return getToken(MSpecParser.TICK, i);
		}
		public TerminalNode IDENTIFIER_LITERAL() { return getToken(MSpecParser.IDENTIFIER_LITERAL, 0); }
		public TerminalNode ARRAY_LOOP_TYPE() { return getToken(MSpecParser.ARRAY_LOOP_TYPE, 0); }
		public IdExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).enterIdExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MSpecListener ) ((MSpecListener)listener).exitIdExpression(this);
		}
	}

	public final IdExpressionContext idExpression() throws RecognitionException {
		IdExpressionContext _localctx = new IdExpressionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_idExpression);
		try {
			setState(412);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(406);
				match(TICK);
				setState(407);
				((IdExpressionContext)_localctx).id = match(IDENTIFIER_LITERAL);
				setState(408);
				match(TICK);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(409);
				match(TICK);
				setState(410);
				((IdExpressionContext)_localctx).id = match(ARRAY_LOOP_TYPE);
				setState(411);
				match(TICK);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 30:
			return innerExpression_sempred((InnerExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean innerExpression_sempred(InnerExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 10);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\66\u01a1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\7\2D\n\2\f\2\16\2G\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\5\4U\n\4\3\4\7\4X\n\4\f\4\16\4[\13\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\5\4c\n\4\3\4\6\4f\n\4\r\4\16\4g\3\4\3\4\5\4l\n\4\3\4\3\4\3\4\3\4\3"+
		"\4\5\4s\n\4\3\4\6\4v\n\4\r\4\16\4w\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0080\n"+
		"\4\3\4\3\4\5\4\u0084\n\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u008c\n\5\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0096\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00aa\n\7\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u00c8\n\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\7\25\u00f5\n\25\f\25"+
		"\16\25\u00f8\13\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\5"+
		"\30\u0104\n\30\3\30\3\30\3\30\3\30\3\30\5\30\u010b\n\30\3\30\3\30\3\31"+
		"\3\31\5\31\u0111\n\31\3\32\3\32\5\32\u0115\n\32\3\32\3\32\3\32\3\32\3"+
		"\32\5\32\u011c\n\32\3\32\7\32\u011f\n\32\f\32\16\32\u0122\13\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\5\33\u0137\n\33\3\33\3\33\3\33\5\33\u013c\n\33\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\7\35\u0144\n\35\f\35\16\35\u0147\13\35\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\7\37\u0150\n\37\f\37\16\37\u0153\13\37"+
		"\3 \3 \3 \3 \3 \3 \3 \3 \7 \u015d\n \f \16 \u0160\13 \5 \u0162\n \3 \5"+
		" \u0165\n \3 \3 \3 \3 \5 \u016b\n \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3 \5 \u017a\n \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \6 \u018a\n"+
		" \r \16 \u018b\3 \6 \u018f\n \r \16 \u0190\3 \7 \u0194\n \f \16 \u0197"+
		"\13 \3!\3!\3!\3!\3!\3!\5!\u019f\n!\3!\2\3>\"\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\2\2\u01c4\2E\3\2\2\2\4J\3\2\2"+
		"\2\6\u0083\3\2\2\2\b\u0085\3\2\2\2\n\u008f\3\2\2\2\f\u00a9\3\2\2\2\16"+
		"\u00ab\3\2\2\2\20\u00af\3\2\2\2\22\u00b5\3\2\2\2\24\u00ba\3\2\2\2\26\u00bf"+
		"\3\2\2\2\30\u00c3\3\2\2\2\32\u00c9\3\2\2\2\34\u00ce\3\2\2\2\36\u00d7\3"+
		"\2\2\2 \u00de\3\2\2\2\"\u00e3\3\2\2\2$\u00e9\3\2\2\2&\u00ed\3\2\2\2(\u00f1"+
		"\3\2\2\2*\u00f9\3\2\2\2,\u00fc\3\2\2\2.\u0101\3\2\2\2\60\u0110\3\2\2\2"+
		"\62\u0112\3\2\2\2\64\u013b\3\2\2\2\66\u013d\3\2\2\28\u0140\3\2\2\2:\u0148"+
		"\3\2\2\2<\u014c\3\2\2\2>\u0179\3\2\2\2@\u019e\3\2\2\2BD\5\4\3\2CB\3\2"+
		"\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2GE\3\2\2\2HI\7\2\2\3I\3\3"+
		"\2\2\2JK\7)\2\2KL\5\6\4\2LM\7*\2\2M\5\3\2\2\2NO\7\3\2\2OT\5@!\2PQ\7)\2"+
		"\2QR\58\35\2RS\7*\2\2SU\3\2\2\2TP\3\2\2\2TU\3\2\2\2UY\3\2\2\2VX\5\b\5"+
		"\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\u0084\3\2\2\2[Y\3\2\2\2\\"+
		"]\7\4\2\2]b\5@!\2^_\7)\2\2_`\58\35\2`a\7*\2\2ac\3\2\2\2b^\3\2\2\2bc\3"+
		"\2\2\2ce\3\2\2\2df\5\b\5\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\u0084"+
		"\3\2\2\2ik\7\5\2\2jl\5\60\31\2kj\3\2\2\2kl\3\2\2\2lm\3\2\2\2mr\5@!\2n"+
		"o\7)\2\2op\58\35\2pq\7*\2\2qs\3\2\2\2rn\3\2\2\2rs\3\2\2\2su\3\2\2\2tv"+
		"\5.\30\2ut\3\2\2\2vw\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\u0084\3\2\2\2yz\7\6"+
		"\2\2z\177\5@!\2{|\7)\2\2|}\58\35\2}~\7*\2\2~\u0080\3\2\2\2\177{\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\5\n\6\2\u0082\u0084"+
		"\3\2\2\2\u0083N\3\2\2\2\u0083\\\3\2\2\2\u0083i\3\2\2\2\u0083y\3\2\2\2"+
		"\u0084\7\3\2\2\2\u0085\u0086\7)\2\2\u0086\u008b\5\f\7\2\u0087\u0088\7"+
		")\2\2\u0088\u0089\5<\37\2\u0089\u008a\7*\2\2\u008a\u008c\3\2\2\2\u008b"+
		"\u0087\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\7*"+
		"\2\2\u008e\t\3\2\2\2\u008f\u0090\7)\2\2\u0090\u0095\5(\25\2\u0091\u0092"+
		"\7)\2\2\u0092\u0093\5<\37\2\u0093\u0094\7*\2\2\u0094\u0096\3\2\2\2\u0095"+
		"\u0091\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\7*"+
		"\2\2\u0098\13\3\2\2\2\u0099\u00aa\5\16\b\2\u009a\u00aa\5\20\t\2\u009b"+
		"\u00aa\5\22\n\2\u009c\u00aa\5\24\13\2\u009d\u00aa\5\26\f\2\u009e\u00aa"+
		"\5\30\r\2\u009f\u00aa\5\32\16\2\u00a0\u00aa\5\34\17\2\u00a1\u00aa\5\36"+
		"\20\2\u00a2\u00aa\5 \21\2\u00a3\u00aa\5\"\22\2\u00a4\u00aa\5$\23\2\u00a5"+
		"\u00aa\5&\24\2\u00a6\u00aa\5(\25\2\u00a7\u00aa\5*\26\2\u00a8\u00aa\5,"+
		"\27\2\u00a9\u0099\3\2\2\2\u00a9\u009a\3\2\2\2\u00a9\u009b\3\2\2\2\u00a9"+
		"\u009c\3\2\2\2\u00a9\u009d\3\2\2\2\u00a9\u009e\3\2\2\2\u00a9\u009f\3\2"+
		"\2\2\u00a9\u00a0\3\2\2\2\u00a9\u00a1\3\2\2\2\u00a9\u00a2\3\2\2\2\u00a9"+
		"\u00a3\3\2\2\2\u00a9\u00a4\3\2\2\2\u00a9\u00a5\3\2\2\2\u00a9\u00a6\3\2"+
		"\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa\r\3\2\2\2\u00ab\u00ac"+
		"\7\7\2\2\u00ac\u00ad\5\60\31\2\u00ad\u00ae\5@!\2\u00ae\17\3\2\2\2\u00af"+
		"\u00b0\7\b\2\2\u00b0\u00b1\5\60\31\2\u00b1\u00b2\5@!\2\u00b2\u00b3\7."+
		"\2\2\u00b3\u00b4\5:\36\2\u00b4\21\3\2\2\2\u00b5\u00b6\7\t\2\2\u00b6\u00b7"+
		"\5\64\33\2\u00b7\u00b8\5@!\2\u00b8\u00b9\5:\36\2\u00b9\23\3\2\2\2\u00ba"+
		"\u00bb\7\n\2\2\u00bb\u00bc\5\64\33\2\u00bc\u00bd\5@!\2\u00bd\u00be\5:"+
		"\36\2\u00be\25\3\2\2\2\u00bf\u00c0\7\13\2\2\u00c0\u00c1\5\60\31\2\u00c1"+
		"\u00c2\5@!\2\u00c2\27\3\2\2\2\u00c3\u00c4\7\5\2\2\u00c4\u00c5\5\60\31"+
		"\2\u00c5\u00c7\5@!\2\u00c6\u00c8\5@!\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8"+
		"\3\2\2\2\u00c8\31\3\2\2\2\u00c9\u00ca\7\f\2\2\u00ca\u00cb\5\64\33\2\u00cb"+
		"\u00cc\5@!\2\u00cc\u00cd\5:\36\2\u00cd\33\3\2\2\2\u00ce\u00cf\7\r\2\2"+
		"\u00cf\u00d0\5\60\31\2\u00d0\u00d1\5@!\2\u00d1\u00d2\7.\2\2\u00d2\u00d3"+
		"\5:\36\2\u00d3\u00d4\5:\36\2\u00d4\u00d5\5:\36\2\u00d5\u00d6\5:\36\2\u00d6"+
		"\35\3\2\2\2\u00d7\u00d8\7\16\2\2\u00d8\u00d9\5\60\31\2\u00d9\u00da\5@"+
		"!\2\u00da\u00db\5:\36\2\u00db\u00dc\5:\36\2\u00dc\u00dd\5:\36\2\u00dd"+
		"\37\3\2\2\2\u00de\u00df\7\17\2\2\u00df\u00e0\5\60\31\2\u00e0\u00e1\5@"+
		"!\2\u00e1\u00e2\5:\36\2\u00e2!\3\2\2\2\u00e3\u00e4\7\20\2\2\u00e4\u00e5"+
		"\5\64\33\2\u00e5\u00e6\5@!\2\u00e6\u00e7\5:\36\2\u00e7\u00e8\5:\36\2\u00e8"+
		"#\3\2\2\2\u00e9\u00ea\7\21\2\2\u00ea\u00eb\5\64\33\2\u00eb\u00ec\5:\36"+
		"\2\u00ec%\3\2\2\2\u00ed\u00ee\7\22\2\2\u00ee\u00ef\5\60\31\2\u00ef\u00f0"+
		"\5@!\2\u00f0\'\3\2\2\2\u00f1\u00f2\7\23\2\2\u00f2\u00f6\5<\37\2\u00f3"+
		"\u00f5\5\62\32\2\u00f4\u00f3\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3"+
		"\2\2\2\u00f6\u00f7\3\2\2\2\u00f7)\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fa"+
		"\7\24\2\2\u00fa\u00fb\5\64\33\2\u00fb+\3\2\2\2\u00fc\u00fd\7\25\2\2\u00fd"+
		"\u00fe\5\60\31\2\u00fe\u00ff\5@!\2\u00ff\u0100\5:\36\2\u0100-\3\2\2\2"+
		"\u0101\u0103\7)\2\2\u0102\u0104\5:\36\2\u0103\u0102\3\2\2\2\u0103\u0104"+
		"\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u010a\7\63\2\2\u0106\u0107\7)\2\2\u0107"+
		"\u0108\5<\37\2\u0108\u0109\7*\2\2\u0109\u010b\3\2\2\2\u010a\u0106\3\2"+
		"\2\2\u010a\u010b\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\7*\2\2\u010d"+
		"/\3\2\2\2\u010e\u0111\7\63\2\2\u010f\u0111\5\64\33\2\u0110\u010e\3\2\2"+
		"\2\u0110\u010f\3\2\2\2\u0111\61\3\2\2\2\u0112\u0114\7)\2\2\u0113\u0115"+
		"\5<\37\2\u0114\u0113\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"\u011b\7\63\2\2\u0117\u0118\7)\2\2\u0118\u0119\58\35\2\u0119\u011a\7*"+
		"\2\2\u011a\u011c\3\2\2\2\u011b\u0117\3\2\2\2\u011b\u011c\3\2\2\2\u011c"+
		"\u0120\3\2\2\2\u011d\u011f\5\b\5\2\u011e\u011d\3\2\2\2\u011f\u0122\3\2"+
		"\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0123\3\2\2\2\u0122"+
		"\u0120\3\2\2\2\u0123\u0124\7*\2\2\u0124\63\3\2\2\2\u0125\u013c\7\26\2"+
		"\2\u0126\u013c\7\27\2\2\u0127\u0128\7\30\2\2\u0128\u013c\7/\2\2\u0129"+
		"\u012a\7\31\2\2\u012a\u013c\7/\2\2\u012b\u012c\7\32\2\2\u012c\u012d\7"+
		"/\2\2\u012d\u012e\7\33\2\2\u012e\u013c\7/\2\2\u012f\u0130\7\34\2\2\u0130"+
		"\u0131\7/\2\2\u0131\u0132\7\33\2\2\u0132\u013c\7/\2\2\u0133\u0134\7\35"+
		"\2\2\u0134\u0136\5:\36\2\u0135\u0137\5@!\2\u0136\u0135\3\2\2\2\u0136\u0137"+
		"\3\2\2\2\u0137\u013c\3\2\2\2\u0138\u013c\7\36\2\2\u0139\u013c\7\37\2\2"+
		"\u013a\u013c\7 \2\2\u013b\u0125\3\2\2\2\u013b\u0126\3\2\2\2\u013b\u0127"+
		"\3\2\2\2\u013b\u0129\3\2\2\2\u013b\u012b\3\2\2\2\u013b\u012f\3\2\2\2\u013b"+
		"\u0133\3\2\2\2\u013b\u0138\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013a\3\2"+
		"\2\2\u013c\65\3\2\2\2\u013d\u013e\5\60\31\2\u013e\u013f\5@!\2\u013f\67"+
		"\3\2\2\2\u0140\u0145\5\66\34\2\u0141\u0142\7!\2\2\u0142\u0144\5\66\34"+
		"\2\u0143\u0141\3\2\2\2\u0144\u0147\3\2\2\2\u0145\u0143\3\2\2\2\u0145\u0146"+
		"\3\2\2\2\u01469\3\2\2\2\u0147\u0145\3\2\2\2\u0148\u0149\7(\2\2\u0149\u014a"+
		"\5> \2\u014a\u014b\7(\2\2\u014b;\3\2\2\2\u014c\u0151\5:\36\2\u014d\u014e"+
		"\7!\2\2\u014e\u0150\5:\36\2\u014f\u014d\3\2\2\2\u0150\u0153\3\2\2\2\u0151"+
		"\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152=\3\2\2\2\u0153\u0151\3\2\2\2"+
		"\u0154\u0155\b \1\2\u0155\u017a\7\61\2\2\u0156\u017a\7.\2\2\u0157\u0164"+
		"\7\63\2\2\u0158\u0161\7\"\2\2\u0159\u015e\5> \2\u015a\u015b\7!\2\2\u015b"+
		"\u015d\5> \2\u015c\u015a\3\2\2\2\u015d\u0160\3\2\2\2\u015e\u015c\3\2\2"+
		"\2\u015e\u015f\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u015e\3\2\2\2\u0161\u0159"+
		"\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0165\7#\2\2\u0164"+
		"\u0158\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u016a\3\2\2\2\u0166\u0167\7)"+
		"\2\2\u0167\u0168\5> \2\u0168\u0169\7*\2\2\u0169\u016b\3\2\2\2\u016a\u0166"+
		"\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u017a\3\2\2\2\u016c\u016d\7\"\2\2\u016d"+
		"\u016e\5> \2\u016e\u016f\7#\2\2\u016f\u017a\3\2\2\2\u0170\u0171\7&\2\2"+
		"\u0171\u0172\5> \2\u0172\u0173\7&\2\2\u0173\u017a\3\2\2\2\u0174\u0175"+
		"\7\'\2\2\u0175\u017a\5> \6\u0176\u017a\7\60\2\2\u0177\u017a\7/\2\2\u0178"+
		"\u017a\7\62\2\2\u0179\u0154\3\2\2\2\u0179\u0156\3\2\2\2\u0179\u0157\3"+
		"\2\2\2\u0179\u016c\3\2\2\2\u0179\u0170\3\2\2\2\u0179\u0174\3\2\2\2\u0179"+
		"\u0176\3\2\2\2\u0179\u0177\3\2\2\2\u0179\u0178\3\2\2\2\u017a\u0195\3\2"+
		"\2\2\u017b\u017c\f\f\2\2\u017c\u017d\7\33\2\2\u017d\u0194\5> \r\u017e"+
		"\u017f\f\n\2\2\u017f\u0180\7-\2\2\u0180\u0194\5> \13\u0181\u0182\f\t\2"+
		"\2\u0182\u0183\7$\2\2\u0183\u0184\5> \2\u0184\u0185\7%\2\2\u0185\u0186"+
		"\5> \n\u0186\u0194\3\2\2\2\u0187\u0189\f\13\2\2\u0188\u018a\7)\2\2\u0189"+
		"\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018c\3\2"+
		"\2\2\u018c\u018e\3\2\2\2\u018d\u018f\7/\2\2\u018e\u018d\3\2\2\2\u018f"+
		"\u0190\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0192\3\2"+
		"\2\2\u0192\u0194\7*\2\2\u0193\u017b\3\2\2\2\u0193\u017e\3\2\2\2\u0193"+
		"\u0181\3\2\2\2\u0193\u0187\3\2\2\2\u0194\u0197\3\2\2\2\u0195\u0193\3\2"+
		"\2\2\u0195\u0196\3\2\2\2\u0196?\3\2\2\2\u0197\u0195\3\2\2\2\u0198\u0199"+
		"\7(\2\2\u0199\u019a\7\63\2\2\u019a\u019f\7(\2\2\u019b\u019c\7(\2\2\u019c"+
		"\u019d\7.\2\2\u019d\u019f\7(\2\2\u019e\u0198\3\2\2\2\u019e\u019b\3\2\2"+
		"\2\u019fA\3\2\2\2%ETYbgkrw\177\u0083\u008b\u0095\u00a9\u00c7\u00f6\u0103"+
		"\u010a\u0110\u0114\u011b\u0120\u0136\u013b\u0145\u0151\u015e\u0161\u0164"+
		"\u016a\u0179\u018b\u0190\u0193\u0195\u019e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}