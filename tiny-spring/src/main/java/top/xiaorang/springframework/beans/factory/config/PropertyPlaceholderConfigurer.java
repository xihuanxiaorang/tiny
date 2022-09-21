package top.xiaorang.springframework.beans.factory.config;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.PropertyValue;
import top.xiaorang.springframework.beans.PropertyValues;
import top.xiaorang.springframework.core.io.DefaultResourceLoader;
import top.xiaorang.springframework.core.io.Resource;
import top.xiaorang.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Properties;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/22 0:15
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {
    /**
     * Default placeholder prefix: {@value}.
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    /**
     * Default placeholder suffix: {@value}.
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";
    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int endIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                        String propKey = strVal.substring(startIndex + 2, endIndex);
                        String propValue = properties.getProperty(propKey);
                        if (propValue != null) {
                            buffer.replace(startIndex, endIndex + 1, propValue);
                            propertyValue.setValue(buffer.toString());
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
