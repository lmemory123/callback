package com.example.callback.demos.web.util;

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

    String converter();

    /** @deprecated */
    @Deprecated
    String format() default "";
}
