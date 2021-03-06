/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ranking;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Jogador {

    String nome;
    int pontuacao;
}

public class Ranking {

    public static Ranking rank = new Ranking();

    void ordenacao(Jogador[] vet, int cont) {
        Jogador aux = new Jogador();
        for (int i = 0; i < cont-1; i++) {
            for (int j = i + 1; j < cont; j++) {
                if (vet[i] != null) {
                    if (vet[i].pontuacao < vet[j].pontuacao) {
                        aux = vet[i];
                        vet[i] = vet[j];
                        vet[j] = aux;
                    }
               }
            }
        }
    }

    void escreverVetor(Jogador[] vetor) {

    }

    void OrdenarRanking(Jogador player1, Jogador player2) {

        Jogador[] vetRanking = new Jogador[12];
        int cont = 0;

        File diretorio = new File("Arquivos Jogador");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
        File arquivo = new File("Arquivos Jogador/Ranking.txt");
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException ex) {
                System.out.println("Erro na leitura");
            }
        }
        try {
            FileReader leitura = new FileReader(arquivo);
            BufferedReader leitura_buff = new BufferedReader(leitura);
            String linha = leitura_buff.readLine();
            while (!linha.equals("")){
                if (!linha.equals("") && (cont < 10)) {
                    Jogador user = new Jogador();
                    String[] line = linha.split(":");
                    user.nome = line[0];
                    user.pontuacao = Integer.parseInt(line[1]);
                    vetRanking[cont] = user;
                    cont++;
                }
                linha = leitura_buff.readLine();
            } //para quando a linha for igual a null
        } catch (IOException ex) {
            System.out.println("Erro na leitura");
        }
        System.out.println("Numero de jogadores no arquivo é de: " + cont);
        
        for (int i = 0; i < cont; i++) {
            if (vetRanking[i] != null) {
                System.out.println(vetRanking[i].nome + ":" + vetRanking[i].pontuacao);
            }

        }
        
        ///Ordenação do vetor para a escrita do ranking
        if (cont < 10) {
            vetRanking[cont + 1] = player1;
            cont++;
            vetRanking[cont] = player2;
            rank.ordenacao(vetRanking, cont); ///ordenação do vetor com a chamada do método
        }
        if (player1.pontuacao > player2.pontuacao) {
            vetRanking[cont + 1] = player1;
        } else {
            vetRanking[cont + 1] = player2;
        }
        rank.ordenacao(vetRanking, cont);///ordenação do vetor com a chamada do método

//        for (int i = 0; i < cont; i++) {
//            if (vetRanking[i] != null) {
//                System.out.println(vetRanking[i].nome + ":" + vetRanking[i].pontuacao);
//            }
//
//        }

        try {
            //objeto que faz interação com o arquivo, o parametro é para que seja possível
            //continuar escrevendo no arquivo
            FileWriter arquivoEscreva = new FileWriter(arquivo);
            //objeto que faz a escrita no arquivo
            BufferedWriter escreva = new BufferedWriter(arquivoEscreva);

            for (int i = 0; i < cont; i++) {
                if (vetRanking[i] != null) {
                    escreva.write(vetRanking[i].nome + ":" + vetRanking[i].pontuacao);
                    escreva.newLine();
                }
            }
            escreva.close();
            arquivoEscreva.close();
        } catch (IOException ex) {
            System.out.println("Falha na escrita do nome");
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here

        Jogador jogador1 = new Jogador();
        jogador1.nome = "Herbert";
        jogador1.pontuacao = 9000;

        Jogador jogador2 = new Jogador();
        jogador1.nome = "Ronisson";
        jogador1.pontuacao = 2000;

        rank.OrdenarRanking(jogador1, jogador2);

    }
}
