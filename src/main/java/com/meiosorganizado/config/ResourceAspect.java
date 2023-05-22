package com.meiosorganizado.config;

import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class ResourceAspect {
    //private static final ParamValidatorEngine paramEngine = new ParamValidatorEngine();

    static final String POINTCUT_EXPRESSION = "@annotation(auditable)";

    @Before(POINTCUT_EXPRESSION)
    public void beforeResourceMethod(JoinPoint jp) throws InstantiationException, IllegalAccessException {
        val signature = (MethodSignature) jp.getSignature();
        val params = signature.getMethod().getParameters();

        log:
        {
            // LOGGER.info( "\nReturn Type: {}\nMethod: {}\nParans: {}\nArgs: {}", signature.getReturnType().getName(), signature.getMethod().getName(), signature.getMethod().getParameters(), jp.getArgs());
        }

        paramConstraints: {
         //   val vs = paramEngine.validate(params, jp.getArgs()); if (violations.size() > 0) { throw new ParamConstraintException("invalid!!", violations);}

        }
    }
     @AfterReturning( pointcut = POINTCUT_EXPRESSION, returning = "result")
     public void afterReturningResourceMethod(JoinPoint jp, Object result) throws Exception {
         val signature = (MethodSignature) jp.getSignature();
         //val rebete = signature.getMethod().getAnnotation(ResponseEntityBodyEmptyThenException.class);

         // if (rebete != null) { if (result != null) {
         //     if (result instanceof ResponseEntity) {
         //         val response = (ResponseEntity) result; if (response.getBody() == null) { val keyAndValues = Maps.<Object, Object>newHashMap(); val heardRefValues = response.getHeaders().get(rebete.headerNameRef()); if (heardRefValues != null) { keyAndValues.put(rebete.headerNameRef(), Arrays.toString(heardRefValues.toArray())); } val exception = rebete .exception() .getConstructor(String.class) .newInstance(PlaceHolder.replace(rebete.message(), keyAndValues)); throw exception; } } } else { throw new IllegalReturnTypeException("Illegal return type!"); } } }

     }
}

