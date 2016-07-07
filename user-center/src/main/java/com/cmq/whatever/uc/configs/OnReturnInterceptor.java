package com.cmq.whatever.uc.configs;

import com.cmq.whatever.uc.exceptions.HttpRequestException;
import com.cmq.whatever.uc.https.results.BaseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 16/7/3.
 */
@Aspect
@Component
public class OnReturnInterceptor {

    @Pointcut("execution(* com.cmq.whatever.uc.controllers..*(..))")
    private void anyMethod(){
        System.out.println("-------");
    }//定义一个切入点

//    @AfterThrowing(value = "anyMethod()",throwing = "e")
//    public void doAfterThrow(JoinPoint joinPoint, Exception e){
//        System.out.println("例外通知"+joinPoint.getTarget().toString());
//    }
//
//    @AfterReturning(value = "anyMethod()",returning = "result")
//    public void doAfterReturning(Object result){
//
//    }

    @Around(value = "anyMethod()")
    public Object doAround(ProceedingJoinPoint pjd){
        BaseResult result = new BaseResult();
        try {
            result.setData(pjd.proceed());
        }catch (HttpRequestException e){
            result.setMsg(e.getMessage());
            result.setCode(e.getCode());
        } catch (Throwable e){
            result.setMsg(e.getMessage());
            result.setCode(100);
        }

        return result;
    }
}
