package com.example.spring02.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor 
extends HandlerInterceptorAdapter{
	//로깅을 위한 변수
	private static final Logger logger=
			LoggerFactory.getLogger(SampleInterceptor.class);

	//선처리 : 어떤 작업 처리전 아래 작업이 먼저 진행됨
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
					throws Exception {
		logger.info("pre handle 작동....");

		return true;//true면 계속진행, false면 멈춤
	}

	//후처리 : 어떤 작업 진행 후 아래 작업이 진행됨
	@Override
	public void postHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("post handle 작동....");

	}
	//위와 같이 인터셉트 테스트를 해보기 위해 샘플 인터셉터를 만들어 들어보았다.
	//이 인터셉터는 "/shop/" url이 발동되며, 상품에 관한 링크를 누를때마다 반응한다.
	//상품에 관한 링크를 누르면 그 작업의 처리전 "pre handle 작동...."이 콘솔에 찍히고
	//작업 처리 후 "post handle 작동...."이 콘솔에 찍힌다.
	//인터셉터를 다 만들었으면 옆과 같이 servlet-context.xml에 등록하러 간다.
	//어떤 url에 인터셉터가 발동하고 싶은지 주소와 인터셉터 이름을 적음.
}
