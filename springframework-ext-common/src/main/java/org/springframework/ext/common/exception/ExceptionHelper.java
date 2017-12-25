package org.springframework.ext.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ext.common.object.Status;

/**
 * Created by only on 2017/3/13.
 */
public class ExceptionHelper {
    protected static final String SPLIT = NestedRuntimeException.SPLIT;

    public static NestedRuntimeException throwNotCatchException(Status status) {
        return throwException(status.getStatus(), status.getCode(), status.getMsg());
    }

    public static NestedRuntimeException throwNotCatchException(String errorCode, String message) {
        return throwException(-9999, errorCode, message);
    }

    public static NestedRuntimeException throwNotCatchException(int status, String errorCode, String message) {
        throw new NotCatchRuntimeException(status, errorCode, message);
    }

    public static NestedRuntimeException throwNotCatchException(String errorMessage) {
        return throwNotCatchException(parseErrorMessage(errorMessage));
    }

    public static NestedRuntimeException throwException(Status status) {
        return throwException(status.getStatus(), status.getCode(), status.getMsg());
    }

    public static NestedRuntimeException throwException(String errorCode, String message) {
        return throwException(-9999, errorCode, message);
    }

    public static NestedRuntimeException throwException(int status, String errorCode, String message) {
        throw new NestedRuntimeException(status, errorCode, message);
    }

    public static NestedRuntimeException throwException(String errorMessage) {
        return throwException(parseErrorMessage(errorMessage));
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
        public NotCatchRuntimeException(int status, String errorCode, String message) {
            super(status, errorCode, message);
        }
    }
}
