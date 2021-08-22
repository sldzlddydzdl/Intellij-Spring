package board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	public void insertBoard(BoardDto boardDto) {
		
		boardMapper.insertBoard(boardDto);
		
	}

	public List<BoardDto> selectAll() {
		
		List<BoardDto> result = boardMapper.selectAll();
		
		return result;
	}

	public BoardDto selectById(int id) {
		// TODO Auto-generated method stub
		BoardDto board =  boardMapper.selectById(id);
		
		return board;
	}

}
