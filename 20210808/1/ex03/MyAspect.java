package springEx02.ex03;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
	
	@Before(value = "execution(public String *.doSomething())")
	public void beforeSomething() {
		System.out.println("문을 열고 집에 들어간다.");
	}
	
	@After(value = "execution(public String *.doSomething())")
	public void afterSomething() {
		System.out.println("문을 닫고 집에서 나온다.");
	}
	
	@AfterReturning(value = "execution(public String *.doSomething())",returning = "msg" )
	public void afterReturning(String msg) {
		System.out.println("msg값 : " + msg);
	}
}
