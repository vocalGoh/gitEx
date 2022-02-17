package com.example.spring02.controller.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.memo.dto.MemoDTO;
import com.example.spring02.service.memo.MemoService;

//참조 : 스프링에게 제어권을 맡긴 3가지
//1) 컨트롤러 : @Controller, @RestController(json으로 리턴하는 컨트롤러)
//2) 서비스 : @Service
//3) 모델(dao) : @Repository

//3. Controller 설정
@Controller //3-1. 컨트롤러 빈으로 등록
@RequestMapping("memo/*") //3-2. 공통적인 url pattern 설정
public class MemoController {
	
	//3-3. Service와 연결
	@Inject
	MemoService memoService;
	
	
	//4. 메모 리스트들을 우리에게 보여주는 기능 만들기
	//4-1. url mapping : 세부적인 url
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		List<MemoDTO> items = memoService.list();
		//4-2. MemoService의 list()로 이동(4-3번)
		//이제 items엔 메모 리스트들이 담겨있다
		
		//4-6. memo_list.jsp에 메모 리스트들을 담아서 포워딩
		//memo_list로 이동하자(4-7번)
		mav.setViewName("memo/memo_list");
		mav.addObject("list", items);
		return mav;
		//return new ModelAndView("memo/memo_list","list",items);
	}
	
	
	
	
	//5-2. 메모장 페이지에서 확인 버튼을 누르면 "insert.do url"이 들어옴
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute MemoDTO dto) {
		//5-3. @ModelAttribute : Controller 메소드의 파라미터나 리턴값을 Model 객체와 바인딩하기 위한 어노테이션
		//5-4. MemoService의 insert()로 이동(5-5번)
		memoService.insert(dto);
		//작성자의 이름과 메모를 추가하고옴
		//5-8. 4-1번 루트를 타고 다시 리스트를 보여주는 페이지로 이동
		return "redirect:/memo/list.do";
	}//5번 끝. memo_list.jsp의 6번으로 넘어가자
	
	
	
	
	
	
	
	
	
	
	
	//6-3. url이 들어오긴 했는데... 변수가 끼어서 들어왔다! 이거 어카노?(6-4번)
	@RequestMapping("view/{idx}")
	public ModelAndView view(@PathVariable int idx, ModelAndView mav) {
		//6-4. @PathVariable
		//역할 : 말 그대로 URL 경로에 변수(여기선 idx)를 넣어주는 역할
		//사용법 : {템플릿 변수} 와 동일한 이름을 갖는 파라미터를 추가하면 된다. 
		
		//6-5. 포워딩 : view.jsp로 이동(6-10번)
		mav.setViewName("memo/view");
		//6-6. 전달할 데이터 : 일단 MemoService의 memo_view로 이동(6-7번)
		mav.addObject("dto", memoService.memo_view(idx));
		//해당 메모의 정보를 가져왔음
		
		return mav;
	}//6-10번으로 이동
	
	
	
	
	
	
	
	
	
	//7-2. url mapping
	@RequestMapping("update/{idx}")
	public String update(@PathVariable int idx, @ModelAttribute MemoDTO dto) {
		//7-3. 6-3번처럼 url에 변수가 끼어서 들어왔으니 @PathVariable이 필요하고
		//@ModelAttribute를 이용하여 Controller 메소드의 파라미터를 Model 객체와 바인딩
		
		//7-3. 메모수정 : MemoService의 update()로 이동(7-4번)
		memoService.update(dto);
		//수정완료 후 목록으로 이동
		//4-1번 루트를 통해 리스트로 돌아옴
		return "redirect:/memo/list.do";
	}
	
	
	
	
	

	
	
	//8-3. url mapping
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx) {
		//8-4. 6-3번처럼 url에 변수가 끼어서 들어왔으니 @PathVariable이 필요
		
		//8-5. 레코드 삭제처리 : MemoService의 delete()로 이동(8-6번)
		memoService.delete(idx);
		//기존의 데이터를 삭제하고 옴
		//8-9. 목록으로 이동 : 4-1번 루트를 통해 리스트로 돌아옴. 8번 끝
		return "redirect:/memo/list.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
