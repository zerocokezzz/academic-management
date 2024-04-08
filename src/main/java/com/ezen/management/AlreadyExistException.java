package com.ezen.management;

public class AlreadyExistException extends IllegalStateException {

    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String s) {
        super(s);
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistException(Throwable cause) {
        super(cause);
    }
}
