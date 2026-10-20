import java.util.Objects;

public class Filme {
    private String nome;
    private int ano;
    private String local;

    public Filme(String nome, int ano, String local) {
        this.nome = Objects.requireNonNull(nome);
        this.ano = ano;
        this.local = Objects.requireNonNull(local);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Filme outro = (Filme) o;
        return ano == outro.ano && nome.equals(outro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, ano);
    }

    @Override
    public String toString() {
        return nome + " (" + ano + ") - " + local;
    }
}
