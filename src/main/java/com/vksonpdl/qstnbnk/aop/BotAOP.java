package com.vksonpdl.qstnbnk.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class BotAOP {
	
	@Pointcut(value= "execution(* com.vksonpdl.qstnbnk.tel.bot.QuizBot.*(..))")  
	private void logQuizBot()   
	{   
	} 
	
	@Pointcut(value= "execution(* com.vksonpdl.qstnbnk.service.impl.TriviaServiceImpl.*(..))")  
	private void logService()   
	{   
	} 
	
	@Around("logService()")
	public Object monitorTelegramExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		Long start = System.currentTimeMillis();
		String methodName = joinPoint.getSignature().toShortString();
		Object result = joinPoint.proceed();
		Long elapsedTime = System.currentTimeMillis()-start;
		log.info("Time taken to execute :{}: is :{}: milliseconds",methodName,elapsedTime);
		return result;
	}

}
