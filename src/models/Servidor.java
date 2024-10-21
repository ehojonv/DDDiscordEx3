package models;

import main.Main;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Servidor {

    private int idServidor;
    private String nomeServidor;
    private final List<Usuario> usuariosServidor = new ArrayList<>();
    private final List<Mensagem> mensagemsServidor = new ArrayList<>();

    public void addUsuario(Usuario usuario) {
        usuariosServidor.add(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        usuariosServidor.remove(usuario);
    }

    public void addMensagem(Mensagem mensagem) {
        mensagemsServidor.add(mensagem);
    }

    public void mostrarMensagens() {
        System.out.printf("\nServidor \033[1;4m\"%s\"\033[22;24m:\n", nomeServidor);
        if (mensagemsServidor.isEmpty()) {
            System.out.println("\033[100;3;30mSeja o primeiro a mandar mensagem!\033[49;23;39m");
        } else {
            mensagemsServidor.forEach(Mensagem::mostrarMensagem);
        }
    }

    //Métodos gerais

    public Servidor() {
    }

    public Servidor(String nomeServidor) {
        this.idServidor = (Main.getListaServidores().size() + 1);
        this.nomeServidor = nomeServidor;
    }

    public int getIdServidor() {
        return idServidor;
    }

    public String getNomeServidor() {
        return nomeServidor;
    }

    public void setNomeServidor(String nomeServidor) {
        this.nomeServidor = nomeServidor;
    }

    public List<Usuario> getUsuariosServidor() {
        return usuariosServidor;
    }

    public List<Mensagem> getMensagemsServidor() {
        return mensagemsServidor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servidor servidor = (Servidor) o;
        return getIdServidor() == servidor.getIdServidor() && Objects.equals(getNomeServidor(), servidor.getNomeServidor()) && Objects.equals(getUsuariosServidor(), servidor.getUsuariosServidor()) && Objects.equals(getMensagemsServidor(), servidor.getMensagemsServidor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdServidor(), getNomeServidor(), getUsuariosServidor(), getMensagemsServidor());
    }

    @Override
    public String toString() {
        return "Servidor{" +
                "idServidor=" + idServidor +
                ", nomeServidor='" + nomeServidor + '\'' +
                ", usuariosServidor=" + usuariosServidor +
                ", mensagemsServidor=" + mensagemsServidor +
                '}';
    }
}
