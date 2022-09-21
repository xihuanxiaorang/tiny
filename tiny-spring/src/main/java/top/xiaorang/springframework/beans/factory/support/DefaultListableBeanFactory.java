package top.xiaorang.springframework.beans.factory.support;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.config.BeanDefinition;
import top.xiaorang.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 2:42
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        for (String beanDefinitionName : getBeanDefinitionNames()) {
            getBean(beanDefinitionName);
        }
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        beanDefinitionMap.forEach(((beanName, beanDefinition) -> {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                beanNames.add(beanName);
            }
        }));
        if (1 == beanNames.size()) {
            return getBean(beanNames.get(0), requiredType);
        }
        throw new BeansException(requiredType + " expected single bean but found " + beanNames.size() + ": " + beanNames);
    }
}
