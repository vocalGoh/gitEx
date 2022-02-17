package com.example.spring02.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor 
extends HandlerInterceptorAdapter{
	
	//지금 하는 것 : 관리자용 로그인 인터셉터 만들기
	
	//전처리 : 메인 액션이 실행되기 전
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		//세션 체크
		HttpSession session=request.getSession();
		if(session.getAttribute("admin_userid") == null) {//세션이 없으면(로그인되지 않은 상태)
			//관리자 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath()+"/admin/login.do?message=nologin");
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
