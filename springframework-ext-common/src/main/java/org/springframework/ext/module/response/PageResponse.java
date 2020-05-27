package org.springframework.ext.module.response;

import lombok.Data;
import org.springframework.ext.common.exception.NestedRuntimeException;
import org.springframework.ext.common.object.Status;

@Data
public class PageResponse<T> extends Response<PageModule<T>> {
    /** 构造函数 */
    public PageResponse() {
        super();
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static PageResponse create(Status status) {
        return create(status.isSuccess(), status.getStatus(), status.getErrorCode(), status.getMessage());
    }

    /**
     * 根据异常设置状态码
     *
     * @param e 异常
     */
    public static PageResponse create(NestedRuntimeException e) {
        return create(false, e.getStatus(), e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static PageResponse create(boolean success, int status, String errorCode, String message) {
        PageResponse response = new PageResponse();
        response.setSuccess(success);
        response.setStatus(status);
        response.setErrorCode(errorCode);
        response.setMessage(message);

        return response;
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static PageResponse create(Status status, String... messages) {
        return create(status.isSuccess(), status.getStatus(), status.getErrorCode(), status.getMessage(), messages);
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static PageResponse create(boolean success, int status, String errorCode, String message, String... messages) {
        PageResponse result = new PageResponse();

        result.setSuccess(success);
        result.setStatus(status);
        result.setErrorCode(errorCode);
        if (message != null && message.contains("%s")) {
            if (messages != null) {
                message = String.format(message, (Object[]) messages);
            }
            // 无替换文本
            else {
                message = message.replaceAll("%s", "");
            }
        }
        result.setMessage(message);

        return result;
    }
}
