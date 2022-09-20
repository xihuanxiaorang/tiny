package top.xiaorang.springframework.test.event;

import top.xiaorang.springframework.context.ApplicationListener;
import top.xiaorang.springframework.context.event.ContextClosedEvent;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/20 18:23
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("容器已关闭事件：" + this.getClass().getName());
    }
}
