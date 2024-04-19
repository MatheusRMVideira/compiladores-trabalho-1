// importando as libs necessárias
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
            CharStream stream = CharStreams.fromFileName(args[0]);
            String arquivoSaida = args[1];
            try (PrintWriter writer = new PrintWriter(arquivoSaida)) { //arquivo de saida
                var lexico = new Alguma(stream); //define o lexico
                Token token = null;

                //varrer os tokens, identificando-os e printando eles
                loop:
                while ((token = lexico.nextToken()).getType() != Token.EOF) {
                    String nomeToken = Alguma.VOCABULARY.getDisplayName(token.getType());

                    switch (nomeToken) {
                        case "INVALIDO":  //token nao reconhecido
                            writer.println("Linha " + token.getLine() + ": " + token.getText() + " - simbolo nao identificado");
                            break loop; // Abortar execucao, erro
                        case "CADEIA_ABERTA":  //faltou aspas para fechar a cadeia
                            writer.println("Linha " + token.getLine() + ": cadeia literal nao fechada");
                            break loop; // Abortar execucao, erro
                        case "COMENTARIO_ABERTO":  //faltou colchetes para fechar o comentario
                            writer.println("Linha " + token.getLine() + ": comentario nao fechado");
                            break loop; // Abortar execucao, erro
                        case "PALAVRAS_CHAVE":  //token eh uma palavra chave
                        case "OP_LOGICO":  //token eh operador logico
                        case "OP_ARITMETICO":  //token eh operador aritmetico
                        case "OP_RELACIONAL":  //token eh operador relacional
                            writer.println("<'" + token.getText() + "','" + token.getText() + "'>");
                            break;
                        default:  //caso token nao seja nennhum dos acima
                            writer.println("<'" + token.getText() + "'," + nomeToken + ">");
                            break;
                    }
                }
            }catch(FileNotFoundException ex) { // tratamento de excecao do output
                System.err.println("O arquivo/diretório não existe:"+args[1]);
            }
        } catch (IOException errado) { //tratamento de excecao do input
            System.err.println("Erro ao abrir arquivo:"+args[1]);
        }
    }
}
