package springEx05;

import org.springframework.stereotype.Component;

//@Component
public class Transformer {
	private Attack attack;
	private String name;
	
	public Transformer() {
		// TODO Auto-generated constructor stub
	}
	
	public void setAttack(Attack attack) {
		this.attack = attack;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void fight() {
		System.out.println(name+"이");
		attack.Attack();
	}

}
