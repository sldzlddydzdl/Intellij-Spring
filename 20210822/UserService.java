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

	public UserDto getUserById(int id) {
		// mapper 를 이용하여 id로 user 객체를 조회한다.
		UserDto user = userMapper.selectUserById(id);
		
		
		
		return user;
	}

	// 유저를 수정하는 메서드
	public String modifyUser(UserDto user) {
		
		int rows = userMapper.updateUser(user);
		
		if(rows > 0) {
			return "수정 성공";
		}
		
		return "수정 실패";
	}

	// 삭제로직 수행
	public String deleteUserById(int id, String password) {
		
		// 데이타가 있는지 확인
		UserDto user = userMapper.selectUserById(id);
		
		int rows = 0;
		if(user != null && user.getPassword().equals(password)) {
			rows = userMapper.deleteUserById(id);
		}
		
		if( rows > 0 ) {
			return "삭제 성공";
		}
		
		return "삭제 실패";
	}

}
