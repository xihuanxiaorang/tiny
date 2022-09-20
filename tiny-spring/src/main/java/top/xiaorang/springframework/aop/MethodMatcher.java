package top.xiaorang.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author liulei
 * @description 方法匹配器(检查目标方法是否符合通知条件)
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 2:55
 */
public interface MethodMatcher {
    /**
     * 执行静态检查给定方法是否匹配。
     *
     * @param method      候选方法
     * @param targetClass 目标类
     * @return 此方法是否静态匹配
     */
    boolean matches(Method method, Class<?> targetClass);
}
