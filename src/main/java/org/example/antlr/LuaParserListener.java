// Generated from C:/javastuff/LuaCompiler/src/main/antlr/LuaParser.g4 by ANTLR 4.13.1

package org.example.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LuaParser}.
 */
public interface LuaParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LuaParser#start_}.
	 * @param ctx the parse tree
	 */
	void enterStart_(LuaParser.Start_Context ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#start_}.
	 * @param ctx the parse tree
	 */
	void exitStart_(LuaParser.Start_Context ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#chunk}.
	 * @param ctx the parse tree
	 */
	void enterChunk(LuaParser.ChunkContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#chunk}.
	 * @param ctx the parse tree
	 */
	void exitChunk(LuaParser.ChunkContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(LuaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(LuaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(LuaParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(LuaParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#ifstat}.
	 * @param ctx the parse tree
	 */
	void enterIfstat(LuaParser.IfstatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#ifstat}.
	 * @param ctx the parse tree
	 */
	void exitIfstat(LuaParser.IfstatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#whileloop}.
	 * @param ctx the parse tree
	 */
	void enterWhileloop(LuaParser.WhileloopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#whileloop}.
	 * @param ctx the parse tree
	 */
	void exitWhileloop(LuaParser.WhileloopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#forloop}.
	 * @param ctx the parse tree
	 */
	void enterForloop(LuaParser.ForloopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#forloop}.
	 * @param ctx the parse tree
	 */
	void exitForloop(LuaParser.ForloopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#vardecl}.
	 * @param ctx the parse tree
	 */
	void enterVardecl(LuaParser.VardeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#vardecl}.
	 * @param ctx the parse tree
	 */
	void exitVardecl(LuaParser.VardeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#funcdecl}.
	 * @param ctx the parse tree
	 */
	void enterFuncdecl(LuaParser.FuncdeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#funcdecl}.
	 * @param ctx the parse tree
	 */
	void exitFuncdecl(LuaParser.FuncdeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#attnamelist}.
	 * @param ctx the parse tree
	 */
	void enterAttnamelist(LuaParser.AttnamelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#attnamelist}.
	 * @param ctx the parse tree
	 */
	void exitAttnamelist(LuaParser.AttnamelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#attrib}.
	 * @param ctx the parse tree
	 */
	void enterAttrib(LuaParser.AttribContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#attrib}.
	 * @param ctx the parse tree
	 */
	void exitAttrib(LuaParser.AttribContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#retstat}.
	 * @param ctx the parse tree
	 */
	void enterRetstat(LuaParser.RetstatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#retstat}.
	 * @param ctx the parse tree
	 */
	void exitRetstat(LuaParser.RetstatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(LuaParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(LuaParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#funcname}.
	 * @param ctx the parse tree
	 */
	void enterFuncname(LuaParser.FuncnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#funcname}.
	 * @param ctx the parse tree
	 */
	void exitFuncname(LuaParser.FuncnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#varlist}.
	 * @param ctx the parse tree
	 */
	void enterVarlist(LuaParser.VarlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#varlist}.
	 * @param ctx the parse tree
	 */
	void exitVarlist(LuaParser.VarlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#namelist}.
	 * @param ctx the parse tree
	 */
	void enterNamelist(LuaParser.NamelistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#namelist}.
	 * @param ctx the parse tree
	 */
	void exitNamelist(LuaParser.NamelistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#explist}.
	 * @param ctx the parse tree
	 */
	void enterExplist(LuaParser.ExplistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#explist}.
	 * @param ctx the parse tree
	 */
	void exitExplist(LuaParser.ExplistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(LuaParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(LuaParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#binop}.
	 * @param ctx the parse tree
	 */
	void enterBinop(LuaParser.BinopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#binop}.
	 * @param ctx the parse tree
	 */
	void exitBinop(LuaParser.BinopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#multop}.
	 * @param ctx the parse tree
	 */
	void enterMultop(LuaParser.MultopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#multop}.
	 * @param ctx the parse tree
	 */
	void exitMultop(LuaParser.MultopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#addop}.
	 * @param ctx the parse tree
	 */
	void enterAddop(LuaParser.AddopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#addop}.
	 * @param ctx the parse tree
	 */
	void exitAddop(LuaParser.AddopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#relop}.
	 * @param ctx the parse tree
	 */
	void enterRelop(LuaParser.RelopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#relop}.
	 * @param ctx the parse tree
	 */
	void exitRelop(LuaParser.RelopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#boolop}.
	 * @param ctx the parse tree
	 */
	void enterBoolop(LuaParser.BoolopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#boolop}.
	 * @param ctx the parse tree
	 */
	void exitBoolop(LuaParser.BoolopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#bitop}.
	 * @param ctx the parse tree
	 */
	void enterBitop(LuaParser.BitopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#bitop}.
	 * @param ctx the parse tree
	 */
	void exitBitop(LuaParser.BitopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#unop}.
	 * @param ctx the parse tree
	 */
	void enterUnop(LuaParser.UnopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#unop}.
	 * @param ctx the parse tree
	 */
	void exitUnop(LuaParser.UnopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(LuaParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(LuaParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#prefixexp}.
	 * @param ctx the parse tree
	 */
	void enterPrefixexp(LuaParser.PrefixexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#prefixexp}.
	 * @param ctx the parse tree
	 */
	void exitPrefixexp(LuaParser.PrefixexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#functioncall}.
	 * @param ctx the parse tree
	 */
	void enterFunctioncall(LuaParser.FunctioncallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#functioncall}.
	 * @param ctx the parse tree
	 */
	void exitFunctioncall(LuaParser.FunctioncallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(LuaParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(LuaParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#functiondef}.
	 * @param ctx the parse tree
	 */
	void enterFunctiondef(LuaParser.FunctiondefContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#functiondef}.
	 * @param ctx the parse tree
	 */
	void exitFunctiondef(LuaParser.FunctiondefContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#funcbody}.
	 * @param ctx the parse tree
	 */
	void enterFuncbody(LuaParser.FuncbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#funcbody}.
	 * @param ctx the parse tree
	 */
	void exitFuncbody(LuaParser.FuncbodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#parlist}.
	 * @param ctx the parse tree
	 */
	void enterParlist(LuaParser.ParlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#parlist}.
	 * @param ctx the parse tree
	 */
	void exitParlist(LuaParser.ParlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#tableconstructor}.
	 * @param ctx the parse tree
	 */
	void enterTableconstructor(LuaParser.TableconstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#tableconstructor}.
	 * @param ctx the parse tree
	 */
	void exitTableconstructor(LuaParser.TableconstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#fieldlist}.
	 * @param ctx the parse tree
	 */
	void enterFieldlist(LuaParser.FieldlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#fieldlist}.
	 * @param ctx the parse tree
	 */
	void exitFieldlist(LuaParser.FieldlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(LuaParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(LuaParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#fieldsep}.
	 * @param ctx the parse tree
	 */
	void enterFieldsep(LuaParser.FieldsepContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#fieldsep}.
	 * @param ctx the parse tree
	 */
	void exitFieldsep(LuaParser.FieldsepContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(LuaParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(LuaParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link LuaParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(LuaParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link LuaParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(LuaParser.StringContext ctx);
}