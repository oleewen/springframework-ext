package org.springframework.ext.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertThat;

/**
 * @author ouliyuan
 * @date 2017-12-27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CallAspectTest.SpringConfig.class)
public class CallAspectTest extends AbstractJUnit4SpringContextTests {
    @Resource
    private CallAspectClass callAspectClass;

    @Test
    public void call() throws Exception {
        String actual = callAspectClass.callMethod(888888L, "spring");

        assertThat(actual, CoreMatchers.is("888888spring"));
    }

    public static class CallAspectClass {

        @Call
        public String callMethod(Long id, String key) {
            return id + key;
        }
    }

    @Aspect
    public static class CallAspect extends CallAround {

        @Pointcut("execution(* org.springframework.ext.common.aspect.CallAspectTest.*.*(..)) && @annotation(org.springframework.ext.common.aspect.Call)")
        public void callPoint() {
        }
    }

    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan
    public static class SpringConfig {
        @Bean
        public CallAspect callAspect() {
            return new CallAspect();
        }

        @Bean
        public CallAspectClass callAspectClass() {
            return new CallAspectClass();
        }
    }
}
