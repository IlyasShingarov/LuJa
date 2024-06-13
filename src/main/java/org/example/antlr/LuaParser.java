// Generated from C:/javastuff/LuaCompiler/src/main/antlr/LuaParser.g4 by ANTLR 4.13.1

package org.example.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LuaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMI=1, EQ=2, BREAK=3, GOTO=4, DO=5, END=6, WHILE=7, REPEAT=8, UNTIL=9, 
		IF=10, THEN=11, ELSEIF=12, ELSE=13, FOR=14, COMMA=15, IN=16, FUNCTION=17, 
		LOCAL=18, LT=19, GT=20, RETURN=21, CONTINUE=22, CC=23, NIL=24, FALSE=25, 
		TRUE=26, DOT=27, SQUIG=28, MINUS=29, POUND=30, OP=31, CP=32, NOT=33, LL=34, 
		GG=35, AMP=36, SS=37, PER=38, COL=39, LE=40, GE=41, AND=42, OR=43, PLUS=44, 
		STAR=45, OCU=46, CCU=47, OB=48, CB=49, EE=50, DD=51, PIPE=52, CARET=53, 
		SLASH=54, DDD=55, SQEQ=56, TYPE_I=57, TYPE_F=58, TYPE_S=59, TYPE_B=60, 
		TYPE_T=61, TYPE_V=62, NAME=63, NORMALSTRING=64, CHARSTRING=65, LONGSTRING=66, 
		INT=67, HEX=68, FLOAT=69, HEX_FLOAT=70, COMMENT=71, WS=72, NL=73, SHEBANG=74;
	public static final int
		RULE_start_ = 0, RULE_chunk = 1, RULE_block = 2, RULE_stat = 3, RULE_ifstat = 4, 
		RULE_whileloop = 5, RULE_forloop = 6, RULE_vardecl = 7, RULE_funcdecl = 8, 
		RULE_attnamelist = 9, RULE_attrib = 10, RULE_retstat = 11, RULE_label = 12, 
		RULE_funcname = 13, RULE_varlist = 14, RULE_namelist = 15, RULE_explist = 16, 
		RULE_exp = 17, RULE_binop = 18, RULE_multop = 19, RULE_addop = 20, RULE_relop = 21, 
		RULE_boolop = 22, RULE_bitop = 23, RULE_unop = 24, RULE_var = 25, RULE_prefixexp = 26, 
		RULE_functioncall = 27, RULE_args = 28, RULE_funcbody = 29, RULE_parlist = 30, 
		RULE_tableconstructor = 31, RULE_fieldlist = 32, RULE_field = 33, RULE_fieldsep = 34, 
		RULE_number = 35, RULE_string = 36, RULE_typehint = 37;
	private static String[] makeRuleNames() {
		return new String[] {
			"start_", "chunk", "block", "stat", "ifstat", "whileloop", "forloop", 
			"vardecl", "funcdecl", "attnamelist", "attrib", "retstat", "label", "funcname", 
			"varlist", "namelist", "explist", "exp", "binop", "multop", "addop", 
			"relop", "boolop", "bitop", "unop", "var", "prefixexp", "functioncall", 
			"args", "funcbody", "parlist", "tableconstructor", "fieldlist", "field", 
			"fieldsep", "number", "string", "typehint"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'break'", "'goto'", "'do'", "'end'", "'while'", 
			"'repeat'", "'until'", "'if'", "'then'", "'elseif'", "'else'", "'for'", 
			"','", "'in'", "'function'", "'local'", "'<'", "'>'", "'return'", "'continue'", 
			"'::'", "'nil'", "'false'", "'true'", "'.'", "'~'", "'-'", "'#'", "'('", 
			"')'", "'not'", "'<<'", "'>>'", "'&'", "'//'", "'%'", "':'", "'<='", 
			"'>='", "'and'", "'or'", "'+'", "'*'", "'{'", "'}'", "'['", "']'", "'=='", 
			"'..'", "'|'", "'^'", "'/'", "'...'", "'~='", "'int'", "'float'", "'string'", 
			"'boolean'", "'table'", "'void'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEMI", "EQ", "BREAK", "GOTO", "DO", "END", "WHILE", "REPEAT", 
			"UNTIL", "IF", "THEN", "ELSEIF", "ELSE", "FOR", "COMMA", "IN", "FUNCTION", 
			"LOCAL", "LT", "GT", "RETURN", "CONTINUE", "CC", "NIL", "FALSE", "TRUE", 
			"DOT", "SQUIG", "MINUS", "POUND", "OP", "CP", "NOT", "LL", "GG", "AMP", 
			"SS", "PER", "COL", "LE", "GE", "AND", "OR", "PLUS", "STAR", "OCU", "CCU", 
			"OB", "CB", "EE", "DD", "PIPE", "CARET", "SLASH", "DDD", "SQEQ", "TYPE_I", 
			"TYPE_F", "TYPE_S", "TYPE_B", "TYPE_T", "TYPE_V", "NAME", "NORMALSTRING", 
			"CHARSTRING", "LONGSTRING", "INT", "HEX", "FLOAT", "HEX_FLOAT", "COMMENT", 
			"WS", "NL", "SHEBANG"
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
	public String getGrammarFileName() { return "LuaParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LuaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Start_Context extends ParserRuleContext {
		public ChunkContext chunk() {
			return getRuleContext(ChunkContext.class,0);
		}
		public TerminalNode EOF() { return getToken(LuaParser.EOF, 0); }
		public Start_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterStart_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitStart_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitStart_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Start_Context start_() throws RecognitionException {
		Start_Context _localctx = new Start_Context(_ctx, getState());
		enterRule(_localctx, 0, RULE_start_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			chunk();
			setState(77);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ChunkContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ChunkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chunk; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterChunk(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitChunk(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitChunk(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChunkContext chunk() throws RecognitionException {
		ChunkContext _localctx = new ChunkContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_chunk);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			block();
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

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public RetstatContext retstat() {
			return getRuleContext(RetstatContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(81);
					stat();
					}
					} 
				}
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6291464L) != 0)) {
				{
				setState(87);
				retstat();
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(LuaParser.SEMI, 0); }
		public VardeclContext vardecl() {
			return getRuleContext(VardeclContext.class,0);
		}
		public FunctioncallContext functioncall() {
			return getRuleContext(FunctioncallContext.class,0);
		}
		public TerminalNode BREAK() { return getToken(LuaParser.BREAK, 0); }
		public WhileloopContext whileloop() {
			return getRuleContext(WhileloopContext.class,0);
		}
		public IfstatContext ifstat() {
			return getRuleContext(IfstatContext.class,0);
		}
		public ForloopContext forloop() {
			return getRuleContext(ForloopContext.class,0);
		}
		public FuncdeclContext funcdecl() {
			return getRuleContext(FuncdeclContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stat);
		try {
			setState(98);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				vardecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				functioncall(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(93);
				match(BREAK);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(94);
				whileloop();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(95);
				ifstat();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(96);
				forloop();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(97);
				funcdecl();
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

	@SuppressWarnings("CheckReturnValue")
	public static class IfstatContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(LuaParser.IF, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> THEN() { return getTokens(LuaParser.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(LuaParser.THEN, i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode END() { return getToken(LuaParser.END, 0); }
		public List<TerminalNode> ELSEIF() { return getTokens(LuaParser.ELSEIF); }
		public TerminalNode ELSEIF(int i) {
			return getToken(LuaParser.ELSEIF, i);
		}
		public TerminalNode ELSE() { return getToken(LuaParser.ELSE, 0); }
		public IfstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterIfstat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitIfstat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitIfstat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfstatContext ifstat() throws RecognitionException {
		IfstatContext _localctx = new IfstatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ifstat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(IF);
			setState(101);
			exp(0);
			setState(102);
			match(THEN);
			setState(103);
			block();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSEIF) {
				{
				{
				setState(104);
				match(ELSEIF);
				setState(105);
				exp(0);
				setState(106);
				match(THEN);
				setState(107);
				block();
				}
				}
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(114);
				match(ELSE);
				setState(115);
				block();
				}
			}

			setState(118);
			match(END);
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

	@SuppressWarnings("CheckReturnValue")
	public static class WhileloopContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(LuaParser.WHILE, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode DO() { return getToken(LuaParser.DO, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(LuaParser.END, 0); }
		public WhileloopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileloop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterWhileloop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitWhileloop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitWhileloop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileloopContext whileloop() throws RecognitionException {
		WhileloopContext _localctx = new WhileloopContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_whileloop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(WHILE);
			setState(121);
			exp(0);
			setState(122);
			match(DO);
			setState(123);
			block();
			setState(124);
			match(END);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ForloopContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(LuaParser.FOR, 0); }
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public TerminalNode EQ() { return getToken(LuaParser.EQ, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public TerminalNode DO() { return getToken(LuaParser.DO, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(LuaParser.END, 0); }
		public NamelistContext namelist() {
			return getRuleContext(NamelistContext.class,0);
		}
		public TerminalNode IN() { return getToken(LuaParser.IN, 0); }
		public ExplistContext explist() {
			return getRuleContext(ExplistContext.class,0);
		}
		public ForloopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forloop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterForloop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitForloop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitForloop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForloopContext forloop() throws RecognitionException {
		ForloopContext _localctx = new ForloopContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_forloop);
		int _la;
		try {
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(FOR);
				setState(127);
				match(NAME);
				setState(128);
				match(EQ);
				setState(129);
				exp(0);
				setState(130);
				match(COMMA);
				setState(131);
				exp(0);
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(132);
					match(COMMA);
					setState(133);
					exp(0);
					}
				}

				setState(136);
				match(DO);
				setState(137);
				block();
				setState(138);
				match(END);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				match(FOR);
				setState(141);
				namelist();
				setState(142);
				match(IN);
				setState(143);
				explist();
				setState(144);
				match(DO);
				setState(145);
				block();
				setState(146);
				match(END);
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

	@SuppressWarnings("CheckReturnValue")
	public static class VardeclContext extends ParserRuleContext {
		public VarlistContext varlist() {
			return getRuleContext(VarlistContext.class,0);
		}
		public TerminalNode EQ() { return getToken(LuaParser.EQ, 0); }
		public ExplistContext explist() {
			return getRuleContext(ExplistContext.class,0);
		}
		public TerminalNode LOCAL() { return getToken(LuaParser.LOCAL, 0); }
		public AttnamelistContext attnamelist() {
			return getRuleContext(AttnamelistContext.class,0);
		}
		public VardeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vardecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterVardecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitVardecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitVardecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VardeclContext vardecl() throws RecognitionException {
		VardeclContext _localctx = new VardeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_vardecl);
		int _la;
		try {
			setState(160);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OP:
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				varlist();
				setState(151);
				match(EQ);
				setState(152);
				explist();
				}
				break;
			case LOCAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(LOCAL);
				setState(155);
				attnamelist();
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(156);
					match(EQ);
					setState(157);
					explist();
					}
				}

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

	@SuppressWarnings("CheckReturnValue")
	public static class FuncdeclContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(LuaParser.FUNCTION, 0); }
		public FuncnameContext funcname() {
			return getRuleContext(FuncnameContext.class,0);
		}
		public FuncbodyContext funcbody() {
			return getRuleContext(FuncbodyContext.class,0);
		}
		public TerminalNode LOCAL() { return getToken(LuaParser.LOCAL, 0); }
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public FuncdeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFuncdecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFuncdecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitFuncdecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncdeclContext funcdecl() throws RecognitionException {
		FuncdeclContext _localctx = new FuncdeclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_funcdecl);
		try {
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				match(FUNCTION);
				setState(163);
				funcname();
				setState(164);
				funcbody();
				}
				break;
			case LOCAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				match(LOCAL);
				setState(167);
				match(FUNCTION);
				setState(168);
				match(NAME);
				setState(169);
				funcbody();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AttnamelistContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(LuaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(LuaParser.NAME, i);
		}
		public List<AttribContext> attrib() {
			return getRuleContexts(AttribContext.class);
		}
		public AttribContext attrib(int i) {
			return getRuleContext(AttribContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public AttnamelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attnamelist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterAttnamelist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitAttnamelist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitAttnamelist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttnamelistContext attnamelist() throws RecognitionException {
		AttnamelistContext _localctx = new AttnamelistContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_attnamelist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(NAME);
			setState(173);
			attrib();
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(174);
				match(COMMA);
				setState(175);
				match(NAME);
				setState(176);
				attrib();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AttribContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(LuaParser.LT, 0); }
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public TerminalNode GT() { return getToken(LuaParser.GT, 0); }
		public AttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterAttrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitAttrib(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitAttrib(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttribContext attrib() throws RecognitionException {
		AttribContext _localctx = new AttribContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_attrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(182);
				match(LT);
				setState(183);
				match(NAME);
				setState(184);
				match(GT);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RetstatContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(LuaParser.RETURN, 0); }
		public TerminalNode BREAK() { return getToken(LuaParser.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(LuaParser.CONTINUE, 0); }
		public TerminalNode SEMI() { return getToken(LuaParser.SEMI, 0); }
		public ExplistContext explist() {
			return getRuleContext(ExplistContext.class,0);
		}
		public RetstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_retstat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterRetstat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitRetstat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitRetstat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetstatContext retstat() throws RecognitionException {
		RetstatContext _localctx = new RetstatContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_retstat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
				{
				setState(187);
				match(RETURN);
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 140189884220151L) != 0)) {
					{
					setState(188);
					explist();
					}
				}

				}
				break;
			case BREAK:
				{
				setState(191);
				match(BREAK);
				}
				break;
			case CONTINUE:
				{
				setState(192);
				match(CONTINUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(195);
				match(SEMI);
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

	@SuppressWarnings("CheckReturnValue")
	public static class LabelContext extends ParserRuleContext {
		public List<TerminalNode> CC() { return getTokens(LuaParser.CC); }
		public TerminalNode CC(int i) {
			return getToken(LuaParser.CC, i);
		}
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(CC);
			setState(199);
			match(NAME);
			setState(200);
			match(CC);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FuncnameContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(LuaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(LuaParser.NAME, i);
		}
		public List<TerminalNode> DOT() { return getTokens(LuaParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(LuaParser.DOT, i);
		}
		public TerminalNode COL() { return getToken(LuaParser.COL, 0); }
		public FuncnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFuncname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFuncname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitFuncname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncnameContext funcname() throws RecognitionException {
		FuncnameContext _localctx = new FuncnameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_funcname);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(NAME);
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(203);
				match(DOT);
				setState(204);
				match(NAME);
				}
				}
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COL) {
				{
				setState(210);
				match(COL);
				setState(211);
				match(NAME);
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

	@SuppressWarnings("CheckReturnValue")
	public static class VarlistContext extends ParserRuleContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public VarlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterVarlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitVarlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitVarlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarlistContext varlist() throws RecognitionException {
		VarlistContext _localctx = new VarlistContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_varlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			var();
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(215);
				match(COMMA);
				setState(216);
				var();
				}
				}
				setState(221);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NamelistContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(LuaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(LuaParser.NAME, i);
		}
		public List<TerminalNode> COL() { return getTokens(LuaParser.COL); }
		public TerminalNode COL(int i) {
			return getToken(LuaParser.COL, i);
		}
		public List<TypehintContext> typehint() {
			return getRuleContexts(TypehintContext.class);
		}
		public TypehintContext typehint(int i) {
			return getRuleContext(TypehintContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public NamelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namelist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterNamelist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitNamelist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitNamelist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamelistContext namelist() throws RecognitionException {
		NamelistContext _localctx = new NamelistContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_namelist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(NAME);
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COL) {
				{
				setState(223);
				match(COL);
				setState(224);
				typehint();
				}
			}

			setState(235);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(227);
					match(COMMA);
					setState(228);
					match(NAME);
					setState(231);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COL) {
						{
						setState(229);
						match(COL);
						setState(230);
						typehint();
						}
					}

					}
					} 
				}
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExplistContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public ExplistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterExplist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitExplist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitExplist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExplistContext explist() throws RecognitionException {
		ExplistContext _localctx = new ExplistContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_explist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			exp(0);
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(239);
				match(COMMA);
				setState(240);
				exp(0);
				}
				}
				setState(245);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExpContext extends ParserRuleContext {
		public TerminalNode NIL() { return getToken(LuaParser.NIL, 0); }
		public TerminalNode FALSE() { return getToken(LuaParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(LuaParser.TRUE, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode DDD() { return getToken(LuaParser.DDD, 0); }
		public PrefixexpContext prefixexp() {
			return getRuleContext(PrefixexpContext.class,0);
		}
		public TableconstructorContext tableconstructor() {
			return getRuleContext(TableconstructorContext.class,0);
		}
		public UnopContext unop() {
			return getRuleContext(UnopContext.class,0);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public BinopContext binop() {
			return getRuleContext(BinopContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitExp(this);
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
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_exp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NIL:
				{
				setState(247);
				match(NIL);
				}
				break;
			case FALSE:
				{
				setState(248);
				match(FALSE);
				}
				break;
			case TRUE:
				{
				setState(249);
				match(TRUE);
				}
				break;
			case INT:
			case HEX:
			case FLOAT:
			case HEX_FLOAT:
				{
				setState(250);
				number();
				}
				break;
			case NORMALSTRING:
			case CHARSTRING:
			case LONGSTRING:
				{
				setState(251);
				string();
				}
				break;
			case DDD:
				{
				setState(252);
				match(DDD);
				}
				break;
			case OP:
			case NAME:
				{
				setState(253);
				prefixexp();
				}
				break;
			case OCU:
				{
				setState(254);
				tableconstructor();
				}
				break;
			case SQUIG:
			case MINUS:
			case POUND:
			case NOT:
				{
				setState(255);
				unop();
				setState(256);
				exp(2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(266);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exp);
					setState(260);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(261);
					binop();
					setState(262);
					exp(2);
					}
					} 
				}
				setState(268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BinopContext extends ParserRuleContext {
		public MultopContext multop() {
			return getRuleContext(MultopContext.class,0);
		}
		public AddopContext addop() {
			return getRuleContext(AddopContext.class,0);
		}
		public RelopContext relop() {
			return getRuleContext(RelopContext.class,0);
		}
		public BoolopContext boolop() {
			return getRuleContext(BoolopContext.class,0);
		}
		public BitopContext bitop() {
			return getRuleContext(BitopContext.class,0);
		}
		public BinopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterBinop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitBinop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitBinop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinopContext binop() throws RecognitionException {
		BinopContext _localctx = new BinopContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_binop);
		try {
			setState(274);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SS:
			case PER:
			case STAR:
			case SLASH:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				multop();
				}
				break;
			case MINUS:
			case PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				addop();
				}
				break;
			case LT:
			case GT:
			case LE:
			case GE:
			case EE:
			case SQEQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(271);
				relop();
				}
				break;
			case AND:
			case OR:
				enterOuterAlt(_localctx, 4);
				{
				setState(272);
				boolop();
				}
				break;
			case SQUIG:
			case LL:
			case GG:
			case AMP:
			case PIPE:
				enterOuterAlt(_localctx, 5);
				{
				setState(273);
				bitop();
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

	@SuppressWarnings("CheckReturnValue")
	public static class MultopContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(LuaParser.STAR, 0); }
		public TerminalNode SLASH() { return getToken(LuaParser.SLASH, 0); }
		public TerminalNode PER() { return getToken(LuaParser.PER, 0); }
		public TerminalNode SS() { return getToken(LuaParser.SS, 0); }
		public MultopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterMultop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitMultop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitMultop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultopContext multop() throws RecognitionException {
		MultopContext _localctx = new MultopContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_multop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 18049995198431232L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class AddopContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(LuaParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LuaParser.MINUS, 0); }
		public AddopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterAddop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitAddop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitAddop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddopContext addop() throws RecognitionException {
		AddopContext _localctx = new AddopContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_addop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			_la = _input.LA(1);
			if ( !(_la==MINUS || _la==PLUS) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class RelopContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(LuaParser.LT, 0); }
		public TerminalNode GT() { return getToken(LuaParser.GT, 0); }
		public TerminalNode LE() { return getToken(LuaParser.LE, 0); }
		public TerminalNode GE() { return getToken(LuaParser.GE, 0); }
		public TerminalNode SQEQ() { return getToken(LuaParser.SQEQ, 0); }
		public TerminalNode EE() { return getToken(LuaParser.EE, 0); }
		public RelopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterRelop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitRelop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitRelop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelopContext relop() throws RecognitionException {
		RelopContext _localctx = new RelopContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_relop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 73186792481226752L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class BoolopContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(LuaParser.AND, 0); }
		public TerminalNode OR() { return getToken(LuaParser.OR, 0); }
		public BoolopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterBoolop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitBoolop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitBoolop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolopContext boolop() throws RecognitionException {
		BoolopContext _localctx = new BoolopContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_boolop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class BitopContext extends ParserRuleContext {
		public TerminalNode AMP() { return getToken(LuaParser.AMP, 0); }
		public TerminalNode PIPE() { return getToken(LuaParser.PIPE, 0); }
		public TerminalNode SQUIG() { return getToken(LuaParser.SQUIG, 0); }
		public TerminalNode LL() { return getToken(LuaParser.LL, 0); }
		public TerminalNode GG() { return getToken(LuaParser.GG, 0); }
		public BitopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterBitop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitBitop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitBitop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BitopContext bitop() throws RecognitionException {
		BitopContext _localctx = new BitopContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_bitop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4503720154890240L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class UnopContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(LuaParser.NOT, 0); }
		public TerminalNode POUND() { return getToken(LuaParser.POUND, 0); }
		public TerminalNode MINUS() { return getToken(LuaParser.MINUS, 0); }
		public TerminalNode SQUIG() { return getToken(LuaParser.SQUIG, 0); }
		public UnopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterUnop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitUnop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitUnop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnopContext unop() throws RecognitionException {
		UnopContext _localctx = new UnopContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_unop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 10468982784L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class VarContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public PrefixexpContext prefixexp() {
			return getRuleContext(PrefixexpContext.class,0);
		}
		public TerminalNode OB() { return getToken(LuaParser.OB, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CB() { return getToken(LuaParser.CB, 0); }
		public TerminalNode DOT() { return getToken(LuaParser.DOT, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_var);
		try {
			setState(298);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				match(NAME);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
				prefixexp();
				setState(296);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OB:
					{
					setState(290);
					match(OB);
					setState(291);
					exp(0);
					setState(292);
					match(CB);
					}
					break;
				case DOT:
					{
					setState(294);
					match(DOT);
					setState(295);
					match(NAME);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class PrefixexpContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(LuaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(LuaParser.NAME, i);
		}
		public List<TerminalNode> OB() { return getTokens(LuaParser.OB); }
		public TerminalNode OB(int i) {
			return getToken(LuaParser.OB, i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> CB() { return getTokens(LuaParser.CB); }
		public TerminalNode CB(int i) {
			return getToken(LuaParser.CB, i);
		}
		public List<TerminalNode> DOT() { return getTokens(LuaParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(LuaParser.DOT, i);
		}
		public FunctioncallContext functioncall() {
			return getRuleContext(FunctioncallContext.class,0);
		}
		public TerminalNode OP() { return getToken(LuaParser.OP, 0); }
		public TerminalNode CP() { return getToken(LuaParser.CP, 0); }
		public PrefixexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterPrefixexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitPrefixexp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitPrefixexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixexpContext prefixexp() throws RecognitionException {
		PrefixexpContext _localctx = new PrefixexpContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_prefixexp);
		try {
			int _alt;
			setState(338);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(300);
				match(NAME);
				setState(309);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(307);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case OB:
							{
							setState(301);
							match(OB);
							setState(302);
							exp(0);
							setState(303);
							match(CB);
							}
							break;
						case DOT:
							{
							setState(305);
							match(DOT);
							setState(306);
							match(NAME);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(311);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(312);
				functioncall(0);
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(319);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case OB:
							{
							setState(313);
							match(OB);
							setState(314);
							exp(0);
							setState(315);
							match(CB);
							}
							break;
						case DOT:
							{
							setState(317);
							match(DOT);
							setState(318);
							match(NAME);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(323);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(324);
				match(OP);
				setState(325);
				exp(0);
				setState(326);
				match(CP);
				setState(335);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(333);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case OB:
							{
							setState(327);
							match(OB);
							setState(328);
							exp(0);
							setState(329);
							match(CB);
							}
							break;
						case DOT:
							{
							setState(331);
							match(DOT);
							setState(332);
							match(NAME);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(337);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctioncallContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(LuaParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(LuaParser.NAME, i);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public List<TerminalNode> OB() { return getTokens(LuaParser.OB); }
		public TerminalNode OB(int i) {
			return getToken(LuaParser.OB, i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> CB() { return getTokens(LuaParser.CB); }
		public TerminalNode CB(int i) {
			return getToken(LuaParser.CB, i);
		}
		public List<TerminalNode> DOT() { return getTokens(LuaParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(LuaParser.DOT, i);
		}
		public TerminalNode OP() { return getToken(LuaParser.OP, 0); }
		public TerminalNode CP() { return getToken(LuaParser.CP, 0); }
		public TerminalNode COL() { return getToken(LuaParser.COL, 0); }
		public FunctioncallContext functioncall() {
			return getRuleContext(FunctioncallContext.class,0);
		}
		public FunctioncallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functioncall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFunctioncall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFunctioncall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitFunctioncall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctioncallContext functioncall() throws RecognitionException {
		return functioncall(0);
	}

	private FunctioncallContext functioncall(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FunctioncallContext _localctx = new FunctioncallContext(_ctx, _parentState);
		FunctioncallContext _prevctx = _localctx;
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_functioncall, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(403);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(341);
				match(NAME);
				setState(350);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(348);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(342);
						match(OB);
						setState(343);
						exp(0);
						setState(344);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(346);
						match(DOT);
						setState(347);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(352);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(353);
				args();
				}
				break;
			case 2:
				{
				setState(354);
				match(OP);
				setState(355);
				exp(0);
				setState(356);
				match(CP);
				setState(365);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(363);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(357);
						match(OB);
						setState(358);
						exp(0);
						setState(359);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(361);
						match(DOT);
						setState(362);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(367);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(368);
				args();
				}
				break;
			case 3:
				{
				setState(370);
				match(NAME);
				setState(379);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(377);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(371);
						match(OB);
						setState(372);
						exp(0);
						setState(373);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(375);
						match(DOT);
						setState(376);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(381);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(382);
				match(COL);
				setState(383);
				match(NAME);
				setState(384);
				args();
				}
				break;
			case 4:
				{
				setState(385);
				match(OP);
				setState(386);
				exp(0);
				setState(387);
				match(CP);
				setState(396);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(394);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(388);
						match(OB);
						setState(389);
						exp(0);
						setState(390);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(392);
						match(DOT);
						setState(393);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(398);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(399);
				match(COL);
				setState(400);
				match(NAME);
				setState(401);
				args();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(435);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(433);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
					case 1:
						{
						_localctx = new FunctioncallContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_functioncall);
						setState(405);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(414);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==DOT || _la==OB) {
							{
							setState(412);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
							case OB:
								{
								setState(406);
								match(OB);
								setState(407);
								exp(0);
								setState(408);
								match(CB);
								}
								break;
							case DOT:
								{
								setState(410);
								match(DOT);
								setState(411);
								match(NAME);
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							setState(416);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(417);
						args();
						}
						break;
					case 2:
						{
						_localctx = new FunctioncallContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_functioncall);
						setState(418);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(427);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==DOT || _la==OB) {
							{
							setState(425);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
							case OB:
								{
								setState(419);
								match(OB);
								setState(420);
								exp(0);
								setState(421);
								match(CB);
								}
								break;
							case DOT:
								{
								setState(423);
								match(DOT);
								setState(424);
								match(NAME);
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							setState(429);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(430);
						match(COL);
						setState(431);
						match(NAME);
						setState(432);
						args();
						}
						break;
					}
					} 
				}
				setState(437);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArgsContext extends ParserRuleContext {
		public TerminalNode OP() { return getToken(LuaParser.OP, 0); }
		public TerminalNode CP() { return getToken(LuaParser.CP, 0); }
		public ExplistContext explist() {
			return getRuleContext(ExplistContext.class,0);
		}
		public TableconstructorContext tableconstructor() {
			return getRuleContext(TableconstructorContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_args);
		int _la;
		try {
			setState(445);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OP:
				enterOuterAlt(_localctx, 1);
				{
				setState(438);
				match(OP);
				setState(440);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 140189884220151L) != 0)) {
					{
					setState(439);
					explist();
					}
				}

				setState(442);
				match(CP);
				}
				break;
			case OCU:
				enterOuterAlt(_localctx, 2);
				{
				setState(443);
				tableconstructor();
				}
				break;
			case NORMALSTRING:
			case CHARSTRING:
			case LONGSTRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(444);
				string();
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

	@SuppressWarnings("CheckReturnValue")
	public static class FuncbodyContext extends ParserRuleContext {
		public TerminalNode OP() { return getToken(LuaParser.OP, 0); }
		public ParlistContext parlist() {
			return getRuleContext(ParlistContext.class,0);
		}
		public TerminalNode CP() { return getToken(LuaParser.CP, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode END() { return getToken(LuaParser.END, 0); }
		public TerminalNode COL() { return getToken(LuaParser.COL, 0); }
		public TypehintContext typehint() {
			return getRuleContext(TypehintContext.class,0);
		}
		public FuncbodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcbody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFuncbody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFuncbody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitFuncbody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncbodyContext funcbody() throws RecognitionException {
		FuncbodyContext _localctx = new FuncbodyContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_funcbody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			match(OP);
			setState(448);
			parlist();
			setState(449);
			match(CP);
			setState(452);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COL) {
				{
				setState(450);
				match(COL);
				setState(451);
				typehint();
				}
			}

			setState(454);
			block();
			setState(455);
			match(END);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ParlistContext extends ParserRuleContext {
		public NamelistContext namelist() {
			return getRuleContext(NamelistContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(LuaParser.COMMA, 0); }
		public TerminalNode DDD() { return getToken(LuaParser.DDD, 0); }
		public ParlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterParlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitParlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitParlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParlistContext parlist() throws RecognitionException {
		ParlistContext _localctx = new ParlistContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_parlist);
		int _la;
		try {
			setState(464);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(457);
				namelist();
				setState(460);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(458);
					match(COMMA);
					setState(459);
					match(DDD);
					}
				}

				}
				break;
			case DDD:
				enterOuterAlt(_localctx, 2);
				{
				setState(462);
				match(DDD);
				}
				break;
			case CP:
				enterOuterAlt(_localctx, 3);
				{
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

	@SuppressWarnings("CheckReturnValue")
	public static class TableconstructorContext extends ParserRuleContext {
		public TerminalNode OCU() { return getToken(LuaParser.OCU, 0); }
		public TerminalNode CCU() { return getToken(LuaParser.CCU, 0); }
		public FieldlistContext fieldlist() {
			return getRuleContext(FieldlistContext.class,0);
		}
		public TableconstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableconstructor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterTableconstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitTableconstructor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitTableconstructor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableconstructorContext tableconstructor() throws RecognitionException {
		TableconstructorContext _localctx = new TableconstructorContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_tableconstructor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			match(OCU);
			setState(468);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 24)) & ~0x3f) == 0 && ((1L << (_la - 24)) & 140189900997367L) != 0)) {
				{
				setState(467);
				fieldlist();
				}
			}

			setState(470);
			match(CCU);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FieldlistContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<FieldsepContext> fieldsep() {
			return getRuleContexts(FieldsepContext.class);
		}
		public FieldsepContext fieldsep(int i) {
			return getRuleContext(FieldsepContext.class,i);
		}
		public FieldlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFieldlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFieldlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitFieldlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldlistContext fieldlist() throws RecognitionException {
		FieldlistContext _localctx = new FieldlistContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_fieldlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			field();
			setState(478);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(473);
					fieldsep();
					setState(474);
					field();
					}
					} 
				}
				setState(480);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			}
			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI || _la==COMMA) {
				{
				setState(481);
				fieldsep();
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

	@SuppressWarnings("CheckReturnValue")
	public static class FieldContext extends ParserRuleContext {
		public TerminalNode OB() { return getToken(LuaParser.OB, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode CB() { return getToken(LuaParser.CB, 0); }
		public TerminalNode EQ() { return getToken(LuaParser.EQ, 0); }
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_field);
		try {
			setState(494);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(484);
				match(OB);
				setState(485);
				exp(0);
				setState(486);
				match(CB);
				setState(487);
				match(EQ);
				setState(488);
				exp(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(490);
				match(NAME);
				setState(491);
				match(EQ);
				setState(492);
				exp(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(493);
				exp(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FieldsepContext extends ParserRuleContext {
		public TerminalNode COMMA() { return getToken(LuaParser.COMMA, 0); }
		public TerminalNode SEMI() { return getToken(LuaParser.SEMI, 0); }
		public FieldsepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldsep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFieldsep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFieldsep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitFieldsep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldsepContext fieldsep() throws RecognitionException {
		FieldsepContext _localctx = new FieldsepContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_fieldsep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496);
			_la = _input.LA(1);
			if ( !(_la==SEMI || _la==COMMA) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(LuaParser.INT, 0); }
		public TerminalNode HEX() { return getToken(LuaParser.HEX, 0); }
		public TerminalNode FLOAT() { return getToken(LuaParser.FLOAT, 0); }
		public TerminalNode HEX_FLOAT() { return getToken(LuaParser.HEX_FLOAT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498);
			_la = _input.LA(1);
			if ( !(((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & 15L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public TerminalNode NORMALSTRING() { return getToken(LuaParser.NORMALSTRING, 0); }
		public TerminalNode CHARSTRING() { return getToken(LuaParser.CHARSTRING, 0); }
		public TerminalNode LONGSTRING() { return getToken(LuaParser.LONGSTRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			_la = _input.LA(1);
			if ( !(((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 7L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypehintContext extends ParserRuleContext {
		public TerminalNode TYPE_I() { return getToken(LuaParser.TYPE_I, 0); }
		public TerminalNode TYPE_F() { return getToken(LuaParser.TYPE_F, 0); }
		public TerminalNode TYPE_S() { return getToken(LuaParser.TYPE_S, 0); }
		public TerminalNode TYPE_B() { return getToken(LuaParser.TYPE_B, 0); }
		public TerminalNode TYPE_V() { return getToken(LuaParser.TYPE_V, 0); }
		public TerminalNode TYPE_T() { return getToken(LuaParser.TYPE_T, 0); }
		public TypehintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typehint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterTypehint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitTypehint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitTypehint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypehintContext typehint() throws RecognitionException {
		TypehintContext _localctx = new TypehintContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_typehint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 9079256848778919936L) != 0)) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 17:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 27:
			return functioncall_sempred((FunctioncallContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean functioncall_sempred(FunctioncallContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001J\u01f9\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0005\u0002S\b\u0002\n\u0002\f\u0002"+
		"V\t\u0002\u0001\u0002\u0003\u0002Y\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003c\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004n\b"+
		"\u0004\n\u0004\f\u0004q\t\u0004\u0001\u0004\u0001\u0004\u0003\u0004u\b"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0087"+
		"\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u0095\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u009f"+
		"\b\u0007\u0003\u0007\u00a1\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b\u00ab\b\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0005\t\u00b2\b\t\n\t\f\t\u00b5\t\t\u0001\n\u0001\n\u0001\n"+
		"\u0003\n\u00ba\b\n\u0001\u000b\u0001\u000b\u0003\u000b\u00be\b\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00c2\b\u000b\u0001\u000b\u0003\u000b\u00c5"+
		"\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0005"+
		"\r\u00ce\b\r\n\r\f\r\u00d1\t\r\u0001\r\u0001\r\u0003\r\u00d5\b\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00da\b\u000e\n\u000e\f\u000e"+
		"\u00dd\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00e2\b"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00e8"+
		"\b\u000f\u0005\u000f\u00ea\b\u000f\n\u000f\f\u000f\u00ed\t\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00f2\b\u0010\n\u0010\f\u0010"+
		"\u00f5\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u0103\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0005\u0011\u0109\b\u0011\n\u0011\f\u0011\u010c\t\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0113"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0129\b\u0019\u0003\u0019\u012b"+
		"\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0005\u001a\u0134\b\u001a\n\u001a\f\u001a\u0137\t\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0005\u001a\u0140\b\u001a\n\u001a\f\u001a\u0143\t\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u014e\b\u001a\n\u001a\f\u001a"+
		"\u0151\t\u001a\u0003\u001a\u0153\b\u001a\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005"+
		"\u001b\u015d\b\u001b\n\u001b\f\u001b\u0160\t\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0005\u001b\u016c\b\u001b\n\u001b\f\u001b\u016f"+
		"\t\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u017a\b\u001b\n"+
		"\u001b\f\u001b\u017d\t\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u018b\b\u001b\n\u001b\f\u001b"+
		"\u018e\t\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b"+
		"\u0194\b\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0005\u001b\u019d\b\u001b\n\u001b\f\u001b\u01a0"+
		"\t\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u01aa\b\u001b\n\u001b\f\u001b"+
		"\u01ad\t\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u01b2\b"+
		"\u001b\n\u001b\f\u001b\u01b5\t\u001b\u0001\u001c\u0001\u001c\u0003\u001c"+
		"\u01b9\b\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u01be\b"+
		"\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003"+
		"\u001d\u01c5\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u01cd\b\u001e\u0001\u001e\u0001\u001e\u0003"+
		"\u001e\u01d1\b\u001e\u0001\u001f\u0001\u001f\u0003\u001f\u01d5\b\u001f"+
		"\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0005 \u01dd\b \n"+
		" \f \u01e0\t \u0001 \u0003 \u01e3\b \u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0003!\u01ef\b!\u0001\"\u0001\"\u0001"+
		"#\u0001#\u0001$\u0001$\u0001%\u0001%\u0001%\u0000\u0002\"6&\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02468:<>@BDFHJ\u0000\n\u0003\u0000%&--66\u0002\u0000\u001d\u001d"+
		",,\u0004\u0000\u0013\u0014()2288\u0001\u0000*+\u0003\u0000\u001c\u001c"+
		"\"$44\u0002\u0000\u001c\u001e!!\u0002\u0000\u0001\u0001\u000f\u000f\u0001"+
		"\u0000CF\u0001\u0000@B\u0001\u00009>\u0223\u0000L\u0001\u0000\u0000\u0000"+
		"\u0002O\u0001\u0000\u0000\u0000\u0004T\u0001\u0000\u0000\u0000\u0006b"+
		"\u0001\u0000\u0000\u0000\bd\u0001\u0000\u0000\u0000\nx\u0001\u0000\u0000"+
		"\u0000\f\u0094\u0001\u0000\u0000\u0000\u000e\u00a0\u0001\u0000\u0000\u0000"+
		"\u0010\u00aa\u0001\u0000\u0000\u0000\u0012\u00ac\u0001\u0000\u0000\u0000"+
		"\u0014\u00b9\u0001\u0000\u0000\u0000\u0016\u00c1\u0001\u0000\u0000\u0000"+
		"\u0018\u00c6\u0001\u0000\u0000\u0000\u001a\u00ca\u0001\u0000\u0000\u0000"+
		"\u001c\u00d6\u0001\u0000\u0000\u0000\u001e\u00de\u0001\u0000\u0000\u0000"+
		" \u00ee\u0001\u0000\u0000\u0000\"\u0102\u0001\u0000\u0000\u0000$\u0112"+
		"\u0001\u0000\u0000\u0000&\u0114\u0001\u0000\u0000\u0000(\u0116\u0001\u0000"+
		"\u0000\u0000*\u0118\u0001\u0000\u0000\u0000,\u011a\u0001\u0000\u0000\u0000"+
		".\u011c\u0001\u0000\u0000\u00000\u011e\u0001\u0000\u0000\u00002\u012a"+
		"\u0001\u0000\u0000\u00004\u0152\u0001\u0000\u0000\u00006\u0193\u0001\u0000"+
		"\u0000\u00008\u01bd\u0001\u0000\u0000\u0000:\u01bf\u0001\u0000\u0000\u0000"+
		"<\u01d0\u0001\u0000\u0000\u0000>\u01d2\u0001\u0000\u0000\u0000@\u01d8"+
		"\u0001\u0000\u0000\u0000B\u01ee\u0001\u0000\u0000\u0000D\u01f0\u0001\u0000"+
		"\u0000\u0000F\u01f2\u0001\u0000\u0000\u0000H\u01f4\u0001\u0000\u0000\u0000"+
		"J\u01f6\u0001\u0000\u0000\u0000LM\u0003\u0002\u0001\u0000MN\u0005\u0000"+
		"\u0000\u0001N\u0001\u0001\u0000\u0000\u0000OP\u0003\u0004\u0002\u0000"+
		"P\u0003\u0001\u0000\u0000\u0000QS\u0003\u0006\u0003\u0000RQ\u0001\u0000"+
		"\u0000\u0000SV\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001"+
		"\u0000\u0000\u0000UX\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000"+
		"WY\u0003\u0016\u000b\u0000XW\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000"+
		"\u0000Y\u0005\u0001\u0000\u0000\u0000Zc\u0005\u0001\u0000\u0000[c\u0003"+
		"\u000e\u0007\u0000\\c\u00036\u001b\u0000]c\u0005\u0003\u0000\u0000^c\u0003"+
		"\n\u0005\u0000_c\u0003\b\u0004\u0000`c\u0003\f\u0006\u0000ac\u0003\u0010"+
		"\b\u0000bZ\u0001\u0000\u0000\u0000b[\u0001\u0000\u0000\u0000b\\\u0001"+
		"\u0000\u0000\u0000b]\u0001\u0000\u0000\u0000b^\u0001\u0000\u0000\u0000"+
		"b_\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000ba\u0001\u0000\u0000"+
		"\u0000c\u0007\u0001\u0000\u0000\u0000de\u0005\n\u0000\u0000ef\u0003\""+
		"\u0011\u0000fg\u0005\u000b\u0000\u0000go\u0003\u0004\u0002\u0000hi\u0005"+
		"\f\u0000\u0000ij\u0003\"\u0011\u0000jk\u0005\u000b\u0000\u0000kl\u0003"+
		"\u0004\u0002\u0000ln\u0001\u0000\u0000\u0000mh\u0001\u0000\u0000\u0000"+
		"nq\u0001\u0000\u0000\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000"+
		"\u0000pt\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000rs\u0005\r\u0000"+
		"\u0000su\u0003\u0004\u0002\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000"+
		"\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0005\u0006\u0000\u0000w\t\u0001"+
		"\u0000\u0000\u0000xy\u0005\u0007\u0000\u0000yz\u0003\"\u0011\u0000z{\u0005"+
		"\u0005\u0000\u0000{|\u0003\u0004\u0002\u0000|}\u0005\u0006\u0000\u0000"+
		"}\u000b\u0001\u0000\u0000\u0000~\u007f\u0005\u000e\u0000\u0000\u007f\u0080"+
		"\u0005?\u0000\u0000\u0080\u0081\u0005\u0002\u0000\u0000\u0081\u0082\u0003"+
		"\"\u0011\u0000\u0082\u0083\u0005\u000f\u0000\u0000\u0083\u0086\u0003\""+
		"\u0011\u0000\u0084\u0085\u0005\u000f\u0000\u0000\u0085\u0087\u0003\"\u0011"+
		"\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0005\u0005\u0000"+
		"\u0000\u0089\u008a\u0003\u0004\u0002\u0000\u008a\u008b\u0005\u0006\u0000"+
		"\u0000\u008b\u0095\u0001\u0000\u0000\u0000\u008c\u008d\u0005\u000e\u0000"+
		"\u0000\u008d\u008e\u0003\u001e\u000f\u0000\u008e\u008f\u0005\u0010\u0000"+
		"\u0000\u008f\u0090\u0003 \u0010\u0000\u0090\u0091\u0005\u0005\u0000\u0000"+
		"\u0091\u0092\u0003\u0004\u0002\u0000\u0092\u0093\u0005\u0006\u0000\u0000"+
		"\u0093\u0095\u0001\u0000\u0000\u0000\u0094~\u0001\u0000\u0000\u0000\u0094"+
		"\u008c\u0001\u0000\u0000\u0000\u0095\r\u0001\u0000\u0000\u0000\u0096\u0097"+
		"\u0003\u001c\u000e\u0000\u0097\u0098\u0005\u0002\u0000\u0000\u0098\u0099"+
		"\u0003 \u0010\u0000\u0099\u00a1\u0001\u0000\u0000\u0000\u009a\u009b\u0005"+
		"\u0012\u0000\u0000\u009b\u009e\u0003\u0012\t\u0000\u009c\u009d\u0005\u0002"+
		"\u0000\u0000\u009d\u009f\u0003 \u0010\u0000\u009e\u009c\u0001\u0000\u0000"+
		"\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u00a1\u0001\u0000\u0000"+
		"\u0000\u00a0\u0096\u0001\u0000\u0000\u0000\u00a0\u009a\u0001\u0000\u0000"+
		"\u0000\u00a1\u000f\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005\u0011\u0000"+
		"\u0000\u00a3\u00a4\u0003\u001a\r\u0000\u00a4\u00a5\u0003:\u001d\u0000"+
		"\u00a5\u00ab\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005\u0012\u0000\u0000"+
		"\u00a7\u00a8\u0005\u0011\u0000\u0000\u00a8\u00a9\u0005?\u0000\u0000\u00a9"+
		"\u00ab\u0003:\u001d\u0000\u00aa\u00a2\u0001\u0000\u0000\u0000\u00aa\u00a6"+
		"\u0001\u0000\u0000\u0000\u00ab\u0011\u0001\u0000\u0000\u0000\u00ac\u00ad"+
		"\u0005?\u0000\u0000\u00ad\u00b3\u0003\u0014\n\u0000\u00ae\u00af\u0005"+
		"\u000f\u0000\u0000\u00af\u00b0\u0005?\u0000\u0000\u00b0\u00b2\u0003\u0014"+
		"\n\u0000\u00b1\u00ae\u0001\u0000\u0000\u0000\u00b2\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b4\u0013\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000"+
		"\u0000\u00b6\u00b7\u0005\u0013\u0000\u0000\u00b7\u00b8\u0005?\u0000\u0000"+
		"\u00b8\u00ba\u0005\u0014\u0000\u0000\u00b9\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u0015\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bd\u0005\u0015\u0000\u0000\u00bc\u00be\u0003 \u0010\u0000\u00bd"+
		"\u00bc\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be"+
		"\u00c2\u0001\u0000\u0000\u0000\u00bf\u00c2\u0005\u0003\u0000\u0000\u00c0"+
		"\u00c2\u0005\u0016\u0000\u0000\u00c1\u00bb\u0001\u0000\u0000\u0000\u00c1"+
		"\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c3\u00c5\u0005\u0001\u0000\u0000\u00c4"+
		"\u00c3\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5"+
		"\u0017\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005\u0017\u0000\u0000\u00c7"+
		"\u00c8\u0005?\u0000\u0000\u00c8\u00c9\u0005\u0017\u0000\u0000\u00c9\u0019"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cf\u0005?\u0000\u0000\u00cb\u00cc\u0005"+
		"\u001b\u0000\u0000\u00cc\u00ce\u0005?\u0000\u0000\u00cd\u00cb\u0001\u0000"+
		"\u0000\u0000\u00ce\u00d1\u0001\u0000\u0000\u0000\u00cf\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u00d4\u0001\u0000"+
		"\u0000\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005\'\u0000"+
		"\u0000\u00d3\u00d5\u0005?\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u001b\u0001\u0000\u0000\u0000"+
		"\u00d6\u00db\u00032\u0019\u0000\u00d7\u00d8\u0005\u000f\u0000\u0000\u00d8"+
		"\u00da\u00032\u0019\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00da\u00dd"+
		"\u0001\u0000\u0000\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00db\u00dc"+
		"\u0001\u0000\u0000\u0000\u00dc\u001d\u0001\u0000\u0000\u0000\u00dd\u00db"+
		"\u0001\u0000\u0000\u0000\u00de\u00e1\u0005?\u0000\u0000\u00df\u00e0\u0005"+
		"\'\u0000\u0000\u00e0\u00e2\u0003J%\u0000\u00e1\u00df\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00eb\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e4\u0005\u000f\u0000\u0000\u00e4\u00e7\u0005?\u0000\u0000"+
		"\u00e5\u00e6\u0005\'\u0000\u0000\u00e6\u00e8\u0003J%\u0000\u00e7\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00ea"+
		"\u0001\u0000\u0000\u0000\u00e9\u00e3\u0001\u0000\u0000\u0000\u00ea\u00ed"+
		"\u0001\u0000\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00eb\u00ec"+
		"\u0001\u0000\u0000\u0000\u00ec\u001f\u0001\u0000\u0000\u0000\u00ed\u00eb"+
		"\u0001\u0000\u0000\u0000\u00ee\u00f3\u0003\"\u0011\u0000\u00ef\u00f0\u0005"+
		"\u000f\u0000\u0000\u00f0\u00f2\u0003\"\u0011\u0000\u00f1\u00ef\u0001\u0000"+
		"\u0000\u0000\u00f2\u00f5\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4!\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f6\u00f7\u0006\u0011\uffff"+
		"\uffff\u0000\u00f7\u0103\u0005\u0018\u0000\u0000\u00f8\u0103\u0005\u0019"+
		"\u0000\u0000\u00f9\u0103\u0005\u001a\u0000\u0000\u00fa\u0103\u0003F#\u0000"+
		"\u00fb\u0103\u0003H$\u0000\u00fc\u0103\u00057\u0000\u0000\u00fd\u0103"+
		"\u00034\u001a\u0000\u00fe\u0103\u0003>\u001f\u0000\u00ff\u0100\u00030"+
		"\u0018\u0000\u0100\u0101\u0003\"\u0011\u0002\u0101\u0103\u0001\u0000\u0000"+
		"\u0000\u0102\u00f6\u0001\u0000\u0000\u0000\u0102\u00f8\u0001\u0000\u0000"+
		"\u0000\u0102\u00f9\u0001\u0000\u0000\u0000\u0102\u00fa\u0001\u0000\u0000"+
		"\u0000\u0102\u00fb\u0001\u0000\u0000\u0000\u0102\u00fc\u0001\u0000\u0000"+
		"\u0000\u0102\u00fd\u0001\u0000\u0000\u0000\u0102\u00fe\u0001\u0000\u0000"+
		"\u0000\u0102\u00ff\u0001\u0000\u0000\u0000\u0103\u010a\u0001\u0000\u0000"+
		"\u0000\u0104\u0105\n\u0001\u0000\u0000\u0105\u0106\u0003$\u0012\u0000"+
		"\u0106\u0107\u0003\"\u0011\u0002\u0107\u0109\u0001\u0000\u0000\u0000\u0108"+
		"\u0104\u0001\u0000\u0000\u0000\u0109\u010c\u0001\u0000\u0000\u0000\u010a"+
		"\u0108\u0001\u0000\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b"+
		"#\u0001\u0000\u0000\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010d\u0113"+
		"\u0003&\u0013\u0000\u010e\u0113\u0003(\u0014\u0000\u010f\u0113\u0003*"+
		"\u0015\u0000\u0110\u0113\u0003,\u0016\u0000\u0111\u0113\u0003.\u0017\u0000"+
		"\u0112\u010d\u0001\u0000\u0000\u0000\u0112\u010e\u0001\u0000\u0000\u0000"+
		"\u0112\u010f\u0001\u0000\u0000\u0000\u0112\u0110\u0001\u0000\u0000\u0000"+
		"\u0112\u0111\u0001\u0000\u0000\u0000\u0113%\u0001\u0000\u0000\u0000\u0114"+
		"\u0115\u0007\u0000\u0000\u0000\u0115\'\u0001\u0000\u0000\u0000\u0116\u0117"+
		"\u0007\u0001\u0000\u0000\u0117)\u0001\u0000\u0000\u0000\u0118\u0119\u0007"+
		"\u0002\u0000\u0000\u0119+\u0001\u0000\u0000\u0000\u011a\u011b\u0007\u0003"+
		"\u0000\u0000\u011b-\u0001\u0000\u0000\u0000\u011c\u011d\u0007\u0004\u0000"+
		"\u0000\u011d/\u0001\u0000\u0000\u0000\u011e\u011f\u0007\u0005\u0000\u0000"+
		"\u011f1\u0001\u0000\u0000\u0000\u0120\u012b\u0005?\u0000\u0000\u0121\u0128"+
		"\u00034\u001a\u0000\u0122\u0123\u00050\u0000\u0000\u0123\u0124\u0003\""+
		"\u0011\u0000\u0124\u0125\u00051\u0000\u0000\u0125\u0129\u0001\u0000\u0000"+
		"\u0000\u0126\u0127\u0005\u001b\u0000\u0000\u0127\u0129\u0005?\u0000\u0000"+
		"\u0128\u0122\u0001\u0000\u0000\u0000\u0128\u0126\u0001\u0000\u0000\u0000"+
		"\u0129\u012b\u0001\u0000\u0000\u0000\u012a\u0120\u0001\u0000\u0000\u0000"+
		"\u012a\u0121\u0001\u0000\u0000\u0000\u012b3\u0001\u0000\u0000\u0000\u012c"+
		"\u0135\u0005?\u0000\u0000\u012d\u012e\u00050\u0000\u0000\u012e\u012f\u0003"+
		"\"\u0011\u0000\u012f\u0130\u00051\u0000\u0000\u0130\u0134\u0001\u0000"+
		"\u0000\u0000\u0131\u0132\u0005\u001b\u0000\u0000\u0132\u0134\u0005?\u0000"+
		"\u0000\u0133\u012d\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000"+
		"\u0000\u0134\u0137\u0001\u0000\u0000\u0000\u0135\u0133\u0001\u0000\u0000"+
		"\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136\u0153\u0001\u0000\u0000"+
		"\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0138\u0141\u00036\u001b\u0000"+
		"\u0139\u013a\u00050\u0000\u0000\u013a\u013b\u0003\"\u0011\u0000\u013b"+
		"\u013c\u00051\u0000\u0000\u013c\u0140\u0001\u0000\u0000\u0000\u013d\u013e"+
		"\u0005\u001b\u0000\u0000\u013e\u0140\u0005?\u0000\u0000\u013f\u0139\u0001"+
		"\u0000\u0000\u0000\u013f\u013d\u0001\u0000\u0000\u0000\u0140\u0143\u0001"+
		"\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000\u0000\u0141\u0142\u0001"+
		"\u0000\u0000\u0000\u0142\u0153\u0001\u0000\u0000\u0000\u0143\u0141\u0001"+
		"\u0000\u0000\u0000\u0144\u0145\u0005\u001f\u0000\u0000\u0145\u0146\u0003"+
		"\"\u0011\u0000\u0146\u014f\u0005 \u0000\u0000\u0147\u0148\u00050\u0000"+
		"\u0000\u0148\u0149\u0003\"\u0011\u0000\u0149\u014a\u00051\u0000\u0000"+
		"\u014a\u014e\u0001\u0000\u0000\u0000\u014b\u014c\u0005\u001b\u0000\u0000"+
		"\u014c\u014e\u0005?\u0000\u0000\u014d\u0147\u0001\u0000\u0000\u0000\u014d"+
		"\u014b\u0001\u0000\u0000\u0000\u014e\u0151\u0001\u0000\u0000\u0000\u014f"+
		"\u014d\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150"+
		"\u0153\u0001\u0000\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0152"+
		"\u012c\u0001\u0000\u0000\u0000\u0152\u0138\u0001\u0000\u0000\u0000\u0152"+
		"\u0144\u0001\u0000\u0000\u0000\u01535\u0001\u0000\u0000\u0000\u0154\u0155"+
		"\u0006\u001b\uffff\uffff\u0000\u0155\u015e\u0005?\u0000\u0000\u0156\u0157"+
		"\u00050\u0000\u0000\u0157\u0158\u0003\"\u0011\u0000\u0158\u0159\u0005"+
		"1\u0000\u0000\u0159\u015d\u0001\u0000\u0000\u0000\u015a\u015b\u0005\u001b"+
		"\u0000\u0000\u015b\u015d\u0005?\u0000\u0000\u015c\u0156\u0001\u0000\u0000"+
		"\u0000\u015c\u015a\u0001\u0000\u0000\u0000\u015d\u0160\u0001\u0000\u0000"+
		"\u0000\u015e\u015c\u0001\u0000\u0000\u0000\u015e\u015f\u0001\u0000\u0000"+
		"\u0000\u015f\u0161\u0001\u0000\u0000\u0000\u0160\u015e\u0001\u0000\u0000"+
		"\u0000\u0161\u0194\u00038\u001c\u0000\u0162\u0163\u0005\u001f\u0000\u0000"+
		"\u0163\u0164\u0003\"\u0011\u0000\u0164\u016d\u0005 \u0000\u0000\u0165"+
		"\u0166\u00050\u0000\u0000\u0166\u0167\u0003\"\u0011\u0000\u0167\u0168"+
		"\u00051\u0000\u0000\u0168\u016c\u0001\u0000\u0000\u0000\u0169\u016a\u0005"+
		"\u001b\u0000\u0000\u016a\u016c\u0005?\u0000\u0000\u016b\u0165\u0001\u0000"+
		"\u0000\u0000\u016b\u0169\u0001\u0000\u0000\u0000\u016c\u016f\u0001\u0000"+
		"\u0000\u0000\u016d\u016b\u0001\u0000\u0000\u0000\u016d\u016e\u0001\u0000"+
		"\u0000\u0000\u016e\u0170\u0001\u0000\u0000\u0000\u016f\u016d\u0001\u0000"+
		"\u0000\u0000\u0170\u0171\u00038\u001c\u0000\u0171\u0194\u0001\u0000\u0000"+
		"\u0000\u0172\u017b\u0005?\u0000\u0000\u0173\u0174\u00050\u0000\u0000\u0174"+
		"\u0175\u0003\"\u0011\u0000\u0175\u0176\u00051\u0000\u0000\u0176\u017a"+
		"\u0001\u0000\u0000\u0000\u0177\u0178\u0005\u001b\u0000\u0000\u0178\u017a"+
		"\u0005?\u0000\u0000\u0179\u0173\u0001\u0000\u0000\u0000\u0179\u0177\u0001"+
		"\u0000\u0000\u0000\u017a\u017d\u0001\u0000\u0000\u0000\u017b\u0179\u0001"+
		"\u0000\u0000\u0000\u017b\u017c\u0001\u0000\u0000\u0000\u017c\u017e\u0001"+
		"\u0000\u0000\u0000\u017d\u017b\u0001\u0000\u0000\u0000\u017e\u017f\u0005"+
		"\'\u0000\u0000\u017f\u0180\u0005?\u0000\u0000\u0180\u0194\u00038\u001c"+
		"\u0000\u0181\u0182\u0005\u001f\u0000\u0000\u0182\u0183\u0003\"\u0011\u0000"+
		"\u0183\u018c\u0005 \u0000\u0000\u0184\u0185\u00050\u0000\u0000\u0185\u0186"+
		"\u0003\"\u0011\u0000\u0186\u0187\u00051\u0000\u0000\u0187\u018b\u0001"+
		"\u0000\u0000\u0000\u0188\u0189\u0005\u001b\u0000\u0000\u0189\u018b\u0005"+
		"?\u0000\u0000\u018a\u0184\u0001\u0000\u0000\u0000\u018a\u0188\u0001\u0000"+
		"\u0000\u0000\u018b\u018e\u0001\u0000\u0000\u0000\u018c\u018a\u0001\u0000"+
		"\u0000\u0000\u018c\u018d\u0001\u0000\u0000\u0000\u018d\u018f\u0001\u0000"+
		"\u0000\u0000\u018e\u018c\u0001\u0000\u0000\u0000\u018f\u0190\u0005\'\u0000"+
		"\u0000\u0190\u0191\u0005?\u0000\u0000\u0191\u0192\u00038\u001c\u0000\u0192"+
		"\u0194\u0001\u0000\u0000\u0000\u0193\u0154\u0001\u0000\u0000\u0000\u0193"+
		"\u0162\u0001\u0000\u0000\u0000\u0193\u0172\u0001\u0000\u0000\u0000\u0193"+
		"\u0181\u0001\u0000\u0000\u0000\u0194\u01b3\u0001\u0000\u0000\u0000\u0195"+
		"\u019e\n\u0005\u0000\u0000\u0196\u0197\u00050\u0000\u0000\u0197\u0198"+
		"\u0003\"\u0011\u0000\u0198\u0199\u00051\u0000\u0000\u0199\u019d\u0001"+
		"\u0000\u0000\u0000\u019a\u019b\u0005\u001b\u0000\u0000\u019b\u019d\u0005"+
		"?\u0000\u0000\u019c\u0196\u0001\u0000\u0000\u0000\u019c\u019a\u0001\u0000"+
		"\u0000\u0000\u019d\u01a0\u0001\u0000\u0000\u0000\u019e\u019c\u0001\u0000"+
		"\u0000\u0000\u019e\u019f\u0001\u0000\u0000\u0000\u019f\u01a1\u0001\u0000"+
		"\u0000\u0000\u01a0\u019e\u0001\u0000\u0000\u0000\u01a1\u01b2\u00038\u001c"+
		"\u0000\u01a2\u01ab\n\u0002\u0000\u0000\u01a3\u01a4\u00050\u0000\u0000"+
		"\u01a4\u01a5\u0003\"\u0011\u0000\u01a5\u01a6\u00051\u0000\u0000\u01a6"+
		"\u01aa\u0001\u0000\u0000\u0000\u01a7\u01a8\u0005\u001b\u0000\u0000\u01a8"+
		"\u01aa\u0005?\u0000\u0000\u01a9\u01a3\u0001\u0000\u0000\u0000\u01a9\u01a7"+
		"\u0001\u0000\u0000\u0000\u01aa\u01ad\u0001\u0000\u0000\u0000\u01ab\u01a9"+
		"\u0001\u0000\u0000\u0000\u01ab\u01ac\u0001\u0000\u0000\u0000\u01ac\u01ae"+
		"\u0001\u0000\u0000\u0000\u01ad\u01ab\u0001\u0000\u0000\u0000\u01ae\u01af"+
		"\u0005\'\u0000\u0000\u01af\u01b0\u0005?\u0000\u0000\u01b0\u01b2\u0003"+
		"8\u001c\u0000\u01b1\u0195\u0001\u0000\u0000\u0000\u01b1\u01a2\u0001\u0000"+
		"\u0000\u0000\u01b2\u01b5\u0001\u0000\u0000\u0000\u01b3\u01b1\u0001\u0000"+
		"\u0000\u0000\u01b3\u01b4\u0001\u0000\u0000\u0000\u01b47\u0001\u0000\u0000"+
		"\u0000\u01b5\u01b3\u0001\u0000\u0000\u0000\u01b6\u01b8\u0005\u001f\u0000"+
		"\u0000\u01b7\u01b9\u0003 \u0010\u0000\u01b8\u01b7\u0001\u0000\u0000\u0000"+
		"\u01b8\u01b9\u0001\u0000\u0000\u0000\u01b9\u01ba\u0001\u0000\u0000\u0000"+
		"\u01ba\u01be\u0005 \u0000\u0000\u01bb\u01be\u0003>\u001f\u0000\u01bc\u01be"+
		"\u0003H$\u0000\u01bd\u01b6\u0001\u0000\u0000\u0000\u01bd\u01bb\u0001\u0000"+
		"\u0000\u0000\u01bd\u01bc\u0001\u0000\u0000\u0000\u01be9\u0001\u0000\u0000"+
		"\u0000\u01bf\u01c0\u0005\u001f\u0000\u0000\u01c0\u01c1\u0003<\u001e\u0000"+
		"\u01c1\u01c4\u0005 \u0000\u0000\u01c2\u01c3\u0005\'\u0000\u0000\u01c3"+
		"\u01c5\u0003J%\u0000\u01c4\u01c2\u0001\u0000\u0000\u0000\u01c4\u01c5\u0001"+
		"\u0000\u0000\u0000\u01c5\u01c6\u0001\u0000\u0000\u0000\u01c6\u01c7\u0003"+
		"\u0004\u0002\u0000\u01c7\u01c8\u0005\u0006\u0000\u0000\u01c8;\u0001\u0000"+
		"\u0000\u0000\u01c9\u01cc\u0003\u001e\u000f\u0000\u01ca\u01cb\u0005\u000f"+
		"\u0000\u0000\u01cb\u01cd\u00057\u0000\u0000\u01cc\u01ca\u0001\u0000\u0000"+
		"\u0000\u01cc\u01cd\u0001\u0000\u0000\u0000\u01cd\u01d1\u0001\u0000\u0000"+
		"\u0000\u01ce\u01d1\u00057\u0000\u0000\u01cf\u01d1\u0001\u0000\u0000\u0000"+
		"\u01d0\u01c9\u0001\u0000\u0000\u0000\u01d0\u01ce\u0001\u0000\u0000\u0000"+
		"\u01d0\u01cf\u0001\u0000\u0000\u0000\u01d1=\u0001\u0000\u0000\u0000\u01d2"+
		"\u01d4\u0005.\u0000\u0000\u01d3\u01d5\u0003@ \u0000\u01d4\u01d3\u0001"+
		"\u0000\u0000\u0000\u01d4\u01d5\u0001\u0000\u0000\u0000\u01d5\u01d6\u0001"+
		"\u0000\u0000\u0000\u01d6\u01d7\u0005/\u0000\u0000\u01d7?\u0001\u0000\u0000"+
		"\u0000\u01d8\u01de\u0003B!\u0000\u01d9\u01da\u0003D\"\u0000\u01da\u01db"+
		"\u0003B!\u0000\u01db\u01dd\u0001\u0000\u0000\u0000\u01dc\u01d9\u0001\u0000"+
		"\u0000\u0000\u01dd\u01e0\u0001\u0000\u0000\u0000\u01de\u01dc\u0001\u0000"+
		"\u0000\u0000\u01de\u01df\u0001\u0000\u0000\u0000\u01df\u01e2\u0001\u0000"+
		"\u0000\u0000\u01e0\u01de\u0001\u0000\u0000\u0000\u01e1\u01e3\u0003D\""+
		"\u0000\u01e2\u01e1\u0001\u0000\u0000\u0000\u01e2\u01e3\u0001\u0000\u0000"+
		"\u0000\u01e3A\u0001\u0000\u0000\u0000\u01e4\u01e5\u00050\u0000\u0000\u01e5"+
		"\u01e6\u0003\"\u0011\u0000\u01e6\u01e7\u00051\u0000\u0000\u01e7\u01e8"+
		"\u0005\u0002\u0000\u0000\u01e8\u01e9\u0003\"\u0011\u0000\u01e9\u01ef\u0001"+
		"\u0000\u0000\u0000\u01ea\u01eb\u0005?\u0000\u0000\u01eb\u01ec\u0005\u0002"+
		"\u0000\u0000\u01ec\u01ef\u0003\"\u0011\u0000\u01ed\u01ef\u0003\"\u0011"+
		"\u0000\u01ee\u01e4\u0001\u0000\u0000\u0000\u01ee\u01ea\u0001\u0000\u0000"+
		"\u0000\u01ee\u01ed\u0001\u0000\u0000\u0000\u01efC\u0001\u0000\u0000\u0000"+
		"\u01f0\u01f1\u0007\u0006\u0000\u0000\u01f1E\u0001\u0000\u0000\u0000\u01f2"+
		"\u01f3\u0007\u0007\u0000\u0000\u01f3G\u0001\u0000\u0000\u0000\u01f4\u01f5"+
		"\u0007\b\u0000\u0000\u01f5I\u0001\u0000\u0000\u0000\u01f6\u01f7\u0007"+
		"\t\u0000\u0000\u01f7K\u0001\u0000\u0000\u0000:TXbot\u0086\u0094\u009e"+
		"\u00a0\u00aa\u00b3\u00b9\u00bd\u00c1\u00c4\u00cf\u00d4\u00db\u00e1\u00e7"+
		"\u00eb\u00f3\u0102\u010a\u0112\u0128\u012a\u0133\u0135\u013f\u0141\u014d"+
		"\u014f\u0152\u015c\u015e\u016b\u016d\u0179\u017b\u018a\u018c\u0193\u019c"+
		"\u019e\u01a9\u01ab\u01b1\u01b3\u01b8\u01bd\u01c4\u01cc\u01d0\u01d4\u01de"+
		"\u01e2\u01ee";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}