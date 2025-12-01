package lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions;

public class EnoughBookNotFoundException extends RuntimeException{
    public EnoughBookNotFoundException() {
        super();
    }

    public EnoughBookNotFoundException(String message) {
        super(message);
    }

    public EnoughBookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
