package services;

import main.Main;
import models.Mensagem;
import models.Servidor;
import models.Usuario;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                                        
                    Menu de organização do DDDiscord
                    1. Filtrar/Ordenar mensagens
                    2. Estatisticas servidores
                    0. Sair
                    ======================================
                    Digite a opção desejada:""");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    exibirMenuMensagens();
                    break;
                case 2:
                    exibirMenuEstatisticasServidores();
                    break;
                default:
                    System.out.println("ero");
                    break;
            }
        }
    }

    private void exibirMenuMensagens() {
        while (opcao != 0) {
            System.out.println("""
                                        
                    Deseja fazer o que:
                    1. Filtrar mensagens
                    2. Ordenar mensagens
                    0. Sair
                    ======================================
                    Digite a opção desejada:""");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    exibirMenuFiltrarMensagens();
                    break;
                case 2:
                    exibirMenuOrdenarMensagens();
                    break;
                default:
                    System.out.println("ero");
                    break;
            }
        }
    }

    private void exibirMenuFiltrarMensagens() {
        while (opcao != 0) {
            System.out.println("""
                                        
                    Filtrar por:
                    1. Autor
                    2. Texto
                    0. Sair
                    ======================================
                    Digite a opção desejada:""");
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

    private void filtrarMensagensPorAutor() {
        System.out.println("\nDigite o nome do autor:");
        var nomeAutor = Normalizer.normalize(scanner.next(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        var autor = usuarios.stream()
                .filter(u -> Normalizer.normalize(u.getNomeUsuario(), Normalizer.Form.NFD)
                        .replaceAll("\\p{M}", "")
                        .equalsIgnoreCase(nomeAutor))
                .toList().getFirst();

        var servidoresDoUsuario = servidores.stream()
                .filter(s -> s.getUsuariosServidor().contains(autor))
                .collect(Collectors.toList());

        servidoresDoUsuario.removeIf(servidor -> servidor.getMensagemsServidor().stream()
                .noneMatch(s -> s.getAutorMensagem() == autor));

        System.out.println("Filtrando mensagens de " + autor.getNomeUsuario() + ":");
        servidoresDoUsuario.forEach(s -> {
            System.out.println("\nServidor: " + s.getNomeServidor());
            s.getMensagemsServidor().stream()
                    .filter(m -> m.getAutorMensagem() == autor)
                    .forEach(Mensagem::mostrarMensagem);
        });
    }

    private void filtrarMensagensPorTermo() {
        System.out.println("\nDigite o termo:");
        var termo = scanner.next();

        var servidoresComTermo = servidores.stream()
                .filter(s -> s.getMensagemsServidor().stream()
                        .anyMatch(m -> m.getConteudo().contains(termo)))
                .toList();

        servidoresComTermo.forEach(s -> {
            System.out.println("\nServidor: " + s.getNomeServidor());
            s.getMensagemsServidor().stream()
                    .filter(m -> m.getConteudo().contains(termo))
                    .forEach(Mensagem::mostrarMensagem);
        });
    }

    private void exibirMenuOrdenarMensagens() {
        while (opcao != 0) {
            System.out.println("""
                                        
                    Deseja Ordenar por:
                    1. Data
                    2. Autor
                    0. Sair
                    ======================================
                    Digite a opção desejada:""");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    ordenarMensagensPorData();
                    break;
                case 2:
                    ordenarMensagensPorAutor();
                    break;
                default:
                    System.out.println("ero");
                    break;
            }
        }

    }

    private void ordenarMensagensPorData() {
        servidores.forEach(s -> {
            if (!s.getMensagemsServidor().isEmpty()) {
                System.out.println("\nServidor: " + s.getNomeServidor());
                s.getMensagemsServidor().stream()
                        .sorted(Comparator.comparing(Mensagem::getDataEnvio))
                        .forEach(Mensagem::mostrarMensagem);
            }
        });
    }

    private void ordenarMensagensPorAutor() {
        System.out.println("\nDigite o nome do autor:");
        var nomeAutor = Normalizer.normalize(scanner.next(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        var autor = usuarios.stream()
                .filter(u -> Normalizer.normalize(u.getNomeUsuario(), Normalizer.Form.NFD)
                        .replaceAll("\\p{M}", "")
                        .equalsIgnoreCase(nomeAutor))
                .toList().getFirst();

        servidores.forEach(s -> {
            if (!s.getMensagemsServidor().isEmpty() &&
                    !s.getMensagemsServidor().stream()
                    .filter(m -> m.getAutorMensagem() == autor)
                    .toList().isEmpty()) {

                System.out.println("\nServidor: " + s.getNomeServidor());

                var mensagemsDoAutor = s.getMensagemsServidor().stream()
                        .filter(m -> m.getAutorMensagem() == autor)
                        .toList();

                if (!mensagemsDoAutor.isEmpty()) {
                    mensagemsDoAutor.stream()
                            .sorted(Comparator.comparing(m -> m.getAutorMensagem() == autor))
                            .forEach(Mensagem::mostrarMensagem);
                }
            }
        });

    }

    private void exibirMenuEstatisticasServidores() {

    }


}
