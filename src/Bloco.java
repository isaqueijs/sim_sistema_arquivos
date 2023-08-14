public class Bloco {
    private int id;
    private int tamanho;
    private Arquivo arquivo;
    private int proxBloco;

    public Bloco(int id, int tamanho, Arquivo arquivo, int proxBloco) {
        this.id = id;
        this.tamanho = tamanho;
        this.arquivo = arquivo;
        this.proxBloco = proxBloco;
    }

    public int getId() {
        return id;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public int getProxBloco() {
        return proxBloco;
    }

    public void setProxBloco(int proxBloco) {
        this.proxBloco = proxBloco;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", tamanho: " + tamanho +
                ", arquivo: " + arquivo +
                ", proxBloco: " + proxBloco +
                "}\n";
    }
}
