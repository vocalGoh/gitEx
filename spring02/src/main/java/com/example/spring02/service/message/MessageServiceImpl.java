package com.example.spring02.service.message;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02.model.message.dao.MessageDAO;
import com.example.spring02.model.message.dao.PointDAO;
import com.example.spring02.model.message.dto.MessageDTO;

@Service
public class MessageServiceImpl implements MessageService {
	
	//Bean을 2개이상 주입시 따로처리해야함. 즉, Inject를 각각 해야함.
	@Inject
	MessageDAO messageDao;
	@Inject
	PointDAO pointDao;
	
	//트랜잭션 처리 대상 method(해당메소드만 트랜잭션 적용)
	@Transactional
	@Override
	public void addMessage(MessageDTO dto) {
		//메시지를 테이블에 저장
		messageDao.create(dto);
		//메시지를 보낸 회원에게 10포인트 추가
		pointDao.updatePoint(dto.getSender(), 10);
	}

	@Override
	public MessageDTO readMessage(String userid, int mid) {
		// TODO Auto-generated method stub
		return null;
	}
}
