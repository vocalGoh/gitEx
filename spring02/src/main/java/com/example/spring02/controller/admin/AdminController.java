package com.example.spring02.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.service.admin.AdminService;
import com.example.spring02.service.shop.ProductService;

@Controller
@RequestMapping("admin/*")
public class AdminController {
	@Inject
	AdminService adminService;
	@Inject //의존관계주입
	ProductService productService;
	
	@RequestMapping("login.do")
	public String login() {
		return "admin/login";//login.jsp로 이동
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto, 
			HttpSession session, ModelAndView mav) {
		String name=adminService.loginCheck(dto);//로그인 체크
		if(name != null) {//로그인 성공=>세션변수 저장
			//관리자용 세션변수
			session.setAttribute("admin_userid", dto.getUserid());
			session.setAttribute("admin_name", name);
			//일반 사용자용 세션변수(관리자도 일반사용자에 포함)
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("admin/admin");//admin.jsp로 이동
		}else {//로그인 실패
			mav.setViewName("admin/login");//login.jsp로 이동
			mav.addObject("message","error");
		}
		return mav;
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();//세션 초기화
		//관리자 로그인 페이지로 이동
		return "redirect:/admin/login.do";
	}
	
	@RequestMapping("write.do")
	public String write() {
		//admin/admin_product_write.jsp로 포워딩
		return "admin/admin_product_write";
	}
	
	@RequestMapping("list.do") //세부 url
	public ModelAndView list(ModelAndView mav) {
		//포워딩할 뷰의 경로
		mav.setViewName("/admin/admin_product_list");
		//정달할 데이터
		mav.addObject("list", productService.listProduct());
		return mav;
	}

}
