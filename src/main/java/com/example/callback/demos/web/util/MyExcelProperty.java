package com.example.callback.demos.web.util;

import com.alibaba.excel.converters.AutoConverter;
import com.alibaba.excel.converters.Converter;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: TokyoMomao
 * DateTime: 2023-09-25 17:41
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyExcelProperty {
    String[] value() default {""};

    int index() default -1;

    int order() default Integer.MAX_VALUE;

    Class<? extends Converter<?>> converter() default AutoConverter.class;
    String converterMap() default "";

    /** @deprecated */
    @Deprecated
    String format() default "";
}
