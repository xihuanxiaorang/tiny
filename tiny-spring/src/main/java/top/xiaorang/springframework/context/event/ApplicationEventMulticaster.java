package top.xiaorang.springframework.context.event;

import top.xiaorang.springframework.context.ApplicationEvent;
import top.xiaorang.springframework.context.ApplicationListener;

/**
 * @author liulei
 * @description 事件多播器
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/20 17:27
 */
public interface ApplicationEventMulticaster {
    /**
     * 添加事件监听器
     *
     * @param listener 事件监听器
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 移除事件监听器
     *
     * @param listener 事件监听器
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件
     *
     * @param event 事件
     */
    void multicastEvent(ApplicationEvent event);
}
