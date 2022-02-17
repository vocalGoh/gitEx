package com.example.spring02.controller.message;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring02.model.message.dto.MessageDTO;
import com.example.spring02.service.message.MessageService;

@RestController
@RequestMapping("messages/*")
public class MessageController {
	@Inject
	MessageService messageService;
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public ResponseEntity<String> addMessage(@RequestBody MessageDTO dto){
		ResponseEntity<String> entity=null;
		
		//웹페이지에서 json으로 request한 파라미터들을 java에서 받으려면 java object로의 변환이 필요하다. 
		//이런 작업을 해주는 어노테이션이 @RequestBody와 @ResponseBody이다.
		//컨트롤러에 두 어노테이션을 추가해주면 json이나 key/value 방식의 xml등으로 송수신 할 수 있다.
		//@RequestBody : http 요청의 body의 내용을 자바객체로 매핑하는 역할
		//@ResponseBody : 자바 객체를 http 요청의 body내용으로 매핑하는 역할
		//ResponseEntity : 리턴값을 json+에러메시지를 함께 처리해준다.
		
		try {
			messageService.addMessage(dto);
			entity=new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			//ex)400에러 : 상호간 변수가 안맞을때
		}
		return entity;
	}
}

//이번 작업의 핵심 포인트 : 트랙잭션의 위엄
//이번 AOP 맛보기를 통해서 알 수 있는건 트랜잭션의 위엄이다. 예를들어, 트랜잭션은 전자거래시 정전으로 인해 돈이 끊겼을때
//그 거래를 막을 수 있어애한다. 그 역할을 AOP가 해주고 JoinPoint 같은 것들이 작업의 시점을 잘 끊어주기에 AOP가 가능한듯.
//MemberService에 @Transactional이 보일것이다. 저걸 주석처리 하면 트랜잭션이 중단됨. 
//이때 mapper에 일부러 타이핑 에러를 준 후(400에러) 메시지를 보내거나 포인트를 추가하면 그게 DB에 저장되게 됨
//원래 트랜잭션이 살아있으면 mapper의 오타 때문에 값이 안들어가게 될틴데 들어가있음... 트랙잭션 처리가 안된거...