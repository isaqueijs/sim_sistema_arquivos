import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------\n"
                + "Sistema de Arquivos" + "\n--------------------------------------------------------------");
        System.out.print("Digite o tamanho da memória física (KB): ");
        int tamanhoMemoria = ler.nextInt();

        System.out.print("Digite o tamanho dos blocos na memória (KB): ");
        int tamanhoBloco = ler.nextInt();
        ler.nextLine();
        System.out.print("Digite seu nome de usuário: ");
        String usuario = ler.nextLine();

        Diretorio dir = new Diretorio("/");
        SistemaArquivos sistema = new SistemaArquivos(tamanhoMemoria, tamanhoBloco, dir, dir);

        String opcao;
        String linhaComando;
        String comando[];

        do {
            System.out.print(usuario + "@sistema_arquivos:" + dir.getNome() + "$ ");
            linhaComando = ler.nextLine();
            comando = linhaComando.split(" ");
            opcao = comando[0];

            switch (opcao) {
                case "pwd":
                    sistema.pwd();
                    break;
                case "ls":
                    sistema.ls();
                    break;
                case "touch":
                    if (comando.length == 3) {
                        sistema.touch(comando[1], Integer.parseInt(comando[2]), usuario);
                        System.out.println(sistema.getMemoria());
                        break;
                    }
                    System.out.println("O comando touch precisa de 2 parâmetros (nome_arquivo tamanho). ex: 'touch nomearquivo.txt 8'");
                    break;
                case "mkdir":
                    if (comando.length == 2) {
                        sistema.mkdir(comando[1]);
                        break;
                    }
                    System.out.println("O comando mkdir de 1 parâmetro (nome_diretório). ex: 'mkdir nome_diretório'");
                    break;
                case "cd":
                    if (comando.length == 2) {
                        sistema.cd(comando[1]);
                        if (comando[1].equals("..")) {
                            dir.setNome("/");
                        } else {
                            dir.setNome("/" + comando[1]);
                        }
                        break;
                    }
                    System.out.println("O comando cd precisa de 1 parâmetro (nome_diretório). ex: 'cd nome_diretório', 'cd ..'");
                    break;
                case "rm":
                    if (comando.length == 2) {
                        sistema.rm(comando[1]);
                        System.out.println(sistema.getMemoria());
                        break;
                    }
                    System.out.println("O comando rm precisa de 1 parâmetro (nome_arquivo). ex: 'rm nome_arquivo'");
                    break;
                case "rmdir":
                    if (comando.length == 2) {
                        sistema.rmdir(comando[1]);
                        System.out.println(sistema.getMemoria());
                        break;
                    }
                    System.out.println("O comando rmdir precisa de 1 parâmetro (nome_diretório). ex: 'rmdir nome_diretório'");
                    break;
                case "free":
                    System.out.println(sistema.getMemoria());
                    break;
                case "clear":
                    clear();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Comando " + comando[0] + " não é suportado!");
            }

        } while (!opcao.equals("exit"));
        
        ler.close();
    }

    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}