package Module.Exceptions;

public class UJCException extends Exception {
    String user;

    public UJCException(String usuario) {

        super("Ja existe um usuario cadastrado com esse nome");
        user = usuario;
    }

    public void printErro() {
        System.out.println("O nome do usuario ja cadastrado é: " + user);
    }
}
