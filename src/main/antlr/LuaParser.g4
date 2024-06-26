/*

MIT license

Author: Ken Domino, October 2023

Based on previous work of: Kazunori Sakamoto, Alexander Alexeev

*/

// $antlr-format alignTrailingComments true, columnLimit 150, minEmptyLines 1, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine false, allowShortBlocksOnASingleLine true, alignSemicolons hanging, alignColons hanging

parser grammar LuaParser;

@header {
package ru.ishingarov.antlr;
}


options {
    tokenVocab = LuaLexer;
}

start_
    : chunk EOF
    ;

chunk
    : block
    ;

block
    : stat* retstat?
    ;

stat
    : ';'
    | vardecl
//    | varlist '=' explist
    | functioncall
//    | label
    | 'break'
//    | 'goto' NAME
//    | 'do' block 'end'
    | whileloop
//    | 'while' exp 'do' block 'end'
//    | 'repeat' block 'until' exp
    | ifstat
//    | 'if' exp 'then' block ('elseif' exp 'then' block)* ('else' block)? 'end'
    | forloop
//    | 'for' NAME '=' exp ',' exp (',' exp)? 'do' block 'end'
//    | 'for' namelist 'in' explist 'do' block 'end'
    | funcdecl
//    | 'function' funcname funcbody
//    | 'local' 'function' NAME funcbody
//    | 'local' attnamelist ('=' explist)?
    ;

ifstat
    : 'if' exp 'then' block ('elseif' exp 'then' block)* ('else' block)? 'end'
    ;

whileloop
    : 'while' exp 'do' block 'end'
    ;

forloop
    : 'for' NAME '=' exp ',' exp (',' exp)? 'do' block 'end'
    | 'for' namelist 'in' explist 'do' block 'end'
    ;

vardecl
    : varlist '=' explist
    | 'local' attnamelist ('=' explist)?
    ;

funcdecl
    : 'function' funcname funcbody
    | 'local' 'function' NAME funcbody
    ;

attnamelist
    : NAME attrib (',' NAME attrib)*
    ;

attrib
    : ('<' NAME '>')?
    ;

retstat
    : ('return' explist? | 'break' | 'continue') ';'?
    ;

label
    : '::' NAME '::'
    ;

funcname
    : NAME ('.' NAME)* (':' NAME)?
    ;

varlist
    : var (',' var)*
    ;

namelist
    : NAME (':' typehint)? (',' NAME (':' typehint)?)*
    ;

explist
    : exp (',' exp)*
    ;

exp
    : 'nil'
    | 'false'
    | 'true'
    | number
    | string
    | '...'
//    | functiondef
    | prefixexp
    | tableconstructor
//    | ('not' | '#' | '-' | '~') exp
    | unop exp
//     Пока не реализовываю
//    | <assoc = right> exp ('^') exp
//    | <assoc = right> exp ('..') exp
    | exp binop exp
//    | exp ('*' | '/' | '%' | '//') exp
//    | exp ('+' | '-') exp
//    | exp ('<' | '>' | '<=' | '>=' | '~=' | '==') exp
//    | exp ('and') exp
//    | exp ('or') exp
//    | exp ('&' | '|' | '~' | '<<' | '>>') exp
    ;

binop
    : multop
    | addop
    | relop
    | boolop
    | bitop
    ;

multop: ('*' | '/' | '%' | '//');
addop: ('+' | '-');
relop: ('<' | '>' | '<=' | '>=' | '~=' | '==');
boolop: ('and' | 'or');
bitop: ('&' | '|' | '~' | '<<' | '>>');


unop
    : ('not' | '#' | '-' | '~')
    ;

// var ::=  Name | prefixexp '[' exp ']' | prefixexp '.' Name
var
    : NAME
    | prefixexp ('[' exp ']' | '.' NAME)
    ;

// prefixexp ::= var | functioncall | '(' exp ')'
prefixexp
    : NAME ('[' exp ']' | '.' NAME)*
    | functioncall ('[' exp ']' | '.' NAME)*
    | '(' exp ')' ('[' exp ']' | '.' NAME)*
    ;

// functioncall ::=  prefixexp args | prefixexp ':' Name args;
functioncall
    : NAME ('[' exp ']' | '.' NAME)* args
    | functioncall ('[' exp ']' | '.' NAME)* args
    | '(' exp ')' ('[' exp ']' | '.' NAME)* args
    | NAME ('[' exp ']' | '.' NAME)* ':' NAME args
    | functioncall ('[' exp ']' | '.' NAME)* ':' NAME args
    | '(' exp ')' ('[' exp ']' | '.' NAME)* ':' NAME args
    ;

args
    : '(' explist? ')'
    | tableconstructor
    | string
    ;

//functiondef
//    : 'function' funcbody
//    ;

funcbody
    : '(' parlist ')' (':' typehint)? block 'end'
    ;

/* lparser.c says "is 'parlist' not empty?"
 * That code does so by checking la(1) == ')'.
 * This means that parlist can derive empty.
 */
parlist
    : namelist (',' '...')?
    | '...'
    |
    ;

tableconstructor
    : '{' fieldlist? '}'
    ;

fieldlist
    : field (fieldsep field)* fieldsep?
    ;

field
    : '[' exp ']' '=' exp
    | NAME '=' exp
    | exp
    ;

fieldsep
    : ','
    | ';'
    ;

number
    : INT
    | HEX
    | FLOAT
    | HEX_FLOAT
    ;

string
    : NORMALSTRING
    | CHARSTRING
    | LONGSTRING
    ;

typehint
    : TYPE_I
    | TYPE_F
    | TYPE_S
    | TYPE_B
    | TYPE_V
    | TYPE_T
    ;