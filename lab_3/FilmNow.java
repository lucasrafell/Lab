import java.util.Scanner;

public class FilmNow {
    private Filme[] filmes = new Filme[100];
    private Filme[] hotList = new Filme[10];
    private Scanner entrada = new Scanner(System.in);
    private boolean executando = true;

    public String adicionarFilme() {
        int posicao = lerPosicao("Posicao do filme (1 a 100): ", filmes.length);
        if (posicao < 0) {
            return "Posicao invalida.";
        }
        if (filmes[posicao] != null) {
            return "Posicao ocupada.";
        }
        String nome = lerTexto("Nome: ");
        Integer ano = lerInteiro("Ano: ");
        String local = lerTexto("Local: ");
        if (nome.isEmpty() || ano == null || local.isEmpty()) {
            return "Dados invalidos.";
        }
        Filme filme = new Filme(nome, ano, local);
        for (Filme cadastrado : filmes) {
            if (filme.equals(cadastrado)) {
                return "Filme ja cadastrado.";
            }
        }
        filmes[posicao] = filme;
        return "Filme adicionado.";
    }

    public String visualizarFilme() {
        Filme filme = selecionarFilme();
        return filme == null ? "Filme nao encontrado." : filme.getNome();
    }

    public String detalhaFilme() {
        Filme filme = selecionarFilme();
        return filme == null ? "Filme nao encontrado." : filme.toString();
    }

    public String mostraTodos() {
        return listar(filmes, "Nenhum filme cadastrado.");
    }

    public void sair() {
        executando = false;
    }

    public String atribuirHotList() {
        Filme filme = selecionarFilme();
        if (filme == null) {
            return "Filme nao encontrado.";
        }
        int posicao = lerPosicao("Posicao na hot list (1 a 10): ", hotList.length);
        if (posicao < 0) {
            return "Posicao invalida.";
        }
        if (hotList[posicao] != null) {
            return "Posicao ocupada.";
        }
        for (Filme favorito : hotList) {
            if (filme.equals(favorito)) {
                return "Filme ja esta na hot list.";
            }
        }
        hotList[posicao] = filme;
        return "Filme adicionado a hot list.";
    }

    public String exibirHotList() {
        return listar(hotList, "Hot list vazia.");
    }

    public String removerHotList() {
        int posicao = lerPosicao("Posicao na hot list (1 a 10): ", hotList.length);
        if (posicao < 0) {
            return "Posicao invalida.";
        }
        if (hotList[posicao] == null) {
            return "Posicao vazia.";
        }
        hotList[posicao] = null;
        return "Filme removido da hot list.";
    }

    private Filme selecionarFilme() {
        int posicao = lerPosicao("Posicao do filme (1 a 100): ", filmes.length);
        return posicao < 0 ? null : filmes[posicao];
    }

    private String listar(Filme[] lista, String mensagemVazia) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] != null) {
                if (resultado.length() > 0) {
                    resultado.append(System.lineSeparator());
                }
                resultado.append(i + 1).append(" - ").append(lista[i]);
            }
        }
        return resultado.length() == 0 ? mensagemVazia : resultado.toString();
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.hasNextLine() ? entrada.nextLine().trim() : "";
    }

    private Integer lerInteiro(String mensagem) {
        try {
            return Integer.valueOf(lerTexto(mensagem));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private int lerPosicao(String mensagem, int limite) {
        Integer posicao = lerInteiro(mensagem);
        return posicao == null || posicao < 1 || posicao > limite ? -1 : posicao - 1;
    }

    public static void main(String[] args) {
        FilmNow sistema = new FilmNow();
        while (sistema.executando) {
            System.out.println("1 - Adicionar filme | 2 - Visualizar filme | 3 - Detalhar filme");
            System.out.println("4 - Mostrar todos | 5 - Atribuir hot list | 6 - Exibir hot list");
            System.out.println("7 - Remover da hot list | 0 - Sair");
            System.out.print("Opcao: ");
            if (!sistema.entrada.hasNextLine()) {
                break;
            }
            String opcao = sistema.entrada.nextLine().trim();
            switch (opcao) {
                case "1": System.out.println(sistema.adicionarFilme()); break;
                case "2": System.out.println(sistema.visualizarFilme()); break;
                case "3": System.out.println(sistema.detalhaFilme()); break;
                case "4": System.out.println(sistema.mostraTodos()); break;
                case "5": System.out.println(sistema.atribuirHotList()); break;
                case "6": System.out.println(sistema.exibirHotList()); break;
                case "7": System.out.println(sistema.removerHotList()); break;
                case "0": sistema.sair(); break;
                default: System.out.println("Opcao invalida.");
            }
        }
    }
}
