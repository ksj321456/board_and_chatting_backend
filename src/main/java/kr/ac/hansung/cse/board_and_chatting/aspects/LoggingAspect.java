package kr.ac.hansung.cse.board_and_chatting.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();
    ThreadLocal<Long> endTime = new ThreadLocal<>();

    @Before("execution(* kr.ac.hansung.cse.board_and_chatting.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before: " + joinPoint.getSignature().getName() + " + current time = " + LocalDateTime.now());

        Object[] args = joinPoint.getArgs();
        Object dto = args[0];
        log.info("dto: " + dto);
        startTime.set(System.currentTimeMillis());
    }

    @After("execution(* kr.ac.hansung.cse.board_and_chatting.controller.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        endTime.set(System.currentTimeMillis());
        log.info("After: " + joinPoint.getSignature().getName() + " + taken time: " + (endTime.get() - startTime.get()) + "ms");
    }

}
