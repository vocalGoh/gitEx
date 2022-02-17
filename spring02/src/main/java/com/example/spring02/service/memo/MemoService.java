package com.example.spring02.service.memo;

import java.util.List;

import com.example.spring02.model.memo.dto.MemoDTO;

public interface MemoService {
	
	
	//4-3. MemoServiceImpl의 list()로 이동(4-4번)
	public List<MemoDTO> list();
	//4-2번으로 돌아가자
	
	
	
	
	
	
	
	//5-5. MemoServiceImpl의 insert()로 이동(5-6번)
	public void insert(MemoDTO dto);
	//5-4번으로 복귀
	
	
	
	//6-7. MemoServiceImpl의 memo_view()로 이동(6-8번)
	public MemoDTO memo_view(int idx);
	//6-6번으로 복귀
	

	
	
	//7-4. MemoServiceImpl의 update()로 이동(7-5번)
	public void update(MemoDTO dto);
	//7-3번으로 복귀
	
	
	

	
	//8-6. MemoServiceImpl의 delete()로 이동(8-7번)
	public void delete(int idx);
	//8-5번으로 이동
	
	
	
	
	
	
	
	
	
	
}
