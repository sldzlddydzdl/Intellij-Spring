package springEx02.ex03;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springEx02/ex03/springContext.xml")
public class MyAopTest {

	@Autowired
	ApplicationContext ctx;
	
	@Test
	public void test() {
		Boy boy = ctx.getBean("boy", Boy.class);
		String result1 = boy.doSomething();
		System.out.println(result1);
		
		Girl girl = ctx.getBean("girl", Girl.class);
		String result2 = girl.doSomething();
		System.out.println(result2);
	}

}
