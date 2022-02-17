package com.example.spring02.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component //1. 스프링에서 관리하는 범용 bean
@Aspect //2. 공통적인 업무를 지원하는 AOP bean 등록
public class LogAdvice {
	//3. 로깅을 위한 변수
	private static final Logger logger=LoggerFactory.getLogger(LogAdvice.class);
	
	//4. Advice의 종류 및 특징
	//@Before(핵심업무 전 적용됨), @After(핵심업무 후 적용됨), @Around(전,후 모두 적용됨)
	//아래 나오는 ..은 모든 하위패키지를 의미, *(..)는 모든 메소드를 의미
	//execution : ("범위" or "범위" or "범위")
	//5. Advice의 적용 : ex) 로그인 전/후의 각 페이지처리 등
	
	@Around(
	 "execution(* com.example.spring02.controller..*Controller.*(..))"
	 + "or execution(* com.example.spring02.service..*Impl.*(..))"	
	 + "or execution(* com.example.spring02.model..dao.*Impl.*(..))")
	
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable { //6. 핵심업무가 실행되는 시점
		long start=System.currentTimeMillis();//현재시간
		Object result=joinPoint.proceed();
		//7. Join Points : method를 호출하는 시점, 예외가 발생하는 시점 등과 같이 특정 작업이 실행되는 시점을 의미함
		//Advice : Join Points에서 실행되어야 하는 코드(실제로 AOP 기능을 구현한 객체)

		String name="";
		String type=joinPoint.getSignature().getDeclaringTypeName();
		if(type.indexOf("Controller") > -1) {
			name="Controller :";//콘솔창에서 Controller : 표시됨
		}else if(type.indexOf("Service") > -1) {
			name="ServiceImpl :";
		}else if(type.indexOf("DAO") > -1) {
			name="DAOImpl :";
		}
		//호출한 클래스, method 정보를 Logger에 저장
		logger.info(name+type+"."+joinPoint.getSignature().getName()+"()");
		//method에 전달되는 매개변수들을 Logger에 저장
		logger.info(Arrays.toString(joinPoint.getArgs()));
		long end=System.currentTimeMillis();
		long time=end-start;
		logger.info("실행시간:" + time);
		return result;
	}
}//로그인을 하는등 뭔가를 하면 특정 메소드들이 실행되면서 오른쪽 짤과 같이 뜸
