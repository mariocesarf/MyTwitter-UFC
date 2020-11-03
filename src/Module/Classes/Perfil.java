package Module.Classes;

import java.util.Vector;

public abstract class Perfil {
    private String usuario;
    private Vector<Perfil> seguidos;
    private Vector<Perfil> seguidores;
    private Vector<Tweet> timeline;
    private boolean ativo;

    public Perfil(String usuario) {
        this.usuario = usuario;
        seguidos = new Vector<Perfil>();
        seguidores = new Vector<Perfil>();
        timeline = new Vector<Tweet>();
        this.ativo = true;
    }

    public void addSeguidor(Perfil user) {
        this.seguidores.add(user);
    }

    public void addSeguido(Perfil user) {
        this.seguidos.add(user);
    }

    public Vector<Perfil> getSeguidores() {
        return seguidores;
    }

    public Vector<Perfil> getSeguidos() {
        return seguidos;
    }

    public Vector<Tweet> getTimeline() {
        return timeline;
    }

    public void addTweet(Tweet tweet) {
        this.timeline.add(tweet);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean atividade) {
        this.ativo = atividade;
    }


}
