package com.zbmatsu.iam.annotations.parser;


import com.alibaba.fastjson.JSON;
import com.zbmatsu.iam.annotations.JsonArray;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Administrator on 2017/3/4
 * 自定义校验规则
 */
public class JsonArrayValidator implements ConstraintValidator<JsonArray, Object> {

    @Override
    public void initialize(JsonArray constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String messageTemplate = context.getDefaultConstraintMessageTemplate();
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation();

        if(value == null){
            return true;
        }

        String str = value.toString();

        //如果能转换成JsonObject对象 则校验通过， 否则 return false 校验不通过
        try {
            JSON.parseArray(str);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
