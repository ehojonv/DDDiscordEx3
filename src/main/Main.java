package main;

import models.Servidor;
import models.Usuario;
import services.Menu;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Servidor> servidores = new ArrayList<>();

    public static void main(String[] args) {

        // Criação e adição ao sistema dos usuários
        var user1 = new Usuario("João");
        addUsuario(user1);
        var user2 = new Usuario("Maria");
        addUsuario(user2);
        var user3 = new Usuario("Carlos");
        addUsuario(user3);
        var user4 = new Usuario("Luana");
        addUsuario(user4);

        // Criação e adição ao sistema dos servidores
        var server1 = new Servidor("Servidor Legal");
        addServer(server1);
        var server2 = new Servidor("Servidor Da Familia");
        addServer(server2);
        var server3 = new Servidor("Games Server");
        addServer(server3);
        var server4 = new Servidor("Servidor de Arte");
        addServer(server4);

        // Usuário 1 entra em servidores
        user1.entrarServidor(server1);
        user1.entrarServidor(server2);
        user1.entrarServidor(server3);

        // Usuário 2 entra em servidores
        user2.entrarServidor(server1);
        user2.entrarServidor(server2);

        // Usuário 3 entra em servidores
        user3.entrarServidor(server1);
        user3.entrarServidor(server2);
        user3.entrarServidor(server3);
        user3.entrarServidor(server4);

        // Usuário 4 entra em servidores
        user4.entrarServidor(server3);
        user4.entrarServidor(server4);

        // Iniciar menu
        new Menu().iniciarMenu();
    }

    public static void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static void addServer(Servidor servidor) {
        servidores.add(servidor);
    }

    public static void addUsuario(String nomeUsuario) {
        usuarios.add(new Usuario(nomeUsuario));
    }

    public static void addServer(String nomeServidor) {
        servidores.add(new Servidor(nomeServidor));
    }

    public static List<Usuario> getListaUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public static List<Servidor> getListaServidores() {
        return new ArrayList<>(servidores);
    }
}
