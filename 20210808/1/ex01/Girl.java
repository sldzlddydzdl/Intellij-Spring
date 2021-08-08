package springEx02.ex01;

public class Girl {
	public String doSomething() {
		
//		System.out.println("집에 문을 열고 들어간다"); // cross cutting
		System.out.println("집에서 티비를 본다"); // 핵심
//		System.out.println("문을 닫고 집을 나온다"); // cross cutting
		
		int a = 0;
		System.out.println(100/a);
		
		return "I am a girl";
	}
}
