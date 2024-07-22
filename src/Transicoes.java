package Main;

// Definição da classe Transicoes
public class Transicoes {
    // Declaração de variáveis privadas da classe
    private boolean existe;    // Indica se a transição existe
    private int estadoFut;     // Estado futuro após a transição
    private char direcao;      // Direção da fita após a transição ('D' para direita ou 'E' para esquerda)
    private char alfFut;       // Símbolo que substituirá o atual na fita

    // Construtor da classe que inicializa a variável existe
    public Transicoes(boolean b) {
        this.existe = b;
    }

    // Método getter para obter o símbolo futuro
    public char getAlfFut() {
        return alfFut;
    }

    // Método getter para obter a direção da transição
    public char getDirecao() {
        return direcao;
    }

    // Método getter para obter o estado futuro
    public int getEstadoFut() {
        return estadoFut;
    }

    // Método getter para verificar se a transição existe
    public boolean getExiste() {
        return existe;
    }

    // Método setter para definir o símbolo futuro
    public void setAlfFut(char alfFut) {
        this.alfFut = alfFut;
    }

    // Método setter para definir a direção da transição
    public void setDirecao(char direcao) {
        this.direcao = direcao;
    }

    // Método setter para definir o estado futuro
    public void setEstadoFut(int estadoFut) {
        this.estadoFut = estadoFut;
    }

    // Método setter para definir se a transição existe
    public void setExiste(boolean existe) {
        this.existe = existe;
    }
}
