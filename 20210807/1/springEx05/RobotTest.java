package springEx05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class RobotTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("springEx05/springContext.xml");
		
		PunchAttack p = ctx.getBean("punchAttack" ,PunchAttack.class);
		p.Attack();
		
	}
}
