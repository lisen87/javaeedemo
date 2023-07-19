package com.example.demo.config;

import com.example.demo.bean.Log;
import com.example.demo.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;


@Aspect
@Component
public class GlobalAop {

    Logger logger = LoggerFactory.getLogger(getClass());

    // 拦截 com.example.demo.controller 包下面所有的方法
    /*@Before("within(com.example.demo.controller..*)")
    public void studentAccess(JoinPoint joinPoint) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object arg : joinPoint.getArgs()) {
            stringBuilder.append(arg);
            stringBuilder.append(",");
        }

        logger.info(stringBuilder.toString());
        logger.info(joinPoint.getSignature().toString());

//        Log log = new Log();
//        log.setFunName("getUserList");
//        LocalDateTime dateTime = LocalDateTime.now();
//        Date date = Date.from( dateTime.atZone( ZoneId.systemDefault()).toInstant());
//        log.setTime(date);
//        logService.add(log);
    }*/

    final LogService logService;

    public GlobalAop(LogService logService) {
        this.logService = logService;
    }

    //拦截 添加 过 com.example.demo.config.AopAnn 注解的方法
    @Around("@annotation(com.example.demo.config.AopAnn)")
    public Object onAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


        String remoteAddr = "";
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null){
            HttpServletRequest request = requestAttributes.getRequest();
            remoteAddr = requestAttributes.getRequest().getRemoteAddr();
            logger.info(remoteAddr);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Object arg : proceedingJoinPoint.getArgs()) {
            if (!(arg instanceof HttpServletRequest)){
                stringBuilder.append(arg);
                stringBuilder.append(",");
            }
        }
        logger.info("--------------------------------------------------------------------------------------------------------------");
        logger.info("方法： --> "+proceedingJoinPoint.getSignature().toString());
        logger.info("参数-- > " + stringBuilder.toString());
        logger.info("--------------------------------------------------------------------------------------------------------------");

        addLog(proceedingJoinPoint.getSignature().toString(),stringBuilder.toString(),remoteAddr);

        Object proceed = proceedingJoinPoint.proceed();

        logger.info(proceed.toString());
        return proceed;
    }

    private void addLog(String methodName,String param,String ip){
        Log log = new Log();
        log.setFunName(methodName);
        log.setFunValue(param);
        log.setIp(ip);
        log.setUserId(new Random().nextInt(100));
        LocalDateTime dateTime = LocalDateTime.now();
        Date date = Date.from( dateTime.atZone( ZoneId.systemDefault()).toInstant());
        log.setTime(date);
        logService.add(log);
    }

}
