package Module.Classes;

public class PessoaFisica extends Perfil {
    private String cpf;

    public PessoaFisica(String usuario) {
        super(usuario);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
