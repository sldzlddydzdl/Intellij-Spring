package SpringEx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MyTest {
	public static void main(String[] args) {
		
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringEx/springContext.xml");
		
		Student s1 = ctx.getBean("myStudent" , Student.class);
		
		System.out.println("이름 : " + s1.getName() );

		System.out.println("나이 : " + s1.getAge() );
		
		float avg = (float)(s1.getScore().getKor()+s1.getScore().getEng()+s1.getScore().getMath())/3; 
		System.out.println("평균 : " + avg);
		
		System.out.println("주소 : " + s1.getAddress().getSi() + " " + s1.getAddress().getDong());
		
		
	}

}
