package top.xiaorang.springframework.util;

import cn.hutool.core.lang.Assert;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/20 17:42
 */
public class ClassUtils {
    public static final String CGLIB_CLASS_SEPARATOR = "$$";

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
            if (cl == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }

    /**
     * 返回给定实例的用户定义类：通常只是给定实例的类，但如果是 CGLIB 生成的子类，则返回原始类。
     *
     * @param instance 要检查的实例
     * @return 用户定义的类
     */
    public static Class<?> getUserClass(Object instance) {
        Assert.notNull(instance, "Instance must not be null");
        return getUserClass(instance.getClass());
    }

    /**
     * 返回给定类的用户定义类：通常只是给定类，但如果是 CGLIB 生成的子类，则返回原始类。
     *
     * @param clazz 要检查的类
     * @return 用户定义的类
     */
    public static Class<?> getUserClass(Class<?> clazz) {
        if (clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null && superclass != Object.class) {
                return superclass;
            }
        }
        return clazz;
    }
}
