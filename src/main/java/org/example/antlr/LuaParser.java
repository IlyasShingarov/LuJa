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
		SLASH=54, DDD=55, SQEQ=56, NAME=57, NORMALSTRING=58, CHARSTRING=59, LONGSTRING=60, 
		INT=61, HEX=62, FLOAT=63, HEX_FLOAT=64, COMMENT=65, WS=66, NL=67, SHEBANG=68;
	public static final int
		RULE_start_ = 0, RULE_chunk = 1, RULE_block = 2, RULE_stat = 3, RULE_vardecl = 4, 
		RULE_funcdecl = 5, RULE_attnamelist = 6, RULE_attrib = 7, RULE_retstat = 8, 
		RULE_label = 9, RULE_funcname = 10, RULE_varlist = 11, RULE_namelist = 12, 
		RULE_explist = 13, RULE_exp = 14, RULE_binop = 15, RULE_multop = 16, RULE_addop = 17, 
		RULE_relop = 18, RULE_boolop = 19, RULE_bitop = 20, RULE_unop = 21, RULE_var = 22, 
		RULE_prefixexp = 23, RULE_functioncall = 24, RULE_args = 25, RULE_functiondef = 26, 
		RULE_funcbody = 27, RULE_parlist = 28, RULE_tableconstructor = 29, RULE_fieldlist = 30, 
		RULE_field = 31, RULE_fieldsep = 32, RULE_number = 33, RULE_string = 34;
	private static String[] makeRuleNames() {
		return new String[] {
			"start_", "chunk", "block", "stat", "vardecl", "funcdecl", "attnamelist", 
			"attrib", "retstat", "label", "funcname", "varlist", "namelist", "explist", 
			"exp", "binop", "multop", "addop", "relop", "boolop", "bitop", "unop", 
			"var", "prefixexp", "functioncall", "args", "functiondef", "funcbody", 
			"parlist", "tableconstructor", "fieldlist", "field", "fieldsep", "number", 
			"string"
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
			"'..'", "'|'", "'^'", "'/'", "'...'", "'~='"
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
			"OB", "CB", "EE", "DD", "PIPE", "CARET", "SLASH", "DDD", "SQEQ", "NAME", 
			"NORMALSTRING", "CHARSTRING", "LONGSTRING", "INT", "HEX", "FLOAT", "HEX_FLOAT", 
			"COMMENT", "WS", "NL", "SHEBANG"
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
			setState(70);
			chunk();
			setState(71);
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
			setState(73);
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
			setState(78);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(75);
					stat();
					}
					} 
				}
				setState(80);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6291464L) != 0)) {
				{
				setState(81);
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
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode BREAK() { return getToken(LuaParser.BREAK, 0); }
		public TerminalNode WHILE() { return getToken(LuaParser.WHILE, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode DO() { return getToken(LuaParser.DO, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode END() { return getToken(LuaParser.END, 0); }
		public TerminalNode REPEAT() { return getToken(LuaParser.REPEAT, 0); }
		public TerminalNode UNTIL() { return getToken(LuaParser.UNTIL, 0); }
		public TerminalNode IF() { return getToken(LuaParser.IF, 0); }
		public List<TerminalNode> THEN() { return getTokens(LuaParser.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(LuaParser.THEN, i);
		}
		public List<TerminalNode> ELSEIF() { return getTokens(LuaParser.ELSEIF); }
		public TerminalNode ELSEIF(int i) {
			return getToken(LuaParser.ELSEIF, i);
		}
		public TerminalNode ELSE() { return getToken(LuaParser.ELSE, 0); }
		public TerminalNode FOR() { return getToken(LuaParser.FOR, 0); }
		public TerminalNode NAME() { return getToken(LuaParser.NAME, 0); }
		public TerminalNode EQ() { return getToken(LuaParser.EQ, 0); }
		public List<TerminalNode> COMMA() { return getTokens(LuaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LuaParser.COMMA, i);
		}
		public NamelistContext namelist() {
			return getRuleContext(NamelistContext.class,0);
		}
		public TerminalNode IN() { return getToken(LuaParser.IN, 0); }
		public ExplistContext explist() {
			return getRuleContext(ExplistContext.class,0);
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
		int _la;
		try {
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				vardecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				functioncall(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(87);
				label();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(88);
				match(BREAK);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(89);
				match(WHILE);
				setState(90);
				exp(0);
				setState(91);
				match(DO);
				setState(92);
				block();
				setState(93);
				match(END);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(95);
				match(REPEAT);
				setState(96);
				block();
				setState(97);
				match(UNTIL);
				setState(98);
				exp(0);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
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
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(120);
				match(FOR);
				setState(121);
				match(NAME);
				setState(122);
				match(EQ);
				setState(123);
				exp(0);
				setState(124);
				match(COMMA);
				setState(125);
				exp(0);
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(126);
					match(COMMA);
					setState(127);
					exp(0);
					}
				}

				setState(130);
				match(DO);
				setState(131);
				block();
				setState(132);
				match(END);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(134);
				match(FOR);
				setState(135);
				namelist();
				setState(136);
				match(IN);
				setState(137);
				explist();
				setState(138);
				match(DO);
				setState(139);
				block();
				setState(140);
				match(END);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(142);
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
		enterRule(_localctx, 8, RULE_vardecl);
		int _la;
		try {
			setState(155);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OP:
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				varlist();
				setState(146);
				match(EQ);
				setState(147);
				explist();
				}
				break;
			case LOCAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				match(LOCAL);
				setState(150);
				attnamelist();
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(151);
					match(EQ);
					setState(152);
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
		enterRule(_localctx, 10, RULE_funcdecl);
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				match(FUNCTION);
				setState(158);
				funcname();
				setState(159);
				funcbody();
				}
				break;
			case LOCAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				match(LOCAL);
				setState(162);
				match(FUNCTION);
				setState(163);
				match(NAME);
				setState(164);
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
		enterRule(_localctx, 12, RULE_attnamelist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(NAME);
			setState(168);
			attrib();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(169);
				match(COMMA);
				setState(170);
				match(NAME);
				setState(171);
				attrib();
				}
				}
				setState(176);
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
		enterRule(_localctx, 14, RULE_attrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(177);
				match(LT);
				setState(178);
				match(NAME);
				setState(179);
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
		enterRule(_localctx, 16, RULE_retstat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
				{
				setState(182);
				match(RETURN);
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 17)) & ~0x3f) == 0 && ((1L << (_la - 17)) & 280650879957889L) != 0)) {
					{
					setState(183);
					explist();
					}
				}

				}
				break;
			case BREAK:
				{
				setState(186);
				match(BREAK);
				}
				break;
			case CONTINUE:
				{
				setState(187);
				match(CONTINUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(190);
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
		enterRule(_localctx, 18, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(CC);
			setState(194);
			match(NAME);
			setState(195);
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
		enterRule(_localctx, 20, RULE_funcname);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(NAME);
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(198);
				match(DOT);
				setState(199);
				match(NAME);
				}
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COL) {
				{
				setState(205);
				match(COL);
				setState(206);
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
		enterRule(_localctx, 22, RULE_varlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			var();
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(210);
				match(COMMA);
				setState(211);
				var();
				}
				}
				setState(216);
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
		enterRule(_localctx, 24, RULE_namelist);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			match(NAME);
			setState(222);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(218);
					match(COMMA);
					setState(219);
					match(NAME);
					}
					} 
				}
				setState(224);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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
		enterRule(_localctx, 26, RULE_explist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			exp(0);
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(226);
				match(COMMA);
				setState(227);
				exp(0);
				}
				}
				setState(232);
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
		public FunctiondefContext functiondef() {
			return getRuleContext(FunctiondefContext.class,0);
		}
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
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_exp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NIL:
				{
				setState(234);
				match(NIL);
				}
				break;
			case FALSE:
				{
				setState(235);
				match(FALSE);
				}
				break;
			case TRUE:
				{
				setState(236);
				match(TRUE);
				}
				break;
			case INT:
			case HEX:
			case FLOAT:
			case HEX_FLOAT:
				{
				setState(237);
				number();
				}
				break;
			case NORMALSTRING:
			case CHARSTRING:
			case LONGSTRING:
				{
				setState(238);
				string();
				}
				break;
			case DDD:
				{
				setState(239);
				match(DDD);
				}
				break;
			case FUNCTION:
				{
				setState(240);
				functiondef();
				}
				break;
			case OP:
			case NAME:
				{
				setState(241);
				prefixexp();
				}
				break;
			case OCU:
				{
				setState(242);
				tableconstructor();
				}
				break;
			case SQUIG:
			case MINUS:
			case POUND:
			case NOT:
				{
				setState(243);
				unop();
				setState(244);
				exp(2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(254);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exp);
					setState(248);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(249);
					binop();
					setState(250);
					exp(2);
					}
					} 
				}
				setState(256);
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
		enterRule(_localctx, 30, RULE_binop);
		try {
			setState(262);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SS:
			case PER:
			case STAR:
			case SLASH:
				enterOuterAlt(_localctx, 1);
				{
				setState(257);
				multop();
				}
				break;
			case MINUS:
			case PLUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(258);
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
				setState(259);
				relop();
				}
				break;
			case AND:
			case OR:
				enterOuterAlt(_localctx, 4);
				{
				setState(260);
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
				setState(261);
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
		enterRule(_localctx, 32, RULE_multop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
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
		enterRule(_localctx, 34, RULE_addop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
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
		enterRule(_localctx, 36, RULE_relop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
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
		enterRule(_localctx, 38, RULE_boolop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
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
		enterRule(_localctx, 40, RULE_bitop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
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
		enterRule(_localctx, 42, RULE_unop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
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
		enterRule(_localctx, 44, RULE_var);
		try {
			setState(286);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(276);
				match(NAME);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(277);
				prefixexp();
				setState(284);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OB:
					{
					setState(278);
					match(OB);
					setState(279);
					exp(0);
					setState(280);
					match(CB);
					}
					break;
				case DOT:
					{
					setState(282);
					match(DOT);
					setState(283);
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
		enterRule(_localctx, 46, RULE_prefixexp);
		try {
			int _alt;
			setState(326);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				match(NAME);
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(295);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case OB:
							{
							setState(289);
							match(OB);
							setState(290);
							exp(0);
							setState(291);
							match(CB);
							}
							break;
						case DOT:
							{
							setState(293);
							match(DOT);
							setState(294);
							match(NAME);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(299);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(300);
				functioncall(0);
				setState(309);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(312);
				match(OP);
				setState(313);
				exp(0);
				setState(314);
				match(CP);
				setState(323);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(321);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case OB:
							{
							setState(315);
							match(OB);
							setState(316);
							exp(0);
							setState(317);
							match(CB);
							}
							break;
						case DOT:
							{
							setState(319);
							match(DOT);
							setState(320);
							match(NAME);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(325);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
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
		int _startState = 48;
		enterRecursionRule(_localctx, 48, RULE_functioncall, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(329);
				match(NAME);
				setState(338);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(336);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(330);
						match(OB);
						setState(331);
						exp(0);
						setState(332);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(334);
						match(DOT);
						setState(335);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(340);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(341);
				args();
				}
				break;
			case 2:
				{
				setState(342);
				match(OP);
				setState(343);
				exp(0);
				setState(344);
				match(CP);
				setState(353);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(351);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(345);
						match(OB);
						setState(346);
						exp(0);
						setState(347);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(349);
						match(DOT);
						setState(350);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(355);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(356);
				args();
				}
				break;
			case 3:
				{
				setState(358);
				match(NAME);
				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(365);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(359);
						match(OB);
						setState(360);
						exp(0);
						setState(361);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(363);
						match(DOT);
						setState(364);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(369);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(370);
				match(COL);
				setState(371);
				match(NAME);
				setState(372);
				args();
				}
				break;
			case 4:
				{
				setState(373);
				match(OP);
				setState(374);
				exp(0);
				setState(375);
				match(CP);
				setState(384);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT || _la==OB) {
					{
					setState(382);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OB:
						{
						setState(376);
						match(OB);
						setState(377);
						exp(0);
						setState(378);
						match(CB);
						}
						break;
					case DOT:
						{
						setState(380);
						match(DOT);
						setState(381);
						match(NAME);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(386);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(387);
				match(COL);
				setState(388);
				match(NAME);
				setState(389);
				args();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(423);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(421);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
					case 1:
						{
						_localctx = new FunctioncallContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_functioncall);
						setState(393);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(402);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==DOT || _la==OB) {
							{
							setState(400);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
							case OB:
								{
								setState(394);
								match(OB);
								setState(395);
								exp(0);
								setState(396);
								match(CB);
								}
								break;
							case DOT:
								{
								setState(398);
								match(DOT);
								setState(399);
								match(NAME);
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							setState(404);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(405);
						args();
						}
						break;
					case 2:
						{
						_localctx = new FunctioncallContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_functioncall);
						setState(406);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(415);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==DOT || _la==OB) {
							{
							setState(413);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
							case OB:
								{
								setState(407);
								match(OB);
								setState(408);
								exp(0);
								setState(409);
								match(CB);
								}
								break;
							case DOT:
								{
								setState(411);
								match(DOT);
								setState(412);
								match(NAME);
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							setState(417);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(418);
						match(COL);
						setState(419);
						match(NAME);
						setState(420);
						args();
						}
						break;
					}
					} 
				}
				setState(425);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
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
		enterRule(_localctx, 50, RULE_args);
		int _la;
		try {
			setState(433);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OP:
				enterOuterAlt(_localctx, 1);
				{
				setState(426);
				match(OP);
				setState(428);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 17)) & ~0x3f) == 0 && ((1L << (_la - 17)) & 280650879957889L) != 0)) {
					{
					setState(427);
					explist();
					}
				}

				setState(430);
				match(CP);
				}
				break;
			case OCU:
				enterOuterAlt(_localctx, 2);
				{
				setState(431);
				tableconstructor();
				}
				break;
			case NORMALSTRING:
			case CHARSTRING:
			case LONGSTRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(432);
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
	public static class FunctiondefContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(LuaParser.FUNCTION, 0); }
		public FuncbodyContext funcbody() {
			return getRuleContext(FuncbodyContext.class,0);
		}
		public FunctiondefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functiondef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).enterFunctiondef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LuaParserListener ) ((LuaParserListener)listener).exitFunctiondef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LuaParserVisitor ) return ((LuaParserVisitor<? extends T>)visitor).visitFunctiondef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctiondefContext functiondef() throws RecognitionException {
		FunctiondefContext _localctx = new FunctiondefContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_functiondef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(FUNCTION);
			setState(436);
			funcbody();
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
		enterRule(_localctx, 54, RULE_funcbody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			match(OP);
			setState(439);
			parlist();
			setState(440);
			match(CP);
			setState(441);
			block();
			setState(442);
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
		enterRule(_localctx, 56, RULE_parlist);
		int _la;
		try {
			setState(451);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(444);
				namelist();
				setState(447);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(445);
					match(COMMA);
					setState(446);
					match(DDD);
					}
				}

				}
				break;
			case DDD:
				enterOuterAlt(_localctx, 2);
				{
				setState(449);
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
		enterRule(_localctx, 58, RULE_tableconstructor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			match(OCU);
			setState(455);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 17)) & ~0x3f) == 0 && ((1L << (_la - 17)) & 280653027441537L) != 0)) {
				{
				setState(454);
				fieldlist();
				}
			}

			setState(457);
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
		enterRule(_localctx, 60, RULE_fieldlist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(459);
			field();
			setState(465);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(460);
					fieldsep();
					setState(461);
					field();
					}
					} 
				}
				setState(467);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			setState(469);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI || _la==COMMA) {
				{
				setState(468);
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
		enterRule(_localctx, 62, RULE_field);
		try {
			setState(481);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(471);
				match(OB);
				setState(472);
				exp(0);
				setState(473);
				match(CB);
				setState(474);
				match(EQ);
				setState(475);
				exp(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(477);
				match(NAME);
				setState(478);
				match(EQ);
				setState(479);
				exp(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(480);
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
		enterRule(_localctx, 64, RULE_fieldsep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
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
		enterRule(_localctx, 66, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(485);
			_la = _input.LA(1);
			if ( !(((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & 15L) != 0)) ) {
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
		enterRule(_localctx, 68, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2017612633061982208L) != 0)) ) {
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
		case 14:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 24:
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
		"\u0004\u0001D\u01ea\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0005"+
		"\u0002M\b\u0002\n\u0002\f\u0002P\t\u0002\u0001\u0002\u0003\u0002S\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003n\b\u0003\n\u0003\f\u0003q\t\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003u\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003\u0081\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0090\b\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004\u009a\b\u0004\u0003\u0004\u009c\b"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00a6\b\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00ad\b\u0006\n"+
		"\u0006\f\u0006\u00b0\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007\u00b5\b\u0007\u0001\b\u0001\b\u0003\b\u00b9\b\b\u0001\b\u0001\b"+
		"\u0003\b\u00bd\b\b\u0001\b\u0003\b\u00c0\b\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0005\n\u00c9\b\n\n\n\f\n\u00cc\t\n\u0001\n"+
		"\u0001\n\u0003\n\u00d0\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b"+
		"\u00d5\b\u000b\n\u000b\f\u000b\u00d8\t\u000b\u0001\f\u0001\f\u0001\f\u0005"+
		"\f\u00dd\b\f\n\f\f\f\u00e0\t\f\u0001\r\u0001\r\u0001\r\u0005\r\u00e5\b"+
		"\r\n\r\f\r\u00e8\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00f7\b\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00fd\b\u000e\n\u000e\f\u000e"+
		"\u0100\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u0107\b\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u011d\b\u0016"+
		"\u0003\u0016\u011f\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0128\b\u0017\n\u0017"+
		"\f\u0017\u012b\t\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0134\b\u0017\n\u0017"+
		"\f\u0017\u0137\t\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017"+
		"\u0142\b\u0017\n\u0017\f\u0017\u0145\t\u0017\u0003\u0017\u0147\b\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0005\u0018\u0151\b\u0018\n\u0018\f\u0018\u0154"+
		"\t\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0160"+
		"\b\u0018\n\u0018\f\u0018\u0163\t\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0005\u0018\u016e\b\u0018\n\u0018\f\u0018\u0171\t\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u017f"+
		"\b\u0018\n\u0018\f\u0018\u0182\t\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0003\u0018\u0188\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0191\b\u0018"+
		"\n\u0018\f\u0018\u0194\t\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u019e"+
		"\b\u0018\n\u0018\f\u0018\u01a1\t\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0005\u0018\u01a6\b\u0018\n\u0018\f\u0018\u01a9\t\u0018\u0001\u0019\u0001"+
		"\u0019\u0003\u0019\u01ad\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003"+
		"\u0019\u01b2\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0003\u001c\u01c0\b\u001c\u0001\u001c\u0001\u001c\u0003"+
		"\u001c\u01c4\b\u001c\u0001\u001d\u0001\u001d\u0003\u001d\u01c8\b\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0005\u001e\u01d0\b\u001e\n\u001e\f\u001e\u01d3\t\u001e\u0001\u001e\u0003"+
		"\u001e\u01d6\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003"+
		"\u001f\u01e2\b\u001f\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001"+
		"\"\u0000\u0002\u001c0#\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BD\u0000\t\u0003"+
		"\u0000%&--66\u0002\u0000\u001d\u001d,,\u0004\u0000\u0013\u0014()2288\u0001"+
		"\u0000*+\u0003\u0000\u001c\u001c\"$44\u0002\u0000\u001c\u001e!!\u0002"+
		"\u0000\u0001\u0001\u000f\u000f\u0001\u0000=@\u0001\u0000:<\u0217\u0000"+
		"F\u0001\u0000\u0000\u0000\u0002I\u0001\u0000\u0000\u0000\u0004N\u0001"+
		"\u0000\u0000\u0000\u0006\u008f\u0001\u0000\u0000\u0000\b\u009b\u0001\u0000"+
		"\u0000\u0000\n\u00a5\u0001\u0000\u0000\u0000\f\u00a7\u0001\u0000\u0000"+
		"\u0000\u000e\u00b4\u0001\u0000\u0000\u0000\u0010\u00bc\u0001\u0000\u0000"+
		"\u0000\u0012\u00c1\u0001\u0000\u0000\u0000\u0014\u00c5\u0001\u0000\u0000"+
		"\u0000\u0016\u00d1\u0001\u0000\u0000\u0000\u0018\u00d9\u0001\u0000\u0000"+
		"\u0000\u001a\u00e1\u0001\u0000\u0000\u0000\u001c\u00f6\u0001\u0000\u0000"+
		"\u0000\u001e\u0106\u0001\u0000\u0000\u0000 \u0108\u0001\u0000\u0000\u0000"+
		"\"\u010a\u0001\u0000\u0000\u0000$\u010c\u0001\u0000\u0000\u0000&\u010e"+
		"\u0001\u0000\u0000\u0000(\u0110\u0001\u0000\u0000\u0000*\u0112\u0001\u0000"+
		"\u0000\u0000,\u011e\u0001\u0000\u0000\u0000.\u0146\u0001\u0000\u0000\u0000"+
		"0\u0187\u0001\u0000\u0000\u00002\u01b1\u0001\u0000\u0000\u00004\u01b3"+
		"\u0001\u0000\u0000\u00006\u01b6\u0001\u0000\u0000\u00008\u01c3\u0001\u0000"+
		"\u0000\u0000:\u01c5\u0001\u0000\u0000\u0000<\u01cb\u0001\u0000\u0000\u0000"+
		">\u01e1\u0001\u0000\u0000\u0000@\u01e3\u0001\u0000\u0000\u0000B\u01e5"+
		"\u0001\u0000\u0000\u0000D\u01e7\u0001\u0000\u0000\u0000FG\u0003\u0002"+
		"\u0001\u0000GH\u0005\u0000\u0000\u0001H\u0001\u0001\u0000\u0000\u0000"+
		"IJ\u0003\u0004\u0002\u0000J\u0003\u0001\u0000\u0000\u0000KM\u0003\u0006"+
		"\u0003\u0000LK\u0001\u0000\u0000\u0000MP\u0001\u0000\u0000\u0000NL\u0001"+
		"\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000OR\u0001\u0000\u0000\u0000"+
		"PN\u0001\u0000\u0000\u0000QS\u0003\u0010\b\u0000RQ\u0001\u0000\u0000\u0000"+
		"RS\u0001\u0000\u0000\u0000S\u0005\u0001\u0000\u0000\u0000T\u0090\u0005"+
		"\u0001\u0000\u0000U\u0090\u0003\b\u0004\u0000V\u0090\u00030\u0018\u0000"+
		"W\u0090\u0003\u0012\t\u0000X\u0090\u0005\u0003\u0000\u0000YZ\u0005\u0007"+
		"\u0000\u0000Z[\u0003\u001c\u000e\u0000[\\\u0005\u0005\u0000\u0000\\]\u0003"+
		"\u0004\u0002\u0000]^\u0005\u0006\u0000\u0000^\u0090\u0001\u0000\u0000"+
		"\u0000_`\u0005\b\u0000\u0000`a\u0003\u0004\u0002\u0000ab\u0005\t\u0000"+
		"\u0000bc\u0003\u001c\u000e\u0000c\u0090\u0001\u0000\u0000\u0000de\u0005"+
		"\n\u0000\u0000ef\u0003\u001c\u000e\u0000fg\u0005\u000b\u0000\u0000go\u0003"+
		"\u0004\u0002\u0000hi\u0005\f\u0000\u0000ij\u0003\u001c\u000e\u0000jk\u0005"+
		"\u000b\u0000\u0000kl\u0003\u0004\u0002\u0000ln\u0001\u0000\u0000\u0000"+
		"mh\u0001\u0000\u0000\u0000nq\u0001\u0000\u0000\u0000om\u0001\u0000\u0000"+
		"\u0000op\u0001\u0000\u0000\u0000pt\u0001\u0000\u0000\u0000qo\u0001\u0000"+
		"\u0000\u0000rs\u0005\r\u0000\u0000su\u0003\u0004\u0002\u0000tr\u0001\u0000"+
		"\u0000\u0000tu\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0005"+
		"\u0006\u0000\u0000w\u0090\u0001\u0000\u0000\u0000xy\u0005\u000e\u0000"+
		"\u0000yz\u00059\u0000\u0000z{\u0005\u0002\u0000\u0000{|\u0003\u001c\u000e"+
		"\u0000|}\u0005\u000f\u0000\u0000}\u0080\u0003\u001c\u000e\u0000~\u007f"+
		"\u0005\u000f\u0000\u0000\u007f\u0081\u0003\u001c\u000e\u0000\u0080~\u0001"+
		"\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0082\u0001"+
		"\u0000\u0000\u0000\u0082\u0083\u0005\u0005\u0000\u0000\u0083\u0084\u0003"+
		"\u0004\u0002\u0000\u0084\u0085\u0005\u0006\u0000\u0000\u0085\u0090\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0005\u000e\u0000\u0000\u0087\u0088\u0003"+
		"\u0018\f\u0000\u0088\u0089\u0005\u0010\u0000\u0000\u0089\u008a\u0003\u001a"+
		"\r\u0000\u008a\u008b\u0005\u0005\u0000\u0000\u008b\u008c\u0003\u0004\u0002"+
		"\u0000\u008c\u008d\u0005\u0006\u0000\u0000\u008d\u0090\u0001\u0000\u0000"+
		"\u0000\u008e\u0090\u0003\n\u0005\u0000\u008fT\u0001\u0000\u0000\u0000"+
		"\u008fU\u0001\u0000\u0000\u0000\u008fV\u0001\u0000\u0000\u0000\u008fW"+
		"\u0001\u0000\u0000\u0000\u008fX\u0001\u0000\u0000\u0000\u008fY\u0001\u0000"+
		"\u0000\u0000\u008f_\u0001\u0000\u0000\u0000\u008fd\u0001\u0000\u0000\u0000"+
		"\u008fx\u0001\u0000\u0000\u0000\u008f\u0086\u0001\u0000\u0000\u0000\u008f"+
		"\u008e\u0001\u0000\u0000\u0000\u0090\u0007\u0001\u0000\u0000\u0000\u0091"+
		"\u0092\u0003\u0016\u000b\u0000\u0092\u0093\u0005\u0002\u0000\u0000\u0093"+
		"\u0094\u0003\u001a\r\u0000\u0094\u009c\u0001\u0000\u0000\u0000\u0095\u0096"+
		"\u0005\u0012\u0000\u0000\u0096\u0099\u0003\f\u0006\u0000\u0097\u0098\u0005"+
		"\u0002\u0000\u0000\u0098\u009a\u0003\u001a\r\u0000\u0099\u0097\u0001\u0000"+
		"\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009c\u0001\u0000"+
		"\u0000\u0000\u009b\u0091\u0001\u0000\u0000\u0000\u009b\u0095\u0001\u0000"+
		"\u0000\u0000\u009c\t\u0001\u0000\u0000\u0000\u009d\u009e\u0005\u0011\u0000"+
		"\u0000\u009e\u009f\u0003\u0014\n\u0000\u009f\u00a0\u00036\u001b\u0000"+
		"\u00a0\u00a6\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005\u0012\u0000\u0000"+
		"\u00a2\u00a3\u0005\u0011\u0000\u0000\u00a3\u00a4\u00059\u0000\u0000\u00a4"+
		"\u00a6\u00036\u001b\u0000\u00a5\u009d\u0001\u0000\u0000\u0000\u00a5\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a6\u000b\u0001\u0000\u0000\u0000\u00a7\u00a8"+
		"\u00059\u0000\u0000\u00a8\u00ae\u0003\u000e\u0007\u0000\u00a9\u00aa\u0005"+
		"\u000f\u0000\u0000\u00aa\u00ab\u00059\u0000\u0000\u00ab\u00ad\u0003\u000e"+
		"\u0007\u0000\u00ac\u00a9\u0001\u0000\u0000\u0000\u00ad\u00b0\u0001\u0000"+
		"\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000"+
		"\u0000\u0000\u00af\r\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b2\u0005\u0013\u0000\u0000\u00b2\u00b3\u00059\u0000\u0000"+
		"\u00b3\u00b5\u0005\u0014\u0000\u0000\u00b4\u00b1\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u000f\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b8\u0005\u0015\u0000\u0000\u00b7\u00b9\u0003\u001a\r\u0000\u00b8"+
		"\u00b7\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9"+
		"\u00bd\u0001\u0000\u0000\u0000\u00ba\u00bd\u0005\u0003\u0000\u0000\u00bb"+
		"\u00bd\u0005\u0016\u0000\u0000\u00bc\u00b6\u0001\u0000\u0000\u0000\u00bc"+
		"\u00ba\u0001\u0000\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd"+
		"\u00bf\u0001\u0000\u0000\u0000\u00be\u00c0\u0005\u0001\u0000\u0000\u00bf"+
		"\u00be\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0"+
		"\u0011\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005\u0017\u0000\u0000\u00c2"+
		"\u00c3\u00059\u0000\u0000\u00c3\u00c4\u0005\u0017\u0000\u0000\u00c4\u0013"+
		"\u0001\u0000\u0000\u0000\u00c5\u00ca\u00059\u0000\u0000\u00c6\u00c7\u0005"+
		"\u001b\u0000\u0000\u00c7\u00c9\u00059\u0000\u0000\u00c8\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000"+
		"\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cf\u0001\u0000"+
		"\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005\'\u0000"+
		"\u0000\u00ce\u00d0\u00059\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000"+
		"\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u0015\u0001\u0000\u0000\u0000"+
		"\u00d1\u00d6\u0003,\u0016\u0000\u00d2\u00d3\u0005\u000f\u0000\u0000\u00d3"+
		"\u00d5\u0003,\u0016\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5\u00d8"+
		"\u0001\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d6\u00d7"+
		"\u0001\u0000\u0000\u0000\u00d7\u0017\u0001\u0000\u0000\u0000\u00d8\u00d6"+
		"\u0001\u0000\u0000\u0000\u00d9\u00de\u00059\u0000\u0000\u00da\u00db\u0005"+
		"\u000f\u0000\u0000\u00db\u00dd\u00059\u0000\u0000\u00dc\u00da\u0001\u0000"+
		"\u0000\u0000\u00dd\u00e0\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000"+
		"\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u0019\u0001\u0000"+
		"\u0000\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e1\u00e6\u0003\u001c"+
		"\u000e\u0000\u00e2\u00e3\u0005\u000f\u0000\u0000\u00e3\u00e5\u0003\u001c"+
		"\u000e\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e8\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e7\u001b\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001\u0000"+
		"\u0000\u0000\u00e9\u00ea\u0006\u000e\uffff\uffff\u0000\u00ea\u00f7\u0005"+
		"\u0018\u0000\u0000\u00eb\u00f7\u0005\u0019\u0000\u0000\u00ec\u00f7\u0005"+
		"\u001a\u0000\u0000\u00ed\u00f7\u0003B!\u0000\u00ee\u00f7\u0003D\"\u0000"+
		"\u00ef\u00f7\u00057\u0000\u0000\u00f0\u00f7\u00034\u001a\u0000\u00f1\u00f7"+
		"\u0003.\u0017\u0000\u00f2\u00f7\u0003:\u001d\u0000\u00f3\u00f4\u0003*"+
		"\u0015\u0000\u00f4\u00f5\u0003\u001c\u000e\u0002\u00f5\u00f7\u0001\u0000"+
		"\u0000\u0000\u00f6\u00e9\u0001\u0000\u0000\u0000\u00f6\u00eb\u0001\u0000"+
		"\u0000\u0000\u00f6\u00ec\u0001\u0000\u0000\u0000\u00f6\u00ed\u0001\u0000"+
		"\u0000\u0000\u00f6\u00ee\u0001\u0000\u0000\u0000\u00f6\u00ef\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f0\u0001\u0000\u0000\u0000\u00f6\u00f1\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f2\u0001\u0000\u0000\u0000\u00f6\u00f3\u0001\u0000"+
		"\u0000\u0000\u00f7\u00fe\u0001\u0000\u0000\u0000\u00f8\u00f9\n\u0001\u0000"+
		"\u0000\u00f9\u00fa\u0003\u001e\u000f\u0000\u00fa\u00fb\u0003\u001c\u000e"+
		"\u0002\u00fb\u00fd\u0001\u0000\u0000\u0000\u00fc\u00f8\u0001\u0000\u0000"+
		"\u0000\u00fd\u0100\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000"+
		"\u0000\u00fe\u00ff\u0001\u0000\u0000\u0000\u00ff\u001d\u0001\u0000\u0000"+
		"\u0000\u0100\u00fe\u0001\u0000\u0000\u0000\u0101\u0107\u0003 \u0010\u0000"+
		"\u0102\u0107\u0003\"\u0011\u0000\u0103\u0107\u0003$\u0012\u0000\u0104"+
		"\u0107\u0003&\u0013\u0000\u0105\u0107\u0003(\u0014\u0000\u0106\u0101\u0001"+
		"\u0000\u0000\u0000\u0106\u0102\u0001\u0000\u0000\u0000\u0106\u0103\u0001"+
		"\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0106\u0105\u0001"+
		"\u0000\u0000\u0000\u0107\u001f\u0001\u0000\u0000\u0000\u0108\u0109\u0007"+
		"\u0000\u0000\u0000\u0109!\u0001\u0000\u0000\u0000\u010a\u010b\u0007\u0001"+
		"\u0000\u0000\u010b#\u0001\u0000\u0000\u0000\u010c\u010d\u0007\u0002\u0000"+
		"\u0000\u010d%\u0001\u0000\u0000\u0000\u010e\u010f\u0007\u0003\u0000\u0000"+
		"\u010f\'\u0001\u0000\u0000\u0000\u0110\u0111\u0007\u0004\u0000\u0000\u0111"+
		")\u0001\u0000\u0000\u0000\u0112\u0113\u0007\u0005\u0000\u0000\u0113+\u0001"+
		"\u0000\u0000\u0000\u0114\u011f\u00059\u0000\u0000\u0115\u011c\u0003.\u0017"+
		"\u0000\u0116\u0117\u00050\u0000\u0000\u0117\u0118\u0003\u001c\u000e\u0000"+
		"\u0118\u0119\u00051\u0000\u0000\u0119\u011d\u0001\u0000\u0000\u0000\u011a"+
		"\u011b\u0005\u001b\u0000\u0000\u011b\u011d\u00059\u0000\u0000\u011c\u0116"+
		"\u0001\u0000\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011d\u011f"+
		"\u0001\u0000\u0000\u0000\u011e\u0114\u0001\u0000\u0000\u0000\u011e\u0115"+
		"\u0001\u0000\u0000\u0000\u011f-\u0001\u0000\u0000\u0000\u0120\u0129\u0005"+
		"9\u0000\u0000\u0121\u0122\u00050\u0000\u0000\u0122\u0123\u0003\u001c\u000e"+
		"\u0000\u0123\u0124\u00051\u0000\u0000\u0124\u0128\u0001\u0000\u0000\u0000"+
		"\u0125\u0126\u0005\u001b\u0000\u0000\u0126\u0128\u00059\u0000\u0000\u0127"+
		"\u0121\u0001\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000\u0000\u0128"+
		"\u012b\u0001\u0000\u0000\u0000\u0129\u0127\u0001\u0000\u0000\u0000\u0129"+
		"\u012a\u0001\u0000\u0000\u0000\u012a\u0147\u0001\u0000\u0000\u0000\u012b"+
		"\u0129\u0001\u0000\u0000\u0000\u012c\u0135\u00030\u0018\u0000\u012d\u012e"+
		"\u00050\u0000\u0000\u012e\u012f\u0003\u001c\u000e\u0000\u012f\u0130\u0005"+
		"1\u0000\u0000\u0130\u0134\u0001\u0000\u0000\u0000\u0131\u0132\u0005\u001b"+
		"\u0000\u0000\u0132\u0134\u00059\u0000\u0000\u0133\u012d\u0001\u0000\u0000"+
		"\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0134\u0137\u0001\u0000\u0000"+
		"\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000"+
		"\u0000\u0136\u0147\u0001\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000"+
		"\u0000\u0138\u0139\u0005\u001f\u0000\u0000\u0139\u013a\u0003\u001c\u000e"+
		"\u0000\u013a\u0143\u0005 \u0000\u0000\u013b\u013c\u00050\u0000\u0000\u013c"+
		"\u013d\u0003\u001c\u000e\u0000\u013d\u013e\u00051\u0000\u0000\u013e\u0142"+
		"\u0001\u0000\u0000\u0000\u013f\u0140\u0005\u001b\u0000\u0000\u0140\u0142"+
		"\u00059\u0000\u0000\u0141\u013b\u0001\u0000\u0000\u0000\u0141\u013f\u0001"+
		"\u0000\u0000\u0000\u0142\u0145\u0001\u0000\u0000\u0000\u0143\u0141\u0001"+
		"\u0000\u0000\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144\u0147\u0001"+
		"\u0000\u0000\u0000\u0145\u0143\u0001\u0000\u0000\u0000\u0146\u0120\u0001"+
		"\u0000\u0000\u0000\u0146\u012c\u0001\u0000\u0000\u0000\u0146\u0138\u0001"+
		"\u0000\u0000\u0000\u0147/\u0001\u0000\u0000\u0000\u0148\u0149\u0006\u0018"+
		"\uffff\uffff\u0000\u0149\u0152\u00059\u0000\u0000\u014a\u014b\u00050\u0000"+
		"\u0000\u014b\u014c\u0003\u001c\u000e\u0000\u014c\u014d\u00051\u0000\u0000"+
		"\u014d\u0151\u0001\u0000\u0000\u0000\u014e\u014f\u0005\u001b\u0000\u0000"+
		"\u014f\u0151\u00059\u0000\u0000\u0150\u014a\u0001\u0000\u0000\u0000\u0150"+
		"\u014e\u0001\u0000\u0000\u0000\u0151\u0154\u0001\u0000\u0000\u0000\u0152"+
		"\u0150\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000\u0153"+
		"\u0155\u0001\u0000\u0000\u0000\u0154\u0152\u0001\u0000\u0000\u0000\u0155"+
		"\u0188\u00032\u0019\u0000\u0156\u0157\u0005\u001f\u0000\u0000\u0157\u0158"+
		"\u0003\u001c\u000e\u0000\u0158\u0161\u0005 \u0000\u0000\u0159\u015a\u0005"+
		"0\u0000\u0000\u015a\u015b\u0003\u001c\u000e\u0000\u015b\u015c\u00051\u0000"+
		"\u0000\u015c\u0160\u0001\u0000\u0000\u0000\u015d\u015e\u0005\u001b\u0000"+
		"\u0000\u015e\u0160\u00059\u0000\u0000\u015f\u0159\u0001\u0000\u0000\u0000"+
		"\u015f\u015d\u0001\u0000\u0000\u0000\u0160\u0163\u0001\u0000\u0000\u0000"+
		"\u0161\u015f\u0001\u0000\u0000\u0000\u0161\u0162\u0001\u0000\u0000\u0000"+
		"\u0162\u0164\u0001\u0000\u0000\u0000\u0163\u0161\u0001\u0000\u0000\u0000"+
		"\u0164\u0165\u00032\u0019\u0000\u0165\u0188\u0001\u0000\u0000\u0000\u0166"+
		"\u016f\u00059\u0000\u0000\u0167\u0168\u00050\u0000\u0000\u0168\u0169\u0003"+
		"\u001c\u000e\u0000\u0169\u016a\u00051\u0000\u0000\u016a\u016e\u0001\u0000"+
		"\u0000\u0000\u016b\u016c\u0005\u001b\u0000\u0000\u016c\u016e\u00059\u0000"+
		"\u0000\u016d\u0167\u0001\u0000\u0000\u0000\u016d\u016b\u0001\u0000\u0000"+
		"\u0000\u016e\u0171\u0001\u0000\u0000\u0000\u016f\u016d\u0001\u0000\u0000"+
		"\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170\u0172\u0001\u0000\u0000"+
		"\u0000\u0171\u016f\u0001\u0000\u0000\u0000\u0172\u0173\u0005\'\u0000\u0000"+
		"\u0173\u0174\u00059\u0000\u0000\u0174\u0188\u00032\u0019\u0000\u0175\u0176"+
		"\u0005\u001f\u0000\u0000\u0176\u0177\u0003\u001c\u000e\u0000\u0177\u0180"+
		"\u0005 \u0000\u0000\u0178\u0179\u00050\u0000\u0000\u0179\u017a\u0003\u001c"+
		"\u000e\u0000\u017a\u017b\u00051\u0000\u0000\u017b\u017f\u0001\u0000\u0000"+
		"\u0000\u017c\u017d\u0005\u001b\u0000\u0000\u017d\u017f\u00059\u0000\u0000"+
		"\u017e\u0178\u0001\u0000\u0000\u0000\u017e\u017c\u0001\u0000\u0000\u0000"+
		"\u017f\u0182\u0001\u0000\u0000\u0000\u0180\u017e\u0001\u0000\u0000\u0000"+
		"\u0180\u0181\u0001\u0000\u0000\u0000\u0181\u0183\u0001\u0000\u0000\u0000"+
		"\u0182\u0180\u0001\u0000\u0000\u0000\u0183\u0184\u0005\'\u0000\u0000\u0184"+
		"\u0185\u00059\u0000\u0000\u0185\u0186\u00032\u0019\u0000\u0186\u0188\u0001"+
		"\u0000\u0000\u0000\u0187\u0148\u0001\u0000\u0000\u0000\u0187\u0156\u0001"+
		"\u0000\u0000\u0000\u0187\u0166\u0001\u0000\u0000\u0000\u0187\u0175\u0001"+
		"\u0000\u0000\u0000\u0188\u01a7\u0001\u0000\u0000\u0000\u0189\u0192\n\u0005"+
		"\u0000\u0000\u018a\u018b\u00050\u0000\u0000\u018b\u018c\u0003\u001c\u000e"+
		"\u0000\u018c\u018d\u00051\u0000\u0000\u018d\u0191\u0001\u0000\u0000\u0000"+
		"\u018e\u018f\u0005\u001b\u0000\u0000\u018f\u0191\u00059\u0000\u0000\u0190"+
		"\u018a\u0001\u0000\u0000\u0000\u0190\u018e\u0001\u0000\u0000\u0000\u0191"+
		"\u0194\u0001\u0000\u0000\u0000\u0192\u0190\u0001\u0000\u0000\u0000\u0192"+
		"\u0193\u0001\u0000\u0000\u0000\u0193\u0195\u0001\u0000\u0000\u0000\u0194"+
		"\u0192\u0001\u0000\u0000\u0000\u0195\u01a6\u00032\u0019\u0000\u0196\u019f"+
		"\n\u0002\u0000\u0000\u0197\u0198\u00050\u0000\u0000\u0198\u0199\u0003"+
		"\u001c\u000e\u0000\u0199\u019a\u00051\u0000\u0000\u019a\u019e\u0001\u0000"+
		"\u0000\u0000\u019b\u019c\u0005\u001b\u0000\u0000\u019c\u019e\u00059\u0000"+
		"\u0000\u019d\u0197\u0001\u0000\u0000\u0000\u019d\u019b\u0001\u0000\u0000"+
		"\u0000\u019e\u01a1\u0001\u0000\u0000\u0000\u019f\u019d\u0001\u0000\u0000"+
		"\u0000\u019f\u01a0\u0001\u0000\u0000\u0000\u01a0\u01a2\u0001\u0000\u0000"+
		"\u0000\u01a1\u019f\u0001\u0000\u0000\u0000\u01a2\u01a3\u0005\'\u0000\u0000"+
		"\u01a3\u01a4\u00059\u0000\u0000\u01a4\u01a6\u00032\u0019\u0000\u01a5\u0189"+
		"\u0001\u0000\u0000\u0000\u01a5\u0196\u0001\u0000\u0000\u0000\u01a6\u01a9"+
		"\u0001\u0000\u0000\u0000\u01a7\u01a5\u0001\u0000\u0000\u0000\u01a7\u01a8"+
		"\u0001\u0000\u0000\u0000\u01a81\u0001\u0000\u0000\u0000\u01a9\u01a7\u0001"+
		"\u0000\u0000\u0000\u01aa\u01ac\u0005\u001f\u0000\u0000\u01ab\u01ad\u0003"+
		"\u001a\r\u0000\u01ac\u01ab\u0001\u0000\u0000\u0000\u01ac\u01ad\u0001\u0000"+
		"\u0000\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae\u01b2\u0005 \u0000"+
		"\u0000\u01af\u01b2\u0003:\u001d\u0000\u01b0\u01b2\u0003D\"\u0000\u01b1"+
		"\u01aa\u0001\u0000\u0000\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b1"+
		"\u01b0\u0001\u0000\u0000\u0000\u01b23\u0001\u0000\u0000\u0000\u01b3\u01b4"+
		"\u0005\u0011\u0000\u0000\u01b4\u01b5\u00036\u001b\u0000\u01b55\u0001\u0000"+
		"\u0000\u0000\u01b6\u01b7\u0005\u001f\u0000\u0000\u01b7\u01b8\u00038\u001c"+
		"\u0000\u01b8\u01b9\u0005 \u0000\u0000\u01b9\u01ba\u0003\u0004\u0002\u0000"+
		"\u01ba\u01bb\u0005\u0006\u0000\u0000\u01bb7\u0001\u0000\u0000\u0000\u01bc"+
		"\u01bf\u0003\u0018\f\u0000\u01bd\u01be\u0005\u000f\u0000\u0000\u01be\u01c0"+
		"\u00057\u0000\u0000\u01bf\u01bd\u0001\u0000\u0000\u0000\u01bf\u01c0\u0001"+
		"\u0000\u0000\u0000\u01c0\u01c4\u0001\u0000\u0000\u0000\u01c1\u01c4\u0005"+
		"7\u0000\u0000\u01c2\u01c4\u0001\u0000\u0000\u0000\u01c3\u01bc\u0001\u0000"+
		"\u0000\u0000\u01c3\u01c1\u0001\u0000\u0000\u0000\u01c3\u01c2\u0001\u0000"+
		"\u0000\u0000\u01c49\u0001\u0000\u0000\u0000\u01c5\u01c7\u0005.\u0000\u0000"+
		"\u01c6\u01c8\u0003<\u001e\u0000\u01c7\u01c6\u0001\u0000\u0000\u0000\u01c7"+
		"\u01c8\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001\u0000\u0000\u0000\u01c9"+
		"\u01ca\u0005/\u0000\u0000\u01ca;\u0001\u0000\u0000\u0000\u01cb\u01d1\u0003"+
		">\u001f\u0000\u01cc\u01cd\u0003@ \u0000\u01cd\u01ce\u0003>\u001f\u0000"+
		"\u01ce\u01d0\u0001\u0000\u0000\u0000\u01cf\u01cc\u0001\u0000\u0000\u0000"+
		"\u01d0\u01d3\u0001\u0000\u0000\u0000\u01d1\u01cf\u0001\u0000\u0000\u0000"+
		"\u01d1\u01d2\u0001\u0000\u0000\u0000\u01d2\u01d5\u0001\u0000\u0000\u0000"+
		"\u01d3\u01d1\u0001\u0000\u0000\u0000\u01d4\u01d6\u0003@ \u0000\u01d5\u01d4"+
		"\u0001\u0000\u0000\u0000\u01d5\u01d6\u0001\u0000\u0000\u0000\u01d6=\u0001"+
		"\u0000\u0000\u0000\u01d7\u01d8\u00050\u0000\u0000\u01d8\u01d9\u0003\u001c"+
		"\u000e\u0000\u01d9\u01da\u00051\u0000\u0000\u01da\u01db\u0005\u0002\u0000"+
		"\u0000\u01db\u01dc\u0003\u001c\u000e\u0000\u01dc\u01e2\u0001\u0000\u0000"+
		"\u0000\u01dd\u01de\u00059\u0000\u0000\u01de\u01df\u0005\u0002\u0000\u0000"+
		"\u01df\u01e2\u0003\u001c\u000e\u0000\u01e0\u01e2\u0003\u001c\u000e\u0000"+
		"\u01e1\u01d7\u0001\u0000\u0000\u0000\u01e1\u01dd\u0001\u0000\u0000\u0000"+
		"\u01e1\u01e0\u0001\u0000\u0000\u0000\u01e2?\u0001\u0000\u0000\u0000\u01e3"+
		"\u01e4\u0007\u0006\u0000\u0000\u01e4A\u0001\u0000\u0000\u0000\u01e5\u01e6"+
		"\u0007\u0007\u0000\u0000\u01e6C\u0001\u0000\u0000\u0000\u01e7\u01e8\u0007"+
		"\b\u0000\u0000\u01e8E\u0001\u0000\u0000\u00006NRot\u0080\u008f\u0099\u009b"+
		"\u00a5\u00ae\u00b4\u00b8\u00bc\u00bf\u00ca\u00cf\u00d6\u00de\u00e6\u00f6"+
		"\u00fe\u0106\u011c\u011e\u0127\u0129\u0133\u0135\u0141\u0143\u0146\u0150"+
		"\u0152\u015f\u0161\u016d\u016f\u017e\u0180\u0187\u0190\u0192\u019d\u019f"+
		"\u01a5\u01a7\u01ac\u01b1\u01bf\u01c3\u01c7\u01d1\u01d5\u01e1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}