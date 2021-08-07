package springEx04;

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
