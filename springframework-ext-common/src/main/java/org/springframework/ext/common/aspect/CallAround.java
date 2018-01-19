package org.springframework.ext.common.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ext.common.exception.ExceptionHelper;
import org.springframework.ext.common.helper.JsonHelper;
import org.springframework.ext.common.setting.Context;
import org.springframework.ext.module.result.ResultSupport;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * 日志切面
 * <pre>
 @Aspect
 public class CallAspect extends CallAround {
      @Pointcut("execution(* com.company.department.business.appname.*.*(..)) && @annotation(org.springframework.ext.common.aspect.Call)")
      public void callPoint() {
      }
 }

 @Configuration
 @EnableAspectJAutoProxy
 @ComponentScan
 public class SpringConfig {
      @Bean
      public CallAspect callAspect() {
          return new CallAspect();
      }

 }
 * </pre>
 *
 * @author only
 * @date 2015-07-14
 */
public class CallAround {
    /** 随机采样频率 */
    private static Random random = new Random();

    /**
     * profiler方法拦截
     *
     * @param joinPoint 连接点
     */
    @Around("callPoint()")
    public Object call(ProceedingJoinPoint joinPoint) throws Throwable {
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
                logger(joinPoint, call, end - start, result);
            }
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            // 打印日志
            error(joinPoint, call, end - start, e);
        }
        return result;
    }

    private void logger(ProceedingJoinPoint joinPoint, Call call, long elapsed, Object result) {
        Logger logger = getLogger(joinPoint, call);

        Object[] args = joinPoint.getArgs();

        // 调用成功
        if (isSuccess(result)) {
            // 超时，打印日志
            if (elapsed > call.elapsed()) {
                logger.error(String.format("timeout@method:%s,args:%s,elapsed:%d;duration:%d,result:%s", joinPoint.getSignature().toShortString(), JsonHelper.toJson(args), call.elapsed(), elapsed, JsonHelper.toJson(result)));
            }
            // 符合采样频率条件
            else if (random.nextInt(call.basic()) <= call.sample()) {
                logger.warn(String.format("sample@method:%s,args:%s,elapsed:%d;duration:%d,result:%s", joinPoint.getSignature().toShortString(), JsonHelper.toJson(args), call.elapsed(), elapsed, JsonHelper.toJson(result)));
            }
            // debug日志
            else if (Context.debug()) {
                logger.warn(String.format("debug@method:%s,args:%s,elapsed:%d;duration:%d,result:%s", joinPoint.getSignature().toShortString(), JsonHelper.toJson(args), call.elapsed(), elapsed, JsonHelper.toJson(result)));
            }
            // 其他（未超时、未采用命中），不打印日志
            else {
                otherCase(joinPoint, call, elapsed, result);
            }
            return;
        }

        // 调用失败，访问日志
        logger.warn(String.format("failure@method:%s,args:%s,elapsed:%d;duration:%d,success:false,result:%s", joinPoint.getSignature().toShortString(), JsonHelper.toJson(args), call.elapsed(), elapsed, JsonHelper.toJson(result)));
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

    protected void otherCase(ProceedingJoinPoint joinPoint, Call call, long elapsed, Object result) {
        // 下层复写，可支持访问日志打印
    }

    private void error(ProceedingJoinPoint joinPoint, Call call, long elapsed, Throwable t) {
        Logger logger = getLogger(joinPoint, call);
        // 记录异常日志
        logger.error(String.format("exception@method:%s,args:%s,elapsed:%d;duration:%d", joinPoint.getSignature().toShortString(), JsonHelper.toJson(joinPoint.getArgs()), call.elapsed(), elapsed), t);
        // 重新抛出异常
        throw ExceptionHelper.throwException(t);
    }

    private Logger getLogger(ProceedingJoinPoint joinPoint, Call call) {
        return LoggerFactory.getLogger(StringUtils.defaultIfBlank(call.value(), joinPoint.getClass().getName()));
    }
}
