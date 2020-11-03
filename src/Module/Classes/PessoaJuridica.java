package Module.Classes;

public class PessoaJuridica extends Perfil {
    private String cnpj;

    public PessoaJuridica(String usuario) {
        super(usuario);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
