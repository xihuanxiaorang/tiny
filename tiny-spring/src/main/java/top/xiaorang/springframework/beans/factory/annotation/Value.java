package top.xiaorang.springframework.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/22 2:10
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
    /**
     * The actual value expression such as <code>#{systemProperties.myProp}</code>
     * or property placeholder such as <code>${my.app.myProp}</code>.
     */
    String value();
}
