package org.springframework.ext.module.response;

import lombok.Data;
import org.springframework.ext.common.exception.NestedRuntimeException;
import org.springframework.ext.common.object.Status;
import org.springframework.ext.module.result.Result;

/**
 * 结果对象
 *
 * @param <T> 模板
 * @author only 2017-7-2
 */
@Data
public class Response<T> extends Result<T> {
    /** 构造函数 */
    public Response() {
        super();
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static Response create(Status status) {
        return create(status.isSuccess(), status.getStatus(), status.getCode(), status.getMsg());
    }

    /**
     * 根据异常设置状态码
     *
     * @param e 异常
     */
    public static Response create(NestedRuntimeException e) {
        return create(false, e.getStatus(), e.getCode(), e.getMsg());
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static Response create(boolean success, int status, String errorCode, String message) {
        Response response = new Response();
        response.setSuccess(success);
        response.setStatus(status);
        response.setCode(errorCode);
        response.setMsg(message);

        return response;
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static Response create(Status status, String... messages) {
        return create(status.isSuccess(), status.getStatus(), status.getCode(), status.getMsg(), messages);
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static Response create(boolean success, int status, String errorCode, String message, String... messages) {
        Response result = new Response();

        result.setSuccess(success);
        result.setStatus(status);
        result.setCode(errorCode);
        if (message != null && message.contains("%s")) {
            if (messages != null) {
                message = String.format(message, (Object[]) messages);
            }
            // 无替换文本
            else {
                message = message.replaceAll("%s", "");
            }
        }
        result.setMsg(message);

        return result;
    }
}
