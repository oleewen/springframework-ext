package org.springframework.ext.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ext.common.consts.StringConst;
import org.springframework.ext.common.object.Status;

/**
 * @author only
 * @date 2017/7/10.
 */
public class NestedRuntimeException extends RuntimeException {
    static final String SPLIT = StringConst.IMARK;

    private NestedRuntimeException() {
    }

    public NestedRuntimeException(String message) {
        super(message);
    }

    public NestedRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public NestedRuntimeException(Status status) {
        this(status.getStatus(), status.getErrorCode(), status.getMessage());
    }

    public NestedRuntimeException(Status status, Throwable throwable) {
        this(status.getStatus(), status.getErrorCode(), status.getMessage(), throwable);
    }

    public NestedRuntimeException(int status, String errorCode, String errorMessage) {
        super(status + SPLIT + errorCode + SPLIT + errorMessage);
    }

    public NestedRuntimeException(int status, String errorCode, String errorMessage, Throwable throwable) {
        super(status + SPLIT + errorCode + SPLIT + errorMessage, throwable);
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
            public String getErrorCode() {
                return finalErrorCode;
            }

            @Override
            public String getMessage() {
                return finalMessage;
            }

            @Override
            public String getMessage(Object... format) {
                return finalMessage;
            }
        };
    }

    public int getStatus() {
        String status = getMessageSplit(0);

        return status != null && !status.isEmpty() ? Integer.valueOf(status) : 0;
    }

    public String getErrorCode() {
        return getMessageSplit(1);
    }

    public String getErrorMessage() {
        return getMessageSplit(2);
    }

    private String getMessageSplit(int index) {
        String message = getMessage();
        if (message != null && message.contains(SPLIT)) {
            String[] split = message.split(SPLIT);

            if (split.length >= index) {
                return split[index];
            }
        }

        return "";
    }
}
