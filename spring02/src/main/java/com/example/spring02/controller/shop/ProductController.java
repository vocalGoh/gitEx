package com.example.spring02.controller.shop;

import java.io.File;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.ProductDTO;
import com.example.spring02.service.shop.ProductService;

@Controller
@RequestMapping("shop/product/") //공통적인 url pattern
public class ProductController {
	
	@Inject //의존관계주입
	ProductService productService;
	
	@RequestMapping("list.do") //세부 url
	public ModelAndView list(ModelAndView mav) {
		//포워딩할 뷰의 경로
		mav.setViewName("/shop/product_list");
		//정달할 데이터
		mav.addObject("list", productService.listProduct());
		return mav;
	}
	
	
	
	
	@RequestMapping("detail/{product_id}")
	public ModelAndView detail(@PathVariable int product_id, 
			ModelAndView mav) {
		//뷰
		mav.setViewName("/shop/product_detail");
		//데이터
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav;
	}
	
	
	
	
	
	
	//12-6. 
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute ProductDTO dto) {
		
		String filename="-";
		
		//12-7. 첨부파일이 있으면
		if(!dto.getFile1().isEmpty()) {
			filename=dto.getFile1().getOriginalFilename();
			try {
				//12-7-1. 디렉토리 설정
				//개발디렉토리(C:\work\spring02\src\main\webapp\WEB-INF\views\images)
				//배포디렉토리...는 아래와 같다.
				String path="C:\\work\\.metadata\\.plugins\\"
						+ "org.eclipse.wst.server.core\\tmp0\\"
						+ "wtpwebapps\\spring02\\WEB-INF\\"
						+ "views\\images\\";
				//12-7-2. 디렉토리가 존재하지 않으면 생성
				new File(path).mkdir();
				//12-7-3. 임시 디렉토리에 저장된 첨부파일을 이동
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPicture_url(filename);
		//12-8. Service, DAO를 거쳐 productMapper.xml로 이동(12-9번) 
		productService.insertProduct(dto);
		//12-9. 상품 등록후 위 list.do테크를 탄 후 상품 리스트에 등록된 상품이 보임
		return "redirect:/shop/product/list.do";
	}//insert()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//13-2. 
	@RequestMapping("edit/{product_id}")
	public ModelAndView edit(@PathVariable("product_id")
	//"edit/6"처럼(6은 제품번호) RestFull한 URI로 올땐 @PathVariable 처리
	int product_id, ModelAndView mav) {
		//13-3-1. 이동할 뷰의 이름
		mav.setViewName("shop/product_edit");
		//13-3-2. 전달 데이터 : Service를 거쳐 mapper로 이동(13-4번)
		mav.addObject("dto", productService.detailProduct(product_id));
		return mav;
	}//13-5. product_edit.jsp로 이동()
	

	
	
	
	
	
	
	
	
	
	//13-8. 상품정보 수정
	@RequestMapping("update.do")
	public String update(ProductDTO dto) {
		String filename="-";
		
		//13-9. 첨부파일이 있으면
		if(!dto.getFile1().isEmpty()) {
			filename=dto.getFile1().getOriginalFilename();
			try {
				//개발디렉토리(C:\work\spring02\src\main\webapp\WEB-INF\views\images)
				//13-9-1. 배포디렉토리는 아래와 같다
				String path="C:\\work\\.metadata\\.plugins\\"
						+ "org.eclipse.wst.server.core\\tmp0\\"
						+ "wtpwebapps\\spring02\\WEB-INF\\"
						+ "views\\images\\";
				//13-9-2. 디렉토리가 존재하지 않으면 생성
				new File(path).mkdir();
				//13-9-3. 임시 디렉토리에 저장된 첨부파일을 이동
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setPicture_url(filename);
		//13-10. 새로운 첨부 파일이 없을 때	
		}else {
			//13-10-1. 기존에 첨부한 파일 정보를 가져옴 : Service, DAO를 거쳐 Mapper로 이동
			//(13-4번)테크임
			ProductDTO dto2=productService.detailProduct(dto.getProduct_id());
			dto.setPicture_url(dto2.getPicture_url());
		}
		//13-11. 상품정보 수정 : Service, DAO를 거쳐 Mapper로 이동(13-12번)
		productService.updateProduct(dto);
		return "redirect:/shop/product/list.do"; //13-13. 상품목록으로 이동
	}//13번 끝
	
	
	
	

	
	//14-2. 
	@RequestMapping("delete.do")
	public String delete(@RequestParam int product_id) {
		//14-3. 첨부파일 삭제 : Service, DAO를 거쳐 mapper로 이동(14-4번)
		String filename=productService.fileInfo(product_id);
		System.out.println("첨부파일 이름:"+filename);
		//14-3-1. 파일이 있으면
		if(filename != null && !filename.equals("-")) {
			String path="C:\\work\\.metadata\\.plugins\\"
					+ "org.eclipse.wst.server.core\\tmp0\\"
					+ "wtpwebapps\\spring02\\WEB-INF\\"
					+ "views\\images\\";
			File f=new File(path+filename);
			System.out.println("파일존재여부 :"+f.exists());
			if(f.exists()) {//14-3-2. 파일이 존재하면
				f.delete();//파일 목록 삭제
				System.out.println("삭제되었습니다.");
			}
		}
		//14-4. 레코드 삭제 : Service, DAO를 거쳐 mapper로 이동(14-5번)
		productService.deleteProduct(product_id);
		//14-6. 화면 이동
		return "redirect:/shop/product/list.do";
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
