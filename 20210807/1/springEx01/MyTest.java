package springEx01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MyTest {
//　스프링컨테이너를　만들고，　빈을　가져와서　출력해보자

	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("springEx01/springContext.xml");
		
		System.out.println("스프링 컨테이너 생성");
		System.out.println(ctx);
		
//		<bean id="myPerson" class="springEx01.Person"/> springContext.xml 에서 빈으로 설정하면 ApplicationContext 을 부를때 알아서 객체를 생성해준다
//		따라서 Person p = new Person(); 을 안해줘도 된다.
		Person p = (Person)ctx.getBean("myPerson"); // 다운 캐스팅을 해줘야한다 왜냐면 스프링은 빈에서 가져온게 뭔지모르니까 
		System.out.println(p);
	}
}
