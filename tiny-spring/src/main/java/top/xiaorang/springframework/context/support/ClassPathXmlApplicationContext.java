package top.xiaorang.springframework.context.support;

import top.xiaorang.springframework.beans.BeansException;

/**
 * @author liulei
 * @description XML文件应用上下文
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 23:58
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 配置文件中加载 bean定义信息，并刷新上下文
     *
     * @param configLocation 配置文件路径
     * @throws BeansException 异常信息
     */
    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    /**
     * 从 XML 配置文件中加载 bean定义信息，并刷新上下文
     *
     * @param configLocations 配置文件路径集合
     * @throws BeansException 异常信息
     */
    public ClassPathXmlApplicationContext(String... configLocations) throws BeansException {
        super();
        setConfigLocations(configLocations);
        refresh();
    }
}
