package com.zbmatsu.iam.annotations.parser;

import com.zbmatsu.iam.annotations.ThingType;
import com.zbmatsu.iam.common.PropertiesBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/4.
 */
public class ThingTypeValidator implements ConstraintValidator<ThingType, Object> {


    @Autowired
    protected PropertiesBean propertiesBean;

    @Override
    public void initialize(ThingType constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {


        String messageTemplate = context.getDefaultConstraintMessageTemplate();
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation();

        if(value == null){
            return false;
        }

        //系统必定会初始化一个types，所以此处不必当心 types为null
        if(Arrays.asList(propertiesBean.getTypes()).contains(value.toString())){
            return true;
        }else{
            return false;
        }

    }

}
