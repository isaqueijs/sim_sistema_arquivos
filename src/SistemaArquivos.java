import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SistemaArquivos {

    private Memoria memoria;
    private Diretorio dir;
    private Diretorio dirRaiz;

    public SistemaArquivos(int tamanhoMemoria, int tamanhoBloco, Diretorio dir, Diretorio dirRaiz) {
        this.memoria = new Memoria(tamanhoMemoria, tamanhoBloco);
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
            if (this.memoria.alocarArquivo(arquivo)) {
                this.dir.adicionarArquivo(arquivo);
                return true;
            }
        }
        return false;
    }

    public boolean rm(String nome) {
        int index = this.dir.existeArquivo(nome);
        if (index > -1) {
            this.memoria.desalocarArquivo(this.dir.getArquivos().get(index));
            this.dir.getArquivos().remove(index);
            return true;
        }
        System.out.println("Arquivo não existe!");
        return false;
    }

    public boolean rmdir(String nome) {
        int index = this.dir.existeDiretorio(nome);
        if (index > -1 ) {
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

    public Memoria getMemoria() {
        return memoria;
    }

    public void setMemoria(Memoria memoria) {
        this.memoria = memoria;
    }

    public Diretorio getDir() {
        return dir;
    }

    public void setDir(Diretorio dir) {
        this.dir = dir;
    }
}
