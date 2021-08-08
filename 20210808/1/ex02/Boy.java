package springEx02.ex02;

public class Boy {
	public String doSomething() {
		
//		System.out.println("집에 문을 열고 들어간다"); // cross cutting
		System.out.println("집에서 게임을 한다"); // 핵심
//		System.out.println("문을 닫고 집을 나온다"); // cross cutting

//		int a = (int)(Math.random()*2);
//		System.out.println(100/a);
		
		try {
			Thread.sleep(5000); // 5초간 쉼
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "I am a boy";
	}
}
