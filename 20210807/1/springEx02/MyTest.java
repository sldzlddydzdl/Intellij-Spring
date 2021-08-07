package springEx02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MyTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("springEx02/springContext.xml");
		
		Person p = ctx.getBean("myPerson", Person.class);
		System.out.println(p);
		
		Phone phone = ctx.getBean("phone", Phone.class);
		System.out.println(phone);
		
	}
}
