package org.springframework.ext.common.exception;

import org.springframework.ext.common.object.Status;

/**
 * Created by only on 2017/7/10.
 */
public class NestedRuntimeException extends RuntimeException {
    static final String SPLIT = "/";

    public NestedRuntimeException(Throwable cause) {
        super(cause);
    }

    public NestedRuntimeException(String message) {
        super(message);
    }

    public NestedRuntimeException(int status, String errorCode, String errorMessage) {
        this(status + SPLIT + errorCode + SPLIT + errorMessage);
    }

    public NestedRuntimeException(Status status) {
        this(status.getStatus(), status.getCode(), status.getMsg());
    }

    public NestedRuntimeException(Status status, Object... format) {
        this(status.getStatus(), status.getCode(), String.format(status.getMsg(), format));
    }

    public int getStatus() {
        String status = getMessageSplit(0);

        return status != null && !status.isEmpty() ? Integer.valueOf(status) : 0;
    }

    public String getCode() {
        return getMessageSplit(1);
    }

    public String getMsg() {
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
