package Module.Classes;

public class Tweet {
    private String usuario;
    private String mensagem;

    public Tweet() {
        usuario = "";
        mensagem = "";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
