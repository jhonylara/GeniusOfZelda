package jhonylara.geniusofzelda;

public class Jogador {

    private int id;
    private String nome;
    private int level;
    private int vidas;
    private int medalhoes;
    private int pontos;

    public  Jogador(){

    }

    public Jogador(int id, String nome, int level, int vidas, int medalhoes, int pontos) {
        this.id = id;
        this.nome = nome;
        this.level = level;
        this.vidas = vidas;
        this.medalhoes = medalhoes;
        this.pontos = pontos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getMedalhoes() {
        return medalhoes;
    }

    public void setMedalhoes(int medalhoes) {
        this.medalhoes = medalhoes;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
