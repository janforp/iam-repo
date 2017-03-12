package com.zbmatsu.iam.annotations;

import com.zbmatsu.iam.annotations.parser.ThingTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/3/4.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = {ThingTypeValidator.class})
public @interface ThingType {
    String message() default "{thingType.not.matches}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
