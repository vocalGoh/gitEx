package com.example.spring02.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor 
extends HandlerInterceptorAdapter {
	//지금 하려는 것 : 필터링!
	//로그인을 하지않고 로그인된 주소를 통해 들어오게 되면 매우 위험한 일 아니노? 그거 방지하기
	//로그인을 하지않고 로그인된 주소만 가지고 웹페이지에 들어가려고 하면 "로그인 하신 후 사용하세요" 문구가 뜸

	//전처리 : 메인 액션이 실행되기 전
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		//세션 객체 생성
		HttpSession session=request.getSession();
		//세션이 없으면(로그인되지 않은 상태)
		if(session.getAttribute("userid") == null) {
			response.sendRedirect(request.getContextPath()+"/member/login.do?message=nologin");
			return false; //메인 액션으로 가지 않음.
		}else {
			return true; //메인 액션으로 이동
		}//true면 걔속 진행, false면 멈춤
	}

	//후처리 : 메인 액션이 실행된 후 : 사실 로그인 처리 문제는 후처리가 필요없기 때문에 그냥 허수아비에 불과하다
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}
