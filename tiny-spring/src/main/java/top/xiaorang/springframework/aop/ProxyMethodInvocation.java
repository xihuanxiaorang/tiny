package top.xiaorang.springframework.aop;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 3:43
 */
public interface ProxyMethodInvocation extends MethodInvocation {
    Object getProxy();

    void setArguments(Object... arguments);
}
