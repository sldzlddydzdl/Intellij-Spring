package springEx02.ex01;

public class MyAspect {
	
	// 핵심기능이 수행되기 전에 수행할 아이!
	public void beforeDoSomething() {
		System.out.println("집에 문을 열고 들어간다"); // cross cutting
	}
	
	// 핵심기능이 수행되고난 후에 수행할 아이!
	public void afterDoSomething() {
		System.out.println("문을 닫고 집을 나온다"); // cross cutting
	}
	
	
	// 핵심 기능이 리턴을 한 후 수행할 아이!
	public void afterReturn(String msg) {
		System.out.println("msg의 값::"+msg);
		System.out.println("리턴이 끝나고!!!!");
	}
	
	// 핵심기능이 예외가 발생했을 때
	public void afterThrowing(Throwable th) {
//		th.printStackTrace();
		System.out.println("예외가 발생했음!!!");
	}
}
