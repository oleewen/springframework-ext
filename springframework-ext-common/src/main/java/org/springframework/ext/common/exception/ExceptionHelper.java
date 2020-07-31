package org.springframework.ext.common.exception;

import org.springframework.ext.common.object.Status;

/**
 * Created by only on 2017/3/13.
 */
public class ExceptionHelper {

    public static NotCatchRuntimeException createNotCatchException(String errorMessage) {
        return createNotCatchException(NestedRuntimeException.parseErrorMessage(errorMessage));
    }

    public static NotCatchRuntimeException createNotCatchException(Throwable t) {
        return new NotCatchRuntimeException(t);
    }

    public static NotCatchRuntimeException createNotCatchException(Status status) {
        return new NotCatchRuntimeException(status);
    }

    public static NotCatchRuntimeException createNotCatchException(Status status, Throwable t) {
        return new NotCatchRuntimeException(status, t);
    }

    public static NotCatchRuntimeException createNotCatchException(String errorCode, String message) {
        return createNotCatchException(-9999, errorCode, message);
    }

    public static NotCatchRuntimeException createNotCatchException(int status, String errorCode, String message) {
        return new NotCatchRuntimeException(status, errorCode, message);
    }

    public static NestedRuntimeException createNestedException(String errorMessage) {
        return createNestedException(NestedRuntimeException.parseErrorMessage(errorMessage));
    }

    public static NestedRuntimeException createNestedException(Throwable t) {
        return new NestedRuntimeException(t);
    }

    public static NestedRuntimeException createNestedException(Status status) {
        return new NestedRuntimeException(status);
    }

    public static NestedRuntimeException createNestedException(Status status, Throwable t) {
        return new NestedRuntimeException(status, t);
    }

    public static NestedRuntimeException createNestedException(String errorCode, String message) {
        return createNestedException(-9999, errorCode, message);
    }

    public static NestedRuntimeException createNestedException(int status, String errorCode, String message) {
        return new NestedRuntimeException(status, errorCode, message);
    }
}
