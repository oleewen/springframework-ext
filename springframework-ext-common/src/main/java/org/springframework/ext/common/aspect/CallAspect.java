package org.springframework.ext.common.aspect;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.ext.common.helper.JsonHelper;
import org.springframework.ext.common.setting.Context;
import org.springframework.ext.module.result.ResultSupport;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * 日志切面
 * <pre>
 * @Aspect
 * public class SpringAspect {
 *      @Pointcut("execution(* com.company.department.business.appname.*.*(..))" && @annotation(org.springframework.ext.common.aspect.Call))
 *      public void loggerPoint() {
 *      }
 * }
 *
 * @Configuration
 * @EnableAspectJAutoProxy
 * @ComponentScan
 * public class SpringConfig {
 *      @Bean
 *      public SpringAspect springAspect() {
 *          return new SpringAspect();
 *      }
 *
 *      @Bean
 *      public CallAspect callAspect() {
 *          return new CallAspect();
 *      }
 * }
 * </pre>
 *
 * @author only
 * @date 2015-07-14
 */
@Aspect
public class CallAspect {
    /** 随机采样频率 */
    private static Random random = new Random();

    /**
     * profiler方法拦截
     *
     * @param joinPoint 连接点
     */
    @Around("loggerPoint()")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        // 方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Call call = method.getAnnotation(Call.class);

        long start = System.currentTimeMillis();

        Object result = null;
        try {
            // 执行方法
            result = joinPoint.proceed();

            long end = System.currentTimeMillis();
            // 有@Logger注解
            if (call != null) {
                // 打印日志
                logger(call, joinPoint.getArgs(), end - start, result);
            }
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            // 打印日志
            error(call, joinPoint.getArgs(), end - start, e);
        }
        return result;
    }

    private void logger(Call call, Object[] args, long elapsed, Object result) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(call.value());

        // 调用成功
        if (isSuccess(result)) {
            // 超时，打印日志
            if (elapsed > call.elapsed()) {
                logger.warn(String.format("timeout@args:%s,elapsed:%d;duration:%d,result:%s", JsonHelper.toJson(args), call.elapsed(), elapsed, JsonHelper.toJson(result)));
            }
            // 符合采样频率条件
            else if (random.nextInt(call.basic()) <= call.sample()) {
                logger.warn(String.format("sample@args:%s,elapsed:%d;duration:%d,result:%s", JsonHelper.toJson(args), call.elapsed(), elapsed, JsonHelper.toJson(result)));
            }
            // debug日志
            else if (Context.debug()) {
                logger.warn(String.format("debug@args:%s,elapsed:%d;duration:%d,result:%s", JsonHelper.toJson(args), call.elapsed(), elapsed, JsonHelper.toJson(result)));
            }
            // 其他（未超时、未采用命中），不打印日志
            return;
        }

        // 调用失败，访问日志
        logger.warn(String.format("access@args:%s;duration:%d,success:false,result:%s", JsonHelper.toJson(args), elapsed, JsonHelper.toJson(result)));
    }

    protected boolean isSuccess(Object result) {
        if (result != null) {
            if (result instanceof ResultSupport) {
                return ((ResultSupport) result).isSuccess();
            }
            return true;
        }
        return false;
    }

    private void error(Call callAnnotation, Object[] args, long elapsed, Throwable e) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(StringUtils.defaultIfBlank(callAnnotation.value(), CallAspect.class.getName()));

        logger.error(String.format("exception@args:%s,elapsed:%d;duration:%d", JsonHelper.toJson(args), ObjectUtils.defaultIfNull(callAnnotation.elapsed(), 0), elapsed), e);
    }
}
