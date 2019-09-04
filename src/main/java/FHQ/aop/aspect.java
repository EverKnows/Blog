package FHQ.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class aspect {

    @Pointcut("execution(public void FHQ.aop.target.*(..))")
    public void point(){}

    @Before("aspect.point()")
    public void before(JoinPoint joinPoint) {
        System.out.println("====before");
    }

    @Around(value = "aspect.point()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("====Around=====");
        Object[] args = point.getArgs();
        Class declaringType = point.getSignature().getDeclaringType();
        System.out.println(declaringType.getName());
        Object o = point.proceed();
        System.out.println("====Around=====");
        return o;
    }

    @AfterReturning(value = "aspect.point()", returning = "object")
    public void afterReturning(JoinPoint joinPoint, Object object) {
        System.out.println("=====afterReturning====");
    }

    @After("aspect.point()")
    public void after(JoinPoint joinPoint) {
        System.out.println("====after====");

    }

    @AfterThrowing(value = "aspect.point()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("====afterThrowing====");
    }





}
