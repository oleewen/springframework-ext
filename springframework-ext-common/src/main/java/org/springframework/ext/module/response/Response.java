package org.springframework.ext.module.response;

import lombok.Data;
import org.springframework.ext.common.exception.NestedRuntimeException;
import org.springframework.ext.common.object.Status;
import org.springframework.ext.module.result.Result;

/**
 * 结果对象
 *
 * @param <T> 模板
 *
 * @author only 2017-7-2
 */
@Data
public class Response<T> extends Result<T> {
    /** 构造函数 */
    public Response() {
        super();
    }

    public Response(Status status) {
        super(status);
    }

    public Response(boolean success, int status, String errorCode, String message) {
        super(success, status, errorCode, message);
    }

    public Response(boolean success, int status, String errorCode, String message, String[] messages) {
        super(success, status, errorCode, message, messages);
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static Response create(Status status) {
        return new Response(status);
    }

    /**
     * 根据异常设置状态码
     *
     * @param e 异常
     */
    public static Response create(NestedRuntimeException e) {
        return create(false, e.getStatus(), e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static Response create(boolean success, int status, String errorCode, String message) {
        return new Response(success, status, errorCode, message);
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static Response create(Status status, String... messages) {
        return create(status.isSuccess(), status.getStatus(), status.getErrorCode(), status.getMessage(), messages);
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static Response create(boolean success, int status, String errorCode, String message, String... messages) {
        return new Response(success, status, errorCode, message, messages);
    }
}
