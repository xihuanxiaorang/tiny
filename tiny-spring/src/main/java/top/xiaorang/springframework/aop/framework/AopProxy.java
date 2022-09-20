package top.xiaorang.springframework.aop.framework;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 3:31
 */
public interface AopProxy {
    /**
     * 创建一个新的代理对象
     *
     * @return 新的代理对象
     */
    Object getProxy();
}
