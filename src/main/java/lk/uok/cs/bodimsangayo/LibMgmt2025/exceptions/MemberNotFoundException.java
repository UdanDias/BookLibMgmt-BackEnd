package lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException() {
        super();
    }

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
