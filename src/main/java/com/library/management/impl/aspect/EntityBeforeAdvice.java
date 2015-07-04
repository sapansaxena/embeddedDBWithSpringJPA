package com.library.management.impl.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class EntityBeforeAdvice {
	//@Pointcut("execution(* com.library.management.impl.write.service.author.AuthorWriteServiceImpl.save()))")
	//@Pointcut("execution(* com.library.management.impl.write.service.author.*.*(..)) && args(obj)")
	//@Pointcut("execution(* com.library.management.impl.repository.author.*.*(..)) && args(obj)")
	public void executePointCut(Object obj){
		System.out.println(obj);
	}
//	@Pointcut("execution(* com.library.management.impl.write.service.author.*.*()))")
	public void executePointCutBlank(){
		System.out.println("anything b");

	}
	//@Around("executePointCut(obj)")
    public Object getNameAdvice(ProceedingJoinPoint method,  Object obj){
		Class<?> objectClass = obj.getClass();
		Class[] cArg = new Class[1];
        cArg[0] = Long.class;
        Class[] dateArg = new Class[1];
        dateArg[0] = Date.class;
		try {
			Method setCreatedPersonId = objectClass.getMethod("setCreatedPersonId", cArg);
			Method setModifiedPersonId = objectClass.getMethod("setModifiedPersonId", cArg);
			Method setCreationDate = objectClass.getMethod("setCreationDate", dateArg);
			Method setModifyDate = objectClass.getMethod("setModifyDate", dateArg);
			Date nowDate = new Date(); 
			setCreatedPersonId.invoke(obj, new Long(1));
			setModifiedPersonId.invoke(obj, new Long(1));
			setCreationDate.invoke(obj, nowDate);
			setModifyDate.invoke(obj, nowDate);
		} catch (NoSuchMethodException | SecurityException | InvocationTargetException |IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Object returnValue;
        System.out.println("Executing Advice on getName()");
        try {
			return method.proceed(new Object[] {obj});
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return obj;
     //   return returnValue;
    }
//	@After("executePointCutBlank()")
    public void getNameAdviceAfter(){
        System.out.println("Executing Advice on getName()");
    }
}
