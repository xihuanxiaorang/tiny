package top.xiaorang.springframework.context.annotation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import top.xiaorang.springframework.beans.factory.config.BeanDefinition;
import top.xiaorang.springframework.beans.factory.support.BeanDefinitionRegistry;
import top.xiaorang.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 23:30
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {
    private final BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void scan(String... basePackages) {
        Assert.notEmpty(basePackages, "At least one base package must be specified");
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                candidate.setScope(resolveBeanScope(candidate));
                registry.registerBeanDefinition(determineBeanName(candidate), candidate);
            }
        }
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String beanName = "";
        if (component != null) {
            beanName = component.value();
        }
        return StrUtil.isEmpty(beanName) ? StrUtil.lowerFirst(beanClass.getSimpleName()) : beanName;
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (scope != null) return scope.value();
        return StrUtil.EMPTY;
    }
}
