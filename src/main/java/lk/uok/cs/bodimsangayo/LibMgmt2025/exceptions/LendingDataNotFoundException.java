package lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions;

public class LendingDataNotFoundException extends RuntimeException{
    public LendingDataNotFoundException() {
        super();
    }

    public LendingDataNotFoundException(String message) {
        super(message);
    }

    public LendingDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
