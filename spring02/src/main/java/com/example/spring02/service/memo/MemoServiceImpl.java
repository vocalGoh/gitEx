package com.example.spring02.service.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.memo.dao.MemoDAO;
import com.example.spring02.model.memo.dto.MemoDTO;

//3-4. Service 설정
//3-5. 컨트롤러에게 본클래스가 Service 클래스임을 명시(bean 등록)
@Service
public class MemoServiceImpl implements MemoService {
	//3-6. DAO와 연결
	@Inject 
	MemoDAO memoDao;

	//4-4. DAO의 list()로 이동(4-5번)
	@Override
	public List<MemoDTO> list() {
		return memoDao.list();
		//참조 : list()가 완성된 인터페이스가 아닌데도 쓸 수 있는 이유는 
		//스프링이 root-context.xml에서 지정한 mybatis객체를 
		//메모리에 올려 셋팅에 의해 생성된 객체를 자동 결합시키며 ArrayList로 받아하기 때문
	}//4-3번으로 돌아가자

	

	
	
	
	
	
	//5-6. DAO의 insert()로 이동(5-7번)
	@Override
	public void insert(MemoDTO dto) {
		memoDao.insert(dto.getWriter(), dto.getMemo());
	}//5-5번으로 복귀

	
	
	
	
	
	
	

	
	
	
	//6-8. DAO의 memo_view()로 이동(6-9번)
	@Override
	public MemoDTO memo_view(int idx) {
		return memoDao.memo_view(idx);
	}//6-7번으로 복귀
	
	
	
	
	
	
	
	
	

	//7-5. DAO의 update()로 이동(7-6번)
	@Override
	public void update(MemoDTO dto) {
		memoDao.update(dto);
	}//7-4번으로 복귀

	
	
	
	
	
	
	
	//8-7. DAO의 delete()로 이동(8-8번)
	@Override
	public void delete(int idx) {
		memoDao.delete(idx);
	}//8-6번으로 복귀
	
	
	
	
	
	
	
	
	
	
	
	
	

}
