package board;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/input")
	public String boardInsert() {
		
		return "boardinput";
		
	}
	
	@PostMapping("/input")
	public String boardInsert(BoardDto boardDto) {
		
		boardDto.setWriteDate(LocalDateTime.now());
		boardDto.setUpdateDate(LocalDateTime.now());
		
		System.out.println(boardDto);
		boardService.insertBoard(boardDto);
		
		return "boardresult";
	}
	
	@GetMapping("/list")
	public String boardList(Model model) {
		
		List<BoardDto> result = boardService.selectAll();
		
		model.addAttribute("boardList" ,result);
		
		return "boardlist";
	}
	
	@GetMapping("/modfiy")
	public String boardModify(@RequestParam int id, Model model) {
		
		System.out.println(id);
		BoardDto board = boardService.selectById(id);
		
		model.addAttribute("board", board);
		
		
		return "boardmodify";
	}
}
