package com.gongfu.exception;

/**
 * 2017年1月9日
 *
 * @向治家 yhqb ValidationException.java
 **/
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 7587045783909295723L;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException() {
        super();
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
