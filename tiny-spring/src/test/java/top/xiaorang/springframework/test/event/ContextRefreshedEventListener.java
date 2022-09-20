package top.xiaorang.springframework.test.event;

import top.xiaorang.springframework.context.ApplicationListener;
import top.xiaorang.springframework.context.event.ContextRefreshedEvent;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/20 18:21
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("容器刷新完成事件：" + this.getClass().getName());
    }
}
