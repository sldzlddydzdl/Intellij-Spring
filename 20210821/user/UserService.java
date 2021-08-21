package user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	//dao 또는 mapper 를 통해 디비에 데이타를 넣어주거나 처리해준다.
	@Autowired
	UserMapper userMapper;
	
	public void insertUser(UserDto user) {
		
		// join 날짜가 아직 정해지지 않았으니 현재 날짜로 만들어주자
		user.setJoinDate(LocalDateTime.now());
		
		userMapper.insertUser(user);
		
	}

	public List<UserDto> getUserList() {
		// TODO Auto-generated method stub
		
		List<UserDto> userList = userMapper.selectAll();
		return userList;
	}

}
