package me.highgo.back.util.aop;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * LogAspect.java
 *
 * @Description : 日志切面
 * @Author huangzhiwei
 * @DATE 2016/5/26
 */
@Aspect
@Component
public class LogAspect {

    public static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * me.highgo.back.controller..*.*(..))")
    public void log() {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes =  (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Map parameterMap = request.getParameterMap();
        Iterator iterator = parameterMap.keySet().iterator();

        logger.info("Invoker Url ---> " + request.getRequestURL() + " Start");
        while(iterator.hasNext()) {
            String key = (String)iterator.next();
            String[] values = (String[])parameterMap.get(key);
            this.logger.info("Parameter   : " + key + " --> " + StringUtils.join(values, ","));
        }
    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable {
        ServletRequestAttributes attributes =  (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        // 处理完请求，返回内容
        logger.info("Response : " + ret);
        this.logger.info("Invoker Url ---> " + attributes.getRequest().getRequestURL() + " End");
    }
}
