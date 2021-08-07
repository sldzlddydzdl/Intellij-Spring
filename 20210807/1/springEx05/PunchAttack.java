package springEx05;

import org.springframework.stereotype.Component;

@Component
public class PunchAttack implements Attack{
	
	@Override
	public void Attack() {
		System.out.println("펀치 공격!");
	}

}
