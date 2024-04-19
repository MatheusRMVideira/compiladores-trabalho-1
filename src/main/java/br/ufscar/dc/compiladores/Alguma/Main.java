// importando as bibliotecas necessárias
package br.ufscar.dc.compiladores.Alguma;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

public class Main {
    public static void main(String args[]) {

        try {
            String arquivoSaida = args[1];
            CharStream stream = CharStreams.fromFileName(args[0]);
            try (PrintWriter writer = new PrintWriter(arquivoSaida)) { //escrita arquivo
                var lexico = new Alguma(stream); //define lexico como Alguma

                Token token = null;

                //varrer os tokens, identificando-os e printando suas linhas lidas
                loop:
                while ((token = lexico.nextToken()).getType() != Token.EOF) {
                    String nomeToken = Alguma.VOCABULARY.getDisplayName(token.getType());

                    switch (nomeToken) {
                        case "INVALIDO":  //caracter nao identificado/invalido
                            writer.println("Linha " + token.getLine() + ": " + token.getText() + " - simbolo nao identificado");
                            break loop;
                        case "CADEIA_ABERTA":  //faltou o aspas
                            writer.println("Linha " + token.getLine() + ": cadeia literal nao fechada");
                            break loop;
                        case "COMENTARIO_ABERTO":  //comentario sem fim
                            writer.println("Linha " + token.getLine() + ": comentario nao fechado");
                            break loop;
                        case "PALAVRAS_CHAVE":  //token faz parte de uma palavra chave
                        case "OP_LOGICO":  //token eh operador logico
                        case "OP_ARITMETICO":  //token eh operador aritmetico
                        case "OP_RELACIONAL":  //token eh operador relacional
                            writer.println("<'" + token.getText() + "','" + token.getText() + "'>");
                            break;
                        default:  //caso token nao seja nada
                            writer.println("<'" + token.getText() + "'," + nomeToken + ">");
                            break;
                    }
                }
            }catch(FileNotFoundException ex) { //erro qnd n encontra arquivo
                System.err.println("O arquivo/diretório não existe:"+args[1]);
            }
        } catch (IOException errado) { //caso nao consiga fazer nada, gera erro
            System.err.println("Erro ao abrir arquivo:"+args[1]);
        }
    }
}
