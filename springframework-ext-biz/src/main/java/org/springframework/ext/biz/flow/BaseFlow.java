/**
 * taobao.com Inc. Copyright (c) 1998-2101 All Rights Reserved.
 */
package org.springframework.ext.biz.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ext.common.helper.BeanHelper;

import java.lang.reflect.Type;

/**
 * 基本流程定义：流程模版方法界定
 *
 * @param <BEAN>   参数对象
 * @param <RESULT> 响应结果
 * @author only 2012-12-23
 */
public class BaseFlow<BEAN, RESULT> {
    /** 日志对象 */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 初始化
     */
    public void init() {
    }

    /**
     * 新建结果对象，该方法由子类实现
     *
     * @return 结果对象
     */
    protected RESULT newResult() {
        // 取实现类的泛型数组
        Type[] actualTypes = BeanHelper.getGenericTypes(getClass());
        // 创建对象
        return BeanHelper.instance(actualTypes[1]);
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}