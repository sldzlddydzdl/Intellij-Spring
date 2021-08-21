package user;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	// 사용자 한테 user 정보를 입력할 수 있는 화면을 제공할 것
	@GetMapping("/input")
	public String inputForm() {
		
		System.out.println("inputForm() 메서드 호출");
		
		
		// return "/WEB-INF/view/inputForm.jsp";
		return "inputForm";
	}  
	
	@PostMapping("/input")
	public String inputForm(UserDto user) throws UnsupportedEncodingException {
		
		System.out.println(" 포스트로 데이타 받음! ");
		
//		String userId = new String(user.getUserId().getBytes("utf-8"),"euc-kr");
		
//		user.setUserId(userId);
		System.out.println(user);
		
		userService.insertUser(user);
		
		return "result";
		
	}
	
	// 목록페이지
	@GetMapping("/list")
	public String getList(Model model) {
		
		// service 를 이용해서
		List<UserDto> userList = userService.getUserList();
		
		// 화면에 보내주기 위하여 모델객체에 보낼 데이타를 넣어준다.
		model.addAttribute("userList", userList);
		
		return "list";
		
	}

}
