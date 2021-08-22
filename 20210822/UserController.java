package user;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 수정요청
	@GetMapping("/modify")
	public String modifyUser(@RequestParam int id, Model m) {
		
		System.out.println("id : " + id);
		
		// 서비스를 이용하여 해당되는 user 정보를 가져온다
		UserDto user = userService.getUserById(id); 
		
		// 화면에 유저 정보를 보내기위해 모델객체에 담아준다.
		System.out.println(user);
		m.addAttribute("user", user);
		
		return "modifyForm";
	}
	
	// 수정
	@PostMapping("/modify")
	public String modifyUser(UserDto user, Model m) {
		System.out.println("modify post : " + user );
		// 수정 결과에 대한 나용을 result 에 담음
		String result = userService.modifyUser(user);
		
		// 화면에 결과를 보여줌
		m.addAttribute("result", result);
		
		return "result";
		
	}
	
	// 삭제요청
	@GetMapping("/delete")
	public String deleteUser(int id, Model model) {
		UserDto user = userService.getUserById(id);
		
		model.addAttribute("user" , user);
		
		return "deleteForm";
	}
	
	@PostMapping("/delete")
	public String deleteUser(int id ,String password , Model model) {
		
		String result = userService.deleteUserById(id, password);
		
		model.addAttribute("result", result);
		
		return "result";
	}

}












