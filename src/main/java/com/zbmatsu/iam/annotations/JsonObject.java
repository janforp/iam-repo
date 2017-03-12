package com.zbmatsu.iam.annotations;


import com.zbmatsu.iam.annotations.parser.JsonObjectValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/3/4.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = {JsonObjectValidator.class})
public @interface JsonObject {

    String message() default "{is.not.json}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
