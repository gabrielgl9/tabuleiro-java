package br.edu.uniritter.canoas.poo.jogo;

import br.edu.uniritter.canoas.poo.jogo.model.Dado;
import br.edu.uniritter.canoas.poo.jogo.model.Jogador;
import br.edu.uniritter.canoas.poo.jogo.model.MuitosJogadoresException;
import br.edu.uniritter.canoas.poo.jogo.model.Tabuleiro;
import br.edu.uniritter.canoas.poo.jogo.view.JogoView;
import br.edu.uniritter.canoas.poo.jogo.view.TabuleiroView;

public class JogoController {
    private static int qtdJogadores;
    private static Tabuleiro tabuleiro;
    private static int jogadorAtual = 0;
    private static boolean finalizado = false;

    public static void iniciarJogo() {
        tabuleiro = new Tabuleiro(50,20,20);
        qtdJogadores = JogoView.intQtdJogadores(2, 6);
        registrarJogadores();

       while(! finalizado) {
           iniciarJogada();
           if (!tabuleiro.verificaVencedor(tabuleiro.getJogadores().get(jogadorAtual), tabuleiro)) {
               proximoJogador();
               TabuleiroView.showSituacaoAtual(tabuleiro);
           } else {
               finalizado = true;
               JogoView.parabenizaVencedor(tabuleiro.getJogadores().get(jogadorAtual));
           }
       }

    }
    private static void proximoJogador() {
        jogadorAtual++;
        if(jogadorAtual == qtdJogadores) {
            jogadorAtual = 0;
        }
    }
    public static void registrarJogadores() {
        for (int i = 1; i <= qtdJogadores; i++) {
            String n = JogoView.InformeJogador(i);
            try {
                tabuleiro.addJogador(new Jogador(n));
            } catch (MuitosJogadoresException e) {
                e.printStackTrace();
            }
        }
    }
    private static void iniciarJogada() {
        JogoView.mostraJogadorAtual(tabuleiro.getJogadores().get(jogadorAtual));
        Dado dado = new Dado();
        tabuleiro.getJogadores().get(jogadorAtual).avanca(dado.jogar());
    }

}
