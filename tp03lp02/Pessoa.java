package tp03lp02;

public class Pessoa {
    // Atributos protected (#)
    protected static int kp = 0;
    protected String nome;
    protected char sexo;
    protected int idade;

    // Construtor default
    public Pessoa() {
        setKp();
    }

    // Construtor com parâmetros
    public Pessoa(String nome, char sexo, int idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        setKp();
    }

    // Métodos Setters
    public void setKp() {
        kp++;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Métodos Getters
    public static int getKp() {
        return kp;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }
}