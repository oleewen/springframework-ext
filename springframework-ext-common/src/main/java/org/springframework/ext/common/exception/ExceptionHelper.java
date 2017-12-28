package org.springframework.ext.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ext.common.object.Status;

/**
 * Created by only on 2017/3/13.
 */
public class ExceptionHelper {
    protected static final String SPLIT = NestedRuntimeException.SPLIT;

    public static NotCatchRuntimeException createNotCatchException(String errorMessage) {
        Status status = parseErrorMessage(errorMessage);
        return createNotCatchException(status);
    }

    public static NotCatchRuntimeException createNotCatchException(Throwable t) {
        return createNotCatchException(t.getMessage(), t);
    }

    public static NotCatchRuntimeException createNotCatchException(String errorMessage, Throwable t) {
        Status status = parseErrorMessage(errorMessage);
        return new NotCatchRuntimeException(status, t);
    }

    public static NotCatchRuntimeException createNotCatchException(Status status) {
        return new NotCatchRuntimeException(status);
    }

    public static NotCatchRuntimeException createNotCatchException(String errorCode, String message) {
        return createNotCatchException(-9999, errorCode, message);
    }

    public static NotCatchRuntimeException createNotCatchException(int status, String errorCode, String message) {
        Status statusEnum = parseErrorMessage(status + SPLIT + errorCode + SPLIT + message);

        return createNotCatchException(statusEnum);
    }

    public static NestedRuntimeException throwException(String errorMessage) {
        Status status = parseErrorMessage(errorMessage);

        return throwException(status);
    }

    public static NestedRuntimeException throwException(Throwable t) {
        return throwException(t.getMessage(), t);
    }

    public static NestedRuntimeException throwException(String errorMessage, Throwable t) {
        Status status = parseErrorMessage(errorMessage);

        return new NestedRuntimeException(status, t);
    }

    public static NestedRuntimeException throwException(Status status) {
        return new NestedRuntimeException(status);
    }

    public static NestedRuntimeException throwException(String errorCode, String message) {
        return throwException(-9999, errorCode, message);
    }

    public static NestedRuntimeException throwException(int status, String errorCode, String message) {
        Status statusEnum = parseErrorMessage(status + SPLIT + errorCode + SPLIT + message);

        return throwException(statusEnum);
    }

    public static Status parseErrorMessage(final String errorMessage) {
        int status = -9999;
        String errorCode = errorMessage;
        String message = errorMessage;

        if (StringUtils.isNotBlank(errorMessage)) {
            final String[] strings = StringUtils.split(errorMessage, SPLIT);

            if (strings != null && strings.length == 3) {
                status = Integer.valueOf(strings[0]);
                errorCode = strings[1];
                message = strings[2];
            }
        }

        final int finalStatus = status;
        final String finalErrorCode = errorCode;
        final String finalMessage = message;

        return new Status() {
            @Override
            public boolean isSuccess() {
                return false;
            }

            @Override
            public int getStatus() {
                return finalStatus;
            }

            @Override
            public String getCode() {
                return finalErrorCode;
            }

            @Override
            public String getMsg() {
                return finalMessage;
            }

            @Override
            public String getMsg(Object... format) {
                return finalMessage;
            }
        };
    }

    public static class NotCatchRuntimeException extends NestedRuntimeException {

        public NotCatchRuntimeException(Status status) {
            super(status);
        }

        public NotCatchRuntimeException(Status status, Throwable cause) {
            super(status, cause);
        }
    }
}
