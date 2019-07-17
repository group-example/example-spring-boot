package example.spring.boot.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * 这个功能用spring的interceptor也可以实现。
 * 继续用切面的目的： 留给其他业务作参考。
 */
@Aspect
@Component
@Slf4j
public class WebRequestAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * example.spring.boot.web.controller.*.*(..))")
    public void webLog(){}


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.debug("------ REQUEST BEGIN ------");
        log.debug("URL : " + request.getRequestURL().toString());
        log.debug("HTTP_METHOD : " + request.getMethod());
        log.debug("IP : " + request.getRemoteAddr());
        log.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.debug("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.debug("------ REQUEST END. SPEND TIME:" + (System.currentTimeMillis() - startTime.get())+" ms. ------");
    }
}
