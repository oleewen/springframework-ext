package org.springframework.ext.module.result;

import lombok.Data;
import org.springframework.ext.common.exception.NestedRuntimeException;
import org.springframework.ext.common.object.Status;
import org.springframework.ext.module.response.PageModule;

@Data
public class PageResult<T> extends Result<PageModule<T>> {
    /** 构造函数 */
    public PageResult() {
        super();
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static PageResult create(Status status) {
        return create(status.isSuccess(), status.getStatus(), status.getErrorCode(), status.getMessage());
    }

    /**
     * 根据异常设置状态码
     *
     * @param e 异常
     */
    public static PageResult create(NestedRuntimeException e) {
        return create(false, e.getStatus(), e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static PageResult create(boolean success, int status, String errorCode, String message) {
        PageResult response = new PageResult();
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
    public static PageResult create(Status status, String... messages) {
        return create(status.isSuccess(), status.getStatus(), status.getErrorCode(), status.getMessage(), messages);
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static PageResult create(boolean success, int status, String errorCode, String message, String... messages) {
        PageResult result = new PageResult();

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
