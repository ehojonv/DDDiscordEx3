package services;

import main.Main;
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

    public void iniciarMenu() {
        escolherUsuario();
    }

    private void escolherUsuario() {
        List<Servidor> servidoresDoUsuario;
        while (opcao != 0 && usuarioEscolhido == null) {
            try {

                System.out.println("\nLogar com qual usuario:");
                usuarios.forEach(u -> System.out.printf("%d. %s\n", (usuarios.indexOf(u) + 1), u.getNomeUsuario()));
                System.out.println("""
                        0. Logoff
                        ======================================
                        Digite a opção desejada:""");
                opcao = scanner.nextInt();

                if (opcao == 0) {
                    System.out.println("Saindo...");
                    continue;
                } else {
                    if (1 <= opcao && opcao <= usuarios.size() + 1) {
                        usuarioEscolhido = usuarios.get(opcao - 1);
                        servidoresDoUsuario = servidores.stream()
                                .filter(s -> s.getUsuariosServidor().contains(usuarioEscolhido))
                                .toList();
                    } else {
                        System.out.println("Opção Inválida");
                        continue;
                    }
                }

                escolherServidor(servidoresDoUsuario);
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Valor inválido");
            }
        }
    }

    private void escolherServidor(List<Servidor> servidoresDoUsuario) {
        while (opcao != 0 && opcao != -1) {
            try {
                System.out.println("\nEscolha um servidor para entrar:");
                servidoresDoUsuario.forEach(s -> System.out.printf("%d. %s\n", (servidoresDoUsuario.indexOf(s) + 1), s.getNomeServidor()));
                System.out.println("""
                        0. Logoff
                        ======================================
                        Digite a opção desejada:""");
                opcao = scanner.nextInt();

                if (opcao == 0) {
                    System.out.println("Saindo...");
                    continue;
                } else {
                    if (1 <= opcao && opcao <= (servidoresDoUsuario.size() + 1)) {
                        Servidor servidorEscolhido = servidoresDoUsuario.get(opcao - 1);
                        conversarNoServidor(servidorEscolhido);
                    } else {
                        System.out.println("Opção Inválida");
                        continue;
                    }
                }
                menuDeTroca();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Valor Inválido");
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
                        System.out.println("Saindo...");
                        break;
                    case 1:
                        break;
                    case 2:
                        usuarioEscolhido = null;
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Valor inválido");
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
}
