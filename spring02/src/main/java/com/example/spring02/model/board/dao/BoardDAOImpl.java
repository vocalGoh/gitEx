package com.example.spring02.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.board.dto.BoardDTO;

@Repository //dao bean등록
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public void deleteFile(String fullName) {
		sqlSession.delete("board.deleteFile", fullName);
	}

	@Override
	public List<String> getAttach(int bno) {
		return sqlSession.selectList("board.getAttach", bno);
	}

	@Override
	public void addAttach(String fullName) {
		sqlSession.insert("board.addAttach", fullName);
	}

	@Override
	public void updateAttach(String fullName, int bno) {
		Map<String,Object> map=new HashMap<>();
		map.put("fullName", fullName);//첨부파일 이름
		map.put("bno", bno);//게시물 번호
		sqlSession.insert("board.updateAttach", map);
		//게시물 board는 update이지만 첨부파일attach는 기존 파일이 있다
		//하더라도 기존것은 그대로 두거나 또는 새파일을 올려 수정(insert)하는
		//것이기 때문에 insert()씀

	}

	@Override
	public void create(BoardDTO dto) throws Exception {
		sqlSession.insert("board.insert", dto);
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		sqlSession.update("board.update", dto);
	}

	
	
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("board.delete", bno);

	}//짤을 보면 알겠지만 show 컬럼이 N으로 업데이트 된 게시물은 화면에 나타나지 않는다.


	

	
	//게시물 목록 리턴
	@Override
	public List<BoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", "%"+keyword+"%");
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("board.listAll", map);
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		sqlSession.update("board.increaseViewcnt", bno);

	}

	
	
	
	
	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		
		Map<String,String> map=new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", "%"+keyword+"%");
		return sqlSession.selectOne("board.countArticle",map);
	}

	
	
	
	
	
	
	
	
	@Override
	public BoardDTO read(int bno) throws Exception {
		return sqlSession.selectOne("board.read", bno);
	}

}
