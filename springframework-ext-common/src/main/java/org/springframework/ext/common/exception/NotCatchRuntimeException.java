package org.springframework.ext.common.exception;

import org.springframework.ext.common.object.Status;

/**
 * @author only
 * @since 2020/7/31
 */
public class NotCatchRuntimeException extends NestedRuntimeException {

    public NotCatchRuntimeException(Throwable t) {
        super(t);
    }

    public NotCatchRuntimeException(Status status) {
        super(status);
    }

    public NotCatchRuntimeException(Status status, Throwable cause) {
        super(status, cause);
    }

    public NotCatchRuntimeException(int status, String errorCode, String message) {
        super(status, errorCode, message);
    }

    public NotCatchRuntimeException(int status, String errorCode, String message, Throwable t) {
        super(status, errorCode, message, t);
    }
}
