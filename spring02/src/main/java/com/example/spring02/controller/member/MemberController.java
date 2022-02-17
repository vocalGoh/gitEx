package com.example.spring02.controller.member;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.service.member.MemberService;

@Controller
@RequestMapping("member/*") //공통 url
public class MemberController {
	//로깅
	private static final Logger logger
	=LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService memberService;
	
	@RequestMapping("address.do")
	public String address() {
		return "member/join";//join.jsp로 이동
	}
	
	
	//회원리스트
	@RequestMapping("list.do")
	public String memberList(Model model) {
		List<MemberDTO> list=memberService.list();
		model.addAttribute("list", list);
		return "member/member_list";
	}
	
	
	//회원등록
	@RequestMapping("write.do")
	public String write() {
		return "member/write";
	}
	
	
	//회원insert
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		memberService.insertMember(dto);
		return "redirect:/member/list.do";
	}
	
	
	//회원수정관련
	@RequestMapping("view.do")
	public String view(@RequestParam String userid, Model model) {
		//모델에 자료 저장
		model.addAttribute("dto", memberService.viewMember(userid));
		return "member/view";
	}
	
	
	
	
	
	
	
	//회원수정처리
	@RequestMapping("update.do")
	public String update(MemberDTO dto, Model model) {
		//비밀번호 체크
		boolean result=memberService.checkPw(dto.getUserid(), 
				dto.getPasswd());
		if(result) {//비밀번호가 맞으면
			//회원정보 수정
			memberService.updateMember(dto);
			//수정 후 목록으로 이동
			return "redirect:/member/list.do";
		}else {//비밀번호가 틀리면
			model.addAttribute("dto", dto);
			model.addAttribute("join_date", memberService.viewMember(
					dto.getUserid()).getJoin_date());
			model.addAttribute("message", "비밀번호를 확인하세요.");
			return "member/view";
		}
	}
	
	//회원삭제
	@RequestMapping("delete.do")
	public String delete(String userid, String passwd, Model model) {
		boolean result=memberService.checkPw(userid, passwd);
		if(result) {//비번이 맞으면 삭제
			memberService.deleteMember(userid);
			return "redirect:/member/list.do";
		}else {
			model.addAttribute("message", "비밀번호를 확인하세요");
			model.addAttribute("dto", memberService.viewMember(userid));
			return "member/view";
		}
		
	}
	
	
	
	@RequestMapping("login.do") //세부 url
	public String login() {
		return "member/login";//login.jsp로 이동
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto, 
			HttpSession session) {
		//로그인 성공 true, 실패 false
		boolean result=memberService.loginCheck(dto, session);
		ModelAndView mav=new ModelAndView();
		if(result) {//로그인 성공
			mav.setViewName("home");//뷰의 이름
		}else {
			mav.setViewName("member/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		//세션 초기화
		memberService.logout(session);
		//login.jsp로 이동
		mav.setViewName("member/login");
		mav.addObject("message", "logout");
		return mav;
	}

}
