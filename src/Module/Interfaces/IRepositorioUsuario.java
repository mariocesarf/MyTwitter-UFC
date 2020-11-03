package Module.Interfaces;

import Module.Classes.Perfil;
import Module.Exceptions.UJCException;
import Module.Exceptions.UNCException;

public interface IRepositorioUsuario {
    void cadastrar(Perfil usuario) throws UJCException;

    Perfil busca(String usuario);

    void atualizar(Perfil usuario) throws UNCException;
}