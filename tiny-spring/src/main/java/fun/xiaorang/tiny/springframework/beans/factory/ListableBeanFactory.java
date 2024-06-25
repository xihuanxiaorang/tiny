package fun.xiaorang.tiny.springframework.beans.factory;

import fun.xiaorang.tiny.springframework.beans.BeansException;

import java.util.Map;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 13:03
 */
public interface ListableBeanFactory extends BeanFactory {
  /**
   * 根据指定类型返回 Bean 实例
   *
   * @param type Bean 类型
   * @param <T>  Bean 类型
   * @return Bean 实例
   * @throws BeansException BeansException
   */
  <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

  /**
   * 返回注册表中所有 Bean 的名称
   *
   * @return Bean 名称集合
   */
  String[] getBeanDefinitionNames();
}
