package springEx05;

import org.springframework.stereotype.Component;

// 컨텍스트 스캔으로 스캔되어 빈으로 등록할수 있도록 어노테이션을 추가 !!

//@Component
public class TaekwonV {
	private Attack attack;
	private String name;
	
	public TaekwonV(Attack attack , String name) {
		this.attack = attack;
		this.name = name;
	}
	
	public void fight() {
		System.out.println(name+"이");
		attack.Attack();
	}
}
