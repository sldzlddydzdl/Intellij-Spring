package user;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

	// 실제 쿼리를 실행해줄 메서드들 정의.
	// 유저 삽입
	@Insert("insert into user(id, user_id, password, birth_date, join_date) values(#{id},#{userId},#{password},#{birthDate},#{joinDate})")
	public void insertUser(UserDto user);

	// 유저 리스트 조회
	@Select("select id, user_id, password, birth_date, join_date from user order by id desc")
	public List<UserDto> selectAll();
	
}
