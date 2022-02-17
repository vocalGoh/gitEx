package com.example.spring02.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;

@Controller
@RequestMapping("shop/cart/*") //공통 url
public class CartController {
	@Inject //Service와 연결
	CartService cartService; 
	
	//6-2. 장바구니 버튼을 눌렀을때 발동된 URL(list.do)을 타고 들어옴
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {		
		Map<String, Object> map = new HashMap<>();
		//세션 변수 확인
		String userid=(String)session.getAttribute("userid");
		if(userid != null) {//로그인한 경우
			//6-3. Service-DAO를 타고 cartMapper로 이동(6-4번)
			List<CartDTO> list = cartService.listCart(userid);
			//사용자의 정보를 기반으로 사용자의 장바구니 내역이 list에 들어간다
						
			//7-2. 장바구니 합계 계산 : Controller의 list.do에 추가!
			int sumMoney = cartService.sumMoney(userid);
			//배송료 계산
			int fee = sumMoney >= 30000 ? 0 : 2500; //합계3만원이상이면 배송료0원
			map.put("sumMoney", sumMoney);//장바구니 금액 합계
			map.put("fee", fee); //배송료
			map.put("sum", sumMoney+fee);//총합계금액(결제금액). 7번 배송비 추가 끝!
						
			map.put("list", list);//맵에 자료 추가
			map.put("count", list.size());
			
			//6-5. 포워딩 및 페이지에 값 전달 : cart_list.jsp로 이동하자(6-7번)
			mav.setViewName("shop/cart_list");//jsp 페이지 이름
			mav.addObject("map", map);//jsp에 전달할 데이터
			return mav;
		}else {//6-6. 로그인하지 않은 경우 : 로그인 페이지로 이동
			return new ModelAndView("member/login", "", null);
		}
	}
	
	
	
	//6-9. 상품을 장바구니에 추가시키기
	@RequestMapping("insert.do") //세부 url
	public String insert(HttpSession session, @ModelAttribute CartDTO dto) {
		
		//세션에 userid 변수가 존재하는지 확인
		String userid = (String)session.getAttribute("userid");
		
		if(userid == null) {//로그인 하지 않은 상태
			return "redirect:/member/login.do"; //로그인 페이지로
		}
		//6-10. 로그인 했을땐 userid를 등록시키고 Service_DAO를 거쳐 cartMapper로 이동한다(6-11번)
		dto.setUserid(userid);
		cartService.insert(dto);
		//6-12. 장바구니에 insert처리 후 장바구니 목록 이동 : 6-2번 타고 돌아감
		return "redirect:/shop/cart/list.do";
	}
	
	
	

	
	
	

	//8. 장바구니 목록 삭제 기능 추가
	//참조)
	//@RequestParam : request.getParameter()
	//@ModelAttribute : 폼데이터 전체이를 dto로 저장

	//8-1. 장바구니에서 삭제 링크를 누르면 아래와 같은 URL이 들어옴
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cart_id, HttpSession session) {
		if(session.getAttribute("userid") != null) {
			//8-2. Service-DAO를 거쳐 mapper로 이동(8-3번)
			cartService.delete(cart_id);
		}
		return "redirect:/shop/cart/list.do";
	}
		
	
	
	
	//9. 장바구니 모두 비우기
	//9-1. 장바구니 페이지에서 장바구니 지우기 버튼을 누르면 아래 URL이 들어온다.
	@RequestMapping("deleteAll.do")
	public String deleteAll(HttpSession session) {
		//세션변수 조회
		String userid=(String)session.getAttribute("userid");
		if(userid != null) {//로그인한 상태면
			//9-2. 장바구니를 비우고 : Service-DAO를 거쳐 mapper로 이동(9-3번)
			cartService.deleteAll(userid);
		}
		return "redirect:/shop/cart/list.do";
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	//10. 수정기능
	//10-1. cart_list에서 수정버튼을 누르면 form태그가 발동되면서 아래 URL이 들어옴
	@RequestMapping("update.do")
	public String update(@RequestParam int[] amount, @RequestParam int[] cart_id, HttpSession session){
		//10-2. 배열처리하는 이유)
		//상품의 수량뿐만이 아니라 다양한 것들을 수정해줘야하기 때문
		
		//세션변수 조회
		String userid=(String)session.getAttribute("userid");
		
		if(userid != null) {
			//hidden으로 넘어오는 cart_id는 배열값으로 넘어온다. 따라서 ArrayList 또는 배열로 처리한다.
			for(int i=0; i<cart_id.length; i++) {
				
				if(amount[i] == 0) {//수량이 0이면 레코드 삭제
					cartService.delete(cart_id[i]);
					
				}else {//수량이 0이 아니면 수정
					CartDTO dto=new CartDTO();
					dto.setUserid(userid);
					dto.setCart_id(cart_id[i]);
					dto.setAmount(amount[i]);
					
					//10-3. Service-DAO를 넘어 mapper로 이동(10-4번)
					cartService.modifyCart(dto);
				}
			}
		}
		return "redirect:/shop/cart/list.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
