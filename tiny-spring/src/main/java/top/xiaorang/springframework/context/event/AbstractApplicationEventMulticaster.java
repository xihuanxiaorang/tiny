package top.xiaorang.springframework.context.event;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.context.ApplicationEvent;
import top.xiaorang.springframework.context.ApplicationListener;
import top.xiaorang.springframework.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/20 17:31
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster {
    private final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);

    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    protected Collection<ApplicationListener<ApplicationEvent>> getApplicationListeners(ApplicationEvent event) {
        List<ApplicationListener<ApplicationEvent>> allListeners = new ArrayList<>();
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            if (supportsEvent(applicationListener, event)) allListeners.add(applicationListener);
        }
        return allListeners;
    }

    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<?> clazz = ClassUtils.getUserClass(applicationListener);
        Type genericInterface = clazz.getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClass;
        try {
            eventClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 判定此 eventClass 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        return eventClass.isAssignableFrom(event.getClass());
    }
}
