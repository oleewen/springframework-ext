package org.springframework.ext.common.exception;

import org.springframework.ext.common.object.Status;

/**
 * @author only
 * @date 2017/7/10.
 */
public class NestedRuntimeException extends RuntimeException {
    static final String SPLIT = "/";

    private NestedRuntimeException() {
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
