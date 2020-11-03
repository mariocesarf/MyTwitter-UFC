package Module.Classes;

import Module.Exceptions.*;
import Module.Interfaces.ITwitter;

import java.util.Vector;

public class MyTwitter implements ITwitter {
    private final RepositorioUsuario repo;

    public MyTwitter(RepositorioUsuario publicRepository) {
        repo = publicRepository;
    }

    @Override
    public void criarPerfil(Perfil usuario) throws PEException {
        try {
            usuario.setAtivo(true);
            repo.cadastrar(usuario);
        } catch (UJCException e) {
            throw new PEException();
        }

    }

    @Override
    public void cancelarPerfil(String usuario) throws PIException, PDException {
        Perfil tempPerf = repo.busca(usuario);
        if (tempPerf == null) {
            throw new PIException();
        } else if (tempPerf.isAtivo() == true) {
            tempPerf.setAtivo(false);
        } else if (tempPerf.isAtivo() == false) {
            throw new PDException();
        }
    }

    @Override
    public void tweetar(String usuario, String mensagem) throws PIException, MFPException {
        Perfil tempPerf = repo.busca(usuario);
        if (tempPerf == null) {
            throw new PIException();
        } else {
            if (mensagem.length() >= 1 && mensagem.length() <= 140) {
                Tweet tempTweet = new Tweet();
                tempTweet.setMensagem(mensagem);
                tempTweet.setUsuario(usuario);
                tempPerf.addTweet(tempTweet);
                for (Perfil perf : tempPerf.getSeguidores()) {
                    perf.addTweet(tempTweet);
                }
            } else {
                throw new MFPException();
            }
        }
    }

    @Override
    public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
        Perfil tempPerf = repo.busca(usuario);
        System.out.println(tempPerf.isAtivo());
        if (tempPerf == null) {
            throw new PIException();
        }
        if (tempPerf.isAtivo() == true) {
            return tempPerf.getTimeline();
        }
        if (tempPerf.isAtivo() == false) {
            throw new PDException();
        }
        return null;
    }

    @Override
    public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
        Perfil tempPerf = repo.busca(usuario);
        Vector<Tweet> tweetsVector = new Vector<>();
        if (tempPerf == null) {
            throw new PIException();
        }
        if (!tempPerf.isAtivo()) {
            throw new PDException();
        }
        for (Tweet tweet : tempPerf.getTimeline()) {
            if (tweet.getUsuario().equals(usuario)) {
                tweetsVector.add(tweet);
            }
        }

        return tweetsVector;

    }

    @Override
    public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {

        Perfil tempSeguidor = repo.busca(seguidor);
        Perfil tempSeguido = repo.busca(seguido);


        if (seguidor.equals(seguido) || tempSeguido.getSeguidores().contains(tempSeguidor)) throw new SIException();
        if (tempSeguido == null || tempSeguidor == null) throw new PIException();
        if (!(tempSeguido.isAtivo() && tempSeguidor.isAtivo())) throw new PDException();

        tempSeguido.addSeguidor(tempSeguidor);
        tempSeguidor.addSeguido(tempSeguido);

    }

    @Override
    public int numeroSeguidores(String usuario) throws PIException, PDException {
        Perfil tempPerf = repo.busca(usuario);
        int numSeg = 0;
        if (tempPerf == null) throw new PIException();
        if (!tempPerf.isAtivo()) throw new PDException();

        for (Perfil perf : tempPerf.getSeguidores()) {
            if (perf.isAtivo()) {
                numSeg++;
            }
        }
        return numSeg;
    }

    @Override
    public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
        Perfil tempPerf = repo.busca(usuario);
        if (tempPerf == null) throw new PIException();
        if (!tempPerf.isAtivo()) throw new PDException();
        return tempPerf.getSeguidores();
    }

    @Override
    public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
        Perfil tempPerf = repo.busca(usuario);

        if (tempPerf == null) throw new PIException();
        if (!tempPerf.isAtivo()) throw new PDException();
        return tempPerf.getSeguidos();
    }
}
