package top.xiaorang.springframework.context;

/**
 * @author liulei
 * @description 事件发布器
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/20 17:18
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
