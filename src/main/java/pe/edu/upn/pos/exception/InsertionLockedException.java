package pe.edu.upn.pos.exception;

public class InsertionLockedException extends RuntimeException {

    public InsertionLockedException() {
        super("Only one record allowed");
    }
}
