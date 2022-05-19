// Generated from /Users/fbartolucci/Documents/Git/assetlan/src/AssetLan.g4 by ANTLR 4.10.1
package Parser;
import ast.AssetLanListener;
import ast.AssetLanVisitor;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AssetLanParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, BOOL=33, ID=34, NUMBER=35, WS=36, LINECOMMENTS=37, BLOCKCOMMENTS=38;
	public static final int
		RULE_init = 0, RULE_program = 1, RULE_field = 2, RULE_asset = 3, RULE_function = 4, 
		RULE_param = 5, RULE_aparam = 6, RULE_statement = 7, RULE_type = 8, RULE_assignment = 9, 
		RULE_move = 10, RULE_print = 11, RULE_transfer = 12, RULE_ret = 13, RULE_ite = 14, 
		RULE_statementseq = 15, RULE_call = 16, RULE_initcall = 17, RULE_exp = 18, 
		RULE_aexp = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"init", "program", "field", "asset", "function", "param", "aparam", "statement", 
			"type", "assignment", "move", "print", "transfer", "ret", "ite", "statementseq", 
			"call", "initcall", "exp", "aexp"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "';'", "'asset'", "'('", "','", "')'", "'['", "']'", "'{'", 
			"'}'", "'int'", "'bool'", "'void'", "'-o'", "'print'", "'transfer'", 
			"'return'", "'if'", "'else'", "'-'", "'!'", "'*'", "'/'", "'+'", "'<'", 
			"'<='", "'>'", "'>='", "'=='", "'!='", "'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "BOOL", "ID", "NUMBER", 
			"WS", "LINECOMMENTS", "BLOCKCOMMENTS"
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
	public String getGrammarFileName() { return "AssetLan.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AssetLanParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class InitContext extends ParserRuleContext {
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener) ((AssetLanListener)listener).enterInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor) return ((AssetLanVisitor<? extends T>)visitor).visitInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_init);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			program();
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

	public static class ProgramContext extends ParserRuleContext {
		public InitcallContext initcall() {
			return getRuleContext(InitcallContext.class,0);
		}
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<AssetContext> asset() {
			return getRuleContexts(AssetContext.class);
		}
		public AssetContext asset(int i) {
			return getRuleContext(AssetContext.class,i);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_program);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(42);
					field();
					}
					} 
				}
				setState(47);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(48);
				asset();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) {
				{
				{
				setState(54);
				function();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			initcall();
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
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(AssetLanParser.ID, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			type();
			setState(63);
			match(ID);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(64);
				match(T__0);
				setState(65);
				exp(0);
				}
			}

			setState(68);
			match(T__1);
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

	public static class AssetContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AssetLanParser.ID, 0); }
		public AssetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterAsset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitAsset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitAsset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssetContext asset() throws RecognitionException {
		AssetContext _localctx = new AssetContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_asset);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(T__2);
			setState(71);
			match(ID);
			setState(72);
			match(T__1);
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

	public static class FunctionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(AssetLanParser.ID, 0); }
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<AparamContext> aparam() {
			return getRuleContexts(AparamContext.class);
		}
		public AparamContext aparam(int i) {
			return getRuleContext(AparamContext.class,i);
		}
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			type();
			setState(75);
			match(ID);
			setState(76);
			match(T__3);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) {
				{
				setState(77);
				param();
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(78);
					match(T__4);
					setState(79);
					param();
					}
					}
					setState(84);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(87);
			match(T__5);
			setState(88);
			match(T__6);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(89);
				aparam();
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(90);
					match(T__4);
					setState(91);
					aparam();
					}
					}
					setState(96);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(99);
			match(T__7);
			setState(100);
			match(T__8);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) {
				{
				{
				setState(101);
				field();
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << ID))) != 0)) {
				{
				{
				setState(107);
				statement();
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(113);
			match(T__9);
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

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(AssetLanParser.ID, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			type();
			setState(116);
			match(ID);
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

	public static class AparamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AssetLanParser.ID, 0); }
		public AparamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aparam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterAparam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitAparam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitAparam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AparamContext aparam() throws RecognitionException {
		AparamContext _localctx = new AparamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_aparam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__2);
			setState(119);
			match(ID);
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

	public static class StatementContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public MoveContext move() {
			return getRuleContext(MoveContext.class,0);
		}
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public TransferContext transfer() {
			return getRuleContext(TransferContext.class,0);
		}
		public RetContext ret() {
			return getRuleContext(RetContext.class,0);
		}
		public IteContext ite() {
			return getRuleContext(IteContext.class,0);
		}
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_statement);
		try {
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				assignment();
				setState(122);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				move();
				setState(125);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(127);
				print();
				setState(128);
				match(T__1);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(130);
				transfer();
				setState(131);
				match(T__1);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(133);
				ret();
				setState(134);
				match(T__1);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(136);
				ite();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(137);
				call();
				setState(138);
				match(T__1);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AssetLanParser.ID, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(ID);
			setState(145);
			match(T__0);
			setState(146);
			exp(0);
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

	public static class MoveContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(AssetLanParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(AssetLanParser.ID, i);
		}
		public MoveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_move; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterMove(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitMove(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitMove(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoveContext move() throws RecognitionException {
		MoveContext _localctx = new MoveContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_move);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(ID);
			setState(149);
			match(T__13);
			setState(150);
			match(ID);
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

	public static class PrintContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(T__14);
			setState(153);
			exp(0);
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

	public static class TransferContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AssetLanParser.ID, 0); }
		public TransferContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transfer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterTransfer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitTransfer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitTransfer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransferContext transfer() throws RecognitionException {
		TransferContext _localctx = new TransferContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_transfer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__15);
			setState(156);
			match(ID);
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

	public static class RetContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public RetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ret; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterRet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitRet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitRet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetContext ret() throws RecognitionException {
		RetContext _localctx = new RetContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ret);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__16);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__19) | (1L << T__20) | (1L << BOOL) | (1L << ID) | (1L << NUMBER))) != 0)) {
				{
				setState(159);
				exp(0);
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

	public static class IteContext extends ParserRuleContext {
		public StatementseqContext thenb;
		public StatementseqContext elseb;
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public List<StatementseqContext> statementseq() {
			return getRuleContexts(StatementseqContext.class);
		}
		public StatementseqContext statementseq(int i) {
			return getRuleContext(StatementseqContext.class,i);
		}
		public IteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterIte(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitIte(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitIte(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IteContext ite() throws RecognitionException {
		IteContext _localctx = new IteContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__17);
			setState(163);
			match(T__3);
			setState(164);
			exp(0);
			setState(165);
			match(T__5);
			setState(166);
			match(T__8);
			setState(167);
			((IteContext)_localctx).thenb = statementseq();
			setState(168);
			match(T__9);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(169);
				match(T__18);
				setState(170);
				match(T__8);
				setState(171);
				((IteContext)_localctx).elseb = statementseq();
				setState(172);
				match(T__9);
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

	public static class StatementseqContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementseqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementseq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterStatementseq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitStatementseq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitStatementseq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementseqContext statementseq() throws RecognitionException {
		StatementseqContext _localctx = new StatementseqContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statementseq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << ID))) != 0)) {
				{
				{
				setState(176);
				statement();
				}
				}
				setState(181);
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

	public static class CallContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(AssetLanParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(AssetLanParser.ID, i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(ID);
			setState(183);
			match(T__3);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__19) | (1L << T__20) | (1L << BOOL) | (1L << ID) | (1L << NUMBER))) != 0)) {
				{
				setState(184);
				exp(0);
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(185);
					match(T__4);
					setState(186);
					exp(0);
					}
					}
					setState(191);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(194);
			match(T__5);
			setState(195);
			match(T__6);
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(196);
				match(ID);
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(197);
					match(T__4);
					setState(198);
					match(ID);
					}
					}
					setState(203);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(206);
			match(T__7);
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

	public static class InitcallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AssetLanParser.ID, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<AexpContext> aexp() {
			return getRuleContexts(AexpContext.class);
		}
		public AexpContext aexp(int i) {
			return getRuleContext(AexpContext.class,i);
		}
		public InitcallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initcall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterInitcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitInitcall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitInitcall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitcallContext initcall() throws RecognitionException {
		InitcallContext _localctx = new InitcallContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_initcall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(ID);
			setState(209);
			match(T__3);
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__19) | (1L << T__20) | (1L << BOOL) | (1L << ID) | (1L << NUMBER))) != 0)) {
				{
				setState(210);
				exp(0);
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(211);
					match(T__4);
					setState(212);
					exp(0);
					}
					}
					setState(217);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(220);
			match(T__5);
			setState(221);
			match(T__6);
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__19) | (1L << T__20) | (1L << BOOL) | (1L << ID) | (1L << NUMBER))) != 0)) {
				{
				setState(222);
				aexp();
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(223);
					match(T__4);
					setState(224);
					aexp();
					}
					}
					setState(229);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(232);
			match(T__7);
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

	public static class ExpContext extends ParserRuleContext {
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	 
		public ExpContext() { }
		public void copyFrom(ExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BaseExpContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public BaseExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterBaseExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitBaseExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitBaseExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinExpContext extends ExpContext {
		public ExpContext left;
		public Token op;
		public ExpContext right;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public BinExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterBinExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitBinExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitBinExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DerExpContext extends ExpContext {
		public TerminalNode ID() { return getToken(AssetLanParser.ID, 0); }
		public DerExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterDerExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitDerExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitDerExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValExpContext extends ExpContext {
		public TerminalNode NUMBER() { return getToken(AssetLanParser.NUMBER, 0); }
		public ValExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterValExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitValExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitValExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegExpContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public NegExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterNegExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitNegExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitNegExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolExpContext extends ExpContext {
		public TerminalNode BOOL() { return getToken(AssetLanParser.BOOL, 0); }
		public BoolExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterBoolExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitBoolExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitBoolExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallExpContext extends ExpContext {
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public CallExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterCallExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitCallExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitCallExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExpContext extends ExpContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public NotExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterNotExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitNotExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitNotExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				_localctx = new BaseExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(235);
				match(T__3);
				setState(236);
				exp(0);
				setState(237);
				match(T__5);
				}
				break;
			case 2:
				{
				_localctx = new NegExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(239);
				match(T__19);
				setState(240);
				exp(12);
				}
				break;
			case 3:
				{
				_localctx = new NotExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(241);
				match(T__20);
				setState(242);
				exp(11);
				}
				break;
			case 4:
				{
				_localctx = new DerExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(243);
				match(ID);
				}
				break;
			case 5:
				{
				_localctx = new CallExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(244);
				call();
				}
				break;
			case 6:
				{
				_localctx = new BoolExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(245);
				match(BOOL);
				}
				break;
			case 7:
				{
				_localctx = new ValExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(246);
				match(NUMBER);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(269);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(267);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new BinExpContext(new ExpContext(_parentctx, _parentState));
						((BinExpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(249);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(250);
						((BinExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__21 || _la==T__22) ) {
							((BinExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(251);
						((BinExpContext)_localctx).right = exp(10);
						}
						break;
					case 2:
						{
						_localctx = new BinExpContext(new ExpContext(_parentctx, _parentState));
						((BinExpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(252);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(253);
						((BinExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__23) ) {
							((BinExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(254);
						((BinExpContext)_localctx).right = exp(9);
						}
						break;
					case 3:
						{
						_localctx = new BinExpContext(new ExpContext(_parentctx, _parentState));
						((BinExpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(255);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(256);
						((BinExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27))) != 0)) ) {
							((BinExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(257);
						((BinExpContext)_localctx).right = exp(8);
						}
						break;
					case 4:
						{
						_localctx = new BinExpContext(new ExpContext(_parentctx, _parentState));
						((BinExpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(258);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(259);
						((BinExpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__28 || _la==T__29) ) {
							((BinExpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(260);
						((BinExpContext)_localctx).right = exp(7);
						}
						break;
					case 5:
						{
						_localctx = new BinExpContext(new ExpContext(_parentctx, _parentState));
						((BinExpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(261);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(262);
						((BinExpContext)_localctx).op = match(T__30);
						setState(263);
						((BinExpContext)_localctx).right = exp(6);
						}
						break;
					case 6:
						{
						_localctx = new BinExpContext(new ExpContext(_parentctx, _parentState));
						((BinExpContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(264);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(265);
						((BinExpContext)_localctx).op = match(T__31);
						setState(266);
						((BinExpContext)_localctx).right = exp(5);
						}
						break;
					}
					} 
				}
				setState(271);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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

	public static class AexpContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public AexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).enterAexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AssetLanListener ) ((AssetLanListener)listener).exitAexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AssetLanVisitor ) return ((AssetLanVisitor<? extends T>)visitor).visitAexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AexpContext aexp() throws RecognitionException {
		AexpContext _localctx = new AexpContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_aexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			exp(0);
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
		case 18:
			return exp_sempred((ExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001&\u0113\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000\u0001\u0001\u0005\u0001"+
		",\b\u0001\n\u0001\f\u0001/\t\u0001\u0001\u0001\u0005\u00012\b\u0001\n"+
		"\u0001\f\u00015\t\u0001\u0001\u0001\u0005\u00018\b\u0001\n\u0001\f\u0001"+
		";\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002C\b\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004Q\b\u0004\n\u0004\f\u0004"+
		"T\t\u0004\u0003\u0004V\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0005\u0004]\b\u0004\n\u0004\f\u0004`\t\u0004\u0003"+
		"\u0004b\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004g\b\u0004"+
		"\n\u0004\f\u0004j\t\u0004\u0001\u0004\u0005\u0004m\b\u0004\n\u0004\f\u0004"+
		"p\t\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u008d\b\u0007"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f"+
		"\u0001\r\u0001\r\u0003\r\u00a1\b\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00af\b\u000e\u0001\u000f"+
		"\u0005\u000f\u00b2\b\u000f\n\u000f\f\u000f\u00b5\t\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00bc\b\u0010\n"+
		"\u0010\f\u0010\u00bf\t\u0010\u0003\u0010\u00c1\b\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00c8\b\u0010\n"+
		"\u0010\f\u0010\u00cb\t\u0010\u0003\u0010\u00cd\b\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005"+
		"\u0011\u00d6\b\u0011\n\u0011\f\u0011\u00d9\t\u0011\u0003\u0011\u00db\b"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005"+
		"\u0011\u00e2\b\u0011\n\u0011\f\u0011\u00e5\t\u0011\u0003\u0011\u00e7\b"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00f8\b\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005"+
		"\u0012\u010c\b\u0012\n\u0012\f\u0012\u010f\t\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0000\u0001$\u0014\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&\u0000\u0005\u0001\u0000"+
		"\u000b\r\u0001\u0000\u0016\u0017\u0002\u0000\u0014\u0014\u0018\u0018\u0001"+
		"\u0000\u0019\u001c\u0001\u0000\u001d\u001e\u0125\u0000(\u0001\u0000\u0000"+
		"\u0000\u0002-\u0001\u0000\u0000\u0000\u0004>\u0001\u0000\u0000\u0000\u0006"+
		"F\u0001\u0000\u0000\u0000\bJ\u0001\u0000\u0000\u0000\ns\u0001\u0000\u0000"+
		"\u0000\fv\u0001\u0000\u0000\u0000\u000e\u008c\u0001\u0000\u0000\u0000"+
		"\u0010\u008e\u0001\u0000\u0000\u0000\u0012\u0090\u0001\u0000\u0000\u0000"+
		"\u0014\u0094\u0001\u0000\u0000\u0000\u0016\u0098\u0001\u0000\u0000\u0000"+
		"\u0018\u009b\u0001\u0000\u0000\u0000\u001a\u009e\u0001\u0000\u0000\u0000"+
		"\u001c\u00a2\u0001\u0000\u0000\u0000\u001e\u00b3\u0001\u0000\u0000\u0000"+
		" \u00b6\u0001\u0000\u0000\u0000\"\u00d0\u0001\u0000\u0000\u0000$\u00f7"+
		"\u0001\u0000\u0000\u0000&\u0110\u0001\u0000\u0000\u0000()\u0003\u0002"+
		"\u0001\u0000)\u0001\u0001\u0000\u0000\u0000*,\u0003\u0004\u0002\u0000"+
		"+*\u0001\u0000\u0000\u0000,/\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000"+
		"\u0000-.\u0001\u0000\u0000\u0000.3\u0001\u0000\u0000\u0000/-\u0001\u0000"+
		"\u0000\u000002\u0003\u0006\u0003\u000010\u0001\u0000\u0000\u000025\u0001"+
		"\u0000\u0000\u000031\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u0000"+
		"49\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u000068\u0003\b\u0004\u0000"+
		"76\u0001\u0000\u0000\u00008;\u0001\u0000\u0000\u000097\u0001\u0000\u0000"+
		"\u00009:\u0001\u0000\u0000\u0000:<\u0001\u0000\u0000\u0000;9\u0001\u0000"+
		"\u0000\u0000<=\u0003\"\u0011\u0000=\u0003\u0001\u0000\u0000\u0000>?\u0003"+
		"\u0010\b\u0000?B\u0005\"\u0000\u0000@A\u0005\u0001\u0000\u0000AC\u0003"+
		"$\u0012\u0000B@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000CD\u0001"+
		"\u0000\u0000\u0000DE\u0005\u0002\u0000\u0000E\u0005\u0001\u0000\u0000"+
		"\u0000FG\u0005\u0003\u0000\u0000GH\u0005\"\u0000\u0000HI\u0005\u0002\u0000"+
		"\u0000I\u0007\u0001\u0000\u0000\u0000JK\u0003\u0010\b\u0000KL\u0005\""+
		"\u0000\u0000LU\u0005\u0004\u0000\u0000MR\u0003\n\u0005\u0000NO\u0005\u0005"+
		"\u0000\u0000OQ\u0003\n\u0005\u0000PN\u0001\u0000\u0000\u0000QT\u0001\u0000"+
		"\u0000\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SV\u0001"+
		"\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000UM\u0001\u0000\u0000\u0000"+
		"UV\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WX\u0005\u0006\u0000"+
		"\u0000Xa\u0005\u0007\u0000\u0000Y^\u0003\f\u0006\u0000Z[\u0005\u0005\u0000"+
		"\u0000[]\u0003\f\u0006\u0000\\Z\u0001\u0000\u0000\u0000]`\u0001\u0000"+
		"\u0000\u0000^\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_b\u0001"+
		"\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000aY\u0001\u0000\u0000\u0000"+
		"ab\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000cd\u0005\b\u0000\u0000"+
		"dh\u0005\t\u0000\u0000eg\u0003\u0004\u0002\u0000fe\u0001\u0000\u0000\u0000"+
		"gj\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000"+
		"\u0000in\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000km\u0003\u000e"+
		"\u0007\u0000lk\u0001\u0000\u0000\u0000mp\u0001\u0000\u0000\u0000nl\u0001"+
		"\u0000\u0000\u0000no\u0001\u0000\u0000\u0000oq\u0001\u0000\u0000\u0000"+
		"pn\u0001\u0000\u0000\u0000qr\u0005\n\u0000\u0000r\t\u0001\u0000\u0000"+
		"\u0000st\u0003\u0010\b\u0000tu\u0005\"\u0000\u0000u\u000b\u0001\u0000"+
		"\u0000\u0000vw\u0005\u0003\u0000\u0000wx\u0005\"\u0000\u0000x\r\u0001"+
		"\u0000\u0000\u0000yz\u0003\u0012\t\u0000z{\u0005\u0002\u0000\u0000{\u008d"+
		"\u0001\u0000\u0000\u0000|}\u0003\u0014\n\u0000}~\u0005\u0002\u0000\u0000"+
		"~\u008d\u0001\u0000\u0000\u0000\u007f\u0080\u0003\u0016\u000b\u0000\u0080"+
		"\u0081\u0005\u0002\u0000\u0000\u0081\u008d\u0001\u0000\u0000\u0000\u0082"+
		"\u0083\u0003\u0018\f\u0000\u0083\u0084\u0005\u0002\u0000\u0000\u0084\u008d"+
		"\u0001\u0000\u0000\u0000\u0085\u0086\u0003\u001a\r\u0000\u0086\u0087\u0005"+
		"\u0002\u0000\u0000\u0087\u008d\u0001\u0000\u0000\u0000\u0088\u008d\u0003"+
		"\u001c\u000e\u0000\u0089\u008a\u0003 \u0010\u0000\u008a\u008b\u0005\u0002"+
		"\u0000\u0000\u008b\u008d\u0001\u0000\u0000\u0000\u008cy\u0001\u0000\u0000"+
		"\u0000\u008c|\u0001\u0000\u0000\u0000\u008c\u007f\u0001\u0000\u0000\u0000"+
		"\u008c\u0082\u0001\u0000\u0000\u0000\u008c\u0085\u0001\u0000\u0000\u0000"+
		"\u008c\u0088\u0001\u0000\u0000\u0000\u008c\u0089\u0001\u0000\u0000\u0000"+
		"\u008d\u000f\u0001\u0000\u0000\u0000\u008e\u008f\u0007\u0000\u0000\u0000"+
		"\u008f\u0011\u0001\u0000\u0000\u0000\u0090\u0091\u0005\"\u0000\u0000\u0091"+
		"\u0092\u0005\u0001\u0000\u0000\u0092\u0093\u0003$\u0012\u0000\u0093\u0013"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0005\"\u0000\u0000\u0095\u0096\u0005"+
		"\u000e\u0000\u0000\u0096\u0097\u0005\"\u0000\u0000\u0097\u0015\u0001\u0000"+
		"\u0000\u0000\u0098\u0099\u0005\u000f\u0000\u0000\u0099\u009a\u0003$\u0012"+
		"\u0000\u009a\u0017\u0001\u0000\u0000\u0000\u009b\u009c\u0005\u0010\u0000"+
		"\u0000\u009c\u009d\u0005\"\u0000\u0000\u009d\u0019\u0001\u0000\u0000\u0000"+
		"\u009e\u00a0\u0005\u0011\u0000\u0000\u009f\u00a1\u0003$\u0012\u0000\u00a0"+
		"\u009f\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1"+
		"\u001b\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005\u0012\u0000\u0000\u00a3"+
		"\u00a4\u0005\u0004\u0000\u0000\u00a4\u00a5\u0003$\u0012\u0000\u00a5\u00a6"+
		"\u0005\u0006\u0000\u0000\u00a6\u00a7\u0005\t\u0000\u0000\u00a7\u00a8\u0003"+
		"\u001e\u000f\u0000\u00a8\u00ae\u0005\n\u0000\u0000\u00a9\u00aa\u0005\u0013"+
		"\u0000\u0000\u00aa\u00ab\u0005\t\u0000\u0000\u00ab\u00ac\u0003\u001e\u000f"+
		"\u0000\u00ac\u00ad\u0005\n\u0000\u0000\u00ad\u00af\u0001\u0000\u0000\u0000"+
		"\u00ae\u00a9\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000"+
		"\u00af\u001d\u0001\u0000\u0000\u0000\u00b0\u00b2\u0003\u000e\u0007\u0000"+
		"\u00b1\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b4\u001f\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0005\"\u0000\u0000\u00b7\u00c0\u0005\u0004\u0000\u0000\u00b8"+
		"\u00bd\u0003$\u0012\u0000\u00b9\u00ba\u0005\u0005\u0000\u0000\u00ba\u00bc"+
		"\u0003$\u0012\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc\u00bf\u0001"+
		"\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001"+
		"\u0000\u0000\u0000\u00be\u00c1\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001"+
		"\u0000\u0000\u0000\u00c0\u00b8\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005"+
		"\u0006\u0000\u0000\u00c3\u00cc\u0005\u0007\u0000\u0000\u00c4\u00c9\u0005"+
		"\"\u0000\u0000\u00c5\u00c6\u0005\u0005\u0000\u0000\u00c6\u00c8\u0005\""+
		"\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c8\u00cb\u0001\u0000"+
		"\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000"+
		"\u0000\u0000\u00ca\u00cd\u0001\u0000\u0000\u0000\u00cb\u00c9\u0001\u0000"+
		"\u0000\u0000\u00cc\u00c4\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005\b\u0000"+
		"\u0000\u00cf!\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005\"\u0000\u0000"+
		"\u00d1\u00da\u0005\u0004\u0000\u0000\u00d2\u00d7\u0003$\u0012\u0000\u00d3"+
		"\u00d4\u0005\u0005\u0000\u0000\u00d4\u00d6\u0003$\u0012\u0000\u00d5\u00d3"+
		"\u0001\u0000\u0000\u0000\u00d6\u00d9\u0001\u0000\u0000\u0000\u00d7\u00d5"+
		"\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8\u00db"+
		"\u0001\u0000\u0000\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00da\u00d2"+
		"\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00dc"+
		"\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005\u0006\u0000\u0000\u00dd\u00e6"+
		"\u0005\u0007\u0000\u0000\u00de\u00e3\u0003&\u0013\u0000\u00df\u00e0\u0005"+
		"\u0005\u0000\u0000\u00e0\u00e2\u0003&\u0013\u0000\u00e1\u00df\u0001\u0000"+
		"\u0000\u0000\u00e2\u00e5\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e6\u00de\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000"+
		"\u0000\u0000\u00e8\u00e9\u0005\b\u0000\u0000\u00e9#\u0001\u0000\u0000"+
		"\u0000\u00ea\u00eb\u0006\u0012\uffff\uffff\u0000\u00eb\u00ec\u0005\u0004"+
		"\u0000\u0000\u00ec\u00ed\u0003$\u0012\u0000\u00ed\u00ee\u0005\u0006\u0000"+
		"\u0000\u00ee\u00f8\u0001\u0000\u0000\u0000\u00ef\u00f0\u0005\u0014\u0000"+
		"\u0000\u00f0\u00f8\u0003$\u0012\f\u00f1\u00f2\u0005\u0015\u0000\u0000"+
		"\u00f2\u00f8\u0003$\u0012\u000b\u00f3\u00f8\u0005\"\u0000\u0000\u00f4"+
		"\u00f8\u0003 \u0010\u0000\u00f5\u00f8\u0005!\u0000\u0000\u00f6\u00f8\u0005"+
		"#\u0000\u0000\u00f7\u00ea\u0001\u0000\u0000\u0000\u00f7\u00ef\u0001\u0000"+
		"\u0000\u0000\u00f7\u00f1\u0001\u0000\u0000\u0000\u00f7\u00f3\u0001\u0000"+
		"\u0000\u0000\u00f7\u00f4\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000"+
		"\u0000\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f8\u010d\u0001\u0000"+
		"\u0000\u0000\u00f9\u00fa\n\t\u0000\u0000\u00fa\u00fb\u0007\u0001\u0000"+
		"\u0000\u00fb\u010c\u0003$\u0012\n\u00fc\u00fd\n\b\u0000\u0000\u00fd\u00fe"+
		"\u0007\u0002\u0000\u0000\u00fe\u010c\u0003$\u0012\t\u00ff\u0100\n\u0007"+
		"\u0000\u0000\u0100\u0101\u0007\u0003\u0000\u0000\u0101\u010c\u0003$\u0012"+
		"\b\u0102\u0103\n\u0006\u0000\u0000\u0103\u0104\u0007\u0004\u0000\u0000"+
		"\u0104\u010c\u0003$\u0012\u0007\u0105\u0106\n\u0005\u0000\u0000\u0106"+
		"\u0107\u0005\u001f\u0000\u0000\u0107\u010c\u0003$\u0012\u0006\u0108\u0109"+
		"\n\u0004\u0000\u0000\u0109\u010a\u0005 \u0000\u0000\u010a\u010c\u0003"+
		"$\u0012\u0005\u010b\u00f9\u0001\u0000\u0000\u0000\u010b\u00fc\u0001\u0000"+
		"\u0000\u0000\u010b\u00ff\u0001\u0000\u0000\u0000\u010b\u0102\u0001\u0000"+
		"\u0000\u0000\u010b\u0105\u0001\u0000\u0000\u0000\u010b\u0108\u0001\u0000"+
		"\u0000\u0000\u010c\u010f\u0001\u0000\u0000\u0000\u010d\u010b\u0001\u0000"+
		"\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e%\u0001\u0000\u0000"+
		"\u0000\u010f\u010d\u0001\u0000\u0000\u0000\u0110\u0111\u0003$\u0012\u0000"+
		"\u0111\'\u0001\u0000\u0000\u0000\u0019-39BRU^ahn\u008c\u00a0\u00ae\u00b3"+
		"\u00bd\u00c0\u00c9\u00cc\u00d7\u00da\u00e3\u00e6\u00f7\u010b\u010d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}