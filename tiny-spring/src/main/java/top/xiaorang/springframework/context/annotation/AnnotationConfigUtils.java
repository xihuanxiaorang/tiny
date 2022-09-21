package top.xiaorang.springframework.context.annotation;

import top.xiaorang.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import top.xiaorang.springframework.beans.factory.config.BeanDefinition;
import top.xiaorang.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/22 3:20
 */
public class AnnotationConfigUtils {
    public static final String AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME =
            "org.springframework.context.annotation.internalAutowiredAnnotationProcessor";

    public static void registerAnnotationConfigProcessors(BeanDefinitionRegistry registry) {
        // 注册底层的自动装配功能的后置处理器 AutowiredAnnotationBeanPostProcessor 的 BeanDefinition
        if (!registry.containsBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)) {
            BeanDefinition def = new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class);
            registry.registerBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME, def);
        }
    }
}
