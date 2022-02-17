package com.example.spring02.controller.chart;

import java.io.FileOutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.chart.JFreeChartService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping("jchart/*")
public class JFreeChartController {
	
	//2. JFreeChartService 연결
	@Inject
	JFreeChartService chartService;
	
	//3. 관리자용 메뉴에서 JFreeChart(png)르르 누르면 발동됨
	@RequestMapping("chart1.do")
	public void createChart1(HttpServletResponse response) {
		try {
			//차트 객체 리턴
			JFreeChart chart=chartService.createChart();
			//차트를 png파일 이미지로 export
			ChartUtilities.writeChartAsPNG(
					response.getOutputStream(), chart, 900, 550);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//1. JFreeChart(pdf)
	//2. 관리자용 메뉴에서 JFreeChart(pdf)를 누르면 발동됨
	@RequestMapping("chart2.do")
	public ModelAndView createChart2(HttpServletResponse response) {
		String message="";
		try {
			//3. 차트 객체 리턴 : 방금전에 봤던 그 creativeChart 맞다..
			JFreeChart chart=chartService.createChart();
			//pdf 문서 객체
			Document document=new Document();
			//pdf 생성 객체
			PdfWriter.getInstance(document, 
					new FileOutputStream("c:/test/test.pdf"));
			document.open();
			//차트를 itextpdf 라이브러리에서 지원하는 이미지 형식으로
			Image png=Image.getInstance(
					ChartUtilities.encodeAsPNG(
							chart.createBufferedImage(500, 500)));
			//pdf 문서에 이미지를 추가
			document.add(png);
			document.close();//pdf문서가 저장됨
			message="pdf 파일이 생성되었습니다.";
			
		} catch (Exception e) {
			message="pdf 파일 생성 실패...";
			e.printStackTrace();
		}
		return new ModelAndView("chart/jchart02", "message",message);	
	}
}




















