package models;

import main.Main;

import java.util.Objects;

public class Usuario {
    private int idUsuario;
    private String nomeUsuario;

    public void entrarServidor(Servidor servidor) {
        servidor.addUsuario(this);
    }

    public void sairServidor(Servidor servidor) {
        servidor.removeUsuario(this);
    }

    public void enviarMensagem(String mensagem, Servidor servidor) {
        servidor.addMensagem(new Mensagem(this,mensagem));
    }

    // Métodos gerais

    public Usuario() {
    }

    public Usuario(String nomeUsuario) {
        this.idUsuario = (Main.getListaUsuarios().size() + 1);
        this.nomeUsuario = nomeUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return getIdUsuario() == usuario.getIdUsuario() && Objects.equals(getNomeUsuario(), usuario.getNomeUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUsuario(), getNomeUsuario());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                '}';
    }
}
