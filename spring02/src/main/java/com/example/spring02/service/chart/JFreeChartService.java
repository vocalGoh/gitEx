package com.example.spring02.service.chart;

import org.jfree.chart.JFreeChart;

public interface JFreeChartService {
	public JFreeChart createChart();
//구글 차트 : 리스트 => json => 자바스크립트로 모두 처리
//JFree 차트 : 테이블 => 리스트 => 데이터셋 => 차트그리기 : 서버에서 모두 처리
}
