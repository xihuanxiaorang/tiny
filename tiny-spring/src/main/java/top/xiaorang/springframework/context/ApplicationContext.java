package top.xiaorang.springframework.context;

import top.xiaorang.springframework.beans.factory.HierarchicalBeanFactory;
import top.xiaorang.springframework.beans.factory.ListableBeanFactory;

/**
 * @author liulei
 * @description 应用上下文
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 22:54
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ApplicationEventPublisher {
}
