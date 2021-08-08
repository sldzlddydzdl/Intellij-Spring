package springEx02.ex03;

import org.springframework.stereotype.Component;

@Component
public class Girl {
	public String doSomething() {
		
		System.out.println("집에서 티비를 본다"); // 핵심

		return "I am a girl";
	}
}
