package top.xiaorang.springframework.beans.factory.support;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.DisposableBean;
import top.xiaorang.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liulei
 * @description 默认单实例注册表
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 1:20
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Object oldObject = this.singletonObjects.get(beanName);
        if (oldObject != null) {
            throw new IllegalStateException("Could not register object [" + singletonObject +
                    "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
        }
        addSingleton(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void destroySingletons() {
        String[] disposableBeanNames = disposableBeans.keySet().toArray(new String[0]);
        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            String beanName = disposableBeanNames[i];
            this.singletonObjects.remove(beanName);
            DisposableBean disposableBean = this.disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

    /**
     * 注册实现了DisposableBean接口的bean实例
     *
     * @param beanName bean名称
     * @param bean     bean实例
     */
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        this.disposableBeans.put(beanName, bean);
    }

    /**
     * 注册单例bean对象
     *
     * @param beanName        bean名称
     * @param singletonObject 单例bean对象
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
