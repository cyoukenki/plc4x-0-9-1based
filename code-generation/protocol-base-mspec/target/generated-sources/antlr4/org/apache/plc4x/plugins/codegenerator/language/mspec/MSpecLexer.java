// Generated from org\apache\plc4x\plugins\codegenerator\language\mspec\MSpec.g4 by ANTLR 4.9.2
package org.apache.plc4x.plugins.codegenerator.language.mspec;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MSpecLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "T__35", "T__36", "TICK", "LBRACKET", "RBRACKET", "LCBRACKET", 
			"RCBRACKET", "BinaryOperator", "ARRAY_LOOP_TYPE", "INTEGER_LITERAL", 
			"INTEGER_CHARACTERS", "INTEGER_CHARACTER", "HEX_LITERAL", "HEX_CHARACTERS", 
			"HEX_CHARACTER", "BOOLEAN_LITERAL", "STRING_LITERAL", "IDENTIFIER_LITERAL", 
			"STRING_CHARACTERS", "STRING_CHARACTER", "LINE_COMMENT", "BLOCK_COMMENT", 
			"WS"
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


	public MSpecLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MSpec.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\66\u01ee\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$"+
		"\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3,\3,\3,\3,\3,\3"+
		",\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\5,\u0185\n,\3-\3-\3-\3-\3-\3-\3-\3"+
		"-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u019c\n-\3.\3.\3/\6/\u01a1"+
		"\n/\r/\16/\u01a2\3\60\3\60\3\61\3\61\3\61\3\61\3\62\6\62\u01ac\n\62\r"+
		"\62\16\62\u01ad\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\5\64\u01bb\n\64\3\65\3\65\5\65\u01bf\n\65\3\65\3\65\3\66\6\66\u01c4\n"+
		"\66\r\66\16\66\u01c5\3\67\6\67\u01c9\n\67\r\67\16\67\u01ca\38\38\39\3"+
		"9\39\39\79\u01d3\n9\f9\169\u01d6\139\39\39\3:\3:\3:\3:\7:\u01de\n:\f:"+
		"\16:\u01e1\13:\3:\3:\3:\3:\3:\3;\6;\u01e9\n;\r;\16;\u01ea\3;\3;\3\u01df"+
		"\2<\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\2_\2a\60c\2e\2g\61i\62k\63m\2"+
		"o\2q\64s\65u\66\3\2\f\6\2,-//\61\61``\4\2>>@@\4\2\'(~~\3\2\62;\4\2ZZz"+
		"z\5\2\62;CHch\7\2//\62;C\\aac|\6\2\f\f\17\17$$^^\4\2\f\f\17\17\5\2\13"+
		"\f\16\17\"\"\2\u01fc\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q"+
		"\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2a\3\2"+
		"\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2"+
		"\3w\3\2\2\2\5|\3\2\2\2\7\u008e\3\2\2\2\t\u0093\3\2\2\2\13\u009a\3\2\2"+
		"\2\r\u00a3\3\2\2\2\17\u00a9\3\2\2\2\21\u00b2\3\2\2\2\23\u00b8\3\2\2\2"+
		"\25\u00c6\3\2\2\2\27\u00cf\3\2\2\2\31\u00db\3\2\2\2\33\u00e2\3\2\2\2\35"+
		"\u00eb\3\2\2\2\37\u00f3\3\2\2\2!\u00fc\3\2\2\2#\u0103\3\2\2\2%\u010e\3"+
		"\2\2\2\'\u0116\3\2\2\2)\u011e\3\2\2\2+\u0122\3\2\2\2-\u0127\3\2\2\2/\u012b"+
		"\3\2\2\2\61\u0130\3\2\2\2\63\u0136\3\2\2\2\65\u0138\3\2\2\2\67\u013f\3"+
		"\2\2\29\u0146\3\2\2\2;\u014b\3\2\2\2=\u0150\3\2\2\2?\u0159\3\2\2\2A\u015b"+
		"\3\2\2\2C\u015d\3\2\2\2E\u015f\3\2\2\2G\u0161\3\2\2\2I\u0163\3\2\2\2K"+
		"\u0165\3\2\2\2M\u0167\3\2\2\2O\u0169\3\2\2\2Q\u016b\3\2\2\2S\u016d\3\2"+
		"\2\2U\u016f\3\2\2\2W\u0184\3\2\2\2Y\u019b\3\2\2\2[\u019d\3\2\2\2]\u01a0"+
		"\3\2\2\2_\u01a4\3\2\2\2a\u01a6\3\2\2\2c\u01ab\3\2\2\2e\u01af\3\2\2\2g"+
		"\u01ba\3\2\2\2i\u01bc\3\2\2\2k\u01c3\3\2\2\2m\u01c8\3\2\2\2o\u01cc\3\2"+
		"\2\2q\u01ce\3\2\2\2s\u01d9\3\2\2\2u\u01e8\3\2\2\2wx\7v\2\2xy\7{\2\2yz"+
		"\7r\2\2z{\7g\2\2{\4\3\2\2\2|}\7f\2\2}~\7k\2\2~\177\7u\2\2\177\u0080\7"+
		"e\2\2\u0080\u0081\7t\2\2\u0081\u0082\7k\2\2\u0082\u0083\7o\2\2\u0083\u0084"+
		"\7k\2\2\u0084\u0085\7p\2\2\u0085\u0086\7c\2\2\u0086\u0087\7v\2\2\u0087"+
		"\u0088\7g\2\2\u0088\u0089\7f\2\2\u0089\u008a\7V\2\2\u008a\u008b\7{\2\2"+
		"\u008b\u008c\7r\2\2\u008c\u008d\7g\2\2\u008d\6\3\2\2\2\u008e\u008f\7g"+
		"\2\2\u008f\u0090\7p\2\2\u0090\u0091\7w\2\2\u0091\u0092\7o\2\2\u0092\b"+
		"\3\2\2\2\u0093\u0094\7f\2\2\u0094\u0095\7c\2\2\u0095\u0096\7v\2\2\u0096"+
		"\u0097\7c\2\2\u0097\u0098\7K\2\2\u0098\u0099\7q\2\2\u0099\n\3\2\2\2\u009a"+
		"\u009b\7c\2\2\u009b\u009c\7d\2\2\u009c\u009d\7u\2\2\u009d\u009e\7v\2\2"+
		"\u009e\u009f\7t\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7e\2\2\u00a1\u00a2"+
		"\7v\2\2\u00a2\f\3\2\2\2\u00a3\u00a4\7c\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6"+
		"\7t\2\2\u00a6\u00a7\7c\2\2\u00a7\u00a8\7{\2\2\u00a8\16\3\2\2\2\u00a9\u00aa"+
		"\7e\2\2\u00aa\u00ab\7j\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7e\2\2\u00ad"+
		"\u00ae\7m\2\2\u00ae\u00af\7u\2\2\u00af\u00b0\7w\2\2\u00b0\u00b1\7o\2\2"+
		"\u00b1\20\3\2\2\2\u00b2\u00b3\7e\2\2\u00b3\u00b4\7q\2\2\u00b4\u00b5\7"+
		"p\2\2\u00b5\u00b6\7u\2\2\u00b6\u00b7\7v\2\2\u00b7\22\3\2\2\2\u00b8\u00b9"+
		"\7f\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7u\2\2\u00bb\u00bc\7e\2\2\u00bc"+
		"\u00bd\7t\2\2\u00bd\u00be\7k\2\2\u00be\u00bf\7o\2\2\u00bf\u00c0\7k\2\2"+
		"\u00c0\u00c1\7p\2\2\u00c1\u00c2\7c\2\2\u00c2\u00c3\7v\2\2\u00c3\u00c4"+
		"\7q\2\2\u00c4\u00c5\7t\2\2\u00c5\24\3\2\2\2\u00c6\u00c7\7k\2\2\u00c7\u00c8"+
		"\7o\2\2\u00c8\u00c9\7r\2\2\u00c9\u00ca\7n\2\2\u00ca\u00cb\7k\2\2\u00cb"+
		"\u00cc\7e\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce\7v\2\2\u00ce\26\3\2\2\2\u00cf"+
		"\u00d0\7o\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7p\2\2\u00d2\u00d3\7w\2\2"+
		"\u00d3\u00d4\7c\2\2\u00d4\u00d5\7n\2\2\u00d5\u00d6\7C\2\2\u00d6\u00d7"+
		"\7t\2\2\u00d7\u00d8\7t\2\2\u00d8\u00d9\7c\2\2\u00d9\u00da\7{\2\2\u00da"+
		"\30\3\2\2\2\u00db\u00dc\7o\2\2\u00dc\u00dd\7c\2\2\u00dd\u00de\7p\2\2\u00de"+
		"\u00df\7w\2\2\u00df\u00e0\7c\2\2\u00e0\u00e1\7n\2\2\u00e1\32\3\2\2\2\u00e2"+
		"\u00e3\7q\2\2\u00e3\u00e4\7r\2\2\u00e4\u00e5\7v\2\2\u00e5\u00e6\7k\2\2"+
		"\u00e6\u00e7\7q\2\2\u00e7\u00e8\7p\2\2\u00e8\u00e9\7c\2\2\u00e9\u00ea"+
		"\7n\2\2\u00ea\34\3\2\2\2\u00eb\u00ec\7r\2\2\u00ec\u00ed\7c\2\2\u00ed\u00ee"+
		"\7f\2\2\u00ee\u00ef\7f\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7p\2\2\u00f1"+
		"\u00f2\7i\2\2\u00f2\36\3\2\2\2\u00f3\u00f4\7t\2\2\u00f4\u00f5\7g\2\2\u00f5"+
		"\u00f6\7u\2\2\u00f6\u00f7\7g\2\2\u00f7\u00f8\7t\2\2\u00f8\u00f9\7x\2\2"+
		"\u00f9\u00fa\7g\2\2\u00fa\u00fb\7f\2\2\u00fb \3\2\2\2\u00fc\u00fd\7u\2"+
		"\2\u00fd\u00fe\7k\2\2\u00fe\u00ff\7o\2\2\u00ff\u0100\7r\2\2\u0100\u0101"+
		"\7n\2\2\u0101\u0102\7g\2\2\u0102\"\3\2\2\2\u0103\u0104\7v\2\2\u0104\u0105"+
		"\7{\2\2\u0105\u0106\7r\2\2\u0106\u0107\7g\2\2\u0107\u0108\7U\2\2\u0108"+
		"\u0109\7y\2\2\u0109\u010a\7k\2\2\u010a\u010b\7v\2\2\u010b\u010c\7e\2\2"+
		"\u010c\u010d\7j\2\2\u010d$\3\2\2\2\u010e\u010f\7w\2\2\u010f\u0110\7p\2"+
		"\2\u0110\u0111\7m\2\2\u0111\u0112\7p\2\2\u0112\u0113\7q\2\2\u0113\u0114"+
		"\7y\2\2\u0114\u0115\7p\2\2\u0115&\3\2\2\2\u0116\u0117\7x\2\2\u0117\u0118"+
		"\7k\2\2\u0118\u0119\7t\2\2\u0119\u011a\7v\2\2\u011a\u011b\7w\2\2\u011b"+
		"\u011c\7c\2\2\u011c\u011d\7n\2\2\u011d(\3\2\2\2\u011e\u011f\7d\2\2\u011f"+
		"\u0120\7k\2\2\u0120\u0121\7v\2\2\u0121*\3\2\2\2\u0122\u0123\7d\2\2\u0123"+
		"\u0124\7{\2\2\u0124\u0125\7v\2\2\u0125\u0126\7g\2\2\u0126,\3\2\2\2\u0127"+
		"\u0128\7k\2\2\u0128\u0129\7p\2\2\u0129\u012a\7v\2\2\u012a.\3\2\2\2\u012b"+
		"\u012c\7w\2\2\u012c\u012d\7k\2\2\u012d\u012e\7p\2\2\u012e\u012f\7v\2\2"+
		"\u012f\60\3\2\2\2\u0130\u0131\7h\2\2\u0131\u0132\7n\2\2\u0132\u0133\7"+
		"q\2\2\u0133\u0134\7c\2\2\u0134\u0135\7v\2\2\u0135\62\3\2\2\2\u0136\u0137"+
		"\7\60\2\2\u0137\64\3\2\2\2\u0138\u0139\7w\2\2\u0139\u013a\7h\2\2\u013a"+
		"\u013b\7n\2\2\u013b\u013c\7q\2\2\u013c\u013d\7c\2\2\u013d\u013e\7v\2\2"+
		"\u013e\66\3\2\2\2\u013f\u0140\7u\2\2\u0140\u0141\7v\2\2\u0141\u0142\7"+
		"t\2\2\u0142\u0143\7k\2\2\u0143\u0144\7p\2\2\u0144\u0145\7i\2\2\u01458"+
		"\3\2\2\2\u0146\u0147\7v\2\2\u0147\u0148\7k\2\2\u0148\u0149\7o\2\2\u0149"+
		"\u014a\7g\2\2\u014a:\3\2\2\2\u014b\u014c\7f\2\2\u014c\u014d\7c\2\2\u014d"+
		"\u014e\7v\2\2\u014e\u014f\7g\2\2\u014f<\3\2\2\2\u0150\u0151\7f\2\2\u0151"+
		"\u0152\7c\2\2\u0152\u0153\7v\2\2\u0153\u0154\7g\2\2\u0154\u0155\7V\2\2"+
		"\u0155\u0156\7k\2\2\u0156\u0157\7o\2\2\u0157\u0158\7g\2\2\u0158>\3\2\2"+
		"\2\u0159\u015a\7.\2\2\u015a@\3\2\2\2\u015b\u015c\7*\2\2\u015cB\3\2\2\2"+
		"\u015d\u015e\7+\2\2\u015eD\3\2\2\2\u015f\u0160\7A\2\2\u0160F\3\2\2\2\u0161"+
		"\u0162\7<\2\2\u0162H\3\2\2\2\u0163\u0164\7$\2\2\u0164J\3\2\2\2\u0165\u0166"+
		"\7#\2\2\u0166L\3\2\2\2\u0167\u0168\7)\2\2\u0168N\3\2\2\2\u0169\u016a\7"+
		"]\2\2\u016aP\3\2\2\2\u016b\u016c\7_\2\2\u016cR\3\2\2\2\u016d\u016e\7}"+
		"\2\2\u016eT\3\2\2\2\u016f\u0170\7\177\2\2\u0170V\3\2\2\2\u0171\u0185\t"+
		"\2\2\2\u0172\u0173\7?\2\2\u0173\u0185\7?\2\2\u0174\u0175\7#\2\2\u0175"+
		"\u0185\7?\2\2\u0176\u0177\7@\2\2\u0177\u0185\7@\2\2\u0178\u0179\7>\2\2"+
		"\u0179\u0185\7>\2\2\u017a\u0185\t\3\2\2\u017b\u017c\7@\2\2\u017c\u0185"+
		"\7?\2\2\u017d\u017e\7>\2\2\u017e\u0185\7?\2\2\u017f\u0180\7(\2\2\u0180"+
		"\u0185\7(\2\2\u0181\u0182\7~\2\2\u0182\u0185\7~\2\2\u0183\u0185\t\4\2"+
		"\2\u0184\u0171\3\2\2\2\u0184\u0172\3\2\2\2\u0184\u0174\3\2\2\2\u0184\u0176"+
		"\3\2\2\2\u0184\u0178\3\2\2\2\u0184\u017a\3\2\2\2\u0184\u017b\3\2\2\2\u0184"+
		"\u017d\3\2\2\2\u0184\u017f\3\2\2\2\u0184\u0181\3\2\2\2\u0184\u0183\3\2"+
		"\2\2\u0185X\3\2\2\2\u0186\u0187\7e\2\2\u0187\u0188\7q\2\2\u0188\u0189"+
		"\7w\2\2\u0189\u018a\7p\2\2\u018a\u019c\7v\2\2\u018b\u018c\7n\2\2\u018c"+
		"\u018d\7g\2\2\u018d\u018e\7p\2\2\u018e\u018f\7i\2\2\u018f\u0190\7v\2\2"+
		"\u0190\u019c\7j\2\2\u0191\u0192\7v\2\2\u0192\u0193\7g\2\2\u0193\u0194"+
		"\7t\2\2\u0194\u0195\7o\2\2\u0195\u0196\7k\2\2\u0196\u0197\7p\2\2\u0197"+
		"\u0198\7c\2\2\u0198\u0199\7v\2\2\u0199\u019a\7g\2\2\u019a\u019c\7f\2\2"+
		"\u019b\u0186\3\2\2\2\u019b\u018b\3\2\2\2\u019b\u0191\3\2\2\2\u019cZ\3"+
		"\2\2\2\u019d\u019e\5]/\2\u019e\\\3\2\2\2\u019f\u01a1\5_\60\2\u01a0\u019f"+
		"\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3"+
		"^\3\2\2\2\u01a4\u01a5\t\5\2\2\u01a5`\3\2\2\2\u01a6\u01a7\7\62\2\2\u01a7"+
		"\u01a8\t\6\2\2\u01a8\u01a9\5c\62\2\u01a9b\3\2\2\2\u01aa\u01ac\5e\63\2"+
		"\u01ab\u01aa\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ad\u01ae"+
		"\3\2\2\2\u01aed\3\2\2\2\u01af\u01b0\t\7\2\2\u01b0f\3\2\2\2\u01b1\u01b2"+
		"\7v\2\2\u01b2\u01b3\7t\2\2\u01b3\u01b4\7w\2\2\u01b4\u01bb\7g\2\2\u01b5"+
		"\u01b6\7h\2\2\u01b6\u01b7\7c\2\2\u01b7\u01b8\7n\2\2\u01b8\u01b9\7u\2\2"+
		"\u01b9\u01bb\7g\2\2\u01ba\u01b1\3\2\2\2\u01ba\u01b5\3\2\2\2\u01bbh\3\2"+
		"\2\2\u01bc\u01be\7$\2\2\u01bd\u01bf\5m\67\2\u01be\u01bd\3\2\2\2\u01be"+
		"\u01bf\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0\u01c1\7$\2\2\u01c1j\3\2\2\2\u01c2"+
		"\u01c4\t\b\2\2\u01c3\u01c2\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c3\3\2"+
		"\2\2\u01c5\u01c6\3\2\2\2\u01c6l\3\2\2\2\u01c7\u01c9\5o8\2\u01c8\u01c7"+
		"\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca\u01c8\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb"+
		"n\3\2\2\2\u01cc\u01cd\n\t\2\2\u01cdp\3\2\2\2\u01ce\u01cf\7\61\2\2\u01cf"+
		"\u01d0\7\61\2\2\u01d0\u01d4\3\2\2\2\u01d1\u01d3\n\n\2\2\u01d2\u01d1\3"+
		"\2\2\2\u01d3\u01d6\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5"+
		"\u01d7\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d7\u01d8\b9\2\2\u01d8r\3\2\2\2\u01d9"+
		"\u01da\7\61\2\2\u01da\u01db\7,\2\2\u01db\u01df\3\2\2\2\u01dc\u01de\13"+
		"\2\2\2\u01dd\u01dc\3\2\2\2\u01de\u01e1\3\2\2\2\u01df\u01e0\3\2\2\2\u01df"+
		"\u01dd\3\2\2\2\u01e0\u01e2\3\2\2\2\u01e1\u01df\3\2\2\2\u01e2\u01e3\7,"+
		"\2\2\u01e3\u01e4\7\61\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01e6\b:\2\2\u01e6"+
		"t\3\2\2\2\u01e7\u01e9\t\13\2\2\u01e8\u01e7\3\2\2\2\u01e9\u01ea\3\2\2\2"+
		"\u01ea\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ed"+
		"\b;\2\2\u01edv\3\2\2\2\16\2\u0184\u019b\u01a2\u01ad\u01ba\u01be\u01c5"+
		"\u01ca\u01d4\u01df\u01ea\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}