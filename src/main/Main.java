package main;

import models.Servidor;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Servidor> servidores = new ArrayList<>();

    public static void main(String[] args) {

        var user1 = new Usuario("João");
        addUsuario(user1);
        var user2 = new Usuario("Maria");
        addUsuario(user2);
        var user3 = new Usuario("Carlos");
        addUsuario(user3);
        var user4 = new Usuario("Luana");
        addUsuario(user4);


        var server1 = new Servidor("Servidor Legal");
        addServer(server1);
        var server2 = new Servidor("Servidor Da Familia");
        addServer(server2);
        var server3 = new Servidor("Games Server");
        addServer(server3);
        var server4 = new Servidor("Servidor de Arte");
        addServer(server4);


        user1.entrarServidor(server1);
        user1.entrarServidor(server2);
        user1.entrarServidor(server3);

        user2.entrarServidor(server1);
        user2.entrarServidor(server2);

        user3.entrarServidor(server1);
        user3.entrarServidor(server2);
        user3.entrarServidor(server3);
        user3.entrarServidor(server4);

        user4.entrarServidor(server3);
        user4.entrarServidor(server4);

//        server1.getMensagemsServidor().forEach(m -> System.out.printf("%s - %s: %s\n",
//                m.getDataEnvio().format(DateTimeFormatter.ofPattern("HH:mm")),
//                m.getAutorMensagem().getNomeUsuario(),
//                m.getConteudo()));

        var scanner = new Scanner(System.in);
        var opcao = -1;
        Servidor servidorEscolhido;
        Usuario usuarioEscolhido = null;

        while (opcao != 0) {
            while (usuarioEscolhido == null && opcao != 0) {
                System.out.println("Escolha o usuario (1-4) ou 0 para sair");
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 0:
                        System.out.println("Saindo");
                        break;
                    case 1:
                        usuarioEscolhido = user1;
                        break;
                    default:
                        System.out.println("ERRO");
                        break;
                }
            }

            while (opcao != 0) {
                System.out.println("Digite que servidore quer checar (1-4) ou 0 para sair");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 0:
                        System.out.println("Saindo");
                        break;
                    case 1:
                        servidorEscolhido = server1;
                        while (true) {
                            server1.mostrarMensagens();
                            System.out.println("Digite sua mensagem ou 0 para sair:");
                            var texto = scanner.nextLine();
                            if (texto.isEmpty()) {
                                scanner.nextLine();
                            } else if (texto.equals("0")) {
                                opcao = 0;
                                break;
                            } else {
                                usuarioEscolhido.enviarMensagem(texto, servidorEscolhido);
                            }

                        }
                        break;
                }
            }
        }
    }

    public static void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static void addServer(Servidor servidor) {
        servidores.add(servidor);
    }

    public static List<Usuario> getListaUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public static List<Servidor> getListaServidores() {
        return new ArrayList<>(servidores);
    }
}
