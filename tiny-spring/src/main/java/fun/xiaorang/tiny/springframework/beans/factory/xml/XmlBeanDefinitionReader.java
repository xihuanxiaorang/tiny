package fun.xiaorang.tiny.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.beans.PropertyValue;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanReference;
import fun.xiaorang.tiny.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import fun.xiaorang.tiny.springframework.beans.factory.support.BeanDefinitionRegistry;
import fun.xiaorang.tiny.springframework.core.io.Resource;
import fun.xiaorang.tiny.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 12:03
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
  public XmlBeanDefinitionReader(final BeanDefinitionRegistry registry) {
    super(registry);
  }

  public XmlBeanDefinitionReader(final BeanDefinitionRegistry registry, final ResourceLoader resourceLoader) {
    super(registry, resourceLoader);
  }

  @Override
  public void loadBeanDefinitions(final Resource resource) throws BeansException {
    try (InputStream inputStream = resource.getInputStream()) {
      doLoadBeanDefinitions(inputStream);
    } catch (IOException | ClassNotFoundException e) {
      throw new BeansException("IOException parsing XML document from " + resource, e);
    }
  }

  @Override
  public void loadBeanDefinitions(final Resource... resources) throws BeansException {
    for (final Resource resource : resources) {
      loadBeanDefinitions(resource);
    }
  }

  @Override
  public void loadBeanDefinitions(final String location) throws BeansException {
    final Resource resource = getResourceLoader().getResource(location);
    loadBeanDefinitions(resource);
  }

  @Override
  public void loadBeanDefinitions(final String... locations) throws BeansException {
    for (final String location : locations) {
      loadBeanDefinitions(location);
    }
  }

  protected void doLoadBeanDefinitions(final InputStream inputStream) throws ClassNotFoundException {
    final Document doc = XmlUtil.readXML(inputStream);
    final Element root = doc.getDocumentElement();
    final NodeList childNodes = root.getChildNodes();

    for (int i = 0; i < childNodes.getLength(); i++) {
      if (!(childNodes.item(i) instanceof final Element bean && "bean".equals(childNodes.item(i).getNodeName()))) {
        continue;
      }
      final String id = bean.getAttribute("id");
      final String name = bean.getAttribute("name");
      final String className = bean.getAttribute("class");
      final Class<?> clazz = Class.forName(className);
      String beanName = StrUtil.isNotEmpty(id) ? id : name;
      if (StrUtil.isEmpty(beanName)) {
        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
      }
      BeanDefinition beanDefinition = new BeanDefinition(clazz);
      for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
        if (!(bean.getChildNodes().item(j) instanceof final Element property && "property".equals(bean.getChildNodes().item(j).getNodeName()))) {
          continue;
        }
        final String nameAttr = property.getAttribute("name");
        final String valueAttr = property.getAttribute("value");
        final String refAttr = property.getAttribute("ref");
        Object value = StrUtil.isNotEmpty(refAttr) ? new BeanReference(refAttr) : valueAttr;
        final PropertyValue propertyValue = new PropertyValue(nameAttr, value);
        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
      }
      if (getRegistry().containsBeanDefinition(beanName)) {
        throw new BeansException("Duplicate bean name [" + beanName + "] is not allowed");
      }
      getRegistry().registerBeanDefinition(beanName, beanDefinition);
    }
  }
}
