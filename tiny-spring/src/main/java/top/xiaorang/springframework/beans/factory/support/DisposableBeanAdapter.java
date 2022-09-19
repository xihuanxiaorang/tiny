package top.xiaorang.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.DisposableBean;
import top.xiaorang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/20 2:09
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        boolean isDisposableBean = bean instanceof DisposableBean;
        if (isDisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        if (StrUtil.isNotEmpty(destroyMethodName) &&
                // 且 bean 既不是 DisposableBean 的实例，指定的初始化方法也不是 DisposableBean 中的接口方法
                !(isDisposableBean && "destroy".equals(destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (destroyMethod == null) {
                throw new BeansException("Could not find an destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
