package springEx02.ex02;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

public class MyAspect {
	
	public String myAround(ProceedingJoinPoint jp) {
		// jp.getSignature().getName() 핵심기능의 메서드명 
		System.out.println("핵심기능의 메서드 명 : "+jp.getSignature().getName());
		String result="";
		long start = System.currentTimeMillis();
		try {
			// 핵심 기능을 호출함!
			result = (String)jp.proceed();
			
		} catch (Throwable e) {
			System.out.println("예외가 발생했음");
			
		}
		long end = System.currentTimeMillis();
		System.out.println("수행하는데 걸린 시간"+(end-start));
		return result;
	}
	
}
