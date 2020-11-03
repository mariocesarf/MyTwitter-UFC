package Module.Exceptions;

public class UNCException extends Exception {
    public UNCException() {
        super("Não existe usuario com esse nome!!");
    }
}
