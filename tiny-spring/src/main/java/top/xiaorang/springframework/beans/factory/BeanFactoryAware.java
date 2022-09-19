package top.xiaorang.springframework.beans.factory;

import top.xiaorang.springframework.beans.BeansException;

/**
 * @author liulei
 * @description 实现此接口，能感知到所属的beanFactory
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/20 4:24
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
