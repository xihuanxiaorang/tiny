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
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) throws ClassNotFoundException {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                if ("bean".equals(ele.getNodeName())) {
                    processBeanDefinition(ele);
                }
            }
        }
    }

    private void processBeanDefinition(Element ele) throws ClassNotFoundException {
        String id = ele.getAttribute("id");
        String nameAttr = ele.getAttribute("name");
        String className = ele.getAttribute("class").trim();
        Class<?> beanClass = Class.forName(className);
        String beanName = StrUtil.isNotEmpty(id) ? id : nameAttr;
        if (StrUtil.isEmpty(beanName)) {
            beanName = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        BeanDefinition beanDefinition = new BeanDefinition(beanClass);
        parsePropertyElements(ele, beanDefinition);
        if (getRegistry().containsBeanDefinition(beanName)) {
            throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
        }
        getRegistry().registerBeanDefinition(beanName, beanDefinition);
    }

    private void parsePropertyElements(Element beanEle, BeanDefinition bd) {
        NodeList nl = beanEle.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element && "property".equals(node.getNodeName())) {
                parsePropertyElement((Element) node, bd);
            }
        }
    }

    private void parsePropertyElement(Element ele, BeanDefinition bd) {
        String propertyName = ele.getAttribute("name");
        String attrRef = ele.getAttribute("ref");
        String attrValue = ele.getAttribute("value");
        Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
        PropertyValue pv = new PropertyValue(propertyName, value);
        bd.getPropertyValues().addPropertyValue(pv);
    }
}
