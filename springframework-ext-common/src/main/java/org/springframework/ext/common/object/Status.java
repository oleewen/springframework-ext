package org.springframework.ext.common.object;

/**
 * 状态接口
 * User: only
 * Date: 14-6-21
 * Time: 下午10:15
 */
public interface Status {
    /**
     * 是否成功状态
     *
     * @return 成功状态，返回true；否则，返回false
     */
    public boolean isSuccess();

    /**
     * 状态码值
     *
     * @return 状态码值
     */
    public int getStatus();

    /**
     * 错误码
     *
     * @return 错误码
     */
    public String getErrorCode();

    /**
     * 状态描述
     *
     * @return 状态描述
     */
    public String getMessage();

    /**
     * 状态描述
     *
     * @return 状态描述
     */
    public String getMessage(Object... format);
}
