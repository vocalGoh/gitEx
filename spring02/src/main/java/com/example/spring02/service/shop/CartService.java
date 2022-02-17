package com.example.spring02.service.shop;

import java.util.List;

import com.example.spring02.model.shop.dto.CartDTO;

public interface CartService {
	public List<CartDTO> cartMoney();//장바구니 챠트출력 관련
	public void insert(CartDTO dto);//장바구니에 상품저장
	public List<CartDTO> listCart(String userid);//장바구니 상품 리스트보기
	public void delete(int cart_id);//장바구니 개별 상품 삭제
	public void deleteAll(String userid);//장바구니 전체지우기
	public void update(int cart_id);//차트 관련
	public int sumMoney(String userid);//장바구니 상품 합계금액처리
	public int countCart(String userid, int product_id); //상품 갯수
	public void updateCart(CartDTO dto);//장바구니속 전체상품 수정
	public void modifyCart(CartDTO dto);//장바구니 개별상품 수량수정

}
