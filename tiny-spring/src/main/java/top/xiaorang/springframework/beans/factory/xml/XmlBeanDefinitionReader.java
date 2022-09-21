package top.xiaorang.springframework.beans.factory.xml;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.PropertyValue;
import top.xiaorang.springframework.beans.factory.config.BeanDefinition;
import top.xiaorang.springframework.beans.factory.config.BeanReference;
import top.xiaorang.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import top.xiaorang.springframework.beans.factory.support.BeanDefinitionRegistry;
import top.xiaorang.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import top.xiaorang.springframework.core.io.Resource;
import top.xiaorang.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liulei
 * @description Xml版本的bean定义信息读取器
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 16:48
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public static final String BEAN_ELEMENT = "bean";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String ID_ATTRIBUTE = "id";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String SCOPE_ATTRIBUTE = "scope";
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String COMPONENT_SCAN_ELEMENT = "component-scan";
    public static final String BASE_PACKAGE_ATTRIBUTE = "base-package";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        Assert.notNull(locations, "Location array must not be null");
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        parseComponentScan(root);
        parseBeanDefinitions(root);
    }

    private void parseComponentScan(Element root) {
        NodeList nl = root.getElementsByTagName(COMPONENT_SCAN_ELEMENT);
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                processComponentScan((Element) node);
            }
        }
    }

    private void processComponentScan(Element ele) {
        String scanPath = ele.getAttribute(BASE_PACKAGE_ATTRIBUTE);
        if (StrUtil.isEmpty(scanPath)) {
            throw new BeansException("The value of base-package attribute can not be empty or null");
        }
        scanPackage(scanPath);
    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.scan(basePackages);
    }

    private void parseBeanDefinitions(Element root) throws ClassNotFoundException {
        NodeList nl = root.getElementsByTagName(BEAN_ELEMENT);
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                processBeanDefinition((Element) node);
            }
        }
    }

    private void processBeanDefinition(Element ele) throws ClassNotFoundException {
        String id = ele.getAttribute(ID_ATTRIBUTE);
        String nameAttr = ele.getAttribute(NAME_ATTRIBUTE);
        String className = ele.getAttribute(CLASS_ATTRIBUTE).trim();
        Class<?> beanClass = Class.forName(className);
        String beanName = StrUtil.isNotEmpty(id) ? id : nameAttr;
        if (StrUtil.isEmpty(beanName)) {
            beanName = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        BeanDefinition beanDefinition = new BeanDefinition(beanClass);
        parseBeanDefinitionAttributes(ele, beanDefinition);
        parsePropertyElements(ele, beanDefinition);
        if (getRegistry().containsBeanDefinition(beanName)) {
            throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
        }
        getRegistry().registerBeanDefinition(beanName, beanDefinition);
    }

    private void parseBeanDefinitionAttributes(Element ele, BeanDefinition bd) {
        if (ele.hasAttribute(SCOPE_ATTRIBUTE)) {
            bd.setScope(ele.getAttribute(SCOPE_ATTRIBUTE));
        }
        if (ele.hasAttribute(INIT_METHOD_ATTRIBUTE)) {
            String initMethodName = ele.getAttribute(INIT_METHOD_ATTRIBUTE);
            bd.setInitMethodName(initMethodName);
        }
        if (ele.hasAttribute(DESTROY_METHOD_ATTRIBUTE)) {
            String destroyMethodName = ele.getAttribute(DESTROY_METHOD_ATTRIBUTE);
            bd.setDestroyMethodName(destroyMethodName);
        }
    }

    private void parsePropertyElements(Element beanEle, BeanDefinition bd) {
        NodeList nl = beanEle.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element && PROPERTY_ELEMENT.equals(node.getNodeName())) {
                parsePropertyElement((Element) node, bd);
            }
        }
    }

    private void parsePropertyElement(Element ele, BeanDefinition bd) {
        String propertyName = ele.getAttribute(NAME_ATTRIBUTE);
        String attrRef = ele.getAttribute(REF_ATTRIBUTE);
        String attrValue = ele.getAttribute(VALUE_ATTRIBUTE);
        Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
        PropertyValue pv = new PropertyValue(propertyName, value);
        bd.getPropertyValues().addPropertyValue(pv);
    }
}
