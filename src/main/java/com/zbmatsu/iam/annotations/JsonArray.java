package com.zbmatsu.iam.annotations;


import com.zbmatsu.iam.annotations.parser.JsonArrayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/3/6
 * 自定义属性验证注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
//约束,校验的规则
@Constraint(validatedBy = {JsonArrayValidator.class})
public @interface JsonArray {

    String message() default "{is.not.json}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
