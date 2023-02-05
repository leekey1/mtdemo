package cn.com.hyun.framework.utils.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * Created by hyunwoo
 */
public class LogInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String logString = "[" + className + "." + methodName + "] - {}";

        log.info(logString, Arrays.toString(pjp.getArgs()));

        Object returnValue = pjp.proceed();

        if (returnValue instanceof Collection) {
            log.info(logString, "result size = " + ((Collection) returnValue).size());
        } else if (returnValue instanceof Map) {
            log.info(logString, "result size = " + ((Map) returnValue).size());
        } else {
            log.info(logString, returnValue);
        }

        return returnValue;
    }
}