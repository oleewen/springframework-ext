package org.springframework.ext.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ext.common.object.Status;

/**
 * Created by only on 2017/3/13.
 */
public class ExceptionHelper {
    public static NestedRunTimeException throwNotCatchException(Status status) {
        return throwException(status.getStatus(), status.getCode(), status.getMsg());
    }

    public static NestedRunTimeException throwNotCatchException(String errorCode, String message) {
        return throwException(-9999, errorCode, message);
    }

    public static NestedRunTimeException throwNotCatchException(int status, String errorCode, String message) {
        throw new NotCatchRuntimeException(status, errorCode, message);
    }

    public static NestedRunTimeException throwNotCatchException(String errorMessage) {
        return throwNotCatchException(parseErrorMessage(errorMessage));
    }

    public static NestedRunTimeException throwException(Status status) {
        return throwException(status.getStatus(), status.getCode(), status.getMsg());
    }

    public static NestedRunTimeException throwException(String errorCode, String message) {
        return throwException(-9999, errorCode, message);
    }

    public static NestedRunTimeException throwException(int status, String errorCode, String message) {
        throw new NestedRunTimeException(status, errorCode, message);
    }

    public static NestedRunTimeException throwException(String errorMessage) {
        return throwException(parseErrorMessage(errorMessage));
    }

    public static Status parseErrorMessage(final String errorMessage) {
        int status = -9999;
        String errorCode = errorMessage;
        String message = errorMessage;

        if (StringUtils.isNotBlank(errorMessage)) {
            final String[] strings = StringUtils.split(errorMessage, "/");

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

    public static class NotCatchRuntimeException extends NestedRunTimeException {
        public NotCatchRuntimeException(int status, String errorCode, String message) {
            super(status, errorCode, message);
        }
    }
}
