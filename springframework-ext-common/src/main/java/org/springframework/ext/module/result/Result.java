package org.springframework.ext.module.result;

import lombok.Data;
import org.springframework.ext.common.exception.NestedRuntimeException;
import org.springframework.ext.common.helper.JsonHelper;
import org.springframework.ext.common.object.Status;

/**
 * 结果对象
 *
 * @param <T> 模板
 *
 * @author only 2013-7-2
 */
@Data
public class Result<T> implements ResultSupport<T> {

    /** Define long serialVersionUID */
    private static final long serialVersionUID = 3928402875296079225L;
    /** 状态 */
    private boolean success = true;
    /** 状态码 例如:0-正常；-1-缺少参数；-3-接口异常 */
    private int status;
    /** 错误码 */
    private String errorCode;
    /** 状态消息 */
    private String message;
    /** 淘宝/tmall商品或店铺的返利信息;外网B2C商品或活动的返利信息 */
    private T module;

    /** 构造函数 */
    public Result() {
        super();
    }

    public Result(Status status) {
        this(status.isSuccess(), status.getStatus(), status.getErrorCode(), status.getMessage());
    }

    public Result(boolean success, int status, String errorCode, String message) {
        Result result = new Result();

        result.setSuccess(success);
        result.setStatus(status);
        result.setErrorCode(errorCode);
        result.setMessage(message);
    }

    public Result(boolean success, int status, String errorCode, String message, String[] messages) {
        Result result = new Result();

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
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static Result create(Status status) {
        return create(status.isSuccess(), status.getStatus(), status.getErrorCode(), status.getMessage());
    }

    /**
     * 根据异常设置状态码
     *
     * @param e 异常
     */
    public static Result create(NestedRuntimeException e) {
        return create(false, e.getStatus(), e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * 设置状态码
     *
     * @param status 状态码
     */
    public static Result create(boolean success, int status, String errorCode, String message) {
        return new Result(success, status, errorCode, message);
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static Result create(Status status, String... messages) {
        return create(status.isSuccess(), status.getStatus(), status.getErrorCode(), status.getMessage(), messages);
    }

    /**
     * 设置状态码
     *
     * @param status   状态码
     * @param messages 替换文本
     */
    public static Result create(boolean success, int status, String errorCode, String message, String... messages) {
        return new Result(success, status, errorCode, message, messages);
    }

    @Override
    public String toString() {
        return JsonHelper.toJson(this);
    }
}