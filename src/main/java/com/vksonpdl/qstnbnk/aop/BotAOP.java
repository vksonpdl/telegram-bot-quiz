package com.vksonpdl.qstnbnk.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class BotAOP {
	
	@Pointcut(value= "execution(* com.vksonpdl.qstnbnk.tel.bot.QuizBot.*(..))")  
	private void logDisplayingBalance()   
	{   
	} 
	
	@Around("logDisplayingBalance()")
	public Object monitorTelegramExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		Long start = System.currentTimeMillis();
		String methodName = joinPoint.getSignature().toString();
		Object result = joinPoint.proceed();
		Long elapsedTime = System.currentTimeMillis()-start;
		log.info("Time taken to execute :{}: is :{}: milliseconds",methodName,elapsedTime);
		return result;
	}

}
