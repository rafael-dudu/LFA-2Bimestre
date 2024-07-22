package Main;

import java.io.Console;
import java.util.Scanner;

public class Main {
    // Declaração de variáveis para armazenar a quantidade de estados, quantidade de estados finais,
    // estado inicial, quantidade de símbolos no alfabeto principal e auxiliar, posição na fita,
    // quantidade total de estados, vetores de símbolos, vetor de marcadores e vetor de estados finais.
    private int qtdEst, qtdEstFin, estIni, qtdSbl, qtdSblAux, posFita, qtdEstTotal;
    private char vetSbl[], vetSblAux[], vetSblTotal[], vetMarcs[] = { '>', '<' };
    private boolean vetEstFin[];
    private String palavraTest;
    private Transicoes matTest[][];
    private static Scanner scan = new Scanner(System.in);
    private StringBuilder fita;

    // Construtor da classe Main
    public Main() {
        // Solicita a quantidade de estados e armazena na variável qtdEst
        System.out.println("Digite a quantidade de estados : ");
        this.qtdEst = scan.nextInt();
        
        // Solicita o estado inicial e valida se é menor ou igual à quantidade de estados
        do {
            System.out.println("Digite o estado inicial : ");
            this.estIni = scan.nextInt();
            if (estIni > qtdEst) {
                System.out.println("Estado inicial maior que quantidade de estados");
            }
        } while (estIni > qtdEst);

        // Solicita a quantidade de símbolos no alfabeto principal
        System.out.println("Digite a quantidade de simbolos no alfabeto ");
        this.qtdSbl = scan.nextInt();
        this.vetSbl = new char[qtdSbl];

        // Solicita e armazena cada símbolo do alfabeto principal
        for (int i = 1; i <= vetSbl.length; i++) {
            System.out.println("Digite o " + i + "º simbolo: ");
            this.vetSbl[i - 1] = scan.next().charAt(0);
        }

        // Solicita a quantidade de símbolos no alfabeto auxiliar
        System.out.println("Digite a quantidade de simbolos no alfabeto auxiliar: (Sem incluir marcador de inicio e branco)");
        this.qtdSblAux = scan.nextInt();
        this.vetSblAux = new char[qtdSblAux];

        // Solicita e armazena cada símbolo do alfabeto auxiliar
        for (int i = 1; i <= vetSblAux.length; i++) {
            System.out.println("Digite o " + i + "º simbolo: ");
            this.vetSblAux[i - 1] = scan.next().charAt(0);
        }

        // Solicita a quantidade de estados finais e valida se é menor ou igual à quantidade de estados
        do {
            System.out.println("Digite a quantidade de estados finais : ");
            qtdEstFin = scan.nextInt();
            if (qtdEstFin > qtdEst) {
                System.out.println("Estado final maior que quantidade de estados");
            }
        } while (qtdEstFin > qtdEst);

        // Inicializa o vetor de estados finais e solicita ao usuário para definir quais são os estados finais
        this.vetEstFin = new boolean[qtdEst];
        for (int i = 1; i <= qtdEst; i++) {
            int tmp;
            System.out.println("S" + i + " eh estado final?");
            tmp = scan.nextInt();
            if (tmp == 1) {
                vetEstFin[i - 1] = true;
                qtdEstFin--;
            }
            if (0 == qtdEstFin) {
                break;
            }
        }

        // Solicita o marcador de início e armazena no vetor vetMarcs
        System.out.println("Digite o marcador de inicio: ");
        this.vetMarcs[0] = scan.next().charAt(0);

        // Solicita o marcador de branco e armazena no vetor vetMarcs
        System.out.println("Digite o marcador de branco: ");
        this.vetMarcs[1] = scan.next().charAt(0);

        // Imprime a tabela de transição
        System.out.println("TABELA DE TRANSICAO");
        System.out.printf("%2s", " ");
        this.qtdEstTotal = qtdSbl + qtdSblAux + 2;

        // Imprime os símbolos do alfabeto principal
        for (int i = 0; i < qtdSbl; i++) {
            System.out.printf("%5c", vetSbl[i]);
        }

        // Imprime os símbolos do alfabeto auxiliar
        for (int i = 0; i < qtdSblAux; i++) {
            System.out.printf("%5c", vetSblAux[i]);
        }

        // Imprime os marcadores de início e branco
        for (int i = 0; i < vetMarcs.length; i++) {
            System.out.printf("%5c", vetMarcs[i]);
        }

        System.out.println();

        // Imprime os estados e suas respectivas transições na tabela de transição
        for (int i = 1; i <= qtdEst; i++) {
            System.out.printf("S%d", i);
            for (int j = 1; j <= qtdEstTotal; j++) {
                System.out.printf(" %2d,%d", i, j);
            }
            System.out.println();
        }

        // Inicializa a matriz de transições
        this.matTest = new Transicoes[qtdEst][qtdEstTotal];
        String leitura = scan.nextLine();
        
        // Solicita e armazena as transições da máquina de Turing
        for (int i = 1; i <= qtdEst; i++) {
            for (int j = 1; j <= qtdEstTotal; j++) {
                System.out.println("Digite o estado futuro da transicao: " + i + "," + j);
                leitura = scan.nextLine();
                if (leitura.equals("X")) {
                    matTest[i - 1][j - 1] = new Transicoes(false);
                    System.out.println("O campo sera anulado!");
                } else {
                    matTest[i - 1][j - 1] = new Transicoes(true);
                    matTest[i - 1][j - 1].setEstadoFut(Integer.valueOf(leitura));
                    System.out.println("Digite o alfabeto futuro da transicao: " + i + "," + j);
                    char c = scan.next().charAt(0);
                    matTest[i - 1][j - 1].setAlfFut(c);
                    System.out.println("Digite a direcao futura da transicao: " + i + "," + j + " (D para direita ou E para a esquerda)");
                    c = scan.next().charAt(0);
                    matTest[i - 1][j - 1].setDirecao(c);
                    scan.nextLine();
                }
                System.out.println("----------------------------------------");
            }
        }

        // Concatena todos os símbolos (alfabeto principal, auxiliar e marcadores) em um vetor total
        this.vetSblTotal = new char[qtdEstTotal];
        System.arraycopy(vetSbl, 0, vetSblTotal, 0, vetSbl.length);
        System.arraycopy(vetSblAux, 0, vetSblTotal, vetSbl.length, vetSblAux.length);
        System.arraycopy(vetMarcs, 0, vetSblTotal, (vetSblAux.length) + (vetSbl.length), vetMarcs.length);
    }

    // Método para solicitar a palavra a ser testada
    public void pegarPalavra() {
        Console con = System.console();
        System.out.println("Digite a palavra a ser testada");
        Scanner sc = new Scanner(con.reader());
        this.palavraTest = sc.nextLine();
        
        // Adiciona os marcadores de início e branco à palavra
        this.palavraTest = vetMarcs[0] + this.palavraTest;
        this.palavraTest = this.palavraTest + vetMarcs[1] + vetMarcs[1];
        this.fita = new StringBuilder(palavraTest);
        this.posFita = 1;
        sc.close();
        
        // Imprime a fita inicial
        System.out.println("Fita inicial: ");
        System.out.println(fita);
        
        // Inicia o teste da palavra
        testarPalavra(estIni - 1, fita.charAt(posFita));
    }

    // Método recursivo para testar a palavra
    private void testarPalavra(int i, char charAt) {
        for (int j = 0; j < qtdEstTotal; j++) {
            if (matTest[i][j].getExiste() && charAt == vetSblTotal[j]) {
                System.out.println("simbolo: " + charAt + " sendo substituido por: " + matTest[i][j].getAlfFut());
                System.out.println("fita indo para " + matTest[i][j].getDirecao());
                this.fita.setCharAt(posFita, matTest[i][j].getAlfFut());

                // Adiciona um marcador de branco se necessário
                if (charAt == this.vetMarcs[1] && matTest[i][j].getAlfFut() != this.vetMarcs[1]) {
                    this.fita.append(this.vetMarcs[1]);
                }

                // Move a posição na fita conforme a direção especificada na transição
                if (matTest[i][j].getDirecao() == 'D') {
                    this.posFita++;
                } else {
                    this.posFita--;
                }

                // Imprime a fita atual
                System.out.println("Fita atual ");
                System.out.println(this.fita);
                System.out.println("----------------------------------------");

                // Verifica se a posição atual é 1 e se o estado futuro é um estado final
                if (posFita == 1 && vetEstFin[matTest[i][j].getEstadoFut() - 1]) {
                    System.out.println("Palavra aceita!");
                    return;
                }

                // Chama recursivamente o método para continuar o teste
                testarPalavra(matTest[i][j].getEstadoFut() - 1, fita.charAt(posFita));
                return;
            }
        }
        // Se nenhuma transição válida for encontrada, a palavra é rejeitada
        System.out.println("Palavra nao aceita!");
        return;
    }
}
