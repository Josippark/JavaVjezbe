package vjezbe.iznimke;

public class VisokaTemperaturaException extends Exception {
    public VisokaTemperaturaException() {
        super("Previsoka temperatura!");
    }

    public VisokaTemperaturaException(String message) {
        super(message);
    }

    public VisokaTemperaturaException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisokaTemperaturaException(Throwable cause) {
        super(cause);
    }
}
