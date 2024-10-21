package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Mensagem {

    private LocalDateTime dataEnvio;
    private Usuario autorMensagem;
    private String conteudo;

    public void mostrarMensagem() {
        System.out.printf("%s - %s: %s\n",dataEnvio.format(DateTimeFormatter.ofPattern("HH:mm")),autorMensagem.getNomeUsuario(),conteudo);
    }

    //Metodos gerais

    public Mensagem() {
    }

    public Mensagem(Usuario autorMensagem, String conteudo) {
        this.dataEnvio = LocalDateTime.now();
        this.autorMensagem = autorMensagem;
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public Usuario getAutorMensagem() {
        return autorMensagem;
    }

    public String getConteudo() {
        return conteudo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensagem mensagem = (Mensagem) o;
        return Objects.equals(getDataEnvio(), mensagem.getDataEnvio()) && Objects.equals(getAutorMensagem(), mensagem.getAutorMensagem()) && Objects.equals(getConteudo(), mensagem.getConteudo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDataEnvio(), getAutorMensagem(), getConteudo());
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "dataEnvio=" + dataEnvio +
                ", autorMensagem=" + autorMensagem +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }
}
