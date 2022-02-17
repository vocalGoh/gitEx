package com.example.spring02.service.message;

import com.example.spring02.model.message.dto.MessageDTO;

public interface MessageService {
	//메시지 쓰기
	public void addMessage(MessageDTO dto);
	//메시지 읽기
	public MessageDTO readMessage(String userid, int mid);
}
