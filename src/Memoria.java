import java.util.ArrayList;
import java.util.Arrays;

public class Memoria {
    private int tamanho;
    private Bloco[] blocos;
    private int tamanhoBloco;

    public Memoria(int tamanho, int tamanhoBloco) {
        this.tamanho = tamanho;
        this.tamanhoBloco = tamanhoBloco;
        this.blocos = gerarBlocos(tamanhoBloco);
    }

    private Bloco[] gerarBlocos(int tamanhoBloco) {
        int qtdBlocos = this.tamanho / tamanhoBloco;
        Bloco[] blocos = new Bloco[qtdBlocos];

        for (int i = 0; i < qtdBlocos; i++) {
            blocos[i] = new Bloco(i, tamanhoBloco, null, -1);
        }

        return blocos;
    }

    public boolean alocarArquivo(Arquivo arquivo) {
        int tamanhoArquivo = arquivo.getTamanho();
        int blocosNecessarios = (int) Math.ceil((double) tamanhoArquivo / this.tamanhoBloco);

        ArrayList<Bloco> blocosLivres = blocosLivres();// [0,2,4]
//      [0,1,0,1,0]
        if (blocosNecessarios <= blocosLivres.size()) {
            int blocosAlocados = 0;
            for (int i = 0; i < getBlocos().length && blocosAlocados < blocosNecessarios; i++) {
                if (getBlocos()[i].getArquivo() == null) {
                    getBlocos()[i].setArquivo(arquivo);
                    blocosAlocados++;
                    if (blocosAlocados < blocosNecessarios) {
                        getBlocos()[i].setProxBloco(blocosLivres.get(blocosAlocados).getId());
                    }

                }
            }
            return true;
        } else {
            System.out.println("Não há espaço suficiente para alocar o arquivo na memória.");
            return false;
        }
    }

    public void desalocarArquivo(Arquivo arquivo) {
        for (Bloco bloco : getBlocos()) {
            if (bloco.getArquivo().equals(arquivo)) {
                bloco.setArquivo(null);
                bloco.setProxBloco(-1);
            }
        }
    }


    public ArrayList blocosLivres() {
        ArrayList<Bloco> blocosLivres = new ArrayList<>();
        for (Bloco bloco : getBlocos()) {
            if (bloco.getArquivo() == null) {
                blocosLivres.add(bloco);
            }
        }
        return blocosLivres;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Bloco[] getBlocos() {
        return blocos;
    }

    public void setBlocos(Bloco[] blocos) {
        this.blocos = blocos;
    }

    @Override
    public String toString() {
        return "Memoria - " +
                "tamanho: " + tamanho + "KB" +
                "\nblocos=" + Arrays.toString(blocos);
    }
}