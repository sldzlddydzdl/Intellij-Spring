package springEx03;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MyTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("springEx03/springContext.xml");
		
		Person p = ctx.getBean("myPerson", Person.class);
		System.out.println(p);
		
		List<String> foods = p.getFavoriteFoods();
		foods.forEach(System.out::println);
		
		Phone phone = ctx.getBean("phone", Phone.class);
		System.out.println(phone);
		
	}
}
