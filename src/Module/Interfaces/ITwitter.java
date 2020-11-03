package Module.Interfaces;

import Module.Classes.Perfil;
import Module.Classes.Tweet;
import Module.Exceptions.*;

import java.util.Vector;

public interface ITwitter {
    void criarPerfil(Perfil usuario) throws PEException;

    void cancelarPerfil(String usuario) throws PIException, PDException;

    void tweetar(String usuario, String mensagem) throws PIException, MFPException;

    Vector<Tweet> timeline(String usuario) throws PIException, PDException;

    Vector<Tweet> tweets(String usuario) throws PIException, PDException;

    void seguir(String seguidor, String seguido) throws PIException, PDException, SIException;

    int numeroSeguidores(String usuario) throws PIException, PDException;

    Vector<Perfil> seguidores(String usuario) throws PIException, PDException;

    Vector<Perfil> seguidos(String usuario) throws PIException, PDException;

}
