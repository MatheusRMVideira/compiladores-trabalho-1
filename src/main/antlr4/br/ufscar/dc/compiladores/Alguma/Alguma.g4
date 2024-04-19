lexer grammar Alguma;


PALAVRAS_CHAVE  : 'algoritmo' | 'fim_algoritmo' | 'declare' | 'constante' | 'tipo' | 'literal' | 'inteiro' | 'real' | 'logico'	| 'verdadeiro' | 'falso' | 'registro' | 'fim_registro' | 'funcao' | 'fim_funcao' | 'var' | 'leia' | 'escreva'	| 'se' | 'entao' | 'senao' | 'fim_se' | 'caso' | 'seja' | 'fim_caso' | 'para' | 'ate' | 'faca' | 'fim_para' | 'enquanto' | 'fim_enquanto' | 'retorne' |  'procedimento'| 'fim_procedimento';


OP_LOGICO : 'e' | 'ou' | 'nao';
OP_ARITMETICO	: '+' | '-' | '*' | '/' | '%';
OP_RELACIONAL : '>' | '>=' | '<' | '<=' | '<>' | '=';


ATRIBUICAO: '<-';
PONT: '^';
ENDEREÇO: '&';
DOISPONTOS: ':';
VIRGULA: ',';
PONTO: '.';
INTERVALO: '..';
ABREPAR: '(';
FECHAPAR: ')';
ABRECHAVE: '[';
FECHACHAVE:']';

NUM_INT : ('0'..'9')+;
NUM_REAL : ('0'..'9')+ ('.' ('0'..'9')+)?;

ESPACO : ( ' ' | '\t' | '\r' | '\n' ) -> skip;


IDENT : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

CADEIA : '"' (~('\n' | '\r' | '"'))* '"';
COMENTARIO : '{' (~('\n'|'\r'|'}'))* '}' -> skip;


CADEIA_ABERTA : '"' (~('\n' | '\r' | '"'))* ('\n' | '\r');
COMENTARIO_ABERTO : '{' (~('\n'|'\r'|'}'))* ('\n'|'\r');


INVALIDO : .;
