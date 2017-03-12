package com.zbmatsu.iam.exception;

/**
 * Created by Administrator on 2017/3/3.
 */
public class ResourceMismatchingException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ResourceMismatchingException() {
        super();
    }

    public ResourceMismatchingException(String message) {
        super(message);
    }

    public ResourceMismatchingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceMismatchingException(Throwable cause) {
        super(cause);
    }

    protected ResourceMismatchingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
