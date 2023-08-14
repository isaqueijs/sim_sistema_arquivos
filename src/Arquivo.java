import java.time.LocalDateTime;

public class Arquivo {
    private String nome;
    private int tamanho;
    private LocalDateTime dataCriacao;
    private String localArquivo;
    private String proprietario;

    public Arquivo(String nome, int tamanho, String localArquivo, String proprietario) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.dataCriacao = LocalDateTime.now();
        this.localArquivo = localArquivo;
        this.proprietario = proprietario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanho() {
        return tamanho;
    }

    public LocalDateTime  getDataCriacao() {
        return dataCriacao;
    }


    public String getLocalArquivo() {
        if (localArquivo.equals("/")) {
            return localArquivo;
        }
        return "/" + localArquivo;
    }

    public void setLocalArquivo(String localArquivo) {
        this.localArquivo = localArquivo;
    }

    public String getProprietario() {
        return proprietario;
    }

    @Override
    public String toString() {
        return "Arquivo{" +
                "nome=" + nome + ", tamanho: " + tamanho +
                "KB, dataCriacao: " + dataCriacao +
                ", localArquivo: " + getLocalArquivo() +
                ", proprietario: " + proprietario + '}';
    }
}
