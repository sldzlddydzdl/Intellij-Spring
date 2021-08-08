package springEx02.ex03;

import org.springframework.stereotype.Component;

@Component
public class Boy {
	public String doSomething() {
		
		System.out.println("집에서 게임을 한다"); // 핵심
		
		return "I am a boy";
	}
}
