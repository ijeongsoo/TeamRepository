package com.mycompany.myapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.mycompany.myapp.dto.Member;

@Component
@Aspect
public class LoginCheckAspect {
	@Pointcut("execution(public * com.mycompany.myapp.controller.NoSessionPageController.*(..))" )
	private void noSession(){}
	
	@Pointcut("execution(public * com.mycompany.myapp.controller.SessionPageController.*(..))" )
	private void session(){}
	
	//Advice
		@Around("noSession()")
		public Object noSessionCheckAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
			//before code
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			Member member = (Member)requestAttributes.getAttribute("login_info", RequestAttributes.SCOPE_SESSION);
			if(member!=null){
				return "redirect:loginHome";
			}else{
				Object result=joinPoint.proceed();
				return result;
			}

		}
		
		@Around("session()")
		public Object sessionCheckAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
			//before code
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			Member member = (Member)requestAttributes.getAttribute("login_info", RequestAttributes.SCOPE_SESSION);
			if(member==null){
				return "redirect:/";
			}else{
				Object result=joinPoint.proceed();
				return result;
			}

		}
}
