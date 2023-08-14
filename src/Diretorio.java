import java.util.ArrayList;

public class Diretorio {
    private String nome;
    private ArrayList<Arquivo> arquivos;
    private ArrayList<Diretorio> subDiretorios;

    public Diretorio(String nome) {
        this.nome = nome;
        this.arquivos = new ArrayList<>();
        this.subDiretorios = new ArrayList<>();
    }

    public boolean nomeJaUtilizado(String nome) {
        for (Arquivo arquivo : arquivos) {
            if (arquivo.getNome().equals(nome)) {
                System.out.println("Arquivo " + nome + " já existe.");
                return true;
            }
        }

        for (Diretorio subDiretorio : subDiretorios) {
            if (subDiretorio.getNome().equals(nome)) {
                System.out.println("Diretório " + nome + " já existe.");
                return true;
            }
        }

        return false;
    }

    public int existeArquivo(String nome) {
        for (int i = 0; i < arquivos.size(); i++) {
            Arquivo arquivo = arquivos.get(i);
            if (arquivo.getNome().equals(nome)) {
                return i;
            }
        }
        return -1;
    }

    public int existeDiretorio(String nome) {
        for (int i = 0; i < subDiretorios.size(); i++) {
            Diretorio subDiretorio = subDiretorios.get(i);
            if (subDiretorio.getNome().equals(nome)) {
                return i;
            }
        }
        return -1;
    }


    public void adicionarArquivo(Arquivo arquivo) {
        arquivos.add(arquivo);
    }

    public void adicionarSubdiretorio(Diretorio subDiretorio) {
        subDiretorios.add(subDiretorio);
    }

    public void listarConteudo() {

        for (Arquivo arquivo : arquivos) {
            System.out.println(arquivo.getNome());
        }

        for (Diretorio subDiretorio : subDiretorios) {
            System.out.println(subDiretorio.getNome());
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(ArrayList<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public ArrayList<Diretorio> getSubDiretorios() {
        return subDiretorios;
    }

    public void setSubDiretorios(ArrayList<Diretorio> subDiretorios) {
        this.subDiretorios = subDiretorios;
    }

    @Override
    public String toString() {
        return "Diretorio{" +
                "nome='" + nome + '\'' +
                ", arquivos=" + arquivos +
                ", subDiretorios=" + subDiretorios +
                '}';
    }
}
