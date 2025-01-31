// Generated from org/apache/plc4x/plugins/codegenerator/language/mspec/expression/Expression.g4 by ANTLR 4.9.2
package org.apache.plc4x.plugins.codegenerator.language.mspec.expression;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, Null=28, Bool=29, Number=30, Identifier=31, 
		String=32, Space=33;
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
			"T__25", "T__26", "Null", "Bool", "Number", "Identifier", "String", "Space", 
			"Int", "Digit"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'!'", "'^'", "'*'", "'/'", "'%'", "'+'", "'>>'", "'<<'", 
			"'>='", "'<='", "'>'", "'<'", "'=='", "'!='", "'&&'", "'&'", "'||'", 
			"'|'", "'?'", "':'", "'('", "')'", "'.'", "','", "'['", "']'", "'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "Null", "Bool", "Number", "Identifier", "String", 
			"Space"
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


	public ExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expression.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u00ce\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r"+
		"\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31"+
		"\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u0096\n\36\3\37\3\37\3\37\7\37\u009b"+
		"\n\37\f\37\16\37\u009e\13\37\5\37\u00a0\n\37\3 \3 \7 \u00a4\n \f \16 "+
		"\u00a7\13 \3!\3!\3!\3!\7!\u00ad\n!\f!\16!\u00b0\13!\3!\3!\3!\3!\3!\7!"+
		"\u00b7\n!\f!\16!\u00ba\13!\3!\5!\u00bd\n!\3\"\3\"\3\"\3\"\3#\3#\7#\u00c5"+
		"\n#\f#\16#\u00c8\13#\3#\5#\u00cb\n#\3$\3$\2\2%\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E\2G\2\3\2\f"+
		"\5\2C\\aac|\6\2\62;C\\aac|\3\2$$\6\2\f\f\17\17$$^^\4\2\f\f\17\17\3\2)"+
		")\6\2\f\f\17\17))^^\5\2\13\f\16\17\"\"\3\2\63;\3\2\62;\2\u00d6\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3I\3\2\2\2\5K\3\2\2\2\7M\3"+
		"\2\2\2\tO\3\2\2\2\13Q\3\2\2\2\rS\3\2\2\2\17U\3\2\2\2\21W\3\2\2\2\23Z\3"+
		"\2\2\2\25]\3\2\2\2\27`\3\2\2\2\31c\3\2\2\2\33e\3\2\2\2\35g\3\2\2\2\37"+
		"j\3\2\2\2!m\3\2\2\2#p\3\2\2\2%r\3\2\2\2\'u\3\2\2\2)w\3\2\2\2+y\3\2\2\2"+
		"-{\3\2\2\2/}\3\2\2\2\61\177\3\2\2\2\63\u0081\3\2\2\2\65\u0083\3\2\2\2"+
		"\67\u0085\3\2\2\29\u0087\3\2\2\2;\u0095\3\2\2\2=\u0097\3\2\2\2?\u00a1"+
		"\3\2\2\2A\u00bc\3\2\2\2C\u00be\3\2\2\2E\u00ca\3\2\2\2G\u00cc\3\2\2\2I"+
		"J\7/\2\2J\4\3\2\2\2KL\7#\2\2L\6\3\2\2\2MN\7`\2\2N\b\3\2\2\2OP\7,\2\2P"+
		"\n\3\2\2\2QR\7\61\2\2R\f\3\2\2\2ST\7\'\2\2T\16\3\2\2\2UV\7-\2\2V\20\3"+
		"\2\2\2WX\7@\2\2XY\7@\2\2Y\22\3\2\2\2Z[\7>\2\2[\\\7>\2\2\\\24\3\2\2\2]"+
		"^\7@\2\2^_\7?\2\2_\26\3\2\2\2`a\7>\2\2ab\7?\2\2b\30\3\2\2\2cd\7@\2\2d"+
		"\32\3\2\2\2ef\7>\2\2f\34\3\2\2\2gh\7?\2\2hi\7?\2\2i\36\3\2\2\2jk\7#\2"+
		"\2kl\7?\2\2l \3\2\2\2mn\7(\2\2no\7(\2\2o\"\3\2\2\2pq\7(\2\2q$\3\2\2\2"+
		"rs\7~\2\2st\7~\2\2t&\3\2\2\2uv\7~\2\2v(\3\2\2\2wx\7A\2\2x*\3\2\2\2yz\7"+
		"<\2\2z,\3\2\2\2{|\7*\2\2|.\3\2\2\2}~\7+\2\2~\60\3\2\2\2\177\u0080\7\60"+
		"\2\2\u0080\62\3\2\2\2\u0081\u0082\7.\2\2\u0082\64\3\2\2\2\u0083\u0084"+
		"\7]\2\2\u0084\66\3\2\2\2\u0085\u0086\7_\2\2\u00868\3\2\2\2\u0087\u0088"+
		"\7p\2\2\u0088\u0089\7w\2\2\u0089\u008a\7n\2\2\u008a\u008b\7n\2\2\u008b"+
		":\3\2\2\2\u008c\u008d\7v\2\2\u008d\u008e\7t\2\2\u008e\u008f\7w\2\2\u008f"+
		"\u0096\7g\2\2\u0090\u0091\7h\2\2\u0091\u0092\7c\2\2\u0092\u0093\7n\2\2"+
		"\u0093\u0094\7u\2\2\u0094\u0096\7g\2\2\u0095\u008c\3\2\2\2\u0095\u0090"+
		"\3\2\2\2\u0096<\3\2\2\2\u0097\u009f\5E#\2\u0098\u009c\7\60\2\2\u0099\u009b"+
		"\5G$\2\u009a\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u0098\3\2"+
		"\2\2\u009f\u00a0\3\2\2\2\u00a0>\3\2\2\2\u00a1\u00a5\t\2\2\2\u00a2\u00a4"+
		"\t\3\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6@\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00ae\t\4\2\2"+
		"\u00a9\u00ad\n\5\2\2\u00aa\u00ab\7^\2\2\u00ab\u00ad\n\6\2\2\u00ac\u00a9"+
		"\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00bd\t\4"+
		"\2\2\u00b2\u00b8\t\7\2\2\u00b3\u00b7\n\b\2\2\u00b4\u00b5\7^\2\2\u00b5"+
		"\u00b7\n\6\2\2\u00b6\u00b3\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00ba\3\2"+
		"\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00bd\t\7\2\2\u00bc\u00a8\3\2\2\2\u00bc\u00b2\3\2"+
		"\2\2\u00bdB\3\2\2\2\u00be\u00bf\t\t\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1"+
		"\b\"\2\2\u00c1D\3\2\2\2\u00c2\u00c6\t\n\2\2\u00c3\u00c5\5G$\2\u00c4\u00c3"+
		"\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\u00cb\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00cb\7\62\2\2\u00ca\u00c2\3"+
		"\2\2\2\u00ca\u00c9\3\2\2\2\u00cbF\3\2\2\2\u00cc\u00cd\t\13\2\2\u00cdH"+
		"\3\2\2\2\16\2\u0095\u009c\u009f\u00a5\u00ac\u00ae\u00b6\u00b8\u00bc\u00c6"+
		"\u00ca\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}