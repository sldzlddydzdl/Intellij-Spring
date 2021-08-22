package board;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BoardMapper {
	
	@Insert("insert into board(id, title, content, writer, write_date, update_date) values(#{id}, #{title}, #{content}, #{writer}, #{writeDate}, #{updateDate})")
	public int insertBoard(BoardDto boardDto);

	@Select("select id, title, content, writer, write_date, update_date from board")
	public List<BoardDto> selectAll();

	@Select("select id, title, content, writer, write_date, update_date from board where id = #{id}")
	public BoardDto selectById(int id);

}
