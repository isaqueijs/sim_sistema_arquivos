import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SistemaArquivos {

    private ArrayList<Disco> discos;
    private int qtdDiscos = 3;
    private Diretorio dir;
    private Diretorio dirRaiz;

    public SistemaArquivos(int tamanhoMemoria, int tamanhoBloco, Diretorio dir, Diretorio dirRaiz) {

        this.discos = new ArrayList<>();

        for (int i = 0; i < this.qtdDiscos; i++) {
            this.discos.add(new Disco(tamanhoMemoria, tamanhoBloco, i));
        }

        this.dir = dir;
        this.dirRaiz = dirRaiz;
    }

    public void pwd() {
        if (!this.dir.getNome().equals("/")) {
            System.out.println("/" + this.dir.getNome());
        } else {
            System.out.println(this.dir.getNome());
        }
    }

    public void ls() {
        this.dir.listarConteudo();
    }

    public boolean touch(String nome, int tamanho, String proprietario) {
        if (validarNome(nome)) {
            Arquivo arquivo = new Arquivo(nome, tamanho, this.dir.getNome(), proprietario);
            if (distribuirArq(arquivo)) return true;
        }
        return false;
    }

    public boolean rm(String nome) {
        int index = this.dir.existeArquivo(nome);
        if (index > -1) {

            for (Disco disco : discos) {
                disco.desalocarArquivo(this.dir.getArquivos().get(index));
            }
            this.dir.getArquivos().remove(index);
            return true;
        }
        System.out.println("Arquivo não existe!");
        return false;
    }

    public boolean rmdir(String nome) {
        int index = this.dir.existeDiretorio(nome);
        if (index > -1) {
            if (this.dir.getSubDiretorios().get(index).getArquivos().size() > 0) {
                System.out.println("Falha ao remover '" + nome + "':Diretório não está vazio");
                return false;
            }

            this.dir.getSubDiretorios().remove(index);
            return true;
        }
        System.out.println("Diretório não existe!");
        return false;
    }

    public void cd(String nome) {
        if (nome.equals("..") && this.dir.getNome().equals("/")) {
            System.out.println("Você já está no diretório raiz '/'");
        } else if (nome.equals("..")) {
            setDir(this.dirRaiz);
        } else {
            int index = this.dir.existeDiretorio(nome);
            if (index > -1) {
                setDir(this.dir.getSubDiretorios().get(index));
            } else {
                System.out.println("Diretório não encontrado.");
            }
        }
    }

    public boolean mkdir(String nome) {
        if (validarNome(nome)) {
            Diretorio subDir = new Diretorio(nome);
            this.dir.adicionarSubdiretorio(subDir);
            return true;
        }
        return false;
    }

    public boolean validarNome(String nome) {
        if (nome == null || nome.isEmpty() || this.dir.nomeJaUtilizado(nome)) {
            return false;
        }

        String pattern = "^[a-zA-Z0-9._-]+$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(nome);

        return matcher.matches();
    }

    public boolean distribuirArq(Arquivo arquivo) {
        if (discos.get(0).alocarArquivo(arquivo) && discos.get(1).alocarArquivo(arquivo)
                && discos.get(2).alocarArquivo(arquivo)) {
            this.dir.adicionarArquivo(arquivo);
            return true;
        }

        return false;
    }

    public void printDiscos() {
        System.out.print("--------------------------------------------------------------\nConteúdo dos Discos:");
        for (Disco disco : getDiscos()) {
            System.out.println("\n--------------------------------------------------------------");
            System.out.print(disco.toString());
        }
        System.out.println("\n--------------------------------------------------------------");
    }

    public ArrayList<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(ArrayList<Disco> discos) {
        this.discos = discos;
    }

    public Diretorio getDir() {
        return dir;
    }

    public void setDir(Diretorio dir) {
        this.dir = dir;
    }
}
