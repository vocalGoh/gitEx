package com.example.spring02.model.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.spring02.model.memo.dto.MemoDTO;

public interface MemoDAO {
	
	@Select("select * from memo order by idx desc")
	//메모리스트를 가져오는 SQL구문
	
	public List<MemoDTO> list();
	//4-5. 이번 방식은 따로 mapper를 만들어 SQL문을 적지 않는다.
	//SQL문 DAO에 만들어적는다. 방식은 위와 같다.
	//4-4번으로 돌아가자
	
	
	
		
	
	
	
	
	
	
	
	//DB에 작성자의 이름과 메모를 추가하는 기능
	@Insert("insert into memo (idx,writer,memo) values "
			+ "((select nvl(max(idx)+1,1) from memo ),"
			+ " #{writer},#{memo} )")
	
	//5-7. 위에 SQL문을 읽어보고 5-6번으로 돌아가자
	public void insert(@Param("writer") String writer, @Param("memo") String memo);
	//참조 : @Param : Mybatis의 SQL 문장에 다수의 파라미터를 전달할 때는 전달되는 변수들에 꼭 @Param 어노테이션을 붙여줘야한다.
	
	
	
	

	
	
	//해당 메모의 정보를 가져오는 SQL문
	@Select("select * from memo where idx=#{idx}")
	
	//6-9. 위에 SQL문을 읽어보고 6-8번으로 돌아가자
	public MemoDTO memo_view(@Param("idx") int idx);
	//참조 : @Param) 
	//Mybatis의 SQL 문장에 다수의 파라미터를 전달할 때는 전달되는 변수들에 꼭 @Param 어노테이션을 붙여줘야한다.
	
	
	
	
	
	
	
	//기존의 메모를 업데이트 하는 SQL문
	@Update("update memo set writer=#{writer}, memo=#{memo} "
			+ " where idx=#{idx}")
	
	//7-6. 위에 SQL문을 읽어보고 7-5번으로 돌아가자
	public void update(MemoDTO dto);
	//참조 : 위 insert와는 달리 개별적인 @Param 대신 dto로 묶어서 전달할 수 있음
	
	
	
	
	
	
	
	//기존의 데이터를  삭제하는 SQL문
	@Delete("delete from memo where idx=#{idx}")
	
	//8-8. 위에 SQL문을 읽어보고 8-7번으로 돌아가자
	public void delete(@Param("idx") int idx);
	//참조 : @Param) 
	//Mybatis의 SQL 문장에 다수의 파라미터를 전달할 때는 전달되는 변수들에 꼭 @Param 어노테이션을 붙여줘야한다.
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
