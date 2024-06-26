package fun.xiaorang.tiny.springframework.context;

import fun.xiaorang.tiny.springframework.beans.factory.HierarchicalBeanFactory;
import fun.xiaorang.tiny.springframework.beans.factory.ListableBeanFactory;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 22:20
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory {
}
