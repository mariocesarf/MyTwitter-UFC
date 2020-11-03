package Module.Classes;

import Module.Exceptions.UJCException;
import Module.Exceptions.UNCException;
import Module.Interfaces.IRepositorioUsuario;

import java.util.ArrayList;

public class RepositorioUsuario implements IRepositorioUsuario {
    public ArrayList<Perfil> usuarios;

    public RepositorioUsuario() {
        usuarios = new ArrayList<Perfil>();
    }

    @Override
    public void cadastrar(Perfil usuario) throws UJCException {
        if (busca(usuario.getUsuario()) == null) {
            usuario.setAtivo(true);
            usuarios.add(usuario);
        } else {
            throw new UJCException(usuario.getUsuario());
        }
    }

    @Override
    public Perfil busca(String usuario) {
        Perfil tempPerf = null;
        for (Perfil perf : usuarios) {
            if (usuario.equals(perf.getUsuario())) {
                tempPerf = perf;
            }
        }
        return tempPerf;
    }

    @Override
    public void atualizar(Perfil newUser) throws UNCException {
        Perfil oldUser = busca(newUser.getUsuario());
        if (oldUser == null) {
            throw new UNCException();
        } else {
            oldUser.setUsuario(newUser.getUsuario());
            oldUser.setAtivo(newUser.isAtivo());
        }
    }
}
