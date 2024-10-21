package services;

import main.Main;
import models.Mensagem;
import models.Servidor;
import models.Usuario;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final List<Usuario> usuarios = Main.getListaUsuarios();
    private static final List<Servidor> servidores = Main.getListaServidores();
    private int opcao = -1;
    private final Scanner scanner = new Scanner(System.in);
    private Usuario usuarioEscolhido = null;

    // Inicia menu de usuários
    public void iniciarMenuUsuarios() {
        escolherUsuario();
    }

    private void escolherUsuario() {
        List<Servidor> servidoresDoUsuario;
        while (opcao != 0 && usuarioEscolhido == null) {
            try {
                System.out.println("Bem vindo ao \033[1;4mDDDiscord\033[22;24m\nGostaria de logar com qual usuario:");
                usuarios.forEach(u -> System.out.printf("%d. %s\n", (usuarios.indexOf(u) + 1), u.getNomeUsuario()));
                System.out.println("""
                        0. Logoff
                        ======================================
                        Digite a opção desejada:""");
                opcao = scanner.nextInt();

                if (opcao == 0) {
                    System.out.println("\033[93mSaindo...\033[39m");
                    continue;
                } else {
                    if (1 <= opcao && opcao <= usuarios.size()) {
                        usuarioEscolhido = usuarios.get(opcao - 1);
                        servidoresDoUsuario = servidores.stream()
                                .filter(s -> s.getUsuariosServidor().contains(usuarioEscolhido))
                                .toList();
                    } else {
                       System.out.println("\033[91mOpção Inválida\033[39m");
                        continue;
                    }
                }

                escolherServidor(servidoresDoUsuario);
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("\033[91mValor Inválido\033[39m");
            }
        }
    }

    private void escolherServidor(List<Servidor> servidoresDoUsuario) {
        while (opcao != 0 && opcao != -1) {
            try {
                System.out.println("\nEscolha um \033[4mservidor\033[24m para entrar:");
                servidoresDoUsuario.forEach(s -> System.out.printf("%d. %s\n", (servidoresDoUsuario.indexOf(s) + 1), s.getNomeServidor()));
                System.out.println("""
                        0. Logoff
                        ======================================
                        Digite a opção desejada:""");
                opcao = scanner.nextInt();

                if (opcao == 0) {
                    System.out.println("\033[93mSaindo...\033[39m");
                    continue;
                } else {
                    if (1 <= opcao && opcao <= servidoresDoUsuario.size()) {
                        Servidor servidorEscolhido = servidoresDoUsuario.get(opcao - 1);
                        conversarNoServidor(servidorEscolhido);
                    } else {
                       System.out.println("\033[91mOpção Inválida\033[39m");
                        continue;
                    }
                }
                menuDeTroca();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("\033[91mValor Inválido\033[39m");
            }
        }
    }

    private void menuDeTroca() {
        while (!(0 <= opcao && opcao <= 2)) {
            try {
                System.out.println("""
                        
                        Logar em um novo:
                        1. Servidor
                        2. Usuário
                        0. Logoff
                        ======================================
                        Digite a opção desejada:""");
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 0:
                        System.out.println("\033[93mSaindo...\033[39m");
                        break;
                    case 1:
                        break;
                    case 2:
                        usuarioEscolhido = null;
                        break;
                    default:
                       System.out.println("\033[91mOpção Inválida\033[39m");
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("\033[91mValor Inválido\033[39m");
            }
        }
        if (opcao == 2) {
            opcao = -1;
        }
    }

    private void conversarNoServidor(Servidor servidorEscolhido) {
        scanner.nextLine();
        while (opcao != -1) {
            servidorEscolhido.mostrarMensagens();
            System.out.println("Digite sua mensagem ou 0 para logoff:");
            var texto = scanner.nextLine();
            if (!texto.isEmpty()) {
                if (texto.equals("0")) {
                    opcao = -1;
                } else {
                    usuarioEscolhido.enviarMensagem(texto, servidorEscolhido);
                }
            }
        }
    }

    public void iniciarMenuAdm() {
        opcao = -1;
        while (opcao != 0) {
            System.out.println("""
                    1. Filtrar/Ordenar mensagens
                    2. Estatisticas servidores
                    0. Sair
                    """);
            opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    exibirMenuMensagens();
                    break;
                case 2:
                    exibirEstatisticasServidores();
                    break;
                default:
                    System.out.println("ero");
                    break;
            }
        }
    }

    private void exibirEstatisticasServidores() {

    }

    private void exibirMenuMensagens() {
        while (opcao != 0) {
            System.out.println("""
                    1. Filtrar mensagens
                    2. Ordenar mensagens
                    0. Sair
                    """);
            opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    filtrarMensagens();
                    break;
                case 2:
                    ordenarMensagens();
                    break;
                default:
                    System.out.println("ero");
                    break;
            }
        }
    }

    private void ordenarMensagens() {

    }

    private void filtrarMensagens() {
        while (opcao != 0) {
            System.out.println("""
                    filtrar por
                    1. Autor
                    2. Texto
                    0. Sair
                    """);
            opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    filtrarMensagensPorAutor();
                    break;
                case 2:
                    filtrarMensagensPorTermo();
                    break;
                default:
                    System.out.println("ero");
                    break;
            }
        }
    }

    private void filtrarMensagensPorTermo() {

    }

    private void filtrarMensagensPorAutor() {
        System.out.println("Digite o nome do autor");
        var nomeAutor = scanner.next();
        var autor = usuarios.stream()
                .filter(u -> u.getNomeUsuario().equalsIgnoreCase(nomeAutor))
                .toList().getFirst();
        System.out.println(autor);

        servidores.stream()
                .filter(s -> s.getUsuariosServidor().contains(autor))
                .toList()        //MUDAR AQUI OLHA <<========================================================================
                .forEach(s -> {
                    System.out.println("Servidor: " + s.getNomeServidor());
                    s.getMensagemsServidor().stream()
                            .filter(m -> m.getAutorMensagem() == autor)
                            .forEach(Mensagem::mostrarMensagem);
                });
    }
}
