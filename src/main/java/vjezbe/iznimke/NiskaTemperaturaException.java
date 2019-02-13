package vjezbe.iznimke;

public class NiskaTemperaturaException extends RuntimeException{
    public NiskaTemperaturaException() {
        super("Preniska temperatura!");
    }

    public NiskaTemperaturaException(String message) {
        super(message);
    }

    public NiskaTemperaturaException(String message, Throwable cause) {
        super(message, cause);
    }

    public NiskaTemperaturaException(Throwable cause) {
        super(cause);
    }
}
