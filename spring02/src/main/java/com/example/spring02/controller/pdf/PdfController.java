package com.example.spring02.controller.pdf;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.pdf.PdfService;

@Controller
@RequestMapping("pdf/*") //공통 url mapping
public class PdfController {
	
	@Inject
	PdfService pdfService;
	
	
	//3. /pdf/list.do 반응하는 PdfController.java 만듬
	//(controller/pdf 패키지에 class파일로 만들어야함)
	@RequestMapping("list.do") //세부 url
	
	public ModelAndView list() throws Exception {
		
		//4. pdf파일 생성 : PdfService를 넘어 PdfServiceImpl로 이동해보자(5번)
		String result=pdfService.createPdf();
		
		//화면 이동
		return new ModelAndView("pdf/result", "message", result);
	}

}
